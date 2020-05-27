package com.zsx.dictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zsx.dictionary.databinding.SayingItemBinding
import com.zsx.dictionary.entity.SayingEntity

class SayingAdapter : ListAdapter<SayingEntity, SayingAdapter.SayingViewHolder>(SayingDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SayingViewHolder {
        return SayingViewHolder(SayingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: SayingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SayingDiffCallback : DiffUtil.ItemCallback<SayingEntity>(){
        override fun areItemsTheSame(oldItem: SayingEntity, newItem: SayingEntity): Boolean {
            return oldItem.famous_saying == newItem.famous_saying
        }

        override fun areContentsTheSame(oldItem: SayingEntity, newItem: SayingEntity): Boolean {
            return oldItem == newItem
        }

    }

    class SayingViewHolder(
        private val binding : SayingItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SayingEntity){
            binding.apply {
                saying = item
                executePendingBindings()
            }
        }
    }


}

