package com.musila.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static  final String BASE_URL="https://en.wikipedia.org/";
    private  static Retrofit retrofit=null;

    public static Retrofit getApiClient(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();

        if(BuildConfig.DEBUG){
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        }else{
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
