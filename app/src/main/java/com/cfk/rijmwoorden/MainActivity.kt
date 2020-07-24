package com.cfk.rijmwoorden

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity


var rhymeType = "vowel"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle: ToggleButton = findViewById(R.id.rhyme_type_toggle)
        toggle.background = getResources().getDrawable(R.drawable.vowel_rhyme_type_button)
        toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                rhymeType = "full"
                toggle.background = getResources().getDrawable(R.drawable.full_rhyme_type_button)
                // The toggle is enabled
            } else {
                rhymeType = "vowel"
                toggle.background = resources.getDrawable(R.drawable.vowel_rhyme_type_button)
                // The toggle is disabled
            }
        }
        //check if enter key has been pressed
        val editText = findViewById<EditText>(R.id.input_word)
        editText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                sendMessage(v)

            }
            false
        }


    }

    fun sendMessage(view: View) {
        hideKeyboard(this)
        val editText = findViewById<EditText>(R.id.input_word)
        val input = editText.text.toString()
        val word = Word(input)
        val rhymepart: String
        rhymepart = if (rhymeType == "full") {
            word.get_rhyme_part()
        } else {
            word.get_phonetic_vowels()
        }

        var message = ""
        message += "Opgesplitst in lettergrepen: " + word.get_split_word()
        message += "\nFonetisch: " + word.phonetisation
        message += "\nRijmgedeelte: $rhymepart"


        //val rhymewords = message

        findViewById<TextView>(R.id.rhymeWords).apply {
            text = message
        }

    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}