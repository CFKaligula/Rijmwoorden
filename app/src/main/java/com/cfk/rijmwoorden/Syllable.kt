package com.cfk.rijmwoorden

import android.util.Log
import java.util.*

private const val TAG = "MyActivity"

open class ASyllable {
    var start_cons = ""
    var vowels = ""
    var end_cons = ""
}

class EmptySyllable : ASyllable()

class Syllable(
    inputText: String = "",
    val prev_syl: ASyllable = EmptySyllable(),
    var next_syl: ASyllable = EmptySyllable(),
    val word: Word? = null
) : ASyllable() {

    val text: String
        get() = start_cons + vowels + end_cons

    init {
        find_cons_and_vowels(inputText.toLowerCase(Locale.getDefault()))
    }

    private fun find_cons_and_vowels(text: String) {
        var found_vowel = false
        for (letter in text) {
            val letter = letter.toString()
            if (letter in LetterDictionaries().consonants) {
                if (!found_vowel) {
                    start_cons += letter
                } else {
                    end_cons += letter
                }
            } else { //letter is a vowel
                vowels += letter
                found_vowel = true
            }
        }
    }

    fun fix_start_cons() {
        if ((prev_syl !is EmptySyllable) and (start_cons + vowels != "tje"))
        //if we have a previous syllable and our syllable does not contain the diminutive 'tje' (as in autootje)
        {
            while (!((start_cons in LetterDictionaries().valid_consonant_combinations) or
                        (start_cons in LetterDictionaries().consonants))
            ) {
                //while your start vowels are neither a valid consonant combination nor a single consonant
                //append the first start con to the previous syllable's end cons
                prev_syl.end_cons += start_cons[0] //!! means non-null asserted call
                start_cons = start_cons.subSequence(1, start_cons.length).toString()
            }
        }
    }

    fun fix_end_cons(index: Int): Int {
        var index = index
        if ((end_cons.length == 1) and (end_cons != "x")) {
            end_cons = ""
            index -= 1
        } else when (end_cons) {
            "tj" -> {
                end_cons = ""
                index -= 2
            }
            "sch" -> {
                end_cons = ""
                index -= 3
            }
            !in setOf("", "ch", "kw", "th", "ng") -> {
                index -= end_cons.length - 1
                end_cons = end_cons[0].toString()
            }
        }
        return index
    }

    fun add_cons(cons: String) {
        if (vowels != "") {
            if ((vowels + cons == "ij") and (end_cons.isEmpty())) {
                vowels += cons
            } else {
                end_cons += cons
            }
        } else {
            start_cons += cons
        }
    }

    fun add_vowel(vowel: String, next_letter: String): Boolean {
        var break_bool = false
        when {
            vowels == "" -> {
                vowels += vowel
            }
            start_cons + vowels == "qu" -> {
                vowels += vowel
            }
            (vowels + vowel) in LetterDictionaries().tripthongs -> {
                //find a tripthong
                vowels += vowel
            }
            (vowels + vowel + next_letter) in LetterDictionaries().tripthongs -> {
                //foresee a tripthong
                vowels += vowel
            }
            (vowels + vowel) in LetterDictionaries().dipthongs -> {
                vowels += vowel
                //break_bool = true //we know we cant make a tripthong, so we can break
            }
            else -> {
                break_bool = true
            }
        }
        return break_bool
    }

    fun add_y() {
        when {
            vowels != "" -> {
                add_cons("y")
            }
            start_cons != "" -> {
                add_vowel("y", "")
            }
            else -> { //THIS SEEMS WEIRD
                add_vowel("y", "")
            }
        }
    }

    fun remove_accents() {
        val unaccented_vowel_list = vowels.map { LetterDictionaries().remove_accent(it.toString()) }
        vowels = unaccented_vowel_list.joinToString(separator = "")
    }


}