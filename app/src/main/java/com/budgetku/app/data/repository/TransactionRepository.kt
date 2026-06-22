package com.budgetku.app.data.repository

import com.budgetku.app.data.local.dao.CategoryTotal
import com.budgetku.app.data.local.dao.TransactionDao
import com.budgetku.app.data.local.entity.TransactionEntity
import com.budgetku.app.data.remote.FirebaseService
import kotlinx.coroutines.flow.Flow

class TransactionRepository(
    private val dao: TransactionDao,
    private val firebaseService: FirebaseService
) {
    fun getTransactions(userId: String): Flow<List<TransactionEntity>> = dao.getAllTransactions(userId)

    fun getFilteredTransactions(userId: String, type: String? = null, category: String? = null): Flow<List<TransactionEntity>> =
        when {
            !type.isNullOrBlank() -> dao.getTransactionsByType(userId, type)
            !category.isNullOrBlank() -> dao.getTransactionsByCategory(userId, category)
            else -> dao.getAllTransactions(userId)
        }

    suspend fun addTransaction(entity: TransactionEntity) {
        dao.insertTransaction(entity)
        try {
            firebaseService.syncTransaction(entity)
            dao.markAsSynced(entity.id)
        } catch (_: Exception) {}
    }

    suspend fun deleteTransaction(entity: TransactionEntity) {
        dao.deleteTransaction(entity)
        try { firebaseService.deleteTransaction(entity.id) } catch (_: Exception) {}
    }

    suspend fun syncPendingTransactions(userId: String) {
        dao.getUnsyncedTransactions(userId).forEach {
            try {
                firebaseService.syncTransaction(it)
                dao.markAsSynced(it.id)
            } catch (_: Exception) {}
        }
    }

    suspend fun getTotalIncome(userId: String, start: Long, end: Long) = dao.getTotalIncome(userId, start, end) ?: 0.0
    suspend fun getTotalExpense(userId: String, start: Long, end: Long) = dao.getTotalExpense(userId, start, end) ?: 0.0
    suspend fun getTotalExpenseByCategory(userId: String, category: String, start: Long, end: Long) =
        dao.getTotalExpenseByCategory(userId, category, start, end) ?: 0.0
    suspend fun getExpenseByCategory(userId: String, start: Long, end: Long): List<CategoryTotal> =
        dao.getExpenseByCategory(userId, start, end)

    suspend fun seedSampleDataIfEmpty(userId: String) {
        // Sengaja dikosongkan.
        // Akun baru harus mulai dari 0 tanpa pemasukan/pengeluaran contoh.
    }
}
