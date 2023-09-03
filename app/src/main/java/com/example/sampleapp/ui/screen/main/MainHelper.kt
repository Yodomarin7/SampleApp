package com.example.sampleapp.ui.screen.main

import com.example.sampleapp.data.source.local.camerasLocal.RoomEntity
import com.example.sampleapp.data.source.local.doorsLocal.DoorEntity

class MainHelper {

    data class State(
        val camerasError: Throwable? = null,
        val camerasWait: Boolean = true,
        val rooms: List<RoomEntity> = listOf(),

        val doorsError: Throwable? = null,
        val doorsWait: Boolean = true,
        val doors: List<DoorEntity> = listOf(),
    )

}