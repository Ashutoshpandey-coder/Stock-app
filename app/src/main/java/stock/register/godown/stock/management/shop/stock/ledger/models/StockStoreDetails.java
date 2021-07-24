package stock.register.godown.stock.management.shop.stock.ledger.models;

public class StockStoreDetails {
    String storeId, availableCount, stockAddDate;

    public StockStoreDetails(String storeId, String availableCount, String stockAddDate) {
        this.storeId = storeId;
        this.availableCount = availableCount;
        this.stockAddDate = stockAddDate;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(String availableCount) {
        this.availableCount = availableCount;
    }

    public String getStockAddDate() {
        return stockAddDate;
    }

    public void setStockAddDate(String stockAddDate) {
        this.stockAddDate = stockAddDate;
    }
}
