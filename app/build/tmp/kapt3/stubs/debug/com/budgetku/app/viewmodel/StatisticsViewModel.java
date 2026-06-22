package com.budgetku.app.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0082@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0012R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/budgetku/app/viewmodel/StatisticsViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/budgetku/app/data/repository/TransactionRepository;", "userId", "", "(Lcom/budgetku/app/data/repository/TransactionRepository;Ljava/lang/String;)V", "_state", "Landroidx/lifecycle/MutableLiveData;", "Lcom/budgetku/app/viewmodel/StatisticsState;", "state", "Landroidx/lifecycle/LiveData;", "getState", "()Landroidx/lifecycle/LiveData;", "buildSixMonthTrend", "", "Lcom/budgetku/app/viewmodel/MonthlyTrend;", "currentMonth", "", "currentYear", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadStatistics", "", "month", "year", "app_debug"})
public final class StatisticsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.budgetku.app.data.repository.TransactionRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userId = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.budgetku.app.viewmodel.StatisticsState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.budgetku.app.viewmodel.StatisticsState> state = null;
    
    public StatisticsViewModel(@org.jetbrains.annotations.NotNull()
    com.budgetku.app.data.repository.TransactionRepository repository, @org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.budgetku.app.viewmodel.StatisticsState> getState() {
        return null;
    }
    
    public final void loadStatistics(int month, int year) {
    }
    
    private final java.lang.Object buildSixMonthTrend(int currentMonth, int currentYear, kotlin.coroutines.Continuation<? super java.util.List<com.budgetku.app.viewmodel.MonthlyTrend>> $completion) {
        return null;
    }
}