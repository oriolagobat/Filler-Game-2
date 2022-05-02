package com.example.filler.gui.game

import android.content.Context
import android.widget.Toast
import com.example.filler.constants.GameColor
import com.example.filler.databinding.ActivityGameBinding
import com.example.filler.logic.game.Game
import com.example.filler.logic.game.GameFactoryImpl
import com.example.filler.logic.game.GameResponse
import com.example.filler.logic.game.GameSettings
import com.example.filler.logic.stub.GameStub9x9

class GameIteration(
    private val context: Context,
    private val binding: ActivityGameBinding,
    private val gameSettings: GameSettings,
) {
    private var game: Game = GameFactoryImpl(gameSettings).makeGame()
    private var gameResponse: GameResponse = game.getGameResponse()
    fun start() {
        setUpGameBoard()
        setUpChooserBar()
    }

    // Sets up the game board, with its adapter
    private fun setUpGameBoard() {
        val columnNumber = gameResponse.board.getNumCols()
        binding.boardGridView.numColumns = columnNumber

        val boardArray: Array<GameColor> = gameResponse.board.toArray()
        binding.boardGridView.adapter =
            BoardAdapter(context, boardArray, binding.boardGridView)
    }

    // Sets up the chooser bar, with its adapter
    private fun setUpChooserBar() {
        // This is how it will be
//        binding.boardGridView.numColumns = settings.nColors
        // This is for now, in order to work  with the game stub
//        binding.selectorGridView.numColumns = 3
        binding.selectorGridView.numColumns = 8
//        val gameStub = GameStub3x3(gameSettings)  //  Game initialization
        val gameStub = GameStub9x9(gameSettings)  //  Game initialization

        val stubSelector: GameResponse = gameStub.initGame()
        val colors = getArrayColors(stubSelector.selector.toArray())
        binding.selectorGridView.adapter =
            SelectorAdapter(context, stubSelector.selector.toArray(), binding.selectorGridView)
        manageSelectorListeners(stubSelector, colors)
    }

    // Manages the listeners of the chooser bar
    private fun manageSelectorListeners(response: GameResponse, colors: Array<GameColor>) {
        // Set on click listener for the selector grid
        binding.selectorGridView.setOnItemClickListener { _, _, position, _ ->
            // Get the color that was clicked
            val color = colors[position]
            if (colorUnClickable(response.selector.toArray(), color)) {
                // If the color is un clickable, do nothing
                return@setOnItemClickListener
            } else {
                // TODO: Pick game color
                Toast.makeText(context, "You selected color $color", Toast.LENGTH_SHORT).show()
            }
        }
    }
}