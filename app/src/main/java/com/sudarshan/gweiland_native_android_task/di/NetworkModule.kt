package com.sudarshan.gweiland_native_android_task.di

import com.sudarshan.gweiland_native_android_task.data.api.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
    ): Service {
        val builder = Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return builder.create(Service::class.java)
    }
}