package com.example.cartelerapp.home.location.response

data class LocationAndAddressResponse(
    val address: Address,
    val addresstype: String,
    val boundingbox: List<String>,
    val display_name: String,
    val importance: Double,
    val lat: String,
    val licence: String,
    val lon: String,
    val name: String,
    val osm_id: Int,
    val osm_type: String,
    val place_id: Int,
    val place_rank: Int,
    val type: String
)