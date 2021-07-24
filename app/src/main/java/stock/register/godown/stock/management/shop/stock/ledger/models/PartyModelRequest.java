package stock.register.godown.stock.management.shop.stock.ledger.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PartyModelRequest implements Parcelable {
    String partyId;
    String partyName;
    String partyMobileNumber;
    String business_id;

    protected PartyModelRequest(Parcel in) {
        partyId = in.readString();
        partyName = in.readString();
        partyMobileNumber = in.readString();
        business_id = in.readString();
    }

    public static final Creator<PartyModelRequest> CREATOR = new Creator<PartyModelRequest>() {
        @Override
        public PartyModelRequest createFromParcel(Parcel in) {
            return new PartyModelRequest(in);
        }

        @Override
        public PartyModelRequest[] newArray(int size) {
            return new PartyModelRequest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public PartyModelRequest(String partyId, String partyName, String partyMobileNumber, String business_id) {
        this.partyId = partyId;
        this.partyName = partyName;
        this.partyMobileNumber = partyMobileNumber;
        this.business_id = business_id;
    }

    public PartyModelRequest(String partyName) {
        this.partyName = partyName;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(partyId);
        dest.writeString(partyName);
        dest.writeString(partyMobileNumber);
        dest.writeString(business_id);
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPartyName() {
        return partyName;
    }

    public String getPartyMobileNumber() {
        return partyMobileNumber;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public static Creator<PartyModelRequest> getCREATOR() {
        return CREATOR;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public void setPartyMobileNumber(String partyMobileNumber) {
        this.partyMobileNumber = partyMobileNumber;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

}
