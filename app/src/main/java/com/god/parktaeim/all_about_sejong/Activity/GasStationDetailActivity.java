package com.god.parktaeim.all_about_sejong.Activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.R;
import com.god.parktaeim.all_about_sejong.KeyWord;
import com.god.parktaeim.all_about_sejong.Model.GasStationDetailItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by parktaeim on 2018. 3. 17..
 */

public class GasStationDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    ArrayList<GasStationDetailItem> gasStationDetailItems = new ArrayList<>();
    private AVLoadingIndicatorView avi;
    private GoogleMap googleMap;
    private ImageView callIcon;
    public static StringBuilder sb;

    private String brand;
    private String name;
    private String address_new;
    private String tellNum;
    private String type;
    private boolean isMaint;  //경정비 시설 존재 여부
    private boolean isCarWash;  //세차장 존재 여부
    private boolean isConvenience;  //편의점 존재 여부
    private boolean isKPETRO; // 품질인증주유소 여부
    private double latitude;
    private double longitude;

    //휘발유
    private String B027;
    private int B027_price;
    private String B027_date;
    private String B027_time;

    //경유
    private String D047;
    private int D047_price;
    private String D047_date;
    private String D047_time;

    //고급휘발유
    private String B034;
    private int B034_price;
    private String B034_date;
    private String B034_time;

    //실내등유
    private String C004;
    private int C004_price;
    private String C004_date;
    private String C004_time;

    //자동차부탄
    private String K015;
    private int K015_price;
    private String K015_date;
    private String K015_time;

    private ImageView carFacilityImgView;
    private ImageView carWashImgView;
    private ImageView convenienceStoreImgView;
    private ImageView KPETROImgView;

    private TextView carFacilityTextView;
    private TextView carWashTextView;
    private TextView convenienceStoreTextView;
    private TextView KPETROTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station_detail);

        init();

        avi = (AVLoadingIndicatorView) findViewById(R.id.gasStationDetail_avi);
        avi.show();

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.gasDetail_googleMap);
        mapFragment.getMapAsync(this);

        callIcon = (ImageView) findViewById(R.id.gasDetail_callIcon);
        ImageView backIcon = (ImageView) findViewById(R.id.gasDetail_backIcon);
        backIcon.setOnClickListener(v -> finish());

        getGasStationInfo();

    }

    private void init() {
        carFacilityImgView = (ImageView) findViewById(R.id.gasDetail_isMaintImgView);
        carWashImgView = (ImageView) findViewById(R.id.gasDetail_isCarWashImgView);
        convenienceStoreImgView = (ImageView) findViewById(R.id.gasDetail_isConvenienceImgView);
        KPETROImgView = (ImageView) findViewById(R.id.gasDetail_isKPETROImgView);

        carFacilityTextView = (TextView) findViewById(R.id.gasDetail_isMaintTv);
        carWashTextView = (TextView) findViewById(R.id.gasDetail_isCarWashTv);
        convenienceStoreTextView = (TextView) findViewById(R.id.gasDetail_isConvenienceTv);
        KPETROTextView = (TextView) findViewById(R.id.gasDetail_isKPETROTv);
    }

    private void setUpIsLayout(){
        boolean[] booleanArr = {isMaint,isCarWash,isConvenience,isKPETRO};
        ImageView[] isLayoutImgViewArr = {carFacilityImgView,carWashImgView,convenienceStoreImgView,KPETROImgView};
        TextView[] isLayoutTvArr = {carFacilityTextView, carWashTextView, convenienceStoreTextView, KPETROTextView};

        for(int i=0;i<booleanArr.length;i++){
            if(booleanArr[i] == false){
                isLayoutImgViewArr[i].setColorFilter(Color.LTGRAY);
                isLayoutTvArr[i].setTextColor(Color.GRAY);
            }else{
            }
        }
    }
    private void getGasStationInfo() {
        Intent getIdIntent = getIntent();
        String gasStationID = getIdIntent.getExtras().getString("gasStationID");

        new Thread() {
            @Override
            public void run() {
                super.run();
                BufferedInputStream inputStream = null;
                try {
                    URL url = new URL(
                            "http://www.opinet.co.kr/api/detailById.do?code=" + KeyWord.OPINET_KEY_CODE + "&id=" + gasStationID + "&out=json"
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

                    changeAddressToWgs84();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setUpMap(latitude,longitude);
                            setUpIsLayout();

                            TextView nameTv = (TextView) findViewById(R.id.gasDetail_nameTv);
                            TextView brandTv = (TextView) findViewById(R.id.gasDetail_brandTv);
                            TextView typeTv = (TextView) findViewById(R.id.gasDetail_typeTv);
                            TextView tellNumTv = (TextView) findViewById(R.id.gasDetail_tellNumTv);
                            TextView addressTv = (TextView) findViewById(R.id.gasDetail_addressTv);

                            nameTv.setText(name);
                            brandTv.setText(brand);
                            typeTv.setText(type);
                            tellNumTv.setText(tellNum);
                            addressTv.setText(address_new);

                            callIcon.setOnClickListener(v -> {
                                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + tellNum)));
                            });

                            setUpTextOilPrice();

                            LinearLayout contentLayout = (LinearLayout) findViewById(R.id.gasDetail_contentLayout);
                            contentLayout.setVisibility(View.VISIBLE);
                            avi.hide();
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

    private void changeAddressToWgs84(){
        String clientId = "eHm6juoXIARjUBibf0n1";// 애플리케이션 클라이언트 아이디값";
        String clientSecret = "h7O6vSoxwj";// 애플리케이션 클라이언트 시크릿값";\
        try {
            String text = URLEncoder.encode(address_new, "utf-8");
            String apiURL = "https://openapi.naver.com/v1/map/geocode?encoding=utf-8&coordType=latlng&query="+text;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            con.disconnect();
//            System.out.println(sb.toString());

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONObject pointObject = jsonObject.getJSONObject("result").getJSONArray("items").getJSONObject(0).getJSONObject("point");

            latitude = pointObject.getDouble("y");
            longitude = pointObject.getDouble("x");

            System.out.println("latitude=="+latitude+ "    longitude==="+longitude);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void setUpTextOilPrice() {
        TextView B027_tradeTv = (TextView) findViewById(R.id.B027_standTv);
        TextView B027_priceTv = (TextView) findViewById(R.id.B027_priceTv);
        TextView D047_tradeTv = (TextView) findViewById(R.id.D047_standTv);
        TextView D047_priceTv = (TextView) findViewById(R.id.D047_priceTv);
        TextView B034_tradeTv = (TextView) findViewById(R.id.B034_standTv);
        TextView B034_priceTv = (TextView) findViewById(R.id.B034_priceTv);
        TextView C004_tradeTv = (TextView) findViewById(R.id.C004_standTv);
        TextView C004_priceTv = (TextView) findViewById(R.id.C004_priceTv);
        TextView K015_tradeTv = (TextView) findViewById(R.id.K015_standTv);
        TextView K015_priceTv = (TextView) findViewById(R.id.K015_priceTv);

        RelativeLayout B027Layout = (RelativeLayout) findViewById(R.id.B027Layout);
        RelativeLayout D047Layout = (RelativeLayout) findViewById(R.id.D047Layout);
        RelativeLayout B034Layout = (RelativeLayout) findViewById(R.id.B034Layout);
        RelativeLayout C004Layout = (RelativeLayout) findViewById(R.id.C004Layout);
        RelativeLayout K015Layout = (RelativeLayout) findViewById(R.id.K015Layout);

        if (B027 != null && B027.length() != 0) {
            B027Layout.setVisibility(View.VISIBLE);
            B027_priceTv.setText(String.valueOf(B027_price) + "원");
            B027_tradeTv.setText(changeDateFormat(B027_date + B027_time));
        }
        if (D047 != null && D047.length() != 0) {
            D047Layout.setVisibility(View.VISIBLE);
            D047_priceTv.setText(String.valueOf(D047_price) + "원");
            D047_tradeTv.setText(changeDateFormat(D047_date + D047_time));
        }
        if (B034 != null && B034.length() != 0) {
            B034Layout.setVisibility(View.VISIBLE);
            B034_priceTv.setText(String.valueOf(B034_price) + "원");
            B034_tradeTv.setText(changeDateFormat(B034_date + B034_time));
        }
        if (C004 != null && C004.length() != 0) {
            C004Layout.setVisibility(View.VISIBLE);
            C004_priceTv.setText(String.valueOf(C004_price) + "원");
            C004_tradeTv.setText(changeDateFormat(C004_date + C004_time));
        }
        if (K015 != null && K015.length() != 0) {
            K015Layout.setVisibility(View.VISIBLE);
            K015_priceTv.setText(String.valueOf(K015_price) + "원");
            K015_tradeTv.setText(changeDateFormat(K015_date + K015_time));
        }

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

            if (jsonObject.getString("KPETRO_YN").equals("Y")) {
                isConvenience = true;
            } else isConvenience = false;

            for (int i = 0; i < oilPriceArray.length(); i++) {
                JSONObject oilPriceObject = (JSONObject) oilPriceArray.get(i);
                if (oilPriceObject.getString("PRODCD").equals("B027")) {
                    B027 = "휘발유";
                    B027_price = oilPriceObject.getInt("PRICE");
                    B027_date = oilPriceObject.getString("TRADE_DT");
                    B027_time = oilPriceObject.getString("TRADE_TM");

                } else if (oilPriceObject.getString("PRODCD").equals("D047")) {
                    D047 = "경유";
                    D047_price = oilPriceObject.getInt("PRICE");
                    D047_date = oilPriceObject.getString("TRADE_DT");
                    D047_time = oilPriceObject.getString("TRADE_TM");

                } else if (oilPriceObject.getString("PRODCD").equals("B034")) {
                    B034 = "고급휘발유";
                    B034_price = oilPriceObject.getInt("PRICE");
                    B034_date = oilPriceObject.getString("TRADE_DT");
                    B034_time = oilPriceObject.getString("TRADE_TM");

                } else if (oilPriceObject.getString("PRODCD").equals("C004")) {
                    C004 = "실내등유";
                    C004_price = oilPriceObject.getInt("PRICE");
                    C004_date = oilPriceObject.getString("TRADE_DT");
                    C004_time = oilPriceObject.getString("TRADE_TM");

                } else if (oilPriceObject.getString("PRODCD").equals("K015")) {
                    K015 = "자동차부탄";
                    K015_price = oilPriceObject.getInt("PRICE");
                    K015_date = oilPriceObject.getString("TRADE_DT");
                    K015_time = oilPriceObject.getString("TRADE_TM");
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

    private String changeDateFormat(String beforeDate) {
        SimpleDateFormat beforeDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd. HH:mm", Locale.KOREA);
        String resultStr = "";

        try {
            resultStr = "(기준: " + simpleDateFormat.format(beforeDateFormat.parse(beforeDate)) + ")";
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resultStr;
    }

    public void setUpMap(double lat, double lon) {
        LatLng dest = new LatLng(lat, lon);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(dest);

        if(googleMap!= null){
            googleMap.addMarker(markerOptions);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dest,15));
        }else {
            //Google Map Is NULL
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMinZoomPreference(6);
    }

}