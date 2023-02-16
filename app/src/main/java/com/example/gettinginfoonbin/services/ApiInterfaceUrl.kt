package com.example.gettinginfoonbin.services

import com.example.gettinginfoonbin.datamodels.BinInfoData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterfaceUrl {
    @GET("/{request}")
    suspend fun getBinData(@Path("request") request: String): BinInfoData
}