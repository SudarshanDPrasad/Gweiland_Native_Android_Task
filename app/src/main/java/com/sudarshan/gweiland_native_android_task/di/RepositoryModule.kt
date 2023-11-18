package com.sudarshan.gweiland_native_android_task.di

import com.sudarshan.gweiland_native_android_task.data.api.Service
import com.sudarshan.gweiland_native_android_task.data.repo.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideLoginRepository(
        service: Service
    ): MainRepository =
        MainRepository(service = service)
}

