package com.example.room.data.repo

import com.example.room.room.SampleEntity

interface SampleRepository {

    fun getAll(): List<SampleEntity>

    fun delete(entity: SampleEntity)

    fun insert(entity: SampleEntity)
}