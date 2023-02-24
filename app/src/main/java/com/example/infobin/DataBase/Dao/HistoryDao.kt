package com.example.infobin.DataBase.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.infobin.DataBase.Entities.HistoryEntity

@Dao
interface HistoryDao {
    @Insert(entity = HistoryEntity::class)
    suspend fun insertItem(item: HistoryEntity)

    @Query("SELECT * FROM history")
    fun getAllHistory(): List<HistoryEntity>?
}