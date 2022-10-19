package com.dmitrienko.demoapp2.data.ranking

import com.dmitrienko.demoapp2.data.ranking.entities.PairGameEntity
import com.dmitrienko.demoapp2.data.ranking.entities.PlayerDataEntity
import com.dmitrienko.demoapp2.data.ranking.entities.UserRankEntity
import com.dmitrienko.demoapp2.domain.score.repos.UserScoreRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserScoreRepositoryImpl @Inject constructor() : UserScoreRepository {

    private val inMemoryCache = mutableListOf<PairGameEntity>()

    init {
        inMemoryCache.addAll(getMockGames())
    }

    override fun getUserScoreList(): Single<List<PairGameEntity>> {
        return Single.fromCallable { inMemoryCache.toList() }
    }

    override fun addUserScore(game: PairGameEntity): Completable {
        return Completable.fromCallable { inMemoryCache.add(game) }
    }
}

fun getMockGames(): List<PairGameEntity> {
    return listOf(
        PairGameEntity(
            UUID.randomUUID().toString(),
            UserRankEntity(UUID.randomUUID().toString(), PlayerDataEntity("Smith"), 4),
            UserRankEntity(UUID.randomUUID().toString(), PlayerDataEntity("John"), 6)
        ),
        PairGameEntity(
            UUID.randomUUID().toString(),
            UserRankEntity(UUID.randomUUID().toString(), PlayerDataEntity("Dan"), 1),
            UserRankEntity(UUID.randomUUID().toString(), PlayerDataEntity("Man"), 5)
        ),
        PairGameEntity(
            UUID.randomUUID().toString(),
            UserRankEntity(UUID.randomUUID().toString(), PlayerDataEntity("Khann"), 14),
            UserRankEntity(UUID.randomUUID().toString(), PlayerDataEntity("Gann"), 7)
        ),
    )
}