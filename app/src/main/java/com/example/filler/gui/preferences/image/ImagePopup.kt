package com.example.filler.gui.preferences.image

import android.net.Uri
import androidx.core.content.FileProvider
import com.example.filler.BuildConfig
import com.example.filler.R
import com.example.filler.constants.gui.GALLERY
import com.example.filler.constants.gui.PROVIDER_STRING
import com.example.filler.constants.gui.RESOURCE_LOCATION
import com.example.filler.constants.gui.SLASH
import com.example.filler.gui.preferences.PreferencesActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ImagePopup(
    private val context: PreferencesActivity,
    private val camera: Camera = Camera(context),
    val mediaGallery: MediaGallery = MediaGallery(context)
) {    init {
    camera.setCameraLauncher()
    mediaGallery.setMediaGalleryLauncher()
}

    var chosenImageUri: Uri =
        Uri.parse(RESOURCE_LOCATION + context.packageName + SLASH + R.drawable.user_profile)

    fun show() {
        MaterialAlertDialogBuilder(context)
            .setTitle(context.getString(R.string.pref_popup_title))
            .setMessage(context.getString(R.string.pref_popup_message))
            .setNegativeButton(context.getString(R.string.pref_popup_camera)) { _, _ ->
                camera.tempImageUri = FileProvider.getUriForFile(
                    context,
                    BuildConfig.APPLICATION_ID + PROVIDER_STRING,
                    camera.createImageFile().also {
                        camera.tempImageFilePath = it.absolutePath
                    }
                )
                camera.imageFromCamera()
                chosenImageUri = camera.tempImageUri!!
            }
            .setNeutralButton(context.getString(R.string.pref_popup_gallery)) { _, _ ->
                mediaGallery.imageFromGallery()
                chosenImageUri = Uri.parse(GALLERY)
            }
            .setPositiveButton(context.getString(R.string.pref_popup_default), null).also {
                chosenImageUri =
                    Uri.parse(RESOURCE_LOCATION + context.packageName + SLASH + R.drawable.user_profile)
            }
            .show()
    }
}