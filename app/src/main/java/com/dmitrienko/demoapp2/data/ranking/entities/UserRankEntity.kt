package com.dmitrienko.demoapp2.data.ranking.entities

typealias PlayerScore = Int

data class PairGameEntity(
    val id: String,
    val player1: UserRankEntity,
    val player2: UserRankEntity
)

data class UserRankEntity(
    val id: String,
    val playerData: PlayerDataEntity,
    val playerScore: PlayerScore
)

data class PlayerDataEntity(
    val name: String
)
