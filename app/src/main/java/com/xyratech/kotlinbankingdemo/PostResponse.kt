package com.xyratech.kotlinbankingdemo

data class PostResponse(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)