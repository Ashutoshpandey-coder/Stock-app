package bhandar.stock.ledger.stock.management.models;

public class StoreModelRequest {
    String storeId;
    String name;
    String businessId;

    public StoreModelRequest(String storeId, String name, String businessId) {
        this.storeId = storeId;
        this.name = name;
        this.businessId = businessId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
}
