package com.budgetku.app.ui.transaction.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.budgetku.app.data.model.Category
import com.budgetku.app.databinding.ItemCategoryBinding

class CategoryAdapter(private val onClick: (Category) -> Unit) :
    ListAdapter<Category, CategoryAdapter.ViewHolder>(DiffCallback()) {
    private var selected: Category? = null

    inner class ViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {
            binding.ivCategory.setImageResource(item.icon)
            binding.tvCategory.text = item.label
            binding.cardCategory.strokeWidth = if (selected == item) 4 else 0
            binding.cardCategory.strokeColor = Color.parseColor(item.colorHex)
            binding.cardCategory.setOnClickListener {
                selected = item
                onClick(item)
                notifyDataSetChanged()
            }
        }
    }

    override fun submitList(list: List<Category>?) {
        selected = list?.firstOrNull()
        super.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(old: Category, new: Category) = old.name == new.name
        override fun areContentsTheSame(old: Category, new: Category) = old == new
    }
}
