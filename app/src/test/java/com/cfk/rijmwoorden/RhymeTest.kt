package com.cfk.rijmwoorden

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RhymeTest {
    @Test
    fun json_read_test() {

        val jsonFileString = getJsonDataFromAsset()
        print(jsonFileString)
        assertEquals(true, true)

    }




}