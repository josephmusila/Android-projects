package com.musila.retrofit;

import com.musila.retrofit.model.SearchResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("w/api.php")
    Call<SearchResponse> searchWiki(@QueryMap Map<String,String> params);
}
