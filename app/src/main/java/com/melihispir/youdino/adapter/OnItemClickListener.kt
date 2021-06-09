package com.melihispir.youdino.adapter

import android.view.View
import com.melihispir.youdino.model.Song

interface OnItemClickListener {
    fun onItemClicked( song: Song)
}