package com.budgetku.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val userId: String,
    val amount: Double,
    val type: String,
    val category: String,
    val description: String,
    val date: Long = System.currentTimeMillis(),
    val isSynced: Boolean = false
)
