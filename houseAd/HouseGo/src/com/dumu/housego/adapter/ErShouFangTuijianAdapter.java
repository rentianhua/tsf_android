package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.util.UrlFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ErShouFangTuijianAdapter extends BaseAdapter {
	private List<ErShouFangRecommendData> ershoufangrecommends;
	private Context context;
	private LayoutInflater Inflater;
	private Typeface typeface;

	public ErShouFangTuijianAdapter(List<ErShouFangRecommendData> ershoufangrecommends, Context context) {
		super();
		this.ershoufangrecommends = ershoufangrecommends;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return ershoufangrecommends==null?0:ershoufangrecommends.size();
	}

	@Override
	public ErShouFangRecommendData getItem(int position) {

		return ershoufangrecommends.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_ershou_tuijian, null);
			holder = new ViewHolder();
			holder.ivImg = (ImageView) convertView.findViewById(R.id.iv_ershoufang_img);
			holder.tvAddress = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_address);
			holder.tvArea = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_price_area);
			holder.tvPrice = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_totalprice);
			holder.tvTitle = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_title);
			
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		ErShouFangRecommendData n = getItem(position);

		// 显示图片的配置

		String url = UrlFactory.TSFURL + n.getThumb();

//		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//				.bitmapConfig(Bitmap.Config.RGB_565).build();
//		ImageLoader.getInstance().displayImage(url, holder.ivImg, options);
		Glide.with(context).load(url).into(holder.ivImg);

		holder.tvTitle.setText(n.getTitle() + "");

		 holder.tvArea.setText(n.getShi()+"室"+n.getTing()+"厅 "+n.getJianzhumianji()+"㎡ ");
		 holder.tvPrice.setText(n.getZongjia()+"");
		 holder.tvAddress.setText(n.getCity_name()+" "+n.getArea_name());

		 
		 
		return convertView;
	}

	class ViewHolder {
		TextView tvTitle;
		TextView tvArea;
		TextView tvPrice;
		TextView tvAddress;
		ImageView ivImg;

	}

}
