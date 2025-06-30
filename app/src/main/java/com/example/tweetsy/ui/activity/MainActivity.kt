package com.example.tweetsy.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetsy.ui.screens.CategoryScreen
import com.example.tweetsy.ui.screens.TweetsDetailScreen
import com.example.tweetsy.ui.theme.TweetsyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TweetsyTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Tweetsy") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Black,
                                titleContentColor = Color.White,
                            )
                        )
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.Companion.padding(innerPadding)) {
                        App()
                    }
                }
            }
        }
    }

}

//Navigation related work
    @Composable
    fun App() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "category") {
            composable(route = "category") {
                CategoryScreen(
                    onCategorySelected = {
                        navController.navigate("detail/${it}")
                    }
                )
            }

            composable(
                route = "detail/{category}",
                arguments = listOf(navArgument("category") { type = NavType.StringType })
            ) {
                TweetsDetailScreen()
            }
        }
    }
