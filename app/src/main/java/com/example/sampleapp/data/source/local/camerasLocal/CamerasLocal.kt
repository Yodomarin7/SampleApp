package com.example.sampleapp.data.source.local.camerasLocal

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

class CamerasLocal(
    private val realm: Realm
) {
    suspend fun get(): List<RoomEntity> {
        return realm.query<RoomEntity>().find()
    }

    suspend fun delete() {
        realm.write {
            val qRooms = query<RoomEntity>().find()
            delete(qRooms)

            val qCameras = query<CamerasEntity>().find()
            delete(qCameras)
        }
    }

    suspend fun createNew(inputMap: Map<String, List<CamerasEntity>>) {
        realm.write {
            val qRooms = query<RoomEntity>().find()
            delete(qRooms)

            val qCameras = query<CamerasEntity>().find()
            delete(qCameras)

            inputMap.forEach { map->
                val roomEntity = RoomEntity().apply { name = map.key }
                map.value.forEach { camerasEntity->
                    roomEntity.cameras.add(camerasEntity)
                }

                copyToRealm(roomEntity)
            }
        }
    }

    suspend fun updateFavority(id: Int) {
        realm.write {
            val qEntity = query<CamerasEntity>(query = "id == $0", id).first().find()
            qEntity?.let {
                it.favorites = !it.favorites
            }
        }
    }
}
















