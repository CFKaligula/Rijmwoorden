package com.cfk.rijmwoorden

import android.annotation.SuppressLint

class Phonetics {

    val start_cons_function_map = mapOf(
        "c" to StartPhonetics()::find_start_c_phonetics,
        "j" to StartPhonetics()::find_start_j_phonetics


    )

    @SuppressLint("NewApi")
    fun find_start_con_phonetics(syllable: Syllable): String {
        var start_con_sound = ""
        if (syllable.start_cons == "tj") {
            // e.g. autoo-tje
            start_con_sound = "ð"
        } else {
            for (i in 0 until syllable.start_cons.length) {

                if ((i == 0) and (syllable.start_cons[i].toString() ==
                            syllable.prev_syl.end_cons.takeLast(1).toString())
                ) {
                    //e.g. blokken -> bloken
                    continue
                }
                val sound = when (syllable.start_cons[i].toString()) {
                    "c" -> StartPhonetics().find_start_c_phonetics()
                    "h" -> StartPhonetics().find_start_h_phonetics()
                    "j" -> StartPhonetics().find_start_j_phonetics()
                    "s" -> StartPhonetics().find_start_s_phonetics()
                    "t" -> StartPhonetics().find_start_t_phonetics()
                    "q" -> StartPhonetics().find_start_q_phonetics()
                    else -> StartPhonetics().default_start_consonant_replacement(syllable, i)
                }
                println(sound)
                if (start_con_sound.takeLast(1).toString() != sound) {
                    if ((syllable.prev_syl !is EmptySyllable) or
                        (find_end_con_phonetics(syllable.prev_syl).takeLast(1) != sound)){
                        start_con_sound += sound
                    }



                }


            }
        }
        return start_con_sound
    }

    fun find_vowel_phonetics(syllable: ASyllable): String {
        return "wiwo"
    }

    fun find_end_con_phonetics(syllable: ASyllable): String {
        var end_con_sound = ""
        if (syllable != null) {
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
}