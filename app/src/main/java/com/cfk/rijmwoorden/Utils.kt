package com.cfk.rijmwoorden

import android.content.Context
import java.io.File
import java.io.IOException
import java.io.InputStream


fun getJsonDataFromAsset(): String? {
    val jsonString: String
    try {
        val inputStream: InputStream = File("src/main/assets/word_list.json").inputStream()



        //val inputString = inputStream.bufferedReader().use { it.readText() }
        jsonString = inputStream.bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}
