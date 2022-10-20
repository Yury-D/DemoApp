package com.dmitrienko.demoapp2.presentation.score

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.dmitrienko.demoapp2.databinding.LayoutEditScoreBinding
import com.dmitrienko.demoapp2.domain.score.entities.PairGameEntity
import com.dmitrienko.demoapp2.domain.score.entities.UserRankEntity
import java.util.*


fun showAddScoreDialog(
    context: Context,
    layoutInflater: LayoutInflater,
    gameEntity: PairGameEntity? = null,
    onAddedAction: (PairGameEntity) -> Unit
) {
    AlertDialog.Builder(context).apply {
        var alertDialog: AlertDialog? = null
        val bindings = LayoutEditScoreBinding.inflate(layoutInflater)
        gameEntity?.let { prefill(it, bindings) }
        bindings.saveButton.setOnClickListener {
            alertDialog?.dismiss()
            onAddedAction(collectScoresAndNames(bindings, gameEntity))
        }
        setView(bindings.root)
        alertDialog = create()
        alertDialog.show()
    }
}

fun prefill(gameEntity: PairGameEntity, bindings: LayoutEditScoreBinding) {
    bindings.apply {
        player1name.setText(gameEntity.player1.playerName)
        player1Score.setText(gameEntity.player1.playerScore.toString())
        player2name.setText(gameEntity.player2.playerName)
        player2Score.setText(gameEntity.player2.playerScore.toString())
    }
}

private fun collectScoresAndNames(
    bindings: LayoutEditScoreBinding,
    gameEntity: PairGameEntity?
): PairGameEntity {
    bindings.apply {
        val player1name = player1name.text.toString()
        val player1score = player1Score.text.toString().toInt()
        val player2name = player2name.text.toString()
        val player2score = player2Score.text.toString().toInt()
        return PairGameEntity(
            gameEntity?.id ?: UUID.randomUUID().toString(),
            UserRankEntity(
                gameEntity?.player1?.id ?: UUID.randomUUID().toString(),
                player1name,
                player1score
            ),
            UserRankEntity(
                gameEntity?.player2?.id ?: UUID.randomUUID().toString(),
                player2name,
                player2score
            )
        )
    }
}