package com.example.filler.gui.game

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.example.filler.R
import com.example.filler.constants.GameColor

abstract class GridAdapter(val context: Context, private val itemList: Array<GameColor>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return itemList.size
    }

    override fun getItem(position: Int): GameColor {
        return itemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = TextView(context)
        val drawableColorId = getColorFromGameColor(getItem(position))
        val background: Drawable = AppCompatResources.getDrawable(context, drawableColorId)!!
        textView.background = background

        return textView
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