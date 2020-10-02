package com.example.volley_glide_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JSON_Array_Pro extends AppCompatActivity {
    RecyclerView rc;
    List<PojoData> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_s_o_n__array__pro);
        rc=findViewById(R.id.rc);
        rc.setLayoutManager(new LinearLayoutManager(this));
        callVolley4();
    }
    public void callVolley4(){
        String url="https://api.github.com/users";
        JsonArrayRequest request =new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    Gson gson=new Gson();
                    try {
                        PojoData data=gson.fromJson(response.getJSONObject(i).toString(),PojoData.class);
                        list.add(data);
                        RecyclerAdapter adapter =new RecyclerAdapter(JSON_Array_Pro.this,list);
                        rc.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JSON_Array_Pro.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);

    }
}