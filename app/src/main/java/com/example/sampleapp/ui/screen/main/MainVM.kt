package com.example.sampleapp.ui.screen.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.data.repo.CamerasREPO
import com.example.sampleapp.data.repo.DoorREPO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val camerasREPO: CamerasREPO,
    private val doorREPO: DoorREPO,
) : ViewModel() {

    private val camerasException = CoroutineExceptionHandler { _, e ->
        setState { copy(camerasError = e, camerasWait = false) }
    }

    private val doorsException = CoroutineExceptionHandler { _, e ->
        setState { copy(doorsError = e, doorsWait = false) }
    }

    private val _state = MutableLiveData(MainHelper.State())
    val state: LiveData<MainHelper.State> = _state
    fun setState(state: MainHelper.State.()-> MainHelper.State) {
        _state.value = _state.value?.state()
    }

    init {
        getCameras()
        getDoors()
    }

    fun updateCameras() {
        setState { copy(camerasError = null) }
        viewModelScope.launch(camerasException) {
            val rooms = camerasREPO.update()
            setState { copy(rooms = rooms) }
        }
    }

    fun updateDoors() {
        setState { copy(doorsError = null) }
        viewModelScope.launch(doorsException) {
            val doors = doorREPO.update()
            setState { copy(doors = doors) }
        }
    }

    fun getCameras() {
        setState { copy(camerasWait = true, camerasError = null) }
        viewModelScope.launch(camerasException) {
            val rooms = camerasREPO.get()
            setState { copy(rooms = rooms, camerasWait = false) }
        }
    }

    fun setCameraFavorite(id: Int) {
        viewModelScope.launch(camerasException) {
            camerasREPO.setFavorite(id)
            getCameras()
        }
    }

    fun getDoors() {
        setState { copy(doorsWait = true, doorsError = null) }
        viewModelScope.launch(doorsException) {
            val doors = doorREPO.get()
            setState { copy(doors = doors, doorsWait = false) }
        }
    }

    fun setDoorFavorite(id: Int) {
        viewModelScope.launch(doorsException) {
            doorREPO.setFavorite(id)
            getDoors()
        }
    }

    fun setDoorName(id: Int, text: String) {
        viewModelScope.launch(doorsException) {
            doorREPO.setName(id, text)
            getDoors()
        }
    }
}

















