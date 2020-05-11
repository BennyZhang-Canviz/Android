package com.example.demoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var users = ArrayList<User>()
        for(i in 0..100){
            var viewType = if(i%2==0) 0 else 1
            val user = User(address = "Beijing$i", username = "Tom$i" , viewType = viewType)
            users.add(user)
        }

        rvDemo.adapter = MainAdapter(users,this)
        //var layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
       var layoutManager = GridLayoutManager(this,2)

        rvDemo.layoutManager = layoutManager






























//        var users = ArrayList<User>()
//        for(i in 0..10){
//            val user = User(address = "Beijing$i", username = "Tom$i" )
//            users.add(user)
//        }
//
//
//        var layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        rvDemo.layoutManager = layoutManager
//        rvDemo.adapter = MainAdapter(users)

    }
}
