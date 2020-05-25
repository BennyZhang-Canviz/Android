package com.example.notebook.util

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import com.bumptech.glide.load.engine.Resource
import com.example.notebook.R

object ToastHelper {
     fun showToast(str: String,context:Context){
        var toast = Toast.makeText(context,str, Toast.LENGTH_LONG).also {
            val toastLayout = it.view as LinearLayout
            val toastTV = toastLayout.getChildAt(0) as TextView
            toastTV.textSize = 30f
            toastTV.setTextColor(context.getColor(R.color.toast_color) )
        }
        toast.setGravity(Gravity.CENTER,0,0)

        toast.show()

    }
}