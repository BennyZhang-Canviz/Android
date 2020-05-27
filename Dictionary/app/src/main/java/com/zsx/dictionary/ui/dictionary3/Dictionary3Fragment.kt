package com.zsx.dictionary.ui.dictionary3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.zsx.dictionary.R
import com.zsx.dictionary.databinding.FragmentDictionary3Binding
import com.zsx.dictionary.util.InjectorUtils
import com.zsx.dictionary.util.SoftInputHelper
import com.zsx.dictionary.viewmodel.Dictionary3ViewModel
import kotlinx.android.synthetic.main.fragment_dictionary3.*

/**
 * A simple [Fragment] subclass.
 */
class Dictionary3Fragment : Fragment() {


    private  val dictionary3ViewModel: Dictionary3ViewModel by viewModels {
        InjectorUtils.provideDictionary3ViewModelFactory()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentDictionary3Binding.inflate(inflater,container,false)


        dictionary3ViewModel.dictionary3Entity.observe(viewLifecycleOwner, Observer {
            binding.viewmodel = it
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_search.setOnClickListener(){
            var keyword = et_search_keyword.text.toString()
            if(keyword.isNotEmpty()){
                dictionary3ViewModel.getDictionary3(keyword)
            }

            SoftInputHelper.hideSoftInput(requireContext(),it)
        }
    }

}
