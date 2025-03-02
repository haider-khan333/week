package com.android.weeknumber.di

import com.android.weeknumber.network.ApiClient
import com.android.weeknumber.network.ApiService
import com.android.weeknumber.repo.GetWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiClient.apiService
    }

    // provide the weather repository
    @Provides
    @Singleton
    fun provideGetWeatherRepository(apiService: ApiService): GetWeatherRepository {
        return GetWeatherRepository(apiService = apiService)
    }
}