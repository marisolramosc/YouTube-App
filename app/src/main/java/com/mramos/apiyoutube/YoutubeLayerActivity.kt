package com.mramos.apiyoutube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID_KEY = "vTDkNw9rbOE"
const val PLAYLIST_ID_KEY = "PL9qQXSjI-WOpc57a0TUo037a4DPsowSY4"


class YoutubeLayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener{
    val TAG = "YoutubeLayerActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube_layer)
//        val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)

        val layout = layoutInflater.inflate(R.layout.activity_youtube_layer, null) as ConstraintLayout
        setContentView(layout)

//        val button1 = Button(this)
//        button1.layoutParams = ConstraintLayout.LayoutParams(600, 100)
//        button1.text = "Button added"
//        layout.addView(button1)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)

    }

    override fun onInitializationSuccess( pl: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean ){
        Log.d(TAG, "onInitializationSuccess")
        Toast.makeText(this, "Initialized successfully", Toast.LENGTH_LONG).show()

        player?.setPlaybackEventListener(playbackEventListener)
        player?.setPlayerStateChangeListener(changeStateListener)

        if(!wasRestored){
            player?.cueVideo(YOUTUBE_VIDEO_ID_KEY)
        }
    }


    override fun onInitializationFailure( provider: YouTubePlayer.Provider?, YouTubeInitializationResult: YouTubeInitializationResult? ) {
        Log.d(TAG, "onInitializationFailure")
        val REQUEST_CODE = 0
        if(YouTubeInitializationResult?.isUserRecoverableError == true){
            YouTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            Toast.makeText(this, "Error starting player", Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object : YouTubePlayer.PlaybackEventListener{
        override fun onPlaying() {
            Toast.makeText(this@YoutubeLayerActivity, "Playing", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeLayerActivity, "Paused", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onSeekTo(p0: Int) {
        }

    }

    private val changeStateListener = object : YouTubePlayer.PlayerStateChangeListener{
        override fun onLoading() {
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onAdStarted() {
            Toast.makeText(this@YoutubeLayerActivity, "Add", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoStarted() {
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeLayerActivity, "End", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }

    }

}