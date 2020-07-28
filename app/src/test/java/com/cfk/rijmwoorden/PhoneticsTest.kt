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
    fun default_start_con_test() {
        assertEquals("blpks", Phonetics().find_start_con_phonetics(Syllable("blpxaank")))
    }

    @Test
    fun default_end_con_test() {
        assertEquals("splks", Phonetics().find_end_con_phonetics(Syllable("kasplx")))
    }

    @Test
    fun default_vowel_test() {
        assertEquals("í", Phonetics().find_vowel_phonetics(Syllable("biet")))
        assertEquals("á:", Phonetics().find_vowel_phonetics(Syllable("gaal")))
        assertEquals("i:", Phonetics().find_vowel_phonetics(Syllable("scheer")))
        assertEquals("í", Phonetics().find_vowel_phonetics(Syllable("tie")))
        assertEquals("e", Phonetics().find_vowel_phonetics(Syllable("pen")))
        assertEquals("á", Phonetics().find_vowel_phonetics(Syllable("ga")))
        assertEquals("ó", Phonetics().find_vowel_phonetics(Syllable("yo")))
    }


    @Test
    fun phonetics_dict() {
        val test_dict = mapOf(
            "appel" to "apel",

            "baas" to "bás",
            "bezem" to "bézem",
            "bezet" to "b0zet",
            "bijl" to "be:l",
            "blokken" to "blok0n",

            "ceder" to "séd0r",
            "casus" to "kásus",
            "chronische" to "grónís0",
            "chronisch" to "grónís",
            "citroen" to "sítrön",

            "denken" to "denk0n",

            "ga" to "gá",
            "ga" to "gá",
            "gas" to "gas",
            "gade" to "gád0",
            "gaas" to "gás",
            "gaal" to "gá:l",
            "gaai" to "gÁ",
            "gag" to "gaæ",

            "herkennen" to "herken0n",

            "lijk" to "lïk",
            "lang" to "laµ",

            "motie" to "mótsí",
            "moties" to "mótsís",
            "motie" to "mótsí",
            "moties" to "mótsís",

            "oranje" to "o:rañ0",

            "pen" to "pen",
            "praatje" to "práð0",
            "perfectie" to "perfeksí",
            "psychose" to "psígós0",

            "quinty" to "kwintí",
            "quasi" to "kwásí",

            "scepter" to "sept0r",
            "sexy" to "seksí",
            "schaar" to "sgá:r",
            "scheren" to "sgi:r0n",
            "sjaal" to "ßá:l",

            "taxi" to "taksí",
            "tieten" to "tít0n",

            "wordt" to "wort",
            "wondtas" to "wontas",

            "yoga" to "jógá"

        )
        for (test in test_dict) {
            test_phonetics(test)
        }

    }

    private fun test_phonetics(dict_entry: Map.Entry<String, String>) {
        assertEquals(dict_entry.value, Word(dict_entry.key).phonetisation)
    }

}