package com.example.infobin.models

import com.example.infobin.datamodels.HistoryItemData

interface IHistoryModel {
    suspend fun getHistoryList(): List<HistoryItemData>
}