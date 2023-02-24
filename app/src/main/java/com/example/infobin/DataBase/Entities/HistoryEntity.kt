package com.example.infobin.DataBase.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.infobin.datamodels.HistoryItemData

@Entity(tableName = "history", [Index("id")])
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "query") val query: String,
    @ColumnInfo(name = "data") val data: String
)

fun HistoryEntity.toHistoryItemData() = HistoryItemData(
    id = id,
    request = query,
    data = data
)
