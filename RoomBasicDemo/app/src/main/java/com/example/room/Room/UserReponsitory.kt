package com.example.room.Room

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class UserReponsitory(context: Context){

    var userDao: UserDao = AppDatabase.getInstance(context).userDao()
    var allUsers: LiveData<List<User>> = userDao.getAll()


    fun insert(user: User){
        InsertAsyncTask(userDao).execute(user)
    }
    fun deleteAllUsers(){
        DeleteAllAsyncTask(userDao).execute()
    }

    fun deleteUser(user:User){
        DeleteUserAsyncTask(userDao).execute(user)
    }

    fun updateUser(user:User){
        UpdateAsyncTask(userDao).execute(user)
    }

    fun findByName(name:String): LiveData<List<User>>  {
       return userDao.findByName("%$name%")
    }


     class InsertAsyncTask(userDao: UserDao): AsyncTask<User, Void, Void>() {
        private val _userDao= userDao
        override fun doInBackground(vararg user: User): Void? {
            if (user != null) {
                _userDao.insert(user)
            }
            return null
        }
    }

    class DeleteUserAsyncTask(var userDao:UserDao): AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg params: User?): Void? {
            userDao.delete(params[0] as User)
            return null
        }
    }

    class DeleteAllAsyncTask(var userDao: UserDao): AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            userDao.deleteAll()
            return null
        }
    }

    class UpdateAsyncTask(private var userDao: UserDao): AsyncTask<User,Void,Void>(){
        override fun doInBackground(vararg user: User): Void? {
            if (user != null) {
                userDao.update(user)
            }
            return null
        }

    }

}