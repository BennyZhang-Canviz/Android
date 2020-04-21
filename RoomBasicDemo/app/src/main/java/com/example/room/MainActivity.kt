package com.example.room

import android.app.TaskStackBuilder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.Room.AppViewModel
import com.example.room.Room.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(findViewById(R.id.fragment))
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }

}

//    private var userCard:Boolean = false
//    private val appViewModel : AppViewModel by lazy {
//        ViewModelProvider(this)[AppViewModel::class.java]
//    }



//        rvMain.layoutManager = LinearLayoutManager(this)
//        var adapter: UserAdapter = UserAdapter(appViewModel)
//        adapter.userCardView(userCard)
//        rvMain.adapter = adapter
//
//        appViewModel.getAllUsers().observe(this,Observer<List<User>>{
//            var temp = adapter.itemCount
//            adapter.setUsers(it)
//            if(it.count() !=temp){
//                adapter.notifyDataSetChanged()
//            }
//
//        })
//
//        btnInsert.setOnClickListener(){
//            for(index in 1..10){
//                var user: User  = User("Hankers $index","Tom $index",index,true)
//                appViewModel.insert(user)
//            }
//        }
//
//        btnCard.setOnClickListener(){
//            userCard = !userCard
//            adapter.userCardView(userCard)
//            rvMain.adapter = adapter
//        }
//
//        btnDelete.setOnClickListener(){
//            appViewModel.deleteAll()
//
//        }