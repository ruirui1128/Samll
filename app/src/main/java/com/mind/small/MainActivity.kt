package com.mind.small

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mind.base.LocalHandler
import com.mind.small.enums.ModuleEnums
import com.mind.small.model.ModuleConfig
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private var faceHandler: LocalHandler? = null
    private var printerHandler: LocalHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jsonData()
        BuildConfig.DEBUG

    }

    private fun jsonData() {
        val content = readModulesFile()
        try {
            val gson = Gson()
//            val listType = object : TypeToken<List<String>>() {}.type
//            val list: List<String> = gson.fromJson(modules, listType)
//            list.forEach {
//                disPatcherModule(it)
//            }

            val config = gson.fromJson(content, ModuleConfig::class.java)
            config.modules?.forEach {
                disPatcherModule(it)
            }

        } catch (e: Exception) {
            Log.e(TAG, "解析失败:" + e.message)
        }

    }


    // 读取 assets 文件夹中的 modules.txt 文件内容
    private fun readModulesFile(): String {
        val assetManager = assets
        val stringBuilder = StringBuilder()

        try {
            // 打开文件
            val inputStream = assetManager.open("configModules.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?

            // 逐行读取文件内容
            while ((reader.readLine().also { line = it }) != null) {
                stringBuilder.append(line).append("\n")
            }

            // 关闭流
            reader.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return stringBuilder.toString()
    }

    // dispatcher

    private fun disPatcherModule(module: String) {
        when (module) {
            ModuleEnums.FACE_MIND_EN.pModuleType -> createFaceMind()
            ModuleEnums.PRINTER_MIND_EN.pModuleType -> createPrintMind()
        }

    }


    private fun createPrintMind() {
        try {
            val faceHandlerLocalClass = Class.forName(ModuleEnums.PRINTER_MIND_EN.clazzName)
            val constructor = faceHandlerLocalClass.getConstructor()
            printerHandler = constructor.newInstance() as LocalHandler?
            val tv = findViewById<TextView>(R.id.tv2)
            printerHandler?.handle("执行", tv)
        } catch (e: Exception) {
            Log.e(TAG, "异常：" + e.message)
        }
    }

    private fun createFaceMind() {
        try {
            val faceHandlerLocalClass = Class.forName(ModuleEnums.FACE_MIND_EN.clazzName)
            val constructor = faceHandlerLocalClass.getConstructor()
            faceHandler = constructor.newInstance() as LocalHandler?
            val tv = findViewById<TextView>(R.id.tv)
            faceHandler?.handle("执行", tv)
        } catch (e: Exception) {
            Log.e(TAG, "createFaceMind异常:" + e.message)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        faceHandler?.onDestroy()
        printerHandler?.onDestroy()
    }

}

