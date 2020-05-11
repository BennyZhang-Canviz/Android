package com.example.basicapp.viewmodel

import androidx.lifecycle.*
import com.example.basicapp.data.Banner
import com.example.basicapp.data.HomeRepository
import com.example.basicapp.data.Plant
import kotlinx.coroutines.launch

//参考 https://www.jianshu.com/p/6da800ddaa2e
class HomeViewModel (private val homeRepository: HomeRepository): ViewModel(){
    private val _response = MutableLiveData<String>()
    private val _banners = MutableLiveData<List<Banner>>()

    val response: LiveData<String> = _response
    val banners: LiveData<List<Banner>> = _banners

    private val _error = MutableLiveData<String>()
    val error:LiveData<String> = _error

    val gardenPlantings: LiveData<List<Plant>> = homeRepository.getGardenPlants()

    var plantName: LiveData<String?> = gardenPlantings.map{
        if(it.isNotEmpty())
            it.takeLast(1)[0].plantName
        else
            null
    }

    fun onHomeViewClicked(){
        refreshView()
    }

    fun getBanner() = launchDataLoad {
        val result = homeRepository.getBanner()
        _banners.postValue(result.data)
    }

    private fun refreshView(): Unit = launchDataLoad{
        val result: String = homeRepository.refreshView()
        _response.value = result
    }

    private fun launchDataLoad(function: suspend () -> Unit): Unit {
        viewModelScope.launch {
            try {
                function()
            }catch (err:HomeRepository.RefreshError){
                _error.value = err.message
            }
        }
    }
}