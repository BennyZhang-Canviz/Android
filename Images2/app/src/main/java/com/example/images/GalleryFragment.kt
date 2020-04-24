package com.example.images

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_gallery.*

/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {

    private val viewModel by viewModels<GalleryViewModel>()

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

        viewModel.photos.observe(viewLifecycleOwner, Observer {
            photoGalleryAdapter.submitList(it)
            fg_swipeRefreshLayout.isRefreshing = false
        })
        //下面这句是让recylerview滚动到最顶部
        //fg_recyclerView.scrollToPosition(0)


        fg_swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadImages()
        }

//        fg_recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if(dy<0) return
//                if(dy>0){
//                   var manager = recyclerView.layoutManager as StaggeredGridLayoutManager
//                    var positionArray = IntArray(2)
//                    manager.findLastVisibleItemPositions(positionArray)
//                    if(positionArray[0]==photoGalleryAdapter.itemCount-1 ){
//                        //在这就要去加载另外一页的内容了
//                    }
//                }
//            }
//        })
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
