package com.example.chidoke.h2omonitor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends BaseAdapter {

	Context context;
	List<Data> rowItems;

	DataAdapter(Context context, List<Data> rowItems) {
		this.context = context;
		this.rowItems = rowItems;
	}

	@Override
	public int getCount() {
		return rowItems.size();
	}

	@Override
	public Object getItem(int position) {
		return rowItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return rowItems.indexOf(getItem(position));
	}

	/* private view holder class */
	private class ViewHolder {

		TextView remark;
		TextView distance;
        ImageView data_image;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.listitem_layout, null);
			holder = new ViewHolder();

			holder.remark = (TextView) convertView
					.findViewById(R.id.remarktextView);
			holder.distance = (TextView) convertView.findViewById(R.id.datatextView);

            holder.data_image = (ImageView) convertView
                    .findViewById(R.id.data_image);

            Data dev_pos = rowItems.get(position);

            //set the data image
            holder.data_image.setImageResource(dev_pos.getData_image());

            //set the remark name
            holder.remark.setText(dev_pos.getRemark());

            //set the data
            holder.distance.setText(dev_pos.getDistance());


			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
            convertView.setTag(holder);
		}

		return convertView;
	}

}


