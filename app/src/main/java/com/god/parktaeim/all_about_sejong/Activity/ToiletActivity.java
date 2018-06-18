package com.god.parktaeim.all_about_sejong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.R;
import com.god.parktaeim.all_about_sejong.Adapter.ToiletRecyclerViewAdapter;
import com.god.parktaeim.all_about_sejong.Model.ToiletItem;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 3. 9..
 */

public class ToiletActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ToiletRecyclerViewAdapter adapter;
    private ImageView backIcon;
    private ImageView searchIcon;

    private String jsonString = null;
    ArrayList<ToiletItem> toiletItemArrayList;
    private TextView intentToiletMaps;
    private MaterialSearchView searchView;
    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet);

        backIcon = (ImageView) findViewById(R.id.toilet_backIcon);
        intentToiletMaps = (TextView) findViewById(R.id.intentMapToilet_textView);
        backIcon.setOnClickListener(v -> finish());
        intentToiletMaps.setOnClickListener(v -> {
            Intent intent = new Intent(ToiletActivity.this, ToiletMapsActivity.class);
            intent.putExtra("ToiletArrayList", toiletItemArrayList);
            startActivity(intent);
        });

        searchView = (MaterialSearchView) findViewById(R.id.toilet_searchView);
        searchIcon = (ImageView) findViewById(R.id.toilet_icon_search);
        avi = (AVLoadingIndicatorView) findViewById(R.id.toilet_avi);

        startAvi();
        getToiletData();

    }

    private void setUpsearchView(){
        searchIcon.setOnClickListener(v->searchView.showSearch());

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
            }
        });
    }

    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.toilet_recyclerview);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ToiletRecyclerViewAdapter(toiletItemArrayList);
        recyclerView.setAdapter(adapter);
    }

    private void getToiletData() {
        String toiletData_json = "";
        try {
            InputStream is = getApplicationContext().getAssets().open("ToiletData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            toiletData_json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(toiletData_json);
            JSONObject resultObject = (JSONObject) jsonObject.get("result");
            JSONArray resultArray = resultObject.getJSONArray("records");

            toiletItemArrayList = getToiletList(resultArray);

            setUpRecyclerView();
            setUpsearchView();
            stopAvi();
            searchIcon.setVisibility(View.VISIBLE);
            intentToiletMaps.setVisibility(View.VISIBLE);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 공중화장실 데이터 가져오기
        /*
        new Thread() {
            @Override
            public void run() {
                super.run();
                BufferedInputStream inputStream = null;
                try {
                    URL url = new URL(
                            "http://data.sejong.go.kr/api/action/datastore_search.jsp?resource_id=8c31e7b8-bfee-47c8-bb7c-a746e6313ee7&serviceKey="
                                    + "nUVCZK/itjQn5Te5oRqRow=="
                    );

                    inputStream = new BufferedInputStream(url.openStream());
                    StringBuffer stringBuffer = new StringBuffer();

                    int i;
                    byte[] b = new byte[4096];
                    while ((i = inputStream.read(b)) != -1) {
                        stringBuffer.append(new String(b, 0, i));
                    }

                    jsonString = stringBuffer.toString();

                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONObject resultObject = (JSONObject) jsonObject.get("result");
                    JSONArray resultArray = resultObject.getJSONArray("records");

                    toiletItemArrayList = getToiletList(resultArray);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setUpRecyclerView();
                            setUpsearchView();
                            stopAvi();
                            searchIcon.setVisibility(View.VISIBLE);
                            intentToiletMaps.setVisibility(View.VISIBLE);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        */
    }

    private ArrayList<ToiletItem> getToiletList(JSONArray resultArray) {
        ArrayList<ToiletItem> arrayList = new ArrayList<>();
        for (int i =0; i<resultArray.length(); i++){
            try {
                JSONObject jsonObject = (JSONObject) resultArray.get(i);
                String name = jsonObject.getString("화장실명");
                String tellNum = jsonObject.getString("전화번호");
                String address = jsonObject.getString("소재지지번주소");
                String openTime = jsonObject.getString("개방시간");
                double latitude = jsonObject.getDouble("위도");
                double longitude = jsonObject.getDouble("경도");

                if(name.length() != 0 && tellNum.length()!=0 && address.length() != 0 && openTime.length() !=0 && String.valueOf(latitude).length() !=0 ){
                    arrayList.add(new ToiletItem(openTime,address,latitude,longitude,tellNum,name));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return arrayList;
    }


    void startAvi(){
        avi.show();
    }

    void stopAvi(){
        avi.hide();
    }
}
