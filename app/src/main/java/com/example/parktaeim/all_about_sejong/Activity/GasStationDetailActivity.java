package com.example.parktaeim.all_about_sejong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.Adapter.AllCenterRecyclerViewAdapter;
import com.example.parktaeim.all_about_sejong.KeyWord;
import com.example.parktaeim.all_about_sejong.Model.DayCareCenterItem;
import com.example.parktaeim.all_about_sejong.Model.GasStationDetailItem;
import com.example.parktaeim.all_about_sejong.R;
import com.example.parktaeim.all_about_sejong.XmlGasHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by parktaeim on 2018. 3. 17..
 */

public class GasStationDetailActivity extends AppCompatActivity {
    ArrayList<GasStationDetailItem> gasStationDetailItems = new ArrayList<>();

    private String brand;
    private String name;
    private String address_new;
    private String tellNum;
    private String type;
    private boolean isMaint;
    private boolean isCarWash;
    private boolean isConvenience;
    private double latitude;
    private double longitude;

    //휘발유
    private String B027;
    private int B027_price;
    private String B027_date;

    //경유
    private String D047;
    private int D047_price;
    private String D047_date;

    //고급휘발유
    private String B034;
    private int B034_price;
    private String B034_date;

    //실내등유
    private String C004;
    private int C004_price;
    private String C004_date;

    //자동차부탄
    private String K015;
    private int K015_price;
    private String K015_date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station_detail);

        getGasStationInfo();
    }

    private void getGasStationInfo() {
        Intent getIdIntent = getIntent();
        String gasStationID = getIdIntent.getExtras().getString("gasStationID");
        Log.d("GasStationID===========", gasStationID);

        new Thread() {
            @Override
            public void run() {
                super.run();
                BufferedInputStream inputStream = null;
                try {
                    URL url = new URL(
                            "http://www.opinet.co.kr/api/detailById.do?code=" + KeyWord.KEY_CODE + "&id=" + gasStationID + "&out=json"
                    );

                    inputStream = new BufferedInputStream(url.openStream());
                    StringBuffer stringBuffer = new StringBuffer();

                    int i;
                    byte[] b = new byte[4096];
                    while ((i = inputStream.read(b)) != -1) {
                        stringBuffer.append(new String(b, 0, i));
                    }

                    String jsonString = stringBuffer.toString();

                    JSONObject firstObject = new JSONObject(jsonString);
                    JSONObject resultObject = (JSONObject) firstObject.get("RESULT");
                    JSONArray resultArray = resultObject.getJSONArray("OIL");
                    Log.d("RESULT JSON===", resultArray.toString());


                    JSONObject jsonObject = (JSONObject) resultArray.get(0);
                    name = jsonObject.getString("OS_NM");
                    address_new = jsonObject.getString("NEW_ADR");
                    brand = jsonObject.getString("POLL_DIV_CO");
                    String tellNum = jsonObject.getString("TEL");
                    type = jsonObject.getString("LPG_YN");
                    latitude = jsonObject.getDouble("GIS_Y_COOR");
                    longitude = jsonObject.getDouble("GIS_X_COOR");
                    JSONArray oilPriceArray = jsonObject.getJSONArray("OIL_PRICE");


                    setUpDataString(jsonObject, oilPriceArray);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView nameTv = (TextView) findViewById(R.id.gasDetail_nameTv);
                            TextView typeTv = (TextView) findViewById(R.id.gasDetail_typeTv);

                            nameTv.setText(name);
                            typeTv.setText(type);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

    }

    private void setUpDataString(JSONObject jsonObject, JSONArray oilPriceArray) {
        try {
            if (jsonObject.getString("MAINT_YN").equals("Y")) {
                isMaint = true;
            } else isMaint = false;

            if (jsonObject.getString("CAR_WASH_YN").equals("Y")) {
                isCarWash = true;
            } else isCarWash = false;

            if (jsonObject.getString("CVS_YN").equals("Y")) {
                isConvenience = true;
            } else isConvenience = false;


            for (int k = 0; k < oilPriceArray.length(); k++) {
                JSONObject oilPriceObject = (JSONObject) oilPriceArray.get(k);
                if (oilPriceObject.getString("PRODCD").equals("B027")) {
                    B027 = "휘발유";
                    B027_price = oilPriceObject.getInt("PRICE");
                    B027_date = oilPriceObject.getString("TRADE_DT");

                } else if (oilPriceObject.getString("PRODCD").equals("D047")) {
                    D047 = "경유";
                    D047_price = oilPriceObject.getInt("PRICE");
                    D047_date = oilPriceObject.getString("TRADE_DT");

                } else if (oilPriceObject.getString("PRODCD").equals("B034")) {
                    B034 = "고급휘발유";
                    B034_price = oilPriceObject.getInt("PRICE");
                    B034_date = oilPriceObject.getString("TRADE_DT");

                } else if (oilPriceObject.getString("PRODCD").equals("C004")) {
                    C004 = "실내등유";
                    C004_price = oilPriceObject.getInt("PRICE");
                    C004_date = oilPriceObject.getString("TRADE_DT");

                } else if (oilPriceObject.getString("PRODCD").equals("K015")) {
                    K015 = "자동차부탄";
                    K015_price = oilPriceObject.getInt("PRICE");
                    K015_date = oilPriceObject.getString("TRADE_DT");

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (brand.equals("SKE")) brand = "SK 에너지";
        else if (brand.equals("GSC")) brand = "GS 칼텍스";
        else if (brand.equals("HDO")) brand = "현대오일뱅크";
        else if (brand.equals("SOL")) brand = "S-OIL";
        else if (brand.equals("RTO")) brand = "자영알뜰";
        else if (brand.equals("RTX")) brand = "고속도로알뜰";
        else if (brand.equals("NHO")) brand = "농협알뜰";
        else if (brand.equals("ETC")) brand = "자가상표";
        else if (brand.equals("E1G")) brand = "E1";
        else if (brand.equals("SKG")) brand = "SK가스";

        if (type.equals("N")) type = "주유소";
        else if (type.equals("Y")) type = "자동차 충전소";
        else if (type.equals("C")) type = "주유소/충전소 겸업";

    }
}
