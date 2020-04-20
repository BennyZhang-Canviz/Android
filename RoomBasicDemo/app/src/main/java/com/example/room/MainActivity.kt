package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.Room.AppViewModel
import com.example.room.Room.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var userCard:Boolean = false
    private val appViewModel : AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain.layoutManager = LinearLayoutManager(this)
        var adapter: UserAdapter = UserAdapter()
        adapter.userCardView(userCard)
        rvMain.adapter = adapter

        appViewModel.getAllUsers().observe(this,Observer<List<User>>{
            adapter.setUsers(it)
            adapter.notifyDataSetChanged()
        })

        btnInsert.setOnClickListener(){
            for(index in 1..10){
                var user: User  = User("Hankers $index","Tom $index",index)
                appViewModel.insert(user)
            }
        }

        btnCard.setOnClickListener(){
            userCard = !userCard
            adapter.userCardView(userCard)
            rvMain.adapter = adapter
        }

        btnDelete.setOnClickListener(){
            appViewModel.deleteAll()

        }
    }


}
