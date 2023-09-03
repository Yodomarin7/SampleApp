package com.example.sampleapp.data.source.local.camerasLocal

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class RoomEntity : RealmObject {
    @PrimaryKey
    var _id: ObjectId = BsonObjectId()

    @Index
    var name: String = ""

    var cameras: RealmList<CamerasEntity> = realmListOf()
}