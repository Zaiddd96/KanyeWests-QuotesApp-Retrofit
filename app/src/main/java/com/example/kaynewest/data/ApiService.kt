package com.example.kaynewest.data

import com.example.kaynewest.model.Quotes
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun getQuote():Quotes
}