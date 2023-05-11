package com.geektech.youtubeapi.ui.detail

import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.geektech.youtubeapi.core.network.result.Status
import com.geektech.youtubeapi.core.ui.BaseActivity
import com.geektech.youtubeapi.data.remote.model.Playlist
import com.geektech.youtubeapi.databinding.ActivityDetailBinding
import com.geektech.youtubeapi.ui.detail.adapter.DetailAdapter
import com.geektech.youtubeapi.ui.playlists.PlaylistsActivity.Companion.DESCRIPTION
import com.geektech.youtubeapi.ui.playlists.PlaylistsActivity.Companion.ID
import com.geektech.youtubeapi.ui.playlists.PlaylistsActivity.Companion.TITLE
import com.geektech.youtubeapi.utils.ConnectionLiveData

class DetailActivity() : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    private lateinit var adapter: DetailAdapter
    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        adapter = DetailAdapter()
        binding.recyclerView.adapter = adapter
    }


    override fun initViewModel() {
        super.initViewModel()
        val getIntentId =
            intent.getStringExtra(ID)
        val getIntentDesc = intent.getStringExtra(DESCRIPTION)
        val getIntentTitle = intent.getStringExtra(TITLE)
        viewModel.getPlaylistItems(getIntentId!!).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                    adapter.addList(it.data?.items as List<Playlist.Item>)
                    binding.tvDescription.text = getIntentDesc
                    binding.tvTitle.text = getIntentTitle
                    binding.progressBar.isVisible = false
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false
                }
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    override fun isConnection() {
        super.isConnection()
        ConnectionLiveData(application).observe(this) {
            if (it) {
                binding.internetConnection.visibility = View.VISIBLE
                binding.noConnection.visibility = View.GONE
            } else {
                binding.internetConnection.visibility = View.GONE
                binding.noConnection.visibility = View.VISIBLE
                initViewModel()
            }
        }
    }
}