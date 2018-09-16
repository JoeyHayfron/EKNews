package com.engineerskasa.eknews.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit mRetrofit = null;

    public Retrofit getCLient(String baseUrl){
        if (mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return  mRetrofit;
    }
}
