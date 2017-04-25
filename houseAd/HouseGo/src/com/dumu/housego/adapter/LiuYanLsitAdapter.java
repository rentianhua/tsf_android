package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.LiuYanList;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.util.TimeTurnDate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LiuYanLsitAdapter extends BaseAdapter {
	private List<LiuYanList> liuyans;
	private Context context;
	private LayoutInflater Inflater;
	private Typeface typeface;

	public LiuYanLsitAdapter(List<LiuYanList> liuyans, Context context ) {
		super();
		this.liuyans = liuyans;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return liuyans==null?0:liuyans.size();
	}

	@Override
	public LiuYanList getItem(int position) {

		return liuyans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.liuyanlist_item, null);
			holder = new ViewHolder();
			holder.ivImg=(ImageView) convertView.findViewById(R.id.liuyanlist_pic);
			holder.ivDelete=(ImageView) convertView.findViewById(R.id.iv_xiaoxidelete);
			holder.tvcontent=(TextView) convertView.findViewById(R.id.liuyanlist_content);
			holder.tvName=(TextView) convertView.findViewById(R.id.liuyanlist_name);
			holder.tvtime=(TextView) convertView.findViewById(R.id.liuyanlist_time);
			holder.tv_yidu=(TextView) convertView.findViewById(R.id.tv_yidu);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		LiuYanList n = getItem(position);

		if(!n.getUserpic().equals("") && n.getUserpic()!=null){
			String url=n.getUserpic();
//			DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//					.bitmapConfig(Bitmap.Config.RGB_565).build();
//			ImageLoader.getInstance().displayImage(url, holder.ivImg, options);
			Glide.with(context).load(url).into(holder.ivImg);
		}else{
			Glide.clear(holder.ivImg);
			holder.ivImg.setImageResource(R.drawable.appicon60x60);
		}
		
	holder.tvcontent.setText(n.getContent());
	
	
	holder.ivDelete.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
		
			
		}
	});
	
	if(n.getYidu()==0){
		holder.tv_yidu.setText("未读");
	}else{
		holder.tv_yidu.setText("");
	}
	
	
	if(n.getRealname()!=null ){
	holder.tvName.setText(n.getRealname());
	}else{
		
		if(n.getFrom_uid().equals("0")){
			holder.tvName.setText("系统消息");
		}else{
			holder.tvName.setText("淘深房用户");
		}
		
		
	}
	
	holder.tvtime.setText(TimeTurnDate.getStringDate(n.getInputtime()));


		return convertView;
	}

	class ViewHolder {
		
		TextView tvName;
		TextView tvcontent;
		TextView tv_yidu;
		TextView tvtime;
		ImageView ivImg;
		ImageView ivDelete;
	}

}
