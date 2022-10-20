package com.dmitrienko.demoapp2.presentation.main

import androidx.lifecycle.ViewModel
import com.dmitrienko.demoapp2.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun getTabName(position: Int): Int {
        return when (position) {
            ScoreMode.TIME_TABLE.menuPosition -> R.string.label_tab_time_table
            ScoreMode.RANKINGS.menuPosition -> R.string.label_tab_rankings
            else -> throw IllegalArgumentException("Only 2 tabs expected")
        }
    }
}