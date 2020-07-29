package com.cfk.rijmwoorden

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class WordSplitTest {
    @Test
    fun split_word() {
        val test_dict = mapOf(
            "laur" to "laur",
            "dromen" to  "dro-men",
            "leerling" to  "leer-ling",
            "ambtenaar" to  "amb-te-naar",
            "koeien" to  "koei-en",
            "piano" to  "pi-a-no",
            "niveau" to  "ni-veau",
            "radio" to  "ra-di-o",
            "blije" to "blij-e",
            "taxi" to "tax-i",
            "lachen" to "lach-en",
            "autootje" to "au-too-tje",
            "herfstjuk" to "herfst-juk",
            "beïnvloeden" to "be-in-vloe-den",
            "blok-étagere" to "blok-e-ta-ge-re",
            "blaséeend" to  "bla-se-eend",
            "baby" to "ba-by",
            //"babyopera" to "ba-by-o-pe-ra",
            "sexy" to "sex-y",
            "babby" to "bab-by",
            "yoghurt" to "yog-hurt",
            "quasi" to "qua-si",
            "chronische" to "chro-ni-sche",
            "lange" to "lang-e",
            "hoofdstad" to "hoofd-stad",
            "emily" to "e-mi-ly",
            "emilytje" to "e-mi-ly-tje",
            "psychose" to "psych-o-se",
            "yoga" to "yo-ga"

        )
        for (test in test_dict){
            test_split_word(test)
        }

    }

    private fun test_split_word(dict_entry: Map.Entry<String, String>){
        assertEquals(dict_entry.value, Word(dict_entry.key).get_split_word())
    }

    @Test
    fun test_y(){
        assertEquals("yog", Word("yog").get_split_word())
    }













}