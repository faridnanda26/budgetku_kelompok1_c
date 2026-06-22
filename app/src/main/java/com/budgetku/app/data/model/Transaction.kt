package com.budgetku.app.data.model

data class Transaction(
    val id: String = "",
    val userId: String = "",
    val amount: Double = 0.0,
    val type: TransactionType = TransactionType.EXPENSE,
    val category: String = "",
    val description: String = "",
    val date: Long = System.currentTimeMillis(),
    val isSynced: Boolean = false
)

enum class TransactionType { INCOME, EXPENSE }
