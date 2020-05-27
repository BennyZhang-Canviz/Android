package com.zsx.dictionary.ui.dictionary2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.zsx.dictionary.adapter.Dictionary2Adapter
import com.zsx.dictionary.databinding.FragmentDictionary2Binding
import com.zsx.dictionary.util.InjectorUtils
import com.zsx.dictionary.util.SoftInputHelper
import com.zsx.dictionary.viewmodel.Dictionary2ViewModel
import kotlinx.android.synthetic.main.fragment_dictionary2.*

class Dictionary2Fragment : Fragment() {

    private  val dictionary2ViewModel: Dictionary2ViewModel by viewModels {
        InjectorUtils.provideDictionary2ViewModelFactory()
    }

    private lateinit var dictionary2Adapter: Dictionary2Adapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentDictionary2Binding.inflate(inflater,container,false)
        dictionary2Adapter = Dictionary2Adapter()
        binding.tvDictionary1.adapter = dictionary2Adapter
        dictionary2ViewModel.dictionary2Entity.observe(viewLifecycleOwner, Observer {
            dictionary2Adapter.submitList(it)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_search.setOnClickListener{
            var keyword = et_search_keyword.text.toString()
            if(keyword.isNotEmpty()){
                dictionary2ViewModel.getDictionary2(keyword)
            }

            SoftInputHelper.hideSoftInput(requireContext(),it)
        }
    }
}
