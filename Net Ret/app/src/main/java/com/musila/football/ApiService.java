package com.musila.football;

import com.musila.football.model.ResponseData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {
    @GET("soccer/players?")
   Call<ResponseData> searchPlayers(@QueryMap Map<String,String> params);
}
