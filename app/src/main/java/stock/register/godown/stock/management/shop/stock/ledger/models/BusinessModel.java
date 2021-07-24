package stock.register.godown.stock.management.shop.stock.ledger.models;

public class BusinessModel {
    String businessId;
    String businessName;
    String fullName;
    String mobile;
    String currencyCode;

    public BusinessModel(String businessId, String businessName, String fullName, String mobile, String currencyCode) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.fullName = fullName;
        this.mobile = mobile;
        this.currencyCode = currencyCode;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
