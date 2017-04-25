package com.dumu.housego.framgent;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.ErShouFangDetailsActivity;
import com.dumu.housego.NewHouseDetailActivity;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.GuanZhuDeletePresenter;
import com.dumu.housego.presenter.GuanZhuNewPresenter;
import com.dumu.housego.presenter.IGuanZhuDeletePresenter;
import com.dumu.housego.presenter.IGuanZhuNewPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.SwipeListView;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.view.IGuanZhuDeleteView;
import com.dumu.housego.view.IGuanZhuNewView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GZNewFramgent extends Fragment implements IGuanZhuNewView , IGuanZhuDeleteView{
	private SwipeListView listview;
	private List<NewHouseDetail> newhousedetails;
	private GuanZhuNewLsitAdapter adapter;
	private UserInfo userinfo;
	private IGuanZhuNewPresenter presenter;
	private IGuanZhuDeletePresenter deletePresenter;
	private 	String username;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_guanzhu_new, null);
		initView(view);
		setListener();
		
		FontHelper.injectFont(view);

		return view;
	}

	 @Override
	 public void onResume() {
		 presenter = new GuanZhuNewPresenter(this);
			deletePresenter = new GuanZhuDeletePresenter(this);
			userinfo = HouseGoApp.getContext().getCurrentUserInfo();
			username = userinfo.getUsername();
		 presenter.LoadGuanZhuNew(username, "new");
	 super.onResume();
	 }
	

	private void setListener() {
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent(getContext(), NewHouseDetailActivity.class);
				String catid = newhousedetails.get(position).getCatid();
				String ID = newhousedetails.get(position).getPosid();
				i.putExtra("catid", catid);
				i.putExtra("Id", ID);
				startActivity(i);
				
			}
		});

	}

	private void initView(View view) {
		listview = (SwipeListView) view.findViewById(R.id.lv_guanzhu_new);

	}

	@Override
	public void showGuanZhuSuccess(List<NewHouseDetail> newhousedetails) {
		this.newhousedetails = newhousedetails;
		if (newhousedetails != null) {
			adapter = new GuanZhuNewLsitAdapter(newhousedetails, getActivity(), listview.getRightViewWidth());
			listview.setAdapter(adapter);
		} else {
			MyToastShowCenter.CenterToast(getContext(), "你还没有关注新房！");
		}

	}

	@Override
	public void showGuanZhuFail(String errorinfo) {
		//MyToastShowCenter.CenterToast(getActivity(), errorinfo);

	}
	
	
	
	
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			final String id = newhousedetails.get(msg.arg2).getId();
			
			UserInfo info = HouseGoApp.getContext().getCurrentUserInfo();
			final String userid = info.getUserid();
			final String username = info.getUsername();

			 deletePresenter.deleteGuanZhu(id, userid, username);
			newhousedetails.remove(msg.arg2);
//			 presenter.LoadGuanZhuNew(username, "new");
			adapter.notifyDataSetChanged();
		}
	};
	
	
	
	
	
	
	
	
	
	
	

	public class GuanZhuNewLsitAdapter extends BaseAdapter {
		private List<NewHouseDetail> newhousedetails;
		private Context context;
		private LayoutInflater Inflater;
		private int mRightWidth = 0;

		public GuanZhuNewLsitAdapter(List<NewHouseDetail> newhousedetails, Context context, int rightWidth) {
			super();
			this.newhousedetails = newhousedetails;
			this.context = context;
			mRightWidth = rightWidth;
			this.Inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return newhousedetails==null?0:newhousedetails.size();
		}

		@Override
		public NewHouseDetail getItem(int position) {

			return newhousedetails.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = Inflater.inflate(R.layout.item_guanzhu_new, null);
				holder = new ViewHolder();
				
				holder.ivImg = (ImageView) convertView.findViewById(R.id.iv_guanzhuershou);
				holder.tvJunjia=(MyTextView) convertView.findViewById(R.id.tv_guanzhu_junjia);
				holder.tvTitle = (MyTextView) convertView.findViewById(R.id.tv_guanzhu_title);
				holder.tvLoupandizhi= (MyTextView) convertView.findViewById(R.id.tv_guanzhu_loupandizhi);
				holder.tvMianjiarea=(MyTextView) convertView.findViewById(R.id.tv_guanzhu_jianzhumianji);
				
				holder.item_left = (LinearLayout) convertView.findViewById(R.id.item_left);
				holder.item_right = (RelativeLayout) convertView.findViewById(R.id.item_right);
				holder.item_right_txt = (MyTextView) convertView.findViewById(R.id.item_right_txt);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			LinearLayout.LayoutParams lp1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			holder.item_left.setLayoutParams(lp1);
			LinearLayout.LayoutParams lp2 = new LayoutParams(mRightWidth, LayoutParams.MATCH_PARENT);
			holder.item_right.setLayoutParams(lp2);

			NewHouseDetail n = getItem(position);

			String url = UrlFactory.TSFURL + n.getThumb();
			Glide.with(context).load(url).into(holder.ivImg);

			holder.tvTitle.setText(n.getTitle() + "");

			holder.tvJunjia.setText(n.getJunjia()+ "元/㎡");
			holder.tvLoupandizhi.setText(n.getLoupandizhi());
			holder.tvMianjiarea.setText(n.getMianjiarea() + "㎡");

			// ȡ����ע�������ֵ

			holder.item_right_txt.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					newhousedetails.remove(position);
					MyToastShowCenter.CenterToast(context, "取消关注");

					listview.hiddenRight(listview.mPreItemView);
					if (myHandler != null) {
						Message msg = myHandler.obtainMessage();
						msg.arg1 = 0;
						msg.arg2 = position;
//						Log.e("position==", "position===="+position);
						myHandler.sendMessage(msg);
					}

				}
			});

			return convertView;
		}



		class ViewHolder {
			MyTextView tvTitle;
			ImageView ivImg;
			MyTextView tvJunjia;
			MyTextView tvMianjiarea;
			MyTextView tvLoupandizhi;
		

			MyTextView item_right_txt;
			LinearLayout item_left;
			RelativeLayout item_right;
		}
	}

	@Override
	public void deleteGuanZhuSuccess(String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGuanZhuFail(String errorinfo) {
		// TODO Auto-generated method stub
		
	}

}
