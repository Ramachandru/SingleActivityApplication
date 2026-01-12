package com.example.singleactivityapp.data.newtwork

import com.example.singleactivityapp.data.Users
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }

    @GET("api/0.8/")
    suspend fun getRandomUserResults(@Query("results") noOfUser: Int): Users?
}