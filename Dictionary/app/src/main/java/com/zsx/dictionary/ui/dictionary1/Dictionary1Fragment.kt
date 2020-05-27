package com.zsx.dictionary.ui.dictionary1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zsx.dictionary.R
import com.zsx.dictionary.adapter.Dictionary1Adapter
import com.zsx.dictionary.databinding.FragmentDictionary1Binding
import com.zsx.dictionary.util.InjectorUtils
import com.zsx.dictionary.util.SoftInputHelper
import com.zsx.dictionary.viewmodel.Dictionary1ViewModel
import kotlinx.android.synthetic.main.fragment_dictionary1.*

class Dictionary1Fragment : Fragment() {

    private  val dictionary1ViewModel: Dictionary1ViewModel by viewModels {
        InjectorUtils.provideDictionary1ViewModelFactory()
    }

    private lateinit var dictionary1Adapter: Dictionary1Adapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentDictionary1Binding.inflate(inflater,container,false)
        dictionary1Adapter = Dictionary1Adapter()
        binding.tvDictionary1.adapter = dictionary1Adapter
        dictionary1ViewModel.dictionary1Entity.observe(viewLifecycleOwner, Observer {
            dictionary1Adapter.submitList(it)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_search.setOnClickListener{
            var keyword = et_search_keyword.text.toString()
            if(keyword.isNotEmpty()){
                dictionary1ViewModel.getDictionary1(keyword)
            }

            SoftInputHelper.hideSoftInput(requireContext(),it)
        }
    }
}
