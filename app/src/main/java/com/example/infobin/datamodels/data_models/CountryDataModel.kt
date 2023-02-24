package com.example.infobin.datamodels.data_models

import android.content.Context
import android.text.SpannableString
import com.example.infobin.R
import com.example.infobin.datamodels.data_types.CountryData
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
        val latitude = item.value?.latitude ?: context.getString(R.string.unknown_latitude)
        val longitude = item.value?.longitude ?: context.getString(R.string.unknown_longitude)
        val coordinates =
            SpannableString(
                context.resources.getString(
                    R.string.coordinate,
                    latitude.toString(),
                    longitude.toString()
                )
            )
        viewHolder.header.text = item.header
        viewHolder.country_name.text = item.value?.name
        viewHolder.coordinates.text = coordinates
    }
}