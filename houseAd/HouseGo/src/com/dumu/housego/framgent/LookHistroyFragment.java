package com.dumu.housego.framgent;

import java.text.ParseException;
import java.util.List;

import com.dumu.housego.ErShouFangDetailsActivity;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.entity.YuYueData;
import com.dumu.housego.presenter.IMyDaiKanHousePresenter;
import com.dumu.housego.presenter.ISureYuYuePresenter;
import com.dumu.housego.presenter.MyDaiKanPresenter;
import com.dumu.housego.presenter.SureYuYuePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.IMyYuYueHouseView;
import com.dumu.housego.view.ISureYuYueView;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class LookHistroyFragment extends Fragment implements IMyYuYueHouseView, ISureYuYueView {
    private Button btnLogin;
    private ImageView no_or_noLogin;
    private UserInfo userinfo;
    private ListView lv_daikan;
    private List<YuYueData> yuyuedatas;
    private MyYuYueDateLsitAdapter2 adapter;
    private IMyDaiKanHousePresenter presenter;
    private ISureYuYuePresenter sureyuyuePresenter;
    private String username;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_look_histroy, null);
        FontHelper.injectFont(view);
        initView(view);
        presenter = new MyDaiKanPresenter(this);
        sureyuyuePresenter = new SureYuYuePresenter(this);

        lv_daikan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Id = yuyuedatas.get(position).getFromid();
                Intent i = new Intent(getActivity(), ErShouFangDetailsActivity.class);
                i.putExtra("catid", "6");
                i.putExtra("id", Id);
                startActivity(i);
            }
        });

        return view;
    }

    public void onResume() {
        userinfo = HouseGoApp.getLoginInfo(getContext());
        if (userinfo == null) {
            no_or_noLogin.setVisibility(View.VISIBLE);
        } else {
            no_or_noLogin.setVisibility(View.GONE);
            username = userinfo.getUsername();
            presenter.LoadMyYuYueHosue(username, "1");
        }
        super.onResume();
    }

    private void initView(View view) {
        no_or_noLogin = (ImageView) view.findViewById(R.id.no_or_noLogin);
        lv_daikan = (ListView) view.findViewById(R.id.lv_daikan);
    }

    public void LoadYuYueDatasuccess(List<YuYueData> yuyuedatas) {
        this.yuyuedatas = yuyuedatas;
        adapter = new MyYuYueDateLsitAdapter2(yuyuedatas, getContext());
        lv_daikan.setAdapter(adapter);
    }

    public class MyYuYueDateLsitAdapter2 extends BaseAdapter {
        private List<YuYueData> yuyuedatas;
        private Context context;
        private LayoutInflater Inflater;
        private Typeface typeface;

        public MyYuYueDateLsitAdapter2(List<YuYueData> yuyuedatas, Context context) {
            super();
            this.yuyuedatas = yuyuedatas;
            this.context = context;
            this.Inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return yuyuedatas == null ? 0 : yuyuedatas.size();
        }

        @Override
        public YuYueData getItem(int position) {

            return yuyuedatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = Inflater.inflate(R.layout.item_myyuyue_kanfang, null);
                holder = new ViewHolder();

                holder.tvTitle = (MyTextView) convertView.findViewById(R.id.myyuyue_kanfang_title);
                holder.tvYuYueDate = (MyTextView) convertView.findViewById(R.id.myyuyue_kanfang_time);
                holder.tvZhuangtai = (MyTextView) convertView.findViewById(R.id.myyuyue_kanfang_zhuangtai);
                holder.tvSure = (TextView) convertView.findViewById(R.id.tv_sure);
                holder.tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            final YuYueData n = getItem(position);
            holder.tvPhone.setText(n.getUsername());
            if (n.getLock().equals("0")) {
                holder.tvSure.setText("确定");
                holder.tvSure.setBackgroundResource(R.drawable.shape_textview_sure);
            } else {
                holder.tvSure.setText("已确定");
                holder.tvSure.setEnabled(false);
                holder.tvSure.setBackgroundResource(R.drawable.shape_textview_sure2);
            }

            holder.tvSure.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = n.getId();
                    sureyuyuePresenter.sureyuyue(username, id);

                }
            });

            String a[] = n.getYuyuetime().split("-");

            String b = a[1];
            // 预约超时处理
            try {
                long currenttime = System.currentTimeMillis();
                String gmtTime = n.getYuyuedate() + " " + b;
                long times = TimeTurnDate.getLongByGMT(gmtTime);

                if (times < currenttime) {
                    holder.tvZhuangtai.setText("已过期");
                    holder.tvZhuangtai.setBackgroundResource(R.drawable.daikan_gray);
                    holder.tvSure.setVisibility(View.GONE);
                } else {
                    holder.tvSure.setVisibility(View.VISIBLE);
                    holder.tvZhuangtai.setText(n.getZhuangtai());
                    holder.tvZhuangtai.setBackgroundResource(R.drawable.daikan_blue);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }


            holder.tvTitle.setText(n.getTitle() + " ");
            holder.tvYuYueDate.setText(n.getYuyuedate() + " " + n.getYuyuetime());

            return convertView;
        }

        class ViewHolder {
            TextView tvTitle;
            TextView tvZhuangtai;
            TextView tvYuYueDate;
            TextView tvSure;
            TextView tvPhone;
        }

    }

    public void LoadYuYueDataFaid(String info) {
//		MyToastShowCenter.CenterToast(getContext(), info);
    }

    @Override
    public void sureyuyue(String info) {
        MyToastShowCenter.CenterToast(getContext(), info);
        presenter.LoadMyYuYueHosue(username, "1");
        adapter.notifyDataSetChanged();

    }

}
