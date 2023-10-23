package com.example.cartelerapp.signUp

import com.example.cartelerapp.signUp.network.SignUpService
import com.example.cartelerapp.signUp.request.NewUserRequest
import javax.inject.Inject

class SignUpRepository @Inject constructor(
    private val signUpService: SignUpService
) {
    suspend fun signUpUser(newUser : NewUserRequest) = signUpService.signUpUser(newUser)
}