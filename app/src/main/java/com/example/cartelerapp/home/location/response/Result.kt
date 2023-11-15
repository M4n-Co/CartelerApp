package com.example.cartelerapp.home.location.response

data class Result(
    val formatted_address: String,
    val place_id: String,
    val types: List<String>
)