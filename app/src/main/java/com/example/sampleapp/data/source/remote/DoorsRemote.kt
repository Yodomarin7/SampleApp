package com.example.sampleapp.data.source.remote

import com.example.sampleapp.data.source.helper.NotSuccess
import com.example.sampleapp.di.app.CprogroupOkHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.Serializable

class DoorsRemote(
    @CprogroupOkHttpClient private val ktor: HttpClient
) {
    @Serializable
    data class Model(
        val success: Boolean = false,
        val data: List<Door>? = null,
    )

    @Serializable
    data class Door(
        val name: String? = null,
        val room: String? = null,
        val id: Int,
        val favorites: Boolean = false,
        val snapshot: String? = null
    )

    suspend fun get(): List<Door> {
        val response = ktor.get("/api/rubetek/doors")

        val json = kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
        val model: Model = json.decodeFromString(response.bodyAsText())
        if(!model.success) throw NotSuccess()

        return model.data ?: listOf()
    }
}