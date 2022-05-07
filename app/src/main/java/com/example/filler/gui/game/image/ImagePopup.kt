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
    val mediaGallery: MediaGallery = MediaGallery(context)
) {    init {
    camera.setCameraLauncher()
    mediaGallery.setMediaGalleryLauncher()
}

    var chosenImageUri: Uri =
        Uri.parse("android.resource://" + context.packageName + "/" + R.drawable.user_profile)

    fun show() {
        MaterialAlertDialogBuilder(context)
            .setTitle(context.getString(R.string.configuration_popup_title))
            .setMessage(context.getString(R.string.configuration_popup_message))
            .setNegativeButton(context.getString(R.string.configuration_popup_camera)) { _, _ ->
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
            .setNeutralButton(context.getString(R.string.configuration_popup_gallery)) { _, _ ->
                mediaGallery.imageFromGallery()
                chosenImageUri = Uri.parse("gallery")
            }
            .setPositiveButton(context.getString(R.string.configuration_popup_default), null).also {
                chosenImageUri =
                    Uri.parse("android.resource://" + context.packageName + "/" + R.drawable.user_profile)
            }
            .show()
    }
}