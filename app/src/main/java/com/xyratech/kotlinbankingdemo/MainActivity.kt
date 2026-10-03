
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xyratech.kotlinbankingdemo.ui.theme.KotlinBankingDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KotlinBankingDemoTheme {
                KotlinBankingDemoTheme {

                    val bankViewModel: BankViewModel = viewModel()

                    BankingDashboard(
                        viewModel = bankViewModel
                    )
                }
            }
        }
    }


@Composable
fun BankingDashboard(
    viewModel: BankViewModel
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
                text = "Welcome, ${viewModel.accountHolder}",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(text = "Account: ${viewModel.accountNumber}")

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text("Available Balance")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "KES ${"%,.2f".format(viewModel.balance)}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Button(
                    onClick = {
                        viewModel.deposit(1000.0)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Deposit")
                }

                Button(
                    onClick = {
                        viewModel.withdraw(500.0)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Withdraw")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Recent Transactions",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {
                items(viewModel.transactions) { transaction ->

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        Text(
                            text = transaction.type,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(transaction.description)

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
}