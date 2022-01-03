package com.example.myapplication.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClient {
    const val BASE_URL = "https://green-thumb-64168.uc.r.appspot.com"
    fun creteRetroFitInstance(): ApiEndPoints {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiEndPoints::class.java)
    }
}