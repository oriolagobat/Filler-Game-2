package com.example.filler.gui.game

import android.content.Intent
import android.widget.GridView
import com.example.filler.R
import com.example.filler.constants.gui.Music
import com.example.filler.constants.logic.GameColor
import com.example.filler.constants.logic.GameState
import com.example.filler.databinding.ActivityGameBinding
import com.example.filler.gui.shared.SongPlayer
import com.example.filler.logic.game.GameResponse

fun getFirstIntFromString(str: String): Int = str.first().digitToInt()

fun getBoard(binding: ActivityGameBinding): GridView = binding.boardGridView
fun getSelector(binding: ActivityGameBinding): GridView = binding.selectorGridView

fun setUpViewModel(viewModel: GUIGameViewModel): Boolean = !viewModel.setUpViewModel.value!!

fun refreshBoardSelectorReference(viewModel: GUIGameViewModel, binding: ActivityGameBinding) {
    val mediator = viewModel.mutableGameMediator.value!!
    mediator.board = binding.boardGridView
    mediator.selector = binding.selectorGridView
    mediator.timer.timerTextView = binding.timer
}

fun gameFinished(gameResponse: GameResponse): Boolean {
    return when (gameResponse.state) {
        GameState.P1_WON, GameState.P2_WON, GameState.DRAW -> true
        else -> false
    }
}

fun getArrayColors(array: Array<Pair<GameColor, Boolean>>): Array<GameColor> =
    array.map { it.first }.toTypedArray()

fun colorUnClickable(
    array: Array<Pair<GameColor, Boolean>>,
    chosenColor: GameColor
): Boolean {
    for (pair in array) {
        if (pair.first == chosenColor && pair.second) {
            return true
        }
    }
    return false
}

fun getColorFromGameColor(gameColor: GameColor): Int {
    return when (gameColor) {
        GameColor.PINK -> R.color.game_pink
        GameColor.ORANGE -> R.color.game_orange
        GameColor.YELLOW -> R.color.game_yellow
        GameColor.GREEN -> R.color.game_green
        GameColor.BLUE -> R.color.game_blue
        GameColor.PURPLE -> R.color.game_purple
        GameColor.CYAN -> R.color.game_cyan
        GameColor.BLACK -> R.color.black
        else -> throw IllegalArgumentException("No more colors...")
    }
}

fun startGameSong(context: GUIGame) {
    val intent = Intent(context, SongPlayer::class.java)
    intent.putExtra(Music.SONG.name, R.raw.game)
    intent.putExtra(Music.LOOP.name, true)
    context.startService(intent)
}

fun stopGameSong(context: GUIGame) {
    val intent = Intent(context, SongPlayer::class.java)
    context.stopService(intent)
}