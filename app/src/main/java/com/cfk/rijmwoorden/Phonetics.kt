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
                    "c" -> StartPhonetics().find_start_c_phonetics()
                    "h" -> StartPhonetics().find_start_h_phonetics()
                    "j" -> StartPhonetics().find_start_j_phonetics()
                    "s" -> StartPhonetics().find_start_s_phonetics()
                    "t" -> StartPhonetics().find_start_t_phonetics()
                    "q" -> StartPhonetics().find_start_q_phonetics()
                    else -> StartPhonetics().default_start_consonant_replacement(syllable, i)
                }

                if (start_con_sound.takeLast(1).toString() != sound) {
                    if ((syllable.prev_syl !is EmptySyllable) or
                        (find_end_con_phonetics(syllable.prev_syl).takeLast(1) != sound)
                    )
                        start_con_sound += sound
                }
            }
        }
        return start_con_sound
    }

    fun find_vowel_phonetics(syllable: Syllable): String {
        println("??${syllable.end_cons}??")
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
        }

        else if ((syllable.start_cons == "c") and (syllable.vowels == "i")) {
            //citroen -> cítroen
            vowel_sound = add_accent(syllable.vowels)
        }

        else if (syllable.vowels in LetterDictionaries().vowels) { //the vowel is not a dipthong or tripthong

            if (syllable.end_cons == ""){

                if ((syllable.next_syl !is EmptySyllable) && (syllable.next_syl.start_cons != "") && (syllable.next_syl.start_cons[0].toString() == "r")){
                    vowel_sound = next_syl_r(syllable.vowels)
                }
                else {
                    vowel_sound = find_open_vowel_phonetics(syllable)
                }

            }

            else if (((syllable.vowels + syllable.end_cons )in setOf("en", "er")) && (syllable.next_syl !is EmptySyllable)) {
                vowel_sound = "0" //CHANGED
            }

            else if ((syllable.end_cons == "sch") && (syllable.vowels == "i")) {
                vowel_sound = add_accent(syllable.vowels)
            }
            else {
                vowel_sound = syllable.vowels
            }
        }

        else if ((syllable.end_cons.toString() != "") && (syllable.end_cons[0].toString() in setOf<String>("r", "l"))){
                vowel_sound = r_or_l_phonetic_symbol(syllable.vowels)

        }

        else { //the vowels are a dipthong/tripthong
            vowel_sound = default_phonetic_symbol(syllable.vowels)
        }

        return vowel_sound
    }

    fun find_end_con_phonetics(syllable: ASyllable): String {
        var end_con_sound = ""
        if (syllable !is EmptySyllable) {
            for (i in 0 until syllable.end_cons.length) {
                val sound = when (syllable.end_cons[i].toString()) {
                    "b" -> EndPhonetics().find_end_b_phonetics()
                    "c" -> EndPhonetics().find_end_c_phonetics()
                    "d" -> EndPhonetics().find_end_d_phonetics()
                    "g" -> EndPhonetics().find_end_g_phonetics()
                    "h" -> EndPhonetics().find_end_h_phonetics()
                    "n" -> EndPhonetics().find_end_n_phonetics()
                    "v" -> EndPhonetics().find_end_v_phonetics()
                    "z" -> EndPhonetics().find_end_z_phonetics()
                    else -> EndPhonetics().default_end_consonant_replacement(syllable, i)
                }
                if (end_con_sound.takeLast(1) != sound) {
                    end_con_sound += sound
                }

            }
        }
        return end_con_sound
    }

    fun find_open_vowel_phonetics(syllable: Syllable): String {
        var vowel_sound = ""
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
            "e" to "0",  // blij-e
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
