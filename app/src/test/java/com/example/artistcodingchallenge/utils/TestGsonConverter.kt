package com.example.artistcodingchallenge.utils

import com.example.artistcodingchallenge.models.ArtistResponse
import com.google.gson.Gson

object TestGsonConverter {
    fun artistResponseFromJson(json : String) : ArtistResponse {
        val gson = Gson()
        return gson.fromJson(json, ArtistResponse::class.java)
    }
}