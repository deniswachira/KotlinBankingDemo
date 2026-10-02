package com.xyratech.kotlinbankingdemo


data class Transaction(
    val id: Int,
    val type: String,
    val amount: Double,
    val description: String,
    val isSuccessful: Boolean
)