package com.example.infobin.datamodels

import com.example.infobin.datamodels.bin_info_data_subtypes.Bank
import com.example.infobin.datamodels.bin_info_data_subtypes.Country
import com.example.infobin.datamodels.bin_info_data_subtypes.Number
import com.google.gson.annotations.SerializedName

data class BinInfoData(
    @SerializedName("number") var number: Number? = Number(),
    @SerializedName("scheme") var scheme: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("prepaid") var prepaid: Boolean? = null,
    @SerializedName("country") var country: Country? = Country(),
    @SerializedName("bank") var bank: Bank? = Bank()
)
