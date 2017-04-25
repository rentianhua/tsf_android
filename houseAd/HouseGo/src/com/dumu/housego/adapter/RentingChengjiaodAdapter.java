package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.util.UrlFactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RentingChengjiaodAdapter extends BaseAdapter {
	private List<RentingRecommendData> ershoufangrecommends;
	private Context context;
	private LayoutInflater Inflater;

	public RentingChengjiaodAdapter(List<RentingRecommendData> ershoufangrecommends, Context context) {
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
			convertView = Inflater.inflate(R.layout.item_renting_recommend, null);
			holder = new ViewHolder();
			holder.ivImg = (ImageView) convertView.findViewById(R.id.iv_ershoufang_img);
			holder.tvAddress = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_address);
			holder.tvArea = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_price_area);
			holder.tvPrice = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_totalprice);
			holder.tvTitle = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_title);
			holder.tvBiaoqian1=(MyTextView) convertView.findViewById(R.id.tv_ershou_biaoqian);
			holder.tvBiaoqian2=(MyTextView) convertView.findViewById(R.id.tv_ershou_biaoqian2);
			
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		RentingRecommendData n = getItem(position);

		// 显示图片的配置

		if(n.getThumb().startsWith("http")){
//			DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//					.bitmapConfig(Bitmap.Config.RGB_565).build();
//
//			ImageLoader.getInstance().displayImage(n.getThumb(), holder.ivImg, options);
			Glide.with(context).load(n.getThumb()).into(holder.ivImg);
		}else{
			String url = UrlFactory.TSFURL + n.getThumb();
			Glide.with(context).load(url).into(holder.ivImg);
//			DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//					.bitmapConfig(Bitmap.Config.RGB_565).build();
//
//			ImageLoader.getInstance().displayImage(url, holder.ivImg, options);
		}
		


		holder.tvTitle.setText(n.getTitle() + "");

		 holder.tvArea.setText(n.getShi()+"室"+n.getTing()+"厅 "+n.getMianji()+"㎡ "+n.getChaoxiang());
		 holder.tvPrice.setText(n.getZujin()+"元");
		 holder.tvAddress.setText(""+n.getCeng()+"共"+n.getZongceng()+"层");

		 holder.tvBiaoqian1.setVisibility(View.GONE);
			holder.tvBiaoqian2.setVisibility(View.GONE); 


//		 if(!n.getBiaoqian().equals("")){
//				String[] b=n.getBiaoqian().split(",");
//				int l=b.length;
//				switch (l) {
//				case 1:
//					holder.tvBiaoqian1.setText(b[0]);
//					holder.tvBiaoqian1.setVisibility(View.VISIBLE);
//					holder.tvBiaoqian2.setVisibility(View.GONE);
//					break;
//				case 2:
//					holder.tvBiaoqian1.setText(b[0]);
//					holder.tvBiaoqian2.setText(b[1]);
//					holder.tvBiaoqian1.setVisibility(View.VISIBLE);
//					holder.tvBiaoqian2.setVisibility(View.VISIBLE);
//					break;
//				}
//			}else{
//				holder.tvBiaoqian1.setVisibility(View.GONE);
//				holder.tvBiaoqian2.setVisibility(View.GONE);
//			}
			
			
		 
		 
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
