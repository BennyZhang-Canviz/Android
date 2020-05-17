package com.androiddevs.mvvmnewsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.model.Article
import com.androiddevs.mvvmnewsapp.model.News
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel (val newsRepository: NewsRepository): ViewModel() {


    val news: MutableLiveData<Resource<News>> = MutableLiveData()
    var newsPage = 1

    var searchedNews : MutableLiveData<Resource<News>> = MutableLiveData()
    var searchNewsPage = 1

    fun getNews(countryCode: String) = viewModelScope.launch{
        news.postValue(Resource.Loading())

        var response = newsRepository.getNews(countryCode, newsPage)
        news.postValue(handleNewsResponse(response))

    }

    private fun handleNewsResponse(response: Response<News>): Resource<News>{
        if(response.isSuccessful){
            response.body()?.let{resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun searchNews(searchQueryString: String) = viewModelScope.launch {
        searchedNews.postValue(Resource.Loading())
        var response = newsRepository.searchNews(searchQueryString, searchNewsPage)
        searchedNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleSearchNewsResponse(response: Response<News>): Resource<News>{
        if(response.isSuccessful){
            response.body()?.let{resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveNews(article: Article) = viewModelScope.launch {
        newsRepository.addNewsToFavorite(article)
    }

    fun getSavedNews() =  newsRepository.getSavedNews()

    fun deleteNews(article: Article) = viewModelScope.launch {
        newsRepository.deleteNews(article)
    }

    init {
        getNews("us")
    }
}