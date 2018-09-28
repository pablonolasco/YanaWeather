package mx.com.weather.yanabit.pablonolasco.yanaweather.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import mx.com.weather.yanabit.pablonolasco.yanaweather.R;
import mx.com.weather.yanabit.pablonolasco.yanaweather.view.activities.HomeActivity;

public class LauncherActivity extends AppCompatActivity {
    private static final int sDALAY=2000;
    private static final Timer sTIMER= new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        TimerTask timerTask= new TimerTask() {
            @Override
            public void run() {
                Intent intent= new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();
            }
        };
        sTIMER.schedule(timerTask,sDALAY);

    }
}
