
package stock.register.godown.stock.management.shop.stock.ledger.models;

public class StockTransactionModelRequest {
    String stockItemId;
    String TransactionTs;
    public enum TxnType{
        IN,
        OUT
    }

    String preTxnStockCount;
    String postTxnStockCount;
    int txnUnitPrice;

    public enum StockTxnEntity{
        STORE,
        PARTY
    }
    String sourceTxnEntityId;

    TxnType txnType;
    StockTxnEntity stockTxnEntity;

    public StockTransactionModelRequest(String stockItemId, String transaction_ts, String preTxnStockCount, String postTxnStockCount, int txnUnitPrice, String sourceTxnEntityId, TxnType txnType, StockTxnEntity stockTxnEntity) {
        this.stockItemId = stockItemId;
        this.TransactionTs = transaction_ts;
        this.preTxnStockCount = preTxnStockCount;
        this.postTxnStockCount = postTxnStockCount;
        this.txnUnitPrice = txnUnitPrice;
        this.sourceTxnEntityId = sourceTxnEntityId;
        this.txnType = txnType;
        this.stockTxnEntity = stockTxnEntity;
    }

    public String getStockItemId() {
        return stockItemId;
    }

    public void setStockItemId(String stockItemId) {
        this.stockItemId = stockItemId;
    }

    public String getTransactionTs() {
        return TransactionTs;
    }

    public void setTransactionTs(String transactionTs) {
        TransactionTs = transactionTs;
    }

    public String getPreTxnStockCount() {
        return preTxnStockCount;
    }

    public void setPreTxnStockCount(String preTxnStockCount) {
        this.preTxnStockCount = preTxnStockCount;
    }

    public String getPostTxnStockCount() {
        return postTxnStockCount;
    }

    public void setPostTxnStockCount(String postTxnStockCount) {
        this.postTxnStockCount = postTxnStockCount;
    }

    public int getTxnUnitPrice() {
        return txnUnitPrice;
    }

    public void setTxnUnitPrice(int txnUnitPrice) {
        this.txnUnitPrice = txnUnitPrice;
    }

    public String getSourceTxnEntityId() {
        return sourceTxnEntityId;
    }

    public void setSourceTxnEntityId(String sourceTxnEntityId) {
        this.sourceTxnEntityId = sourceTxnEntityId;
    }

    public TxnType getTxnType() {
        return txnType;
    }

    public void setTxnType(TxnType txnType) {
        this.txnType = txnType;
    }

    public StockTxnEntity getStockTxnEntity() {
        return stockTxnEntity;
    }

    public void setStockTxnEntity(StockTxnEntity stockTxnEntity) {
        this.stockTxnEntity = stockTxnEntity;
    }
}
