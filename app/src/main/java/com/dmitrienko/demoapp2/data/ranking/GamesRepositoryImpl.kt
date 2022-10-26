package com.dmitrienko.demoapp2.data.ranking

import com.dmitrienko.demoapp2.data.ranking.room.GamesDao
import com.dmitrienko.demoapp2.data.ranking.room.entities.PairGameDbEntity
import com.dmitrienko.demoapp2.domain.score.entities.PairGameEntity
import com.dmitrienko.demoapp2.domain.score.entities.UserRankEntity
import com.dmitrienko.demoapp2.domain.score.entities.mapToDomain
import com.dmitrienko.demoapp2.domain.score.repos.GamesRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesRepositoryImpl @Inject constructor(
    private val gamesDao: GamesDao
) : GamesRepository {

    override fun initMockData(): Completable {
        return Observable.fromCallable { getMockGames() }
            .observeOn(Schedulers.io())
            .flatMapIterable { it }
            .flatMapCompletable { addGame(it) }

    }

    override fun getGamesList(): Observable<List<PairGameEntity>> {
        return gamesDao.getAll()
            .map {
                it.map(PairGameDbEntity::mapToDomain)
            }
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
