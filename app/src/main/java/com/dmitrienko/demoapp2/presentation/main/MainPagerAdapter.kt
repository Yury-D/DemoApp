package com.dmitrienko.demoapp2.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dmitrienko.demoapp2.presentation.rankings.RankingFragment
import com.dmitrienko.demoapp2.presentation.score.ScoreFragment

class MainPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            ScoreMode.TIME_TABLE.menuPosition -> ScoreFragment.newInstance()
            ScoreMode.RANKINGS.menuPosition -> RankingFragment.newInstance()
            else -> throw IllegalArgumentException("only 2 tabs expected")
        }
    }

    override fun getItemCount() = ScoreMode.values().size
}

enum class ScoreMode(val menuPosition: Int) {
    TIME_TABLE(0),
    RANKINGS(1)
}