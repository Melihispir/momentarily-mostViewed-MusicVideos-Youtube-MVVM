package com.melihispir.youdino.view

import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.melihispir.youdino.R
import com.melihispir.youdino.adapter.OnItemClickListener
import com.melihispir.youdino.adapter.SongAdapter
import com.melihispir.youdino.adapter.SongAdapter2
import com.melihispir.youdino.model.Song
import com.melihispir.youdino.viewmodel.SongViewModel
import kotlinx.android.synthetic.main.fragment_song_list.*


class SongListFragment : Fragment(),OnItemClickListener {

    private lateinit var viewModel : SongViewModel
    private val songAdapter = SongAdapter2(arrayListOf(),this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)
        viewModel = ViewModelProviders.of(this).get(SongViewModel::class.java)
        viewModel.refreshData()
        songListRecycler.layoutManager = LinearLayoutManager(context)
        songListRecycler.adapter = songAdapter
        swipeRefreshLayout.setOnRefreshListener {
            songLoading.visibility = View.VISIBLE
            songListRecycler.visibility = View.GONE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.SongList.observe(viewLifecycleOwner, Observer { songs ->
            songs?.let {

                songListRecycler.visibility = View.VISIBLE
                songAdapter.UpdateSongList(songs)

            }
        })

        viewModel.SongLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    songListRecycler.visibility = View.GONE
                    songLoading.visibility = View.VISIBLE
                } else {
                    songLoading.visibility = View.GONE
                }

            }

        })


    }

    override fun onItemClicked(song: Song) {
        val action = SongListFragmentDirections.actionSongListFragmentToSongVideoFragment(song.SongUrl)
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }

    }
}