package com.cfk.rijmwoorden

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException
import java.io.InputStream


fun getJsonDataFromAsset(filepath: String): String? {
    val jsonString: String
    try {
        val inputStream: InputStream = File(filepath).inputStream()
        jsonString = inputStream.bufferedReader().use { it.readText() }

    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

fun create_rhyme_dictionaries() {
    val gson = Gson()

    val inputStream: InputStream = File("src/main/assets/word_list.json").inputStream()
    val json_word_list = inputStream.bufferedReader().use { it.readText() }
    val converter = object : TypeToken<List<String>>() {}.type
    val words: List<String> = gson.fromJson(json_word_list, converter)
    println("got words")

    val full_dict = mutableMapOf<String, String>()
    val vowel_dict = mutableMapOf<String, String>()
    println("got dicts")

    for (entry in words) {
        //println(entry)
        if (!(containsInvalidChar(entry))) {
            val word = Word(entry)
            full_dict[word.text] = word.get_rhyme_part("full")
            vowel_dict[word.text] = word.get_rhyme_part("vowel")
            //println(word.text)
        }
    }

    val full_json = gson.toJson(full_dict)
    val vowel_json = gson.toJson(vowel_dict)
    File("src/main/assets/full_dictionary2.json").writeText(full_json)
    File("src/main/assets/vowel_dictionary2.json").writeText(vowel_json)
}


fun find_rhyme(inputWord: String, rhymeType: String): List<String> {
    val rhyme_part = Word(inputWord).get_rhyme_part(rhymeType)
    val filepath: String = if (rhymeType == "full") {
        "src/main/assets/full_dictionary2.json"
    } else {
        "src/main/assets/vowel_dictionary2.json"
    }

    val jsonFileString = getJsonDataFromAsset(filepath)
    val gson = Gson()
    val converter = object : TypeToken<Map<String, String>>() {}.type
    val words: Map<String, String> = gson.fromJson(jsonFileString, converter)

    val rhyme_words = mutableListOf<String>()
    for (word in words) {
        if (word.value == rhyme_part && !containsInvalidChar(word.key)) {
            rhyme_words.add(word.key)
            //println(word.key)
        }
    }
    return rhyme_words
}

fun containsInvalidChar(word: String): Boolean {
    val allowed_characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzÁÄáäÉËéëÍÏíïÚÜúüÓÖóö-"
    var containsInvalidChar = false
    if (word.isNotEmpty()) {
        for (c in word.toCharArray()) {
            if (c !in allowed_characters) {
                containsInvalidChar = true
                break
            }

        }
    }
    return containsInvalidChar
}

fun find_rhyme(inputWord: String, rhymeType: String, context: Context): List<String> {


    val rhyme_part = Word(inputWord).get_rhyme_part(rhymeType)
    val filepath: String = if (rhymeType == "full") {
        "full_dictionary2.json"
    } else {
        "vowel_dictionary2.json"
    }
    val jsonFileString = getJsonDataFromAsset(context, filepath)
    val gson = Gson()
    val listPersonType = object : TypeToken<Map<String, String>>() {}.type


    val words: Map<String, String> = gson.fromJson(jsonFileString, listPersonType)
    val rhyme_words = mutableListOf<String>()
    for (word in words) {
        if (word.value == rhyme_part && !containsInvalidChar(word.key)) {
            rhyme_words.add(word.key)
        }
    }
    return rhyme_words
}

