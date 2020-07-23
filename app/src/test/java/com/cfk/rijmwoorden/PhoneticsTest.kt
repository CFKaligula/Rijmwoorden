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

}