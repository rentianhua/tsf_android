package com.dumu.housego.adapter;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.entity.HistroyData;
import com.dumu.housego.entity.MyTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HistroyDataAdapter extends BaseAdapter {
	private List<HistroyData> histroys;
	private Context context;
	private LayoutInflater Inflater;
	private Typeface typeface;

	public HistroyDataAdapter(List<HistroyData> histroys, Context context) {
		super();
		this.histroys = histroys;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return histroys==null?0:histroys.size();
	}

	@Override
	public HistroyData getItem(int position) {

		return histroys.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_histroy_data, null);
			holder = new ViewHolder();
			holder.tv_histroy_type=(MyTextView) convertView.findViewById(R.id.tv_histroy_type);
			holder.tv_histroy_status=(MyTextView) convertView.findViewById(R.id.tv_histroy_status);
			holder.tvtime=(MyTextView) convertView.findViewById(R.id.tv_histroy_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		
		
		HistroyData n = getItem(position);
		holder.tv_histroy_type.setText(n.getType());
		
		holder.tv_histroy_status.setText(n.getAction());
		
		holder.tvtime.setText(n.getInputtime());
//		long time=Long.valueOf(n.getInputtime());
//		String t=TimeTurnDate.getStringDateMoreMORE(time);
//		holder.tvtime.setText(t);

		return convertView;
	}

	class ViewHolder {
		TextView tvtime;
		TextView tv_histroy_type;
		TextView tv_histroy_status;
	}

}
