package com.example.cartelerapp.home.movieDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.ActivityVideoAndDetailsBinding
import com.example.cartelerapp.home.activity.HomeActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoAndDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVideoAndDetailsBinding

    private  var mPlayer : ExoPlayer? = null

    private val args : VideoAndDetailsActivityArgs by navArgs()
    private val entrenamiento : EntrenamientoInfo get() = args.entrena

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoAndDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI(){
        initListeners()
        setInfo()
    }

    private fun setInfo() {
        with(binding){
            tvTitle.text = entrenamiento.nombre
            tvTitle.isSelected = true

            tvAim.text = entrenamiento.objetivo
            tvDescription.text = entrenamiento.descripcion
            tvInstructions.text = entrenamiento.instrucciones
            tvExercisesNumber.text = entrenamiento.numeroEjercicios.toString()
            tvSeriesNumber.text = entrenamiento.numeroSeries.toString()
        }
        initVideo()
    }

    private fun initVideo() {
        with(binding){
            mPlayer = ExoPlayer.Builder(binding.playerView.context).build()
            playerView.player = mPlayer
            val mediaItem = entrenamiento.mediaSource?.let { MediaItem.fromUri(it) }
            if (mediaItem != null) {
                mPlayer?.setMediaItem(mediaItem)
            }
            mPlayer?.prepare()
            mPlayer?.playWhenReady = true
        }
    }
    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onStop() {
        super.onStop()
        mPlayer?.playWhenReady = false
        mPlayer?.release()
        mPlayer = null
    }
}