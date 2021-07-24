package stock.register.godown.stock.management.shop.stock.ledger.models;

public class PartyInfo {
    String CustomerName;
    String CreatedAt;

    public PartyInfo(String customer_name, String created_at) {
        CustomerName = customer_name;
        CreatedAt = created_at;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }
}
