package com.dmitrienko.demoapp2.presentation.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmitrienko.demoapp2.domain.score.entities.PairGameEntity
import com.dmitrienko.demoapp2.domain.score.repos.GamesRepository
import com.dmitrienko.demoapp2.presentation.base.BaseViewModel
import com.dmitrienko.demoapp2.utils.Completables
import com.dmitrienko.demoapp2.utils.Observables
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val gamesRepository: GamesRepository
) : BaseViewModel() {

    companion object {
        private val TAG = ScoreViewModel::class.java.simpleName
    }

    val swipeRefreshIsRefreshing: MutableLiveData<Boolean> = MutableLiveData()

    private val _gamesList = MutableLiveData<List<PairGameEntity>>()
    val gamesList: LiveData<List<PairGameEntity>> = _gamesList

    fun addGame(gameEntity: PairGameEntity) {
        disposable.add(gamesRepository.addGame(gameEntity)
            .compose(Completables.setIoAndMainSchedulers())
            .subscribe(
                {
                    Log.e(TAG, "addGame succeed")//TODO
                }, {
                    Log.e(TAG, "error in addGame", it)
                }
            ))
    }

    fun initMockData() {
        disposable.add(gamesRepository.initMockData().subscribe())
    }

    fun fetchGames() {
        disposable.add(gamesRepository.getGamesList()
            .compose(Observables.setIoAndMainSchedulers())
            .doOnSubscribe { swipeRefreshIsRefreshing.value = true }
            .subscribe({
                swipeRefreshIsRefreshing.value = false
                _gamesList.value = it
            }, {
                Log.e(TAG, "error in fetchGames", it)
            })
        )
    }

    fun updateGame(game: PairGameEntity) {
        disposable.add(gamesRepository.updateGame(game)
            .compose(Completables.setIoAndMainSchedulers())
            .subscribe(
                {
                    Log.e(TAG, "updateGame succeed")//TODO
                }, {
                    Log.e(TAG, "error in addGame", it)
                }
            ))
    }
}
