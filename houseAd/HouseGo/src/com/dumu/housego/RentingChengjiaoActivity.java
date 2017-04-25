package com.dumu.housego;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.util.UrlFactory;

public class RentingChengjiaoActivity extends BaseActivity {
    private RentingDetail b;
    private ListView lv_listview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renting_chengjiao);
        setLisenter();

        lv_listview = (ListView) findViewById(R.id.lv_listview);
    }

    protected void onResume() {
        b = (RentingDetail) getIntent().getSerializableExtra("rt");
        show();
        super.onResume();
    }

    private void show() {
        if (b != null) {
            lv_listview.setAdapter(new renting_chenjiaoAdapter());
            lv_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                    if (b != null && b.tongqu != null && b.tongqu.size() > 0) {
                        Intent i = new Intent(RentingChengjiaoActivity.this, RentingDetailActivity.class);
                        String catid = b.tongqu.get(0).getCatid();
                        String ID = b.tongqu.get(0).getId();
                        i.putExtra("catid", catid);
                        i.putExtra("id", ID);
                        startActivity(i);
                    }
                }
            });
        }
    }

    private void setLisenter() {
        findViewById(R.id.ll_back_chengjiao).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    public class renting_chenjiaoAdapter extends BaseAdapter {
        public int getCount() {
            return b == null || b.tongqu == null || b.tongqu.size() <= 0 ? 0 : 1;
        }

        public RentingDetail getItem(int position) {
            return b.tongqu.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(RentingChengjiaoActivity.this).inflate(R.layout.item_chengjiao_house, null);
                holder = new ViewHolder();

                holder.image = (ImageView) convertView.findViewById(R.id.image);
                holder.tv1 = (TextView) convertView.findViewById(R.id.tv_title1);
                holder.tv2 = (TextView) convertView.findViewById(R.id.tv_title2);
                holder.tv3 = (TextView) convertView.findViewById(R.id.tv_title3);
                holder.tv4 = (TextView) convertView.findViewById(R.id.tv_title4);
                holder.tv5 = (TextView) convertView.findViewById(R.id.tv_title5);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            RentingDetail n = getItem(position);
            String url = n.getThumb();
            Glide.with(RentingChengjiaoActivity.this).load(UrlFactory.TSFURL + url).into(holder.image);
            holder.tv1.setText(n.getTitle() + "");
            holder.tv2.setText(n.getCeng() + "(第" + n.getZongceng() + "层)" + n.getChaoxiang());
            holder.tv3.setText("成交日期：" + TimeTurnDate.getStringShortDate(n.getUpdatetime()));

            int zongjia = Integer.valueOf(n.getZujin().trim());
            int mianji = Integer.valueOf(n.getMianji());
            if (zongjia <= 0) {
                holder.tv4.setText("价格待定");
                holder.tv5.setText("");
            } else {
                if (mianji <= 0) {
                    holder.tv4.setText(zongjia + "元");
                    holder.tv5.setText("");
                } else {
                    holder.tv4.setText(zongjia + "元/月");
                    holder.tv5.setText(mianji + "平米");
                }
            }

            return convertView;
        }

        class ViewHolder {
            ImageView image;
            TextView tv1;
            TextView tv2;
            TextView tv3;
            TextView tv4;
            TextView tv5;
        }
    }
}
