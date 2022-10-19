package com.dmitrienko.demoapp2.presentation.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dmitrienko.demoapp2.data.ranking.entities.PairGameEntity
import com.dmitrienko.demoapp2.domain.score.repos.UserScoreRepository
import com.dmitrienko.demoapp2.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val userScoreRepository: UserScoreRepository
) : BaseViewModel() {

    companion object {
        private val TAG = ScoreViewModel::class.java.simpleName
    }

    val swipeRefreshIsRefreshing: MutableLiveData<Boolean> = MutableLiveData()

    private val _resultsList = MutableLiveData<List<PairGameEntity>>()

    val scoreList: LiveData<List<PairGameEntity>> = Transformations.map(_resultsList) {
        it //map if needed
    }

    fun addUsedScore(scoreData: PairGameEntity) {
        disposable.add(userScoreRepository.addUserScore(scoreData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.e(TAG, "addUsedScore succeed")//TODO
                }, {
                    Log.e(TAG, "error in addUsedScore", it)
                }
            ))
    }

    fun fetchUserScores() {
        disposable.add(userScoreRepository.getUserScoreList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { swipeRefreshIsRefreshing.value = true }
            .doFinally { swipeRefreshIsRefreshing.value = false }
            .subscribe({
                _resultsList.value = it
            }, {
                Log.e(TAG, "error in fetchUserScores", it)
            })
        )
    }
}