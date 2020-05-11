package com.example.shopping.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.R
import com.example.shopping.data.ShoppingItem
import com.example.shopping.viewmodel.ShoppingViewModel

class ShoppingAdapter( var items: List<ShoppingItem>, private var shoppingViewModel: ShoppingViewModel) :
    RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var tvName = view.findViewById(R.id.tvName) as TextView
        var tvAmount = view.findViewById(R.id.tvAmount) as TextView
        var ivDelete = view.findViewById(R.id.ivDelete) as ImageView
        var ivPlus = view.findViewById<ImageView>(R.id.ivPlus)
        var ivMinus = view.findViewById<ImageView>(R.id.ivMinus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        var view:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.shopping_item,parent,false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        var shoppingItem = items[position]
        holder.tvName.text = shoppingItem.name
        holder.tvAmount.text = shoppingItem.amount.toString()
        holder.ivDelete.setOnClickListener(){
            shoppingViewModel.delete(shoppingItem)
        }
        holder.ivMinus.setOnClickListener(){

            if(shoppingItem.amount>0){
                shoppingItem.amount--
                shoppingViewModel.update(shoppingItem)
            }
        }
        holder.ivPlus.setOnClickListener(){
            shoppingItem.amount++
            shoppingViewModel.update(shoppingItem)
        }
    }
}


