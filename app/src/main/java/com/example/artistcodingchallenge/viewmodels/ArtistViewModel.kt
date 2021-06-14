package com.example.artistcodingchallenge.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artistcodingchallenge.api.ArtistRepository
import com.example.artistcodingchallenge.models.ArtistResponse
import com.example.artistcodingchallenge.models.TrackData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val artistRepo: ArtistRepository
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private val tracks: MutableLiveData<List<TrackData>> = MutableLiveData()

    fun getTracks(): LiveData<List<TrackData>> {
        return tracks
    }

    fun loadTracks(term: String) {
        disposables.add(
            artistRepo.getTrackResults(term)
                .subscribeOn(Schedulers.io())
                .subscribe(this::trackSuccess, this::onError)
        )
    }

    private fun trackSuccess(artistResponse: ArtistResponse) {
        tracks.postValue(artistResponse.trackData)
    }

    private fun onError(throwable: Throwable) {
        Log.e("_WORK", "${throwable.message}")
    }
}