package com.ahmadov.artbookhilt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadov.artbookhilt.model.ImageResponse
import com.ahmadov.artbookhilt.repo.ArtRepositoryInterface
import com.ahmadov.artbookhilt.roomdb.Art
import com.ahmadov.artbookhilt.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArtViewModel @Inject constructor(

    private val repository: ArtRepositoryInterface

) : ViewModel() {

    //Art Fragment
    val artList=repository.getArt()

    //Image Api Fragment

    private val images=MutableLiveData<Resource<ImageResponse>>()
    val imageList:LiveData<Resource<ImageResponse>>
        get()=images

    private val selectedImage=MutableLiveData<String>()
    val selectedImageUrl:LiveData<String>
        get () =selectedImage

    //Art Details Fragment

    private var insertArtMsg=MutableLiveData<Resource<Art>>()
    val insertArtMessage:LiveData<Resource<Art>>
        get()=insertArtMsg

    fun resetArtMsg(){
        insertArtMsg=MutableLiveData<Resource<Art>>()
    }

    fun setSelectedImage(url:String){
        selectedImage.postValue(url)
    }

    fun deleteArt(art:Art)=viewModelScope.launch{
        repository.deleteArt(art)
    }

    fun insertArt(art:Art)=viewModelScope.launch{
        repository.insertArt(art)
    }

    fun makeArt(name:String,artistName:String,year:String){
        if(name.isEmpty() || artistName.isEmpty() || year.isEmpty()){
            insertArtMsg.postValue(Resource.error("Enter name, artist , year",null))
            return
        }
        val yearInt=try{
            year.toInt()

        }catch (e:Exception){
            insertArtMsg.postValue(Resource.error("Year should be number",null))
            return
        }
        val art=Art(name,artistName,yearInt,selectedImageUrl.value?: "")
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))
    }

    fun searchForImage(searchString:String){
        if(searchString.isEmpty()){
            return
        }
        images.value=Resource.loading(null)
        viewModelScope.launch {
            val response=repository.searchImage(searchString)
            images.value=response
        }

    }

}