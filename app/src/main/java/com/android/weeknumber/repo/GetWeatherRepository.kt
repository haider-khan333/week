package com.android.weeknumber.repo

import com.android.weeknumber.models.WeatherResponseModel
import com.android.weeknumber.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class GetWeatherRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getWeather(lat: Double, lon: Double): Response<WeatherResponseModel> {
        return apiService.getWeather(lat = lat, lon = lon)
    }
}