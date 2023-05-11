package com.geektech.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import com.geektech.youtubeapi.App
import com.geektech.youtubeapi.core.ui.BaseViewModel
import com.geektech.youtubeapi.data.remote.model.Playlist
import com.geektech.youtubeapi.core.network.result.Resource

class PlaylistsViewModel : BaseViewModel() {

    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return App().repository.getPlaylists()
    }
}