package com.budgetku.app.ui.budget

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.budgetku.app.BudgetKuApplication
import com.budgetku.app.data.model.Category
import com.budgetku.app.databinding.DialogAddBudgetBinding
import com.budgetku.app.databinding.FragmentBudgetBinding
import com.budgetku.app.ui.budget.adapter.BudgetAdapter
import com.budgetku.app.util.CurrencyFormatter
import com.budgetku.app.util.DateUtils
import com.budgetku.app.util.Resource
import com.budgetku.app.viewmodel.AuthViewModel
import com.budgetku.app.viewmodel.BudgetViewModel
import com.budgetku.app.viewmodel.BudgetViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class BudgetFragment : Fragment() {
    private var _binding: FragmentBudgetBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var viewModel: BudgetViewModel
    private lateinit var adapter: BudgetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userId = authViewModel.currentUserId() ?: return
        val app = requireActivity().application as BudgetKuApplication
        viewModel = ViewModelProvider(this, BudgetViewModelFactory(app.container.budgetRepository, userId))[BudgetViewModel::class.java]
        adapter = BudgetAdapter { viewModel.deleteBudget(it.budget) }
        binding.rvBudgets.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBudgets.adapter = adapter

        binding.btnAddBudget.setOnClickListener { showDialog() }
        binding.btnPrevMonth.setOnClickListener { viewModel.previousMonth() }
        binding.btnNextMonth.setOnClickListener { viewModel.nextMonth() }

        viewModel.selectedMonth.observe(viewLifecycleOwner) { updateMonth() }
        viewModel.selectedYear.observe(viewLifecycleOwner) { updateMonth() }
        viewModel.budgetUsage.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.tvEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        }
        viewModel.summary.observe(viewLifecycleOwner) {
            binding.tvTotalBudget.text = "Total Dianggarkan: ${CurrencyFormatter.format(it.totalBudget)}"
            binding.tvTotalSpent.text = "Terpakai: ${CurrencyFormatter.format(it.totalSpent)} (${it.percentage}%)"
            binding.progressTotal.progress = it.percentage.coerceIn(0, 100)
        }
        viewModel.uiState.observe(viewLifecycleOwner) {
            if (it is Resource.Error) Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun updateMonth() {
        binding.tvMonth.text = DateUtils.formatMonthYear(viewModel.selectedMonth.value ?: DateUtils.currentMonth(), viewModel.selectedYear.value ?: DateUtils.currentYear())
    }

    private fun showDialog() {
        val db = DialogAddBudgetBinding.inflate(layoutInflater)
        val categories = Category.expenseCategories()
        db.spinnerCategory.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categories.map { it.label })
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Tambah Anggaran")
            .setView(db.root)
            .setPositiveButton("Simpan") { _, _ ->
                val amount = CurrencyFormatter.parseRupiah(db.etBudgetAmount.text?.toString().orEmpty())
                if (amount > 0) viewModel.addBudget(categories[db.spinnerCategory.selectedItemPosition].name, amount)
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onResume() {
        super.onResume()
        if (::viewModel.isInitialized) viewModel.loadBudgetUsage()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
