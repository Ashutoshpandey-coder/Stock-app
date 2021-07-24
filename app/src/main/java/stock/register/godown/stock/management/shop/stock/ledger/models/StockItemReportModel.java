package stock.register.godown.stock.management.shop.stock.ledger.models;

public class StockItemReportModel {
    String stockItemId;
    String StockId;
    String name;
    String purchasePrice;
    String sellingPrice;
    String mrp;
    String priceUnit;
    int availableCount;

    public StockItemReportModel(String stockItemId, String StockId, String name, String purchasePrice, String sellingPrice, String mrp, String priceUnit, int availableCount) {
        this.stockItemId = stockItemId;
        this.StockId = StockId;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.mrp = mrp;
        this.priceUnit = priceUnit;
        this.availableCount = availableCount;
    }

    public String getStockItemId() {
        return stockItemId;
    }

    public void setStockItemId(String stockItemId) {
        this.stockItemId = stockItemId;
    }

    public String getStockId() {
        return StockId;
    }

    public void setStockId(String stockId) {
        this.StockId = stockId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }
}
