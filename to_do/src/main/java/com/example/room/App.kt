package com.example.room

import android.app.Application
import com.example.room.util.RoomUtil

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        RoomUtil.createDb(this)
    }

}