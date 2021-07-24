package stock.register.godown.stock.management.shop.stock.ledger.models;

public class AddMoreViewModel {
    String openingStockQuantity , availableCount, asOfDate;

    public AddMoreViewModel(String openingStockQuantity, String availableCount, String asOfDate) {
        this.openingStockQuantity = openingStockQuantity;
        this.availableCount = availableCount;
        this.asOfDate = asOfDate;
    }

    public String getOpeningStockQuantity() {
        return openingStockQuantity;
    }

    public void setOpeningStockQuantity(String openingStockQuantity) {
        this.openingStockQuantity = openingStockQuantity;
    }

    public String getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(String availableCount) {
        this.availableCount = availableCount;
    }

    public String getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(String asOfDate) {
        this.asOfDate = asOfDate;
    }
}
