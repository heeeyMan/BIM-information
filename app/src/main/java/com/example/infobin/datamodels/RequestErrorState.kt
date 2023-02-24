package com.example.infobin.datamodels

import com.example.infobin.R

enum class RequestErrorState {
    CORRECT,
    NOT_FOUND,
    NO_INTERNET,
    SERVER_ERROR,
    DATA_BASE_ERROR,
    CLIENT_ERROR,
    UNKNOWN_ERROR;

    fun errorTextVisible(): Boolean {
        return when (this) {
            CORRECT -> false
            NOT_FOUND, NO_INTERNET, SERVER_ERROR,
            UNKNOWN_ERROR, CLIENT_ERROR, DATA_BASE_ERROR, -> true
        }
    }

    fun messageErrorText(): Int? {
        return when (this) {
            CORRECT -> null
            NOT_FOUND -> R.string.not_found
            NO_INTERNET -> R.string.no_internet
            SERVER_ERROR -> R.string.server_error
            UNKNOWN_ERROR -> R.string.unknown_error
            CLIENT_ERROR -> R.string.client_error
            DATA_BASE_ERROR -> R.string.data_base_error

        }
    }

    fun buttonColor(): Pair<Int, Int> {
        return when (this) {
            CORRECT, NOT_FOUND, SERVER_ERROR, UNKNOWN_ERROR, CLIENT_ERROR, DATA_BASE_ERROR -> Pair(
                R.drawable.button_state,
                R.color.orange_700
            )
            NO_INTERNET -> Pair(R.drawable.button_blocked, R.color.gray_400)
        }
    }

    fun tryAgainButtonVisible(): Boolean {
        return when (this) {
            CORRECT, NOT_FOUND, SERVER_ERROR, UNKNOWN_ERROR, CLIENT_ERROR, DATA_BASE_ERROR -> false
            NO_INTERNET -> true
        }
    }
}