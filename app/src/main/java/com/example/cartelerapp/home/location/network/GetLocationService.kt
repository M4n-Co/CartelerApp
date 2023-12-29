package com.example.cartelerapp.home.location.network

import com.example.cartelerapp.home.location.response.LocationAndAddressResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetLocationService {
    @GET("reverse?format=json")
    suspend fun getLocation(
        @Query("lat") lat : String,
        @Query("lon") lon : String
    ):Response<LocationAndAddressResponse>
}