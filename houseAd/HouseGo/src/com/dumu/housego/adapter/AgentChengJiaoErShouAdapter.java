package com.dumu.housego.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.util.UrlFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AgentChengJiaoErShouAdapter extends BaseAdapter {
	private List<ErShouFangDetails> ershoudetails;
	private Context context;
	private LayoutInflater Inflater;
	private Typeface typeface;

	
	
	
	public AgentChengJiaoErShouAdapter(List<ErShouFangDetails> ershoudetails, Context context) {
		super();
		this.ershoudetails = ershoudetails;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return ershoudetails==null?0:ershoudetails.size();
	}

	@Override
	public ErShouFangDetails getItem(int position) {

		return ershoudetails.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_agent_chengjiao_ershou, null);
			holder = new ViewHolder();

			holder.ivchengjiaoershou = (ImageView) convertView.findViewById(R.id.iv_chengjiaoershou);
			holder.tvJunjia = (MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_junjia);
			holder.tvTitle = (MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_title);
			holder.tvZongjia = (MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_jine);
			holder.tvDate=(MyTextView) convertView.findViewById(R.id.tv_chengjiao_date);
			holder.tvHousedetail=(MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_housedetail);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		
		ErShouFangDetails n = getItem(position);

		String url = UrlFactory.TSFURL + n.getThumb();
		//DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
		//		.bitmapConfig(Bitmap.Config.RGB_565).build();
		//ImageLoader.getInstance().displayImage(url, holder.ivchengjiaoershou, options);
		Glide.with(context).load(url).into(holder.ivchengjiaoershou);

		int junjia ;
		if(n.getJianzhumianji().equals("0")){
			junjia=Integer.valueOf(n.getZongjia()) * 10000 ;
		}else{
			junjia= Integer.valueOf(n.getZongjia()) * 10000 / Integer.valueOf(n.getJianzhumianji());
		}
	
		
		
		
		

		holder.tvJunjia.setText(junjia + "元/平");
		holder.tvTitle.setText(n.getTitle());
		holder.tvZongjia.setText(n.getZongjia() + "万");
		long time=Long.valueOf(n.getUpdatetime());
		
		Log.e("timeeeeeeeeeeeeeee", "time="+time);
		holder.tvDate.setText(formatData(time));
		holder.tvHousedetail.setText(n.getChaoxiang()+"/"+n.getCeng()+"/"+n.getCurceng()+"层");

		return convertView;

	}

	class ViewHolder {
		MyTextView tvTitle;
		MyTextView tvHousedetail;
		MyTextView tvJunjia;
		MyTextView tvZongjia;
		ImageView ivchengjiaoershou;
		MyTextView tvDate;
		
	}
	
	public static String formatData( long timeStamp) {
		if (timeStamp == 0) {
			return "";
		}
		timeStamp = timeStamp * 1000;
		String result = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		result = format.format(new Date(timeStamp));
		return result;
	}
	
}
