package mx.com.weather.yanabit.pablonolasco.yanaweather.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.weather.yanabit.pablonolasco.yanaweather.R;
import mx.com.weather.yanabit.pablonolasco.yanaweather.model.MinutelyVO;

public class MinutelyAdapter extends RecyclerView.Adapter<MinutelyAdapter.ViewHolder>{
    private ArrayList<MinutelyVO>minutelyVOArrayList;
    private Context context;

    public MinutelyAdapter(ArrayList<MinutelyVO> minutelyVOArrayList, Context context) {
        this.minutelyVOArrayList = minutelyVOArrayList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder=null;

        try {
            View view= LayoutInflater.from(context).inflate(R.layout.item_minutely,parent,false);
            viewHolder=new ViewHolder(view);
        }catch (Exception e){
            e.printStackTrace();
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try{
            holder.hour.setText(minutelyVOArrayList.get(position).getmHour());
            holder.state.setText(minutelyVOArrayList.get(position).getmState());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (minutelyVOArrayList == null)
            return 0;
        return minutelyVOArrayList.size();
    }


    class  ViewHolder extends RecyclerView.ViewHolder{
        private TextView hour;
        private  TextView state;
        public ViewHolder(View itemView) {
            super(itemView);
            try {
                hour=itemView.findViewById(R.id.tv_hour_minutely_list);
                state=itemView.findViewById(R.id.tv_probability_minutely_list);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
