package com.example.cartelerapp.home.profile.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cartelerapp.home.profile.ProfileRepository
import javax.inject.Inject

class ProfileViewModelFactory @Inject constructor(
    private val repository: ProfileRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            ProfileViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}