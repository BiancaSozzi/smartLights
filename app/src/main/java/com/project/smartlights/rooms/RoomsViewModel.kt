package com.project.smartlights.rooms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.project.smartlights.data.DataRepository
import com.project.smartlights.data.HouseRoom
import com.project.smartlights.data.Light
import java.util.UUID

class RoomsViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val roomsWithLights = dataRepository.allRooms.asLiveData()
    val allLights = dataRepository.allLights.asLiveData()
    val addRoomSucceed: MutableLiveData<Boolean> = MutableLiveData(true)

    suspend fun addRoom(room: HouseRoom) {
        if (roomsWithLights.value?.none { it -> it.houseRoom.name.lowercase() == room.name.lowercase()} == true) {
            dataRepository.insertRoom(room)
            addRoomSucceed.postValue(true)
            addLight(room.uid, "${room.name} light 1")
            return
        }
        addRoomSucceed.postValue(false)
    }

    suspend fun addLight(roomId: String, lightName: String) {
        val light = Light(
            UUID.randomUUID().toString(),
            false,
            lightName,
            roomId
        )
        dataRepository.insertLight(light)
    }

    suspend fun deleteRoom(room: HouseRoom) {
        dataRepository.delete(room)
    }
}

class RoomsViewModelFactory(private val repository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoomsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}