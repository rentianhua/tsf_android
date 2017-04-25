package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.adapter.NewHouseDongTaiAdapter;
import com.dumu.housego.entity.NewDongTai;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;

public class HouseAllDetailsActivity extends BaseActivity {
    private ListView lv_new_dongtai;
    private LinearLayout ll_new_dongtai_back, ll_newdongtai, ll_new_xiangqing_back, ll_new_loupanxiangqing;
    private NewHouseDongTaiAdapter dongtaiAdapter;

    @ViewInject(R.id.tv_kaifashang)
    TextView tvKaifangshang;
    @ViewInject(R.id.tv_cankaojunjia)
    TextView tvCankaoJunjia;
    @ViewInject(R.id.tv_kaipanshijian)
    TextView tvKaipanshijian;
    @ViewInject(R.id.tv_jiaofangshijian)
    TextView tvJiaoFangtime;
    @ViewInject(R.id.tv_jianzhutype)
    TextView tvJianZhuType;
    @ViewInject(R.id.tv_chanquannianxain)
    TextView tvChanquanNianxian;
    @ViewInject(R.id.tv_guihuhushu)
    TextView tvGuiHuahushu;
    @ViewInject(R.id.tv_guihuachewei)
    TextView tvGuiHuaCheWei;
    @ViewInject(R.id.tv_rongjilv)
    TextView tvRongjilv;
    @ViewInject(R.id.tv_lvhualv)
    TextView tvLvHualv;
    @ViewInject(R.id.tv_jianzhumianji)
    TextView tvJianZhuMianji;
    @ViewInject(R.id.tv_wuyegongsi)
    TextView tvWuYegongsi;
    @ViewInject(R.id.tv_wuyefei)
    TextView tvWuYefei;
    @ViewInject(R.id.tv_shuidianranqi)
    TextView tvShuidianranqi;
    @ViewInject(R.id.tv_zhandimianji)
    TextView tvZhandimianji;
    @ViewInject(R.id.tv_wuyetype)
    TextView tvWuyetype;

    private String TAG;

    private List<NewDongTai> dongtais = new ArrayList<NewDongTai>();
    private NewHouseDetail e = new NewHouseDetail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_all_details);
        FontHelper.injectFont(findViewById(android.R.id.content));
        x.view().inject(this);
        initview();
        setlistener();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onResume() {
        TAG = getIntent().getStringExtra("TAG");
        switch (Integer.valueOf(TAG)) {
            case 1:

                ll_newdongtai.setVisibility(View.VISIBLE);
                ll_new_loupanxiangqing.setVisibility(View.GONE);

                String nu = getIntent().getStringExtra("dongtaiNull");
                if (nu.equals("w")) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "暂无动态");
                } else {
                    this.dongtais = (List<NewDongTai>) getIntent().getSerializableExtra("dongtai");
                    dongtaiAdapter = new NewHouseDongTaiAdapter(dongtais, getApplicationContext());
                    lv_new_dongtai.setAdapter(dongtaiAdapter);
                }
                break;
            case 2:
                ll_newdongtai.setVisibility(View.GONE);
                ll_new_loupanxiangqing.setVisibility(View.VISIBLE);

                this.e = (NewHouseDetail) getIntent().getSerializableExtra("xiangqing");
                ShowAllDetails();
                break;
        }
        super.onResume();
    }


    private void ShowAllDetails() {
        tvCankaoJunjia.setText(e.getJunjia() + "元/㎡");
        if (!e.getChanquannianxian().equals("")) {
            tvChanquanNianxian.setText(e.getChanquannianxian() + "年");
        }
        tvGuiHuaCheWei.setText(e.getGuihuachewei());
        tvGuiHuahushu.setText(e.getGuihuahushu());
        tvJianZhuMianji.setText(e.getJianzhumianji() + "㎡");
        tvWuyetype.setText(e.getWuyeleixing());

        //@[@"塔楼", @"板楼", @"板塔结合"];
        String jztype = "";
        if (e.getJianzhuleixing() != null) {
            if (e.getJianzhuleixing().equals("1")) {
                jztype = "塔楼";
            }
            if (e.getJianzhuleixing().equals("2")) {
                jztype = "板楼";
            }
            if (e.getJianzhuleixing().equals("3")) {
                jztype = "板塔结合";
            }
        }
        tvJianZhuType.setText(jztype);

        tvJiaoFangtime.setText(e.getJiaofangdate());
        tvKaifangshang.setText(e.getKaifashang());
        tvKaipanshijian.setText(e.getKaipandate());
        tvLvHualv.setText(e.getLvhualv());
        tvRongjilv.setText(e.getRongjilv());
        tvShuidianranqi.setText(e.getShuidianranqi());
        tvWuYefei.setText(e.getWuyefei());
        tvWuYegongsi.setText(e.getWuyegongsi());
        tvZhandimianji.setText(e.getZhandimianji() + "㎡");
    }

    private void initview() {
        lv_new_dongtai = (ListView) findViewById(R.id.lv_new_dongtai);
        ll_new_dongtai_back = (LinearLayout) findViewById(R.id.ll_new_dongtai_back);
        ll_newdongtai = (LinearLayout) findViewById(R.id.ll_newdongtai);
        ll_new_xiangqing_back = (LinearLayout) findViewById(R.id.ll_new_xiangqing_back);
        ll_new_loupanxiangqing = (LinearLayout) findViewById(R.id.ll_new_loupanxiangqing);
    }

    private void setlistener() {
        ll_new_dongtai_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_new_xiangqing_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
