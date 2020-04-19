package com.example.navigationwithviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private val navigationController: NavController by lazy{
        Navigation.findNavController(this,R.id.fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupActionBarWithNavController(this,navigationController)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp()
    }
}
