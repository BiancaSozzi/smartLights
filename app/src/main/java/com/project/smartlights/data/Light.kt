package com.project.smartlights.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(onDelete = CASCADE, entity = HouseRoom::class, parentColumns = ["uid"], childColumns = ["houseRoom"])
])
data class Light (
    @PrimaryKey val uuid: String,
    var isOn: Boolean = false,
    var name: String,
    var houseRoom: String
)