package com.zsx.dictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zsx.dictionary.databinding.Dictionary1ItemBinding

import com.zsx.dictionary.entity.Dictionary1Entity


class Dictionary1Adapter :
    ListAdapter<Dictionary1Entity, Dictionary1Adapter.Dictionary1ViewHolder>(Dictionary1DiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dictionary1ViewHolder {
        return Dictionary1ViewHolder(
            Dictionary1ItemBinding.inflate(LayoutInflater.from(parent.context),parent,
                false))
    }

    override fun onBindViewHolder(holder: Dictionary1ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class Dictionary1DiffCallback : DiffUtil.ItemCallback<Dictionary1Entity>(){
        override fun areItemsTheSame(oldItem: Dictionary1Entity, newItem: Dictionary1Entity): Boolean {
            return oldItem.hanzi == newItem.hanzi
        }

        override fun areContentsTheSame(oldItem: Dictionary1Entity, newItem: Dictionary1Entity): Boolean {
            return oldItem == newItem
        }

    }

    class Dictionary1ViewHolder(
        private val binding : Dictionary1ItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Dictionary1Entity){
            binding.apply {
                dictionary1 = item
                executePendingBindings()
            }

        }
    }


}

