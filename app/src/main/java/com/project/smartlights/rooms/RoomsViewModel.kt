package com.project.smartlights.rooms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.smartlights.data.Room

class RoomsViewModel : ViewModel() {

    val rooms: MutableLiveData<MutableList<Room>> = MutableLiveData(
        mutableListOf(Room("Kitchen"), Room("Bathroom"))
    )

    fun addRoom(name: String) {
        rooms.value?.add(Room(name))
    }
}