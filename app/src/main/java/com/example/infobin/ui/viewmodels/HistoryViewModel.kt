package com.example.infobin.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infobin.datamodels.HistoryDataState
import com.example.infobin.datamodels.HistoryItemData
import com.example.infobin.models.IHistoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryViewModel(
    private val model: IHistoryModel,
) : ViewModel(), IHistoryView {
    var historyList = MutableLiveData<List<HistoryItemData>>()
    var historyListState = MutableLiveData<HistoryDataState>()
    fun getHistoryList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val data = model.getHistoryList()
                withContext(Dispatchers.Main) {
                    if (data.isNotEmpty()) {
                        historyListState.postValue(HistoryDataState.NO_EMPTY)
                        historyList.postValue(data)
                    } else historyListState.postValue(HistoryDataState.EMPTY)
                }
            }
        }
    }
}