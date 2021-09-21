package com.example.myapplication.data.repository

import com.example.myapplication.data.api.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllMovies() = retrofitService.getAllMovies()
}
