package com.example.sampleapp.data.source.remote

import com.example.sampleapp.data.source.helper.NotSuccess
import com.example.sampleapp.di.app.CprogroupOkHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.Serializable

class CamerasRemote(
    @CprogroupOkHttpClient private val ktor: HttpClient
) {

    @Serializable
    data class Model(
        val success: Boolean = false,
        val data: Data? = null,
    )

    @Serializable
    data class Data(
        val room: List<String>? = null,
        val cameras: List<Camera>? = null,
    )

    @Serializable
    data class Camera(
        val name: String? = null,
        val room: String?,
        val id: Int,
        val favorites: Boolean = false,
        val rec: Boolean = false,
        val snapshot: String? = null
    )

    suspend fun getSortedAsMap(): Map<String, List<Camera>> {
        val response = ktor.get("/api/rubetek/cameras")

        val json = kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
        val model: Model = json.decodeFromString(response.bodyAsText())
        if(!model.success) throw NotSuccess()

        val map = mutableMapOf<String, List<Camera>>()
        model.data?.room?.forEach { room->
            val list = model.data.cameras?.filter { it.room == room }
            list?.let { map.put(room, it) }
        }

        return  map
    }

}