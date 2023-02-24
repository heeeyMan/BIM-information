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
            try {
                val data = model.getHistoryList()
                withContext(Dispatchers.Main) {
                    if (data.isNotEmpty()) {
                        historyListState.postValue(HistoryDataState.NO_EMPTY)
                        historyList.postValue(data)
                    } else historyListState.postValue(HistoryDataState.EMPTY)
                }
            }catch (error: Throwable) {
                historyListState.postValue(HistoryDataState.DATA_BASE_ERROR)
            }
        }
    }
}