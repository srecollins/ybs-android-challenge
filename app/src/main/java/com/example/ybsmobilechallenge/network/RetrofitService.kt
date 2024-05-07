package com.example.ybsmobilechallenge.network

import com.example.ybsmobilechallenge.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitService {
    private const val BASE_URL = Constants.FLICKR_BASE_ENDPOINT

    // logging
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Create OkHttpClient and add logging interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Create Retrofit instance
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create an instance of ApiService using Retrofit
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
