package com.project.smartlights.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.project.smartlights.data.DataRepository
import com.project.smartlights.data.HouseRoom

class RoomsViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val rooms = dataRepository.allRooms.asLiveData()

    suspend fun addRoom(room: HouseRoom) {
        dataRepository.insert(room)
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