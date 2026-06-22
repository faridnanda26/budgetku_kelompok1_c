package com.budgetku.app.ui.budget.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.budgetku.app.data.model.Category
import com.budgetku.app.data.repository.BudgetUsage
import com.budgetku.app.databinding.ItemBudgetBinding
import com.budgetku.app.util.CurrencyFormatter

class BudgetAdapter(private val onDelete: (BudgetUsage) -> Unit) :
    ListAdapter<BudgetUsage, BudgetAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemBudgetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BudgetUsage) {
            val category = Category.fromName(item.budget.category)
            binding.ivCategory.setImageResource(category.icon)
            binding.tvCategory.text = category.label
            binding.tvAmount.text = "${CurrencyFormatter.formatShort(item.spent)} / ${CurrencyFormatter.formatShort(item.budget.limitAmount)}"
            binding.tvPercent.text = "${item.percentage}%"
            binding.progressBudget.progress = item.percentage.coerceIn(0, 100)
            binding.progressBudget.setIndicatorColor(Color.parseColor(when {
                item.percentage < 70 -> "#27AE60"
                item.percentage <= 90 -> "#F1C40F"
                else -> "#E74C3C"
            }))
            binding.ivWarning.visibility = if (item.percentage > 100) View.VISIBLE else View.GONE
            binding.root.setOnLongClickListener { onDelete(item); true }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemBudgetBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class DiffCallback : DiffUtil.ItemCallback<BudgetUsage>() {
        override fun areItemsTheSame(old: BudgetUsage, new: BudgetUsage) = old.budget.id == new.budget.id
        override fun areContentsTheSame(old: BudgetUsage, new: BudgetUsage) = old == new
    }
}
