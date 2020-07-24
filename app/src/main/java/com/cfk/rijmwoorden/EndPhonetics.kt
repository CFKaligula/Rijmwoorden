package com.cfk.rijmwoorden

import android.annotation.SuppressLint

class EndPhonetics {

    fun default_end_consonant_replacement(ASyllable: ASyllable, i: Int): String {
        val switcher = mapOf(
            "y" to "j",
            "x" to "ks"
        )
        return switcher.getOrDefault(
            ASyllable.end_cons[i].toString(),
            ASyllable.end_cons[i].toString()
        )
    }

    fun find_end_b_phonetics(syllable: Syllable, i: Int): String {
        var end_con_sound = ""
        end_con_sound = "p"
        return end_con_sound

    }

    fun find_end_c_phonetics(syllable: Syllable, i: Int): String {
        var end_con_sound = ""
        if (i > 0 && syllable.end_cons[i-1] == 's'){
            //legendarisch
        } else if (i < syllable.end_cons.length-1 && syllable.end_cons[i+1] == 'h'){
            //ch, should not appear at end, but in case
            end_con_sound = "g"
        } else {
            end_con_sound = "k"
        }
        return end_con_sound

    }

    fun find_end_d_phonetics(syllable: Syllable, i: Int): String {
        var end_con_sound = ""
        end_con_sound = "t"
        return end_con_sound

    }

    fun find_end_g_phonetics(syllable: Syllable, i: Int): String {
        var end_con_sound = ""
        if (i> 0 && syllable.end_cons[i-1] == 'n'){
            //ng is already processed at n
        } else {
            //voiceless g sound
            end_con_sound = "æ"
        }
        return end_con_sound

    }

    fun find_end_h_phonetics(syllable: Syllable, i: Int): String {
        var end_con_sound = ""
        if (i > 0 && syllable.end_cons[i-1] == 'c'){
            //ch, already processed at c
        } else {
            end_con_sound = "h"
        }
        return end_con_sound

    }

    fun find_end_n_phonetics(syllable: Syllable, i: Int): String {
        var end_con_sound = ""
        end_con_sound = if (i+1 <= syllable.end_cons.length-1 && syllable.end_cons[i+1] == 'g') {
            "µ"
        } else if (syllable.next_syl !is EmptySyllable && syllable.next_syl.start_cons != "") {
            val next_syl_first_start_con = Syllable(inputText = syllable.next_syl.text).start_cons[0]
            if (next_syl_first_start_con == 'j'){
                "ñ"
            } else {
                "n"
            }
        } else {
            "n"
        }
        return end_con_sound

    }

    fun find_end_v_phonetics(syllable: Syllable, i: Int): String {
        var end_con_sound = ""
        end_con_sound = "f"
        return end_con_sound

    }

    fun find_end_z_phonetics(syllable: Syllable, i: Int): String {
        var end_con_sound = ""
        end_con_sound = "s"
        return end_con_sound

    }
}