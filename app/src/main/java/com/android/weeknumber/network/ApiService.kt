package com.android.weeknumber.network

import com.android.weeknumber.models.WeatherResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("onecall")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String? = "3fb0662c78e68ef55a3ef19f5286b211"
    ): Response<WeatherResponseModel>

}