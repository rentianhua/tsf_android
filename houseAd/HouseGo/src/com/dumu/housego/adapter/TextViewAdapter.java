package com.dumu.housego.adapter;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.QiuzuANDQiuGou;
import com.dumu.housego.util.TimeTurnDate;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TextViewAdapter extends BaseAdapter {
	private List<String> blocktrades;
	private Context context;
	private LayoutInflater Inflater;
	private Typeface typeface;

	public TextViewAdapter(List<String> blocktrades, Context context) {
		super();
		this.blocktrades = blocktrades;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return blocktrades==null?0:blocktrades.size();
	}

	@Override
	public String getItem(int position) {

		return blocktrades.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_list_forphone, null);
			holder = new ViewHolder();
			holder.tv_qiuzu_area=(TextView) convertView.findViewById(R.id.tv_spinner_show);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String n = getItem(position);
		
		
		holder.tv_qiuzu_area.setText(n);

		return convertView;
	}

	class ViewHolder {
		TextView tv_qiuzu_area;
	}

}
