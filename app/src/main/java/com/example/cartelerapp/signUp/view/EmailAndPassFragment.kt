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
            binding.tilPass.error = null
        }
    }

    private fun initListeners() {
        binding.btnStartRegistration.setOnClickListener{
            if (valida()){
                val email = binding.etEmail.text.toString()
                val pass = binding.etPass.text.toString()

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
        }else if (validatePass(binding.etPass.text.toString().trim())){
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
        return !patron.matcher(cadena).find()
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