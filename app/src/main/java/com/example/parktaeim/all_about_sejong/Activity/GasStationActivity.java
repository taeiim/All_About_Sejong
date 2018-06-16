package com.example.parktaeim.all_about_sejong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.parktaeim.all_about_sejong.Adapter.GasStationLowPriceAdapter;
import com.example.parktaeim.all_about_sejong.InfoDialog;
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

        ImageView backIcon = (ImageView) findViewById(R.id.gas_backIcon);
        backIcon.setOnClickListener(v->finish());
        recyclerView = (RecyclerView) findViewById(R.id.gas_recyclerview);
        avi = (AVLoadingIndicatorView) findViewById(R.id.gasStation_avi);
        avi.show();

        parsingLowestPriceArrayList();
        setUpIntent();
        setUpInfoDialog();
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

    private void setUpInfoDialog() {
        String infoStr = "주유소 가격 정보는 매일 업데이트 됩니다.\n\n근처 주유소, 세종시 전체 주유소 기능 업데이트 준비중입니다. ";

        ImageView infoIcon = (ImageView) findViewById(R.id.gas_infoIcon);
        infoIcon.setOnClickListener(v->{
            FragmentManager fragmentManager = getSupportFragmentManager();
            InfoDialog infoDialog = new InfoDialog();

            Bundle args = new Bundle();
            args.putString("infoText",infoStr);

            infoDialog.setArguments(args);
            infoDialog.show(fragmentManager,"infoText");
        });
    }
}
