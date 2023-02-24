package com.example.infobin.ui.fragments

import com.example.infobin.datamodels.TypesItem

interface OnItemClick {
    fun onItemClick(clickedItem: String, type: TypesItem)
}