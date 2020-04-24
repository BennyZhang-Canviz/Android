package com.example.images

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.photo_cell.view.*

class PhotoGalleryAdapter : PagedListAdapter<PhotoEntity, PhotoViewHolder>(DIFFCALLBACK) {

    companion object{
        const val NORMAL_VIEW_TYPE=0
        const val FOOTER_VIEW_TYPE=1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var holder: PhotoViewHolder
        if(viewType == NORMAL_VIEW_TYPE){
             holder =PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_cell,parent,false))
            holder.itemView.setOnClickListener(){
                Bundle().apply {
                    putParcelableArrayList("photos",ArrayList(currentList!!))
                    putInt("photo_position",holder.adapterPosition)
                    holder.itemView.findNavController().navigate(R.id.action_galleryFragment_to_pagerPhotoFragment,this)
                }

            }
        }else{
            holder = PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_bottom,parent,false).also {
                (it.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
            })
        }

        return holder
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("itemCount",itemCount.toString() +"=======")
        return if(position == itemCount - 1 ) FOOTER_VIEW_TYPE else NORMAL_VIEW_TYPE
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        if(position == itemCount -1){
            return
        }
        holder.itemView.fg_shimmerlayout.apply {
            setShimmerColor(0x55FFFFFF)
            setShimmerAngle(0)
            startShimmerAnimation()
        }
        var photItem =  getItem(position)
        if (photItem != null) {
            holder.itemView.fg_cell_imageView.layoutParams.height = photItem.photoHeight
            holder.itemView.pc_textUsername.text = "${photItem.user}/${photItem.favorites}"
        }

        Glide.with(holder.itemView)
            .load(getItem(position)?.previewURL)
            .placeholder(R.drawable.ic_image_green_24dp)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.itemView.fg_shimmerlayout?.stopShimmerAnimation()
                    return false
                }

            })
            .into(holder.itemView.fg_cell_imageView)


    }

    object DIFFCALLBACK: DiffUtil.ItemCallback<PhotoEntity>(){
        override fun areItemsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
            return oldItem.photoId == newItem.photoId
        }

    }

}

 class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)