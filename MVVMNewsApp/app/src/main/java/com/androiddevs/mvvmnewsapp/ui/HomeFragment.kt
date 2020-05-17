package com.androiddevs.mvvmnewsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.adapter.NewsAdapter
import com.androiddevs.mvvmnewsapp.util.Resource
import com.androiddevs.mvvmnewsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Response
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {


    private lateinit var  newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        newsViewModel = (activity as MainActivity).newsViewModel
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        newsAdapter.setOnItemClickListener {
            var bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_articleDetailFragment,
                bundle
            )
        }
        newsViewModel.news.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { newsItems ->
                        newsAdapter.differ.submitList(newsItems.articles)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
