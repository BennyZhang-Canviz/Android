package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.*
import com.androiddevs.mvvmnewsapp.model.Article

@Database(entities = [Article::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase :RoomDatabase(){
    abstract fun getArticleDao(): ArticleDao

    companion object{
        @Volatile
        private var instance: ArticleDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context): ArticleDatabase {
          return  Room.databaseBuilder(context,ArticleDatabase::class.java,"article.db")
                .build()
        }
    }

}