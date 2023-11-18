package com.sudarshan.gweiland_native_android_task.di

import retrofit2.HttpException
import retrofit2.Response


data class DataState<out T>(
    val data: T? = null,
    val error: String? = null,
    val loading: Boolean = false,
) {
    companion object {
        fun <T> success(data: T): DataState<T> = DataState(data = data)

        fun <T> error(message: String?, data: T? = null): DataState<T> =
            DataState(error = message, data = data)

        fun <T> loading(data: T? = null): DataState<T> = DataState(data = data, loading = true)
    }
}

@Suppress("UNCHECKED_CAST")
suspend fun <T> Response<T>.parse(
    onSuccess: suspend (T) -> Unit
) {
    if (isSuccessful) {
        val responseBody = body()
        if (responseBody == null) {
            onSuccess(Unit as T)
        } else {
            onSuccess(responseBody)
        }
    } else {
        throw HttpException(this)
    }
}