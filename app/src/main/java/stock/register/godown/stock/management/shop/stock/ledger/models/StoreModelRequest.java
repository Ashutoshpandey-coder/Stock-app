package stock.register.godown.stock.management.shop.stock.ledger.models;

import android.os.Parcel;
import android.os.Parcelable;

public class StoreModelRequest implements Parcelable {
    String name;
    String businessId;

    public StoreModelRequest(String name, String businessId) {
        this.name = name;
        this.businessId = businessId;
    }

    protected StoreModelRequest(Parcel in) {
        name = in.readString();
        businessId = in.readString();
    }

    public StoreModelRequest(String name) {
        this.name = name;
    }

    public static final Creator<StoreModelRequest> CREATOR = new Creator<StoreModelRequest>() {
        @Override
        public StoreModelRequest createFromParcel(Parcel in) {
            return new StoreModelRequest(in);
        }

        @Override
        public StoreModelRequest[] newArray(int size) {
            return new StoreModelRequest[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(businessId);
    }
}
