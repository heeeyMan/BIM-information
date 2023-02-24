package com.example.infobin.assebly

import com.example.infobin.DataBase.Dependencies
import com.example.infobin.models.HistoryModel
import com.example.infobin.ui.viewmodels.HistoryViewModel

class HistoryAssembly : IHistoryAssembly {
    override fun build(): HistoryViewModel {
        val model = HistoryModel(Dependencies.historyRepository)
        return HistoryViewModel(model)
    }
}