package com.budgetku.app.ui.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.budgetku.app.BudgetKuApplication
import com.budgetku.app.data.model.Category
import com.budgetku.app.databinding.FragmentStatisticsBinding
import com.budgetku.app.util.CurrencyFormatter
import com.budgetku.app.viewmodel.*
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class StatisticsFragment : Fragment() {
    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var viewModel: StatisticsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userId = authViewModel.currentUserId() ?: return
        val app = requireActivity().application as BudgetKuApplication
        viewModel = ViewModelProvider(this, StatisticsViewModelFactory(app.container.transactionRepository, userId))[StatisticsViewModel::class.java]
        viewModel.state.observe(viewLifecycleOwner) {
            setupPieChart(it.expenseByCategory)
            setupBarChart(it.monthlyTrend)
            binding.tvTotalExpense.text = "Total pengeluaran: ${CurrencyFormatter.format(it.expenseByCategory.values.sum())}"
        }
    }

    private fun setupPieChart(data: Map<String, Double>) {
        binding.pieChart.apply {
            description.isEnabled = false
            isDrawHoleEnabled = true
            holeRadius = 55f
            setEntryLabelColor(Color.TRANSPARENT)
            setNoDataText("Belum ada data pengeluaran")
        }

        if (data.isEmpty()) {
            binding.pieChart.clear()
            binding.pieChart.invalidate()
            return
        }

        val entries = data.map { PieEntry(it.value.toFloat(), Category.fromName(it.key).label) }
        val dataSet = PieDataSet(entries, "").apply {
            colors = listOf(Color.parseColor("#FF6B6B"), Color.parseColor("#4ECDC4"), Color.parseColor("#45B7D1"), Color.parseColor("#96CEB4"), Color.parseColor("#FFEAA7"), Color.parseColor("#DDA0DD"))
            valueTextColor = Color.WHITE
            valueTextSize = 12f
            sliceSpace = 3f
        }
        binding.pieChart.apply {
            this.data = PieData(dataSet)
            animateY(1000)
            invalidate()
        }
    }

    private fun setupBarChart(data: List<MonthlyTrend>) {
        val incomeSet = BarDataSet(data.mapIndexed { i, it -> BarEntry(i.toFloat(), it.income.toFloat()) }, "Pemasukan").apply {
            color = Color.parseColor("#27AE60")
            valueTextColor = Color.TRANSPARENT
        }
        val expenseSet = BarDataSet(data.mapIndexed { i, it -> BarEntry(i.toFloat(), it.expense.toFloat()) }, "Pengeluaran").apply {
            color = Color.parseColor("#E74C3C")
            valueTextColor = Color.TRANSPARENT
        }
        binding.barChart.apply {
            this.data = BarData(incomeSet, expenseSet).apply { barWidth = 0.35f }
            description.isEnabled = false
            xAxis.valueFormatter = IndexAxisValueFormatter(data.map { it.label })
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1f
            axisRight.isEnabled = false
            animateY(1000)
            invalidate()
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
