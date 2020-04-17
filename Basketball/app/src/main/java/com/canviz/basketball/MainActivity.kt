package com.canviz.basketball

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.canviz.basketball.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.model = ViewModelProvider(this)[MyViewModel::class.java]
        binding.lifecycleOwner = this

        //getSharedPreferences("Basketball", Context.MODE_PRIVATE).edit().putString("my shareed proeference","100").apply()
        //Log.d("test sp",getSharedPreferences("Basketball", Context.MODE_PRIVATE).getString("my shareed proeference",""))
    }
}
