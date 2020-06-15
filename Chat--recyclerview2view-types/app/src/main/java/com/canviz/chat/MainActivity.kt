package com.canviz.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RecycGridAdapter
    private var data = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        robot.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        adapter = RecycGridAdapter(this, data)
        robot.adapter = adapter
    }

    private fun initData() {
        repeat(2) {
            data.add(Fruit("apple", R.drawable.chat, 1))
            data.add(Fruit("banana", R.drawable.chat,2))
            data.add(Fruit("orange", R.drawable.chat,1))
            data.add(Fruit("watermelon", R.drawable.chat,2))
            data.add(Fruit("pear", R.drawable.chat,1))
            data.add(Fruit("grape", R.drawable.chat,2))
            data.add(Fruit("pineapple", R.drawable.chat,1))
            data.add(Fruit("strawberry", R.drawable.chat,2))
            data.add(Fruit("cherry", R.drawable.chat,1))
            data.add(Fruit("mango", R.drawable.chat,2))
        }
    }

}