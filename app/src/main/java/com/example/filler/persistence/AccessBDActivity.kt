package com.example.filler.persistence

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.filler.R

class AccessBDActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbacces)
        if (savedInstanceState == null) addQueryFragment()
    }

    private fun addQueryFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<QueryFrag>(R.id.query_frag_container)
        }
    }
}