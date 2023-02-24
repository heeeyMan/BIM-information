package com.example.infobin.router

import com.example.infobin.datamodels.TypesItem

interface IMainRouter {
    fun navigationToHistory()
    fun openLink(data: String, typeLink: TypesItem)
}