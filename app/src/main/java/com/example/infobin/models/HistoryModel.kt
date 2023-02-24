package com.example.infobin.models

import com.example.infobin.DataBase.HistoryRepository
import com.example.infobin.datamodels.HistoryItemData

class HistoryModel(
    private val historyRepository: HistoryRepository
) : IHistoryModel {
    override suspend fun getHistoryList(): List<HistoryItemData> {
        return historyRepository.getAllHistory() ?: throw Exception("Your history is empty")
    }
}