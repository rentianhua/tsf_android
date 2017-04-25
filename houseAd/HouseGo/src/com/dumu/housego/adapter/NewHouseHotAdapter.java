package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.NewHouseHotRecommend;
import com.dumu.housego.util.UrlFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewHouseHotAdapter extends BaseAdapter {
	private List<NewHouseHotRecommend> newhousehots;
	private Context context;
	private LayoutInflater Inflater;

	public NewHouseHotAdapter(List<NewHouseHotRecommend> newhousehots, Context context) {
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
	public NewHouseHotRecommend getItem(int position) {

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

		NewHouseHotRecommend n = getItem(position);
		String url =UrlFactory.TSFURL + n.getThumb();
		// Glide.with(context).load(url).into(holder.newhouse_rexiao_iv);
//		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//				.bitmapConfig(Bitmap.Config.RGB_565).build();
//		ImageLoader.getInstance().displayImage(url, holder.newhouse_rexiao_iv, options);
		Glide.with(context).load(url).into(holder.newhouse_rexiao_iv);

		holder.newhouse_rexiao_area.setText(n.getMianjiarea()+"平米");
		holder.newhouse_rexiao_address.setText(n.getLoupandizhi());
		holder.newhouse_rexiao_title.setText(n.getTitle());
		holder.newhouse_rexiao_price.setText(n.getJunjia() + "元/㎡");

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
