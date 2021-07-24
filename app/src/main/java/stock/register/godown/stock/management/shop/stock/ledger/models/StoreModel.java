package stock.register.godown.stock.management.shop.stock.ledger.models;

public class StoreModel {
    String storeId;
    String name;
    String businessId;

    public StoreModel(String storeId, String name, String businessId) {
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
