package com.example.images

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

enum class NetworkStatus{
    LOADING,
    FAILED,
    COMPLETED
}

class PixabayDataSource(private val context: Context): PageKeyedDataSource<Int,PhotoEntity>() {
    var keyWords = arrayOf("dog", "animal", "beauty","girl","flowers","young girl").random()
    private var _networkStatus = MutableLiveData<NetworkStatus>()
    val networkStatus: LiveData<NetworkStatus> = _networkStatus

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PhotoEntity>
    ) {
        _networkStatus.postValue(NetworkStatus.LOADING)
        var url = "https://pixabay.com/api/?key=16166289-94217dfedd10727e3604e9119&q=${keyWords}&per_page=20&page=1"
        StringRequest(Request.Method.GET,url,
            Response.Listener {
                var resultList = Gson().fromJson(it,PhotosEntity::class.java).hits.toList()
                callback.onResult(resultList,null,2)
            },
            Response.ErrorListener{
                _networkStatus.postValue(NetworkStatus.FAILED)
            })
            .also {
                VolleySingleton.getInstance(context).requestQueue.add(it)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoEntity>) {
        _networkStatus.postValue(NetworkStatus.LOADING)
        var url = "https://pixabay.com/api/?key=16166289-94217dfedd10727e3604e9119&q=${keyWords}&per_page=20&page=${params.key}"
        StringRequest(Request.Method.GET,url,Response.Listener {
            var results = Gson().fromJson(it,PhotosEntity::class.java).hits.toList()
            callback.onResult(results,params.key.plus(1))
        },Response.ErrorListener {
            _networkStatus.postValue(NetworkStatus.FAILED)
        }).also {
            VolleySingleton.getInstance(context).requestQueue.add(it)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoEntity>) {

    }
}

//class PixabayDataFactory(private val context: Context): DataSource.Factory<Int,PhotoEntity>(){
//    override fun create(): DataSource<Int, PhotoEntity> {
//        return PixabayDataSource(context)
//    }
//
//}
//class PixabayDataSourceFactory(private val context: Context):DataSource.Factory<Int,PhotoEntity>() {
//    override fun create(): DataSource<Int, PhotoEntity> {
//        return PixabayDataSource(context)
//    }
//}
