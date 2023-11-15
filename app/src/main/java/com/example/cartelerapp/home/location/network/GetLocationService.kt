package com.example.cartelerapp.home.location.network

import com.example.cartelerapp.home.location.response.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GetLocationService {
    @GET("maps/api/geocode/json?latlng=19.98278,-101.76069&location_type=ROOFTOP&result_type=street_address&key=AIzaSyBrjWg1CDm76swXcG-jPupPvMfAoxFyTbw")
    suspend fun getLocation(
    ):Response<LocationResponse>
}