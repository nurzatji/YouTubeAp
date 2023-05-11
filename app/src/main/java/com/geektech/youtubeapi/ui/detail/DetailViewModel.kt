package com.geektech.youtubeapi.ui.detail

import androidx.lifecycle.LiveData
import com.geektech.youtubeapi.App
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.core.ui.BaseViewModel
import com.geektech.youtubeapi.data.remote.model.Playlist
import com.geektech.youtubeapi.data.remote.model.PlaylistItem

class DetailViewModel : BaseViewModel() {

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItem>> {
        return App().repository.getPlaylistItems(playlistId)
    }
}