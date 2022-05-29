package com.example.filler.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(GameSummary::class), version = 5, exportSchema = false)
abstract class GameSummaryRoomDatabase : RoomDatabase() {

    abstract fun gameSummaryDao(): GameSummaryDAO

    private class GameSummaryDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var summaryDao = database.gameSummaryDao()
//                    summaryDao.deleteAll()
                }
            }
        }
    }

    // Singleton prevents multiple instances of database
    companion object {
        @Volatile
        private var INSTANCE: GameSummaryRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): GameSummaryRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameSummaryRoomDatabase::class.java,
                    "summary_database"
                )
                    .addCallback(GameSummaryDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
