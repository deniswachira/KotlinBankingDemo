
package com.xyratech.kotlinbankingdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xyratech.kotlinbankingdemo.ui.theme.KotlinBankingDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val account = BankAccount(
            accountNumber = "ACC001",
            accountHolder = "Denis",
            balance = 20_000.0
        )

        account.deposit(5_000.0)
        account.withdraw(3_000.0)

        setContent {
            KotlinBankingDemoTheme {
                BankingDashboard(
                    accountHolder = account.accountHolder,
                    accountNumber = account.accountNumber,
                    balance = account.getBalance(),
                    transactions = account.getTransactions()
                )
            }
        }
    }
}

@Composable
fun BankingDashboard(
    accountHolder: String,
    accountNumber: String,
    balance: Double,
    transactions: List<Transaction>
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            Text(
                text = "Welcome, $accountHolder",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Account: $accountNumber")

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(text = "Available Balance")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "KES ${"%,.2f".format(balance)}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Recent Transactions",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {
                items(transactions) { transaction ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        Text(
                            text = transaction.type,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(text = transaction.description)

                        Text(
                            text = "KES ${"%,.2f".format(transaction.amount)}"
                        )

                        Text(
                            text = if (transaction.isSuccessful)
                                "Successful"
                            else
                                "Failed"
                        )

                        HorizontalDivider()
                    }
                }
            }
        }
    }
}