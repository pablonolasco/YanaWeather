package mx.com.weather.yanabit.pablonolasco.yanaweather.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.weather.yanabit.pablonolasco.yanaweather.R;
import mx.com.weather.yanabit.pablonolasco.yanaweather.model.HourVO;

public class HourlyWeatherAdapter extends BaseAdapter {
    private ArrayList<HourVO>mHourVOArrayList;
    private Context mContext;

    public HourlyWeatherAdapter(ArrayList<HourVO> mHourVOArrayList, Context mContext) {
        this.mHourVOArrayList = mHourVOArrayList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if (mHourVOArrayList == null)return 0;
        return mHourVOArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHourVOArrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            ViewHolder viewHolder;

            if (view == null){
                viewHolder= new ViewHolder();
                view= LayoutInflater.from(mContext).inflate(R.layout.item_hourly,null);
                viewHolder.hour=view.findViewById(R.id.tv_hour_hourly_list);
                viewHolder.probability =view.findViewById(R.id.tv_state_hourly_list);
                view.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder) view.getTag();
            }

            viewHolder.hour.setText(mHourVOArrayList.get(i).getmHour());
            viewHolder.probability.setText(mHourVOArrayList.get(i).getmProbability());

        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    static class ViewHolder{
        TextView hour;
        TextView probability;
    }
}
