package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDatasource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDatasource
import com.example.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieLocalDatasource: MovieLocalDatasource,
        movieRemoteDatasource: MovieRemoteDatasource,
        movieCacheDataSource:MovieCacheDataSource
    ):MovieRepository{
        return MovieRepositoryImpl(movieRemoteDatasource,movieLocalDatasource,movieCacheDataSource)
    }

    @Singleton
    @Provides
    fun provideArtistRepository(
        artistLocalDataSource:ArtistLocalDataSource,
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistCacheDataSource: ArtistCacheDataSource
    ):ArtistRepository{
        return ArtistRepositoryImpl(artistLocalDataSource,artistRemoteDataSource,artistCacheDataSource)
    }

    @Singleton
    @Provides
    fun provideTvShowRepository(
        tvShowLocalDataSource:TvShowLocalDataSource,
        tvShowRemoteDataSource:TvShowRemoteDataSource,
        tvShowCacheDataSource:TvShowCacheDataSource
    ):TvShowRepository{
        return TvShowRepositoryImpl(tvShowLocalDataSource,tvShowRemoteDataSource,tvShowCacheDataSource)
    }
}