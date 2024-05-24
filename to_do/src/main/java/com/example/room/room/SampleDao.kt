package com.example.room.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SampleDao {

    @Query("SELECT * FROM sample")
    fun getAll(): List<SampleEntity>

    @Delete
    fun delete(entity: SampleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: SampleEntity)

}