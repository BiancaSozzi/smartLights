package com.project.smartlights.data

import androidx.annotation.WorkerThread


class DataRepository(private val houseRoomsDao: HouseRoomsDao) {

    val allRooms = houseRoomsDao.getAll()

    @WorkerThread
    suspend fun insert(houseRoom: HouseRoom) {
        houseRoomsDao.insertRoom(houseRoom)
    }

    @WorkerThread
    suspend fun delete(houseRoom: HouseRoom) {
        houseRoomsDao.deleteRoom(houseRoom)
    }
}