package com.example.aroundhospital.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM hospital")
    fun getAll(): Flow<List<BookmarkEntity>>

    @Delete
    fun delete(entity: BookmarkEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: BookmarkEntity)

}