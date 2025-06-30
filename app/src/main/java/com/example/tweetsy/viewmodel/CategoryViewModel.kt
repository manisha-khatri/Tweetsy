package com.example.tweetsy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsy.repository.TweetRepository
import com.example.tweetsy.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val tweetRepository: TweetRepository
) : ViewModel() {

    private val _categories = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val categories: StateFlow<UiState<List<String>>> = _categories

    fun fetchCategories() {
        viewModelScope.launch {
            val response = tweetRepository.getCategories()
            if(response != null) {
                _categories.emit(UiState.Success(response))
            } else
                _categories.emit(UiState.Error("Data Not Found"))
        }
    }
}
