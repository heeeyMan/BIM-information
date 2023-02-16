package com.example.gettinginfoonbin.ui.viewmodels

import com.example.gettinginfoonbin.datamodels.HistoryItemData

interface IHistoryView {
    fun updateHistoryList(): List<HistoryItemData>
}