package stock.register.godown.stock.management.shop.stock.ledger.models;

import java.util.List;

public class StorePartyReportModel {
    List<StoreInfo> StoreReports;
    List<PartyInfo> PartyReports;

    public StorePartyReportModel(List<StoreInfo> store_reports, List<PartyInfo> party_reports) {
        StoreReports = store_reports;
        PartyReports = party_reports;
    }

    public List<StoreInfo> getStoreReports() {
        return StoreReports;
    }

    public void setStoreReports(List<StoreInfo> storeReports) {
        StoreReports = storeReports;
    }

    public List<PartyInfo> getPartyReports() {
        return PartyReports;
    }

    public void setPartyReports(List<PartyInfo> partyReports) {
        PartyReports = partyReports;
    }
}
