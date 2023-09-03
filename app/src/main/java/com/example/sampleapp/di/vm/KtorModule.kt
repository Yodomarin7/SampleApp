package com.example.sampleapp.di.vm

import com.example.sampleapp.data.source.remote.CamerasRemote
import com.example.sampleapp.data.source.remote.DoorsRemote
import com.example.sampleapp.di.app.CprogroupOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(ViewModelComponent::class)
object KtorModule {

    @Provides
    fun provideCamerasRemote(
        @CprogroupOkHttpClient ktor: HttpClient,
    ): CamerasRemote {
        return CamerasRemote(ktor)
    }

    @Provides
    fun provideDoorsRemote(
        @CprogroupOkHttpClient ktor: HttpClient,
    ): DoorsRemote {
        return DoorsRemote(ktor)
    }

}