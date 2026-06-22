package com.budgetku.app.di

import android.content.Context
import com.budgetku.app.data.local.BudgetDatabase
import com.budgetku.app.data.remote.FirebaseService
import com.budgetku.app.data.repository.BudgetRepository
import com.budgetku.app.data.repository.TransactionRepository

class AppContainer(context: Context) {
    private val database = BudgetDatabase.getInstance(context)
    private val firebaseService = FirebaseService()

    val transactionRepository = TransactionRepository(database.transactionDao(), firebaseService)
    val budgetRepository = BudgetRepository(database.budgetDao(), database.transactionDao(), firebaseService)
}
