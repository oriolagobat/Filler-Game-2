package com.example.filler.gui.game

import android.content.Context
import android.widget.Toast
import com.example.filler.constants.GameColor
import com.example.filler.constants.GridType
import com.example.filler.databinding.ActivityGameBinding
import com.example.filler.logic.GameResponse
import com.example.filler.logic.GameSettings
import com.example.filler.logic.stub.GameStub3x3

class GameIteration(
    private val context: Context,
    private val binding: ActivityGameBinding,
    private val gameSettings: GameSettings
) {
    fun start() {
        setUpGameBoard()
        setUpChooserBar()
    }

    // FIXME: Remove stub functionality and replace with actual game logic

    // Set's up the game board, with its adapter
    private fun setUpGameBoard() {
        // This is how it will be
//        binding.boardGridView.numColumns = settings.boardSize
        // This is for now, in order to work  with the game stub
        binding.boardGridView.numColumns = 3
//        binding.boardGridView.numColumns = 9
        val gameStub = GameStub3x3(gameSettings)  //  Game initialization
//        val gameStub = GameStub9x9(gameSettings)  //  Game initialization

        val stubBoard: GameResponse = gameStub.initGame()
        binding.boardGridView.adapter =
            GridAdapter(context, stubBoard.board.toArray(), binding.boardGridView, GridType.BOARD)
    }

    // Set's up the chooser bar, with its adapter
    private fun setUpChooserBar() {
        // This is how it will be
//        binding.boardGridView.numColumns = settings.nColors
        // This is for now, in order to work  with the game stub
        binding.selectorGridView.numColumns = 3
//        binding.selectorGridView.numColumns = 8
        val gameStub = GameStub3x3(gameSettings)  //  Game initialization
//        val gameStub = GameStub9x9(gameSettings)  //  Game initialization

        val stubSelector: GameResponse = gameStub.initGame()
        val colors = getArrayColors(stubSelector.selector.toArray())
        binding.selectorGridView.adapter =
            GridAdapter(context, colors, binding.selectorGridView, GridType.SELECTOR)
        manageSelectorListeners(stubSelector, colors)
    }

    // Manages the listeners of the chooser bar
    private fun manageSelectorListeners(response: GameResponse, colors: Array<GameColor>) {
        // Set on click listener for the selector grid
        binding.selectorGridView.setOnItemClickListener { _, _, position, _ ->
            // Get the color that was clicked
            val color = colors[position]
            if (colorUnClickable(response.selector.toArray(), color)) {
                // If the color is unclickable, do nothing
                return@setOnItemClickListener
            } else {
                // TODO: Pick game color
                Toast.makeText(context, "You selected color $color", Toast.LENGTH_SHORT).show()
            }
        }
    }
}