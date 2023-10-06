package com.example.cartelerapp.home.movieDetail

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.FragmentMovieDetailBinding
import com.example.cartelerapp.home.activity.HomeActivity

class MovieDetailFragment : Fragment() {

    private var _biding : FragmentMovieDetailBinding? = null
    private val binding get() = _biding!!

    private lateinit var fActivity : HomeActivity

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
        initVideoView()
        listeners()
    }

    private fun listeners() {
        binding.btnBack.setOnClickListener {
            fActivity.onBackPressed()
        }
    }

    private fun initVideoView() {
        val uri = Uri.parse("http://techslides.com/demos/sample-videos/small.mp4")
        binding.vvTeaser.setVideoURI(uri)

        val mediaController = MediaController(requireContext())
        binding.vvTeaser.setMediaController(mediaController)
        mediaController.setAnchorView(binding.vvTeaser)
    }

}