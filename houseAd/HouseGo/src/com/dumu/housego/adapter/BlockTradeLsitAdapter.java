package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.BlockTradeList;
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

public class BlockTradeLsitAdapter extends BaseAdapter {
	private List<BlockTradeList> blocktrades;
	private Context context;
	private LayoutInflater Inflater;
	private Typeface typeface;

	public BlockTradeLsitAdapter(List<BlockTradeList> blocktrades, Context context) {
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
	public BlockTradeList getItem(int position) {

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
			convertView = Inflater.inflate(R.layout.item_blocktrade_recommend, null);
			holder = new ViewHolder();
			holder.ivImg = (ImageView) convertView.findViewById(R.id.iv_ershoufang_img);
			holder.tvAddress = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_address);
			holder.tvArea = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_price_area);
			holder.tvMeterPrice = (MyTextView) convertView.findViewById(R.id.tv_ershou_nianxain);
			holder.tvPrice = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_totalprice);
			holder.tvTitle = (MyTextView) convertView.findViewById(R.id.tv_ershoufang_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		BlockTradeList n = getItem(position);

		String url = UrlFactory.TSFURL + n.getThumb();

//		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//				.bitmapConfig(Bitmap.Config.RGB_565).build();
//		ImageLoader.getInstance().displayImage(url, holder.ivImg, options);
		Glide.with(context).load(url).into(holder.ivImg);

		holder.tvTitle.setText(n.getTitle() + "");
		
		// holder.tvPrice.setText(n.getZongjia()+"");
		 holder.tvAddress.setText(n.getHezuofangshi()+"/"+n.getWuyetype());

		holder.tvArea.setText(n.getCityname()+" "+n.getAreaname()+"/"+n.getZhandimianji()+"㎡");

		holder.tvPrice.setText(n.getZongjia()+"");
//		if(n.getShiyongnianxian()==null || n.getShiyongnianxian().equals("")){
//			holder.tvMeterPrice.setText("使用年限:长期");
//		}else{
		if(n.getShiyongnianxian()!=null  && !n.getShiyongnianxian().equals("")){
			if(n.getShiyongnianxian().equals("999")){
				holder.tvMeterPrice.setText("使用年限:长期");
			}else{
				holder.tvMeterPrice.setText("使用年限:"+n.getShiyongnianxian()+"年");	
			}
			
		}else{
			holder.tvMeterPrice.setText("使用年限:  ");
		}
			
//			
//		}

		return convertView;
	}

	class ViewHolder {
		TextView tvTitle;
		TextView tvArea;
		TextView tvPrice;
		TextView tvAddress;
		TextView tvMeterPrice;
		ImageView ivImg;
	}

}
