package com.geektech.youtubeapi

import android.app.Application
import com.geektech.youtubeapi.repository.Repository

class App : Application() {

    val repository: Repository by lazy {
        Repository()
    }
}