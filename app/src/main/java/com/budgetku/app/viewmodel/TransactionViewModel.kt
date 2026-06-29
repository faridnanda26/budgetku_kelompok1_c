package com.budgetku.app.viewmodel

import androidx.lifecycle.*
import com.budgetku.app.data.local.entity.TransactionEntity
import com.budgetku.app.data.repository.TransactionRepository
import com.budgetku.app.util.DateUtils
import com.budgetku.app.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class TransactionViewModel(
    private val repository: TransactionRepository,
    private val userId: String
) : ViewModel() {
    private val filterType = MutableStateFlow<String?>(null)
    private val filterCategory = MutableStateFlow<String?>(null)

    val transactions: LiveData<List<TransactionEntity>> =
        combine(filterType, filterCategory) { type, category -> type to category }
            .flatMapLatest { (type, category) -> repository.getFilteredTransactions(userId, type, category) }
            .asLiveData()

    private val _summaryState = MutableLiveData<SummaryState>()
    val summaryState: LiveData<SummaryState> = _summaryState

    private val _uiState = MutableLiveData<Resource<Unit>>()
    val uiState: LiveData<Resource<Unit>> = _uiState

    init {
        viewModelScope.launch {
            repository.syncPendingTransactions(userId)
            loadMonthlySummary()
        }
    }

    fun addTransaction(amount: Double, type: String, category: String, description: String, date: Long) {
        viewModelScope.launch {
            _uiState.value = Resource.Loading
            try {
                repository.addTransaction(TransactionEntity(userId = userId, amount = amount, type = type, category = category, description = description, date = date))
                loadMonthlySummary()
                _uiState.value = Resource.Success(Unit)
            } catch (e: Exception) {
                _uiState.value = Resource.Error(e.message ?: "Gagal menyimpan transaksi")
            }
        }
    }

    fun deleteTransaction(entity: TransactionEntity) {
        viewModelScope.launch {
            repository.deleteTransaction(entity)
            loadMonthlySummary()
        }
    }

    fun updateTransaction(id: String, amount: Double, type: String, category: String, description: String, date: Long) {
        viewModelScope.launch {
            _uiState.value = Resource.Loading
            try {
                repository.addTransaction(
                    TransactionEntity(
                        id = id,
                        userId = userId,
                        amount = amount,
                        type = type,
                        category = category,
                        description = description,
                        date = date
                    )
                )
                loadMonthlySummary()
                _uiState.value = Resource.Success(Unit)
            } catch (e: Exception) {
                _uiState.value = Resource.Error(e.message ?: "Gagal memperbarui transaksi")
            }
        }
    }

    fun setFilter(type: String? = null, category: String? = null) {
        filterType.value = type
        filterCategory.value = category
    }

    fun clearFilter() = setFilter()

    fun loadMonthlySummary() {
        viewModelScope.launch {
            val (start, end) = DateUtils.getCurrentMonthRange()
            val income = repository.getTotalIncome(userId, start, end)
            val expense = repository.getTotalExpense(userId, start, end)
            _summaryState.value = SummaryState(income, expense, income - expense)
        }
    }
}

data class SummaryState(val totalIncome: Double = 0.0, val totalExpense: Double = 0.0, val balance: Double = 0.0)

class TransactionViewModelFactory(
    private val repository: TransactionRepository,
    private val userId: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) return TransactionViewModel(repository, userId) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
