package com.example.room

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.room.Room.User


class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private   var users:List<User> = emptyList()

    private var displayCard = false

    fun userCardView(userCardView:Boolean){
        displayCard = userCardView
    }
    fun setUsers(userArray:List<User>){
        users = userArray
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var user = users[position]
        holder.tvId.text = user.id.toString()
        holder.tvUsername.text = user.lastName + " " + user.firstName
        holder.tvAge.text = "Age: ${user.age}"

        holder.itemView.setOnClickListener(){
            var url: Uri = Uri.parse("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=abcdef")
            var intent:Intent = Intent(Intent.ACTION_VIEW)
            intent.data = url
            it.context.startActivity(intent)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view: View = if(displayCard){
            LayoutInflater.from(parent.context).inflate(R.layout.user_card,parent,false)
        }else{
            LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        }
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvId: TextView = itemView.findViewById(R.id.tvId)
        var tvUsername: TextView = itemView.findViewById(R.id.tvUsername)
        var tvAge: TextView = itemView.findViewById(R.id.tvAge)
    }

}