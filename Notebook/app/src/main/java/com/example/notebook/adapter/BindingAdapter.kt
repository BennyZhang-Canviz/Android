package com.example.notebook.adapter


import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter(value = ["timeSpan","hintMessage"],requireAll = true)
fun bindFormatDateTime(view: TextView,  timeSpan: Long, hintMessage:String ){
    if(timeSpan==0L){
        view.text = ""
    }else{
        var dateSpam =   Calendar.getInstance().apply { timeInMillis = timeSpan }
        formatDatetime(view, dateSpam,header = hintMessage)
    }

}

@BindingAdapter("showLessContent")
fun bindShowLessContent(view: TextView, str: String ){
    if(str.length > 100){
        view.text = str.substring(0,100) + "..."
    }else{
        view.text = str
    }

}



fun formatDatetime(view: TextView, datestamp: Calendar, header:String){
    val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:MM:SS", Locale.CHINA)
    var str =header + dateFormat.format(datestamp.time).toString()
    view.text = str
}