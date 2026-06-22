package com.budgetku.app.di;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/budgetku/app/di/AppContainer;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "budgetRepository", "Lcom/budgetku/app/data/repository/BudgetRepository;", "getBudgetRepository", "()Lcom/budgetku/app/data/repository/BudgetRepository;", "database", "Lcom/budgetku/app/data/local/BudgetDatabase;", "firebaseService", "Lcom/budgetku/app/data/remote/FirebaseService;", "transactionRepository", "Lcom/budgetku/app/data/repository/TransactionRepository;", "getTransactionRepository", "()Lcom/budgetku/app/data/repository/TransactionRepository;", "app_debug"})
public final class AppContainer {
    @org.jetbrains.annotations.NotNull()
    private final com.budgetku.app.data.local.BudgetDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.budgetku.app.data.remote.FirebaseService firebaseService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.budgetku.app.data.repository.TransactionRepository transactionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.budgetku.app.data.repository.BudgetRepository budgetRepository = null;
    
    public AppContainer(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.budgetku.app.data.repository.TransactionRepository getTransactionRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.budgetku.app.data.repository.BudgetRepository getBudgetRepository() {
        return null;
    }
}