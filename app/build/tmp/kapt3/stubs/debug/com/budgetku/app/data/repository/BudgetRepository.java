package com.budgetku.app.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ,\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0017J&\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/budgetku/app/data/repository/BudgetRepository;", "", "budgetDao", "Lcom/budgetku/app/data/local/dao/BudgetDao;", "transactionDao", "Lcom/budgetku/app/data/local/dao/TransactionDao;", "firebaseService", "Lcom/budgetku/app/data/remote/FirebaseService;", "(Lcom/budgetku/app/data/local/dao/BudgetDao;Lcom/budgetku/app/data/local/dao/TransactionDao;Lcom/budgetku/app/data/remote/FirebaseService;)V", "addBudget", "", "entity", "Lcom/budgetku/app/data/local/entity/BudgetEntity;", "(Lcom/budgetku/app/data/local/entity/BudgetEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBudget", "getBudgetUsage", "", "Lcom/budgetku/app/data/repository/BudgetUsage;", "userId", "", "month", "", "year", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "seedDefaultBudgetsIfEmpty", "app_debug"})
public final class BudgetRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.budgetku.app.data.local.dao.BudgetDao budgetDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.budgetku.app.data.local.dao.TransactionDao transactionDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.budgetku.app.data.remote.FirebaseService firebaseService = null;
    
    public BudgetRepository(@org.jetbrains.annotations.NotNull()
    com.budgetku.app.data.local.dao.BudgetDao budgetDao, @org.jetbrains.annotations.NotNull()
    com.budgetku.app.data.local.dao.TransactionDao transactionDao, @org.jetbrains.annotations.NotNull()
    com.budgetku.app.data.remote.FirebaseService firebaseService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addBudget(@org.jetbrains.annotations.NotNull()
    com.budgetku.app.data.local.entity.BudgetEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteBudget(@org.jetbrains.annotations.NotNull()
    com.budgetku.app.data.local.entity.BudgetEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getBudgetUsage(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, int month, int year, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.budgetku.app.data.repository.BudgetUsage>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object seedDefaultBudgetsIfEmpty(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, int month, int year, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}