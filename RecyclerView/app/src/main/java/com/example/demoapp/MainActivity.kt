package com.example.demoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var users = ArrayList<User>()
        for(i in 0..10){
            var viewType = if(i%2==0) 0 else 1
            val user = User(address = "Beijing$i", username = "Tom$i" , viewType = viewType)
            users.add(user)
        }

        rvDemo.adapter = MainAdapter(users,this)
        //使用LinearLayoutManager
        var layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        //使用GridLayoutManager
       //var layoutManager = GridLayoutManager(this,2)

        rvDemo.layoutManager = layoutManager

        //添加分隔线
        var decoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        //自定义颜色
        ContextCompat.getDrawable(this, R.drawable.decoratoin)?.let { decoration.setDrawable(it) }
        rvDemo.addItemDecoration(decoration)

    }
}
