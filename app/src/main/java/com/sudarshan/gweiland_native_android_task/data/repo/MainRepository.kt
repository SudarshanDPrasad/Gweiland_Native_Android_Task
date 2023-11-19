package com.sudarshan.gweiland_native_android_task.data.repo

import com.sudarshan.gweiland_native_android_task.data.api.Service
import com.sudarshan.gweiland_native_android_task.di.DataState
import com.sudarshan.gweiland_native_android_task.di.parse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val API_KEY = "041359a5-57c6-439d-8f35-74d4b59c0f30"
const val HEADER = "application/json"

class MainRepository @Inject constructor(
    val service: Service
) {
    fun receiveCryptoData() = flow {
        service.receiveCryptoData(
            HttpHeaders = HEADER,
            API_KEY = API_KEY,
            limit = 20
        ).parse {
            emit(DataState.success(it))
        }
    }.catch { error ->
        emit(DataState.error(error.message))
    }

    fun receiveCryptoImageData(
        symbol: String
    ) = flow {
        service.receiveCryptoImageData(
            HttpHeaders = HEADER,
            API_KEY = API_KEY,
            symbol = symbol
        ).parse {
            emit(DataState.success(it))
        }
    }.catch { error ->
        emit(DataState.error(error.message))
    }
}