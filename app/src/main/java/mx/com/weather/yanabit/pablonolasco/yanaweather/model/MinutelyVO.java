package mx.com.weather.yanabit.pablonolasco.yanaweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MinutelyVO implements Parcelable {
    private String mHour;
    private String mState;

    public MinutelyVO() {
    }

    protected MinutelyVO(Parcel in) {
        mHour = in.readString();
        mState = in.readString();
    }

    public static final Creator<MinutelyVO> CREATOR = new Creator<MinutelyVO>() {
        @Override
        public MinutelyVO createFromParcel(Parcel in) {
            return new MinutelyVO(in);
        }

        @Override
        public MinutelyVO[] newArray(int size) {
            return new MinutelyVO[size];
        }
    };

    public String getmHour() {
        return mHour;
    }

    public void setmHour(String mHour) {
        this.mHour = mHour;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mHour);
        parcel.writeString(mState);

    }
}
