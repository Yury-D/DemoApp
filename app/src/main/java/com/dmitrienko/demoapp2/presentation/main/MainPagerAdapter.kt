package com.dmitrienko.demoapp2.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dmitrienko.demoapp2.presentation.score.ScoreFragment

class MainPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return ScoreFragment.newInstance()
    }

    override fun getItemCount(): Int {
        return 2
    }
}