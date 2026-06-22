package com.budgetku.app.data.local.dao

import androidx.room.*
import com.budgetku.app.data.local.entity.BudgetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Query("SELECT * FROM budgets WHERE userId = :userId AND month = :month AND year = :year ORDER BY category ASC")
    fun getBudgetsByMonth(userId: String, month: Int, year: Int): Flow<List<BudgetEntity>>

    @Query("SELECT * FROM budgets WHERE userId = :userId AND month = :month AND year = :year ORDER BY category ASC")
    suspend fun getBudgetListByMonth(userId: String, month: Int, year: Int): List<BudgetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget: BudgetEntity)

    @Delete
    suspend fun deleteBudget(budget: BudgetEntity)

    @Query("UPDATE budgets SET isSynced = 1 WHERE id = :id")
    suspend fun markAsSynced(id: String)
}
