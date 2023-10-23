package com.example.cartelerapp.home.billboard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cartelerapp.home.billboard.GetBillboardRepository
import javax.inject.Inject

class BillboardViewModelFactory @Inject constructor(
    private val repository: GetBillboardRepository
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BillboardViewModel::class.java)){
            BillboardViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}