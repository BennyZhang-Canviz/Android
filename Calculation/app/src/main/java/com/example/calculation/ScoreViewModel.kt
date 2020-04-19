package com.example.calculation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle

class ScoreViewModel (application: Application): AndroidViewModel(application) {
    private var _application = application
    private val sharedPreferenceKey = "spSavedKey"
    private val highestScoreKey = "highestScoreKey"

    var highestScore:MutableLiveData<Int> = MutableLiveData(0)
    var equation:MutableLiveData<String> = MutableLiveData("")
    var answer:MutableLiveData<Int> = MutableLiveData(0)
    var answerCorrectCount :MutableLiveData<Int> = MutableLiveData(0)

//    private val _answerCorrectCount :MutableLiveData<Int> = MutableLiveData(12)
//    fun getAnswerCorrect(): MutableLiveData<Int> = _answerCorrectCount



    init {
        if(highestScore.value ==0){
            highestScore.value = application.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).getInt(highestScoreKey,0)
        }
    }



    fun generate(){
        val LEVEL:Int = 20
        var x:Int = (0..LEVEL).random()
        var y = (0..LEVEL).random()
        var operator = "+"
        if(x%2==0){
            operator = "+"
            if(x>y){
                answer.value = x
                equation.value = y.toString() + operator + (x-y).toString() + "=?"
            }else{
                answer.value = y
                equation.value = x.toString() + operator + (y-x).toString() + "=?"
            }
        }else{
            operator = "-"
            if(x>y){
                answer.value = x-y
                equation.value = "$x$operator$y=?"
            }else{
                answer.value = y-x
                equation.value = "$y$operator$x=?"
            }
        }
    }

    fun save(){
        _application.getSharedPreferences(sharedPreferenceKey, Context.MODE_PRIVATE).edit().putInt(highestScoreKey,(answerCorrectCount.value?:0)).apply()
        highestScore.value = answerCorrectCount.value
    }

    fun answerCorrect(){
        //_answerCorrectCount.value = (_answerCorrectCount.value?:0) + 10
        answerCorrectCount.value = (answerCorrectCount.value?.plus(1))
        generate()
    }
}