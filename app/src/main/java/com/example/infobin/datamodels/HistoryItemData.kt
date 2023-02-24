package com.example.infobin.datamodels

import com.example.infobin.DataBase.Entities.HistoryEntity

data class HistoryItemData (
    val request: String,
    val data: String,
    val id: Long = 0,
)

fun HistoryItemData.toHistoryEntity() = HistoryEntity(
    id = id,
    query = request,
    data = data
)