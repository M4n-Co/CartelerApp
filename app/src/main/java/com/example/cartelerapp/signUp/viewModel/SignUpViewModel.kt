package com.example.cartelerapp.signUp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cartelerapp.signUp.SignUpRepository
import com.example.cartelerapp.signUp.request.NewUserRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: SignUpRepository
):ViewModel(){
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val signUpResult = MutableLiveData<Boolean>()

    fun signUpUser(newUser : NewUserRequest){
        viewModelScope.launch {
            loading.postValue(true)
            val response = repository.signUpUser(newUser)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    loading.value = false
                    signUpResult.value = true
                }else{
                    errorMessage.value = response.message()
                }
            }
        }
    }
}