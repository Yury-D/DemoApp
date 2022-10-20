package com.dmitrienko.demoapp2.data.ranking.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserRankDbEntity(
    @ColumnInfo(name = "id") @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val playerName: String,
    @ColumnInfo(name = "score") val playerScore: Int
)