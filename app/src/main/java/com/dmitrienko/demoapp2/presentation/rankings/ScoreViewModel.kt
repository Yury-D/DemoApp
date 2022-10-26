package com.dmitrienko.demoapp2.presentation.rankings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmitrienko.demoapp2.domain.score.entities.UserRankEntity
import com.dmitrienko.demoapp2.domain.score.interactors.GetRankingsInteractor
import com.dmitrienko.demoapp2.presentation.base.BaseViewModel
import com.dmitrienko.demoapp2.utils.Observables
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val rankingsInteractor: GetRankingsInteractor
) : BaseViewModel() {

    companion object {
        private val TAG = RankingViewModel::class.java.simpleName
    }

    val swipeRefreshIsRefreshing: MutableLiveData<Boolean> = MutableLiveData()

    private val _rankingsList = MutableLiveData<List<UserRankEntity>>()
    val rankingsList: LiveData<List<UserRankEntity>> = _rankingsList

    fun fetchRankings() {
        disposable.add(rankingsInteractor.getRankings()
            .compose(Observables.setIoAndMainSchedulers())
            .doOnSubscribe { swipeRefreshIsRefreshing.value = true }
            .subscribe({
                swipeRefreshIsRefreshing.value = false
                _rankingsList.value = it
            }, {
                Log.e(TAG, "error in fetchRankings", it)
            })
        )
    }
}
