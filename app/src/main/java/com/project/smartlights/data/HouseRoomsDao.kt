package com.project.smartlights.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface HouseRoomsDao {
    @Transaction
    @Query("SELECT * FROM houseroom")
    fun getAll(): Flow<List<HouseRoomWithLights>>

    @Insert
    suspend fun insertRoom(room: HouseRoom)

    @Delete
    suspend fun deleteRoom(room: HouseRoom)

    @Insert
    suspend fun insertLight(light: Light)

    @Query("SELECT * FROM light")
    fun getAllLights(): Flow<List<Light>>

}