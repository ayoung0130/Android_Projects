package com.android.kakao_book.room.bookmark

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM book")
    fun getAll() : Flow<List<BookmarkEntity>>

    @Delete
    fun delete(entity: BookmarkEntity)

    // 동일한 자료가 있을시 REPLACE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: BookmarkEntity)

    @Update
    fun update(memo: BookmarkEntity)
}