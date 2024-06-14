package com.mind.face

import android.util.Log
import android.widget.TextView
import com.mind.base.LocalHandler

/**
 * create by Rui on 2024-06-12
 * desc:
 */
class FaceHandler : LocalHandler {
    companion object {
        private val TAG = FaceHandler::class.java.simpleName
    }

    override fun handle(msg: String, tv: TextView) {
        Log.e(TAG, "======执行 FaceHandler=====")
        tv.text = "$msg:FaceHandler"
    }

    override fun onDestroy() {
        Log.e(TAG, "======执行 FaceHandler  onDestroy =====")
    }
}