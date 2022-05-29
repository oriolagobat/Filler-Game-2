package com.example.filler.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameSummary::class], version = 5, exportSchema = false)
abstract class GameSummaryRoomDatabase : RoomDatabase() {

    abstract fun gameSummaryDao(): GameSummaryDAO

    // Singleton prevents multiple instances of database
    companion object {
        @Volatile
        private var INSTANCE: GameSummaryRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): GameSummaryRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameSummaryRoomDatabase::class.java,
                    "summary_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
