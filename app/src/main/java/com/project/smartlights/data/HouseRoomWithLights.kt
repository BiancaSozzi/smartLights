package com.project.smartlights.data

import androidx.room.Embedded
import androidx.room.Relation

data class HouseRoomWithLights (
    @Embedded
    val houseRoom: HouseRoom,
    @Relation(
        parentColumn = "uid",
        entityColumn = "houseRoom"
    )
    val lights: List<Light>
)