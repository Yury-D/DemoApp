package com.dmitrienko.demoapp2.presentation.score

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmitrienko.demoapp2.databinding.LayoutScoreRowBinding
import com.dmitrienko.demoapp2.domain.score.entities.PairGameEntity

class ScoreAdapter(
    private val onItemClicked: (PairGameEntity) -> Unit
) : ListAdapter<PairGameEntity, GameViewHolder>(PairGameEntityDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            LayoutScoreRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) {
            onItemClicked(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
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


class GameViewHolder(
    private val bindings: LayoutScoreRowBinding,
    onItemClicked: (Int) -> Unit
) :
    RecyclerView.ViewHolder(bindings.root) {

    init {
        itemView.setOnClickListener { onItemClicked(adapterPosition) }
    }

    fun bind(item: PairGameEntity) {
        bindings.apply {
            player1Name.text = item.player1.playerName
            player2Name.text = item.player2.playerName
            player1Score.text = item.player1.playerScore.toString()
            player2Score.text = item.player2.playerScore.toString()
        }
    }

}
