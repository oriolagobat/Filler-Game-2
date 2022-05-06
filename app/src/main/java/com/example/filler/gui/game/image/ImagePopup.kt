package com.example.filler.gui.game.image

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.example.filler.BuildConfig
import com.example.filler.gui.configuration.NewGameConfiguration
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.File

class ImagePopup(
    private val context: NewGameConfiguration,
    private val imageId: Int,
    private val imageFile: File = File(context.filesDir, "image.png"),
    private var takenImageUri: Uri = FileProvider.getUriForFile(
        context.applicationContext,
        BuildConfig.APPLICATION_ID + ".provider", imageFile
    ),

    private val galleryResult: ActivityResultLauncher<String> =
        context.registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri ->
            val image = context.findViewById<ImageView>(imageId)
            image.setImageURI(uri)
        },

    private val cameraResult: ActivityResultLauncher<Uri> =
        context.registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                val image = context.findViewById<ImageView>(imageId)
                image.setImageURI(takenImageUri)
            }
        }
) {    init {
    MaterialAlertDialogBuilder(context)
        .setTitle("Profile picture")
        .setMessage("What do you want to do with your profile picture?")
        .setNegativeButton("Take a picture") { _, _ ->
            imageFromCamera()
        }
        .setNeutralButton("Choose from gallery") { _, _ ->
            imageFromGallery()
        }
        .setPositiveButton("Use the default one", null)
        .show()
}

    private fun imageFromGallery() {
        if (!hasGalleryPermissions()) requestGalleryPermissions()
        val imageMIME = "image/*"
        galleryResult.launch(imageMIME)
    }

    private fun hasGalleryPermissions(): Boolean = ActivityCompat.checkSelfPermission(
        context.applicationContext,
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestGalleryPermissions() = ActivityCompat.requestPermissions(
        context,
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0
    )

    private fun imageFromCamera() {
        if (!hasCameraPermissions()) requestCameraPermissions()
        cameraResult.launch(takenImageUri)
    }

    private fun hasCameraPermissions() = ActivityCompat.checkSelfPermission(
        context.applicationContext,
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).toString()
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestCameraPermissions() = ActivityCompat.requestPermissions(
        context,
        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA), 0
    )
}