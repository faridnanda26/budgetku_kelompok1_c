package com.budgetku.app.ui.transaction;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u0012H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0014\u0010\r\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/budgetku/app/ui/transaction/TransactionHistoryFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/budgetku/app/databinding/FragmentTransactionHistoryBinding;", "adapter", "Lcom/budgetku/app/ui/transaction/adapter/TransactionAdapter;", "authViewModel", "Lcom/budgetku/app/viewmodel/AuthViewModel;", "getAuthViewModel", "()Lcom/budgetku/app/viewmodel/AuthViewModel;", "authViewModel$delegate", "Lkotlin/Lazy;", "binding", "getBinding", "()Lcom/budgetku/app/databinding/FragmentTransactionHistoryBinding;", "data", "", "Lcom/budgetku/app/data/local/entity/TransactionEntity;", "viewModel", "Lcom/budgetku/app/viewmodel/TransactionViewModel;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "view", "showDeleteConfirmationDialog", "transaction", "app_debug"})
public final class TransactionHistoryFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.budgetku.app.databinding.FragmentTransactionHistoryBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy authViewModel$delegate = null;
    private com.budgetku.app.viewmodel.TransactionViewModel viewModel;
    private com.budgetku.app.ui.transaction.adapter.TransactionAdapter adapter;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.budgetku.app.data.local.entity.TransactionEntity> data;
    
    public TransactionHistoryFragment() {
        super();
    }
    
    private final com.budgetku.app.databinding.FragmentTransactionHistoryBinding getBinding() {
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
    
    private final void showDeleteConfirmationDialog(com.budgetku.app.data.local.entity.TransactionEntity transaction) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}