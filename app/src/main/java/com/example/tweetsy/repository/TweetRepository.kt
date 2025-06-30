package com.example.tweetsy.repository

import com.example.tweetsy.api.TweetsyAPI
import com.example.tweetsy.model.TweetListItem
import javax.inject.Inject

class TweetRepository @Inject constructor(
    private val tweetsyAPI: TweetsyAPI
) {

    suspend fun getCategories(): List<String>? {
        val response = tweetsyAPI.getCategories()

        if(response.isSuccessful && response.body()!=null) {
            return response.body()
        }
        return null
    }

    suspend fun getTweets(category: String): List<TweetListItem>? {
        val response = tweetsyAPI.getTweets("tweets[?(@.category==\"$category\")]")

        if(response.isSuccessful && response.body()!=null) {
            return response.body()
        }
        return null
    }
}