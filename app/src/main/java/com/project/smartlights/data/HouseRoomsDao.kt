package com.project.smartlights.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HouseRoomsDao {
    @Query("SELECT * FROM houseroom")
    fun getAll(): Flow<List<HouseRoom>>

    @Insert
    suspend fun insertRoom(room: HouseRoom)

    @Delete
    suspend fun deleteRoom(room: HouseRoom)

}