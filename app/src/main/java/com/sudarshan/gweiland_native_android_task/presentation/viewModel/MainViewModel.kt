package com.sudarshan.gweiland_native_android_task.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudarshan.gweiland_native_android_task.data.repo.MainRepository
import com.sudarshan.gweiland_native_android_task.data.response.model.image.ImageResponseDTO
import com.sudarshan.gweiland_native_android_task.data.response.model.cryptoList.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {
    private var error by mutableStateOf("")
    var cryptoResponse = mutableStateListOf<Data>()
    var cryptoImage = mutableStateListOf<ImageResponseDTO>()

    init {
        sendData()
    }

    private fun sendData() {
        cryptoResponse.clear()
        repository.receiveCryptoData().onEach { dataState ->
            dataState.data?.data?.let { response ->
                response.forEach {
                    cryptoResponse.add(it)
                    repository.receiveCryptoImageData(it.symbol).onEach { imageResponseState ->
                        imageResponseState.data?.let { imageResponse ->
                            cryptoImage.add(imageResponse)
                        }
                    }.launchIn(viewModelScope)
                }
                error = ""
            }
            dataState.error?.run {
                error = this
            }
        }.launchIn(viewModelScope)
    }
}