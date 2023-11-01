package com.example.cartelerapp.home.location

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cartelerapp.R
import com.example.cartelerapp.databinding.FragmentLocationBinding
import com.example.cartelerapp.home.activity.HomeActivity
import com.example.cartelerapp.login.MainActivity
import com.example.cartelerapp.splash.LoadingActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LocationFragment : Fragment() {

    private var _binding : FragmentLocationBinding? = null
    private val binding get() = _binding!!

    private lateinit var fActivity : HomeActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fActivity = (activity as? HomeActivity)!!
        initUI()

    }
    private fun initUI(){
        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            fActivity.onBackPressed()
        }

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.logout_title)
                .setMessage(R.string.message_logout)
                .setPositiveButton(R.string.accept){_,_ ->
                    logout()
                }
                .setNegativeButton(R.string.cancel){_,_ ->

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
}