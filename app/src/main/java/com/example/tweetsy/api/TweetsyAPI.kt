package com.example.tweetsy.api

import com.example.tweetsy.model.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyAPI {

    @GET("/v3/b/685bafae8a456b7966b54051?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<TweetListItem>>

    @GET("/v3/b/685bafae8a456b7966b54051?meta=false")
    @Headers("X-JSON-Path: tweets..category") // Static header
    suspend fun getCategories(): Response<List<String>>
}