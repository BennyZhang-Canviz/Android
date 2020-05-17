package com.androiddevs.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.mvvmnewsapp.model.Article
import retrofit2.http.DELETE

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article:Article):Long

    @Query("SELECT * FROM ARTICLES")
    fun getAll():LiveData<List<Article>>

//    @Query("SELECT * FROM ARTICLES WHERE id = :id")
//    fun getById(id:Int):LiveData<Article>

    @Delete
    suspend fun delete(article:Article)

    @Update
    suspend fun update(article: Article)

}