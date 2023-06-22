package com.ahmadov.artbookhilt.api

import com.ahmadov.artbookhilt.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.ahmadov.artbookhilt.util.Util.API_KEY
import retrofit2.Response


interface RetrofitApi  {
    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery:String,
        @Query("key") apiKey:String= API_KEY
    ):Response<ImageResponse>
}