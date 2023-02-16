package com.example.gettinginfoonbin.datamodels

import com.example.gettinginfoonbin.R

enum class BinTextState {
    EMPTY,
    TOO_MANY_SYMBOLS,
    WRONG_SYMBOL,
    CORRECT;
    fun errorTextVisible(): Boolean {
        return when(this) {
            CORRECT -> false
            TOO_MANY_SYMBOLS, WRONG_SYMBOL, EMPTY -> true
        }
    }
    fun messageErrorText(): Int? {
        return when(this) {
            CORRECT -> null
            TOO_MANY_SYMBOLS -> R.string.too_many_symbols
            WRONG_SYMBOL -> R.string.not_correct_symbol
            EMPTY -> R.string.empty_text
        }
    }

    fun buttonColor(): Pair<Int, Int> {
        return when(this) {
            CORRECT -> Pair(R.drawable.button_state, R.color.orange_700)
            TOO_MANY_SYMBOLS, WRONG_SYMBOL, EMPTY -> Pair(R.drawable.button_blocked, R.color.gray_400)
        }
    }
}