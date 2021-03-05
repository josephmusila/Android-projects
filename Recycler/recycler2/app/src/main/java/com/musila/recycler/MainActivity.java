package com.musila.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String url="https://myjson.dit.upm.es/api/bins/qvm";
    RecyclerView recyclerView;
    ArticleAdapter adapter;
    ArrayList<Article> articles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ArticleAdapter();

        recyclerView.setAdapter(adapter);
        articles=new ArrayList<>();
        getData();


    }

    private void getData() {
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               try {
                   for (int i=0; i<response.length();i++){
                       JSONObject jsonObject=response.getJSONObject(i);
                       Article article=new Article();
                       article.setImage(jsonObject.getString("name"));
                       article.setTitle(jsonObject.getString("title"));
                       article.setBody(jsonObject.getString("article"));
                       articles.add(article);
                   }

               }catch (JSONException e){
                   Toast.makeText(MainActivity.this,"Json is not valid",Toast.LENGTH_SHORT).show();
               }
                adapter.setData(articles);
               adapter.notifyDataSetChanged();
               progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Error occured",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}