package com.cfk.rijmwoorden

import android.app.Activity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
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
        toggle.background = resources.getDrawable(R.drawable.vowel_rhyme_type_button)
        toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                rhymeType = "full"
                toggle.background = resources.getDrawable(R.drawable.full_rhyme_type_button)
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

        findViewById<TextView>(R.id.rhymeWords).movementMethod = ScrollingMovementMethod()



    }

    fun sendMessage(view: View) {
        hideKeyboard(this)
        val editText = findViewById<EditText>(R.id.input_word)
        val input = editText.text.toString()
        val word = Word(input)
        val rhymewords = find_rhyme(input, rhymeType, applicationContext)
        val rhymepart = word.get_rhyme_part(rhymeType)

        var info_message = ""
        info_message += "Opgesplitst in lettergrepen: " + word.get_split_word()
        info_message += "\nFonetisch: " + word.phonetisation
        info_message += "\nRijmgedeelte: $rhymepart"
        var rhyme_words_message = ""

        for (word in rhymewords){
            rhyme_words_message += "$word,\t"
        }


        findViewById<TextView>(R.id.info).apply {
            text = info_message
        }
        findViewById<TextView>(R.id.rhymeWords).apply {
            text = rhyme_words_message
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