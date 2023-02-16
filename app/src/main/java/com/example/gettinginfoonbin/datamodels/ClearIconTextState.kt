package com.example.gettinginfoonbin.datamodels

enum class ClearIconTextState {
    EMPTY,
    NO_EMPTY;
    fun iconVisible(): Boolean {
        return when(this) {
            EMPTY -> false
            NO_EMPTY -> true
        }
    }
}