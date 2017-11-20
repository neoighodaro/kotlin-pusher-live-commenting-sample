package com.example.android.pushersample

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by idorenyin on 11/18/17.
 */


public class RetrofitClient {
    fun getClient(): ApiService {
        val httpClient = OkHttpClient.Builder()
        val builder = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder
                .client(httpClient.build())
                .build()
        return retrofit.create(ApiService::class.java)
    }
}