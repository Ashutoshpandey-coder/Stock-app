package stock.register.godown.stock.management.shop.stock.ledger.models;

import java.util.ArrayList;
import java.util.List;

public class StockItemModelRequest {
    String name;
    String purchasePrice;
    String sellingPrice;
    String mrp;
    String priceUnit;
    List<StockStoreDetails> stockStoreDetails ;

    public StockItemModelRequest(String name, String purchasePrice, String sellingPrice, String mrp, String priceUnit, List<StockStoreDetails> stockStoreDetails) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.mrp = mrp;
        this.priceUnit = priceUnit;
        this.stockStoreDetails = stockStoreDetails;
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

    public List<StockStoreDetails> getStockStoreDetails() {
        return stockStoreDetails;
    }

    public void setStockStoreDetails(List<StockStoreDetails> stockStoreDetails) {
        this.stockStoreDetails = stockStoreDetails;
    }
}
