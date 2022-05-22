package com.example.filler.gui.results

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.filler.databinding.ActivityResultsBinding
import com.example.filler.gui.shared.hideNavBar

class Results : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideNavBar(this)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this, "Results", Toast.LENGTH_SHORT).show()
    }
}