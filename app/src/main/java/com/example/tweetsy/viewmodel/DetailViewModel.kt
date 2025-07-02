package com.example.tweetsy.viewmodel

import androidx.lifecycle.SavedStateHandle
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
class DetailViewModel @Inject constructor(
    private val tweetRepository: TweetRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _tweets = MutableStateFlow<UiState<List<TweetListItem>>>(UiState.Loading)
    val tweets: StateFlow<UiState<List<TweetListItem>>> get() = _tweets

    private val category: String = savedStateHandle["category"] ?: "motivation"

    init {
        fetchTweets()
    }

    private fun fetchTweets() {
        viewModelScope.launch {
            try {
                val response = tweetRepository.getTweets(category)
                if (response != null) {
                    _tweets.value = UiState.Success(response)
                } else {
                    _tweets.value = UiState.Error("No tweets found for '$category'")
                }
            } catch (e: Exception) {
                _tweets.value = UiState.Error("Something went wrong: ${e.message}")
            }
        }
    }
}
