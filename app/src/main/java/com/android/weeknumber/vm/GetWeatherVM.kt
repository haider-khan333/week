package com.android.weeknumber.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weeknumber.models.WeatherResponseModel
import com.android.weeknumber.repo.GetWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class GetWeatherUIState {
    data object Loading : GetWeatherUIState()
    data class Success(val data: WeatherResponseModel) : GetWeatherUIState()
    data class Error(val error: String) : GetWeatherUIState()
}


@HiltViewModel
class GetWeatherVM @Inject constructor(
    private val getWeatherRepository: GetWeatherRepository,
) : ViewModel() {

    private val _weather = MutableStateFlow<GetWeatherUIState>(GetWeatherUIState.Loading)
    val weather: StateFlow<GetWeatherUIState> = _weather.asStateFlow()


    fun getWeather(lat: Double, lon: Double) {

        viewModelScope.launch {
            _weather.value = GetWeatherUIState.Loading
            try {
                val response = getWeatherRepository.getWeather(lat = lat, lon = lon)
                if (response.isSuccessful && response.body() != null) {
                    val apiResponse = response.body()!!
                    Log.d("TAG", "getWeather: Success response:\n\n\n$apiResponse\n\n\n")
                    _weather.value = GetWeatherUIState.Success(apiResponse)
                } else {
                    Log.d(
                        "TAG",
                        "getWeather: Api Error\n\nStatus Code=${response.code()},\nMessage=${response.message()}"
                    )
                    _weather.value =
                        GetWeatherUIState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("TAG", "getWeather: Api Exception\n\nException=${e.message}")
                _weather.value = GetWeatherUIState.Error("Exception: ${e.message}")
            }
        }
    }

}