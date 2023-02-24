package com.example.infobin.datamodels

import com.example.infobin.R

enum class HistoryDataState {
    EMPTY,
    DATA_BASE_ERROR,
    NO_EMPTY;

    fun errorTextVisible(): Boolean {
        return when (this) {
            EMPTY, DATA_BASE_ERROR -> true
            NO_EMPTY -> false
        }
    }

    fun errorText(): Int {
        return when (this) {
            EMPTY -> R.string.history_empty
            DATA_BASE_ERROR -> R.string.data_base_error
            NO_EMPTY -> R.string.empty_text
        }
    }
}