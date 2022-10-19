package com.dmitrienko.demoapp2.presentation.score

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitrienko.demoapp2.R
import com.dmitrienko.demoapp2.databinding.FragmentScoreBinding
import com.dmitrienko.demoapp2.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoreFragment : Fragment(R.layout.fragment_score) {

    private val scoreViewModel by viewModels<ScoreViewModel>()
    private val binding by viewBinding(FragmentScoreBinding::bind)
    private val scoreAdapter by lazy { ScoreAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scoreViewModel.fetchUserScores()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        scoreViewModel.swipeRefreshIsRefreshing.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }

        scoreViewModel.scoreList.observe(viewLifecycleOwner) {
            scoreAdapter.submitList(it)
        }
    }

    private fun initRecycler() {
        binding.scoreTableRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = scoreAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ScoreFragment()
    }

}