package com.example.parktaeim.all_about_sejong.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.DayCareCenterItem;
import com.example.parktaeim.all_about_sejong.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 2. 2..
 */

public class DayCareCenterActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView backIcon;
    private TextView nearTextView;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AllCenterRecyclerViewAdapter adapter;

    private StringBuffer stringBuffer;
    private String jsonString = null;
    ArrayList<DayCareCenterItem> dayCareCenterItemArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_care_center);

        backIcon = (ImageView) findViewById(R.id.center_backIcon);
        backIcon.setOnClickListener(v->finish());

        setRecycerView();
    }

    private void setRecycerView() {

        getData();



    }


    private void getData(){
        new Thread() {
            @Override
            public void run() {
                super.run();
                BufferedInputStream inputStream = null;
                try {
                    URL url = new URL(
                            "http://data.sejong.go.kr/api/action/datastore_search.jsp?resource_id=b6a4dcd2-fc38-4d75-97bf-ee603082b205&serviceKey="
                                    + "nUVCZK/itjQn5Te5oRqRow=="
                    );

                    inputStream = new BufferedInputStream(url.openStream());
                    StringBuffer stringBuffer = new StringBuffer();

                    int i;
                    byte[] b = new byte[4096];
                    while ((i = inputStream.read(b)) != -1) {
                        stringBuffer.append(new String(b, 0, i));
                    }

                    Log.d("get JSON=====", stringBuffer.toString());
                    System.out.println(stringBuffer.toString());

                    jsonString = stringBuffer.toString();

                    JSONObject jsonObject = new JSONObject(jsonString);
                    Log.d("after JSON===", jsonObject.toString());

                    JSONObject resultObject = (JSONObject) jsonObject.get("result");
                    Log.d("after JSONARRAY===", resultObject.toString());

                    JSONArray resultArray = resultObject.getJSONArray("records");
                    Log.d("RESULT JSON===", resultArray.toString());

                    dayCareCenterItemArrayList = getArrayList(resultArray);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView = (RecyclerView) findViewById(R.id.allCenter_recyclerView);
                            layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());

                            adapter = new AllCenterRecyclerViewAdapter(dayCareCenterItemArrayList);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
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

    private ArrayList<DayCareCenterItem> getArrayList(JSONArray resultArray) {
        ArrayList<DayCareCenterItem> arrayList = new ArrayList<>();
        try{
            for(int i=0;i< resultArray.length();i++){
                JSONObject jsonObject = (JSONObject) resultArray.get(i);
                String name = jsonObject.getString("어린이집명");
                String type = jsonObject.getString("어린이집유형구분");
                String  isCarString = jsonObject.getString("통학차량운영여부");
                Boolean isCar = null;
                if(isCarString.equals("Y")){
                    isCar = true;
                }else if(isCarString.equals("N")){
                    isCar = false;
                }
                String tellNum = jsonObject.getString("어린이집전화번호");
                int cctvCount = jsonObject.getInt("CCTV설치수");
                String address = jsonObject.getString("소재지도로명주소");
                int studentCount = jsonObject.getInt("정원수");
                int teacherCount = jsonObject.getInt("보육교직원수");
                int playgroundCount = jsonObject.getInt("놀이터수");
                int roomCount = jsonObject.getInt("보육실수");

                arrayList.add(new DayCareCenterItem(null,name,type,isCar,tellNum,cctvCount,address,studentCount,teacherCount,playgroundCount,roomCount));

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Log.d("arrayList",arrayList.toString());
        return arrayList;
    }

    @Override
    public void onClick(View v) {

    }
}
