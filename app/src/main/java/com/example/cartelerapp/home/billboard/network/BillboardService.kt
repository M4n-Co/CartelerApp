package com.example.cartelerapp.home.billboard.network

import com.example.cartelerapp.home.billboard.response.BillboardResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BillboardService {
    @GET("blocks/cards/{sectionId}")
    suspend fun billBoard(
        @Path("sectionId") sectionId : String
    ) : Response<BillboardResponse>
}