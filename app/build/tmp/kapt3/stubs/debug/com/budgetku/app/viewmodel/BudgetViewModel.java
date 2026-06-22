package com.budgetku.app.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020#J\b\u0010$\u001a\u00020\fH\u0002J\b\u0010%\u001a\u00020\fH\u0002J\u000e\u0010&\u001a\u00020\u00132\u0006\u0010\'\u001a\u00020(J\u0006\u0010)\u001a\u00020\u0013J\u0006\u0010*\u001a\u00020\u0013J\u0006\u0010+\u001a\u00020\u0013R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/budgetku/app/viewmodel/BudgetViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/budgetku/app/data/repository/BudgetRepository;", "userId", "", "(Lcom/budgetku/app/data/repository/BudgetRepository;Ljava/lang/String;)V", "_budgetUsage", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/budgetku/app/data/repository/BudgetUsage;", "_selectedMonth", "", "kotlin.jvm.PlatformType", "_selectedYear", "_summary", "Lcom/budgetku/app/viewmodel/BudgetSummary;", "_uiState", "Lcom/budgetku/app/util/Resource;", "", "budgetUsage", "Landroidx/lifecycle/LiveData;", "getBudgetUsage", "()Landroidx/lifecycle/LiveData;", "selectedMonth", "getSelectedMonth", "selectedYear", "getSelectedYear", "summary", "getSummary", "uiState", "getUiState", "addBudget", "category", "limitAmount", "", "currentMonth", "currentYear", "deleteBudget", "entity", "Lcom/budgetku/app/data/local/entity/BudgetEntity;", "loadBudgetUsage", "nextMonth", "previousMonth", "app_debug"})
public final class BudgetViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.budgetku.app.data.repository.BudgetRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userId = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _selectedMonth = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> selectedMonth = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _selectedYear = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> selectedYear = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.budgetku.app.data.repository.BudgetUsage>> _budgetUsage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.budgetku.app.data.repository.BudgetUsage>> budgetUsage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.budgetku.app.viewmodel.BudgetSummary> _summary = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.budgetku.app.viewmodel.BudgetSummary> summary = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.budgetku.app.util.Resource<kotlin.Unit>> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.budgetku.app.util.Resource<kotlin.Unit>> uiState = null;
    
    public BudgetViewModel(@org.jetbrains.annotations.NotNull()
    com.budgetku.app.data.repository.BudgetRepository repository, @org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getSelectedMonth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getSelectedYear() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.budgetku.app.data.repository.BudgetUsage>> getBudgetUsage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.budgetku.app.viewmodel.BudgetSummary> getSummary() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.budgetku.app.util.Resource<kotlin.Unit>> getUiState() {
        return null;
    }
    
    public final void addBudget(@org.jetbrains.annotations.NotNull()
    java.lang.String category, double limitAmount) {
    }
    
    public final void deleteBudget(@org.jetbrains.annotations.NotNull()
    com.budgetku.app.data.local.entity.BudgetEntity entity) {
    }
    
    public final void nextMonth() {
    }
    
    public final void previousMonth() {
    }
    
    public final void loadBudgetUsage() {
    }
    
    private final int currentMonth() {
        return 0;
    }
    
    private final int currentYear() {
        return 0;
    }
}