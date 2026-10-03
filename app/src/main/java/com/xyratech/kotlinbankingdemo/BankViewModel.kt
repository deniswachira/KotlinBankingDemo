package com.xyratech.kotlinbankingdemo

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// This means BankViewModel inherits from Android's ViewModel class.
class BankViewModel: ViewModel() {
    private val account = BankAccount(
        accountNumber = "ACC001",
        accountHolder = "Denis",
        balance = 20_000.0
    )

    var balance by mutableDoubleStateOf(account.getBalance())
        //Other classes can read the balance, but only the ViewModel can change it.
        private set

    val transactions = mutableStateListOf<Transaction>()

    val accountHolder = account.accountHolder
    val accountNumber = account.accountNumber

    // The init block executes when the ViewModel is created.
    init {
        account.deposit(5_000.0)
        account.withdraw(3_000.0)

        balance = account.getBalance()
        transactions.addAll(account.getTransactions())
    }

    fun deposit(amount: Double) {
        account.deposit(amount)
        balance = account.getBalance()
        transactions.add(account.getTransactions().last())
    }

    fun withdraw(amount: Double) {
        account.withdraw(amount)
        balance = account.getBalance()
        transactions.add(account.getTransactions().last())
    }

}