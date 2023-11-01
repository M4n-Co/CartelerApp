package com.example.cartelerapp.signUp.view

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.FragmentUserInfoBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class UserInfoFragment : Fragment() {

    private var _binding : FragmentUserInfoBinding? = null
    private val binding get() = _binding!!
    lateinit var fActivity: SignUpActivity

    private var date = ""
    private var gender = "otro"

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
        binding.etAvatar.doAfterTextChanged {
            binding.tilAvatar.error = null
        }
    }

    private fun initListeners() {
        binding.btnDatePicker.setOnClickListener {
            showDataPicker()
        }

        binding.btnOther.setOnClickListener {
            setButtonSelection(true, false, false)
            gender = "otro"
        }

        binding.btnWoman.setOnClickListener {
            setButtonSelection(false, true, false)
            gender = "femenino"
        }

        binding.btnMen.setOnClickListener {
            setButtonSelection(false, false, true)
            gender = "masculino"
        }

        binding.btnOther.performClick()

        binding.btnFinishSignUp.setOnClickListener {
            if (valida()){

                binding.pbSignUpUser.isVisible = true
                val name = binding.etName.text.toString().trim()
                val lastName = binding.etLastName.text.toString().trim()
                val phone = binding.etPhone.text.toString().trim()
                val weight = binding.etWeight.text.toString().trim()
                val height = binding.etHeight.text.toString().trim()
                val avatar = binding.etAvatar.text.toString().trim()
                fActivity.finishRegistration(name, lastName, date, gender, phone, weight, height, avatar)

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
        date = sdfToback.format(currentDate.time)
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

        if(binding.etAvatar.text.toString().trim().isEmpty()) {
            binding.tilAvatar.requestFocus()
            binding.tilAvatar.error = getString(R.string.required_field)
            ok = false
        }

        return ok
    }

}