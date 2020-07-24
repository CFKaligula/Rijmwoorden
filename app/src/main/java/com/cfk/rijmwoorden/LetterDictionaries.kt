package com.cfk.rijmwoorden

import java.lang.IllegalArgumentException

class LetterDictionaries {
    val consonants: Set<String> = setOf(
        "b", "c", "d", "f", "g", "h", "k", "l", "j",
        "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"
    )
    val vowels: Set<String> = setOf("a", "e", "i", "o", "u")
    val vowels_with_accents: Set<String> = setOf("á", "ä", "é", "ë", "í", "ï", "ó", "ö", "ú", "ü")
    val dipthongs: Set<String> = setOf(
        "au", "ou", "ei", "ij", "oe",
        "ui", "aa", "ee", "ie", "oo", "uu", "oi"
    )
    val tripthongs: Set<String> = setOf("oei", "eau", "eeu", "ooi", "aai", "oeu", "ieu")
    val break_symbol = "-"
    val valid_consonant_combinations: Set<String> = setOf(
        "",
        "bl",
        "br",
        "cl",
        "cr",
        "dr",
        "dl",
        "dr",
        "fl",
        "fj",
        "fr",
        "gr",
        "gl",
        "gr",
        // h
        "kl",
        "kn",
        "kr",
        // l
        // j
        // m
        // n
        "pl",
        "pr",
        // q
        // r
        "sk",
        "sl",
        "sj",
        "sm",
        "sn",
        "sp",
        "sr",
        "st",
        "sw",
        "sch",
        "schr",
        "schl",
        "str",
        "spr",
        "spl",
        "scr",  // sh
        "th",
        "tr",
        "vl",
        "vr",
        "wr",
        // x
        // y
        "zw"
    )

    val preposition_exceptions: Set<String> =
        setOf("beter", "geven", "ver", "beven", "bezem", "gele")

    val phonetic_system_consonants: Set<String> = setOf(
        "b", "c", "d", "f", "g",
        "h", "j", "k", "l", "m",
        "n", "p", "q", "r", "s",
        "t", "v", "w", "x", "y",
        "z",

        "®", "µ", "ß", "æ", "ð",
        "ñ", "þ"
    )

    val phonetic_system_vowels: Set<String> = setOf(
        "0", ":",
        "a", "i", "e", "o", "u",
        "á", "í", "é", "ó", "ú",
        "ä", "ï", "ë", "ö", "ü",
        "ê", "î",
        "A", "Á", "O", "Ö", "Ó"
    )

    fun remove_accent(letter: String): String = when (letter) {
        in setOf("á", "ä") -> "a"
        in setOf("é", "ë") -> "e"
        in setOf("í", "ï") -> "i"
        in setOf("ó", "ö") -> "o"
        in setOf("ú", "ü") -> "u"
        else -> throw IllegalArgumentException("Cannot remove accent if letter is not a vowel")
    }
}