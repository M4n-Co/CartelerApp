package com.example.cartelerapp.signUp.view

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.FragmentEmailAndPassBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class EmailAndPassFragment : Fragment() {

    private var _biding : FragmentEmailAndPassBinding? = null
    private val binding get() = _biding!!

    private lateinit var fActivity: SignUpActivity
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _biding = FragmentEmailAndPassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fActivity = (activity as? SignUpActivity)!!
        initUI()

    }

    private fun initUI() {
        initListeners()
        initTextChangedListener()
    }

    private fun initTextChangedListener() {
        binding.etEmail.doAfterTextChanged {
            binding.tilEmail.error = null
        }
        binding.etPass.doAfterTextChanged {
            val pass = binding.etPass.text.toString()

            if (checkPass(pass) && binding.etEmail.text.toString().trim().isNotEmpty()){
                binding.btnStartRegistration.isEnabled = true
            }
        }
    }

    private fun checkPass(pass: String): Boolean {
        var ok = true

        if (checkUppercase(pass)){
            binding.ivCheckUppercase.setImageResource(R.drawable.ic_check)
        }else{
            binding.ivCheckUppercase.setImageResource(R.drawable.ic_waiting_check)
            ok = false
        }

        if (checkNumber(pass)){
            binding.ivCheckNumber.setImageResource(R.drawable.ic_check)
        }else{
            binding.ivCheckNumber.setImageResource(R.drawable.ic_waiting_check)
            ok = false
        }

        if (pass.length >= 5){
            binding.ivCheckLong.setImageResource(R.drawable.ic_check)
        }else{
            binding.ivCheckLong.setImageResource(R.drawable.ic_waiting_check)
            ok = false
        }

        if (checkSpacialCharacters(pass)){
            binding.ivCheckCharacters.setImageResource(R.drawable.ic_check)
        }else{
            binding.ivCheckCharacters.setImageResource(R.drawable.ic_waiting_check)
            ok = false
        }

        return ok
    }

    private fun initListeners() {
        binding.btnStartRegistration.setOnClickListener{
            if (valida()){
                val email = binding.etEmail.text.toString().trim()
                val pass = binding.etPass.text.toString().trim()

                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = auth.currentUser
                        val firebaseId = user?.uid

                        fActivity.initRegistration(email, pass, firebaseId!!)
                    } else {
                        showError()
                    }
                }
            }
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
        return !pattern.matcher(email).matches()
    }
    private fun checkUppercase(pass: String): Boolean {
        val patron = Pattern.compile("[A-Z]")
        return patron.matcher(pass).find()
    }
    private fun checkNumber(pass: String): Boolean {
        val patron = Pattern.compile("[0-9]")
        return patron.matcher(pass).find()
    }
    private fun checkSpacialCharacters(pass: String): Boolean {
        val patron = Pattern.compile("[@\$?¡\\-_#.*]")
        return patron.matcher(pass).find()
    }

    private fun showError() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.error))
        builder.setMessage(getString(R.string.mensaje))
        builder.setPositiveButton(getString(R.string.aceptar),null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}