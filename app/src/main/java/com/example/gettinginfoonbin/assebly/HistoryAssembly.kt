package com.example.gettinginfoonbin.assebly

import com.example.gettinginfoonbin.models.HistoryModel
import com.example.gettinginfoonbin.services.HistoryService
import com.example.gettinginfoonbin.ui.viewmodels.HistoryViewModel

class HistoryAssembly: IHistoryAssembly {
    override fun build(): HistoryViewModel {
        val service = HistoryService()
        val model = HistoryModel(service)
        return HistoryViewModel(model)
    }
}