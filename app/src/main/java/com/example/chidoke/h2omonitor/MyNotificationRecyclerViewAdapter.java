package com.example.chidoke.h2omonitor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyNotificationRecyclerViewAdapter extends RecyclerView.Adapter<MyNotificationRecyclerViewAdapter.ViewHolder>{

    private List<Data> countries;
    private int rowLayout;
    private Context mContext;

    public MyNotificationRecyclerViewAdapter(List<Data> countries, int rowLayout, Context context) {
        this.countries = countries;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Data country = countries.get(i);
        viewHolder.countryName.setText(R.string.app_name);
        viewHolder.countrySize.setText(R.string.data);
        viewHolder.countryImage.setImageResource(R.drawable.critical);

    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView countryName;
        public TextView countrySize;
        public ImageView countryImage;

        public ViewHolder(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.remarktextView);
            countrySize = (TextView) itemView.findViewById(R.id.datatextView);
            countryImage = (ImageView)itemView.findViewById(R.id.data_image);
        }

    }
}
