package com.example.room

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.Room.AppViewModel
import com.example.room.Room.User


class UserAdapter(var viewModel: AppViewModel) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

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
        //下面这句貌似很重要,要不然会重复绑定switch 事件
        holder.switch.setOnCheckedChangeListener(null)

        if(!user.showAge){
            holder.tvAge.visibility = View.GONE
            holder.switch.isChecked = false
        }else
        {
            holder.tvAge.visibility = View.VISIBLE
            holder.switch.isChecked = true
        }
        holder.switch.setOnCheckedChangeListener { _, isChecked ->
            //var user: User = holder.itemView.getTag(R.id.user_for_view_holder) as User
            //if(user!=null) {
                if (isChecked) {
                    holder.tvAge.visibility = View.VISIBLE
                    user.showAge = true
                    viewModel.update(user)
                } else {
                    holder.tvAge.visibility = View.GONE
                    user.showAge = false
                    viewModel.update(user)
                }
            //}
        }
        holder.itemView.setTag(R.id.user_for_view_holder,user)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view: View = if(displayCard){
            LayoutInflater.from(parent.context).inflate(R.layout.user_card,parent,false)
        }else{
            LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        }
        var holder: UserViewHolder = UserViewHolder(view)
        holder.itemView.setOnClickListener(){
            var url: Uri = Uri.parse("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=abcdef")
            var intent:Intent = Intent(Intent.ACTION_VIEW)
            intent.data = url
            it.context.startActivity(intent)

        }


        return holder
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvId: TextView = itemView.findViewById(R.id.tvId)
        var tvUsername: TextView = itemView.findViewById(R.id.tvUsername)
        var tvAge: TextView = itemView.findViewById(R.id.tvAge)
        var switch: Switch  = itemView.findViewById(R.id.switch1)
    }

}