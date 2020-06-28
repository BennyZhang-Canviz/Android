package com.zsx.examples

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_weak_reference.*
import java.lang.ref.WeakReference

class HandlerWeakReferenceActivity : AppCompatActivity() {
    private lateinit var  handler: MyHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weak_reference)
        handler = MyHandler(this)
        handler.sendEmptyMessage(1)
    }
}

class MyHandler(var context: Context): Handler() {
    private var weakReference: WeakReference<Context> = WeakReference(context)

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        (weakReference.get() as HandlerWeakReferenceActivity)?.textView.text = "updateed by handler"
    }
}