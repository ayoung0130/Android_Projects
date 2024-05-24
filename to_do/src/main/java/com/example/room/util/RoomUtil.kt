package com.example.room.util

import android.content.Context
import androidx.room.Room
import com.example.room.room.SampleDao
import com.example.room.room.SampleDatabase

object RoomUtil {

    private lateinit var db: SampleDatabase

    fun createDb(context: Context) {
        db = Room.databaseBuilder(
            context = context,
            klass = SampleDatabase::class.java,
            "sample"
        ).build()
    }

    fun getSampleDao() : SampleDao {
        return db.sampleDao()
    }

}