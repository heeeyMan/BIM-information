package com.example.infobin.datamodels

enum class HistoryDataState {
    EMPTY,
    NO_EMPTY;

    fun errorTextVisible(): Boolean {
        return when (this) {
            EMPTY -> true
            NO_EMPTY -> false
        }
    }
}