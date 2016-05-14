package com.example.chidoke.h2omonitor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by Harrison on 6/11/2015.
 */

public class ListRowViewHolder extends RecyclerView.ViewHolder {

    protected NetworkImageView thumbnail;
    protected TextView remark;
    protected TextView distance;
    protected ImageView imageview;
    protected RelativeLayout relativeLayout;

    public ListRowViewHolder(View view) {
        super(view);

        this.remark = (TextView) view.findViewById(R.id.remarktextView);
        this.distance = (TextView) view.findViewById(R.id.datatextView);
        this.imageview = (ImageView) view.findViewById(R.id.data_image);
        this.relativeLayout = (RelativeLayout) view.findViewById(R.id.myrelative);
        view.setClickable(true);
    }

}
