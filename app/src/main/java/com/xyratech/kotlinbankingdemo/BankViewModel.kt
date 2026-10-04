package com.xyratech.kotlinbankingdemo

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.CancellationException

// This means BankViewModel inherits from Android's ViewModel class.
class BankViewModel: ViewModel() {
    private val account = BankAccount(
        accountNumber = "ACC001",
        accountHolder = "Denis",
        balance = 20_000.0
    )

    private val repository = AccountRepository()

    var balance by mutableDoubleStateOf(account.getBalance())
        //Other classes can read the balance, but only the ViewModel can change it.
        private set
    var isLoading by mutableStateOf(false)
        private set
    var userProfile by mutableStateOf<UserResponse?>(null)
        private set
    var isUserLoading by mutableStateOf(false)
        private set

    var userError by mutableStateOf<String?>(null)
        private set

    var createdPost by mutableStateOf<PostResponse?>(null)
        private set

    var isCreatingPost by mutableStateOf(false)
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

    fun loadAcountBalance(){
        viewModelScope.launch{
            isLoading = true
            try{
                balance = repository.getAccountBalance()
            }finally {
                isLoading = false
            }
        }
    }

    fun loadUserProfile(){
        //Starts the asynchronous operation.
        viewModelScope.launch {
          // Updates the loading state.
            isUserLoading = true
            userError = null
            try {
                //Makes the actual HTTP request.
                val result = repository.getUserProfile()

                userProfile = result
            }catch (exception: CancellationException){
                throw exception
            }catch (exception: Exception){
                userError = exception.message
                    ?: "Unable to load user profile"
            }finally {
                isUserLoading = false
            }
        }
    }

    fun createPost() {

        viewModelScope.launch {

            isCreatingPost = true
            userError = null

            try {
                createdPost = repository.createPost()

            } catch (exception: CancellationException) {
                throw exception

            } catch (exception: Exception) {
                userError = exception.message
                    ?: "Unable to create post"

            } finally {
                isCreatingPost = false
            }
        }
    }

}