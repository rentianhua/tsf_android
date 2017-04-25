package com.dumu.housego;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.util.FontHelper;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class RentingAllDetailActivity extends BaseActivity {
    @ViewInject(R.id.tv_alldetail_xiaoqujieshao)
    TextView tvXiaoqujieshao;
    @ViewInject(R.id.tv_alldetail_fangwopeitao)
    TextView tvFangwopeitao;
    @ViewInject(R.id.tv_alldetail_shenghuopeitao)
    TextView tvShenghuopeitao;
    @ViewInject(R.id.tv_alldetail_zhuangxiumiaoshu)
    TextView tvZhuangxiumiaoshu;
    @ViewInject(R.id.tv_alldetail_chuzuyuanyin)
    TextView tvChuzuyuanyin;
    @ViewInject(R.id.tv_alldetail_yezhushuo)
    TextView tvYezhushuo;

    @ViewInject(R.id.iv_ershoualldetall_close)
    ImageView ivClose;

    private RentingDetail rt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rt_shou_all_detail);
        FontHelper.injectFont(findViewById(android.R.id.content));
        x.view().inject(this);
        setLisenter();
    }

    protected void onResume() {
        rt = (RentingDetail) getIntent().getSerializableExtra("rt");
        showAllDetail();
        super.onResume();
    }

    private void showAllDetail() {
        if (rt != null) {
            tvXiaoqujieshao.setText(rt.getXiaoquintro());
            tvFangwopeitao.setText(rt.getFangwupeitao());
            tvShenghuopeitao.setText(rt.getShenghuopeitao());
            tvZhuangxiumiaoshu.setText(rt.getZxdesc());
            tvChuzuyuanyin.setText(rt.getCzreason());
            tvYezhushuo.setText(rt.getYezhushuo());
        }
    }

    private void setLisenter() {
        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
