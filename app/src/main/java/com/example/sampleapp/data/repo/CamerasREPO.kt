package com.example.sampleapp.data.repo

import com.example.sampleapp.data.source.local.camerasLocal.CamerasEntity
import com.example.sampleapp.data.source.local.camerasLocal.CamerasLocal
import com.example.sampleapp.data.source.local.camerasLocal.RoomEntity
import com.example.sampleapp.data.source.remote.CamerasRemote

class CamerasREPO(
    private val camerasRemote: CamerasRemote,
    private val camerasLocal: CamerasLocal,
) {

    suspend fun setFavorite(id: Int) {
        camerasLocal.updateFavority(id)
    }

    suspend fun update(): List<RoomEntity> {
        camerasLocal.delete()
        return get()
    }

    suspend fun get(): List<RoomEntity> {
        val localData = camerasLocal.get()

        if(localData.isEmpty()) {
            val remoteData = camerasRemote.getSortedAsMap()
            val localMap = mutableMapOf<String, List<CamerasEntity>>()

            remoteData.forEach { remoteMap->
                val localList = mutableListOf<CamerasEntity>()

                remoteMap.value.forEach { camerasRemote->
                    val entity = CamerasEntity().apply {
                        name = camerasRemote.name ?: ""
                        id = camerasRemote.id
                        favorites = camerasRemote.favorites
                        rec = camerasRemote.rec
                        snapshot = camerasRemote.snapshot
                    }

                    localList.add(entity)
                }

                localMap.put(remoteMap.key, localList)
            }

            camerasLocal.createNew(localMap)
            return camerasLocal.get()
        }
        else return localData
    }

}