package com.cfk.rijmwoorden

class Word(inputText: String) {
    val text: String = inputText
    private val length: Int = text.length
    private val syllables: MutableList<Syllable> = initialize_syllables(0, mutableListOf())
    val phonetisation: String = initialize_phonetisation()


    fun get_split_word(): String {
        var result = ""
        for (syllable: Syllable in syllables) {
            result += syllable.text + LetterDictionaries().break_symbol
        }
        if (result.length > 1) result = result.subSequence(0, result.length - 1).toString()
        return result
    }

    private fun initialize_syllables(
        start: Int,
        syllableList: MutableList<Syllable>
    ): MutableList<Syllable> {
        //Base Case, if we went over every letter in the word
        if (start >= length) {
            for (i in 0 until syllableList.size - 1) {
                syllableList[i].next_syl = syllableList[i + 1]
            }
            return syllableList
        }
        //Create the new syllable
        val syl: Syllable = if (syllableList.size == 0) {
            Syllable(word = this)
        } else {
            Syllable(prev_syl = syllableList.last(), word = this)
        }
        //Loop over every letter starting from index
        var index = start
        while (index < length + 1) {

            if (index >= length) {
                break
            }
            val letter = text[index].toString()
            val next_letter = if (index < length - 1) text[index + 1].toString() else ""

            if (letter == LetterDictionaries().break_symbol) {
                index++
                break
            }
            //Letter is a consonant
            if (letter in LetterDictionaries().consonants) {
                syl.add_cons(letter)
                index++
            }
            //Letter is a vowel
            else if (letter in LetterDictionaries().vowels) {
                if (letter == "y" && next_letter in LetterDictionaries().vowels) {

                    syl.add_cons(letter)
                    index++
                } else if (syl.end_cons.isNotEmpty()) {
                    index = syl.fix_end_cons(index)
                    break
                } else {

                    val break_bool = syl.add_vowel(letter, next_letter)
                    if (break_bool) break
                    index++
                }
            }//Letter is a vowel with an accent
            else if (letter in LetterDictionaries().vowels_with_accents) {
                if (syl.vowels.isEmpty()) {
                    val break_bool = syl.add_vowel(letter, next_letter)
                    if (break_bool) break
                    index++
                } else {
                    index = syl.fix_end_cons(index)
                    break
                }
            }
        }

        if (syl.vowels in LetterDictionaries().vowels_with_accents) {
            syl.remove_accents()
        }
        syl.fix_start_cons()
        syllableList.add(syl)
        return initialize_syllables(index, syllableList)
    }

    private fun initialize_phonetisation(): String {
        var result = ""

        for (syllable in syllables) {
            result += Phonetics().find_start_con_phonetics(syllable)
            result += Phonetics().find_vowel_phonetics(syllable)
            result += Phonetics().find_end_con_phonetics(syllable)
        }
        return result

    }

    private fun get_phonetic_vowels(): String {
        var vowels = ""
        for (letter in phonetisation) {
            val letter = letter.toString()
            if (letter in LetterDictionaries().phonetic_system_vowels) {
                vowels += letter
            }
        }
        return vowels
    }

    fun get_rhyme_part(rhymeType: String): String {
        var rhyme_part = ""
        rhyme_part = if (rhymeType == "full") {
            var start_length = 0
            for (letter in phonetisation) {
                val letter = letter.toString()
                if ((letter in LetterDictionaries().phonetic_system_consonants) or (letter == "0")) {
                    start_length++
                } else {
                    break
                }
            }
            phonetisation.subSequence(start_length, phonetisation.length).toString()
        } else {
            get_phonetic_vowels()
        }
        return rhyme_part
    }
}