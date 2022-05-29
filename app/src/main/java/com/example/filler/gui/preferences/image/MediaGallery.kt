package com.example.filler.gui.preferences.image

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.filler.constants.gui.IMAGE_MIME
import com.example.filler.gui.preferences.PreferencesActivity

class MediaGallery(
    private val context: PreferencesActivity,
) {
    private lateinit var mediaGalleryLauncher: ActivityResultLauncher<Array<String>>
    var imageUri: String? = null

    fun setMediaGalleryLauncher() {
        mediaGalleryLauncher =
            context.registerForActivityResult(ActivityResultContracts.OpenDocument()) {
                context.contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                imageUri = it.toString()
            }
    }

    fun imageFromGallery() {
        if (!hasGalleryPermissions()) requestGalleryPermissions()
        val imageMIME = IMAGE_MIME
        mediaGalleryLauncher.launch(arrayOf(imageMIME))
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