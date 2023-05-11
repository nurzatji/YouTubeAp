package com.geektech.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.data.remote.RemoteDataSource
import com.geektech.youtubeapi.data.remote.model.Playlist
import com.geektech.youtubeapi.data.remote.model.PlaylistItem
import kotlinx.coroutines.Dispatchers

class Repository {

    private val dataSource: RemoteDataSource by lazy {
        RemoteDataSource()
    }

    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlaylists()
            emit(response)
        }
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItem>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlaylistItems(playlistId)
            emit(response)
        }
    }
}