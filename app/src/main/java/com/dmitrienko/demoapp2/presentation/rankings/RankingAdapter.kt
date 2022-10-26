package com.dmitrienko.demoapp2.presentation.rankings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmitrienko.demoapp2.databinding.LayoutRankRowBinding
import com.dmitrienko.demoapp2.domain.score.entities.UserRankEntity

class RankingAdapter : ListAdapter<UserRankEntity, UserRankViewHolder>(UserRankEntityDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRankViewHolder {
        return UserRankViewHolder(
            LayoutRankRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserRankViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


object UserRankEntityDiffCallback : DiffUtil.ItemCallback<UserRankEntity>() {
    override fun areItemsTheSame(oldItem: UserRankEntity, newItem: UserRankEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserRankEntity, newItem: UserRankEntity): Boolean {
        return oldItem == newItem
    }
}


class UserRankViewHolder(
    private val bindings: LayoutRankRowBinding
) : RecyclerView.ViewHolder(bindings.root) {

    fun bind(item: UserRankEntity) {
        bindings.apply {
            player1Name.text = item.playerName
            player1Score.text = item.playerScore.toString()
        }
    }
}

