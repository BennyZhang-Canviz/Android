package com.example.images

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_gallery.*

/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {

    private lateinit var viewModel:GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        val photoGalleryAdapter = PhotoGalleryAdapter()
        fg_recyclerView.apply {
            adapter = photoGalleryAdapter
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }

        viewModel = ViewModelProvider(this)[GalleryViewModel::class.java]
        viewModel.photos.observe(viewLifecycleOwner, Observer {
            photoGalleryAdapter.submitList(it)
            fg_swipeRefreshLayout.isRefreshing = false
        })
        viewModel.photos.value?:viewModel.loadImages()

        fg_swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadImages()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        fg_swipeRefreshLayout.isRefreshing = true
        Handler().postDelayed(Runnable {viewModel.loadImages()},1000)
        return super.onOptionsItemSelected(item)
    }
}
