package com.dmitrienko.demoapp2.domain.score.repos

import com.dmitrienko.demoapp2.domain.score.entities.PairGameEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface GamesRepository {
    fun getGamesList(): Observable<List<PairGameEntity>>
    fun addGame(game: PairGameEntity): Completable
    fun updateGame(game: PairGameEntity): Completable
}