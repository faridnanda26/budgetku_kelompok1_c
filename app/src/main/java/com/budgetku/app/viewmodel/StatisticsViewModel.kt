package com.budgetku.app.viewmodel

import androidx.lifecycle.*
import com.budgetku.app.data.repository.TransactionRepository
import com.budgetku.app.util.DateUtils
import kotlinx.coroutines.launch
import java.util.Calendar

class StatisticsViewModel(
    private val repository: TransactionRepository,
    private val userId: String
) : ViewModel() {
    private val _state = MutableLiveData<StatisticsState>()
    val state: LiveData<StatisticsState> = _state

    init { loadStatistics(DateUtils.currentMonth(), DateUtils.currentYear()) }

    fun loadStatistics(month: Int, year: Int) {
        viewModelScope.launch {
            val (start, end) = DateUtils.getMonthRange(month, year)
            val categoryTotals = repository.getExpenseByCategory(userId, start, end).associate { it.category to it.total }
            _state.value = StatisticsState(categoryTotals, buildSixMonthTrend(month, year))
        }
    }

    private suspend fun buildSixMonthTrend(currentMonth: Int, currentYear: Int): List<MonthlyTrend> {
        val cal = Calendar.getInstance().apply { set(Calendar.MONTH, currentMonth - 1); set(Calendar.YEAR, currentYear) }
        cal.add(Calendar.MONTH, -5)
        return (0 until 6).map {
            val m = cal.get(Calendar.MONTH) + 1
            val y = cal.get(Calendar.YEAR)
            val (start, end) = DateUtils.getMonthRange(m, y)
            val trend = MonthlyTrend(DateUtils.monthLabel(m), repository.getTotalIncome(userId, start, end), repository.getTotalExpense(userId, start, end))
            cal.add(Calendar.MONTH, 1)
            trend
        }
    }
}

data class StatisticsState(val expenseByCategory: Map<String, Double> = emptyMap(), val monthlyTrend: List<MonthlyTrend> = emptyList())
data class MonthlyTrend(val label: String, val income: Double, val expense: Double)

class StatisticsViewModelFactory(private val repository: TransactionRepository, private val userId: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatisticsViewModel::class.java)) return StatisticsViewModel(repository, userId) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
