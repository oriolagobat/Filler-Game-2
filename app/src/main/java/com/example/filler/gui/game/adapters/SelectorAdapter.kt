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
import com.example.filler.constants.logic.GameColor
import com.example.filler.gui.game.getColorFromGameColor

class SelectorAdapter(
    private val context: Context,
    var content: Array<Pair<GameColor, Boolean>>,
    private val grid: GridView
) : BaseAdapter() {
    override fun getCount(): Int {
        return content.size
    }

    override fun getItem(position: Int): Pair<GameColor, Boolean> {
        return content[position]
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
        val drawableColorId = getColorFromGameColor(getItem(position).first)
        val background: Drawable = AppCompatResources.getDrawable(context, drawableColorId)!!
        textView.background = background
        setTextViewHeight(textView)

        // If color is un-clickable apply an alpha to it
        modifyAlpha(position, textView)

        return view
    }

    private fun modifyAlpha(position: Int, textView: TextView) {
        val alpha = if (colorUnClickable(position)) 0.05f else 1f
        textView.alpha = alpha
    }

    private fun colorUnClickable(position: Int) = getItem(position).second

    // Textview height should be the total grid's width divided by the number of columns (square per row)
    private fun setTextViewHeight(textView: TextView) {
        textView.layoutParams.height = grid.width / getSquarePerRow()
    }


    // We'll have as many squares in a row as the total number of squares.
    private fun getSquarePerRow(): Int = content.size
}