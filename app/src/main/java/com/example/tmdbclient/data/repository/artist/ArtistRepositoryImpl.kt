package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private var artistLocalDataSource:ArtistLocalDataSource,
    private var artistRemoteDataSource:ArtistRemoteDataSource,
    private var artistCacheDataSource:ArtistCacheDataSource
):ArtistRepository {
    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val newListOfMovies=getArtistsFromAPI()
        artistLocalDataSource. clearAll()
        artistLocalDataSource.saveArtistsToDB(newListOfMovies)

        return newListOfMovies
    }
    suspend fun getArtistsFromAPI():List<Artist>{
        lateinit var artistList:List<Artist>
        try {
            var response=artistRemoteDataSource.getArtists()
            var body=response.body()
            if (body!=null){
                artistList=body.artists
            }

        }catch (exception:Exception){
            Log.i("MyTag",exception.message.toString())
        }
        return artistList

    }
    suspend fun getArtistsFromDB():List<Artist>{
        lateinit var artistList:List<Artist>
        try {
            artistList=artistLocalDataSource.getArtistsFromDB()


        }catch (exception:Exception){
            Log.i("MyTag",exception.message.toString())
        }
        if (artistList.size>0){
            return artistList
        }else{
            artistList=getArtistsFromAPI()
            artistLocalDataSource.saveArtistsToDB(artistList)
        }
        return artistList
    }
    suspend fun getArtistsFromCache():List<Artist>{
        lateinit var artistList:List<Artist>
        try {
            artistList=artistCacheDataSource.getArtistsFromCache()

        }catch (exception:Exception){
            Log.i("MyTag",exception.message.toString())
        }
        if (artistList.size>0){
            return artistList
        }else{
            artistList=getArtistsFromDB()
            artistCacheDataSource.saveArtistsToCache(artistList)
        }
        return artistList
    }
}