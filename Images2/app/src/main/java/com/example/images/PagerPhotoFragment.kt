package com.example.images

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_pager_photo.*
import kotlinx.android.synthetic.main.fragment_pager_photo.view.*
import kotlinx.android.synthetic.main.photo_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 */
class PagerPhotoFragment : Fragment() {

    private val REQUEST_CODE:Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fpp_photoTag.text = "Loading..."
        var photos: ArrayList<PhotoItem>? = arguments?.getParcelableArrayList("photos")
        PagerPhotoAdapter().apply {
            fpp_photoViewPager.adapter = this
            submitList(photos)
        }

        fpp_photoViewPager.registerOnPageChangeCallback (object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                fpp_photoTag.text = "${position+1}/${photos?.size}"
            }
            }
        )

        arguments?.getInt("photo_position")?.let { fpp_photoViewPager.setCurrentItem(it,false) }

        imageViewSave.setOnClickListener(){
            if(Build.VERSION.SDK_INT<29 && ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),REQUEST_CODE)
            }else{
                viewLifecycleOwner.lifecycleScope.launch {
                    savePhoto()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE ->{
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    viewLifecycleOwner.lifecycleScope.launch {
                        savePhoto()
                    }

                }else{
                    Toast.makeText(context,"请授权以使用保存图片功能",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private suspend fun savePhoto(){
        withContext(Dispatchers.IO){
            val holder = (fpp_photoViewPager[0] as RecyclerView).findViewHolderForAdapterPosition(fpp_photoViewPager.currentItem) as PagerPhotoViewHolder
            val bitMap = holder.itemView.pv_image.drawable.toBitmap()
            val saveUri = requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                ContentValues())?:kotlin.run{
                return@withContext
            }
            requireContext().contentResolver.openOutputStream(saveUri).use {
                if(bitMap.compress(Bitmap.CompressFormat.JPEG,90,it)){
                    MainScope().launch {
                        Toast.makeText(context,"保存图片成功",Toast.LENGTH_LONG).show()
                    }
                }else{
                    MainScope().launch {
                        Toast.makeText(context,"保存图片失败",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
