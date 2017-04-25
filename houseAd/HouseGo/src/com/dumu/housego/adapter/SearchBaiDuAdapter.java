package com.dumu.housego.adapter;

import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.SearchXiaoQu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchBaiDuAdapter extends BaseAdapter {
	private List<String> xiaoqus;
	private Context context;
	private LayoutInflater Inflater;

	public SearchBaiDuAdapter(List<String> xiaoqus, Context context) {
		super();
		this.xiaoqus = xiaoqus;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return xiaoqus==null?0:xiaoqus.size();
	}

	@Override
	public String getItem(int position) {

		return xiaoqus.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_search_xiaoqu, null);
			holder = new ViewHolder();
			holder.tvSearchTitle = (MyTextView) convertView.findViewById(R.id.tv_search_title);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String n = getItem(position);
			
		holder.tvSearchTitle.setText(n);
		return convertView;

	}

	class ViewHolder {
		TextView tvSearchTitle;

	}
}
