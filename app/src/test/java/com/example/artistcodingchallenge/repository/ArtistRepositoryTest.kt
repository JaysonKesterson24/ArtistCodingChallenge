package com.example.artistcodingchallenge.repository

import com.example.artistcodingchallenge.api.ArtistRepository
import com.example.artistcodingchallenge.di.NetworkModule
import com.example.artistcodingchallenge.models.ArtistResponses
import io.reactivex.disposables.CompositeDisposable
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class ArtistRepositoryTest {
    //grabbing ApiService to test Repository functionality
    private val artistApiService = NetworkModule.provideArtistApiService()

    private lateinit var artistRepository: ArtistRepository
    private val disposables = CompositeDisposable()

    @Before
    fun setUp() {
        artistRepository = ArtistRepository(artistApiService)
    }

    // using @after to take care of clean up after tests are done
    @After
    fun tearDown() {
        disposables.clear()
    }

    @Test
    fun `getTrackResults returns valid ArtistResponse`() {
        disposables.add(
            artistRepository.getTrackResults("Rihanna").test()
                .awaitDone(200, TimeUnit.MILLISECONDS)
                .assertNoErrors()
                .assertComplete()
                .assertValueCount(1)
                .apply {
                    values().single().also { response ->
                        assertThat((response.trackData?.first()?.artistName)).isEqualTo(
                            ArtistResponses.testArtistResponse.trackData?.first()?.artistName
                        )
                    }
                }
        )
    }
}