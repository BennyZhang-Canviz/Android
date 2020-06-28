package com.zsx.examples.adapterexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.zsx.examples.R
import kotlinx.android.synthetic.main.activity_adapter.*

class AdapterActivity : AppCompatActivity() {

    private var students = mutableListOf(
        Student("Tom",30, "Beijing China"),
        Student("Lisa",88, "Shanghai China"),
        Student("Jenny",38, "Tianjin China")
    )

    private lateinit var  myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adapter)
        init()
    }

    private fun init() {
        myAdapter = MyAdapter(R.layout.adapter_list_item, students)
        myAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                var stu = students[position]
                Toast.makeText(applicationContext, stu.name, Toast.LENGTH_SHORT).show()
            }

        })
        //添加header
        var view =  layoutInflater.inflate(R.layout.adapter_list_header,null,false)
        myAdapter.addHeaderView(view.rootView)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter

        }
    }
}