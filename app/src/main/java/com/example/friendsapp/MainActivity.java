package com.example.friendsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    AppCompatButton b1,b2;
    String apiurl="https://friendsapi-re5a.onrender.com/adddata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.nameid);
        e2=(EditText) findViewById(R.id.fnameid);
        e3=(EditText) findViewById(R.id.fnicknameid);
        e4=(EditText) findViewById(R.id.disid);
        b1=(AppCompatButton) findViewById(R.id.subid);
        b2=(AppCompatButton) findViewById(R.id.viewid);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), VIEWALL.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getname=e1.getText().toString();
                String getfriendname=e2.getText().toString();
                String getfriendnickname=e3.getText().toString();
                String getdiscription=e4.getText().toString();
                JSONObject friend=new JSONObject();
                try {
                    friend.put("name",getname);
                    friend.put("friendName",getfriendname);
                    friend.put("friendNickName",getfriendnickname);
                    friend.put("DescribeYourFriend",getdiscription);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST, apiurl, friend,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                e1.setText("");
                                e2.setText("");
                                e3.setText("");
                                e4.setText("");
                                Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                //Request Queue
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });

    }
}