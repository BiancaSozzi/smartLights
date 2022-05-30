package com.project.smartlights.data

import androidx.annotation.WorkerThread


class DataRepository(private val houseRoomsDao: HouseRoomsDao) {

    val allRooms = houseRoomsDao.getAll()
    val allLights = houseRoomsDao.getAllLights()

    @WorkerThread
    suspend fun insertRoom(houseRoom: HouseRoom) {
        houseRoomsDao.insertRoom(houseRoom)
    }

    @WorkerThread
    suspend fun insertLight(light: Light) {
        houseRoomsDao.insertLight(light)
    }

    @WorkerThread
    suspend fun delete(houseRoom: HouseRoom) {
        houseRoomsDao.deleteRoom(houseRoom)
    }
}