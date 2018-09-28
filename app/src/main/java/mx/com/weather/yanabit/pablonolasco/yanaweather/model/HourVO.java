package mx.com.weather.yanabit.pablonolasco.yanaweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class HourVO implements Parcelable {

    private String mHour;
    private String mProbability;

    public HourVO() {
    }

    protected HourVO(Parcel in) {
        mHour = in.readString();
        mProbability = in.readString();
    }

    public static final Creator<HourVO> CREATOR = new Creator<HourVO>() {
        @Override
        public HourVO createFromParcel(Parcel in) {
            return new HourVO(in);
        }

        @Override
        public HourVO[] newArray(int size) {
            return new HourVO[size];
        }
    };

    public String getmHour() {
        return mHour;
    }

    public void setmHour(String mHour) {
        this.mHour = mHour;
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
        parcel.writeString(mHour);
        parcel.writeString(mProbability);
    }
}
