package mx.com.weather.yanabit.pablonolasco.yanaweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Daily implements Parcelable {
    private String mName;
    private String mDescription;
    private String mProbability;

    public Daily() {
    }

    protected Daily(Parcel in) {
        mName = in.readString();
        mDescription = in.readString();
        mProbability = in.readString();
    }

    public static final Creator<Daily> CREATOR = new Creator<Daily>() {
        @Override
        public Daily createFromParcel(Parcel in) {
            return new Daily(in);
        }

        @Override
        public Daily[] newArray(int size) {
            return new Daily[size];
        }
    };

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmProbability() {
        return mProbability;
    }

    public void setmProbability(String mProbability) {
        this.mProbability = mProbability;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(mName);
            parcel.writeString(mDescription);
            parcel.writeString(mProbability);
    }
}
