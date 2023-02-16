package com.example.gettinginfoonbin.assebly

import android.content.Context
import androidx.navigation.NavController
import com.example.gettinginfoonbin.models.MainModel
import com.example.gettinginfoonbin.router.MainRouter
import com.example.gettinginfoonbin.services.NetworkService
import com.example.gettinginfoonbin.ui.utils.OnItemClick
import com.example.gettinginfoonbin.ui.viewmodels.MainViewModel

class MainAssembly(
    private val navController: NavController,
    private val context: Context,
    private val click: OnItemClick
): IMainAssembly {
    override fun build(): MainViewModel {
        val networkService = NetworkService(context)
        val model = MainModel(context, click, networkService)
        val router = MainRouter(navController, context)
        return MainViewModel(model, router)
    }
}