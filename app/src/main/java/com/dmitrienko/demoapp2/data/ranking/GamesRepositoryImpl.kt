package com.dmitrienko.demoapp2.data.ranking

import com.dmitrienko.demoapp2.data.ranking.room.GamesDao
import com.dmitrienko.demoapp2.data.ranking.room.entities.PairGameDbEntity
import com.dmitrienko.demoapp2.domain.score.entities.PairGameEntity
import com.dmitrienko.demoapp2.domain.score.entities.UserRankEntity
import com.dmitrienko.demoapp2.domain.score.entities.mapToDomain
import com.dmitrienko.demoapp2.domain.score.repos.GamesRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesRepositoryImpl @Inject constructor(
    private val gamesDao: GamesDao
) : GamesRepository {

    init {
        getMockGames().forEach {
            addGame(it).subscribe()
        }
    }

    override fun getGamesList(): Single<List<PairGameEntity>> {
        return gamesDao.getAll()
            .flatMapIterable { it }
            .map(PairGameDbEntity::mapToDomain)
            .toList()
    }

    override fun addGame(game: PairGameEntity): Completable {
        return gamesDao.createGame(game.toPairGameDbEntity())
    }

    override fun updateGame(game: PairGameEntity): Completable {
        return gamesDao.updateGame(game.toPairGameDbEntity())
    }
}

fun getMockGames(): List<PairGameEntity> {
    return listOf(
        PairGameEntity(
            UUID.randomUUID().toString(),
            UserRankEntity(UUID.randomUUID().toString(), "Smith", 4),
            UserRankEntity(UUID.randomUUID().toString(), "John", 6)
        ),
        PairGameEntity(
            UUID.randomUUID().toString(),
            UserRankEntity(UUID.randomUUID().toString(), "Dan", 1),
            UserRankEntity(UUID.randomUUID().toString(), "Man", 5)
        ),
        PairGameEntity(
            UUID.randomUUID().toString(),
            UserRankEntity(UUID.randomUUID().toString(), "Khann", 14),
            UserRankEntity(UUID.randomUUID().toString(), "Gann", 7)
        ),
    )
}