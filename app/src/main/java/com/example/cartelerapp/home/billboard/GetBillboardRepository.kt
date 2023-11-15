package com.example.cartelerapp.home.billboard

import com.example.cartelerapp.home.billboard.network.GetBillboardService
import javax.inject.Inject

class GetBillboardRepository @Inject constructor(
    private val service: GetBillboardService
){
    suspend fun getBillboard(sectionId : String) = service.getBillboard(sectionId)
}