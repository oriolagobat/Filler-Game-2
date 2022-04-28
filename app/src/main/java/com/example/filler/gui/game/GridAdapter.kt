package com.example.filler.gui.game

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.example.filler.R
import com.example.filler.constants.GameColor
import com.example.filler.constants.GridType
import kotlin.math.sqrt

class GridAdapter(
    private val context: Context,
    private val colorsList: Array<GameColor>,
    private val grid: GridView,
    private val gridType: GridType
) :
    BaseAdapter() {
    override fun getCount(): Int {
        return colorsList.size
    }

    override fun getItem(position: Int): GameColor {
        return colorsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Inflate the layout if it is not already inflated
        val view: View = convertView ?: View.inflate(context, R.layout.grid_item, null)
        // Get the textview from the layout
        val textView = view.findViewById<TextView>(R.id.gridItem)

        // Get the background color from the corresponding array position and set it
        val drawableColorId = getColorFromGameColor(getItem(position))
        val background: Drawable = AppCompatResources.getDrawable(context, drawableColorId)!!
        textView.background = background
        setTextViewHeight(textView)

        return view
    }

    private fun getColorFromGameColor(gameColor: GameColor): Int {
        return when (gameColor) {
            GameColor.PINK -> R.color.game_pink
            GameColor.ORANGE -> R.color.game_orange
            GameColor.YELLOW -> R.color.game_yellow
            GameColor.GREEN -> R.color.game_green
            GameColor.BLUE -> R.color.game_blue
            GameColor.PURPLE -> R.color.game_purple
            GameColor.CYAN -> R.color.game_cyan
            GameColor.BLACK -> R.color.black
            else -> throw UnsupportedOperationException("No more colors...")
        }
    }

    // If we're dealing with the board, the textview height should be the total size of the grid divided by the number of rows (square per row)
    // If we're dealing with the selector, the textview height should be the total grid's width divided by the number of columns (square per row)
    private fun setTextViewHeight(textView: TextView) {
        when (gridType) {
            GridType.BOARD -> textView.layoutParams.height = grid.height / getSquarePerRow()
            GridType.SELECTOR -> textView.layoutParams.height = grid.width / getSquarePerRow()
        }
    }


    // If we're dealing with the board, we'll have as many squares in a row as the square root of the number of squares.
    // If we're dealing with the selector, we'll have as many squares in a row as the total number of squares.
    private fun getSquarePerRow(): Int {
        return when (gridType) {
            GridType.BOARD -> sqrt(colorsList.size.toDouble()).toInt()
            GridType.SELECTOR -> colorsList.size
        }
    }
}