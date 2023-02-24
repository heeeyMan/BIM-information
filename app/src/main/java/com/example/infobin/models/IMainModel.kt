package com.example.infobin.models

import com.example.infobin.datamodels.BinInfoData
import com.xwray.groupie.kotlinandroidextensions.Item

interface IMainModel {
    suspend fun getBinData(request: String): BinInfoData
    fun getData(data: BinInfoData): List<Item>
    suspend fun addHistoryItem(query: String)
    fun isConnection(): Boolean
}