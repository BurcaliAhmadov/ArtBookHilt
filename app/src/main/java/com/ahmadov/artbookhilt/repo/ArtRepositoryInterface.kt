package com.ahmadov.artbookhilt.repo

import androidx.lifecycle.LiveData
import com.ahmadov.artbookhilt.api.RetrofitApi
import com.ahmadov.artbookhilt.model.ImageResponse
import com.ahmadov.artbookhilt.roomdb.Art
import com.ahmadov.artbookhilt.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art:Art)

    suspend fun searchImage(imageString:String):Resource<ImageResponse>

    fun getArt() : LiveData<List<Art>>
}