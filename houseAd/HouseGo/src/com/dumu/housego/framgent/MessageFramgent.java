package com.dumu.housego.framgent;

import java.util.List;

import com.bumptech.glide.Glide;
import com.dumu.housego.LiuYanDetailActivity;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.LiuYanList;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ILiuYanDeletePresenter;
import com.dumu.housego.presenter.ILiuYanPresenter;
import com.dumu.housego.presenter.LiuYanDeletePresenter;
import com.dumu.housego.presenter.LiuYanListPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.ILiuYanDeleteView;
import com.dumu.housego.view.ILiuYanView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MessageFramgent extends Fragment implements ILiuYanView, ILiuYanDeleteView {
    private RelativeLayout rlMessageNo;
    private UserInfo userinfo;
    private ListViewForScrollView lv_message;
    private MyReboundScrollView scrollview;
    private List<LiuYanList> liuyans;
    private ILiuYanPresenter liuyanlistPresenter;
    private LiuYanLsitAdapter listadapter;
    private String userid;
    private Handler handler = new Handler();
    private ILiuYanDeletePresenter deletePresenter;


    private Runnable runnable = new Runnable() {
        public void run() {
            this.update();
            handler.postDelayed(this, 5000);// 间隔120秒
        }

        void update() {
            liuyanlistPresenter.liuyan(userid);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgent_message, null);

        setViews(view);
        setListener();

        FontHelper.injectFont(view);
        return view;

    }

    @Override
    public void onResume() {

        deletePresenter = new LiuYanDeletePresenter(this);
        liuyanlistPresenter = new LiuYanListPresenter(this);
        userinfo = HouseGoApp.getLoginInfo(getContext());
        if (userinfo != null) {
            rlMessageNo.setVisibility(View.GONE);
            scrollview.setVisibility(View.VISIBLE);
            userid = userinfo.getUserid();
            liuyanlistPresenter.liuyan(userid);
            handler.postDelayed(runnable, 1000);
        } else {
            rlMessageNo.setVisibility(View.VISIBLE);
            scrollview.setVisibility(View.GONE);
        }

        super.onResume();
    }

    private void setListener() {
        lv_message.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LiuYanList l = liuyans.get(position);
                String towho = l.getFrom_uid();
                String towho1 = l.getTo_uid();
                Intent i = new Intent(getActivity(), LiuYanDetailActivity.class);
                i.putExtra("realname", l.getRealname());
                i.putExtra("id", l.getId());
                if (towho.equals(userid)) {
                    i.putExtra("towho", towho1);

                } else {
                    i.putExtra("towho", towho);
                }
                startActivity(i);
            }
        });
    }

    private void setViews(View view) {
        rlMessageNo = (RelativeLayout) view.findViewById(R.id.rl_message_no);
        scrollview = (MyReboundScrollView) view.findViewById(R.id.sc_message);
        lv_message = (ListViewForScrollView) view.findViewById(R.id.lv_message);

    }

    @Override
    public void liuyanlist(List<LiuYanList> liuyans) {
//		rlMessageNo.setVisibility(View.GONE);
//		scrollview.setVisibility(View.VISIBLE);
        this.liuyans = liuyans;
        listadapter = new LiuYanLsitAdapter(liuyans, getActivity());
        lv_message.setAdapter(listadapter);

    }

    @Override
    public void liuyanerror(String info) {
        rlMessageNo.setVisibility(View.VISIBLE);
        scrollview.setVisibility(View.GONE);
    }


    public class LiuYanLsitAdapter extends BaseAdapter {
        private List<LiuYanList> liuyans;
        private Context context;
        //	private LayoutInflater Inflater;
        private Typeface typeface;

        public LiuYanLsitAdapter(List<LiuYanList> liuyans, Context context) {
            super();
            this.liuyans = liuyans;
            this.context = context;
//		this.Inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return liuyans == null ? 0 : liuyans.size();
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
                LayoutInflater Inflater = LayoutInflater.from(context);
                convertView = Inflater.inflate(R.layout.liuyanlist_item, null);
                holder = new ViewHolder();
                holder.ivImg = (ImageView) convertView.findViewById(R.id.liuyanlist_pic);
                holder.ivDelete = (ImageView) convertView.findViewById(R.id.iv_xiaoxidelete);
                holder.tvcontent = (TextView) convertView.findViewById(R.id.liuyanlist_content);
                holder.tvName = (TextView) convertView.findViewById(R.id.liuyanlist_name);
                holder.tvtime = (TextView) convertView.findViewById(R.id.liuyanlist_time);
                holder.tv_yidu = (TextView) convertView.findViewById(R.id.tv_yidu);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final LiuYanList n = getItem(position);

            if (n.getUserpic() != null && !n.getUserpic().equals("")) {
                String url = n.getUserpic();
//			DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//					.bitmapConfig(Bitmap.Config.RGB_565).build();
//			ImageLoader.getInstance().displayImage(url, holder.ivImg, options);
                Glide.with(context).load(url).into(holder.ivImg);
            } else {
                Glide.clear(holder.ivImg);
                holder.ivImg.setImageResource(R.drawable.appicon60x60);
            }

            holder.tvcontent.setText(n.getContent());

            holder.ivDelete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletePresenter.Deleteliuyan(n.getId(), userid);
                }
            });

            if (n.getYidu() == 0) {
                holder.tv_yidu.setText("未读");
            } else {
                holder.tv_yidu.setText("");
            }

            if (n.getRealname() != null) {
                holder.tvName.setText(n.getRealname());
            } else {
                if (n.getFrom_uid().equals("0")) {
                    holder.tvName.setText("系统消息");
                } else {
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

    public void deleteliuyan(String info) {
        MyToastShowCenter.CenterToast(getContext(), info);
    }
}
