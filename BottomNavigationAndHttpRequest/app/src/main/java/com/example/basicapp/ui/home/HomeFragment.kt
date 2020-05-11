package com.example.basicapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.basicapp.R
import com.example.basicapp.databinding.FragmentHomeBinding
import com.example.basicapp.util.InjectorUtils
import com.example.basicapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels{
        InjectorUtils.provideHomeViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        val binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.tvNavigation.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_home_to_detail_activity)
        }

        binding.tvHttp.setOnClickListener {
            homeViewModel.getBanner()
        }

        binding.rootLayout.setOnClickListener {
            homeViewModel.onHomeViewClicked()
        }
        homeViewModel.banners.observe(viewLifecycleOwner, Observer {
            Toast.makeText(this.context, it[0].title, Toast.LENGTH_LONG).show()

            //Timber.e("result-> "+banner.title)
        })
        homeViewModel.response.observe(viewLifecycleOwner, Observer {
            Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
        })
        return binding.root
    }

}
