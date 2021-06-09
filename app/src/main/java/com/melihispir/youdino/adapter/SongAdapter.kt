package com.melihispir.youdino.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.melihispir.youdino.R
import com.melihispir.youdino.databinding.MusicRowBinding
import com.melihispir.youdino.model.Song
import com.melihispir.youdino.view.SongListFragmentDirections
import kotlinx.android.synthetic.main.music_row.view.*
import androidx.navigation.findNavController

class SongAdapter (val SongList : ArrayList<Song>,val mListener: OnItemClickListener) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    class SongViewHolder(var view: MusicRowBinding) : RecyclerView.ViewHolder(view.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MusicRowBinding>(inflater,
                R.layout.music_row, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int {
        return SongList.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.view.song = SongList[position]



    }


    fun UpdateSongList(NewSongList: List<Song>) {
        SongList.clear()
        SongList.addAll(NewSongList)
        notifyDataSetChanged()
    }
}
