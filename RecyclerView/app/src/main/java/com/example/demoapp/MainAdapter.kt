package com.example.demoapp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.nio.file.SimpleFileVisitor


class MainAdapter(var users: ArrayList<User>,var context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_NORMAL = 1
    private val VIEW_TYPE_SIMPLE = 2

    inner class NormalHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvUsername : TextView = view.findViewById(R.id.tvUsername) as TextView
        var tvAddress : TextView = view.findViewById(R.id.tvAddress) as TextView
    }

    inner class SimpleHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvUsername : TextView = view.findViewById(R.id.tvUsername) as TextView
    }

    override fun getItemViewType(position: Int): Int {
        return if(users[position].viewType ==0){
            VIEW_TYPE_NORMAL
        }else{
            VIEW_TYPE_SIMPLE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var inflate = LayoutInflater.from(parent.context)
        return if(viewType==VIEW_TYPE_NORMAL){
            var view : View = inflate.inflate(R.layout.fragment_layout,parent,false)
            NormalHolder(view)
        }else{
            var view : View = inflate.inflate(R.layout.fragment_layout_title_only,parent,false)
            SimpleHolder(view)
        }


    }

    override fun getItemCount(): Int {
        return users.size
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var user = users[position]
        when(holder){
            is NormalHolder ->{
                holder.tvAddress.text = user.address
                holder.tvUsername.text = user.username
                holder.tvUsername.setOnClickListener(){
                    Toast.makeText(context,user.username,Toast.LENGTH_LONG).show()
                }
            }
            is SimpleHolder ->{
                holder.tvUsername.text = user.username
                holder.tvUsername.setOnClickListener(){
                    Toast.makeText(context,user.username,Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}


















