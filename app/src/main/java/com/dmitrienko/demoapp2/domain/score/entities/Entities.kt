package com.dmitrienko.demoapp2.domain.score.entities

import com.dmitrienko.demoapp2.data.ranking.room.entities.PairGameDbEntity
import com.dmitrienko.demoapp2.data.ranking.room.entities.UserRankDbEntity

typealias PlayerScore = Int

data class PairGameEntity(
    val id: String,
    val player1: UserRankEntity,
    val player2: UserRankEntity
) {
    fun toPairGameDbEntity(): PairGameDbEntity = PairGameDbEntity(
        id = id,
        player1 = player1.toUserRankDbEntity(),
        player2 = player2.toUserRankDbEntity(),
    )
}

data class UserRankEntity(
    val id: String,
    val playerName: String,
    val playerScore: PlayerScore
) {
    fun toUserRankDbEntity() = UserRankDbEntity(
        id = id,
        playerName = playerName,
        playerScore = playerScore
    )
}

