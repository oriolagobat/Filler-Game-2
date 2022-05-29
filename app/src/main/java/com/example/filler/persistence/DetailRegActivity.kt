package com.example.filler.persistence

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.filler.R

class DetailRegActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_reg)
        if (savedInstanceState == null) addDetailFragment()
    }

    private fun addDetailFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<RegFrag>(R.id.details_frag_container_single)
        }
    }
}