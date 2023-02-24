package com.example.infobin.DataBase

import com.example.infobin.DataBase.Dao.HistoryDao
import com.example.infobin.DataBase.Entities.toHistoryItemData
import com.example.infobin.datamodels.HistoryItemData
import com.example.infobin.datamodels.toHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withContext

class HistoryRepository(private val historyDao: HistoryDao) {
    suspend fun insertHistoryItem(item: HistoryItemData) {
        Dispatchers.IO {
            historyDao.insertItem(item.toHistoryEntity())
        }
    }

    suspend fun getAllHistory(): List<HistoryItemData>? {
        return withContext(Dispatchers.IO) {
            historyDao.getAllHistory()?.map { it.toHistoryItemData() }
        }
    }
}