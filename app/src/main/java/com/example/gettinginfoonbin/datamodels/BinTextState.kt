package com.example.gettinginfoonbin.datamodels

import com.example.gettinginfoonbin.R

enum class BinTextState {
    EMPTY,
    NO_EMPTY;
    fun errorTextVisible(): Boolean {
        return when(this) {
            EMPTY -> true
            NO_EMPTY -> false
        }
    }
    fun clearIconVisible(): Boolean {
        return when(this) {
            EMPTY -> false
            NO_EMPTY -> true
        }
    }
    fun messageErrorText(): Int? {
        return when(this) {
            EMPTY -> R.string.empty_text
            NO_EMPTY -> null
        }
    }

    fun buttonColor(): Pair<Int, Int> {
        return when(this) {
            EMPTY -> Pair(R.drawable.button_blocked, R.color.gray_400)
            NO_EMPTY -> Pair(R.drawable.button_state, R.color.orange_700)
        }
    }
}