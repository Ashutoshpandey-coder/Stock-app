package stock.register.godown.stock.management.shop.stock.ledger.models;

public class StoreInfo {
    String storeName;
    String storeId;
    int stockedItems;

    public StoreInfo(String storeName, String storeId, int stockedItems) {
        this.storeName = storeName;
        this.storeId = storeId;
        this.stockedItems = stockedItems;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public int getStockedItems() {
        return stockedItems;
    }

    public void setStockedItems(int stockedItems) {
        this.stockedItems = stockedItems;
    }
}
