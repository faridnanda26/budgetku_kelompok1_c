package com.budgetku.app.data.model

import com.budgetku.app.R

enum class Category(val label: String, val icon: Int, val colorHex: String) {
    MAKANAN("Makanan", R.drawable.ic_food, "#FF6B6B"),
    TRANSPORTASI("Transport", R.drawable.ic_transport, "#4ECDC4"),
    BELANJA("Belanja", R.drawable.ic_shopping, "#45B7D1"),
    HIBURAN("Hiburan", R.drawable.ic_entertainment, "#96CEB4"),
    KESEHATAN("Kesehatan", R.drawable.ic_health, "#FFEAA7"),
    PENDIDIKAN("Pendidikan", R.drawable.ic_education, "#DDA0DD"),
    GAJI("Gaji", R.drawable.ic_salary, "#27AE60"),
    FREELANCE("Freelance", R.drawable.ic_freelance, "#2ECC71"),
    INVESTASI("Investasi", R.drawable.ic_investment, "#1ABC9C"),
    LAINNYA("Lainnya", R.drawable.ic_other, "#BDC3C7");

    companion object {
        fun fromName(name: String): Category = values().find { it.name == name } ?: LAINNYA
        fun expenseCategories(): List<Category> = listOf(MAKANAN, TRANSPORTASI, BELANJA, HIBURAN, KESEHATAN, PENDIDIKAN, LAINNYA)
        fun incomeCategories(): List<Category> = listOf(GAJI, FREELANCE, INVESTASI, LAINNYA)
    }
}
