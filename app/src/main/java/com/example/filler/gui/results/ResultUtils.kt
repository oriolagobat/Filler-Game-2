package com.example.filler.gui.results

import android.content.Intent
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.filler.R
import com.example.filler.constants.gui.Music
import com.example.filler.constants.gui.Outcomes
import com.example.filler.constants.gui.Scores
import com.example.filler.databinding.ActivityResultsBinding
import com.example.filler.gui.game.data.Score
import com.example.filler.gui.shared.SongPlayer
import com.example.filler.gui.shared.getValidMailOrError

fun setUpResultListeners(
    context: Results,
    binding: ActivityResultsBinding
) {
    binding.emailInput.setOnClickListener(context)
    binding.sendEmailButton.setOnClickListener(context)
    binding.restartGameButton.setOnClickListener(context)
    binding.closeButton.setOnClickListener(context)
    binding.settingsButton.setOnClickListener(context)
}

fun startSongPlayer(
    context: Results,
    startIntent: Intent
) {
    val playerIntent = Intent(context, SongPlayer::class.java)
    playerIntent.putExtra(Music.LOOP.name, false)  // Makes it not loop
    when (startIntent.getStringExtra(Outcomes.OUTCOME.name)) {
        Outcomes.WIN.toString() -> playerIntent.putExtra(Music.SONG.name, R.raw.win)
        Outcomes.LOSE.toString() -> playerIntent.putExtra(Music.SONG.name, R.raw.lose)
        Outcomes.DRAW.toString() -> playerIntent.putExtra(Music.SONG.name, R.raw.draw)
    }
    context.startService(playerIntent)
}

fun updateScoreText(
    context: Results,
    intent: Intent,
    binding: ActivityResultsBinding
) {
    val p1Score: Score = intent.getSerializableExtra(Scores.PLAYER1SCORE.name) as Score
    val p2Score = intent.getSerializableExtra(Scores.PLAYER2SCORE.name) as Score
    val p1Name = context.getString((R.string.score_p1_sample))
    val p2Name = context.getString((R.string.score_ai_sample))

    binding.p1Score.text = formatScore(p1Name, p1Score.value.toString())
    binding.aiScore.text = formatScore(p2Name, p2Score.value.toString())

    setCorrectTextColor(context, intent, binding)
}

private fun formatScore(name: String, score: String): String = "$name: $score"

private fun setCorrectTextColor(
    context: Results,
    intent: Intent,
    binding: ActivityResultsBinding
) {
    val (p1Color, p2Color) = when (intent.getStringExtra(Outcomes.OUTCOME.name)) {
        Outcomes.WIN.name -> Pair(R.color.green, R.color.red)
        Outcomes.LOSE.name -> Pair(R.color.red, R.color.green)
        Outcomes.DRAW.name -> Pair(R.color.yellow, R.color.yellow)
        else -> throw IllegalArgumentException("No more possible results")
    }
    binding.p1Score.setTextColor(ContextCompat.getColor(context, p1Color))
    binding.aiScore.setTextColor(ContextCompat.getColor(context, p2Color))
}

fun getWinInfo(): Triple<Int, Int, Int> = Triple(
    R.drawable.result_win,
    R.string.results_win_header,
    R.string.results_outcome_win
)

fun getLoseInfo(): Triple<Int, Int, Int> = Triple(
    R.drawable.result_lose,
    R.string.results_lose_header,
    R.string.results_outcome_draw
)

fun getDrawInfo(): Triple<Int, Int, Int> = Triple(
    R.drawable.result_draw,
    R.string.results_draw_header,
    R.string.results_outcome_draw
)

fun saveEmail(
    context: Results,
    emailInput: EditText
): String {
    return getValidMailOrError(context, emailInput)
}
