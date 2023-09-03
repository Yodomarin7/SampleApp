package com.example.sampleapp.di.app

import com.example.sampleapp.data.source.remote.CamerasRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.defaultRequest
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CprogroupOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    @Singleton
    @CprogroupOkHttpClient
    @Provides
    fun provideClientCprogroup(): HttpClient {
        val client = HttpClient(OkHttp) {
            engine {
                addInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                })
            }
            defaultRequest {
                url("http://cars.cprogroup.ru")
            }
        }
        return client
    }

}