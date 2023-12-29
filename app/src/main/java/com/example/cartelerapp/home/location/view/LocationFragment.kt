package com.example.cartelerapp.home.location.view

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.FragmentLocationBinding
import com.example.cartelerapp.home.activity.HomeActivity
import com.example.cartelerapp.home.location.viewModel.LocationViewModel
import com.example.cartelerapp.login.MainActivity
import com.example.cartelerapp.splash.LoadingActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : Fragment(), OnMapReadyCallback {

    private var _binding : FragmentLocationBinding? = null
    private val binding get() = _binding!!

    private lateinit var fActivity : HomeActivity
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var map : GoogleMap
    private lateinit var coordinates : LatLng

    private val viewModel : LocationViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fActivity = (activity as? HomeActivity)!!
        initUI()

    }
    private fun initUI(){
        initListeners()
        getLocation()
        viewModelObserves()
    }

    private fun viewModelObserves() {
        viewModel.locationResponse.observe(viewLifecycleOwner){
            if (it != null){
                val location = it.display_name
                binding.tvLocation.text = location
            }
        }
        viewModel.loading.observe(viewLifecycleOwner){
            binding.progressBar.isVisible = it
        }
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            fActivity.onBackPressed()
        }

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.logout_title)
                .setMessage(R.string.message_logout)
                .setPositiveButton(R.string.accept){_,_ ->
                    logout()
                }
                .setNegativeButton(R.string.cancel){_,_ ->

                }.show()
        }
    }

    private fun logout() {
        val sharedPreferences = requireActivity().getSharedPreferences(LoadingActivity.SHARED_KEY, Context.MODE_PRIVATE)
        val editShared = sharedPreferences.edit()
        editShared.apply {
            remove(LoadingActivity.EMAIL_KEY)
        }.apply()

        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        fActivity.finish()
    }

    private fun getLocation() {

        val task = fusedLocationProviderClient.lastLocation
        if(ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
            return
        }
        task.addOnSuccessListener {
            if (it != null){
                coordinates = LatLng(it.latitude, it.longitude)

                val ubicacion = "${it.latitude},${it.longitude}"
                binding.tvLocation.text = ubicacion
                viewModel.getLocation(it.latitude.toString(), it.longitude.toString())

                createMapFragment()
            }
        }

    }

    private fun createMapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.fMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
    }

    private fun createMarker() {
        val marker = MarkerOptions().position(coordinates)
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 18f),
            5000,
            null
        )
    }
}