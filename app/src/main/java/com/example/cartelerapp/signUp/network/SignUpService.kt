package com.example.cartelerapp.signUp.network

import com.example.cartelerapp.signUp.request.NewUserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("users")
    suspend fun signUpUser(
        @Body newUser : NewUserRequest
    ):Response<Unit>
}