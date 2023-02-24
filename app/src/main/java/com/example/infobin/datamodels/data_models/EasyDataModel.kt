package com.example.infobin.datamodels.data_models

import com.example.infobin.R
import com.example.infobin.datamodels.data_types.EasyData
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.easy_data_item.*

class EasyDataModel(
    private val item: EasyData,
) : Item() {
    override fun getLayout() = R.layout.easy_data_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.header.text = item.header
        viewHolder.value.text = item.value
    }
}