package com.xyratech.kotlinbankingdemo

import retrofit2.http.GET

interface BankApiService{

    @GET("user/1")
    suspend fun getUser(): UserResponse
}