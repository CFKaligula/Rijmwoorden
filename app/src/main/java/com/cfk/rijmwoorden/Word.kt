package com.cfk.rijmwoorden

class Word(inputText: String) {
    val text = inputText
    val length = text.length
    val syllables = initialize_syllables(0, emptyList())

    init{
        initialize_phonetisation()
    }


    fun initialize_syllables(start: Int, syllableList: List<Syllable>){

    }

    fun initialize_phonetisation(){

    }
}