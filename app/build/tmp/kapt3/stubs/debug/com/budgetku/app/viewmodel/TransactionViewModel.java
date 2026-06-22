package com.budgetku.app.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J.\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020\fJ\u000e\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u0016J\u0006\u0010%\u001a\u00020\fJ\u001e\u0010&\u001a\u00020\f2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0005R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/budgetku/app/viewmodel/TransactionViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/budgetku/app/data/repository/TransactionRepository;", "userId", "", "(Lcom/budgetku/app/data/repository/TransactionRepository;Ljava/lang/String;)V", "_summaryState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/budgetku/app/viewmodel/SummaryState;", "_uiState", "Lcom/budgetku/app/util/Resource;", "", "filterCategory", "Lkotlinx/coroutines/flow/MutableStateFlow;", "filterType", "summaryState", "Landroidx/lifecycle/LiveData;", "getSummaryState", "()Landroidx/lifecycle/LiveData;", "transactions", "", "Lcom/budgetku/app/data/local/entity/TransactionEntity;", "getTransactions", "uiState", "getUiState", "addTransaction", "amount", "", "type", "category", "description", "date", "", "clearFilter", "deleteTransaction", "entity", "loadMonthlySummary", "setFilter", "app_debug"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
public final class TransactionViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.budgetku.app.data.repository.TransactionRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userId = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> filterType = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> filterCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.budgetku.app.data.local.entity.TransactionEntity>> transactions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.budgetku.app.viewmodel.SummaryState> _summaryState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.budgetku.app.viewmodel.SummaryState> summaryState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.budgetku.app.util.Resource<kotlin.Unit>> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.budgetku.app.util.Resource<kotlin.Unit>> uiState = null;
    
    public TransactionViewModel(@org.jetbrains.annotations.NotNull()
    com.budgetku.app.data.repository.TransactionRepository repository, @org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.budgetku.app.data.local.entity.TransactionEntity>> getTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.budgetku.app.viewmodel.SummaryState> getSummaryState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.budgetku.app.util.Resource<kotlin.Unit>> getUiState() {
        return null;
    }
    
    public final void addTransaction(double amount, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String description, long date) {
    }
    
    public final void deleteTransaction(@org.jetbrains.annotations.NotNull()
    com.budgetku.app.data.local.entity.TransactionEntity entity) {
    }
    
    public final void setFilter(@org.jetbrains.annotations.Nullable()
    java.lang.String type, @org.jetbrains.annotations.Nullable()
    java.lang.String category) {
    }
    
    public final void clearFilter() {
    }
    
    public final void loadMonthlySummary() {
    }
}