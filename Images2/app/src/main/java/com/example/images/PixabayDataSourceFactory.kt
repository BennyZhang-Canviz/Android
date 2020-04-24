package com.example.images

import android.content.Context
import androidx.paging.DataSource

class PixabayDataSourceFactory(private val context: Context): DataSource.Factory<Int,PhotoEntity>() {
    override fun create(): DataSource<Int, PhotoEntity> {
        return PixabayDataSource(context)
    }
}