package com.dmitrienko.demoapp2.presentation.score

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmitrienko.demoapp2.data.ranking.entities.PairGameEntity
import com.dmitrienko.demoapp2.databinding.LayoutScoreRowBinding

class ScoreAdapter : ListAdapter<PairGameEntity, UserRankViewHolder>(PairGameEntityDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRankViewHolder {
        return UserRankViewHolder(
            LayoutScoreRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserRankViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


object PairGameEntityDiffCallback : DiffUtil.ItemCallback<PairGameEntity>() {
    override fun areItemsTheSame(oldItem: PairGameEntity, newItem: PairGameEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PairGameEntity, newItem: PairGameEntity): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: PairGameEntity, newItem: PairGameEntity): Any? {
        return null
        //TODO add payload for score
    }
}


class UserRankViewHolder(private val bindings: LayoutScoreRowBinding) :
    RecyclerView.ViewHolder(bindings.root) {

    fun bind(item: PairGameEntity) {
        bindings.apply {
            player1Name.text = item.player1.playerData.name
            player2Name.text = item.player2.playerData.name
            player1Score.text = item.player1.playerScore.toString()
            player2Score.text = item.player2.playerScore.toString()
        }
    }

}
