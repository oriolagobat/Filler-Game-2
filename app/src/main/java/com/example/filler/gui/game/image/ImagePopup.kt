package com.example.filler.gui.game.image

import android.widget.ImageView
import android.widget.Toast
import com.example.filler.gui.game.GUIGame
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ImagePopup(
    private val context: GUIGame,
    private val imageId: Int
) {    init {
    val userPFP = context.findViewById<ImageView>(imageId)

    MaterialAlertDialogBuilder(context)
        .setTitle("Profile picture")
        .setMessage("What do you want to do with your profile picture?")
        .setNegativeButton("Take a picture") { _, _ ->
            imageFromCamera()
            Toast.makeText(context, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }
        .setNeutralButton("Choose from gallery") { _, _ ->
            imageFromGallery()
            Toast.makeText(context, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }
        .setPositiveButton("Use the default one", null)
        .show()
}
}