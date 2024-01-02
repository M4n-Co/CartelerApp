package com.example.cartelerapp.home.activity

import android.content.Intent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.ActivityHomeBinding
import com.example.cartelerapp.splash.LoadingActivity
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var navController : NavController

    var uEmail = ""

    private val requestCheckSettings = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

    }

    private fun initUI() {
        obtenerExtras()
        initNavigation()
    }

    private fun obtenerExtras() {
        val bundle = intent.extras
        uEmail = bundle?.getString(LoadingActivity.EMAIL_KEY).toString()
    }

    private fun initNavigation() {

        binding.meowBottom.add(MeowBottomNavigation.Model(1,R.drawable.ic_profile))
        binding.meowBottom.add(MeowBottomNavigation.Model(2,R.drawable.ic_billboard))
        binding.meowBottom.add(MeowBottomNavigation.Model(3,R.drawable.ic_location))
        binding.meowBottom.show(1)

        val navHost = supportFragmentManager.findFragmentById(R.id.fcView) as NavHostFragment
        navController = navHost.navController
        binding.meowBottom.setOnClickMenuListener {v->
            when(v.id){
                1 -> {
                    navController.navigate(R.id.profileFragment)
                }
                2 -> {
                    navController.popBackStack(R.id.locationFragment, true)
                    navController.navigate(R.id.billboardFragment)
                }
                3 -> {
                    isEnableGPS()
                }
                else -> {
                    navController.navigate(R.id.profileFragment)
                }
            }
        }
    }

    private fun isEnableGPS() {
        val locationRequest = LocationRequest.Builder(LocationRequest.PRIORITY_HIGH_ACCURACY, 10000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .build()

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            // All location settings are satisfied. The client can initialize
            // location requests here.
            // ...
            navController.popBackStack(R.id.billboardFragment,true)
            navController.navigate(R.id.locationFragment)
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException){
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(this, requestCheckSettings
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.meowBottom.show(1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == requestCheckSettings){
            if (resultCode == RESULT_OK){
                navController.popBackStack(R.id.billboardFragment,true)
                navController.navigate(R.id.locationFragment)
            }else{
                binding.meowBottom.show(1)
            }
        }

    }
}