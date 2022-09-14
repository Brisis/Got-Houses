package com.example.gothouses.retrofit

import com.example.gothouses.models.House
import com.google.gson.JsonObject
import retrofit2.http.*

interface RetroService {
    @GET("/api/houses")
    suspend fun getHouses() : NetworkResponse<List<House>, Error>

    @GET("/api/houses/1")
    suspend fun getHouse() : NetworkResponse<House, Error>
}