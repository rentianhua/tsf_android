package com.dumu.housego.adapter;

import java.text.ParseException;
import java.util.List;

import com.dumu.housego.R;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.YuYueData;
import com.dumu.housego.util.NoUnderlineSpan;
import com.dumu.housego.util.TimeTurnDate;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyYuYueDateLsitAdapter extends BaseAdapter {
	private List<YuYueData> yuyuedatas;
	private Context context;
	private LayoutInflater Inflater;
	private Typeface typeface;

	public MyYuYueDateLsitAdapter(List<YuYueData> yuyuedatas, Context context) {
		super();
		this.yuyuedatas = yuyuedatas;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return yuyuedatas==null?0:yuyuedatas.size();
	}

	@Override
	public YuYueData getItem(int position) {

		return yuyuedatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_myyuyue_kanfang2, null);
			holder = new ViewHolder();
			holder.tvTitle = (MyTextView) convertView.findViewById(R.id.myyuyue_kanfang_title);
			holder.tvYuYueDate = (MyTextView) convertView.findViewById(R.id.myyuyue_kanfang_time);
			holder.tvZhuangtai = (MyTextView) convertView.findViewById(R.id.myyuyue_kanfang_zhuangtai);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		YuYueData n = getItem(position);
		String a[] = n.getYuyuetime().split("-");
		String b = a[1];
		// 预约超时处理
		try {
			long currenttime = System.currentTimeMillis();
			String gmtTime = n.getYuyuedate() + " " + b;
			long times = TimeTurnDate.getLongByGMT(gmtTime);

			if (times < currenttime) {
				holder.tvZhuangtai.setText("已过期");
				holder.tvZhuangtai.setBackgroundResource(R.drawable.daikan_gray);
			}else{
				holder.tvZhuangtai.setText(n.getZhuangtai());
				holder.tvZhuangtai.setBackgroundResource(R.drawable.daikan_blue);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}



		holder.tvTitle.setText(n.getTitle() + " ");
		holder.tvYuYueDate.setText(n.getYuyuedate() + " " + n.getYuyuetime());
	
		
		
		return convertView;
	}

	class ViewHolder {
		TextView tvTitle;
		TextView tvZhuangtai;
		TextView tvYuYueDate;
		
	}

}
