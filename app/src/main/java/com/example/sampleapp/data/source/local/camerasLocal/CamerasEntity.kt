package com.example.sampleapp.data.source.local.camerasLocal

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class CamerasEntity : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var id: Int = 0
    var favorites: Boolean = false
    var rec: Boolean = false
    var snapshot: String? = null
}