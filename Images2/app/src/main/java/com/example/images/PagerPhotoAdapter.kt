package com.example.images

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.photo_view.view.*

class PagerPhotoAdapter : ListAdapter<PhotoEntity,PagerPhotoViewHolder>(Diff) {

    object Diff:DiffUtil.ItemCallback<PhotoEntity>(){
        override fun areItemsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
            return oldItem.photoId == newItem.photoId
        }

        override fun areContentsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerPhotoViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.photo_view,parent,false).apply {
            return PagerPhotoViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: PagerPhotoViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(getItem(position).photoURL)
            .placeholder(R.drawable.ic_image_green_24dp)
            .into(holder.itemView.pv_image)
    }
}

class PagerPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)