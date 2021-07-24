package stock.register.godown.stock.management.shop.stock.ledger.models;

import java.util.List;

public class StoreReportModel {
    int TotalStockIn;
    int TotalStockOut;
    List<ItemTransactionModel> ItemTransactions;

    public StoreReportModel(int total_stockIn, int total_stockOut, List<ItemTransactionModel> itemTransactions) {
        TotalStockIn = total_stockIn;
        TotalStockOut = total_stockOut;
        ItemTransactions = itemTransactions;
    }

    public int getTotalStockIn() {
        return TotalStockIn;
    }

    public void setTotalStockIn(int totalStockIn) {
        TotalStockIn = totalStockIn;
    }

    public int getTotalStockOut() {
        return TotalStockOut;
    }

    public void setTotalStockOut(int totalStockOut) {
        TotalStockOut = totalStockOut;
    }

    public List<ItemTransactionModel> getItemTransactions() {
        return ItemTransactions;
    }

    public void setItemTransactions(List<ItemTransactionModel> itemTransactions) {
        ItemTransactions = itemTransactions;
    }
}
