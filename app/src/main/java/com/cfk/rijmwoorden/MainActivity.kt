package com.cfk.rijmwoorden

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton

private const val TAG = "MyActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val syllable = Syllable("balk")

        Log.w(TAG, "${syllable.start_cons}, ${syllable.vowels}, ${syllable.end_cons}")
        val syllable2 = Syllable("káén")
        Log.w(TAG, "${syllable2.start_cons}, ${syllable2.vowels}, ${syllable2.end_cons}")
        syllable2.remove_accents()
        Log.w(TAG, "${syllable2.start_cons}, ${syllable2.vowels}, ${syllable2.end_cons}")
        Log.w(TAG, "Remove accent: ${LetterDictionaries().remove_accent("é")}")
        //Log.w(TAG, "Remove accent: ${remove_accent("k")}")

        val word = Word("chronische")
        Log.w(TAG, "Length of ${word.text} is: ${word.length}")
        Log.w(TAG, "Split word of ${word.text} is: ${word.get_split_word()}")

        val toggle: ToggleButton = findViewById(R.id.rhyme_type_toggle)
        toggle.setBackgroundColor(Color.parseColor("#B388FF"))
        var rhymeType = ""
        toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                rhymeType = "full"
                toggle.setBackgroundColor(Color.parseColor("#AB47BC"))
                // The toggle is enabled
            } else {
                rhymeType = "vowel"
                toggle.setBackgroundColor(Color.parseColor("#B388FF"))
                // The toggle is disabled
            }
        }

    }
    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.input_word)
        val message = editText.text.toString()
        val word = Word(message)
        var rhymeType = ""

       val rhymewords = message

        findViewById<TextView>(R.id.rhymeWords).apply {
            text = word.get_split_word()
        }

    }

}