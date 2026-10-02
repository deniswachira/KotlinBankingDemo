package com.xyratech.kotlinbankingdemo

class BankAccount(
    //cannot be reassigned
    val accountNumber: String,
    val accountHolder: String,
    // encapsulated
    // can be reassigned
    private var balance: Double
) {
    private val transactions = mutableListOf<Transaction>()

    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount

            transactions.add(
                Transaction(
                    id = transactions.size + 1,
                    type = "DEPOSIT",
                    amount = amount,
                    description = "Money deposited",
                    isSuccessful = true
                )
            )
        }
    }

    fun withdraw(amount: Double): Boolean {
        if (amount > 0 && amount <= balance) {
            balance -= amount

            transactions.add(
                Transaction(
                    id = transactions.size + 1,
                    type = "WITHDRAWAL",
                    amount = amount,
                    description = "Money withdrawn",
                    isSuccessful = true
                )
            )

            return true
        }

        transactions.add(
            Transaction(
                id = transactions.size + 1,
                type = "WITHDRAWAL",
                amount = amount,
                description = "Insufficient balance or invalid amount",
                isSuccessful = false
            )
        )

        return false
    }

    fun getBalance(): Double{
        return balance
    }
    fun getTransactions(): List<Transaction> {
        return transactions
    }
}