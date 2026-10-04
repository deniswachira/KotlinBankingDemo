package com.xyratech.kotlinbankingdemo

import kotlinx.coroutines.delay

class AccountRepository {
    suspend fun getAccountBalance(): Double {

        // Simulate a network request
        delay(2000)

        return 22_000.0
    }
    suspend fun getUserProfile(): UserResponse{
        // New API request using Retrofit
        return RetrofitClient.api.getUser()
    }

    suspend fun createPost(): PostResponse {

        val request = CreatePostRequest(
            title = "My First Android API Request",
            body = "Learning Kotlin and Retrofit",
            userId = 1
        )

        return RetrofitClient.api.createPost(request)
    }
}