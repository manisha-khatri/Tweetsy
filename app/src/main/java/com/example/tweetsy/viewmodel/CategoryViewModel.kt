package com.example.tweetsy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsy.model.TweetListItem
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

    private val _tweets = MutableStateFlow<UiState<List<TweetListItem>>>(UiState.Loading)
    val tweets: StateFlow<UiState<List<TweetListItem>>> = _tweets


    fun fetchCategories() {
        viewModelScope.launch {
            val response = tweetRepository.getCategories()
            if(response != null) {
                _categories.emit(UiState.Success(response))
            } else
                _categories.emit(UiState.Error("Data Not Found"))
        }
    }

    fun fetchTweets(category: String) {
        viewModelScope.launch {
            val response = tweetRepository.getTweets(category)
            if(response != null) {
               _tweets.emit(UiState.Success(response))
            } else
                _tweets.emit(UiState.Error("Data Not Found"))
        }
    }
}
