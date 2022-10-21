package com.dmitrienko.demoapp2.presentation.score

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.dmitrienko.demoapp2.R
import com.dmitrienko.demoapp2.databinding.LayoutEditScoreBinding
import com.dmitrienko.demoapp2.domain.score.entities.PairGameEntity
import com.dmitrienko.demoapp2.domain.score.entities.UserRankEntity
import com.google.android.material.textfield.TextInputEditText
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
            val enteredEntity = collectScoresAndNames(bindings, gameEntity)
            enteredEntity?.apply {
                alertDialog?.dismiss()
                onAddedAction(enteredEntity)
            }
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
): PairGameEntity? {
    bindings.apply {
        val p1name = player1name.text.toString()
        val p1score = player1Score.text.toString()
        val p2name = player2name.text.toString()
        val p2score = player2Score.text.toString()
        if (validate(player1name, player2name, player1Score, player2Score).not()) return null
        return PairGameEntity(
            gameEntity?.id ?: UUID.randomUUID().toString(),
            UserRankEntity(
                gameEntity?.player1?.id ?: UUID.randomUUID().toString(),
                p1name,
                p1score.toInt()
            ),
            UserRankEntity(
                gameEntity?.player2?.id ?: UUID.randomUUID().toString(),
                p2name,
                p2score.toInt()
            )
        )
    }
}

fun validate(vararg view: TextInputEditText): Boolean {
    return view.none {
        val notValid = it.text.isNullOrEmpty()
        if (notValid) it.error = it.resources?.getString(R.string.text_input_error)
        notValid
    }
}