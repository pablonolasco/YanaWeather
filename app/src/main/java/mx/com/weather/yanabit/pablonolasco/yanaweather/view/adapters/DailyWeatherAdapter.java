package mx.com.weather.yanabit.pablonolasco.yanaweather.view.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.weather.yanabit.pablonolasco.yanaweather.R;
import mx.com.weather.yanabit.pablonolasco.yanaweather.model.Daily;

public class DailyWeatherAdapter extends BaseAdapter{
    private ArrayList<Daily>dailyArrayList;
    private Context context;

    public DailyWeatherAdapter(ArrayList<Daily> dailyArrayList, Context context) {
        this.dailyArrayList = dailyArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (dailyArrayList == null) return 0;
        return dailyArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return dailyArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            ViewHolde viewHolde;

            if (view == null){
                viewHolde= new ViewHolde();
                view= LayoutInflater.from(context).inflate(R.layout.item_daily,null);
                viewHolde.tittle=(TextView)view.findViewById(R.id.tv_tittle_list);
                viewHolde.descripcion=(TextView)view.findViewById(R.id.tv_description_list);
                viewHolde.probability=(TextView)view.findViewById(R.id.tv_probality_list);
                view.setTag(viewHolde);
            }else{
                viewHolde=(ViewHolde) view.getTag();
            }

            viewHolde.tittle.setText(dailyArrayList.get(i).getmName());
            viewHolde.descripcion.setText(dailyArrayList.get(i).getmDescription());
            viewHolde.probability.setText(dailyArrayList.get(i).getmProbability());
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

    static class ViewHolde{
        TextView tittle;
        TextView descripcion;
        TextView probability;
    }
}
