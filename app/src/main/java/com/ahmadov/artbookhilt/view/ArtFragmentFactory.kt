package com.ahmadov.artbookhilt.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.ahmadov.artbookhilt.adapter.ArtRecyclerAdapter
import com.ahmadov.artbookhilt.adapter.ImageRecyclerAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val glide :RequestManager,
    private val artRecyclerAdapter: ArtRecyclerAdapter,
    private val imageRecyclerAdapter: ImageRecyclerAdapter
) :FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            ArtFragment::class.java.name -> ArtFragment(artRecyclerAdapter)
            ArtDetailsfragment::class.java.name -> ArtDetailsfragment(glide)
            else -> super.instantiate(classLoader, className)
        }


    }
}