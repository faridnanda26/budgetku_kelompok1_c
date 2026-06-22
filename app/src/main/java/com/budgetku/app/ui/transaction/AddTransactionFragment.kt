package com.budgetku.app.ui.transaction

import android.os.Bundle
import android.text.*
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.budgetku.app.BudgetKuApplication
import com.budgetku.app.R
import com.budgetku.app.data.model.Category
import com.budgetku.app.databinding.FragmentAddTransactionBinding
import com.budgetku.app.ui.transaction.adapter.CategoryAdapter
import com.budgetku.app.util.CurrencyFormatter
import com.budgetku.app.util.DateUtils
import com.budgetku.app.util.Resource
import com.budgetku.app.viewmodel.AuthViewModel
import com.budgetku.app.viewmodel.TransactionViewModel
import com.budgetku.app.viewmodel.TransactionViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar

class AddTransactionFragment : Fragment() {
    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var viewModel: TransactionViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private var selectedType = "EXPENSE"
    private var selectedCategory = Category.MAKANAN
    private var selectedDate = System.currentTimeMillis()
    private var formatting = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔑 1. Ambil inisialisasi dasar rujukan ID User & Repository yang dibutuhkan oleh fragment
        val userId = authViewModel.currentUserId() ?: run { findNavController().navigate(R.id.loginFragment); return }
        val app = requireActivity().application as BudgetKuApplication
        viewModel = ViewModelProvider(this, TransactionViewModelFactory(app.container.transactionRepository, userId))[TransactionViewModel::class.java]

        // 2. Jalankan fungsi setup view & observe data
        setup()
        observe()
    }

    private fun setup() {
        categoryAdapter = CategoryAdapter { selectedCategory = it }
        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.rvCategories.adapter = categoryAdapter

        binding.tvDate.text = DateUtils.formatDate(selectedDate)

        // 🟢 PERBAIKAN UTAMA: Ambil tanda kiriman dari DashboardFragment
        val isIncome = arguments?.getBoolean("IS_INCOME", false) ?: false

        if (isIncome) {
            selectedType = "INCOME"
            binding.toggleType.check(R.id.btnIncome) // Aktifkan tombol Pemasukan
            val list = Category.incomeCategories()
            selectedCategory = list.first()
            categoryAdapter.submitList(list)
        } else {
            selectedType = "EXPENSE"
            binding.toggleType.check(R.id.btnExpense) // Aktifkan tombol Pengeluaran
            val list = Category.expenseCategories()
            selectedCategory = list.first()
            categoryAdapter.submitList(list)
        }

        // Listener pendeteksi saat pengguna mengubah pilihan jenis transaksi secara manual
        binding.toggleType.addOnButtonCheckedListener { _, checkedId, checked ->
            if (!checked) return@addOnButtonCheckedListener
            selectedType = if (checkedId == R.id.btnIncome) "INCOME" else "EXPENSE"
            val list = if (selectedType == "INCOME") Category.incomeCategories() else Category.expenseCategories()
            selectedCategory = list.first()
            categoryAdapter.submitList(list)
        }

        binding.etAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
            override fun afterTextChanged(s: Editable?) {
                if (formatting) return
                val value = CurrencyFormatter.parseRupiah(s?.toString().orEmpty())
                if (value <= 0) return
                formatting = true
                binding.etAmount.setText(CurrencyFormatter.format(value).replace("Rp", "").trim())
                binding.etAmount.setSelection(binding.etAmount.text?.length ?: 0)
                formatting = false
            }
        })

        binding.btnPickDate.setOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker().setTitleText("Pilih tanggal").setSelection(selectedDate).build()
            picker.addOnPositiveButtonClickListener { selectedDate = it; binding.tvDate.text = DateUtils.formatDate(it) }
            picker.show(parentFragmentManager, "date_picker")
        }
        binding.btnSave.setOnClickListener { save() }
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun save() {
        val amount = CurrencyFormatter.parseRupiah(binding.etAmount.text?.toString().orEmpty())
        if (amount <= 0) {
            binding.tilAmount.error = "Jumlah harus lebih dari 0"
            return
        }
        binding.tilAmount.error = null
        viewModel.addTransaction(amount, selectedType, selectedCategory.name, binding.etDescription.text?.toString()?.trim().orEmpty(), selectedDate)
    }

    private fun observe() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> binding.btnSave.isEnabled = false
                is Resource.Success -> { binding.btnSave.isEnabled = true; findNavController().popBackStack() }
                is Resource.Error -> { binding.btnSave.isEnabled = true; Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show() }
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}