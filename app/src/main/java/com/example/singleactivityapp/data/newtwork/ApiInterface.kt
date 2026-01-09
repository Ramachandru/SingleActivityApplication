package com.example.singleactivityapp.data.newtwork

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }

    @GET("api/0.8/")
    suspend fun getRandomUserResults(@Query("results") noOfUser: Int): Users?
}