package com.example.cartelerapp.home.location

import com.example.cartelerapp.home.location.network.GetLocationService
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val service: GetLocationService
) {
    suspend fun getLocation(
        lat : String,
        lon : String
    )=service.getLocation(lat, lon)
}