package com.example.filler.gui.game

import com.example.filler.constants.GameColor
import com.example.filler.constants.PlayerType
import com.example.filler.databinding.ActivityGameBinding
import com.example.filler.logic.game.Game
import com.example.filler.logic.game.GameResponse

class GameIteration(
    private val context: GUIGame,
    private val binding: ActivityGameBinding,
    private val game: Game,
    private val gameResponse: GameResponse,
    private val playerType: PlayerType
) {
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
        val columnNumber = gameResponse.selector.getTotalAmountOfColors()
        binding.selectorGridView.numColumns = columnNumber

        val selectorArray: Array<Pair<GameColor, Boolean>> = gameResponse.selector.toArray()
        binding.selectorGridView.adapter =
            SelectorAdapter(context, selectorArray, binding.selectorGridView)

        manageInteraction()
    }

    private fun manageInteraction() {
        when (playerType) {
            PlayerType.HUMAN -> humanInteraction()
            PlayerType.AI -> aiInteraction()
        }
    }

    private fun humanInteraction() {
        val selectorArray: Array<Pair<GameColor, Boolean>> = gameResponse.selector.toArray()

        val colorsSelectorArray: Array<GameColor> = selectorArray.map { it.first }.toTypedArray()
        manageSelectorListeners(colorsSelectorArray)
    }

    // Manages the listeners of the chooser bar
    private fun manageSelectorListeners(colors: Array<GameColor>) {
        // Set on click listener for the selector grid
        binding.selectorGridView.setOnItemClickListener { _, _, position, _ ->
            // Get the color that was clicked
            val color = colors[position]
            if (colorUnClickable(gameResponse.selector.toArray(), color)) {
                // If the color is un clickable, do nothing
                return@setOnItemClickListener
            } else {
                val nextIterationGameResponse = game.pickColorManually(color)
                context.nextIteration(nextIterationGameResponse, PlayerType.AI)
            }
        }
    }

    private fun aiInteraction() {
        val nextIterationGameResponse = game.pickColorThroughAI()
        context.nextIteration(nextIterationGameResponse,PlayerType.HUMAN)
    }
}