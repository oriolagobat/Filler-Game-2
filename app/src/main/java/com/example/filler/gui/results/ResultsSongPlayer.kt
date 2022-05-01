package com.example.filler.gui.results

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class ResultsSongPlayer : Service() {
    private lateinit var player: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        val song = intent?.getIntExtra("song", -1)

        manageSoundPlayer(song!!)
        return startId
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player.isPlaying) {
            player.stop()
            player.release()
        }
    }

    private fun manageSoundPlayer(song: Int) {
        if (!::player.isInitialized || player.isPlaying) {  // First execution
            player = MediaPlayer.create(applicationContext, song)
            player.start()
        } else {  // Other executions, stop and re-set taskGPlayer
            player.stop()
            player.reset()
            player.release()
            player = MediaPlayer.create(applicationContext, song)
            player.start()
        }
    }
}