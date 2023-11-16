package com.example.cartelerapp.signUp.view

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.FragmentUserInfoBinding
import com.example.cartelerapp.home.activity.HomeActivity
import com.example.cartelerapp.signUp.request.NewUserRequest
import com.example.cartelerapp.signUp.viewModel.SignUpViewModel
import com.example.cartelerapp.splash.LoadingActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    private var _binding : FragmentUserInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var fActivity: SignUpActivity

    private val viewModel : SignUpViewModel by viewModels()

    private val args : UserInfoFragmentArgs by navArgs()

    private val nuFirebaseId : String get() = args.EmailPassFirebaseID[2]
    private val nuEmail : String get() = args.EmailPassFirebaseID[0]
    private val nuPass : String get() = args.EmailPassFirebaseID[1]
    private var nuBirthdate = ""
    private var nuGender = ""
    private var nuSubscription = "free"
    private var nuTecnologia = "ANDROID"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
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
        viewModelObserves()
    }

    private fun viewModelObserves(){
        viewModel.signUpResult.observe(viewLifecycleOwner){
            if (it){
                login()
            }else{
                Toast.makeText(requireContext(), R.string.signup_error, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner){
            binding.pbSignUpUser.isVisible = it
        }

    }

    private fun login() {

        val sharedPreferences = requireActivity().getSharedPreferences(LoadingActivity.SHARED_KEY, Context.MODE_PRIVATE)
        val editShared = sharedPreferences.edit()
        editShared.apply {
            putString(LoadingActivity.EMAIL_KEY, nuEmail)
        }.apply()

        val intent = Intent(requireContext(), HomeActivity::class.java).apply {
            putExtra(LoadingActivity.EMAIL_KEY, nuEmail)
        }
        startActivity(intent)
        fActivity.finish()
    }

    private fun initTextChangedListener() {
        binding.etName.doAfterTextChanged {
            binding.tilName.error = null
        }
        binding.etLastName.doAfterTextChanged {
            binding.tilLastname.error = null
        }
        binding.etBirthdate.doAfterTextChanged {
            binding.tilBirthdate.error = null
        }
        binding.etPhone.doAfterTextChanged {
            binding.tilPhone.error = null
        }
        binding.etWeight.doAfterTextChanged {
            binding.tilWeight.error = null
        }
        binding.etHeight.doAfterTextChanged {
            binding.tilHeight.error = null
        }
    }

    private fun initListeners() {
        binding.btnDatePicker.setOnClickListener {
            showDataPicker()
        }

        binding.btnOther.setOnClickListener {
            setButtonSelection(true, false, false)
            nuGender = "otro"
        }

        binding.btnWoman.setOnClickListener {
            setButtonSelection(false, true, false)
            nuGender = "femenino"
        }

        binding.btnMen.setOnClickListener {
            setButtonSelection(false, false, true)
            nuGender = "masculino"
        }

        binding.btnOther.performClick()

        binding.btnFinishSignUp.setOnClickListener {
            if (valida()){

                finishRegistration()

            }
        }
    }

    private fun setButtonSelection(sport: Boolean, gym: Boolean, yoga: Boolean) {
        if (sport){
            binding.btnOther.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.secondary))
            binding.btnOther.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            binding.btnWoman.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            binding.btnWoman.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))

            binding.btnMen.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            binding.btnMen.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))
        }
        if (gym){
            binding.btnOther.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            binding.btnOther.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))

            binding.btnWoman.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.secondary))
            binding.btnWoman.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            binding.btnMen.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            binding.btnMen.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))
        }
        if (yoga){
            binding.btnOther.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            binding.btnOther.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))

            binding.btnWoman.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            binding.btnWoman.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))

            binding.btnMen.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.secondary))
            binding.btnMen.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }

    }

    private fun showDataPicker() {
        val currentDate = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            currentDate.set(Calendar.YEAR, year)
            currentDate.set(Calendar.MONTH, month)
            currentDate.set(Calendar.DAY_OF_MONTH, day)
            upDateTextBirthdate(currentDate)
        }
        DatePickerDialog(requireContext(), datePicker, 1990, Calendar.MONTH, Calendar.DAY_OF_MONTH).show()
    }

    private fun upDateTextBirthdate(currentDate: Calendar) {
        val format = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(format, Locale.UK)
        binding.etBirthdate.setText(sdf.format(currentDate.time))


        val formatToBcck = "yyyy-MM-dd"
        val sdfToback = SimpleDateFormat(formatToBcck, Locale.UK)
        nuBirthdate = sdfToback.format(currentDate.time)
    }

    private fun finishRegistration() {

        val name = binding.etName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val weight = binding.etWeight.text.toString().trim()
        val height = binding.etHeight.text.toString().trim()

        val newUser = NewUserRequest(
            firebaseId = nuFirebaseId,
            email = nuEmail,
            nombre = name,
            apellido = lastName,
            fechaNacimiento = nuBirthdate,
            sexo = nuGender,
            telefono = phone,
            peso = weight,
            username = nuEmail,
            password = nuPass,
            estatura = height,
            avatar = "Avatar",
            suscription = nuSubscription,
            tecnologia = nuTecnologia)

        viewModel.signUpUser(newUser)
    }

    private fun valida():Boolean{
        var ok = true

        if(binding.etName.text.toString().trim().isEmpty()){
            binding.tilName.requestFocus()
            binding.tilName.error = getString(R.string.required_field)
            ok = false
        }

        if(binding.etLastName.text.toString().trim().isEmpty()) {
            binding.tilLastname.requestFocus()
            binding.tilLastname.error = getString(R.string.required_field)
            ok = false
        }

        if(binding.etBirthdate.text.toString().trim().isEmpty()) {
            binding.tilBirthdate.requestFocus()
            binding.tilBirthdate.error = getString(R.string.required_field)
            ok = false
        }

        if(binding.etPhone.text.toString().trim().isEmpty()) {
            binding.tilPhone.requestFocus()
            binding.tilPhone.error = getString(R.string.required_field)
            ok = false
        }

        if(binding.etWeight.text.toString().trim().isEmpty()) {
            binding.tilWeight.requestFocus()
            binding.tilWeight.error = getString(R.string.required_field)
            ok = false
        }

        if(binding.etHeight.text.toString().trim().isEmpty()) {
            binding.tilHeight.requestFocus()
            binding.tilHeight.error = getString(R.string.required_field)
            ok = false
        }

        return ok
    }

}