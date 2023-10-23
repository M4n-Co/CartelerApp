package com.example.cartelerapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import com.example.cartelerapp.R
import com.example.cartelerapp.home.activity.HomeActivity
import com.example.cartelerapp.databinding.ActivityMainBinding
import com.example.cartelerapp.signUp.view.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    companion object{
        const val EMAIL_KEY = "EMAIL"
    }

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initListeners()
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            if (valida()){

                val email = binding.etEmail.text.toString()
                val pass = binding.etPass.text.toString()

                binding.pbLogin.isVisible = true

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = auth.currentUser
                        //val firebaseId = user?.uid //firebaseId
                        binding.pbLogin.isVisible = false
                        user?.email?.let { uEmail -> login(uEmail) }
                    } else {
                        showError()
                        binding.pbLogin.isVisible = false
                    }
                }
            }
        }
        binding.btnRegistration.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(uEmail:String) {
        binding.etEmail.setText("")
        binding.etPass.setText("")
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(EMAIL_KEY, uEmail)
        }
        startActivity(intent)
    }
    private fun showError() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.error))
        builder.setMessage(getString(R.string.error_login))
        builder.setPositiveButton(getString(R.string.aceptar),null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
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
        }else if (!validatePass(binding.etPass.text.toString().trim())){
            binding.tilPass.requestFocus()
            binding.tilPass.error = getString(R.string.incorrect_format)
            ok = false
        }

        return ok
    }

    private fun validateEmail(email: String): Boolean{
        val pattern = Patterns.EMAIL_ADDRESS
        return !pattern.matcher(email).matches()
    }
    private fun validatePass(cadena: String): Boolean {
        val patron = Pattern.compile("^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{5,10}$")
        return patron.matcher(cadena).find()
    }
}