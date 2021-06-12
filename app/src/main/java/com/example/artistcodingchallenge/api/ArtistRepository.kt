package com.example.artistcodingchallenge.api

import com.example.artistcodingchallenge.models.ArtistResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ArtistRepository {

    private val baseUrl = "https://itunes.apple.com/"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val artistApiService : ArtistApiService = retrofit.create(ArtistApiService::class.java)

    fun getTrackResults(term : String) : Single<ArtistResponse> {
        return artistApiService.getArtistTracks(term)
    }
}