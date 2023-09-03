package com.example.sampleapp.data.source.local.doorsLocal

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

class DoorsLocal(
    private val realm: Realm
) {
    suspend fun get(): List<DoorEntity> {
        return realm.query<DoorEntity>().find()
    }

    suspend fun delete() {
        realm.write {
            val qDoors = query<DoorEntity>().find()
            delete(qDoors)
        }
    }

    suspend fun createNew(inputList: List<DoorEntity>) {
        realm.write {
            val qRooms = query<DoorEntity>().find()
            delete(qRooms)

            inputList.forEach { doorEntity->
                copyToRealm(doorEntity)
            }
        }
    }

    suspend fun updateFavority(id: Int) {
        realm.write {
            val qEntity = query<DoorEntity>(query = "id == $0", id).first().find()
            qEntity?.let {
                it.favorites = !it.favorites
            }
        }
    }

    suspend fun updateName(id: Int, text: String) {
        realm.write {
            val qEntity = query<DoorEntity>(query = "id == $0", id).first().find()
            qEntity?.let {
                it.name = text
            }
        }
    }
}