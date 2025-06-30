package com.example.tweetsy.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tweetsy.ui.screens.CategoryScreen
import com.example.tweetsy.ui.screens.TweetsDetailScreen
import com.example.tweetsy.ui.state.UiState
import com.example.tweetsy.ui.theme.TweetsyTheme
import com.example.tweetsy.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TweetsyTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.Companion.padding(innerPadding))
                    SetCategoryScreen()
                }
            }
        }
    }

    @Composable
    fun SetCategoryScreen() {
        val state = viewModel.categories.collectAsStateWithLifecycle()
        when (val uiState = state.value) {
            is UiState.Loading -> {
                Text(text = "Loading ...")
                viewModel.fetchCategories()
            }

            is UiState.Success -> {
                CategoryScreen(uiState.data)
            }

            is UiState.Error -> {
                Text(text = uiState.message.toString())
            }
        }
    }

    @Composable
    fun SetTweetsDetailScreen() {
        val state = viewModel.tweets.collectAsStateWithLifecycle()

        when(val uiState = state.value) {
            is UiState.Loading -> {
                Text(text = "Loading ...")
            }
            is UiState.Success -> {
                TweetsDetailScreen(uiState.data)
            }
            is UiState.Error -> {
                Text(text = uiState.message.toString())
            }
        }

    }
}
