package com.ahmadov.artbookhilt.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ahmadov.artbookhilt.R
import com.ahmadov.artbookhilt.databinding.FragmentArtDetailsBinding

class ArtDetailsfragment:Fragment(R.layout.fragment_art_details) {
    private var fragmentBinding:FragmentArtDetailsBinding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding =FragmentArtDetailsBinding.bind(view)
        fragmentBinding=binding

        binding.artImageView.setOnClickListener {
            val action=ArtDetailsfragmentDirections.actionArtDetailsfragmentToImageApiFragment()
            findNavController().navigate(action)
        }
        val callback=object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


    }

    override fun onDestroyView() {
        fragmentBinding=null
        super.onDestroyView()
    }
}