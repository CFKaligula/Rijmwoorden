package com.cfk.rijmwoorden

import android.annotation.SuppressLint

class StartPhonetics {


    fun default_start_consonant_replacement(syllable: Syllable, i: Int): String {
        val switcher = mapOf(
            "y" to "j",
            "x" to "ks"
        )
        return switcher.getOrDefault(
            syllable.start_cons[i].toString(),
            syllable.start_cons[i].toString()
        )
    }


    fun find_start_c_phonetics(syllable: Syllable, i: Int): String {
        //removed the 'sce' stuff in 3d if statement also changed length check to != "" check
        var start_con_sound = ""
        if (i > 0 && syllable.start_cons[i - 1].toString() == "s") {
            // sc,sch already processed at s
        } else if (i < syllable.start_cons.length - 1 && syllable.start_cons[i + 1].toString() == "h") {
            //for "ch".
            start_con_sound = "g"
        } else if (i < syllable.start_cons.length - 1 || (syllable.vowels != "" && syllable.vowels[0] in setOf(
                'a',
                'o',
                'u'
            ))
        ) {
            start_con_sound = "k"
        } else {
            start_con_sound = "s"
        }
        return start_con_sound

    }

    fun find_start_h_phonetics(syllable: Syllable, i: Int): String {
        var start_con_sound = ""
        if (i > 0 && syllable.start_cons[i - 1].toString() == "c") {
            // ch, already processed at c
        } else {
            start_con_sound = "h"
        }
        return start_con_sound

    }

    fun find_start_j_phonetics(syllable: Syllable, i: Int): String {
        var start_con_sound = ""
        if (syllable.prev_syl.end_cons == "n") {
            // for "nj", this case is handled at the n already
        } else if (i > 0 && syllable.start_cons[i - 1] == 's') {
            //for sjaal,  already processed at s
        } else {
            start_con_sound = "j"
        }
        return start_con_sound

    }

    fun find_start_s_phonetics(syllable: Syllable, i: Int): String {
        //CHANGED FIRST IF
        var start_con_sound = ""
        start_con_sound = if (i < syllable.start_cons.length - 2 && syllable.start_cons[i + 1] == 'c' && syllable.start_cons[i + 2] == 'h') {
            if ((syllable.vowels + syllable.end_cons) == "e" && syllable.next_syl is EmptySyllable) {
                //e.g. logi-sche -> -se
                "s"
            } else {
                "sg"
            }

        } else if (i < syllable.start_cons.length -1 && syllable.start_cons[i+1] == 'j'){
            "ß"
        } else {
            "s"
        }
        return start_con_sound

    }

    fun find_start_t_phonetics(syllable: Syllable, i: Int): String {
        var start_con_sound = ""
        start_con_sound = if (syllable.start_cons + syllable.vowels == "tie" && syllable.next_syl is EmptySyllable) {
            if (syllable.prev_syl_last_end_con != "" && syllable.prev_syl_last_end_con == "c"){
                // e.g. perfec-tie -> sí
                "s"
            } else {
                //mo-tie -> tsí
                "ts"
            }
        } else {
            "t"
        }
        return start_con_sound

    }

    fun find_start_q_phonetics(syllable: Syllable, i: Int): String {
        var start_con_sound = ""
        start_con_sound = if (syllable.vowels != "" && syllable.vowels[0] == 'u'){
            //qua = kwa
            "kw"
        } else {
            // e.g. qat
            "k"
        }
        return start_con_sound

    }
}