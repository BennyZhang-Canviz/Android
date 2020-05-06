package com.example.eventbus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_post_event.*
import org.greenrobot.eventbus.EventBus

class PostEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_event)

        button2.setOnClickListener(){
            var messageEvent = MessageEvent()
            messageEvent.message = "Post somtthing to main"
            EventBus.getDefault().post(messageEvent)
            finish()
        }
    }
}
