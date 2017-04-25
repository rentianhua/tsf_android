package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.NewHouseHotRecommend;
import com.dumu.housego.entity.RentingTuijian;
import com.dumu.housego.util.UrlFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RentingTuijianAdapter extends BaseAdapter {
	private List<RentingTuijian> newhousehots;
	private Context context;
	private LayoutInflater Inflater;

	public RentingTuijianAdapter(List<RentingTuijian> newhousehots, Context context) {
		super();
		this.newhousehots = newhousehots;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return newhousehots==null?0:newhousehots.size();
	}

	@Override
	public RentingTuijian getItem(int position) {

		return newhousehots.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_new_house_rexiao, null);
			holder = new ViewHolder();
			holder.newhouse_rexiao_address = (MyTextView) convertView.findViewById(R.id.newhouse_rexiao_address);
			holder.newhouse_rexiao_price = (MyTextView) convertView.findViewById(R.id.newhouse_rexiao_price);
			holder.newhouse_rexiao_area = (MyTextView) convertView.findViewById(R.id.newhouse_rexiao_area);
			holder.newhouse_rexiao_title = (MyTextView) convertView.findViewById(R.id.newhouse_rexiao_title);
			holder.newhouse_rexiao_iv = (ImageView) convertView.findViewById(R.id.newhouse_rexiao_iv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		RentingTuijian n = getItem(position);
		String url =UrlFactory.TSFURL + n.getThumb();
//		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//				.bitmapConfig(Bitmap.Config.RGB_565).build();
//		ImageLoader.getInstance().displayImage(url, holder.newhouse_rexiao_iv, options);
		Glide.with(context).load(url).into(holder.newhouse_rexiao_iv);

		holder.newhouse_rexiao_area.setText(n.getProvince_name()+" "+n.getCity_name()+" "+n.getArea_name());
		holder.newhouse_rexiao_address.setText("板楼");
		holder.newhouse_rexiao_title.setText(n.getTitle());
		holder.newhouse_rexiao_price.setText(n.getZujin() + "元/月");

		return convertView;
	}

	class ViewHolder {
		TextView newhouse_rexiao_address;
		TextView newhouse_rexiao_price;
		TextView newhouse_rexiao_area;
		TextView newhouse_rexiao_title;
		ImageView newhouse_rexiao_iv;

	}

}
