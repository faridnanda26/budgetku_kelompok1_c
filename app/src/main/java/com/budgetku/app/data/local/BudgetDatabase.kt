package com.budgetku.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.budgetku.app.data.local.dao.BudgetDao
import com.budgetku.app.data.local.dao.TransactionDao
import com.budgetku.app.data.local.entity.BudgetEntity
import com.budgetku.app.data.local.entity.TransactionEntity

@Database(entities = [TransactionEntity::class, BudgetEntity::class], version = 1, exportSchema = false)
abstract class BudgetDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun budgetDao(): BudgetDao

    companion object {
        @Volatile private var INSTANCE: BudgetDatabase? = null

        fun getInstance(context: Context): BudgetDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, BudgetDatabase::class.java, "budgetku_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
