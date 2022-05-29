package com.example.filler

import android.app.Application
import com.example.filler.persistence.database.GameSummaryRepository
import com.example.filler.persistence.database.GameSummaryRoomDatabase
import com.google.android.material.color.DynamicColors

class FillerApplication : Application() {
    val database by lazy { GameSummaryRoomDatabase.getDatabase(this) }
    val repository by lazy { GameSummaryRepository(database.gameSummaryDao()) }
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
