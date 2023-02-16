package com.example.gettinginfoonbin.datamodels.data_models

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.example.gettinginfoonbin.R
import com.example.gettinginfoonbin.datamodels.data_types.CardData
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.card_data_item.*
import kotlinx.android.synthetic.main.card_data_item.header
import kotlinx.android.synthetic.main.card_data_item.view.header
import kotlinx.android.synthetic.main.easy_data_item.view.*

class CardDataModel(
    private val item: CardData,
    private val context: Context,
    private val leftValue: SpannableString,
    private val rightValue: SpannableString
) : Item() {

    override fun getLayout() = R.layout.card_data_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.header.text = item.header
        viewHolder.length.header.text = item.length?.header
        viewHolder.length.value.text = item.length?.value
        viewHolder.luhn.header.text = item.luhn?.header
        if(item.luhn?.isPrepaid == true) {
            leftValue.setSpan(
                ForegroundColorSpan(Color.BLUE),
                0,
                leftValue.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        } else {
            rightValue.setSpan(
                ForegroundColorSpan(Color.BLUE),
                0,
                rightValue.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
        viewHolder.luhn.value.text = context.resources.getString(R.string.boolean_data, leftValue, rightValue)
    }
}