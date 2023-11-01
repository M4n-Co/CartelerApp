package com.example.cartelerapp.home.profile.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cartelerapp.home.profile.ProfileRepository
import com.example.cartelerapp.home.profile.response.ResponseProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository : ProfileRepository
) : ViewModel(){
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val profileResponse = MutableLiveData<ResponseProfile>()

    fun getProfileUser(email : String){
        viewModelScope.launch(Dispatchers.IO){
            loading.postValue(true)

            val response = repository.getProfileUser(email)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    loading.value = false
                    profileResponse.value = response.body()?.get(0)
                }else{
                    loading.value = false
                    errorMessage.value = response.message()
                }
            }
        }
    }
}