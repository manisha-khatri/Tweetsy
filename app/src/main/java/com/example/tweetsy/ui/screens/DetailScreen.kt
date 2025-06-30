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
import com.example.tweetsy.model.TweetListItem
import androidx.compose.foundation.lazy.items


@Composable
fun TweetsDetailScreen(tweets: List<TweetListItem>) {
    LazyColumn {
        items(tweets) { item ->
            TweetListItem(item.text)
        }
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