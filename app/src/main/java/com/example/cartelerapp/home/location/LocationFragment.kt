package com.example.cartelerapp.home.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.FragmentLocationBinding
import com.example.cartelerapp.home.activity.HomeActivity

class LocationFragment : Fragment() {

    private var _binding : FragmentLocationBinding? = null
    private val binding get() = _binding!!

    private lateinit var fActivity : HomeActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fActivity = (activity as? HomeActivity)!!
        initUI()

    }
    private fun initUI(){
        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            fActivity.onBackPressed()
        }
    }
}