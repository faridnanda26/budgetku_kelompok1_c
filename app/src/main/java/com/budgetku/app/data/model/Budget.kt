package com.budgetku.app.data.model

data class Budget(
    val id: String = "",
    val userId: String = "",
    val category: String = "",
    val limitAmount: Double = 0.0,
    val month: Int = 1,
    val year: Int = 2026
)
