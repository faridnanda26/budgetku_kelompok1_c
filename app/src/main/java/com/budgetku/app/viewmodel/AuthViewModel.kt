package com.budgetku.app.viewmodel

import androidx.lifecycle.*
import com.budgetku.app.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val _authState = MutableLiveData<Resource<FirebaseUser>>()
    val authState: LiveData<Resource<FirebaseUser>> = _authState

    fun login(email: String, password: String) {
        _authState.value = Resource.Loading
        viewModelScope.launch {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                _authState.value = Resource.Success(result.user ?: error("User tidak ditemukan"))
            } catch (e: Exception) {
                _authState.value = Resource.Error(e.message ?: "Login gagal")
            }
        }
    }

    fun register(name: String, email: String, password: String) {
        _authState.value = Resource.Loading
        viewModelScope.launch {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val user = result.user ?: error("User tidak ditemukan")
                // Akun baru dibuat dalam kondisi kosong/0.
                // Transaksi dan anggaran tidak dibuat otomatis agar data tiap akun benar-benar mulai dari nol.
                val initialUserData = mapOf(
                    "name" to name,
                    "email" to email,
                    "createdAt" to System.currentTimeMillis(),
                    "totalIncome" to 0.0,
                    "totalExpense" to 0.0,
                    "balance" to 0.0,
                    "totalBudget" to 0.0,
                    "totalBudgetUsed" to 0.0
                )
                db.collection("users").document(user.uid).set(initialUserData).await()

                db.collection("users").document(user.uid)
                    .collection("app_state")
                    .document("summary")
                    .set(
                        mapOf(
                            "totalIncome" to 0.0,
                            "totalExpense" to 0.0,
                            "balance" to 0.0,
                            "totalBudget" to 0.0,
                            "totalBudgetUsed" to 0.0,
                            "updatedAt" to System.currentTimeMillis()
                        )
                    ).await()

                _authState.value = Resource.Success(user)
            } catch (e: Exception) {
                _authState.value = Resource.Error(e.message ?: "Registrasi gagal")
            }
        }
    }

    fun isLoggedIn() = auth.currentUser != null
    fun currentUserId(): String? = auth.currentUser?.uid
    fun currentEmail(): String? = auth.currentUser?.email
    fun logout() = auth.signOut()
}
