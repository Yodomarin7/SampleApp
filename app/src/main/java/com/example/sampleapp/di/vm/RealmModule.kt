package com.example.sampleapp.di.vm

import com.example.sampleapp.data.source.local.camerasLocal.CamerasLocal
import com.example.sampleapp.data.source.local.doorsLocal.DoorsLocal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.realm.kotlin.Realm

@Module
@InstallIn(ViewModelComponent::class)
object RealmModule {

    @Provides
    fun provideCamerasLocal(
        realm: Realm,
    ): CamerasLocal {
        return CamerasLocal(realm)
    }

    @Provides
    fun provideDoorLocal(
        realm: Realm,
    ): DoorsLocal {
        return DoorsLocal(realm)
    }

}