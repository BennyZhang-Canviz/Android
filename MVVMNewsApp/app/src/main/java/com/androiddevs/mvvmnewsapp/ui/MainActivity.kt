package com.androiddevs.mvvmnewsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.util.InjectorUtils
import com.androiddevs.mvvmnewsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

     val  newsViewModel:NewsViewModel by viewModels{
        InjectorUtils.provideNewsViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setupWithNavController(news_fragment.findNavController())
    }
}
