package com.zsx.examples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zsx.examples.adapterexample.AdapterActivity
import com.zsx.examples.loading.LoadingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        btnAdapter.setOnClickListener{
            startActivity(Intent(this,AdapterActivity::class.java))
            finish()
        }
        btnHandler.setOnClickListener{
            startActivity(Intent(this,HandlerWeakReferenceActivity::class.java))
            finish()
        }

        btnWorkManager.setOnClickListener{
            startActivity(Intent(this,WorkManagerActivity::class.java))
            finish()
        }
        btnLoading.setOnClickListener{
            startActivity(Intent(this,LoadingActivity::class.java))
            finish()
        }

        btnLottie.setOnClickListener{
            startActivity(Intent(this,LottieActivity::class.java))
            finish()
        }
    }
}