package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.ErShouFangDetails;
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

public class ChengJiaoErShouAdapter extends BaseAdapter {
	private List<ErShouFangDetails> ershoudetails;
	private Context context;
	private LayoutInflater Inflater;
	private Typeface typeface;

	public ChengJiaoErShouAdapter(List<ErShouFangDetails> ershoudetails, Context context) {
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
			convertView = Inflater.inflate(R.layout.item_chengjiao_ershou, null);
			holder = new ViewHolder();

			holder.ivchengjiaoershou = (ImageView) convertView.findViewById(R.id.iv_chengjiaoershou);
			holder.tvArea = (MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_area);
			holder.tvChaoxiang = (MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_chaoxiang);
			holder.tvFangxing = (MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_fangxing);
			holder.tvJianzhutype = (MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_jianzhutype);
			holder.tvJunjia = (MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_junjia);
			holder.tvLouceng = (MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_louceng);
			holder.tvTitle = (MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_title);
			holder.tvXiaoquname = (MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_xiaoquname);
			holder.tvZongjia = (MyTextView) convertView.findViewById(R.id.tv_chengjiaoershou_zongjia);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		
		ErShouFangDetails n = getItem(position);

		String url = UrlFactory.TSFURL + n.getThumb();
//		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//				.bitmapConfig(Bitmap.Config.RGB_565).build();
//		ImageLoader.getInstance().displayImage(url, holder.ivchengjiaoershou, options);
		Glide.with(context).load(url).into(holder.ivchengjiaoershou);

		holder.tvArea.setText(n.getJianzhumianji() + "平米");
		holder.tvChaoxiang.setText(n.getChaoxiang());
		holder.tvFangxing.setText(n.getShi() + "室" + n.getTing() + "厅");
		holder.tvJianzhutype.setText(n.getFangling() + "年房" + n.getJianzhutype());
		int junjia ;
		if(n.getJianzhumianji().equals("0")){
			junjia = Integer.valueOf(n.getZongjia()) * 10000;
		}else{
			junjia = Integer.valueOf(n.getZongjia()) * 10000 / Integer.valueOf(n.getJianzhumianji());
		}
		


		holder.tvJunjia.setText(junjia + "元/㎡");
		holder.tvLouceng.setText(n.getCeng() + "(共" + n.getZongceng() + "层)");
		holder.tvTitle.setText(n.getTitle());
		holder.tvXiaoquname.setText(n.getXiaoquname());
		holder.tvZongjia.setText(n.getZongjia() + "");

		return convertView;

	}

	class ViewHolder {
		TextView tvTitle;
		TextView tvXiaoquname;
		TextView tvFangxing;
		TextView tvArea;
		TextView tvChaoxiang;
		TextView tvZongjia;
		TextView tvLouceng;
		TextView tvJianzhutype;
		TextView tvJunjia;
		ImageView ivchengjiaoershou;
	}
}
