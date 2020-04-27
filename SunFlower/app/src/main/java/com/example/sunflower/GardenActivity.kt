package com.example.sunflower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sunflower.databinding.ActivityGradenBinding
import androidx.databinding.DataBindingUtil.setContentView
class GardenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // androidx.databinding.DataBindingUtil.setContentView<ActivityGradenBinding>(this,R.layout.activity_graden)
        setContentView(R.layout.activity_graden)
    }
}
