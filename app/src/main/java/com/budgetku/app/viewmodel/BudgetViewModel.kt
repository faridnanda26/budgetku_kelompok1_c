package com.budgetku.app.viewmodel

import androidx.lifecycle.*
import com.budgetku.app.data.local.entity.BudgetEntity
import com.budgetku.app.data.repository.BudgetRepository
import com.budgetku.app.data.repository.BudgetUsage
import com.budgetku.app.util.DateUtils
import com.budgetku.app.util.Resource
import kotlinx.coroutines.launch

class BudgetViewModel(
    private val repository: BudgetRepository,
    private val userId: String
) : ViewModel() {
    private val _selectedMonth = MutableLiveData(DateUtils.currentMonth())
    val selectedMonth: LiveData<Int> = _selectedMonth
    private val _selectedYear = MutableLiveData(DateUtils.currentYear())
    val selectedYear: LiveData<Int> = _selectedYear

    private val _budgetUsage = MutableLiveData<List<BudgetUsage>>()
    val budgetUsage: LiveData<List<BudgetUsage>> = _budgetUsage

    private val _summary = MutableLiveData<BudgetSummary>()
    val summary: LiveData<BudgetSummary> = _summary

    private val _uiState = MutableLiveData<Resource<Unit>>()
    val uiState: LiveData<Resource<Unit>> = _uiState

    init {
        viewModelScope.launch {
            loadBudgetUsage()
        }
    }

    fun addBudget(category: String, limitAmount: Double) {
        viewModelScope.launch {
            _uiState.value = Resource.Loading
            try {
                repository.addBudget(BudgetEntity(userId = userId, category = category, limitAmount = limitAmount, month = currentMonth(), year = currentYear()))
                loadBudgetUsage()
                _uiState.value = Resource.Success(Unit)
            } catch (e: Exception) {
                _uiState.value = Resource.Error(e.message ?: "Gagal menyimpan anggaran")
            }
        }
    }

    fun deleteBudget(entity: BudgetEntity) {
        viewModelScope.launch {
            repository.deleteBudget(entity)
            loadBudgetUsage()
        }
    }

    fun nextMonth() {
        val m = currentMonth(); val y = currentYear()
        if (m == 12) { _selectedMonth.value = 1; _selectedYear.value = y + 1 } else _selectedMonth.value = m + 1
        loadBudgetUsage()
    }

    fun previousMonth() {
        val m = currentMonth(); val y = currentYear()
        if (m == 1) { _selectedMonth.value = 12; _selectedYear.value = y - 1 } else _selectedMonth.value = m - 1
        loadBudgetUsage()
    }

    fun loadBudgetUsage() {
        viewModelScope.launch {
            val usage = repository.getBudgetUsage(userId, currentMonth(), currentYear())
            _budgetUsage.value = usage
            val totalBudget = usage.sumOf { it.budget.limitAmount }
            val totalSpent = usage.sumOf { it.spent }
            val percent = if (totalBudget == 0.0) 0 else ((totalSpent / totalBudget) * 100).toInt()
            _summary.value = BudgetSummary(totalBudget, totalSpent, percent)
        }
    }

    private fun currentMonth() = _selectedMonth.value ?: DateUtils.currentMonth()
    private fun currentYear() = _selectedYear.value ?: DateUtils.currentYear()
}

data class BudgetSummary(val totalBudget: Double = 0.0, val totalSpent: Double = 0.0, val percentage: Int = 0)

class BudgetViewModelFactory(
    private val repository: BudgetRepository,
    private val userId: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BudgetViewModel::class.java)) return BudgetViewModel(repository, userId) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
