package com.example.cartelerapp.home.billboard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cartelerapp.home.billboard.GetBillboardRepository
import com.example.cartelerapp.home.billboard.response.BillboardResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BillboardViewModel @Inject constructor(
    private val repository: GetBillboardRepository
):ViewModel(){
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val billboardResult = MutableLiveData<BillboardResponse>()

    fun getBillboard(sectionId : String){
        viewModelScope.launch(Dispatchers.IO){
            loading.postValue(true)

            val response = repository.getBillboard(sectionId)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    loading.value = false
                    billboardResult.value = response.body()
                }else{
                    errorMessage.value = response.message()
                }
            }
        }
    }
}