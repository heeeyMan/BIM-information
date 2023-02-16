package com.example.gettinginfoonbin.router

import com.example.gettinginfoonbin.datamodels.TypesItem

interface IMainRouter {
    fun navigationToHistory()
    fun openLink(data: String, typeLink: TypesItem)
}