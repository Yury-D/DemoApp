package com.dmitrienko.demoapp2.data.ranking.room.entities

import androidx.room.*

@Entity(
    tableName = "games",
    indices = [
        Index("id", unique = true)
    ]
)
data class PairGameDbEntity(
    @ColumnInfo(name = "id") @PrimaryKey val id: String,
    @Embedded(prefix = "player1_") val player1: UserRankDbEntity,
    @Embedded(prefix = "player2_") val player2: UserRankDbEntity,
)
