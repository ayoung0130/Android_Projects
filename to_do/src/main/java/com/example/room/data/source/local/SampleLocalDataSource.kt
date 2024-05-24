package com.example.room.data.source.local

import com.example.room.room.SampleEntity

interface SampleLocalDataSource {

    fun getAll(): List<SampleEntity>

    fun delete(entity: SampleEntity)

    fun insert(entity: SampleEntity)
}