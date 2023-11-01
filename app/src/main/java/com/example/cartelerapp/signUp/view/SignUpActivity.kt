package com.example.cartelerapp.signUp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.ActivitySignUpBinding
import com.example.cartelerapp.home.activity.HomeActivity
import com.example.cartelerapp.login.MainActivity
import com.example.cartelerapp.signUp.interfaceAux.SignUpAux
import com.example.cartelerapp.signUp.request.NewUserRequest
import com.example.cartelerapp.signUp.viewModel.SignUpViewModel
import com.example.cartelerapp.splash.LoadingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity(), SignUpAux {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var navController : NavController

    private val viewModel : SignUpViewModel by viewModels()

    private var nuFirebaseId = ""
    private var nuEmail = ""
    private var nuPass = ""
    private var nuName = ""
    private var nuLasteName = ""
    private var nuBirthdate = ""
    private var nuGender = ""
    private var nuPhone = ""
    private var nuWidth = ""
    private var nuHeight = ""
    private var nuAvatar = ""
    private var nuSubscription = "free"
    private var nuTecnologia = "ANDROID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

    }

    private fun initUI() {
        viewModelObserves()
    }

    private fun viewModelObserves(){
        viewModel.signUpResult.observe(this){
            if (it){
                login()
            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.errorMessage.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun login() {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(LoadingActivity.EMAIL_KEY, nuEmail)
        }
        startActivity(intent)
    }

    override fun initRegistration(email : String, pass : String, firebaseId : String) {
        nuFirebaseId = firebaseId
        nuEmail = email
        nuPass = pass

        val navHost = supportFragmentManager.findFragmentById(R.id.fcView) as NavHostFragment
        navController = navHost.navController
        navController.navigate(R.id.action_emailAndPassFragment_to_userInfoFragment)
    }

    override fun finishRegistration(name: String, lastName: String, birthdate: String, gender: String,
        phone: String, weight: String, height: String, avatar: String
    ) {
        nuName = name
        nuLasteName = lastName
        nuBirthdate = birthdate
        nuGender = gender
        nuPhone = phone
        nuWidth = weight
        nuHeight = height
        nuAvatar = avatar

        val newUser = NewUserRequest(
            firebaseId = nuFirebaseId,
            email = nuEmail,
            nombre = nuName,
            apellido = nuLasteName,
            fechaNacimiento = nuBirthdate,
            sexo = nuGender,
            telefono = nuPhone,
            peso = nuWidth,
            username = nuEmail,
            password = nuPass,
            estatura = nuHeight,
            avatar = nuAvatar,
            suscription = nuSubscription,
            tecnologia = nuTecnologia)

        viewModel.signUpUser(newUser)
    }
}