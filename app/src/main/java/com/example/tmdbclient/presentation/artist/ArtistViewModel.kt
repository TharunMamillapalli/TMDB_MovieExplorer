package com.example.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.GetArtistsUseCase
import com.example.tmdbclient.domain.usecase.UpdateArtistUseCase

class ArtistViewModel(
    private val getArtistsUseCase:GetArtistsUseCase,
    private val updateArtistsUseCase:UpdateArtistUseCase
):ViewModel() {
    fun getArtists()= liveData {
        val artistList=getArtistsUseCase.execute()
        emit(artistList)
    }
    fun updateArtists()= liveData {
        val artistList=updateArtistsUseCase.execute()
        emit(artistList)
    }
}