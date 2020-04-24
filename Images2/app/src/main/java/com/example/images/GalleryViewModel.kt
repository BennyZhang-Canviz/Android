package com.example.images

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.toLiveData


class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    val photos = PixabayDataSourceFactory(application).toLiveData(1)

    fun loadImages(){
        photos.value?.dataSource?.invalidate()
    }
}