package com.example.sunflower

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import com.example.sunflower.adapters.PlantAdapter
import com.example.sunflower.data.PlantRepository

import com.example.sunflower.databinding.FragmentPlantListBinding
import com.example.sunflower.databinding.ListItmePlantBinding
import com.example.sunflower.utilities.InjectorUtils
import com.example.sunflower.viewmodels.PlantListViewModel
import kotlinx.android.synthetic.main.fragment_plant_list.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlantListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlantListFragment : Fragment() {

    private val viewModel: PlantListViewModel by viewModels{
        InjectorUtils.providePlantListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = FragmentPlantListBinding.inflate(inflater,container,false)
        context?:return binding.root

        var adapter = PlantAdapter()
        binding.plantList.adapter = adapter


        viewModel.plants.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            adapter.submitList(it)
        })

        return binding.root
    }


}
