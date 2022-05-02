package com.project.smartlights.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HouseRoom (
    @PrimaryKey val uid: String,
    var name: String,
    var lightOn: Boolean = false
)
