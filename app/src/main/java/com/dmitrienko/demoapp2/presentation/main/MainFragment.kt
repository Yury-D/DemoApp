package com.dmitrienko.demoapp2.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dmitrienko.demoapp2.R
import com.dmitrienko.demoapp2.domain.score.entities.PairGameEntity
import com.dmitrienko.demoapp2.domain.score.entities.UserRankEntity
import com.dmitrienko.demoapp2.databinding.FragmentMainBinding
import com.dmitrienko.demoapp2.databinding.LayoutEditScoreBinding
import com.dmitrienko.demoapp2.presentation.score.ScoreViewModel
import com.dmitrienko.demoapp2.utils.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val scoreViewModel by viewModels<ScoreViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewPager.adapter = MainPagerAdapter(childFragmentManager, lifecycle)
            TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
                tab.text = "OBJECT ${(position + 1)}"//TODO move to the VM
            }.attach()

            binding.fab.setOnClickListener { view ->
                showAddScoreDialog(requireContext(), layoutInflater, onAddedAction = {
                    scoreViewModel.addGame(it)
                })
            }
        }
    }

}