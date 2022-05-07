package com.example.filler.gui.game.image

import android.net.Uri
import androidx.core.content.FileProvider
import com.example.filler.BuildConfig
import com.example.filler.R
import com.example.filler.gui.configuration.NewGameConfiguration
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ImagePopup(
    private val context: NewGameConfiguration,
    private val camera: Camera = Camera(context),
    private val mediaGallery: MediaGallery = MediaGallery(context)
) {    init {
    camera.setCameraLauncher()
    mediaGallery.setMediaGalleryLauncher()
}

    lateinit var chosenImageUri: Uri

    fun show() {
        MaterialAlertDialogBuilder(context)
            .setTitle("Profile picture")
            .setMessage("What do you want to do with your profile picture?")
            .setNegativeButton("Take a picture") { _, _ ->
                camera.tempImageUri = FileProvider.getUriForFile(
                    context,
                    BuildConfig.APPLICATION_ID + ".provider",
                    camera.createImageFile().also {
                        camera.tempImageFilePath = it.absolutePath
                    }
                )
                camera.imageFromCamera()
                chosenImageUri = camera.tempImageUri!!
            }
            .setNeutralButton("Choose from gallery") { _, _ ->
                mediaGallery.imageFromGallery()
                chosenImageUri = mediaGallery.imageUri
            }
            .setPositiveButton("Use the default one", null).also {
                chosenImageUri = Uri.parse("android.resource://" + context.packageName + "/" + R.drawable.user_profile)
            }
            .show()
    }
}