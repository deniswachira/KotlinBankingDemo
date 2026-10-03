package com.xyratech.kotlinbankingdemo

import kotlinx.coroutines.delay

class AccountRepository {
    suspend fun getAccountBalance(): Double {

        // Simulate a network request
        delay(2000)

        return 22_000.0
    }
}