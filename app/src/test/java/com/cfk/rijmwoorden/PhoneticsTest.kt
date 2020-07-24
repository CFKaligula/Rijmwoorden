package com.cfk.rijmwoorden

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PhoneticsTest {
    @Test
    fun default_start_con_test(){
        assertEquals("blpks", Phonetics().find_start_con_phonetics(Syllable("blpxaank")))
    }

    @Test
    fun default_end_con_test(){
        assertEquals("splks", Phonetics().find_end_con_phonetics(Syllable("kasplx")))
    }

    @Test
    fun default_vowel_test(){
        //assertEquals("í", Phonetics().find_vowel_phonetics(Syllable("biet")))
        //assertEquals("á0", Phonetics().find_vowel_phonetics(Syllable("gaal")))
        //assertEquals("ii", Phonetics().find_vowel_phonetics(Syllable("scheer")))
        assertEquals("í", Phonetics().find_vowel_phonetics(Syllable("tie")))
        //assertEquals("0", Phonetics().find_vowel_phonetics(Syllable("pen")))
    }


    //@Test
    fun phonetics_dict() {
        val test_dict = mapOf(
            "ga " to  "gá ",
            "gas " to  "gas ",
            "gade " to  "gád0 ",
            "sexy " to  "seksí ",
            "gaas " to  "gás ",
            "gaal " to  "gá0l ",
            "baas " to  "bás ",
            "lijk " to  "lïk ",
            "bijl " to  "beel ",
            "lang " to  "laµ ",
            "chronische " to  "grónís0 ",
            "chronisch " to  "grónís ",
            "scepter " to  "sept0r ",
            "ceder " to  "séd0r ",
            "casus " to  "kásus ",
            "herkennen " to  "herken0n ",
            "denken " to  "denk0n ",
            "bezem " to  "bézem ",
            "bezet " to  "b0zet ",
            "gag " to  "gaæ ",
            "taxi " to  "taksí ",
            "yoga " to  "jógá ",
            "schaar " to  "sgá0r ",
            "scheren " to  "sgiir0n ",
            "praatje " to  "práð0 ",
            "quinty " to  "kwintí ",
            "quasi " to  "kwásí ",
            "citroen " to  "sítrön ",
            "appel " to  "apel ",
            "blokken " to  "blok0n ",
            "oranje " to  "oorañ0 ",
            "sjaal " to  "ßá0l ",
            "motie " to  "mótsí ",
            "moties " to  "mótsís ",
            "perfectie " to  "perfeksí ",
            "tieten " to  "tít0n ",
            "wordt " to  "wort ",
            "wondtas " to  "wontas ",
            "motie " to  "mótsí ",
            "moties " to  "mótsís "

        )
        for (test in test_dict){
            test_phonetics(test)
        }

    }

    private fun test_phonetics(dict_entry: Map.Entry<String, String>){
        assertEquals(dict_entry.value, Word(dict_entry.key).phonetisation)
    }

}