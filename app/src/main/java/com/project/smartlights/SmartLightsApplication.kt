package com.project.smartlights

import android.app.Application
import com.project.smartlights.data.AppDatabase
import com.project.smartlights.data.DataRepository

class SmartLightsApplication: Application() {

    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { DataRepository(database.houseRoomsDao()) }
}