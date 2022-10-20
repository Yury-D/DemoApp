package com.dmitrienko.demoapp2.domain.score.entities

import com.dmitrienko.demoapp2.data.ranking.room.entities.PairGameDbEntity

fun PairGameDbEntity.mapToDomain() = PairGameEntity(
    id = id,
    UserRankEntity(
        id = player1.id,
        playerName = player1.playerName,
        playerScore = player1.playerScore
    ),
    UserRankEntity(
        id = player1.id,
        playerName = player2.playerName,
        playerScore = player1.playerScore
    )
)