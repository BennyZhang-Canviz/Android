package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.util.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private val retrofit by lazy {
            val client = OkHttpClient.Builder().build()
            Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}