package com.example.images

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_pager_photo.*

/**
 * A simple [Fragment] subclass.
 */
class PagerPhotoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fpp_photoTag.text = "Loading..."
        var photos: ArrayList<PhotoItem>? = arguments?.getParcelableArrayList("photos")
        PagerPhotoAdapter().apply {
            fpp_photoViewPager.adapter = this
            submitList(photos)
        }

        fpp_photoViewPager.registerOnPageChangeCallback (object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                fpp_photoTag.text = "${position+1}/${photos?.size}"
            }
            }
        )

        arguments?.getInt("photo_position")?.let { fpp_photoViewPager.setCurrentItem(it,false) }
    }
}
