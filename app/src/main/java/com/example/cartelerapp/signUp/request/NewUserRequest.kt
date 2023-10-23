package com.example.cartelerapp.signUp.request

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class NewUserRequest(
    @field:SerializedName("firebaseId") val firebaseId : String,
    @field:SerializedName("email") val email : String,
    @field:SerializedName("nombre") val nombre : String,
    @field:SerializedName("apellido") val apellido : String,
    @field:SerializedName("fechaNacimiento") val fechaNacimiento : String,
    @field:SerializedName("sexo") val sexo : String,
    @field:SerializedName("telefono") val telefono : String,
    @field:SerializedName("peso") val peso : String,
    @field:SerializedName("username") val username : String,
    @field:SerializedName("password") val password : String,
    @field:SerializedName("estatura") val estatura : String,
    @field:SerializedName("avatar") val avatar : String,
    @field:SerializedName("suscription") val suscription : String,
    @field:SerializedName("tecnologia") val tecnologia : String
)