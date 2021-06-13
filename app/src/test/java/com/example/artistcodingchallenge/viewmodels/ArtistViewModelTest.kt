package com.example.artistcodingchallenge.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.artistcodingchallenge.api.ArtistRepository
import com.example.artistcodingchallenge.models.ArtistResponse
import com.example.artistcodingchallenge.models.ArtistResponses
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ArtistViewModelTest {
    @get:Rule
    // Using executor rule to run tasks synchronously
    val instantExecutorRule = InstantTaskExecutorRule()

    private val artistRepository : ArtistRepository = mockk()

    private val artistName: String = "Rihanna"
    private val artistResponse : ArtistResponse = ArtistResponses.testArtistResponse

    private lateinit var artistViewModel: ArtistViewModel

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        artistViewModel = ArtistViewModel(artistRepository)

        every{artistRepository.getTrackResults(artistName)} returns Single.just(artistResponse)
    }

    @Test
    fun `loadTracks with artist name returns ArtistResponse`() {
        artistViewModel.loadTracks(artistName) // Making the call for the test
        artistViewModel.getTracks().observeForever {} // Setting a test observer for LiveData to update
        assertThat(artistViewModel.getTracks().value).isEqualTo(artistResponse.trackData) // checking to make sure we are getting what we expect
    }
}