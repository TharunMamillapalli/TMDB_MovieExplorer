package com.example.tmdbclient.presentation.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.UpdateTvShowUseCase

class TvShowViewModel(
    private val getTvShowsUseCase:GetTvShowsUseCase,
    private val updateTvShowsUseCase:UpdateTvShowUseCase
):ViewModel() {
    fun getTvShows()= liveData {
        val tvShowList=getTvShowsUseCase.execute()
        emit(tvShowList)
    }
    fun updatTvShows()= liveData {
        val tvShowList=updateTvShowsUseCase.execute()
        emit(tvShowList)
    }
}