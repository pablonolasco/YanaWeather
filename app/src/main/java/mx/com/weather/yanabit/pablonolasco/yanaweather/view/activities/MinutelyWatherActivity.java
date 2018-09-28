package mx.com.weather.yanabit.pablonolasco.yanaweather.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.weather.yanabit.pablonolasco.yanaweather.R;
import mx.com.weather.yanabit.pablonolasco.yanaweather.model.MinutelyVO;
import mx.com.weather.yanabit.pablonolasco.yanaweather.util.Constants;
import mx.com.weather.yanabit.pablonolasco.yanaweather.view.adapters.MinutelyAdapter;

public class MinutelyWatherActivity extends AppCompatActivity {

    @BindView(R.id.rv_minutely)
    RecyclerView rvMinutely;
    @BindView(R.id.tv_message_data_minutely)
    TextView tvMessageDataMinutely;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minutely_wather);
        ButterKnife.bind(this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        rvMinutely.setLayoutManager(layoutManager);
        rvMinutely.setHasFixedSize(true);//indica que los elementos son de tamaño especifico
        Intent intent = getIntent();
        ArrayList<MinutelyVO> minutelyVOS = intent.getParcelableArrayListExtra(Constants.MINUTELY_ARRAY);
        if (minutelyVOS != null && !minutelyVOS.isEmpty()){
            tvMessageDataMinutely.setVisibility(View.GONE);
            rvMinutely.setVisibility(View.VISIBLE);//GGone no ocupa espacio en layout a differenca
            //de invisibilit
            MinutelyAdapter minutelyAdapter = new MinutelyAdapter(minutelyVOS, this);
            rvMinutely.setAdapter(minutelyAdapter);

        }else{
            tvMessageDataMinutely.setVisibility(View.VISIBLE);
            rvMinutely.setVisibility(View.GONE);//GGone no ocupa espacio en layout a differenca
            //de invisibility
        }

    }

}
