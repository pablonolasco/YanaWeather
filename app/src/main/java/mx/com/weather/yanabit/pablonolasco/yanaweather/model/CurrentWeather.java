package mx.com.weather.yanabit.pablonolasco.yanaweather.model;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

import butterknife.BindDrawable;
import butterknife.ButterKnife;
import mx.com.weather.yanabit.pablonolasco.yanaweather.R;
import mx.com.weather.yanabit.pablonolasco.yanaweather.util.Constants;

public class CurrentWeather implements Serializable {
    @BindDrawable(R.drawable.clear_night) Drawable clearNight;
    @BindDrawable(R.drawable.clear_day) Drawable clearDay;
    @BindDrawable(R.drawable.cloudy) Drawable cloudy;
    @BindDrawable(R.drawable.cloudy_night) Drawable cloudyNight;
    @BindDrawable(R.drawable.fog) Drawable fog;
    @BindDrawable(R.drawable.na) Drawable na;
    @BindDrawable(R.drawable.partly_cloudy) Drawable partlyCloudy;
    @BindDrawable(R.drawable.rain) Drawable rain;
    @BindDrawable(R.drawable.sleet) Drawable sleet;
    @BindDrawable(R.drawable.snow) Drawable snow;
    @BindDrawable(R.drawable.sunny) Drawable sunny;
    @BindDrawable(R.drawable.wind) Drawable wind;

    private String mDescription;
    private String mCurrentTemperature;
    private String mLowerTemperature;
    private String mHighTemperature;
    private String mIconImage;


    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmCurrentTemperature() {
        return mCurrentTemperature;
    }

    public void setmCurrentTemperature(String mCurrentTemperature) {
        this.mCurrentTemperature = mCurrentTemperature;
    }

    public String getmLowerTemperature() {
        return mLowerTemperature;
    }

    public void setmLowerTemperature(String mLowerTemperature) {
        this.mLowerTemperature = mLowerTemperature;
    }

    public String getmHighTemperature() {
        return mHighTemperature;
    }

    public void setmHighTemperature(String mHighTemperature) {
        this.mHighTemperature = mHighTemperature;
    }

    public String getmIconImage() {
        return mIconImage;
    }

    public void setmIconImage(String mIconImage) {
        this.mIconImage = mIconImage;
    }

    public CurrentWeather() {
    }

    public CurrentWeather(Activity activity) {
        ButterKnife.bind(this,activity);
    }

    public Drawable getIconDrawableResource(){
        switch (getmIconImage()) {
            case Constants.CLEAR_NIGHT:
                return clearNight;
            case Constants.CLEAR_DAY:
                return clearDay;
            case Constants.CLOUDY:
                return cloudy;
            case Constants.PARTLY_CLOUDY_NIGHT:
                return cloudyNight;
            case Constants.FOG:
                return fog;
            case Constants.NA:
                return na;
            case Constants.PARTLY_CLOUDY_DAY:
                return partlyCloudy;
            case Constants.RAIN:
                return rain;
            case Constants.SLEET:
                return sleet;
            case Constants.SNOW:
                return snow;
            case Constants.SUNNY:
                return sunny;
            case Constants.WIND:
                return wind;
            default:
                return na;

        }
    }
}
