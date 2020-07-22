package com.cfk.rijmwoorden

import android.util.Log
private const val TAG = "MyActivity"
class Word(inputText: String) {
    val text = inputText
    val length = text.length
    val syllables: MutableList<Syllable> = initialize_syllables(0, mutableListOf<Syllable>())

    init{
        initialize_phonetisation()
    }

    fun get_split_word(): String {
        var result = ""
        for (syllable in syllables){
            result += syllable.text + LetterDictionaries().break_symbol
        }
        result = result.subSequence(0, result.length-1).toString()
        return result
    }

    fun initialize_syllables(start: Int, syllableList: MutableList<Syllable>): MutableList<Syllable> {
        //Base Case, if we went over every letter in the word
        if (start >= length){
            for (i in 0 until syllableList.size-1){
                syllableList[i].next_syl = syllableList[i+1]
            }
            return syllableList
        }
        //Create the new syllable
        val syl: Syllable
        if (syllableList.size == 0){
            syl = Syllable(word = this)
        } else{
            syl = Syllable(prev_syl = syllableList.last(), word = this)
        }
        //Loop over every letter starting from index
        var index = start
        while (index < length + 1)  {
            if (index >= length) {
                break
            }
            var next_let = if (index < length-1) text[index+1].toString()  else ""

            if (text[index].toString() == LetterDictionaries().break_symbol){
                index ++
                break
            }


            //Letter is a consonant
            if(text[index].toString() in LetterDictionaries().consonants){
                if ((text[index].toString() == "y") and (index == length-1)) {
                    if (syl.end_cons.length > 0){
                        index = syl.fix_end_cons(index)
                        break
                    } else{
                        syl.add_y()
                        index++
                    }
                } else {
                    syl.add_cons(text[index].toString())
                    index++
                }

            }
            //Letter is a vowel
            else if(text[index].toString() in LetterDictionaries().vowels){
                if (syl.end_cons.length > 0){
                    index = syl.fix_end_cons(index)
                    break
                } else {
                    var break_bool = syl.add_vowel(text[index].toString(), next_let)
                    if (break_bool) break
                    index++
                }


            }//Letter is a vowel with an accent
            else if(text[index].toString() in LetterDictionaries().vowels_with_accents){
                if(syl.vowels.length == 0){
                    var break_bool = syl.add_vowel(text[index].toString(), next_let)
                    if (break_bool) break
                    index++
                } else {
                    index = syl.fix_end_cons(index)
                    break
                }
            }
        }

        if (syl.vowels in LetterDictionaries().vowels_with_accents){
            syl.remove_accents()
        }
        syl.fix_start_cons()
        syllableList.add(syl)
        return initialize_syllables(index, syllableList)
    }

    fun initialize_phonetisation(){

    }
}