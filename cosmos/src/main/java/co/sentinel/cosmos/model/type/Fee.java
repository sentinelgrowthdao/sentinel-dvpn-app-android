package co.sentinel.cosmos.model.type;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class Fee implements Parcelable {

    @SerializedName("gas")
    public String gas;

    @SerializedName("amount")
    public ArrayList<Coin> amount;

    @Nullable
    @SerializedName("granter")
    public String granter;


    public Fee() {
    }

    public Fee(String gas, ArrayList<Coin> amount, @Nullable String granter) {
        this.gas = gas;
        this.amount = amount;
        this.granter = granter;
    }

    protected Fee(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        gas = in.readString();
        amount = new ArrayList<>();
        in.readTypedList(amount, Coin.CREATOR);
        granter = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gas);
        dest.writeTypedList(amount);
        dest.writeString(granter);
    }

    public static final Creator<Fee> CREATOR = new Creator<Fee>() {
        @Override
        public Fee createFromParcel(Parcel in) {
            return new Fee(in);
        }

        @Override
        public Fee[] newArray(int size) {
            return new Fee[size];
        }
    };
}
