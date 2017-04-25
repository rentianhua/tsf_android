package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.util.UrlFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecommendHouseAdapter extends BaseAdapter {
	private List<RecommendNews> recommends;
	private Context context;
	private LayoutInflater Inflater;
	
	public RecommendHouseAdapter(List<RecommendNews> recommends, Context context) {
		super();
		this.recommends = recommends;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return recommends==null?0:recommends.size();
	}

	@Override
	public RecommendNews getItem(int position) {

		return recommends.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_recommend_house, null);
			holder = new ViewHolder();
			
			holder.ivRecommendImg = (ImageView) convertView.findViewById(R.id.iv_recommend_img);
			holder.tvRecommendContent = (MyTextView) convertView.findViewById(R.id.tv_recommend_content);
			holder.tvRecommendTitle = (MyTextView) convertView.findViewById(R.id.tv_recommend_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		RecommendNews recommend = getItem(position);
		String url =UrlFactory.TSFURL + recommend.getThumb();
		// Glide.with(context).load(url).into(holder.ivRecommendImg);
//		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//				.bitmapConfig(Bitmap.Config.RGB_565).build();
//		ImageLoader.getInstance().displayImage(url, holder.ivRecommendImg, options);
		Glide.with(context).load(url).into(holder.ivRecommendImg);

		holder.tvRecommendContent.setText(recommend.getDescription());
		holder.tvRecommendTitle.setText(recommend.getTitle());

		return convertView;
	}

	class ViewHolder {
		TextView tvRecommendTitle;
		TextView tvRecommendContent;
		ImageView ivRecommendImg;
	}

}
