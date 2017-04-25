package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.util.UrlFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RentingMapHosueAdapter extends BaseAdapter {
	private List<RentingRecommendData> ershoufangrecommends;
	private Context context;
	private LayoutInflater Inflater;
	private String xiaoquname;

	public RentingMapHosueAdapter(List<RentingRecommendData> ershoufangrecommends, Context context,String xiaoquname) {
		super();
		this.ershoufangrecommends = ershoufangrecommends;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
		this.xiaoquname=xiaoquname;
	}

	@Override
	public int getCount() {
		return ershoufangrecommends==null?0:ershoufangrecommends.size();
	}

	@Override
	public RentingRecommendData getItem(int position) {

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
			convertView = Inflater.inflate(R.layout.item_renting_maphouse, null);
			holder = new ViewHolder();
			holder.ivImg = (ImageView) convertView.findViewById(R.id.iv_ershoufang_img);
			holder.tvAddress = (TextView) convertView.findViewById(R.id.tv_ershoufang_address);
			holder.tvArea = (TextView) convertView.findViewById(R.id.tv_ershoufang_price_area);
			holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_ershoufang_totalprice);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_ershoufang_title);
			holder.tvBiaoqian1=(TextView) convertView.findViewById(R.id.tv_ershou_biaoqian);
			holder.tvBiaoqian2=(TextView) convertView.findViewById(R.id.tv_ershou_biaoqian2);
			
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		RentingRecommendData n = getItem(position);

		// 显示图片的配置

		String url = UrlFactory.TSFURL + n.getThumb();

//		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//				.bitmapConfig(Bitmap.Config.RGB_565).build();
//		ImageLoader.getInstance().displayImage(url, holder.ivImg, options);
		Glide.with(context).load(url).into(holder.ivImg);

		holder.tvTitle.setText(n.getTitle() + "");

		 holder.tvArea.setText(n.getShi()+"室"+n.getTing()+"厅 "+n.getMianji()+"㎡ "+n.getChaoxiang());
		 holder.tvPrice.setText(n.getZujin()+"元");
		 holder.tvAddress.setText(xiaoquname);

		 


		 if(!n.getBiaoqian().equals("")){
				String[] b=n.getBiaoqian().split(",");
				int l=b.length;
				switch (l) {
				case 1:
					holder.tvBiaoqian1.setText(b[0]);
					holder.tvBiaoqian1.setVisibility(View.VISIBLE);
					holder.tvBiaoqian2.setVisibility(View.GONE);
					break;
				case 2:
					holder.tvBiaoqian1.setText(b[0]);
					holder.tvBiaoqian2.setText(b[1]);
					holder.tvBiaoqian1.setVisibility(View.VISIBLE);
					holder.tvBiaoqian2.setVisibility(View.VISIBLE);
					break;
				}
			}else{
				holder.tvBiaoqian1.setVisibility(View.GONE);
				holder.tvBiaoqian2.setVisibility(View.GONE);
			}
			
			
		 
		 
		return convertView;
	}

	class ViewHolder {
		TextView tvTitle;
		TextView tvArea;
		TextView tvPrice;
		TextView tvAddress;
		ImageView ivImg;
		
		TextView tvBiaoqian1;
		TextView tvBiaoqian2;
		
	}

}
