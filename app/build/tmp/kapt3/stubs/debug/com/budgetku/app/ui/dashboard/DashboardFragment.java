package com.budgetku.app.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0017H\u0016J\b\u0010!\u001a\u00020\u0017H\u0016J\u001a\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010$\u001a\u00020\u0017H\u0002J\b\u0010%\u001a\u00020\u0017H\u0002J\u0010\u0010&\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020(H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/budgetku/app/ui/dashboard/DashboardFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/budgetku/app/databinding/FragmentDashboardBinding;", "authViewModel", "Lcom/budgetku/app/viewmodel/AuthViewModel;", "getAuthViewModel", "()Lcom/budgetku/app/viewmodel/AuthViewModel;", "authViewModel$delegate", "Lkotlin/Lazy;", "binding", "getBinding", "()Lcom/budgetku/app/databinding/FragmentDashboardBinding;", "budgetAdapter", "Lcom/budgetku/app/ui/budget/adapter/BudgetAdapter;", "budgetViewModel", "Lcom/budgetku/app/viewmodel/BudgetViewModel;", "transactionAdapter", "Lcom/budgetku/app/ui/transaction/adapter/TransactionAdapter;", "transactionViewModel", "Lcom/budgetku/app/viewmodel/TransactionViewModel;", "observe", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onViewCreated", "view", "setupClicks", "setupRecycler", "showDeleteConfirmationDialog", "transaction", "Lcom/budgetku/app/data/local/entity/TransactionEntity;", "app_debug"})
public final class DashboardFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.budgetku.app.databinding.FragmentDashboardBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy authViewModel$delegate = null;
    private com.budgetku.app.viewmodel.TransactionViewModel transactionViewModel;
    private com.budgetku.app.viewmodel.BudgetViewModel budgetViewModel;
    private com.budgetku.app.ui.transaction.adapter.TransactionAdapter transactionAdapter;
    private com.budgetku.app.ui.budget.adapter.BudgetAdapter budgetAdapter;
    
    public DashboardFragment() {
        super();
    }
    
    private final com.budgetku.app.databinding.FragmentDashboardBinding getBinding() {
        return null;
    }
    
    private final com.budgetku.app.viewmodel.AuthViewModel getAuthViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupRecycler() {
    }
    
    private final void setupClicks() {
    }
    
    private final void observe() {
    }
    
    private final void showDeleteConfirmationDialog(com.budgetku.app.data.local.entity.TransactionEntity transaction) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}