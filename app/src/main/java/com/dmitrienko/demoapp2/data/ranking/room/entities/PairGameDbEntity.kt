package com.dmitrienko.demoapp2.data.ranking.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "games",
    indices = [
        Index("id", unique = true)
    ]
)
data class PairGameDbEntity(
    @ColumnInfo(name = "id") @PrimaryKey val id: String,
    @ColumnInfo(name = "player1") val player1: UserRankDbEntity,
    @ColumnInfo(name = "player2") val player2: UserRankDbEntity,
)
