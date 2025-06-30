package com.example.tweetsy.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tweetsy.ui.state.UiState
import com.example.tweetsy.viewmodel.DetailViewModel

@Composable
fun TweetsDetailScreen() {
    val viewModel: DetailViewModel = hiltViewModel()
    val state = viewModel.tweets.collectAsStateWithLifecycle()

    when(val uiState = state.value) {
        is UiState.Loading -> Text(text = "Loading ...")
        is UiState.Success -> {
            LazyColumn() {
                items(uiState.data) { item ->
                    TweetListItem(item.text)
                }
            }
        }
        is UiState.Error -> Text(text = uiState.message.toString())
    }
}

@Composable
fun TweetListItem(tweet: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        border = BorderStroke(1.dp, Color.Gray),
        content = {
            Text(
                text = tweet,
                modifier = Modifier
                    .padding((16.dp)),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}