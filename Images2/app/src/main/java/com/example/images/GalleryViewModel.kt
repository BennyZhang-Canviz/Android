package com.example.images

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private var _photos:MutableLiveData<List<PhotoItem>> = MutableLiveData<List<PhotoItem>>()

    val photos: LiveData<List<PhotoItem>>
        get() = _photos

    fun loadImages(){
        var keyWords = arrayOf("dog", "animal", "beauty","girl","flowers","young girl")
        var url = "https://pixabay.com/api/?key=16166289-94217dfedd10727e3604e9119&q=${keyWords.random()}&per_page=100"

        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener {
                _photos.value = Gson().fromJson(it,Pixabay::class.java).hits.toList()
            },
            Response.ErrorListener {

            })
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)


    }

}