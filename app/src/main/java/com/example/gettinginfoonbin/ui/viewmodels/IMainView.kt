package com.example.gettinginfoonbin.ui.viewmodels

import com.example.gettinginfoonbin.datamodels.TypesItem

interface IMainView {
    fun showHistory()
    fun requestBinInfo(request: String)
    fun itemClicked(clickedItem: String, type: TypesItem)
    fun addHistoryItem(query: String)
    fun checkConnection(): Boolean
}