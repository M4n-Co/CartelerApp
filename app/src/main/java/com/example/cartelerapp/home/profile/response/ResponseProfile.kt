package com.example.cartelerapp.home.profile.response

import com.google.gson.annotations.SerializedName

data class ResponseProfile(
    @field:SerializedName("apellido") val apellido: String,
    @field:SerializedName("avatar") val avatar: String,
    @field:SerializedName("email") val email: String,
    @field:SerializedName("estatura") val estatura: Double,
    @field:SerializedName("firebaseId") val firebaseId: String,
    @field:SerializedName("nombre") val nombre: String,
    @field:SerializedName("peso") val peso: Int,
    @field:SerializedName("sexo") val sexo: String,
    @field:SerializedName("suscription") val suscription: String,
    @field:SerializedName("tecnologia") val tecnologia: String,
    @field:SerializedName("telefono") val telefono: String,
    @field:SerializedName("username") val username: String
)