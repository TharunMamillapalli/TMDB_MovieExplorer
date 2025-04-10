package com.example.tmdbclient.data.repository.tvshow

import android.util.Log
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.tmdbclient.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
    private var tvShowLocalDataSource:TvShowLocalDataSource,
    private var tvShowRemoteDataSource:TvShowRemoteDataSource,
    private var tvShowCacheDataSource: TvShowCacheDataSource
):TvShowRepository {

    override suspend fun getTvShows(): List<TvShow> {
        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow> {
        var newListOfTvShows=getTvShowsFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(newListOfTvShows)
        tvShowCacheDataSource.saveTvShowsToCache(newListOfTvShows)
        return newListOfTvShows

    }

    suspend fun getTvShowsFromAPI():List<TvShow>{
        lateinit var tvShowsList:List<TvShow>
        try {
            var response=tvShowRemoteDataSource.getTvShow()
            var body=response.body()
            if(body!=null){
                tvShowsList=body.tvShows
            }

        }catch (exception:Exception){
            Log.i("Mytag",exception.message.toString())
        }
        return tvShowsList
    }
    suspend fun getTvShowsFromDB():List<TvShow>{
        lateinit var tvShowsList:List<TvShow>
        try {
            tvShowsList=tvShowLocalDataSource.getTvShowsFromDB()
        }catch (exception:Exception){
            Log.i("MyTag",exception.message.toString())
        }
        if (tvShowsList.size>0){
            return tvShowsList
        }else{
            tvShowsList=getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowsList)
        }
        return tvShowsList
    }
    suspend fun getTvShowsFromCache():List<TvShow>{
        lateinit var tvShowsList:List<TvShow>
        try {
            tvShowsList=tvShowCacheDataSource.getTvShowFromCache()
        }catch (exception:Exception){
            Log.i("MyTag",exception.message.toString())
        }
        if (tvShowsList.size>0){
            return tvShowsList
        }else{
            tvShowsList=getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowsList)
        }
        return tvShowsList
    }
}