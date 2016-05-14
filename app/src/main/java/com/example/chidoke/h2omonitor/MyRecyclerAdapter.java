package com.example.chidoke.h2omonitor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by Judekayode on 4/25/2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<ListRowViewHolder> {

    private List<Data> listItemsList;
    private Context mContext;
    private int focusedItem = 0;


    public MyRecyclerAdapter(Context context, List<Data> listItemsList) {
        this.listItemsList = listItemsList;
        this.mContext = context;
    }

    @Override
    public ListRowViewHolder onCreateViewHolder(final ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_layout, null);
        ListRowViewHolder holder = new ListRowViewHolder(v);


        return holder;
    }


    @Override
    public void onBindViewHolder(final ListRowViewHolder listRowViewHolder, int position) {
        Data listItems = listItemsList.get(position);
        listRowViewHolder.itemView.setSelected(focusedItem == position);

        listRowViewHolder.getLayoutPosition();


        listRowViewHolder.remark.setText(Html.fromHtml(listItems.getRemark()));
        listRowViewHolder.distance.setText(Html.fromHtml(listItems.getDistance()));
        listRowViewHolder.imageview.setImageResource(listItems.getData_image());

    }

    public void clearAdapter () {
        listItemsList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (null != listItemsList ? listItemsList.size() : 0);
    }
}
