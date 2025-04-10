package com.example.tmdbclient.data.repository.tvshow.datasourceimpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.tvshow.TvShowList
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private var tmdbService: TMDBService,
    private var apiKey:String
    ): TvShowRemoteDataSource {
    override suspend fun getTvShow(): Response<TvShowList> =tmdbService.getPopularTvShows(apiKey)

}