package com.budgetku.app.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016\u00a8\u0006\u0018"}, d2 = {"Lcom/budgetku/app/data/model/Category;", "", "label", "", "icon", "", "colorHex", "(Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;)V", "getColorHex", "()Ljava/lang/String;", "getIcon", "()I", "getLabel", "MAKANAN", "TRANSPORTASI", "BELANJA", "HIBURAN", "KESEHATAN", "PENDIDIKAN", "GAJI", "FREELANCE", "INVESTASI", "LAINNYA", "Companion", "app_debug"})
public enum Category {
    /*public static final*/ MAKANAN /* = new MAKANAN(null, 0, null) */,
    /*public static final*/ TRANSPORTASI /* = new TRANSPORTASI(null, 0, null) */,
    /*public static final*/ BELANJA /* = new BELANJA(null, 0, null) */,
    /*public static final*/ HIBURAN /* = new HIBURAN(null, 0, null) */,
    /*public static final*/ KESEHATAN /* = new KESEHATAN(null, 0, null) */,
    /*public static final*/ PENDIDIKAN /* = new PENDIDIKAN(null, 0, null) */,
    /*public static final*/ GAJI /* = new GAJI(null, 0, null) */,
    /*public static final*/ FREELANCE /* = new FREELANCE(null, 0, null) */,
    /*public static final*/ INVESTASI /* = new INVESTASI(null, 0, null) */,
    /*public static final*/ LAINNYA /* = new LAINNYA(null, 0, null) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String label = null;
    private final int icon = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String colorHex = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.budgetku.app.data.model.Category.Companion Companion = null;
    
    Category(java.lang.String label, int icon, java.lang.String colorHex) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLabel() {
        return null;
    }
    
    public final int getIcon() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getColorHex() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.budgetku.app.data.model.Category> getEntries() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a8\u0006\n"}, d2 = {"Lcom/budgetku/app/data/model/Category$Companion;", "", "()V", "expenseCategories", "", "Lcom/budgetku/app/data/model/Category;", "fromName", "name", "", "incomeCategories", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.budgetku.app.data.model.Category fromName(@org.jetbrains.annotations.NotNull()
        java.lang.String name) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.budgetku.app.data.model.Category> expenseCategories() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.budgetku.app.data.model.Category> incomeCategories() {
            return null;
        }
    }
}