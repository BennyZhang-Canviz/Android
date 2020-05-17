package com.androiddevs.mvvmnewsapp.viewmodel

import androidx.room.Database
import com.androiddevs.mvvmnewsapp.api.RetrofitInstance
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.model.Article

class NewsRepository (val db: ArticleDatabase) {

    suspend fun getNews(countryCode: String, pageNum: Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,page = pageNum)

    suspend fun searchNews(searchQueryString: String, pageNum:Int) =
        RetrofitInstance.api.searchBreakingNews(searchQueryString,page = pageNum)

    suspend fun addNewsToFavorite(article: Article){
        db.getArticleDao().insert(article)
    }

    fun getSavedNews()=
        db.getArticleDao().getAll()
    suspend fun deleteNews(article: Article) =
        db.getArticleDao().delete(article)
}