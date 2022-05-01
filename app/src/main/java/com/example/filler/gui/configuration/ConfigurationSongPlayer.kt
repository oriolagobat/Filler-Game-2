package com.example.filler.gui.configuration

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.filler.R

class ConfigurationSongPlayer : Service() {
    private lateinit var player: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        player = MediaPlayer.create(applicationContext, R.raw.configuration)
        player.isLooping = true
        player.start()

        return startId
    }

    override fun onDestroy() {
        super.onDestroy()

        if (::player.isInitialized && player.isPlaying) {
            player.stop()
            player.release()
        }
    }
}

