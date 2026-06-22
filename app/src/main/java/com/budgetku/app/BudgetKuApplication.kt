package com.budgetku.app

import android.app.Application
import com.budgetku.app.di.AppContainer

class BudgetKuApplication : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
