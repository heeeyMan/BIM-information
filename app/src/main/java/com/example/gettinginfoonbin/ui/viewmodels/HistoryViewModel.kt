package com.example.gettinginfoonbin.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gettinginfoonbin.datamodels.HistoryItemData
import com.example.gettinginfoonbin.models.IHistoryModel

class HistoryViewModel(
    private val model: IHistoryModel,
) : ViewModel(), IHistoryView {
    var historyList = MutableLiveData<List<HistoryItemData>>()
    override fun updateHistoryList(): List<HistoryItemData> {
        TODO("Not yet implemented")
    }
}