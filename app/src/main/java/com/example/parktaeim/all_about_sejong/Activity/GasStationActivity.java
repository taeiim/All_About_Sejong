package com.example.parktaeim.all_about_sejong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.parktaeim.all_about_sejong.Adapter.GasStationLowPriceAdapter;
import com.example.parktaeim.all_about_sejong.GeoPoint;
import com.example.parktaeim.all_about_sejong.GeoTrans;
import com.example.parktaeim.all_about_sejong.Model.GasStationItem;
import com.example.parktaeim.all_about_sejong.R;
import com.example.parktaeim.all_about_sejong.RecyclerViewClickListener;
import com.example.parktaeim.all_about_sejong.XmlGasHandler;
import com.wang.avi.AVLoadingIndicatorView;

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
    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station);

        recyclerView = (RecyclerView) findViewById(R.id.gas_recyclerview);
        avi = (AVLoadingIndicatorView) findViewById(R.id.gasStation_avi);
        avi.show();

        GeoPoint in_pt = new GeoPoint(127., 38.);
        System.out.println("geo in : xGeo="  + in_pt.getX() + ", yGeo=" + in_pt.getY());
        GeoPoint tm_pt = GeoTrans.convert(GeoTrans.GEO, GeoTrans.TM, in_pt);
        System.out.println("tm : xTM=" + tm_pt.getX() + ", yTM=" + tm_pt.getY());
        GeoPoint katec_pt = GeoTrans.convert(GeoTrans.TM, GeoTrans.KATEC, tm_pt);
        System.out.println("katec : xKATEC=" + katec_pt.getX() + ", yKATEC=" + katec_pt.getY());
        GeoPoint out_pt = GeoTrans.convert(GeoTrans.KATEC, GeoTrans.GEO, katec_pt);
        System.out.println("geo out : xGeo=" + out_pt.getX() + ", yGeo=" + out_pt.getY());
        GeoPoint in2_pt = new GeoPoint(128., 38.);
        System.out.println("geo distance between (127,38) and (128,38) =" + GeoTrans.getDistancebyGeo(in_pt, in2_pt) + "km");


        parsingLowestPriceArrayList();
        setUpIntent();
    }

    private void setUpIntent() {
        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(GasStationActivity.this,GasStationDetailActivity.class);
                intent.putExtra("gasStationID",gasStationItemArrayList.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
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
                        avi.hide();
                    }
                });
            }
        }.start();

    }

    private void setUpRecyclerView() {
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new GasStationLowPriceAdapter(gasStationItemArrayList);
        recyclerView.setAdapter(adapter);
    }
}
