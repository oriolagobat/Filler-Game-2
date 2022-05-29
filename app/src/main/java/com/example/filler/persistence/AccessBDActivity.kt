package com.example.filler.persistence

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.filler.R
import com.example.filler.constants.gui.Summary
import com.example.filler.persistence.database.GameSummary


class AccessBDActivity : AppCompatActivity() {
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
        if (detailViewIsPresent()) setDetailFragment()
        else startDetailActivity(summary)
    }

    private fun detailViewIsPresent(): Boolean {
        return findViewById<FragmentContainerView>(R.id.details_frag_container) != null
    }

    private fun setDetailFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<RegFrag>(R.id.details_frag_container)
        }
    }

    private fun startDetailActivity(summary: GameSummary) {
        val intent = Intent(this, DetailRegActivity::class.java)
        intent.putExtra(Summary.GAMESUMMARY.name, summary)
        startActivity(intent)
    }
}