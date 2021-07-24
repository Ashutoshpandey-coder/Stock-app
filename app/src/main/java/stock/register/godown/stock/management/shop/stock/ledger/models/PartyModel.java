package stock.register.godown.stock.management.shop.stock.ledger.models;

public class PartyModel {

    String partyId;
    String name;
    String mobile;
    String businessId;

    public PartyModel(String partyId, String name, String mobile, String businessId) {
        this.partyId = partyId;
        this.name = name;
        this.mobile = mobile;
        this.businessId = businessId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
}
