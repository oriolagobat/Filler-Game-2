package com.example.filler.persistence

import android.content.ClipData.newIntent
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.filler.FillerApplication
import com.example.filler.R
import com.example.filler.constants.gui.Summary
import com.example.filler.persistence.database.GameSummary
import com.example.filler.persistence.database.GameSummaryViewModel
import com.example.filler.persistence.database.GameSummaryViewModelFactory

class AccessBDActivity : AppCompatActivity() {
    private val gameSummaryViewModel: GameSummaryViewModel by viewModels {
        GameSummaryViewModelFactory((application as FillerApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbacces)
        if (savedInstanceState == null) addQueryFragment()
    }

    private fun addQueryFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<QueryFrag>(R.id.items_frag_container)
        }
    }

    fun onItemClick(summary: GameSummary) {
        val intent = Intent(this, DetailRegActivity::class.java)
        intent.putExtra(Summary.GAMESUMMARY.name, summary)
        startActivity(intent)
    }
}