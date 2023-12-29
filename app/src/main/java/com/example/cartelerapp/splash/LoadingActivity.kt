package com.example.cartelerapp.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.cartelerapp.databinding.ActivityLoadingBinding
import com.example.cartelerapp.home.activity.HomeActivity
import com.example.cartelerapp.login.MainActivity

class LoadingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoadingBinding

    private var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        screenSplash.setKeepOnScreenCondition {false}

        initAnimation()

    }

    private fun initAnimation() {
        if (cont < 3){
            binding.ivLogo.animate().apply {
                duration = 700
                interpolator = LinearInterpolator()
                rotationBy(360f)
                withEndAction {
                    binding.ivLogo.postDelayed({
                        cont++
                        initAnimation() }, 500)
                }
                start()
            }
        }else{
            loginOrLogear()
        }
    }

    private fun loginOrLogear() {
        val sharedPreferences = getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString(EMAIL_KEY, null)

        if (!userEmail.isNullOrEmpty()){
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra(EMAIL_KEY, userEmail)
            }
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    companion object{
        const val SHARED_KEY = "SHARED_KEY"
        const val EMAIL_KEY = "EMAIL_KEY"
    }

}