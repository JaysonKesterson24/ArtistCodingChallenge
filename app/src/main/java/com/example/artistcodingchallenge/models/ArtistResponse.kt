package com.example.artistcodingchallenge.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArtistResponse (
    @SerializedName("resultCount")
    @Expose
    var resultCount: Int? = null,

    @SerializedName("results")
    @Expose
    var trackData: List<TrackData>? = null
)