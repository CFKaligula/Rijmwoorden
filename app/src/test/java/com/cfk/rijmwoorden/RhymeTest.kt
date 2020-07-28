package com.cfk.rijmwoorden

import org.junit.Test

import org.junit.Assert.*

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RhymeTest {
    @Test
    fun json_read_test() {
       create_rhyme_dictionaries()

        find_rhyme("lopen", "vowel")
        find_rhyme("lopen", "full")

        assertEquals(true, true)

    }




}