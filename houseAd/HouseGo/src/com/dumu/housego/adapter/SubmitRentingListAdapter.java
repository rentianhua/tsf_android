package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.UrlFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SubmitRentingListAdapter extends BaseAdapter {
	private List<RentingDetail> submitershous;
	private Context context;
	private LayoutInflater Inflater;

	public SubmitRentingListAdapter(List<RentingDetail> submitershous, Context context) {
		super();
		this.submitershous = submitershous;
		this.context = context;
		this.Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return submitershous==null?0:submitershous.size();
	}

	@Override
	public RentingDetail getItem(int position) {

		return submitershous.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = Inflater.inflate(R.layout.item_submit_renting_list, null);
			holder = new ViewHolder();

			holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_submit_renting_list);

			holder.tvChenHu = (MyTextView) convertView.findViewById(R.id.tv_submit_renting_list_chuzuType);
			holder.tvDetail = (MyTextView) convertView.findViewById(R.id.tv_submit_renting_list_detail);
			holder.ivDelete = (ImageView) convertView.findViewById(R.id.iv_submit_renting_list_delete);
			holder.ivEdit = (ImageView) convertView.findViewById(R.id.iv_submit_renting_list_edit);
			holder.tvLouceng = (MyTextView) convertView.findViewById(R.id.tv_submit_renting_list_area);
			holder.tvTitle = (MyTextView) convertView.findViewById(R.id.tv_submit_renting_list_title);
			holder.tvUpLoadsfz = (MyTextView) convertView.findViewById(R.id.tv_submit_renting_list_sfz);
			holder.tvUpLoadht = (MyTextView) convertView.findViewById(R.id.tv_submit_renting_list_hetong);
			holder.tvZongjia = (MyTextView) convertView.findViewById(R.id.tv_submit_renting_list_zongjia);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		try {

			RentingDetail n = getItem(position);
			String url =UrlFactory.TSFURL  + n.getThumb();

//			DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//					.bitmapConfig(Bitmap.Config.RGB_565).build();
//			ImageLoader.getInstance().displayImage(url, holder.ivPic, options);
			Glide.with(context).load(url).into(holder.ivPic);

			String pubtype = n.getPub_type();
			if (pubtype.equals("1")) {
				holder.tvChenHu.setText("直接出租");
			} else if (pubtype.equals("2")) {
				holder.tvChenHu.setText("委托给经纪人");
			} else {
				holder.tvChenHu.setText("委托给平台");
			}

			holder.tvDetail.setText(n.getCityname() + " / " + n.getAreaname() + " / " + n.getXiaoquname());
			holder.tvLouceng.setText(n.getShi() + "室" + n.getTing() + "厅 / " + n.getMianji() + "平米");
			holder.tvTitle.setText(n.getTitle() + "");
			holder.tvZongjia.setText(n.getZujin() + "");

			holder.ivEdit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					MyToastShowCenter.CenterToast(context, "还不能编辑");
				}
			});
			holder.ivDelete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					MyToastShowCenter.CenterToast(context, "还不能编辑");
				}
			});

			holder.tvUpLoadht.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					MyToastShowCenter.CenterToast(context, "还不能上传图片");

				}
			});

			holder.tvUpLoadsfz.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					MyToastShowCenter.CenterToast(context, "还不能上传图片");

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertView;

	}

	class ViewHolder {
		TextView tvTitle;
		TextView tvDetail;
		TextView tvLouceng;
		TextView tvChenHu;
		TextView tvZongjia;
		TextView tvUpLoadht;
		TextView tvUpLoadsfz;
		ImageView ivEdit;
		ImageView ivDelete;
		ImageView ivPic;
	}
}
