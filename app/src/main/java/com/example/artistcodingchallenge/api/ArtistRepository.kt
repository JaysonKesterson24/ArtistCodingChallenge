package com.example.artistcodingchallenge.api

import com.example.artistcodingchallenge.models.ArtistResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ArtistRepository(private val artistApiService: ArtistApiService) {

    fun getTrackResults(term : String) : Single<ArtistResponse> {
        return artistApiService.getArtistTracks(term)
    }
}