package com.example.cartelerapp.home.location.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cartelerapp.home.billboard.response.BillboardResponse
import com.example.cartelerapp.home.location.LocationRepository
import com.example.cartelerapp.home.location.response.LocationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository
):ViewModel(){
    val loading = MutableLiveData<Boolean>()
    val locationResponse = MutableLiveData<LocationResponse>()

    fun getLocation(latLng : String, apiKey : String){
        viewModelScope.launch(Dispatchers.IO){
            loading.postValue(true)

            val response = repository.getLocation(latLng, apiKey)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    loading.value = false
                    locationResponse.value = response.body()
                }
            }
        }
    }
}