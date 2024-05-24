package com.example.room.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SampleEntity::class], version = 1)
abstract class SampleDatabase : RoomDatabase(){
    abstract fun sampleDao(): SampleDao
}