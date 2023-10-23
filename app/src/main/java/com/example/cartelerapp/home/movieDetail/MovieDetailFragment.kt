package com.example.cartelerapp.home.movieDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.cartelerapp.databinding.FragmentMovieDetailBinding
import com.example.cartelerapp.home.activity.HomeActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class MovieDetailFragment : Fragment(){

    private var _biding : FragmentMovieDetailBinding? = null
    private val binding get() = _biding!!

    private lateinit var fActivity : HomeActivity
    private  var mPlayer : ExoPlayer? = null

    private val args : MovieDetailFragmentArgs by navArgs()
    private val entrenamiento : EntrenamientoInfo get() = args.entrena
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _biding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fActivity = (activity as? HomeActivity)!!
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
            mPlayer = ExoPlayer.Builder(requireContext()).build()
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
            fActivity.onBackPressed()
        }
    }

    override fun onStop() {
        super.onStop()
        mPlayer?.playWhenReady = false
        mPlayer?.release()
        mPlayer = null
    }
}
