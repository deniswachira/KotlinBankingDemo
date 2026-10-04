package com.xyratech.kotlinbankingdemo

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface BankApiService{

    @GET("user/1")
    suspend fun getUser(): UserResponse

    @POST("posts/add")
    suspend fun createPost(
        @Body request: CreatePostRequest
    ): Response<PostResponse>
}