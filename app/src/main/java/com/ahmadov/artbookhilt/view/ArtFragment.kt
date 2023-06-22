package com.ahmadov.artbookhilt.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ahmadov.artbookhilt.R
import com.ahmadov.artbookhilt.databinding.FragmentArtBinding

class ArtFragment:Fragment(R.layout.fragment_art) {

private var fragmentBinding:FragmentArtBinding?=null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentArtBinding.bind(view)
        fragmentBinding=binding

        binding.fab.setOnClickListener {
            val action=ArtFragmentDirections.actionArtFragmentToArtDetailsfragment()
            findNavController().navigate(action)

        }

    }

    override fun onDestroyView() {
        fragmentBinding=null
        super.onDestroyView()
    }

}