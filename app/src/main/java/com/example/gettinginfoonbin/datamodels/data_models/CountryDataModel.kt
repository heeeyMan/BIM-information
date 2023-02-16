package com.example.gettinginfoonbin.datamodels.data_models

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.example.gettinginfoonbin.R
import com.example.gettinginfoonbin.datamodels.data_types.CountryData
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.card_data_item.header
import kotlinx.android.synthetic.main.country_data_item.*

class CountryDataModel(
    private val item: CountryData,
    private val context: Context
) : Item() {

    override fun getLayout() = R.layout.country_data_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val latitude = item.value?.latitude.toString()
        val longitude =item.value?.longitude.toString()
        val coordinates = SpannableString(context.resources.getString(R.string.coordinate, latitude, longitude))
        viewHolder.header.text = item.header
        viewHolder.country_name.text = item.value?.name
        viewHolder.coordinates.text = coordinates
    }
}