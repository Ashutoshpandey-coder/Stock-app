package stock.register.godown.stock.management.shop.stock.ledger.models;

public class ItemTransactionModel {
    String ItemName;
    String ItemId;
    int TotalIn;
    int TotalOut;
    String OpeningStock;
    String ClosingStock;

    public ItemTransactionModel(String item_name, String item_id, int total_in, int total_out, String openingStock, String closingStock) {
        ItemName = item_name;
        ItemId = item_id;
        TotalIn = total_in;
        TotalOut = total_out;
        OpeningStock = openingStock;
        ClosingStock = closingStock;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public int getTotalIn() {
        return TotalIn;
    }

    public void setTotalIn(int totalIn) {
        TotalIn = totalIn;
    }

    public int getTotalOut() {
        return TotalOut;
    }

    public void setTotalOut(int totalOut) {
        TotalOut = totalOut;
    }

    public String getOpeningStock() {
        return OpeningStock;
    }

    public void setOpeningStock(String openingStock) {
        OpeningStock = openingStock;
    }

    public String getClosingStock() {
        return ClosingStock;
    }

    public void setClosingStock(String closingStock) {
        ClosingStock = closingStock;
    }
}
