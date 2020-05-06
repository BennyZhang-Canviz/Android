package com.example.eventbus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        EventBus.getDefault().register(this)
        button.setOnClickListener(){
            var intent = Intent(this,PostEventActivity::class.java)
            startActivity(intent)
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(message: MessageEvent ) {
        textView.text = message.message.toString()

        Toast.makeText(this,message.message,Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
