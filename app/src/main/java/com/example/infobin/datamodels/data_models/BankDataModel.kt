package com.example.infobin.datamodels.data_models

import android.content.Context
import com.example.infobin.R
import com.example.infobin.datamodels.TypesItem
import com.example.infobin.datamodels.data_types.BankData
import com.example.infobin.ui.fragments.OnItemClick
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.bank_data_item.*
import kotlinx.android.synthetic.main.bank_data_item.header

class BankDataModel(
    private val item: BankData,
    private val context: Context,
    private val click: OnItemClick
) : Item() {
    override fun getLayout() = R.layout.bank_data_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val url = item.value?.url ?: context.getString(R.string.unknown_link)
        val phone = item.value?.phone ?: context.getString(R.string.unknown_number)
        val city = item.value?.city ?: context.getString(R.string.unknown_city)
        val bankName = item.value?.name ?: context.getString(R.string.unknown_bank)
        viewHolder.header.text = item.header
        viewHolder.bank_name.text = context.getString(R.string.bank_name_city, bankName, city)
        viewHolder.link.text = url
        viewHolder.phone.text = phone
        viewHolder.phone.setOnClickListener {
            click.onItemClick(phone, TypesItem.PHONE)
        }
        viewHolder.link.setOnClickListener {
            click.onItemClick(url, TypesItem.LINK)
        }
        viewHolder.bank_name.setOnClickListener {
            click.onItemClick("$city, $bankName", TypesItem.MAP)
        }
    }
}