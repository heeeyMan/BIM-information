package com.example.infobin.DataBase.Base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.infobin.DataBase.Dao.HistoryDao
import com.example.infobin.DataBase.Entities.HistoryEntity

@Database(version = 1, entities = [HistoryEntity::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
}