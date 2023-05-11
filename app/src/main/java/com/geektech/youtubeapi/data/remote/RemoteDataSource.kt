package com.geektech.youtubeapi.data.remote

import com.geektech.youtubeapi.BuildConfig
import com.geektech.youtubeapi.core.network.BaseDataSource
import com.geektech.youtubeapi.core.network.RetrofitClient
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.data.remote.model.Playlist
import com.geektech.youtubeapi.data.remote.model.PlaylistItem

class RemoteDataSource : BaseDataSource() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    suspend fun getPlaylists(): Resource<Playlist> {
        return getResult {
            apiService.getPlaylists(
                BuildConfig.API_KEY,
                "contentDetails,snippet",
                "UCWOA1ZGywLbqmigxE4Qlvuw",
                30
            )
        }
    }

    suspend fun getPlaylistItems(playlistId: String): Resource<PlaylistItem> {
        return getResult {
            apiService.getPlaylistItems(
                BuildConfig.API_KEY,
                "contentDetails,snippet",
                playlistId,
                30
            )
        }
    }
}