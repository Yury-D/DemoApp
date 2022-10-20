package com.dmitrienko.demoapp2.presentation.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dmitrienko.demoapp2.domain.score.entities.PairGameEntity
import com.dmitrienko.demoapp2.domain.score.repos.GamesRepository
import com.dmitrienko.demoapp2.presentation.base.BaseViewModel
import com.dmitrienko.demoapp2.utils.Completables
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val gamesRepository: GamesRepository
) : BaseViewModel() {

    companion object {
        private val TAG = ScoreViewModel::class.java.simpleName
    }

    val swipeRefreshIsRefreshing: MutableLiveData<Boolean> = MutableLiveData()

    private val _resultsList = MutableLiveData<List<PairGameEntity>>()

    val gamesList: LiveData<List<PairGameEntity>> = Transformations.map(_resultsList) {
        it //map if needed
    }

    fun addGame(gameEntity: PairGameEntity) {
        disposable.add(gamesRepository.addGame(gameEntity)
            .compose(Completables.setSchedulers())
            .subscribe(
                {
                    Log.e(TAG, "addGame succeed")//TODO
                }, {
                    Log.e(TAG, "error in addGame", it)
                }
            ))
    }

    fun fetchGames() {
        disposable.add(gamesRepository.getGamesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { swipeRefreshIsRefreshing.value = true }
            .subscribe({
                swipeRefreshIsRefreshing.value = false
                _resultsList.value = it
            }, {
                Log.e(TAG, "error in fetchGames", it)
            })
        )
    }

    fun updateGame(game: PairGameEntity) {
        disposable.add(gamesRepository.updateGame(game)
            .compose(Completables.setSchedulers())
            .subscribe(
                {
                    Log.e(TAG, "updateGame succeed")//TODO
                }, {
                    Log.e(TAG, "error in addGame", it)
                }
            ))
    }
}