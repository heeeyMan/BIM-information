package com.example.gettinginfoonbin.models

import com.example.gettinginfoonbin.datamodels.BinInfoData
import com.example.gettinginfoonbin.datamodels.TypesItem
import com.xwray.groupie.kotlinandroidextensions.Item

interface IMainModel {
    suspend fun getBinData(request: String): BinInfoData
    fun getData(data: BinInfoData): List<Item>
    fun addHistoryItem(query: String)
    fun isConnection(): Boolean
}