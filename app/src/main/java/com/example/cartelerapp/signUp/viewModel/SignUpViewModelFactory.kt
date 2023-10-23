package com.example.cartelerapp.signUp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cartelerapp.signUp.SignUpRepository
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import javax.inject.Inject

class SignUpViewModelFactory @Inject constructor(
    private val repository: SignUpRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(SignUpViewModel::class.java)){
            SignUpViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}