package com.example.cartelerapp.home.billboard.response

import com.google.gson.annotations.SerializedName
data class BillboardResponse(
    @field:SerializedName("_id") val _id: String,
    @field:SerializedName("cards") val cards: List<Card>,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("resumen") val resumen: String,
    @field:SerializedName("titulo") val titulo: String,
)