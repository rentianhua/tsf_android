package com.dumu.housego;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.SeeHouse;
import com.dumu.housego.util.FontHelper;

import java.util.List;

public class ErShouDaikanActivity extends BaseActivity {
    private ErShouFangDetails e;
    private TextView tv_weekhistroy;
    private TextView tv_totalhistroy;
    private ListView lv_daikanlistview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_shou_daikan);
        setLisenter();

        tv_weekhistroy = (TextView) findViewById(R.id.tv_weekhistroy);
        tv_totalhistroy = (TextView) findViewById(R.id.tv_totalhistroy);
        lv_daikanlistview = (ListView) findViewById(R.id.lv_daikanlistview);
    }

    protected void onResume() {
        e = (ErShouFangDetails) getIntent().getSerializableExtra("er");
        showAllDetail();
        super.onResume();
    }

    private void showAllDetail() {
        if (e != null) {
            List<SeeHouse> list = e.getDaikan();
            List<SeeHouse> wlist = SeeHouse.filterWeekSee(list);
            tv_weekhistroy.setText(wlist != null ? wlist.size() + "" : "0");
            tv_totalhistroy.setText(list != null ? list.size() + "" : "0");

            lv_daikanlistview.setAdapter(new daikaiAdapter());
        }
    }

    private void setLisenter() {
        findViewById(R.id.ll_back_ershoufangdaikan).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    public class daikaiAdapter extends BaseAdapter {
        public int getCount() {
            return e == null || e.getDaikan() == null ? 0 : e.getDaikan().size();
        }

        public SeeHouse getItem(int position) {
            return e.getDaikan().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(ErShouDaikanActivity.this).inflate(R.layout.item_daikan, null);
                holder = new ViewHolder();

                holder.tvDate = (TextView) convertView.findViewById(R.id.tv_date);
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                holder.tvTel = (TextView) convertView.findViewById(R.id.tv_tel);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            SeeHouse n = getItem(position);
            holder.tvDate.setText(n.yuyuedate);
            holder.tvName.setText(n.realname);
            holder.tvTel.setText(n.username);

            return convertView;
        }

        class ViewHolder {
            TextView tvDate;
            TextView tvName;
            TextView tvTel;
        }
    }
}
