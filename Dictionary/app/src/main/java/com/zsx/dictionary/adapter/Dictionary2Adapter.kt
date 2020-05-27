package com.zsx.dictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zsx.dictionary.databinding.Dictionary1ItemBinding
import com.zsx.dictionary.databinding.Dictionary2ItemBinding


import com.zsx.dictionary.entity.Dictionary2Entity


class Dictionary2Adapter :
    ListAdapter<Dictionary2Entity, Dictionary2Adapter.Dictionary1ViewHolder>(Dictionary1DiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dictionary1ViewHolder {
        return Dictionary1ViewHolder(
            Dictionary2ItemBinding.inflate(LayoutInflater.from(parent.context),parent,
                false))
    }

    override fun onBindViewHolder(holder: Dictionary1ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class Dictionary1DiffCallback : DiffUtil.ItemCallback<Dictionary2Entity>(){
        override fun areItemsTheSame(oldItem: Dictionary2Entity, newItem: Dictionary2Entity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Dictionary2Entity, newItem: Dictionary2Entity): Boolean {
            return oldItem == newItem
        }

    }

    class Dictionary1ViewHolder(
        private val binding : Dictionary2ItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Dictionary2Entity){
            binding.apply {
                 dictionary= item
                executePendingBindings()
            }

        }
    }


}

