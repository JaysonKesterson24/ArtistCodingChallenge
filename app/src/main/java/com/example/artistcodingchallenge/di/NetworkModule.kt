package com.example.artistcodingchallenge.di

import com.example.artistcodingchallenge.api.ArtistApiService
import com.example.artistcodingchallenge.api.ArtistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl : String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideArtistApiService() : ArtistApiService {
        return provideRetrofit("https://itunes.apple.com/")
            .create(ArtistApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideArtistRepository() : ArtistRepository {
        return ArtistRepository(provideArtistApiService())
    }
}