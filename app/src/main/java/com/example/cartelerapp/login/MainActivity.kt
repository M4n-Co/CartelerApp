package com.example.cartelerapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.cartelerapp.R
import com.example.cartelerapp.home.activity.HomeActivity
import com.example.cartelerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun valida():Boolean{
        var ok = true

        if(binding.etEmail.text.toString().trim().isEmpty()){
            binding.tilEmail.requestFocus()
            binding.tilEmail.error = getString(R.string.required_field)
            ok = false
        }else if(validateEmail(binding.etEmail.text.toString().trim())){
            binding.tilEmail.requestFocus()
            binding.tilEmail.error = getString(R.string.incorrect_format)
            ok = false
        }

        if(binding.etPass.text.toString().trim().isEmpty()){
            binding.tilPass.requestFocus()
            binding.tilPass.error = getString(R.string.required_field)
            ok = false
        }


        return ok
    }

    private fun validateEmail(email: String): Boolean{
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}