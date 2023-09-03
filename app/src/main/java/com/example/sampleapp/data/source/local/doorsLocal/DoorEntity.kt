package com.example.sampleapp.data.source.local.doorsLocal

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class DoorEntity : RealmObject {
    @PrimaryKey
    var _id: ObjectId = BsonObjectId()
    var name: String = ""
    var id: Int = 0
    var favorites: Boolean = false
    var snapshot: String? = null
}