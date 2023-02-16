package com.example.gettinginfoonbin.ui.utils

import com.example.gettinginfoonbin.datamodels.TypesItem

interface OnItemClick {
    fun onItemClick(clickedItem: String, type: TypesItem)
}