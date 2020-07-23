package com.cfk.rijmwoorden

import android.annotation.SuppressLint

class EndPhonetics {
    @SuppressLint("NewApi")
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

    fun find_end_b_phonetics(): String {
        var end_con_sound = ""
        return end_con_sound

    }

    fun find_end_c_phonetics(): String {
        var end_con_sound = ""
        return end_con_sound

    }

    fun find_end_d_phonetics(): String {
        var end_con_sound = ""
        return end_con_sound

    }

    fun find_end_g_phonetics(): String {
        var end_con_sound = ""
        return end_con_sound

    }

    fun find_end_h_phonetics(): String {
        var end_con_sound = ""
        return end_con_sound

    }

    fun find_end_n_phonetics(): String {
        var end_con_sound = ""
        return end_con_sound

    }

    fun find_end_v_phonetics(): String {
        var end_con_sound = ""
        return end_con_sound

    }

    fun find_end_z_phonetics(): String {
        var end_con_sound = ""
        return end_con_sound

    }
}