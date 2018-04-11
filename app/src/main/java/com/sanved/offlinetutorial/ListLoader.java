package com.sanved.offlinetutorial;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Sanved on 21-05-2016.
 */
public class ListLoader extends AppCompatActivity {

    RecyclerView rv;
    Toolbar toolbar;

    RVAdapt adapt;

    ArrayList<DataKaRakhwala> list;

    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_loader);

        if(savedInstanceState == null){
            Bundle bundle = getIntent().getExtras();
            if(bundle != null){
                type = bundle.getString("type");
            }
        }else{
            type = (String) savedInstanceState.getSerializable("type");
        }

        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setTitle("Select tutorial");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_36dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        jsonValidate();

    }

    public String takeJSONfromAssets() {
        String json = null;
        try {
            //Take JSONObbject into a stream
            InputStream is = getAssets().open(type);
            //Using a bytestream, copy the data from the json to a string using UTF 8 encoding to escape the slashes and quotes
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void jsonValidate(){
        try {

            JSONObject jobj = new JSONObject(takeJSONfromAssets());

            JSONArray jarr = jobj.getJSONArray("links");

            HashMap<String, String> hmtemp;

            list = new ArrayList<DataKaRakhwala>();

            for (int i = 0; i < jarr.length(); i++) {

                JSONObject jo_inside = jarr.getJSONObject(i);

                String name = jo_inside.getString("name");
                int pages = jo_inside.getInt("pages");
                String filename = jo_inside.getString("filename");

                list.add(new DataKaRakhwala(name,pages,filename));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Context con = ListLoader.this.getApplication();
        adapt = new RVAdapt(list, con);

        rv.setAdapter(adapt);
    }


}



