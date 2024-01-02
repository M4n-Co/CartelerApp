package com.example.cartelerapp.home.location.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cartelerapp.home.location.LocationRepository
import com.example.cartelerapp.home.location.response.LocationAndAddressResponse
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
    val locationResponse = MutableLiveData<LocationAndAddressResponse>()

    fun getLocation(lat : String, lon : String){
        viewModelScope.launch(Dispatchers.IO){
            loading.postValue(true)

            val response = repository.getLocation(lat, lon)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    loading.value = false
                    locationResponse.value = response.body()
                }
            }
        }
    }

}