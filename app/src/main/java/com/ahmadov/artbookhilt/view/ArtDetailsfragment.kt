package com.ahmadov.artbookhilt.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ahmadov.artbookhilt.R
import com.ahmadov.artbookhilt.databinding.FragmentArtDetailsBinding
import com.ahmadov.artbookhilt.util.Status
import com.ahmadov.artbookhilt.viewmodel.ArtViewModel
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArtDetailsfragment
@Inject constructor(
    val glide: RequestManager
)
    :Fragment(R.layout.fragment_art_details) {
    private var fragmentBinding:FragmentArtDetailsBinding?=null
    lateinit var viewModel:ArtViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        val binding =FragmentArtDetailsBinding.bind(view)
        fragmentBinding=binding

        subscribeToObservers()

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

        binding.saveButton.setOnClickListener {
            viewModel.makeArt(
                 binding.artText.text.toString()
                ,binding.artistText.text.toString()
                ,binding.yearText.text.toString())
        }


    }
    private fun subscribeToObservers(){
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer {url->
            fragmentBinding?.let {
                glide.load(url).into(it.artImageView)
            }
        })

        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                    viewModel.resetArtMsg()
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message?:" Error", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {

                }
            }

        })
    }


    override fun onDestroyView() {
        fragmentBinding=null
        super.onDestroyView()
    }
}