package com.example.basicapp.network

import com.example.basicapp.data.Banner
import com.example.basicapp.data.MyResponse
import com.google.gson.Gson
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MainNetwork {
    @GET("next_title.json")
    suspend fun fetchNextTitle():String

    @GET("/banner/json")
    suspend fun getBanner(): MyResponse<List<Banner>>
}


val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    //.addInterceptor(NetworkInterceptor())
    .build()
 val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://www.wanandroid.com")
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object Api{
    val retrofitService: MainNetwork by lazy { retrofit.create(MainNetwork::class.java) }
}


private val FAKE_RESULTS = listOf(
    "Apple",
    "Beet",
    "Grape",
    "Orange",
    "Pear"
)

class NetworkInterceptor(): Interceptor{

    private var lastResult: String = ""
    val gson = Gson()
    private var attempts = 0

    private fun wantRandomError() = attempts++ %5==0

    override fun intercept(chain: Interceptor.Chain): Response {
        var wantRandomError = wantRandomError()
        return  makeOkResult(chain,chain.request())
//        return if(wantRandomError){
//            makeErrorResult(chain.request())
//        }else{
//            makeOkResult(chain,chain.request())
//        }
    }

    private fun makeOkResult(chain: Interceptor.Chain, request: Request): Response {
        val url = chain.request().url().toString()
        if(url.contains("banner")){
            return chain.proceed(request)
        }
        var nextResult = lastResult
        while (nextResult == lastResult){
            nextResult = FAKE_RESULTS.random()
        }
        lastResult = nextResult
        return Response.Builder()
            .code(200)
            .request(request)
            .message("OK")
            .body(
                ResponseBody.create(
                    MediaType.get("application/json"),
                    gson.toJson(nextResult)
                )
            )
            .build()
    }

    private fun makeErrorResult(request: Request): Response {
       return Response.Builder()
           .code(500)
           .request(request)
           .protocol(Protocol.HTTP_1_0)
           .message("Bad server day")
           .body(
               ResponseBody.create(
                   MediaType.get("application/json"),
                   gson.toJson(mapOf("cause" to "not sure"))
               )
           )
           .build()
    }


}

