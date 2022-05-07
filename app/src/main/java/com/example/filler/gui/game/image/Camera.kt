package com.example.filler.gui.game.image

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.filler.gui.configuration.NewGameConfiguration
import java.io.File

class Camera(
    private val context: NewGameConfiguration,
    var tempImageUri: Uri? = null,
    var tempImageFilePath: String = ""
) {
    private lateinit var camaraLauncher: ActivityResultLauncher<Uri>

    fun setCameraLauncher() {
        camaraLauncher = context.registerForActivityResult(ActivityResultContracts.TakePicture()) {}
    }

    fun createImageFile(): File {
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("temp_image", ".jpg", storageDir)
    }

    fun imageFromCamera() {
        if (!hasCameraPermissions()) requestCameraPermissions()
        camaraLauncher.launch(tempImageUri)
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