package com.dmitrienko.demoapp2.presentation.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dmitrienko.demoapp2.domain.score.entities.PairGameEntity
import com.dmitrienko.demoapp2.domain.score.repos.GamesRepository
import com.dmitrienko.demoapp2.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val userScoreRepository: GamesRepository
) : BaseViewModel() {

    companion object {
        private val TAG = ScoreViewModel::class.java.simpleName
    }

    val swipeRefreshIsRefreshing: MutableLiveData<Boolean> = MutableLiveData()

    private val _resultsList = MutableLiveData<List<PairGameEntity>>()

    val scoreList: LiveData<List<PairGameEntity>> = Transformations.map(_resultsList) {
        it //map if needed
    }

    fun addGame(gameEntity: PairGameEntity) {
        disposable.add(userScoreRepository.addGame(gameEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.e(TAG, "addGame succeed")//TODO
                }, {
                    Log.e(TAG, "error in addGame", it)
                }
            ))
    }

    fun fetchGames() {
        disposable.add(userScoreRepository.getGamesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { swipeRefreshIsRefreshing.value = true }
            .doFinally { swipeRefreshIsRefreshing.value = false }
            .subscribe({
                _resultsList.value = it
            }, {
                Log.e(TAG, "error in fetchGames", it)
            })
        )
    }

    fun updateGame(game: PairGameEntity) {
        disposable.add(userScoreRepository.updateGame(game)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.e(TAG, "updateGame succeed")//TODO
                }, {
                    Log.e(TAG, "error in addGame", it)
                }
            ))
    }
}