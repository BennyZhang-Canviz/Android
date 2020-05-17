package com.androiddevs.mvvmnewsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.adapter.NewsAdapter
import com.androiddevs.mvvmnewsapp.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    private lateinit var  newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = (activity as MainActivity).newsViewModel
        newsAdapter = NewsAdapter()

        rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        newsViewModel.getSavedNews().observe(viewLifecycleOwner, Observer {
            newsAdapter.differ.submitList(it)
        })

        newsAdapter.setOnItemClickListener {
            var bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_favoriteFragment_to_articleDetailFragment,
                bundle
            )
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var article = newsAdapter.differ.currentList[viewHolder.adapterPosition]
                newsViewModel.deleteNews(article)
                Snackbar.make(view, "Deleted.", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        newsViewModel.saveNews(article)
                    }
                    show()
                }
            }

        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedNews)
        }
    }

}
