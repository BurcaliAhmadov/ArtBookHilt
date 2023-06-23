package com.ahmadov.artbookhilt.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.ahmadov.artbookhilt.api.RetrofitApi
import com.ahmadov.artbookhilt.roomdb.ArtDatabase
import com.ahmadov.artbookhilt.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context:Context)= Room.databaseBuilder(
        context,ArtDatabase::class.java,"ArtBookDB").build()


    @Singleton
    @Provides
    fun injectDao(database:ArtDatabase)=database.artDao()

    @Singleton
    @Provides
    fun injectRetrofitApi():RetrofitApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(RetrofitApi::class.java)
    }


}