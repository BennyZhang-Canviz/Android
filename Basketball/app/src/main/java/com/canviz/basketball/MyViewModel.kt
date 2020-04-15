package com.canviz.basketball

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel(){

    var TeamAScore : MutableLiveData<Int> = MutableLiveData(0)
    var TeamBScore : MutableLiveData<Int> = MutableLiveData(0)

    private var backA = 0
    private var backB = 0

    fun setBackValue(){
        backA = (TeamAScore.value?:0)
        backB = (TeamBScore.value?:0)
    }

    fun addA(numberToAdd: Int){
        setBackValue()
        TeamAScore.value = (TeamAScore.value?:0) + numberToAdd
    }
    fun addB(numberToAdd: Int){
        setBackValue()
        TeamBScore.value = (TeamBScore.value?:0) + numberToAdd
    }

    fun reset(){
        TeamAScore.value = 0
        TeamBScore.value = 0
        setBackValue()


    }

    fun undo(){
        TeamAScore.value = backA
        TeamBScore.value = backB
    }

}