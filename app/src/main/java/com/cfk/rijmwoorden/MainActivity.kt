package com.cfk.rijmwoorden

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton

private const val TAG = "MyActivity"

var rhymeType = "vowel"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle: ToggleButton = findViewById(R.id.rhyme_type_toggle)
        toggle.setBackgroundColor(Color.parseColor("#B388FF"))
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
        //check if enter key has been pressed
        val editText = findViewById<EditText>(R.id.input_word)
        editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                sendMessage(v)

            }
            false
        })


    }

    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.input_word)
        val input = editText.text.toString()
        val word = Word(input)
        val rhymepart: String
        if (rhymeType == "full") {
            rhymepart = word.get_rhyme_part()
        } else {
            rhymepart = word.get_phonetic_vowels()
        }

        var message = ""
        message += "Opgesplitst in lettergrepen: " + word.get_split_word()
        message += "\nFonetisch: " + word.phonetisation
        message += "\nRijmgedeelte: " + rhymepart


        //val rhymewords = message

        findViewById<TextView>(R.id.rhymeWords).apply {
            text = message
        }

    }

}