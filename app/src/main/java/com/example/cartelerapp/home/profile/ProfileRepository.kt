package com.example.cartelerapp.home.profile

import com.example.cartelerapp.home.profile.network.ProfileService
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val service : ProfileService
) {
    suspend fun getProfileUser (email:String) = service.getProfileUser(email)
}