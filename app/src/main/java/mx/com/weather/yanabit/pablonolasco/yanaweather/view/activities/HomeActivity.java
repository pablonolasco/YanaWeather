package mx.com.weather.yanabit.pablonolasco.yanaweather.view.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.Timer;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.com.weather.yanabit.pablonolasco.yanaweather.R;
import mx.com.weather.yanabit.pablonolasco.yanaweather.model.CurrentWeather;
import mx.com.weather.yanabit.pablonolasco.yanaweather.model.Daily;
import mx.com.weather.yanabit.pablonolasco.yanaweather.model.HourVO;
import mx.com.weather.yanabit.pablonolasco.yanaweather.model.MinutelyVO;
import mx.com.weather.yanabit.pablonolasco.yanaweather.util.AlerDialogUtils;
import mx.com.weather.yanabit.pablonolasco.yanaweather.util.Constants;
import mx.com.weather.yanabit.pablonolasco.yanaweather.util.LocationGPSUtils;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.iv_clear_day)
    ImageView ivClearDay;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.tv_current_temp)
    TextView tvCurrentTemp;
    @BindView(R.id.tv_h_grade)
    TextView tvHGrade;
    @BindView(R.id.tv_l_grade)
    TextView tvLGrade;
    private CurrentWeather mCurrentWeather;
    private ArrayList<Daily> dailyArrayList;
    private ArrayList<HourVO> hourVOArrayList;
    private ArrayList<MinutelyVO> minutelyVOArrayList;
    @BindDrawable(R.drawable.clear_night)
    Drawable clear_night;
    /*String latitud = "37.8267";
    String longitud = "-122.423";*/
    String latitud = "";
    String longitud = "";
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        activedGPS();
        if (getStatusGPS(this)) {

            ArrayList<String> gps_array= getLatituLongitud();
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);

            String url = Constants.URL_BASE + Constants.API_KEY + gps_array.get(0) + "," + gps_array.get(1) + Constants.UNITS;

// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {
                                CurrentWeather mCurrentWeather = getmCurrentWeather(response);

                                ivClearDay.setImageDrawable(mCurrentWeather.getIconDrawableResource());
                                tvDescription.setText(mCurrentWeather.getmDescription());
                                tvCurrentTemp.setText(mCurrentWeather.getmCurrentTemperature());
                                tvHGrade.setText(String.format("H: %s°", mCurrentWeather.getmHighTemperature()));
                                tvLGrade.setText(String.format("L: %s°", mCurrentWeather.getmLowerTemperature()));
                                dailyArrayList = getDailyWeatherFromJson(response);
                                hourVOArrayList = getHourFromJson(response);
                                minutelyVOArrayList = getMinutelyFromJson(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error", "That didn't work!");
                    Toast.makeText(getApplicationContext(), R.string.error_conexion, Toast.LENGTH_SHORT).show();
                }
            });

// Add the request to the RequestQueue.
            queue.add(stringRequest);

        }else{
            showSimpleAlertDialog(this,"Mensaje","GPS Desactivado, ¿ Desea Activar GPS? ");
        }

    }

    public  void showSimpleAlertDialog(Context context, String title, String message){

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton(context.getString(R.string.txt_dialog_btn_acept), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getLatituLongitud();
                }
            });
            builder.setCancelable(false);
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getLatituLongitud(){
       final ArrayList<String>gps= new ArrayList<>();
       activedGPS();
        try {
            // Acquire a reference to the system Location Manager
            LocationManager locationManager =(LocationManager) HomeActivity.this.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates

            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    // Called when a new location is found by the network location provider.
                    gps.add(location.getLongitude()+"");
                    gps.add(location.getLatitude()+"");
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {}


                public void onProviderEnabled(String provider) {}

                public void onProviderDisabled(String provider) {}
            };
            int permissionCheck = ContextCompat.checkSelfPermission(HomeActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION);// Register the listener with the Location Manager to receive location updates
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gps;

    }

    private boolean getStatusGPS(Context context) {
        boolean flag = false;
        try {
            if (LocationGPSUtils.isGPSProvider(this) && LocationGPSUtils.isNetowrkProvider(this)) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;

    }

    private void activedGPS(){
        // Assume thisActivity is the current activity
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_DENIED){
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

    }

    public CurrentWeather getmCurrentWeather(String json) throws JSONException {
        CurrentWeather currentWeather = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObjectWeather = jsonObject.getJSONObject("currently");
            String summary = jsonObjectWeather.getString(Constants.SUMMARY);
            String icon = jsonObjectWeather.getString(Constants.ICON);
            String temperature = Math.round(jsonObjectWeather.getDouble("temperature")) + "";
            //get object Daily
            JSONObject jsonObjectDailyWeather = jsonObject.getJSONObject(Constants.DAILY);
            JSONArray jsonArrayDailyData = jsonObjectDailyWeather.getJSONArray(Constants.DATA);
            JSONObject jsonObjectData = jsonArrayDailyData.getJSONObject(0);
            String temHigh = Math.round(jsonObjectData.getDouble("temperatureHigh")) + "";
            String temLow = Math.round(jsonObjectData.getDouble("temperatureLow")) + "";
            currentWeather = new CurrentWeather(HomeActivity.this);
            currentWeather.setmDescription(summary);
            currentWeather.setmIconImage(icon);
            currentWeather.setmCurrentTemperature(temperature);
            currentWeather.setmHighTemperature(temHigh);
            currentWeather.setmLowerTemperature(temLow);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentWeather;
    }

    private ArrayList<Daily> getDailyWeatherFromJson(String json) throws JSONException {
        ArrayList<Daily> dailyArrayList = new ArrayList<>();
        DateFormat dataFormat = new SimpleDateFormat("EEEE");
        Timer timer = new Timer();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObjectDaily = jsonObject.getJSONObject(Constants.DAILY);
            JSONArray jsonArrayDaily = jsonObjectDaily.getJSONArray(Constants.DATA);
            Daily daily;
            for (int i = 0; i < jsonArrayDaily.length(); i++) {
                daily = new Daily();
                JSONObject jsonObjectDay = jsonArrayDaily.getJSONObject(i);
                //Multiplicacion en milisegundos
                String date = dataFormat.format(jsonObjectDay.getLong(Constants.TIME) * 1000);
                String probabilidad = "Probabilidad:" + jsonObjectDay.getDouble(Constants.PRECIP_PROBABILITY) * 1000 + "%";
                daily.setmName(date);
                daily.setmProbability(probabilidad);
                daily.setmDescription(jsonObjectDay.getString(Constants.SUMMARY));
                dailyArrayList.add(daily);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return dailyArrayList;
    }

    private ArrayList<HourVO> getHourFromJson(String json) throws JSONException {
        ArrayList<HourVO> hourVOArrayList = new ArrayList<>();
        HourVO hourVO;
        try {
            DateFormat dataFormat = new SimpleDateFormat("HH:mm");

            JSONObject jsonObjectHour = new JSONObject(json);
            String time = jsonObjectHour.getString(Constants.TIME_ZONE);
            dataFormat.setTimeZone(TimeZone.getTimeZone(time));

            JSONObject objectHour = jsonObjectHour.getJSONObject("hourly");
            JSONArray jsonArrayData = objectHour.getJSONArray(Constants.DATA);
            for (int i = 0; i < jsonArrayData.length(); i++) {
                hourVO = new HourVO();
                JSONObject hour = jsonArrayData.getJSONObject(i);
                String data = dataFormat.format(hour.getDouble(Constants.TIME) * 1000);
                hourVO.setmHour(data);
                hourVO.setmProbability(hour.getString(Constants.SUMMARY));
                hourVOArrayList.add(hourVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hourVOArrayList;
    }

    private ArrayList<MinutelyVO> getMinutelyFromJson(String json) throws JSONException {
        ArrayList<MinutelyVO> minutelyVOArrayList = new ArrayList<>();
        MinutelyVO minutelyVO = null;
        try {
            DateFormat dataFormat = new SimpleDateFormat("HH:mm");
            JSONObject jsonObjectMinutely = new JSONObject(json);
            String time = jsonObjectMinutely.getString(Constants.TIME_ZONE);
            dataFormat.setTimeZone(TimeZone.getTimeZone(time));

            JSONObject jsonMinutely = jsonObjectMinutely.getJSONObject("minutely");
            JSONArray jsonArrayMinutely = jsonMinutely.getJSONArray(Constants.DATA);
            for (int i = 0; i < jsonArrayMinutely.length(); i++) {
                minutelyVO = new MinutelyVO();
                JSONObject minutely = jsonArrayMinutely.getJSONObject(i);
                String tittle = dataFormat.format(minutely.getDouble(Constants.TIME) * 1000);
                String probabilidad = "Probabilidad:" + minutely.getDouble(Constants.PRECIP_PROBABILITY) * 1000 + "%";

                minutelyVO.setmHour(tittle);
                minutelyVO.setmState(probabilidad);
                minutelyVOArrayList.add(minutelyVO);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return minutelyVOArrayList;
    }

    @OnClick({R.id.tv_daily, R.id.tv_hourly, R.id.tv_minutely})
    public void onViewClicked(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.tv_daily:
                intent = new Intent(getApplicationContext(), DailyWeatherActivity.class);
                intent.putParcelableArrayListExtra(Constants.DAYS_ARRAY, dailyArrayList);
                startActivity(intent);

                break;
            case R.id.tv_hourly:
                intent = new Intent(getApplicationContext(), HourlyWeatherActivity.class);
                intent.putParcelableArrayListExtra(Constants.HOUR_ARRAY, hourVOArrayList);
                startActivity(intent);

                break;
            case R.id.tv_minutely:
                intent = new Intent(getApplicationContext(), MinutelyWatherActivity.class);
                intent.putParcelableArrayListExtra(Constants.MINUTELY_ARRAY, minutelyVOArrayList);
                startActivity(intent);

                break;
        }
    }
}
