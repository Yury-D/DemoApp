package com.dmitrienko.demoapp2.presentation.rankings

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
class RankingFragment : Fragment(R.layout.fragment_score) {

    private val rankingViewModel by viewModels<RankingViewModel>()
    private val binding by viewBinding(FragmentScoreBinding::bind)
    private val rankingAdapter by lazy { RankingAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rankingViewModel.fetchRankings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        rankingViewModel.swipeRefreshIsRefreshing.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }
        binding.swipeRefresh.setOnRefreshListener { rankingViewModel.fetchRankings() }

        rankingViewModel.rankingsList.observe(viewLifecycleOwner) {
            rankingAdapter.submitList(it)
        }
    }

    private fun initRecycler() {
        binding.scoreTableRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rankingAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RankingFragment()
    }

}