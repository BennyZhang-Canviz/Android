package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.model.News
import com.androiddevs.mvvmnewsapp.util.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.URL

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("apiKey")
        apiKey: String = Constant.API_KEY,
        @Query("page")
        page:Int = 1
    ): Response<News>

    @GET("v2/everything")
    suspend fun searchBreakingNews(
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String = Constant.API_KEY,
        @Query("page")
        page:Int = 1
    ): Response<News>
}