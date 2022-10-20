package com.dmitrienko.demoapp2.domain.score.interactors

import com.dmitrienko.demoapp2.domain.score.entities.PairGameEntity
import com.dmitrienko.demoapp2.domain.score.entities.UserRankEntity
import com.dmitrienko.demoapp2.domain.score.repos.GamesRepository
import io.reactivex.Observable


class GetRankingsInteractor(
    private val gamesRepository: GamesRepository
) {

    fun getRankings(): Observable<List<UserRankEntity>> {
        return gamesRepository.getGamesList()
            .map { getRankings(it) }
    }

    private fun getRankings(games: List<PairGameEntity>): List<UserRankEntity> {
        val usersList = games.flatMap { listOf(it.player1, it.player2) }
        return usersList
            .associate { userRankEntity ->
                userRankEntity.playerName to usersList.filter {
                    userRankEntity.playerName == it.playerName
                }
            }
            .map { entry ->
                UserRankEntity(
                    "",
                    entry.key,
                    entry.value.sumOf { it.playerScore }
                )
            }
            .sortedByDescending { it.playerScore }

    }
}