package com.example.artistcodingchallenge.api

import com.example.artistcodingchallenge.models.ArtistResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistApiService {
    @GET("search/")
    fun getArtistTracks(
        @Query("term") term : String = "Adele"
    ) : Single<ArtistResponse>
}