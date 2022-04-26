package com.example.filler.gui.game

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintSet
import com.example.filler.R
import com.example.filler.constants.GameColor
import kotlin.math.sqrt

class GridAdapter(private val context: Context, private val colorsList: Array<GameColor>) :
    BaseAdapter() {
    private var squareSize: Int = setSquareSizeFromTotalNumber()
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

        // FIXME: Developing
        val layout = view.findViewById<View>(R.id.gridLayout)

        // Set the textView size
        setSquareSizeFromTotalNumber()
        setSize(textView)

        // FIXME: Developing
        layout.layoutParams.height = squareSize * 9
        layout.layoutParams.width = squareSize * 9

        // Get the background color from the corresponding array position and set it
        val drawableColorId = getColorFromGameColor(getItem(position))
        val background: Drawable = AppCompatResources.getDrawable(context, drawableColorId)!!
        textView.background = background

        return view
    }

    private fun setSquareSizeFromTotalNumber(): Int {
        val totalNumber = colorsList.size
        val squaresPerLine = sqrt(totalNumber.toDouble()).toInt()
        // Perfect relation is 3:100, so we just apply this relation to the number of squares
        return (3 * 100) / squaresPerLine
    }

    private fun setSize(textView: TextView) {
        // FIXME: Developing
//        val layoutParams = LinearLayout.LayoutParams(squareSize, squareSize)
//
//        textView.layoutParams = layoutParams
        textView.width = squareSize
        textView.height = squareSize
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
}