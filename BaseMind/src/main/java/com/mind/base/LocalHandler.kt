package com.mind.base

import android.widget.TextView

/**
 * create by Rui on 2024-06-12
 * desc:
 */
interface LocalHandler {
    fun handle(msg: String, tv: TextView)
    fun onDestroy()
}