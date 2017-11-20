package com.example.android.pushersample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by idorenyin on 11/18/17.
 */

interface ApiService {
    @GET("/{message}")
    fun sendMessage(@Path("message") title: String): Call<String>
}