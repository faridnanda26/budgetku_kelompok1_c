package com.budgetku.app.data.repository

import com.budgetku.app.data.local.dao.BudgetDao
import com.budgetku.app.data.local.dao.TransactionDao
import com.budgetku.app.data.local.entity.BudgetEntity
import com.budgetku.app.data.remote.FirebaseService
import com.budgetku.app.util.DateUtils

class BudgetRepository(
    private val budgetDao: BudgetDao,
    private val transactionDao: TransactionDao,
    private val firebaseService: FirebaseService
) {
    suspend fun addBudget(entity: BudgetEntity) {
        budgetDao.insertBudget(entity)
        try {
            firebaseService.syncBudget(entity)
            budgetDao.markAsSynced(entity.id)
        } catch (_: Exception) {}
    }

    suspend fun deleteBudget(entity: BudgetEntity) = budgetDao.deleteBudget(entity)

    suspend fun getBudgetUsage(userId: String, month: Int, year: Int): List<BudgetUsage> {
        val (start, end) = DateUtils.getMonthRange(month, year)
        return budgetDao.getBudgetListByMonth(userId, month, year).map { b ->
            val spent = transactionDao.getTotalExpenseByCategory(userId, b.category, start, end) ?: 0.0
            BudgetUsage(b, spent, b.limitAmount - spent, if (b.limitAmount == 0.0) 0 else ((spent / b.limitAmount) * 100).toInt())
        }
    }

    suspend fun seedDefaultBudgetsIfEmpty(userId: String, month: Int, year: Int) {
        // Sengaja dikosongkan.
        // Akun baru harus mulai dari 0 tanpa anggaran contoh/default.
    }
}

data class BudgetUsage(
    val budget: BudgetEntity,
    val spent: Double,
    val remaining: Double,
    val percentage: Int
)
