package com.androiddevs.mvvmnewsapp.util

import android.content.Context
import androidx.room.Database
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.viewmodel.NewsRepository
import com.androiddevs.mvvmnewsapp.viewmodel.NewsViewModelProviderFactory

object InjectorUtils {

    fun provideNewsViewModelFactory(context: Context): NewsViewModelProviderFactory {
        val repository = getHomeRepository(context)
        return NewsViewModelProviderFactory(repository)
    }

    private fun getHomeRepository(context: Context): NewsRepository {
        return NewsRepository(ArticleDatabase.invoke(context))
    }
}