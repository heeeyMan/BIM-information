package com.example.gettinginfoonbin.services

import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Timeout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

object BinDataClient {
    private const val baseUrl = "https://lookup.binlist.net"
    @RequiresApi(Build.VERSION_CODES.O)
    fun getInstance(): Retrofit {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
    }

}