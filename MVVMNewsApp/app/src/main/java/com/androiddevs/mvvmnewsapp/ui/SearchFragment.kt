package com.androiddevs.mvvmnewsapp.ui

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.adapter.NewsAdapter
import com.androiddevs.mvvmnewsapp.util.Resource
import com.androiddevs.mvvmnewsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    private lateinit var  newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        newsViewModel = (activity as MainActivity).newsViewModel


        newsAdapter = NewsAdapter()
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        newsAdapter.setOnItemClickListener {
            var bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(R.id.action_searchFragment_to_articleDetailFragment, bundle)
        }
        newsViewModel.searchedNews.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { newsItems ->
                        newsAdapter.differ.submitList(newsItems.articles)
                    }

                }
            }
        })

        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {

                    if (editable.toString().isNotEmpty()) {
                        newsViewModel.searchNews(editable.toString())
                    }
                }
            }
        }
    }

    private fun hideProgressBar(){
        paginationProgressBar.visibility = View.GONE
    }
    private fun showProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
    }

}
