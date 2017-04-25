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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ATQiuGouLsitAdapter extends BaseAdapter {
	private List<QiuzuANDQiuGou> blocktrades;
	private Context context;
	private LayoutInflater Inflater;
	private Typeface typeface;

	public ATQiuGouLsitAdapter(List<QiuzuANDQiuGou> blocktrades, Context context) {
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
	public QiuzuANDQiuGou getItem(int position) {

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
			convertView = Inflater.inflate(R.layout.item_qiugou_at, null);
			holder = new ViewHolder();
			holder.tv_qiuzu_area=(MyTextView) convertView.findViewById(R.id.tv_qiugou_area);
			holder.tv_qiuzu_jine=(MyTextView) convertView.findViewById(R.id.tv_qiugou_jine);
			holder.tv_qiuzu_phone=(MyTextView) convertView.findViewById(R.id.tv_qiugou_phone);
			holder.tvtime=(MyTextView) convertView.findViewById(R.id.tv_gou_time);
			holder.delete=(ImageView) convertView.findViewById(R.id.delete);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		QiuzuANDQiuGou n = getItem(position);
		
//		if(n.getLock().equals("1")){
			holder.delete.setVisibility(View.GONE);
//		}else{
//			holder.delete.setVisibility(View.VISIBLE);
//		}
//		
//		holder.delete.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		holder.tv_qiuzu_area.setText("深圳"+n.getCity_name()+n.getArea_name());
		holder.tv_qiuzu_jine.setText(n.getZongjiarange()+"");
		holder.tv_qiuzu_phone.setText(n.getChenghu()+" "+n.getUsername());
		
		long time=Long.valueOf(n.getInputtime());
		String t=TimeTurnDate.getStringDateMoreMORE(time);
		holder.tvtime.setText(t);

		return convertView;
	}

	class ViewHolder {
		TextView tvtime;
		TextView tv_qiuzu_area;
		TextView tv_qiuzu_phone;
		TextView tv_qiuzu_jine;
		ImageView delete;
	}

}
