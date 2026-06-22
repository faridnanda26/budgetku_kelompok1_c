package com.budgetku.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "budgets")
data class BudgetEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val userId: String,
    val category: String,
    val limitAmount: Double,
    val month: Int,
    val year: Int,
    val isSynced: Boolean = false
)
