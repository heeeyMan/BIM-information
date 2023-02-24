package com.example.infobin.assebly

import android.content.Context
import androidx.navigation.NavController
import com.example.infobin.DataBase.Dependencies
import com.example.infobin.models.MainModel
import com.example.infobin.router.MainRouter
import com.example.infobin.services.NetworkService
import com.example.infobin.ui.fragments.OnItemClick
import com.example.infobin.ui.viewmodels.MainViewModel

class MainAssembly(
    private val navController: NavController,
    private val context: Context,
    private val click: OnItemClick
) : IMainAssembly {
    override fun build(): MainViewModel {
        val networkService = NetworkService(context)
        val model = MainModel(context, click, networkService, Dependencies.historyRepository)
        val router = MainRouter(navController, context)
        return MainViewModel(model, router)
    }
}