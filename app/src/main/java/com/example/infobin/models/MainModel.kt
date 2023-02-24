package com.example.infobin.models

import android.content.Context
import android.os.Build
import android.text.SpannableString
import androidx.annotation.RequiresApi
import com.example.infobin.DataBase.HistoryRepository
import com.example.infobin.R
import com.example.infobin.datamodels.BinInfoData
import com.example.infobin.datamodels.HistoryItemData
import com.example.infobin.datamodels.data_models.*
import com.example.infobin.datamodels.data_types.*
import com.example.infobin.services.ApiInterfaceUrl
import com.example.infobin.services.BinDataClient
import com.example.infobin.services.INetworkService
import com.example.infobin.ui.fragments.OnItemClick
import com.xwray.groupie.kotlinandroidextensions.Item
import java.text.SimpleDateFormat
import java.util.*

class MainModel(
    private val context: Context,
    private val click: OnItemClick,
    private val networkService: INetworkService,
    private val historyRepository: HistoryRepository
) : IMainModel {
    private val dateFormat = "hh:mm:ss dd/M/yyyy"

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getBinData(request: String): BinInfoData {
        val retrofit = BinDataClient.getInstance()
        val apiInterface = retrofit.create(ApiInterfaceUrl::class.java)
        return apiInterface.getBinData(request)
    }

    override fun getData(data: BinInfoData): List<Item> {
        val scheme = EasyData(context.resources.getString(R.string.scheme), data.scheme)
        val brand = EasyData(context.resources.getString(R.string.brand), data.brand)
        val type = EasyData(context.resources.getString(R.string.type), data.type)
        val cardNumber = CardData(
            context.resources.getString(R.string.card_number),
            EasyData(context.resources.getString(R.string.length), data.number?.length.toString()),
            BoolData(context.resources.getString(R.string.luhn), data.number?.luhn)
        )
        val prepaid = BoolData(context.resources.getString(R.string.prepaid), data.prepaid)
        val country = CountryData(context.resources.getString(R.string.country), data.country)
        val bank = BankData(context.resources.getString(R.string.bank), data.bank)

        return listOf(
            BankDataModel(bank, context, click),
            CountryDataModel(country, context),
            BoolDataModel(
                prepaid, context,
                context.resources.getString(R.string.true_value),
                context.resources.getString(R.string.false_value)
            ),
            CardDataModel(
                cardNumber, context,
                SpannableString(context.resources.getString(R.string.debit)),
                SpannableString(context.resources.getString(R.string.credit))
            ),
            EasyDataModel(scheme),
            EasyDataModel(brand),
            EasyDataModel(type)
        )
    }

    override suspend fun addHistoryItem(query: String) {
        historyRepository.insertHistoryItem(toHistoryItem(query))
    }

    override fun isConnection(): Boolean {
        return networkService.checkNetworkConnection()
    }

    private fun toHistoryItem(query: String): HistoryItemData {
        return HistoryItemData(query, getCurrentDateTime())
    }

    private fun getCurrentDateTime(): String {
        return Calendar.getInstance().time.toString(dateFormat)
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }
}