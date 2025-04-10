package com.example.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateMoviesUseCase

class MovieViewModel(
    private val getMovieUseCase:GetMoviesUseCase,
    private val updateMovieUseCase:UpdateMoviesUseCase
):ViewModel() {
    fun getMovies()= liveData {
        val movieList=getMovieUseCase.execute()
        emit(movieList)
    }
    fun updateMovies()= liveData {
        val movieList=updateMovieUseCase.execute()
        emit(movieList)
    }
}