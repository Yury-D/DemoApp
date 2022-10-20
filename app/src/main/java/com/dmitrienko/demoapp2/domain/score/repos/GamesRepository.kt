package com.dmitrienko.demoapp2.domain.score.repos

import com.dmitrienko.demoapp2.domain.score.entities.PairGameEntity
import io.reactivex.Completable
import io.reactivex.Single

interface GamesRepository {
    fun getGamesList(): Single<List<PairGameEntity>>
    fun addGame(game: PairGameEntity): Completable
    fun updateGame(game: PairGameEntity): Completable
}