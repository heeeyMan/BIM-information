package com.example.gettinginfoonbin.datamodels.data_models

import android.content.Context
import com.example.gettinginfoonbin.R
import com.example.gettinginfoonbin.datamodels.TypesItem
import com.example.gettinginfoonbin.datamodels.data_types.BankData
import com.example.gettinginfoonbin.ui.utils.OnItemClick
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
        val url = item.value?.url ?: ""
        val phone = item.value?.phone ?: ""
        val city = item.value?.city ?: ""
        val bankName = item.value?.name ?: ""
        viewHolder.header.text = item.header
        viewHolder.bank_name.text = context.getString(R.string.bank_name_city, bankName, city)
        viewHolder.link.text = url
        viewHolder.phone.text = phone
        viewHolder.phone.setOnClickListener{
            click.onItemClick(phone, TypesItem.PHONE)
        }
        viewHolder.link.setOnClickListener{
            click.onItemClick(url, TypesItem.LINK)
        }
        viewHolder.bank_name.setOnClickListener{
            click.onItemClick("$city, $bankName", TypesItem.MAP)
        }
    }
}