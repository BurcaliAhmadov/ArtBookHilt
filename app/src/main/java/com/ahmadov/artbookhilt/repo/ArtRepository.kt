package com.ahmadov.artbookhilt.repo

import androidx.lifecycle.LiveData
import com.ahmadov.artbookhilt.api.RetrofitApi
import com.ahmadov.artbookhilt.model.ImageResponse
import com.ahmadov.artbookhilt.roomdb.Art
import com.ahmadov.artbookhilt.roomdb.ArtDao
import com.ahmadov.artbookhilt.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    val retrofitApi :RetrofitApi,
    val artDao:ArtDao
):ArtRepositoryInterface {
    override suspend fun insertArt(art: Art) {
        artDao.insertDao(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteDao(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try{
            val response=retrofitApi.imageSearch(imageString)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?: Resource.error("Error",null)
            }
            else{
                Resource.error("Error",null)
            }

        }
        catch (e:Exception){
            Resource.error("No Data",null)
        }
    }
}