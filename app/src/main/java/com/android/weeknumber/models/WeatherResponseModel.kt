package com.android.weeknumber.models

import com.google.gson.annotations.SerializedName

data class WeatherResponseModel(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @SerializedName("timezone_offset") val timezoneOffset: Int,
    val current: CurrentWeather,
    val minutely: List<Minutely>,
    val hourly: List<Hourly>,
    val daily: List<Daily>
)

data class CurrentWeather(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("dew_point") val dewPoint: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_deg") val windDeg: Int,
    val weather: List<Weather>
)

data class Minutely(
    val dt: Long,
    val precipitation: Double
)

data class Hourly(
    val dt: Long,
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("dew_point") val dewPoint: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_deg") val windDeg: Int,
    @SerializedName("wind_gust") val windGust: Double,
    val weather: List<Weather>,
    val pop: Double
)

data class Daily(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    @SerializedName("moon_phase") val moonPhase: Double,
    val summary: String,
    val temp: Temperature,
    @SerializedName("feels_like") val feelsLike: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("dew_point") val dewPoint: Double,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_deg") val windDeg: Int,
    @SerializedName("wind_gust") val windGust: Double,
    val weather: List<Weather>,
    val clouds: Int,
    val pop: Double,
    val uvi: Double
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class FeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)
