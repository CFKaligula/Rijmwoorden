package com.cfk.rijmwoorden

import org.junit.Assert.assertEquals
import org.junit.Test

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
        find_rhyme("eeuw", "vowel")
        find_rhyme("eeuw", "full")

        assertEquals(true, true)

    }




}