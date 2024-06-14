package com.mind.printer

import android.util.Log
import android.widget.TextView
import com.mind.base.LocalHandler

/**
 * create by Rui on 2024-06-12
 * desc:
 */
class PrinterHandler : LocalHandler {
    companion object {
        private val TAG = PrinterHandler::class.java.simpleName
    }

    override fun handle(msg: String, tv: TextView) {
        Log.e(TAG, "======执行 PrinterHandler=====")
        tv.text = "$msg:PrinterHandler"
    }

    override fun onDestroy() {
        Log.e(TAG, "======执行 PrinterHandler  onDestroy =====")
    }
}