package com.example.cartelerapp.home.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.ActivityHomeBinding
import com.example.cartelerapp.login.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var navController : NavController

    var uEmail = ""

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
        uEmail = bundle?.getString(MainActivity.EMAIL_KEY).toString()
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
                    navController.navigate(R.id.billboardFragment)
                }
                3 -> {
                    navController.navigate(R.id.locationFragment)
                }
                else -> {
                    navController.navigate(R.id.profileFragment)
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.meowBottom.show(1)
    }
}