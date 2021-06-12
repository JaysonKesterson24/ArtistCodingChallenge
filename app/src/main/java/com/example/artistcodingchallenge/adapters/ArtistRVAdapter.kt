package com.example.artistcodingchallenge.adapters

import android.media.MediaParser
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistcodingchallenge.databinding.ActivityMainBinding
import com.example.artistcodingchallenge.databinding.TrackListItemBinding
import com.example.artistcodingchallenge.models.TrackData

class ArtistRVAdapter : RecyclerView.Adapter<ArtistRVAdapter.ViewHolder>() {

    private var trackList: List<TrackData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = TrackListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trackList[position])
    }

    fun updateList(newList : List<TrackData>) {
        this.trackList = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding : TrackListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(trackData: TrackData) {

            with(binding) {
                artistName.text = trackData.artistName
                trackName.text = trackData.trackName
                trackPrice.text = trackData.trackPrice.toString()
                genre.text = trackData.primaryGenreName
                releaseDate.text = trackData.releaseDate
            }

        }

    }
}