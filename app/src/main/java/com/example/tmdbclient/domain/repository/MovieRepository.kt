package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.tvshow.TvShow

interface MovieRepository {
    suspend fun getMovies():List<Movie>?

    suspend fun updateMovies():List<Movie>?


}