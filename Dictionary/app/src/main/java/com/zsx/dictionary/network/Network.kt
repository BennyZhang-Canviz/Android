package com.zsx.dictionary.network

import com.zsx.dictionary.entity.Dictionary1ResultEntity
import com.zsx.dictionary.entity.Dictionary2ResultEntity
import com.zsx.dictionary.entity.Dictionary3ResultEntity
import com.zsx.dictionary.entity.SayingResultEntity
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val baseURL ="http://api.avatardata.cn"
//去https://www.avatardata.cn/免费申请吧
const val sayingAppKey = "" //名人名言
const val dictionary1Key = "" //新华字典
const val dictionary2Key = "" //成语词典
const val dictionary3Key = "" //辞海


val okHttpClient = OkHttpClient.Builder().build()

val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(baseURL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface MainNetwork{
    @GET("/MingRenMingYan/LookUp")
    suspend fun getSaying(
        @Query("keyword") keyword:String,
        @Query("key" ) key:String = sayingAppKey,
        @Query("page") pageNumber: Int = 1,
        @Query("rows") rows: Int = 50

    ): SayingResultEntity

    @GET("/XinHuaZiDian/LookUp")
    suspend fun getDictionary1(
        @Query("content") content:String,
        @Query("key" ) key:String = dictionary1Key
    ): Dictionary1ResultEntity

    @GET("/ChengYu/Search")
    suspend fun getDictionary2(
        @Query("keyWord") content:String,
        @Query("key" ) key:String = dictionary2Key
    ): Dictionary2ResultEntity

    @GET("/CiHai/LookUp")
    suspend fun getDictionary3(
        @Query("keyWord") content:String,
        @Query("key" ) key:String = dictionary3Key
    ): Dictionary3ResultEntity
}


object Api {
    val retrofitService: MainNetwork by lazy { retrofit.create(MainNetwork::class.java) }
}
