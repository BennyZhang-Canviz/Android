package com.canviz.viewmodelwithsharedpreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.canviz.viewmodelwithsharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewMode : MyViewModel by lazy {
        ViewModelProvider(this)[MyViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.model = viewMode
        binding.lifecycleOwner = this
    }

    override fun onPause() {
        super.onPause()
        viewMode.saveResult()
    }
}
