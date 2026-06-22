package com.budgetku.app.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J,\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ,\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\'J\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/budgetku/app/data/local/dao/BudgetDao;", "", "deleteBudget", "", "budget", "Lcom/budgetku/app/data/local/entity/BudgetEntity;", "(Lcom/budgetku/app/data/local/entity/BudgetEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBudgetListByMonth", "", "userId", "", "month", "", "year", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBudgetsByMonth", "Lkotlinx/coroutines/flow/Flow;", "insertBudget", "markAsSynced", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface BudgetDao {
    
    @androidx.room.Query(value = "SELECT * FROM budgets WHERE userId = :userId AND month = :month AND year = :year ORDER BY category ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.budgetku.app.data.local.entity.BudgetEntity>> getBudgetsByMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, int month, int year);
    
    @androidx.room.Query(value = "SELECT * FROM budgets WHERE userId = :userId AND month = :month AND year = :year ORDER BY category ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBudgetListByMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, int month, int year, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.budgetku.app.data.local.entity.BudgetEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertBudget(@org.jetbrains.annotations.NotNull()
    com.budgetku.app.data.local.entity.BudgetEntity budget, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBudget(@org.jetbrains.annotations.NotNull()
    com.budgetku.app.data.local.entity.BudgetEntity budget, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE budgets SET isSynced = 1 WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAsSynced(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}