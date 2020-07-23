package com.cfk.rijmwoorden

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi

class StartPhonetics {

    @SuppressLint("NewApi")
    fun default_start_consonant_replacement(syllable: Syllable, i: Int): String {
        val switcher = mapOf(
            "y" to "j",
            "x" to "ks"
        )
        return switcher.getOrDefault(syllable.start_cons[i].toString(), syllable.start_cons[i].toString())
    }


    fun find_start_c_phonetics(): String {
        var start_con_sound = ""
        return start_con_sound

    }

    fun find_start_h_phonetics(): String {
        var start_con_sound = ""
        return start_con_sound

    }

    fun find_start_j_phonetics(): String {
        var start_con_sound = ""
        return start_con_sound

    }

    fun find_start_s_phonetics(): String {
        var start_con_sound = ""
        return start_con_sound

    }

    fun find_start_t_phonetics(): String {
        var start_con_sound = ""
        return start_con_sound

    }

    fun find_start_q_phonetics(): String {
        var start_con_sound = ""
        return start_con_sound

    }
}