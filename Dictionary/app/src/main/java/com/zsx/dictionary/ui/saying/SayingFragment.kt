package com.zsx.dictionary.ui.saying

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.zsx.dictionary.adapter.SayingAdapter
import com.zsx.dictionary.databinding.FragmentSayingBinding
import com.zsx.dictionary.util.InjectorUtils
import com.zsx.dictionary.util.SoftInputHelper
import com.zsx.dictionary.viewmodel.SayingViewModel
import kotlinx.android.synthetic.main.fragment_saying.*

class SayingFragment : Fragment() {

    private val sayingViewModel: SayingViewModel by viewModels{
        InjectorUtils.provideSayingViewModelFactory()
    }

    private lateinit var sayingAdapter: SayingAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var binding=  FragmentSayingBinding.inflate(inflater,container,false)
        sayingAdapter = SayingAdapter()
        binding.rvSayings.adapter = sayingAdapter

        addObserve()
      return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindClickEvent()

    }

    private fun addObserve() {
        sayingViewModel.sayings.observe(viewLifecycleOwner, Observer {
            sayingAdapter.submitList(it)
        })
    }

    private fun bindClickEvent() {
        button_search.setOnClickListener{
            var keyword = et_search_keyword.text.toString()
            if(keyword.isNotEmpty()){
                sayingViewModel.getSaying(keyword)
            }
//            val inputMethodManager =
//                context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            inputMethodManager!!.hideSoftInputFromWindow(it?.windowToken, 0)
            SoftInputHelper.hideSoftInput(requireContext(),it)


        }


    }
}
