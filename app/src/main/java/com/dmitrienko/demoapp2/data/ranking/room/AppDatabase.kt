package com.dmitrienko.demoapp2.data.ranking.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dmitrienko.demoapp2.data.ranking.room.entities.PairGameDbEntity
import com.dmitrienko.demoapp2.data.ranking.room.entities.UserRankDbEntity

const val DATABASE_NAME : String = "GamesDb"

@Database(
    version = 1,
    entities = [
        PairGameDbEntity::class,
        UserRankDbEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao

}