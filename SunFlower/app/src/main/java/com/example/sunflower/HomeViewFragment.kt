package com.example.sunflower

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower.adapters.MY_GARDEN_PAGE_INDEX
import com.example.sunflower.adapters.SunflowerPagerAdapter
import com.example.sunflower.databinding.FragmentGardenBinding
import com.example.sunflower.databinding.FragmentHomeViewBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass.
 */
class HomeViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeViewBinding.inflate(inflater,container,false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager
        viewPager.adapter = SunflowerPagerAdapter(this)

        TabLayoutMediator(tabLayout,viewPager){tab,position->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabText(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getTabIcon(position:Int):Int{
        return when(position){
            MY_GARDEN_PAGE_INDEX->R.drawable.garden_tab_selector
            else -> R.drawable.plant_list_tab_selector
        }
    }
    private fun getTabText(position:Int):String{
        return when(position){
            MY_GARDEN_PAGE_INDEX->"My Garden"
            else -> "Plant List"
        }
    }

}
