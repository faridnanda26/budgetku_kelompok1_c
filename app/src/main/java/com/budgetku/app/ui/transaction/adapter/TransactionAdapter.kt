package com.budgetku.app.ui.transaction.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.budgetku.app.R
import com.budgetku.app.data.local.entity.TransactionEntity
import com.budgetku.app.data.model.Category
import com.budgetku.app.databinding.ItemTransactionBinding
import com.budgetku.app.util.CurrencyFormatter
import com.budgetku.app.util.DateUtils

class TransactionAdapter(
    private val onClick: (TransactionEntity) -> Unit,
    private val onLongClick: (TransactionEntity) -> Unit
) : ListAdapter<TransactionEntity, TransactionAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TransactionEntity) {
            val category = Category.fromName(item.category)
            val isIncome = item.type == "INCOME"

            binding.tvDescription.text = item.description.ifEmpty { category.label }
            binding.tvDate.text = DateUtils.formatDateShort(item.date)
            binding.tvCategory.text = category.label
            binding.ivCategoryIcon.setImageResource(category.icon)
            binding.cardCategory.setCardBackgroundColor(Color.parseColor(category.colorHex))
            binding.tvAmount.text = "${if (isIncome) "+" else "-"} ${CurrencyFormatter.format(item.amount)}"
            binding.tvAmount.setTextColor(ContextCompat.getColor(binding.root.context, if (isIncome) R.color.income_green else R.color.expense_red))

            binding.root.setOnClickListener { onClick(item) }

            binding.root.setOnLongClickListener {
                onLongClick(item)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class DiffCallback : DiffUtil.ItemCallback<TransactionEntity>() {
        override fun areItemsTheSame(old: TransactionEntity, new: TransactionEntity) = old.id == new.id
        override fun areContentsTheSame(old: TransactionEntity, new: TransactionEntity) = old == new
    }
}