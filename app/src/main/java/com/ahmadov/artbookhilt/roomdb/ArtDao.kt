package com.ahmadov.artbookhilt.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDao(art: Art)

    @Delete
    suspend fun deleteDao(art:Art)

    @Query("SELECT * FROM arts")
    fun observeArts():LiveData<List<Art>>
}