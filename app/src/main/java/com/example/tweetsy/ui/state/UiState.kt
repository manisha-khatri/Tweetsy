package com.example.tweetsy.ui.state

sealed class UiState<out T> {
    object Loading: UiState<Nothing>()
    data class Success<T>(val data: T): UiState<T>()
    class Error(val message: String): UiState<Nothing>()
}