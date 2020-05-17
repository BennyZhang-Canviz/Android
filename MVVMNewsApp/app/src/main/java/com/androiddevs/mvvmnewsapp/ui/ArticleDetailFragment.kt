package com.androiddevs.mvvmnewsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*

/**
 * A simple [Fragment] subclass.
 */
class ArticleDetailFragment : Fragment() {

    private lateinit var  newsViewModel: NewsViewModel
    val args:ArticleDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = (activity as MainActivity).newsViewModel
        var article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        fab.setOnClickListener{
            newsViewModel.saveNews(article)
            Toast.makeText(context,"Saved!",Toast.LENGTH_LONG).show()
        }
    }

}
