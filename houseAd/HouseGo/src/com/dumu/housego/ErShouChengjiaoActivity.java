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
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.util.UrlFactory;

public class ErShouChengjiaoActivity extends BaseActivity {
    private ErShouFangDetails e;
    private ListView lv_listview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_shou_chengjiao);
        setLisenter();

        lv_listview = (ListView) findViewById(R.id.lv_listview);
    }

    protected void onResume() {
        e = (ErShouFangDetails) getIntent().getSerializableExtra("er");
        show();
        super.onResume();
    }

    private void show() {
        if (e != null) {
            lv_listview.setAdapter(new er_chenjiaoAdapter());
            lv_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                    if (e != null && e.tongqu != null && e.tongqu.size() > 0) {
                        Intent i = new Intent(ErShouChengjiaoActivity.this, ErShouFangDetailsActivity.class);
                        String catid = e.tongqu.get(0).getCatid();
                        String ID = e.tongqu.get(0).getId();
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

    public class er_chenjiaoAdapter extends BaseAdapter {
        public int getCount() {
            return e == null || e.tongqu == null || e.tongqu.size() <= 0 ? 0 : 1;
        }

        public ErShouFangDetails getItem(int position) {
            return e.tongqu.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(ErShouChengjiaoActivity.this).inflate(R.layout.item_chengjiao_house, null);
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

            ErShouFangDetails n = getItem(position);
            String url = n.getThumb();
            Glide.with(ErShouChengjiaoActivity.this).load(UrlFactory.TSFURL + url).into(holder.image);
            holder.tv1.setText(n.getTitle() + "");
            holder.tv2.setText(n.getCeng() + "(第" + n.getCurceng() + "层)" + n.getChaoxiang());
            holder.tv3.setText("签约日期：" + TimeTurnDate.getStringShortDate(n.getUpdatetime()));

            int zongjia = Integer.valueOf(n.getZongjia().trim());
            int mianji = Integer.valueOf(n.getJianzhumianji());
            if (zongjia <= 0) {
                holder.tv4.setText("价格待定");
                holder.tv5.setText("");
            } else {
                if (mianji <= 0) {
                    holder.tv4.setText(zongjia + "万");
                    holder.tv5.setText("");
                } else {
                    int price = 0;
                    if (zongjia != 0 && mianji != 0) {
                        price = (zongjia) * (10000) / mianji;
                    }
                    holder.tv4.setText(zongjia + "万");
                    holder.tv5.setText(price + "元/平米");
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
