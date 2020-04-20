package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.room.Room.AppDatabase
import com.example.room.Room.AppViewModel
import com.example.room.Room.User
import com.example.room.Room.UserDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val appViewModel : AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        appViewModel.getAllUsers().observe(this, Observer {
            var result: String = ""
            for(user in it){
                result += "ID: ${user.id} first name: ${user.firstName} last name: ${user.lastName} age: ${user.age} \n"
            }
            textView.text = result
        })
        btnInsert.setOnClickListener(){
            var user: User  = User("Your first name","Your last name",30)
            appViewModel.insert(user)
        }

        btnDelete.setOnClickListener(){
            appViewModel.deleteAll()

        }
    }


}
