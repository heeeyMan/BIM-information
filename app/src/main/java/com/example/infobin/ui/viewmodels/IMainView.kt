package com.example.infobin.ui.viewmodels

import com.example.infobin.datamodels.TypesItem

interface IMainView {
    fun showHistory()
    fun requestBinInfo(request: String)
    fun itemClicked(clickedItem: String, type: TypesItem)
    fun addHistoryItem(query: String)
    fun checkConnection(): Boolean
}