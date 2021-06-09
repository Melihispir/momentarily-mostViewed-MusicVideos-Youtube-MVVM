package com.melihispir.youdino.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.melihispir.youdino.model.Song
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException


class SongViewModel : ViewModel() {
    val  SongList = MutableLiveData<List<Song>>()
    val  SongLoading = MutableLiveData<Boolean>()


    fun refreshData(){
        var s  = " "
        var list2 =  arrayListOf<String>()
        var doc: Document? = null
        try {
            doc = Jsoup.connect("https://kworb.net/youtube/").get()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val el: Elements? = doc?.select("tr")
        val el2: Elements? = doc?.select("a[href]")

        var  s3 = " "
        var  s4 = " "
        el?.removeAt(0)
       if(el!=null) {
           for (e in el) {
               s = e.text()
               var dizi: List<String> = s.split(" ")

               for (i in 0..dizi.size - 1) {
                   if (i == 0)
                       continue;
                   else if (i == 1)
                       continue;
                   else if (i == dizi.size - 2)
                       continue;
                   else if (i == dizi.size - 1)
                       continue;
                   else
                       s4 += dizi[i] + " "
               }

               list2.add(s4)
               s4 = " "
           }
       }

        var songliste = arrayListOf<Song>()
        var list = arrayListOf<String>()


        if (el2 != null) {
            for(e in el2){
                    s=e.attr( "href" )
                if(s.contains("video")){
                    var  s2 = "https://kworb.net/youtube/"
                    s3= s2+s
                    list.add(s3)
                }
               else if (s.contains("https://www.youtube.com/watch"))
                   list.add(s)

            }
            list?.removeAt(0)
        }
        for(i in 0.. list2.size-1){
            var song  = Song (list2.get(i),list.get(i))
            songliste.add(song)
        }



        SongList.value = songliste
        SongLoading.value = false

    }




}


