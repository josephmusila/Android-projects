package com.musila.football;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.musila.football.model.DataItem;
import com.musila.football.model.ResponseData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText search;
    private Button go;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search=findViewById(R.id.search);
        go=findViewById(R.id.go);
        listView=findViewById(R.id.listview);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query=search.getText().toString();

                if(query.isEmpty()){
                    Toast.makeText(MainActivity.this,"enter your query",Toast.LENGTH_SHORT).show();
                    return;
                }
               ApiService apiService=AppClient.getApiClient().create(ApiService.class);
                Map<String,String> params=new HashMap<>();
                params.put("apikey","302a04f0-7c4c-11eb-bd81-0bbd9ae9a255");
                params.put("country_id","48");
                params.put("format","json");
                params.put("srsearch",query);

                Call<ResponseData> call=apiService.searchPlayers(params);

                call.enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        ArrayList<String> results=new ArrayList<>();
                        String dataItems=response.body().getQuery().getList();

                        for(DataItem d:dataItems){
                            results.add(d.getFirstname());

                        }
                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,results);
                        listView.setAdapter(arrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {

                    }
                });
            }
        });
    }

}