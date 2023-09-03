package com.example.sampleapp.di.vm

import com.example.sampleapp.data.repo.CamerasREPO
import com.example.sampleapp.data.repo.DoorREPO
import com.example.sampleapp.data.source.local.camerasLocal.CamerasLocal
import com.example.sampleapp.data.source.local.doorsLocal.DoorsLocal
import com.example.sampleapp.data.source.remote.CamerasRemote
import com.example.sampleapp.data.source.remote.DoorsRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {

    @Provides
    fun provideCamerasREPO(
        camerasRemote: CamerasRemote,
        camerasLocal: CamerasLocal,
    ): CamerasREPO {
        return CamerasREPO(camerasRemote, camerasLocal)
    }

    @Provides
    fun provideDoorREPO(
        doorsRemote: DoorsRemote,
        doorsLocal: DoorsLocal,
    ): DoorREPO {
        return DoorREPO(doorsRemote, doorsLocal)
    }

}