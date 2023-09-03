package com.example.sampleapp.di.app

import com.example.sampleapp.data.source.local.camerasLocal.CamerasEntity
import com.example.sampleapp.data.source.local.camerasLocal.CamerasLocal
import com.example.sampleapp.data.source.local.camerasLocal.RoomEntity
import com.example.sampleapp.data.source.local.doorsLocal.DoorEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealmModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                CamerasEntity::class,
                RoomEntity::class,
                DoorEntity::class,
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

}






















