package com.melihispir.youdino.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.melihispir.youdino.R
import kotlinx.android.synthetic.main.fragment_song_video.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.IOException


class SongVideoFragment : Fragment() {


    private var SongUrl = ""
    private var value = ""
    private var iframeSrc=""
    private var iframeSrc2=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val action = SongVideoFragmentDirections.actionSongVideoFragmentToSongListFragment()
                view?.let {
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            SongUrl = SongVideoFragmentArgs.fromBundle(it).songUrl
        }
        var doc3: Document? = null
        try {
            doc3 = Jsoup.connect(SongUrl).get()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val iframe: Element = doc3?.select("iframe")!!.first()
        iframeSrc = iframe.attr("src")
        iframeSrc2 = iframeSrc.replace("embed/", "watch?v=")
        webview.webViewClient = WebViewClient()
        webview.settings.javaScriptEnabled = true
        webview.loadUrl(iframeSrc2)


    }





}