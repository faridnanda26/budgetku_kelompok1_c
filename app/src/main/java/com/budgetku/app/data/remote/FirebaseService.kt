package com.budgetku.app.data.remote

import com.budgetku.app.data.local.entity.BudgetEntity
import com.budgetku.app.data.local.entity.TransactionEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseService {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val userId: String get() = auth.currentUser?.uid ?: throw IllegalStateException("User belum login")

    suspend fun syncTransaction(entity: TransactionEntity) {
        db.collection("users").document(userId).collection("transactions")
            .document(entity.id).set(entity.toMap()).await()
    }

    suspend fun deleteTransaction(id: String) {
        db.collection("users").document(userId).collection("transactions")
            .document(id).delete().await()
    }

    suspend fun syncBudget(entity: BudgetEntity) {
        db.collection("users").document(userId).collection("budgets")
            .document(entity.id).set(entity.toMap()).await()
    }

    private fun TransactionEntity.toMap() = mapOf(
        "id" to id, "userId" to userId, "amount" to amount, "type" to type,
        "category" to category, "description" to description, "date" to date
    )

    private fun BudgetEntity.toMap() = mapOf(
        "id" to id, "userId" to userId, "category" to category,
        "limitAmount" to limitAmount, "month" to month, "year" to year
    )
}
