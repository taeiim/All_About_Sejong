package com.example.parktaeim.all_about_sejong.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.parktaeim.all_about_sejong.Adapter.GasStationLowPriceAdapter;
import com.example.parktaeim.all_about_sejong.GeoPoint;
import com.example.parktaeim.all_about_sejong.GeoTrans;
import com.example.parktaeim.all_about_sejong.Model.GasStationItem;
import com.example.parktaeim.all_about_sejong.R;
import com.example.parktaeim.all_about_sejong.XmlGasHandler;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by parktaeim on 2018. 2. 13..
 */

public class GasStationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GasStationLowPriceAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<GasStationItem> gasStationItemArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station);

        parsingLowestPriceArrayList();
    }


    private void parsingLowestPriceArrayList() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(
                            "http://www.opinet.co.kr/api/lowTop10.do?out=xml&code=F260180226&prodcd=B027&area=1901"
                    );

                    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                    SAXParser parser = saxParserFactory.newSAXParser();

                    XMLReader xmlReader = parser.getXMLReader();

                    XmlGasHandler xmlGasHandler = new XmlGasHandler();
                    xmlReader.setContentHandler(xmlGasHandler);
                    xmlReader.parse(new InputSource(url.openStream()));

                    gasStationItemArrayList = xmlGasHandler.getLowPriceArrayList();


                } catch (Exception e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setUpRecyclerView();
                    }
                });
            }
        }.start();

    }

    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.gas_recyclerview);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new GasStationLowPriceAdapter(gasStationItemArrayList);
        recyclerView.setAdapter(adapter);
    }
}
