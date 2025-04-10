package com.example.tmdbclient.data.repository.artist.datasourceimpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private var tmdbService: TMDBService,
    private var apiKey:String
): ArtistRemoteDataSource {
    override suspend fun getArtists(): Response<ArtistList> =tmdbService.getPopularArtists(apiKey)
}