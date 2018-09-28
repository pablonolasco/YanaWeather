package mx.com.weather.yanabit.pablonolasco.yanaweather.view.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import mx.com.weather.yanabit.pablonolasco.yanaweather.R;
import mx.com.weather.yanabit.pablonolasco.yanaweather.model.Daily;
import mx.com.weather.yanabit.pablonolasco.yanaweather.util.Constants;
import mx.com.weather.yanabit.pablonolasco.yanaweather.view.adapters.DailyWeatherAdapter;

public class DailyWeatherActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather);
        Intent intent= getIntent();
        ArrayList<Daily>dailyArrayList= intent.getParcelableArrayListExtra(Constants.DAYS_ARRAY);

        DailyWeatherAdapter dailyWeatherAdapter= new DailyWeatherAdapter(dailyArrayList,this);
        setListAdapter(dailyWeatherAdapter);
    }
}
