package com.example.sunflower.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.sunflower.data.Plant
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.sunflower.databinding.ListItmePlantBinding



//import com.example.sunflower

class PlantAdapter : ListAdapter<Plant,RecyclerView.ViewHolder>(PlantDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       var holder = PlantViewHolder(ListItmePlantBinding.inflate(LayoutInflater.from(parent.context),parent,false))

       return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var plant = getItem(position)
        holder.itemView.setOnClickListener(){
            Bundle().apply {
                putString("plantId",plant.plantId)
                holder.itemView.findNavController().navigate(com.example.sunflower.R.id.action_homeViewFragment_to_plantDetailFragment,this)
            }

        }
        (holder as PlantViewHolder).bind(plant)
    }
}

class PlantViewHolder(private val binding:  ListItmePlantBinding)
    :RecyclerView.ViewHolder(binding.root){
    init {

    }

    fun bind(item:Plant){
        binding.apply {
             plant = item
            executePendingBindings()
        }
    }
}

private class PlantDiffCallback: DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }

}

