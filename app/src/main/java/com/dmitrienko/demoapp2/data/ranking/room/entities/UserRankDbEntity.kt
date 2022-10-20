package com.dmitrienko.demoapp2.data.ranking.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [
        Index("id", unique = true)
    ]
)
data class UserRankDbEntity(
    @ColumnInfo(name = "id") @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val playerName: String,
    @ColumnInfo(name = "score") val playerScore: Int
)