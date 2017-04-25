package com.dumu.housego.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.MyTextView;
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

public class SubmitErshouListAdapter extends BaseAdapter {
	private List<ErShouFangDetails> submitershous;
	private Context context;
	private LayoutInflater Inflater;

	public SubmitErshouListAdapter(List<ErShouFangDetails> submitershous, Context context) {
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
	public ErShouFangDetails getItem(int position) {

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
			convertView = Inflater.inflate(R.layout.item_submit_ershou_list, null);
			holder = new ViewHolder();

			holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_submit_ershou_list);

			holder.tvChenHu = (MyTextView) convertView.findViewById(R.id.tv_submit_ershou_list_chenhu);
			holder.tvDetail = (MyTextView) convertView.findViewById(R.id.tv_submit_ershou_list_detail);
			holder.ivEdit = (ImageView) convertView.findViewById(R.id.iv_submit_ershou_list_edit);
			holder.ivDelete = (ImageView) convertView.findViewById(R.id.iv_submit_ershou_list_delete);
			holder.tvLouceng = (MyTextView) convertView.findViewById(R.id.tv_submit_ershou_list_louceng);
			holder.tvTitle = (MyTextView) convertView.findViewById(R.id.tv_submit_ershou_list_title);
			holder.tvUpLoadht = (MyTextView) convertView.findViewById(R.id.tv_submit_ershou_list_hetong);
			holder.tvUpLoadsfz = (MyTextView) convertView.findViewById(R.id.tv_submit_ershou_list_sfz);
			holder.tvZongjia = (MyTextView) convertView.findViewById(R.id.tv_submit_ershou_list_zongjia);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		try {

			ErShouFangDetails n = getItem(position);
			String url = UrlFactory.TSFURL + n.getThumb();

//			DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//					.bitmapConfig(Bitmap.Config.RGB_565).build();
//			ImageLoader.getInstance().displayImage(url, holder.ivPic, options);
			Glide.with(context).load(url).into(holder.ivPic);

			String type=n.getTypeid();
			
			String pubtype = n.getPub_type();
			if (pubtype.equals("1")) {
				holder.tvChenHu.setText(n.getUsername()+"/自售");
			} else if (pubtype.equals("2")) {
				holder.tvChenHu.setText(n.getUsername()+"/委托给经纪人");
			} else {
				holder.tvChenHu.setText(n.getUsername()+"/委托给平台");
			}
			
			holder.tvDetail.setText(n.getCityname() + " / " + n.getAreaname() + " / " + n.getXiaoquname());
			holder.tvLouceng.setText(n.getCeng() + "(共" + n.getZongceng() + "层)");
			holder.tvTitle.setText(n.getTitle() + "");
			holder.tvZongjia.setText(n.getZongjia() + "");
			
			
		
			holder.ivEdit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					MyToastShowCenter.CenterToast(context, "还不能编辑");
				}
			});
			
			holder.ivDelete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
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
