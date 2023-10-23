package com.example.cartelerapp.home.billboard.response

import com.google.gson.annotations.SerializedName

data class Entrenamiento(
    @field:SerializedName("_id") val _id: String,
    @field:SerializedName("descripcion") val descripcion: String,
    @field:SerializedName("descripcionCorta") val descripcionCorta: String,
    @field:SerializedName("grupoMuscular") val grupoMuscular: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("instrucciones") val instrucciones: String,
    @field:SerializedName("mediaSource") val mediaSource: String,
    @field:SerializedName("nivel") val nivel: String,
    @field:SerializedName("nombre") val nombre: String,
    @field:SerializedName("numeroEjercicios") val numeroEjercicios: Int,
    @field:SerializedName("numeroSeries") val numeroSeries: Int,
    @field:SerializedName("objetivo") val objetivo: String
)