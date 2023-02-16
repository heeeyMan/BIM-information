package com.example.gettinginfoonbin.datamodels.bin_info_data_subtypes

import com.google.gson.annotations.SerializedName

data class Number(
    @SerializedName("length") var length: Int? = null,
    @SerializedName("luhn") var luhn: Boolean? = null
)
