package com.sudarshan.gweiland_native_android_task.data.api

import com.sudarshan.gweiland_native_android_task.data.response.model.image.ImageResponseDTO
import com.sudarshan.gweiland_native_android_task.data.response.model.cryptoList.CryptoResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Service {

    @GET("v1/cryptocurrency/listings/latest")
    suspend fun receiveCryptoData(
        @Header("HttpHeaders.ACCEPT") HttpHeaders: String,
        @Header("X-CMC_PRO_API_KEY") API_KEY: String,
        @Query("limit") limit : Int
    ): Response<CryptoResponseDTO>

    @GET("v2/cryptocurrency/info")
    suspend fun receiveCryptoImageData(
        @Header("HttpHeaders.ACCEPT") HttpHeaders: String,
        @Header("X-CMC_PRO_API_KEY") API_KEY: String,
        @Query("symbol") symbol: String
    ): Response<ImageResponseDTO>
}