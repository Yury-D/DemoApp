package com.dmitrienko.demoapp2.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dmitrienko.demoapp2.R
import com.dmitrienko.demoapp2.data.ranking.entities.PairGameEntity
import com.dmitrienko.demoapp2.data.ranking.entities.PlayerDataEntity
import com.dmitrienko.demoapp2.data.ranking.entities.UserRankEntity
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
                showAddScoreDialog(layoutInflater, onAddedAction = {
                    scoreViewModel.addUsedScore(it)
                })
            }
        }
    }

    private fun showAddScoreDialog(layoutInflater: LayoutInflater, onAddedAction: (PairGameEntity) -> Unit) {
        AlertDialog.Builder(requireContext()).apply {
            val bindings = LayoutEditScoreBinding.inflate(layoutInflater)
            bindings.saveButton.setOnClickListener {
                onAddedAction(collectScoresAndNames(bindings))
            }
            setView(bindings.root)
            create().show()
        }
    }

    private fun collectScoresAndNames(bindings: LayoutEditScoreBinding): PairGameEntity {
        bindings.apply {
            val player1name = player1name.text.toString()
            val player1score = player1Score.text.toString().toInt()
            val player2name = player2name.text.toString()
            val player2score = player2Score.text.toString().toInt()
            return PairGameEntity(
                UUID.randomUUID().toString(),
                UserRankEntity(UUID.randomUUID().toString(), PlayerDataEntity(player1name), player1score),
                UserRankEntity(UUID.randomUUID().toString(), PlayerDataEntity(player2name), player2score)
            )
        }
    }

}