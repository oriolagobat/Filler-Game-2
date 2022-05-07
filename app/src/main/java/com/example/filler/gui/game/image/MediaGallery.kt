package com.example.filler.gui.game.image

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.filler.gui.configuration.NewGameConfiguration

class MediaGallery(
    private val context: NewGameConfiguration,
) {
    private lateinit var mediaGalleryLauncher: ActivityResultLauncher<String>
    var imageUri: Uri? = null

    fun setMediaGalleryLauncher() {
        mediaGalleryLauncher =
            context.registerForActivityResult(ActivityResultContracts.GetContent()) {
                imageUri = it
            }
    }

    fun imageFromGallery() {
        if (!hasGalleryPermissions()) requestGalleryPermissions()
        val imageMIME = "image/*"
        mediaGalleryLauncher.launch(imageMIME)
    }

    private fun hasGalleryPermissions(): Boolean = ActivityCompat.checkSelfPermission(
        context.applicationContext,
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestGalleryPermissions() = ActivityCompat.requestPermissions(
        context,
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0
    )
}