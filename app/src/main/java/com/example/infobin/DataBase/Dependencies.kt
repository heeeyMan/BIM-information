package com.example.infobin.DataBase

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import com.example.infobin.DataBase.Base.AppDataBase

@SuppressLint("StaticFieldLeak")
object Dependencies {
    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }

    private val appDataBase: AppDataBase by lazy {
        Room.databaseBuilder(context, AppDataBase::class.java, "database.db")
            .build()
    }

    val historyRepository: HistoryRepository by lazy {
        HistoryRepository(appDataBase.getHistoryDao())
    }
}