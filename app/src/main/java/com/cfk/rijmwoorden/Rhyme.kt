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

    val full_dict = mutableMapOf<String, MutableList<String>>()
    val vowel_dict = mutableMapOf<String, MutableList<String>>()
    for (entry in words) {
        //println(entry)
        if (!(containsInvalidChar(entry))) {
            val word = Word(entry)
            val full_rhyme_part = word.get_rhyme_part("full")

            if (full_rhyme_part in full_dict) {
                full_dict[word.get_rhyme_part("full")]?.add(word.text)
            } else {
                full_dict[word.get_rhyme_part("full")] = mutableListOf(word.text)
            }

            val vowel_rhyme_part = word.get_rhyme_part("vowel")
            if (vowel_rhyme_part in vowel_dict) {
                vowel_dict[word.get_rhyme_part("vowel")]?.add(word.text)
            } else {
                vowel_dict[word.get_rhyme_part("vowel")] = mutableListOf(word.text)
            }


        }
    }
    val full_json = gson.toJson(full_dict)
    val vowel_json = gson.toJson(vowel_dict)
    File("src/main/assets/full_dictionary.json").writeText(full_json)
    File("src/main/assets/vowel_dictionary.json").writeText(vowel_json)
}


fun find_rhyme(inputWord: String, rhymeType: String): List<String> {
    val filepath: String = if (rhymeType == "full") {
        "src/main/assets/full_dictionary.json"
    } else {
        "src/main/assets/vowel_dictionary.json"
    }
    val jsonFileString = getJsonDataFromAsset(filepath)
    return find_rhyme_in_json_list(jsonFileString, inputWord, rhymeType)

}

fun find_rhyme(inputWord: String, rhymeType: String, context: Context): List<String> {
    //val rhyme_part = Word(inputWord).get_rhyme_part(rhymeType)
    val filepath: String = if (rhymeType == "full") {
        "full_dictionary.json"
    } else {
        "vowel_dictionary.json"
    }
    val jsonFileString = getJsonDataFromAsset(context, filepath)
    return find_rhyme_in_json_list(jsonFileString, inputWord, rhymeType)
}

fun find_rhyme_in_json_list(
    jsonFileString: String?,
    inputWord: String,
    rhymeType: String
): List<String> {
    val gson = Gson()
    val converter = object : TypeToken<Map<String, MutableList<String>>>() {}.type
    val words: Map<String, MutableList<String>> = gson.fromJson(jsonFileString, converter)

    val rhyme_words = words.getOrDefault(Word(inputWord).get_rhyme_part(rhymeType), mutableListOf())
    println(rhyme_words)
    return rhyme_words
}


fun containsInvalidChar(word: String): Boolean {
    val allowed_characters =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzÁÄáäÉËéëÍÏíïÚÜúüÓÖóö-"
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


