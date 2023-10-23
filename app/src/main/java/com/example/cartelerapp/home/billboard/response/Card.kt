package com.example.cartelerapp.home.billboard.response

import com.google.gson.annotations.SerializedName

data class Card(
    @field:SerializedName("_id") val _id: String,
    @field:SerializedName("descripcion") val descripcion: String,
    @field:SerializedName("entrenamiento") val entrenamiento: Entrenamiento,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("imagen") val imagen: Imagen,
    @field:SerializedName("orientacion") val orientacion: String,
    @field:SerializedName("titulo") val titulo: String,
)