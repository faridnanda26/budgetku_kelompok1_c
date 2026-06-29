package com.budgetku.app.ui.dashboard

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.budgetku.app.BudgetKuApplication
import com.budgetku.app.R
import com.budgetku.app.data.local.entity.TransactionEntity
import com.budgetku.app.databinding.FragmentDashboardBinding
import com.budgetku.app.ui.budget.adapter.BudgetAdapter
import com.budgetku.app.ui.transaction.adapter.TransactionAdapter
import com.budgetku.app.util.CurrencyFormatter
import com.budgetku.app.viewmodel.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var budgetViewModel: BudgetViewModel
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var budgetAdapter: BudgetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userId = authViewModel.currentUserId() ?: run { findNavController().navigate(R.id.loginFragment); return }
        val app = requireActivity().application as BudgetKuApplication
        transactionViewModel = ViewModelProvider(this, TransactionViewModelFactory(app.container.transactionRepository, userId))[TransactionViewModel::class.java]
        budgetViewModel = ViewModelProvider(this, BudgetViewModelFactory(app.container.budgetRepository, userId))[BudgetViewModel::class.java]
        setupRecycler()
        setupClicks()
        observe()
    }

    private fun setupRecycler() {
        transactionAdapter = TransactionAdapter(
            onClick = { transaction ->
                val bundle = Bundle().apply {
                    putString("TRANSACTION_ID", transaction.id)
                    putBoolean("IS_INCOME", transaction.type == "INCOME")
                }
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.dashboardFragment, false)
                    .setLaunchSingleTop(true)
                    .build()
                findNavController().navigate(R.id.addTransactionFragment, bundle, navOptions)
            },
            onLongClick = { transaction ->
                showDeleteConfirmationDialog(transaction)
            }
        )

        budgetAdapter = BudgetAdapter { budgetViewModel.deleteBudget(it.budget) }

        binding.rvRecentTransactions.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecentTransactions.adapter = transactionAdapter
        binding.rvRecentTransactions.isNestedScrollingEnabled = false

        binding.rvBudgets.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvBudgets.adapter = budgetAdapter
    }

    private fun setupClicks() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.dashboardFragment, false)
            .setLaunchSingleTop(true)
            .build()

        binding.btnAddIncome.setOnClickListener {
            val bundle = Bundle().apply { putBoolean("IS_INCOME", true) }
            findNavController().navigate(R.id.addTransactionFragment, bundle, navOptions)
        }

        binding.btnAddExpense.setOnClickListener {
            val bundle = Bundle().apply { putBoolean("IS_INCOME", false) }
            findNavController().navigate(R.id.addTransactionFragment, bundle, navOptions)
        }

        binding.tvSeeAll.setOnClickListener {
            findNavController().navigate(R.id.transactionHistoryFragment, null, navOptions)
        }

        binding.ivLogout.setOnClickListener {
            authViewModel.logout()
            val options = NavOptions.Builder()
                .setPopUpTo(R.id.nav_graph, true)
                .build()
            findNavController().navigate(R.id.loginFragment, null, options)
        }
    }

    private fun observe() {
        binding.tvGreeting.text = "Halo, ${authViewModel.currentEmail()?.substringBefore('@') ?: "User"} 👋"
        transactionViewModel.summaryState.observe(viewLifecycleOwner) {
            binding.tvBalance.text = CurrencyFormatter.format(it.balance)
            binding.tvIncome.text = CurrencyFormatter.formatShort(it.totalIncome)
            binding.tvExpense.text = CurrencyFormatter.formatShort(it.totalExpense)
        }
        transactionViewModel.transactions.observe(viewLifecycleOwner) {
            transactionAdapter.submitList(it.take(5))
            binding.tvEmptyRecent.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        }
        budgetViewModel.budgetUsage.observe(viewLifecycleOwner) {
            budgetAdapter.submitList(it)
            binding.tvEmptyBudget.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun showDeleteConfirmationDialog(transaction: TransactionEntity) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Hapus Transaksi")
            .setMessage("Apakah Anda yakin ingin menghapus transaksi ini? Tindakan ini tidak dapat dibatalkan.")
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Hapus") { dialog, _ ->
                transactionViewModel.deleteTransaction(transaction)
                Snackbar.make(binding.root, "Transaksi berhasil dihapus", Snackbar.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .show()
    }

    override fun onResume() {
        super.onResume()
        if (::transactionViewModel.isInitialized) transactionViewModel.loadMonthlySummary()
        if (::budgetViewModel.isInitialized) budgetViewModel.loadBudgetUsage()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}