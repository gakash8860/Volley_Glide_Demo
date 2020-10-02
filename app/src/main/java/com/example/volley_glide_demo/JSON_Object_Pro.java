package com.example.volley_glide_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class JSON_Object_Pro extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    ImageView i;
    Button b1;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_s_o_n__object__pro);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        i=findViewById(R.id.imageView2);
        et1=findViewById(R.id.et);
        b1=findViewById(R.id.button5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=et1.getText().toString();
                if(!id.equals("")) {
                    callVolley3(id);
                }else{
                    Toast.makeText(JSON_Object_Pro.this, "PLz enter Id", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void callVolley3(String id){
        String url="https://api.github.com/users/"+id;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                PojoData data=gson.fromJson(response,PojoData.class);
                t1.setText(data.getName());
                t2.setText(data.getEmail()+" ");
                t3.setText(data.getAvatarUrl());
                t4.setText(data.getCompany()+"");
                Glide.with(JSON_Object_Pro.this).load(data.getAvatarUrl()).into(i);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                t1.setText(error.getMessage());
            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }
}