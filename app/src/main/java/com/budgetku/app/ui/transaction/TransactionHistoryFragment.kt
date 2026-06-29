package com.budgetku.app.ui.transaction

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.budgetku.app.BudgetKuApplication
import com.budgetku.app.R
import com.budgetku.app.data.local.entity.TransactionEntity
import com.budgetku.app.databinding.FragmentTransactionHistoryBinding
import com.budgetku.app.ui.transaction.adapter.TransactionAdapter
import com.budgetku.app.viewmodel.AuthViewModel
import com.budgetku.app.viewmodel.TransactionViewModel
import com.budgetku.app.viewmodel.TransactionViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class TransactionHistoryFragment : Fragment() {
    private var _binding: FragmentTransactionHistoryBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var viewModel: TransactionViewModel
    private lateinit var adapter: TransactionAdapter
    private var data: List<TransactionEntity> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTransactionHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userId = authViewModel.currentUserId() ?: return
        val app = requireActivity().application as BudgetKuApplication
        viewModel = ViewModelProvider(this, TransactionViewModelFactory(app.container.transactionRepository, userId))[TransactionViewModel::class.java]

        adapter = TransactionAdapter(
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

        binding.rvTransactions.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTransactions.adapter = adapter

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false
            override fun onSwiped(vh: RecyclerView.ViewHolder, direction: Int) {
                val item = data.getOrNull(vh.bindingAdapterPosition) ?: return
                viewModel.deleteTransaction(item)
                Snackbar.make(binding.root, "Transaksi dihapus", Snackbar.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(binding.rvTransactions)

        binding.chipAll.setOnClickListener { viewModel.clearFilter() }
        binding.chipIncome.setOnClickListener { viewModel.setFilter(type = "INCOME") }
        binding.chipExpense.setOnClickListener { viewModel.setFilter(type = "EXPENSE") }
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }

        viewModel.transactions.observe(viewLifecycleOwner) {
            data = it
            adapter.submitList(it)
            binding.tvEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
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
                viewModel.deleteTransaction(transaction)
                Snackbar.make(binding.root, "Transaksi berhasil dihapus", Snackbar.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}