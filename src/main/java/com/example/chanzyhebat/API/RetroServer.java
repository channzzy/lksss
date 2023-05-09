package com.example.chanzyhebat.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    public static String baseUrl = "http://103.67.187.184/api/";
    public static Retrofit retro;

    public static Retrofit getCon(){
        if(retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retro;
    }
}
