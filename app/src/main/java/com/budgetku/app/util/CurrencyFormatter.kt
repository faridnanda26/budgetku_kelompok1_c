package com.budgetku.app.util

import java.text.NumberFormat
import java.util.Locale

object CurrencyFormatter {
    private val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID")).apply {
        maximumFractionDigits = 0
    }

    fun format(amount: Double): String = formatter.format(amount)

    fun formatShort(amount: Double): String = when {
        amount >= 1_000_000 -> "Rp ${String.format(Locale("id", "ID"), "%.1f", amount / 1_000_000)}jt"
        amount >= 1_000 -> "Rp ${String.format(Locale("id", "ID"), "%.0f", amount / 1_000)}rb"
        else -> format(amount)
    }

    fun parseRupiah(text: String): Double {
        return text.filter { it.isDigit() }.toDoubleOrNull() ?: 0.0
    }
}
