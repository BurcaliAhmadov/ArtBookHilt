package com.ahmadov.artbookhilt.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ahmadov.artbookhilt.R
import com.ahmadov.artbookhilt.adapter.ImageRecyclerAdapter
import com.ahmadov.artbookhilt.databinding.FragmentImageApiBinding
import javax.inject.Inject

class ImageApiFragment @Inject constructor(
    val imageRecyclerAdapter: ImageRecyclerAdapter
): Fragment(R.layout.fragment_image_api) {

    private var fragmentBinding:FragmentImageApiBinding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=FragmentImageApiBinding.bind(view)
        fragmentBinding=binding

    }




}