package com.example.room.Room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class AppViewModel(application: Application) : AndroidViewModel(application) {
    private var userResponsitory: UserReponsitory = UserReponsitory(application)

    fun getAllUsers(): LiveData<List<User>>{
        return  userResponsitory.allUsers
    }

    fun insert(user: User){
        userResponsitory.insert(user)
    }

    fun deleteAll(){
        userResponsitory.deleteAllUsers()
    }

    fun update(user: User)
    {
        userResponsitory.updateUser(user)
    }

    fun findByName(name:String): LiveData<List<User>>{
       return userResponsitory.findByName(name)
    }
}