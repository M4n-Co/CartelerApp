package com.example.cartelerapp.home.profile.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.FragmentProfileBinding
import com.example.cartelerapp.home.activity.HomeActivity
import com.example.cartelerapp.home.profile.response.ResponseProfile
import com.example.cartelerapp.home.profile.viewModel.ProfileViewModel
import com.example.cartelerapp.login.MainActivity
import com.example.cartelerapp.splash.LoadingActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ProfileViewModel by viewModels()
    private lateinit var fActivity : HomeActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fActivity = (activity as? HomeActivity)!!
        initUI()
    }

    private fun initUI() {
        initListeners()
        viewModelRequest()
        viewModelObserves()
    }

    private fun initListeners() {
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.logout_title)
                .setMessage(R.string.message_logout)
                .setPositiveButton(R.string.accept){ _, _ ->
                    logout()
                }
                .setNegativeButton(R.string.cancel){ _, _ ->

                }.show()
        }
    }

    private fun logout() {
        val sharedPreferences = requireActivity().getSharedPreferences(LoadingActivity.SHARED_KEY, Context.MODE_PRIVATE)
        val editShared = sharedPreferences.edit()
        editShared.apply {
            remove(LoadingActivity.EMAIL_KEY)
        }.apply()

        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        fActivity.finish()
    }

    private fun viewModelRequest() {
        val email = fActivity.uEmail
        if (email.isNotEmpty()){
            viewModel.getProfileUser(email)
        }
    }

    private fun viewModelObserves() {
        viewModel.profileResponse.observe(viewLifecycleOwner){info ->
            if (info != null){
                setInfo(info)
            }
        }
        viewModel.loading.observe(viewLifecycleOwner){
            binding.pbInfo.isVisible = it
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setInfo(info: ResponseProfile) {
        with(binding){
            val name = "${info.nombre} ${info.apellido}"
            tvName.text = name
            tvUserEmail.text = info.email
            tvHeightUser.text = info.estatura.toString()
            tvWidthUser.text= info.peso.toString()
        }
    }
}