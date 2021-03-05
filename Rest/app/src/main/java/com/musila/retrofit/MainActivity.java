package com.musila.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.musila.retrofit.model.SearchItem;
import com.musila.retrofit.model.SearchResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button send;
    private ProgressBar progressBar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.input);
        send=findViewById(R.id.send);
        progressBar=findViewById(R.id.progressbar);
        listView=findViewById(R.id.listview);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query=editText.getText().toString();

                if (query.isEmpty()){
                    Toast.makeText(MainActivity.this,"Enter your Query First",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                ApiService apiService=ApiClient.getApiClient().create(ApiService.class);
                Map<String,String> params=new HashMap<>();
                params.put("action","query");
                params.put("format","json");
                params.put("list","search");
                params.put("srsearch",query);

                Call<SearchResponse> call=apiService.searchWiki(params);
                call.enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        progressBar.setVisibility(View.GONE);

                        //update listview

                        ArrayList<String> results=new ArrayList<>();
                        List<SearchItem> searchItems= response.body().getQuery().getSearch();

                        for(SearchItem s:searchItems){
                            results.add(s.getTitle());
                        }
                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,results);
                        listView.setAdapter(arrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this,"Could not return data",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}