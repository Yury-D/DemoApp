package com.dmitrienko.demoapp2.domain.score.repos

import com.dmitrienko.demoapp2.data.ranking.entities.PairGameEntity
import io.reactivex.Completable
import io.reactivex.Single

interface UserScoreRepository {
    fun getUserScoreList(): Single<List<PairGameEntity>>
    fun addUserScore(game: PairGameEntity): Completable
}