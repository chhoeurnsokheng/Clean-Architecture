package com.example.myapplication.data.api

import com.google.gson.JsonObject
import retrofit2.http.GET

interface APIs {
    @GET("api/users")
    fun sampleGet():JsonObject
}