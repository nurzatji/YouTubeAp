package com.geektech.youtubeapi.ui.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geektech.youtubeapi.data.remote.model.Playlist
import com.geektech.youtubeapi.data.remote.model.PlaylistItem
import com.geektech.youtubeapi.databinding.ItemDetailBinding
import com.geektech.youtubeapi.utils.loadImage

class DetailAdapter() : Adapter<DetailAdapter.DetailViewHolder>() {

    private var list = ArrayList<PlaylistItem.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<Playlist.Item>) {
        this.list = list as ArrayList<PlaylistItem.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class DetailViewHolder(private val binding: ItemDetailBinding) :
        ViewHolder(binding.root) {
        fun bind(item: PlaylistItem.Item) {
            with(binding) {
                tvVideoName.text = item.snippet?.title
                tvTime.text = item.snippet?.publishedAt
                ivVideo.loadImage(item.snippet?.thumbnails?.standard?.url!!)
            }
        }
    }
}