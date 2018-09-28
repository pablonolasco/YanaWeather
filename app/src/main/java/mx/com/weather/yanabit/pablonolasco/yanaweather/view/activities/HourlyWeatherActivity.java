package mx.com.weather.yanabit.pablonolasco.yanaweather.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.weather.yanabit.pablonolasco.yanaweather.R;
import mx.com.weather.yanabit.pablonolasco.yanaweather.model.HourVO;
import mx.com.weather.yanabit.pablonolasco.yanaweather.util.Constants;
import mx.com.weather.yanabit.pablonolasco.yanaweather.view.adapters.HourlyWeatherAdapter;

public class HourlyWeatherActivity extends AppCompatActivity {

    @BindView(R.id.lv_hourly)
    ListView lvHourly;
    @BindView(R.id.tv_message_data_hourly)
    TextView tv_message_data_hourly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_weather);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        ArrayList<HourVO> hourVOArrayList = intent.getParcelableArrayListExtra(Constants.HOUR_ARRAY);


        HourlyWeatherAdapter hourlyWeatherAdapter = new HourlyWeatherAdapter(hourVOArrayList, this);
        lvHourly.setAdapter(hourlyWeatherAdapter);
        lvHourly.setEmptyView(tv_message_data_hourly);
    }
}
