package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.room.Room.AppDatabase
import com.example.room.Room.User
import com.example.room.Room.UserDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var userDatabase: AppDatabase
    lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userDatabase = Room.databaseBuilder(this,AppDatabase::class.java,"appDB").allowMainThreadQueries().build()
        userDao = userDatabase.userDao()
        updateView()

        btnInsert.setOnClickListener(){
            var user: User  = User("Your first name","Your last name",30)
            userDao.insert(user)
            updateView()
        }

        btnDelete.setOnClickListener(){
            userDao.deleteAll()
            updateView()
        }
    }

    private fun updateView(){
        var users: List<User> = userDao.getAll()
        var result = ""
        for(user in users){
            result += "username:${user.firstName} ${user.lastName} age: ${user.age}\n"
        }
        textView.text = result
    }
}
