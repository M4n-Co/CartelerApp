package com.example.cartelerapp.home.profile.network

import com.example.cartelerapp.home.profile.response.ResponseProfile
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileService {
    @GET("users?")
    suspend fun getProfileUser(
        @Query("email") email:String
    ):Response<List<ResponseProfile>>
}