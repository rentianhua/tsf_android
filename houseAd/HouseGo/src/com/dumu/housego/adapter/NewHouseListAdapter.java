package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.NewHouseList;
import com.dumu.housego.entity.Street;
import com.dumu.housego.util.UrlFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewHouseListAdapter extends BaseAdapter {
	private List<NewHouseList> newhouselists;
	private Context context;
	private LayoutInflater Inflater;

	public NewHouseListAdapter(List<NewHouseList> newhouselists, Context context) {
		super();
		this.newhouselists = newhouselists;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return newhouselists==null?0:newhouselists.size();
	}

	@Override
	public NewHouseList getItem(int position) {

		return newhouselists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_new_house_list, null);
			holder = new ViewHolder();
			holder.newhouselist_address = (MyTextView) convertView.findViewById(R.id.newhouselist_address);
			holder.newhouselist_price = (MyTextView) convertView.findViewById(R.id.newhouselist_price);
			holder.newhouselist_area = (MyTextView) convertView.findViewById(R.id.newhouselist_area);
			holder.newhouselist_title = (MyTextView) convertView.findViewById(R.id.newhouselist_title);
			holder.newhouselist_iv = (ImageView) convertView.findViewById(R.id.newhouselist_iv);
			holder.newhouselist_kaipandate = (MyTextView) convertView.findViewById(R.id.newhouselist_kaipandate);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		NewHouseList n = getItem(position);

		//DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
		//		.bitmapConfig(Bitmap.Config.RGB_565).build();
		if (n.getThumb().startsWith("https://www.tao")) {
			//ImageLoader.getInstance().displayImage(n.getThumb(), holder.newhouselist_iv, options);
			Glide.with(context).load(n.getThumb()).into(holder.newhouselist_iv);
		} else {
			String url = UrlFactory.TSFURL + n.getThumb();
			//ImageLoader.getInstance().displayImage(url, holder.newhouselist_iv, options);
			Glide.with(context).load(url).into(holder.newhouselist_iv);
		}

		//holder.newhouselist_address.setText(n.getLoupandizhi());
		String[] areas= n.getShiarea().split(",");
		String shi="";
		if(areas.length>1) {
			shi = areas[0] + "-" + areas[areas.length - 1];
		}else
		{
			shi = areas[0];
		}

		holder.newhouselist_address.setText(shi + "室/" + n.getMianjiarea()+ "平米");
		holder.newhouselist_title.setText(n.getTitle());
		if(n.getJunjia().equals("0")){
			holder.newhouselist_price.setText( "未定价");
		}else{
			holder.newhouselist_price.setText(n.getJunjia() + "元/㎡");
		}
		holder.newhouselist_area.setText(n.getCityname() + " " + n.getAreaname());

		holder.newhouselist_kaipandate.setText("开盘时间：" + n.getKaipandate());
		return convertView;
	}

	class ViewHolder {
		TextView newhouselist_address;
		TextView newhouselist_price;
		TextView newhouselist_area;
		TextView newhouselist_title;
		ImageView newhouselist_iv;
		TextView newhouselist_kaipandate;
	}

}
