package com.example.sampleapp.data.repo

import com.example.sampleapp.data.source.local.doorsLocal.DoorEntity
import com.example.sampleapp.data.source.local.doorsLocal.DoorsLocal
import com.example.sampleapp.data.source.remote.DoorsRemote

class DoorREPO(
    private val doorsRemote: DoorsRemote,
    private val doorsLocal: DoorsLocal,
) {

    suspend fun setFavorite(id: Int) {
        doorsLocal.updateFavority(id)
    }

    suspend fun setName(id: Int, text: String) {
        doorsLocal.updateName(id, text)
    }

    suspend fun update(): List<DoorEntity> {
        doorsLocal.delete()
        return get()
    }

    suspend fun get(): List<DoorEntity> {
        val localData = doorsLocal.get()

        if(localData.isEmpty()) {
            val remoteData = doorsRemote.get()

            val localList = mutableListOf<DoorEntity>()
            remoteData.forEach { door->
                val entity = DoorEntity().apply {
                    name = door.name ?: ""
                    id = door.id
                    favorites = door.favorites
                    snapshot = door.snapshot
                }

                localList.add(entity)
            }

            doorsLocal.createNew(localList)
            return doorsLocal.get()
        }
        else return localData
    }

}