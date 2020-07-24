package com.cfk.rijmwoorden

import android.annotation.SuppressLint

class Phonetics {


    @SuppressLint("NewApi")
    fun find_start_con_phonetics(syllable: Syllable): String {
        var start_con_sound = ""
        if (syllable.start_cons == "tj") {
            // e.g. autoo-tje
            start_con_sound = "ð"
        } else {
            for (i in 0 until syllable.start_cons.length) {
                //e.g. blokken -> bloken
                if ((i == 0) and (syllable.start_cons[i].toString() ==
                            syllable.prev_syl.end_cons.takeLast(1).toString())
                ) continue

                val sound: String = when (syllable.start_cons[i].toString()) {
                    "c" -> StartPhonetics().find_start_c_phonetics(syllable, i)
                    "h" -> StartPhonetics().find_start_h_phonetics(syllable, i)
                    "j" -> StartPhonetics().find_start_j_phonetics(syllable, i)
                    "s" -> StartPhonetics().find_start_s_phonetics(syllable, i)
                    "t" -> StartPhonetics().find_start_t_phonetics(syllable, i)
                    "q" -> StartPhonetics().find_start_q_phonetics(syllable, i)
                    else -> StartPhonetics().default_start_consonant_replacement(syllable, i)
                }


                if (start_con_sound.takeLast(1).toString() != sound) {
                    val previous_syllable = Syllable(syllable.prev_syl.text)
                    val prev_syl_last_end_con_phonetics = find_end_con_phonetics(previous_syllable)
                    if (prev_syl_last_end_con_phonetics.length == 0 || prev_syl_last_end_con_phonetics.takeLast(1) != sound) {
                        start_con_sound += sound
                    }

                }

            }
        }
        return start_con_sound

    }

    fun find_end_con_phonetics(syllable: Syllable): String {
        var end_con_sound = ""
        for (i in 0 until syllable.end_cons.length) {
            val sound = when (syllable.end_cons[i].toString()) {
                "b" -> EndPhonetics().find_end_b_phonetics(syllable, i)
                "c" -> EndPhonetics().find_end_c_phonetics(syllable, i)
                "d" -> EndPhonetics().find_end_d_phonetics(syllable, i)
                "g" -> EndPhonetics().find_end_g_phonetics(syllable, i)
                "h" -> EndPhonetics().find_end_h_phonetics(syllable, i)
                "n" -> EndPhonetics().find_end_n_phonetics(syllable, i)
                "v" -> EndPhonetics().find_end_v_phonetics(syllable, i)
                "z" -> EndPhonetics().find_end_z_phonetics(syllable, i)
                else -> EndPhonetics().default_end_consonant_replacement(syllable, i)
            }
            if (end_con_sound.takeLast(1) != sound) {
                end_con_sound += sound
            }

        }
        return end_con_sound
    }

    fun find_vowel_phonetics(syllable: Syllable): String {
        var vowel_sound = ""

        if ((syllable.start_cons == "q") and (syllable.vowels != "") and (syllable.vowels[0] == 'u')) {
            //qua -> kwa
            val new_syllable_text = syllable.vowels.subSequence(1, syllable.vowels.length)
                .toString() + syllable.end_cons
            val syl_without_qu = Syllable(
                inputText = new_syllable_text,
                prev_syl = syllable.prev_syl,
                next_syl = syllable.next_syl
            )
            vowel_sound = find_vowel_phonetics(syl_without_qu) //CHANGED
        } else if ((syllable.start_cons == "c") and (syllable.vowels == "i")) {
            //citroen -> cítroen
            vowel_sound = add_accent(syllable.vowels)
        } else if (syllable.vowels in LetterDictionaries().vowels) { //the vowel is not a dipthong or tripthong

            if (syllable.end_cons == "") {

                if ((syllable.next_syl !is EmptySyllable) && (syllable.next_syl.start_cons != "") && (syllable.next_syl.start_cons[0].toString() == "r")) {
                    vowel_sound = next_syl_r(syllable.vowels)
                } else {
                    vowel_sound = find_open_vowel_phonetics(syllable)
                }

            } else if (((syllable.vowels + syllable.end_cons) in setOf(
                    "en",
                    "er"
                )) && (syllable.next_syl is EmptySyllable) && (syllable.prev_syl !is EmptySyllable)
            ) {
                vowel_sound = "0" //CHANGED
            } else if ((syllable.end_cons == "sch") && (syllable.vowels == "i")) {
                vowel_sound = add_accent(syllable.vowels)
            } else {
                vowel_sound = syllable.vowels
            }
        } else if ((syllable.end_cons.toString() != "") && (syllable.end_cons[0].toString() in setOf<String>(
                "r",
                "l"
            ))
        ) {
            vowel_sound = r_or_l_phonetic_symbol(syllable.vowels)

        } else { //the vowels are a dipthong/tripthong
            vowel_sound = default_phonetic_symbol(syllable.vowels)
        }

        return vowel_sound
    }


    fun find_open_vowel_phonetics(syllable: Syllable): String {
        var vowel_sound = ""
        if (syllable.text in setOf(
                "ge",
                "be"
            ) && syllable.word.text !in LetterDictionaries().preposition_exceptions
        ) {
            vowel_sound = "0"
        } else if (syllable.next_syl !is EmptySyllable) {
            vowel_sound = add_accent(syllable.vowels)
        } else {
            vowel_sound = ending_vowel(syllable.vowels)
        }
        return vowel_sound

    }


    @SuppressLint("NewApi")
    fun add_accent(vowel: String): String {
        val switcher = mapOf<String, String>(
            "a" to "á",  // la = laa
            "e" to "é",  // beter
            "i" to "í",  // never happens, only in simon i think
            "o" to "ó",  // boven
            "u" to "ú"
        )
        return switcher.getOrDefault(vowel, vowel)
    }

    @SuppressLint("NewApi")
    fun ending_vowel(vowel: String): String {
        val switcher = mapOf<String, String>(
            "a" to "á", //ga
            "i" to "í", //bi
            "e" to "0", // blij-e
            "o" to "ó", //zo
            "u" to "ú",
            "y" to "í"  // baby
        )
        return switcher.getOrDefault(vowel, vowel)
    }

    @SuppressLint("NewApi")
    fun default_phonetic_symbol(dipthong: String): String {
        val switcher = mapOf<String, String>(
            "aa" to "á",
            "ee" to "é",
            "ie" to "í",
            "oo" to "ó",
            "uu" to "ú",
            "au" to "ä",
            "ou" to "ä",
            "ij" to "ï",
            "ei" to "ï",
            "eu" to "ë",
            "oe" to "ö",
            "ui" to "ü",
            "ai" to "A",
            "oi" to "O",
            "aai" to "Á",
            "eau" to "ó",
            "ooi" to "Ó",
            "oei" to "Ö",
            "oeu" to "uu",
            "y" to "í"   // sexy
        )
        return switcher.getOrDefault(dipthong, "DIPTHONGERROR")
    }


    @SuppressLint("NewApi")
    fun r_or_l_phonetic_symbol(vowel: String): String {
        val switcher = mapOf<String, String>(
            "aa" to "á0",
            "ee" to "ii",
            "ie" to "í0",
            "oo" to "o0",
            "uu" to "ú0",
            "ij" to "ee",
            "ei" to "ee",
            "oe" to "ö0",
            "ui" to "ü0"
        )
        return switcher.getOrDefault(vowel, vowel)
    }


    @SuppressLint("NewApi")
    fun next_syl_r(vowel: String): String {
        val switcher = mapOf<String, String>(
            "o" to "oo",  // blij-e
            "e" to "ii"  // baby
        )
        return switcher.getOrDefault(vowel, vowel)
    }


}
