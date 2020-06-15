package com.canviz.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecycGridAdapter(val context: Context, var data: List<Fruit>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        val TYPE_One=1
        val TYPE_Two=2
    }
    inner class TypeOneViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tv_item_lv: TextView = v.findViewById(R.id.tv_item_lv)
        val iv_item_lv: ImageView = v.findViewById(R.id.iv_item_lv)
    }
    inner class TypeTwoViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tv_item_lv: TextView = v.findViewById(R.id.tv_item_lv)
        val iv_item_lv: ImageView = v.findViewById(R.id.iv_item_lv)
    }
    override fun getItemViewType(position: Int): Int {
        var fruit  = data.get(position)
        if(fruit.FruitType ==1){
            return TYPE_One
        }
        return TYPE_Two
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val  view : View
        var vh : RecyclerView.ViewHolder
        if(viewType == TYPE_One){
            view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
            vh = TypeOneViewHolder(view)
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.item2, parent, false)
            vh =TypeTwoViewHolder(view)
        }
        vh.itemView.setOnClickListener {
            //拿到用户点击的位置
            var position = vh.absoluteAdapterPosition
            var fruit = data[position]
            Toast.makeText(context, "你点击的了-》" + fruit.name, Toast.LENGTH_SHORT).show()
        }
        vh.itemView.setOnLongClickListener {
            //拿到用户点击的位置
            var position = vh.absoluteAdapterPosition
            var fruit = data[position]
            Toast.makeText(context, "你长按了-》" + fruit.name, Toast.LENGTH_SHORT).show()
            true
        }
        return vh
    }
    override fun getItemCount(): Int {
        return data.size
    }
    //在该方法中数据绑定
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var fruit = data[position]
        var viewType = getItemViewType(position)
        if(viewType == TYPE_One){
            (holder as TypeOneViewHolder).iv_item_lv.setImageResource(fruit.ImgId)
            (holder as TypeOneViewHolder).tv_item_lv.text = fruit.name
        }
        else{
            (holder as TypeTwoViewHolder).iv_item_lv.setImageResource(fruit.ImgId)
            (holder as TypeTwoViewHolder).tv_item_lv.text = fruit.name
        }

    }
}
