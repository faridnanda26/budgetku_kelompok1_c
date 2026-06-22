package com.budgetku.app.data.local.dao

import androidx.room.*
import com.budgetku.app.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

data class CategoryTotal(val category: String, val total: Double)

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions WHERE userId = :userId ORDER BY date DESC")
    fun getAllTransactions(userId: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE userId = :userId AND date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getTransactionsByDateRange(userId: String, startDate: Long, endDate: Long): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE userId = :userId AND type = :type ORDER BY date DESC")
    fun getTransactionsByType(userId: String, type: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE userId = :userId AND category = :category ORDER BY date DESC")
    fun getTransactionsByCategory(userId: String, category: String): Flow<List<TransactionEntity>>

    @Query("SELECT SUM(amount) FROM transactions WHERE userId = :userId AND type = 'INCOME' AND date BETWEEN :startDate AND :endDate")
    suspend fun getTotalIncome(userId: String, startDate: Long, endDate: Long): Double?

    @Query("SELECT SUM(amount) FROM transactions WHERE userId = :userId AND type = 'EXPENSE' AND date BETWEEN :startDate AND :endDate")
    suspend fun getTotalExpense(userId: String, startDate: Long, endDate: Long): Double?

    @Query("SELECT SUM(amount) FROM transactions WHERE userId = :userId AND category = :category AND type = 'EXPENSE' AND date BETWEEN :startDate AND :endDate")
    suspend fun getTotalExpenseByCategory(userId: String, category: String, startDate: Long, endDate: Long): Double?

    @Query("SELECT category, SUM(amount) AS total FROM transactions WHERE userId = :userId AND type = 'EXPENSE' AND date BETWEEN :startDate AND :endDate GROUP BY category")
    suspend fun getExpenseByCategory(userId: String, startDate: Long, endDate: Long): List<CategoryTotal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions WHERE isSynced = 0 AND userId = :userId")
    suspend fun getUnsyncedTransactions(userId: String): List<TransactionEntity>

    @Query("UPDATE transactions SET isSynced = 1 WHERE id = :id")
    suspend fun markAsSynced(id: String)

    @Query("SELECT COUNT(*) FROM transactions WHERE userId = :userId")
    suspend fun countTransactions(userId: String): Int
}
