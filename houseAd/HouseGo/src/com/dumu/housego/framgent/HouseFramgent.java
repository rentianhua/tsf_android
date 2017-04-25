package com.dumu.housego.framgent;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.ErShouFangDetailsActivity;
import com.dumu.housego.R;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.MyTextView;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.entity.YuYueData;
import com.dumu.housego.presenter.IMyDaiKanHousePresenter;
import com.dumu.housego.presenter.ISureYuYuePresenter;
import com.dumu.housego.presenter.MyDaiKanPresenter;
import com.dumu.housego.presenter.SureYuYuePresenter;
import com.dumu.housego.util.CarouselPagerAdapter;
import com.dumu.housego.util.CarouselViewPager;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.NoScrollViewPager;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.view.IMyYuYueHouseView;
import com.dumu.housego.view.ISureYuYueView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class HouseFramgent extends Fragment implements CarouselViewPager.OnPageChangeListener, IMyYuYueHouseView, ISureYuYueView {
    private Button btnHouseLogin;
    private NoScrollViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private List<Fragment> fragmentss;
    private LinearLayout llContorl;
    private UserInfo userinfo;
    private Button btnLookDate, btnLookHistroy, btnHouseNoLoginLogin;
    private LinearLayout ll_agent_house, ll_noLogin_house, ll_putong_house;

    private CarouselViewPager mCarouselView;
    private List<ImageView> ivList = new ArrayList<ImageView>();
    private int[] ivIds = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};

    private ImageView[] indicationPoint;
    private LinearLayout pointLayout;
    private ListView lv_agent_kanfang;
    private ImageView agent_no_message;

    private List<YuYueData> yuyuedatas;
    private MyYuYueDateLsitAdapter2 adapter;
    private IMyDaiKanHousePresenter presenter;
    private ISureYuYuePresenter sureyuyuePresenter;
    private String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgent_house, null);
        setViews(view);
        initData();
        presenter = new MyDaiKanPresenter(this);
        sureyuyuePresenter = new SureYuYuePresenter(this);
        setListener();
        setViewPagerAdapter();
        btnLookDate.setTextColor(getResources().getColor(android.R.color.white));
        btnLookDate.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        FontHelper.injectFont(view);

        return view;
    }

    private void initData() {
        for (int i = 0; i < ivIds.length; i++) {
            ImageView iv = new ImageView(getActivity());
            iv.setImageResource(ivIds[i]);
            ivList.add(iv);
        }

        indicationPoint = new ImageView[ivList.size()];
        for (int i = 0; i < indicationPoint.length; i++) {
            ImageView point = new ImageView(getActivity());
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(10, 10);
            layout.setMargins(10, 0, 10, 0);
            point.setLayoutParams(layout);

            indicationPoint[i] = point;
            if (i == 0) {
                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            pointLayout.addView(point);
        }

        mCarouselView.setAdapter(new CarouselPagerAdapter(ivList));
        mCarouselView.addOnPageChangeListener(this);
        mCarouselView.setDisplayTime(2000);
        mCarouselView.start();

    }

    @Override
    public void onResume() {
        userinfo = HouseGoApp.getLoginInfo(getContext());
        if (userinfo != null) {

            if (userinfo.getModelid().equals("35")) {
                ll_noLogin_house.setVisibility(View.GONE);
                ll_agent_house.setVisibility(View.GONE);
                ll_putong_house.setVisibility(View.VISIBLE);
            } else {
                username = userinfo.getUsername();
                presenter.LoadMyYuYueHosue(username, "1");
                ll_putong_house.setVisibility(View.GONE);
                ll_noLogin_house.setVisibility(View.GONE);
                ll_agent_house.setVisibility(View.VISIBLE);
            }

        } else {
            ll_agent_house.setVisibility(View.GONE);
            ll_putong_house.setVisibility(View.GONE);
            ll_noLogin_house.setVisibility(View.VISIBLE);
        }

        super.onResume();
    }

    private void setViewPagerAdapter() {
        fragmentss = new ArrayList<Fragment>();
        fragmentss.add(new LookDateFragment());
        fragmentss.add(new LookHistroyFragment());
        pagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);

    }

    private void setListener() {
        btnLookDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                btnLookDate.setTextColor(getResources().getColor(android.R.color.white));
                btnLookDate.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnLookHistroy.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                btnLookHistroy.setBackgroundColor(getResources().getColor(android.R.color.white));
            }
        });
        btnLookHistroy.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                btnLookHistroy.setTextColor(getResources().getColor(android.R.color.white));
                btnLookHistroy.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                btnLookDate.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                btnLookDate.setBackgroundColor(getResources().getColor(android.R.color.white));
            }
        });

        btnHouseNoLoginLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

    }

    private void setViews(View view) {
        viewPager = (NoScrollViewPager) view.findViewById(R.id.house_viewpage);
        llContorl = (LinearLayout) view.findViewById(R.id.ll_contorl);
        btnLookDate = (Button) view.findViewById(R.id.tv_look_date);
        btnLookHistroy = (Button) view.findViewById(R.id.tv_look_histroy);

        ll_agent_house = (LinearLayout) view.findViewById(R.id.ll_agent_house);
        ll_noLogin_house = (LinearLayout) view.findViewById(R.id.ll_noLogin_house);
        ll_putong_house = (LinearLayout) view.findViewById(R.id.ll_putong_house);

        mCarouselView = (CarouselViewPager) view.findViewById(R.id.nologin_mCarouselView);
        pointLayout = (LinearLayout) view.findViewById(R.id.nologin_pointLayout);

        btnHouseNoLoginLogin = (Button) view.findViewById(R.id.btn_house_noLogin_Login);

        agent_no_message = (ImageView) view.findViewById(R.id.agent_no_message);
        lv_agent_kanfang = (ListView) view.findViewById(R.id.lv_agent_kanfang);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentss.get(position);
        }

        @Override
        public int getCount() {
            return fragmentss == null ? 0 : fragmentss.size();
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setPointColor(position % ivList.size());

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setPointColor(int selectItem) {
        for (int i = 0; i < indicationPoint.length; i++) {
            if (i == selectItem) {
                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

        }
    }


    @Override
    public void LoadYuYueDataFaid(String info) {
        agent_no_message.setVisibility(View.VISIBLE);
        lv_agent_kanfang.setVisibility(View.GONE);
    }

    @Override
    public void sureyuyue(String info) {
        MyToastShowCenter.CenterToast(getContext(), info);
        presenter.LoadMyYuYueHosue(username, "1");
        adapter.notifyDataSetChanged();

    }

    @Override
    public void LoadYuYueDatasuccess(List<YuYueData> yuyuedatas) {
        this.yuyuedatas = yuyuedatas;
        adapter = new MyYuYueDateLsitAdapter2(yuyuedatas, getContext());
        lv_agent_kanfang.setAdapter(adapter);
        lv_agent_kanfang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Id = HouseFramgent.this.yuyuedatas.get(position).getFromid();
                Intent i = new Intent(getActivity(), ErShouFangDetailsActivity.class);
                i.putExtra("catid", "6");
                i.putExtra("id", Id);
                startActivity(i);
            }
        });
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
                holder.tvSure.setEnabled(true);
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
}
