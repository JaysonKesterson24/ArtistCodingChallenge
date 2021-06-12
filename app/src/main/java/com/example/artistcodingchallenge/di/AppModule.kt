package com.example.artistcodingchallenge.di

import android.content.Context
import com.example.artistcodingchallenge.ArtistApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideArtistApplication(@ApplicationContext context: Context) : ArtistApplication {
        return context as ArtistApplication
    }

}