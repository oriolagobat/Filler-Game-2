package com.example.filler.gui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.example.filler.R
import com.example.filler.databinding.ActivityHelpBinding

class Help : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set this activity's binding
        val binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the textView text
        // As it has html to format it, it has to be done by code
        binding.rulesText.text =
            HtmlCompat.fromHtml(getString(R.string.help_rules), HtmlCompat.FROM_HTML_MODE_COMPACT)

        // Start a listener on the button
        // When clicked, finish the activity
        binding.backHomeButton.setOnClickListener {
            finish()
        }
    }
}
