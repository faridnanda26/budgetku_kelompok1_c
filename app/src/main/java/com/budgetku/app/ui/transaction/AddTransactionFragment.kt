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

    private var transactionId: String? = null
    private var isEditMode = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = authViewModel.currentUserId() ?: run { findNavController().navigate(R.id.loginFragment); return }
        val app = requireActivity().application as BudgetKuApplication
        viewModel = ViewModelProvider(this, TransactionViewModelFactory(app.container.transactionRepository, userId))[TransactionViewModel::class.java]

        transactionId = arguments?.getString("TRANSACTION_ID")
        isEditMode = transactionId != null

        setup()
        observe()

        if (isEditMode) {
            preFillData()
        }
    }

    private fun setup() {
        categoryAdapter = CategoryAdapter { selectedCategory = it }
        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.rvCategories.adapter = categoryAdapter

        binding.tvDate.text = DateUtils.formatDate(selectedDate)

        val isIncome = arguments?.getBoolean("IS_INCOME", false) ?: false

        if (isIncome) {
            selectedType = "INCOME"
            binding.toggleType.check(R.id.btnIncome)
            val list = Category.incomeCategories()
            selectedCategory = list.first()
            categoryAdapter.submitList(list)
        } else {
            selectedType = "EXPENSE"
            binding.toggleType.check(R.id.btnExpense)
            val list = Category.expenseCategories()
            selectedCategory = list.first()
            categoryAdapter.submitList(list)
        }

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

    private fun preFillData() {
        binding.toolbar.title = "Edit Transaksi"

        viewModel.transactions.observe(viewLifecycleOwner) { list ->
            val currentTx = list.find { it.id == transactionId } ?: return@observe

            // Set nominal uang
            formatting = true
            binding.etAmount.setText(CurrencyFormatter.format(currentTx.amount).replace("Rp", "").trim())
            formatting = false

            // Set deskripsi catatan
            binding.etDescription.setText(currentTx.description)

            // Set waktu tanggal lama
            selectedDate = currentTx.date
            binding.tvDate.text = DateUtils.formatDate(selectedDate)

            // Set jenis dan kategori lama
            selectedType = currentTx.type
            binding.toggleType.check(if (selectedType == "INCOME") R.id.btnIncome else R.id.btnExpense)

            val categoryList = if (selectedType == "INCOME") Category.incomeCategories() else Category.expenseCategories()
            categoryAdapter.submitList(categoryList)

            val savedCategory = Category.fromName(currentTx.category)
            selectedCategory = savedCategory

            binding.rvCategories.post {
                val position = categoryList.indexOf(savedCategory)
                if (position != -1) {
                    categoryAdapter.notifyItemChanged(position)
                }
            }
        }
    }

    private fun save() {
        val amount = CurrencyFormatter.parseRupiah(binding.etAmount.text?.toString().orEmpty())
        if (amount <= 0) {
            binding.tilAmount.error = "Jumlah harus lebih dari 0"
            return
        }
        binding.tilAmount.error = null
        val description = binding.etDescription.text?.toString()?.trim().orEmpty()

        if (isEditMode) {
            viewModel.updateTransaction(transactionId!!, amount, selectedType, selectedCategory.name, description, selectedDate)
        } else {
            viewModel.addTransaction(amount, selectedType, selectedCategory.name, description, selectedDate)
        }
    }

    private fun observe() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> binding.btnSave.isEnabled = false
                is Resource.Success -> {
                    binding.btnSave.isEnabled = true
                    val msg = if (isEditMode) "Transaksi diperbarui" else "Transaksi disimpan"
                    Snackbar.make(requireActivity().findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
                is Resource.Error -> { binding.btnSave.isEnabled = true; Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show() }
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}