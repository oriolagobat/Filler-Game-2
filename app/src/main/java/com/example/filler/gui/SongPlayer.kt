package com.example.filler.gui

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.filler.constants.gui.Music

class SongPlayer : Service() {
    private lateinit var player: MediaPlayer
    private var callIntent: Intent? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        callIntent = intent

        val song = intent?.getIntExtra(Music.SONG.name, -1)

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
        if (::player.isInitialized && !player.isPlaying) {  // Not first execution
            player.stop()
            player.reset()
            player.release()
        }
        player = MediaPlayer.create(applicationContext, song)
        player.start()
        loopIfWanted()
    }

    private fun loopIfWanted() {
        val loop = callIntent?.getBooleanExtra(Music.LOOP.name, false)
        if (loop!!) {
            player.isLooping = true
        }
    }
}