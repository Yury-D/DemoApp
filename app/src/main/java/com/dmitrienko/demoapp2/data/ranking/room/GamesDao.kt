package com.dmitrienko.demoapp2.data.ranking.room

import androidx.room.*
import com.dmitrienko.demoapp2.data.ranking.room.entities.PairGameDbEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface GamesDao {
    @Query("SELECT * FROM games")
    fun getAll(): Observable<List<PairGameDbEntity?>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateGame(game: PairGameDbEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createGame(game: PairGameDbEntity): Completable
}
