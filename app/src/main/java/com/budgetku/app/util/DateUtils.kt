package com.budgetku.app.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {
    fun getCurrentMonthRange(): Pair<Long, Long> {
        val cal = Calendar.getInstance()
        return getMonthRange(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR))
    }

    fun getMonthRange(month: Int, year: Int): Pair<Long, Long> {
        val cal = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month - 1)
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val start = cal.timeInMillis
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH))
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 999)
        return Pair(start, cal.timeInMillis)
    }

    fun currentMonth(): Int = Calendar.getInstance().get(Calendar.MONTH) + 1
    fun currentYear(): Int = Calendar.getInstance().get(Calendar.YEAR)

    fun formatDate(timestamp: Long): String =
        SimpleDateFormat("dd MMM yyyy", Locale("id", "ID")).format(Date(timestamp))

    fun formatDateShort(timestamp: Long): String =
        SimpleDateFormat("dd MMM", Locale("id", "ID")).format(Date(timestamp))

    fun formatMonthYear(month: Int, year: Int): String {
        val cal = Calendar.getInstance().apply {
            set(Calendar.MONTH, month - 1)
            set(Calendar.YEAR, year)
        }
        return SimpleDateFormat("MMMM yyyy", Locale("id", "ID")).format(cal.time)
    }

    fun monthLabel(month: Int): String {
        val cal = Calendar.getInstance().apply { set(Calendar.MONTH, month - 1) }
        return SimpleDateFormat("MMM", Locale("id", "ID")).format(cal.time)
    }
}
