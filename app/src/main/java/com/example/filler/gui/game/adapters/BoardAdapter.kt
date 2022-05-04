package com.example.filler.gui.game.adapters

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
import com.example.filler.gui.game.getColorFromGameColor
import kotlin.math.sqrt

class BoardAdapter(
    private val context: Context,
    var colorsList: Array<GameColor>,
    private val grid: GridView,
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
        val view: View = convertView ?: View.inflate(context, R.layout.board_item, null)
        // Get the textview from the layout
        val textView = view.findViewById<TextView>(R.id.boardItem)

        // Get the background color from the corresponding array position and set it
        val drawableColorId = getColorFromGameColor(getItem(position))
        val background: Drawable = AppCompatResources.getDrawable(context, drawableColorId)!!
        textView.background = background
        setTextViewHeight(textView)

        return view
    }

    // Textview height should be the total size of the grid divided by the number of rows (square per row)
    private fun setTextViewHeight(textView: TextView) {
        textView.layoutParams.height = grid.height / getSquarePerRow()
    }


    // We'll have as many squares in a row as the square root of the number of squares.
    private fun getSquarePerRow(): Int =
        sqrt(colorsList.size.toDouble()).toInt()
}