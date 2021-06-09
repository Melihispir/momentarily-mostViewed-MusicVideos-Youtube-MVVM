package com.melihispir.youdino.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.melihispir.youdino.R
import com.melihispir.youdino.databinding.MusicRowBinding
import com.melihispir.youdino.model.Song

class SongAdapter2 internal constructor(
        private val songList: ArrayList<Song>,
        private val mListener: OnItemClickListener
) : RecyclerView.Adapter<SongAdapter2.SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder = SongViewHolder.from(parent)

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) = holder.bind(songList[position], mListener)

    override fun getItemCount(): Int = songList.size

    class SongViewHolder(val binding: MusicRowBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(currentSong: Song, listener : OnItemClickListener){
            binding.song = currentSong
            binding.listener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SongViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: MusicRowBinding = DataBindingUtil
                        .inflate(layoutInflater, R.layout.music_row,
                                parent, false)
                return SongViewHolder(binding)
            }
        }
    }
    fun UpdateSongList(NewSongList: List<Song>) {
        songList.clear()
        songList.addAll(NewSongList)
        notifyDataSetChanged()
    }


}