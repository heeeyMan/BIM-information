package com.example.infobin.datamodels.data_models

import android.content.Context
import com.example.infobin.R
import com.example.infobin.datamodels.data_types.BoolData
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.card_data_item.header
import kotlinx.android.synthetic.main.easy_data_item.*

class BoolDataModel(
    private val item: BoolData,
    private val context: Context,
    private val leftValue: String,
    private val rightValue: String
) : Item() {

    override fun getLayout() = R.layout.easy_data_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.header.text = item.header
        viewHolder.value.text =
            context.resources.getString(R.string.boolean_data, leftValue, rightValue)
    }
}