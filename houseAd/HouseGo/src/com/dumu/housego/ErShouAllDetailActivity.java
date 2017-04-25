package com.dumu.housego;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.util.FontHelper;

public class ErShouAllDetailActivity extends BaseActivity {
    @ViewInject(R.id.tv_alldetail_huxing)
    TextView tvHuxing;
    @ViewInject(R.id.tv_alldetail_louceng)
    TextView tvLouceng;
    @ViewInject(R.id.tv_alldetail_jianzhumianji)
    TextView tvJianzhumianji;
    @ViewInject(R.id.tv_alldetail_huxingjiegou)
    TextView tvHuxingjiegou;
    @ViewInject(R.id.tv_alldetail_taoneimianji)
    TextView tvTaoneimianji;
    @ViewInject(R.id.tv_alldetail_jianzhuleixing)
    TextView tvJianzhuleixing;
    @ViewInject(R.id.tv_alldetail_chaoxiang)
    TextView tvChaoxiang;
    @ViewInject(R.id.tv_alldetail_jianzhujiegou)
    TextView tvJianzhujiegou;
    @ViewInject(R.id.tv_alldetail_zhuangxiu)
    TextView tvZhuangxiu;
    @ViewInject(R.id.tv_alldetail_tihubili)
    TextView tvTihubili;
    @ViewInject(R.id.tv_alldetail_beiyongdianti)
    TextView tvBeiyongdianti;
    @ViewInject(R.id.tv_alldetail_guapaitime)
    TextView tvGuapaiTime;
    @ViewInject(R.id.tv_alldetail_wuyetype)
    TextView tvWuyeType;
    @ViewInject(R.id.tv_alldetail_shangcijiaoyi)
    TextView tvShangcijiaoyi;
    @ViewInject(R.id.tv_alldetail_fangwutype)
    TextView tvFangwuType;
    @ViewInject(R.id.tv_alldetail_chanquansuoshu)
    TextView tvChanquansuoshu;
    @ViewInject(R.id.tv_alldetail_weiYizhuzhai)
    TextView tvWeiyizhuzhai;
    @ViewInject(R.id.tv_alldetail_diyaxinxi)
    TextView tvDiyaxinxi;


    @ViewInject(R.id.tv_alldetail_zhoubianpeitao)
    TextView tvZhoubianpeitao;
    @ViewInject(R.id.tv_alldetail_quansudiya)
    TextView tvQuansudiya;
    @ViewInject(R.id.tv_alldetail_xiaoquyoushi)
    TextView tvXiaoquyoushi;
    @ViewInject(R.id.tv_alldetail_jiaoyupeitao)
    TextView tvJiaoyupeitao;

    @ViewInject(R.id.tv_alldetail_touzifenxi)
    TextView tvTouzifenxi;
    @ViewInject(R.id.tv_alldetail_hexinmaidian)
    TextView tvHexinmaidian;
    @ViewInject(R.id.tv_alldetail_jiaotongchuxing)
    TextView tvJiaotongchuxing;
    @ViewInject(R.id.tv_alldetail_shuifeijiexi)
    TextView tvShuifeijiexi;
    @ViewInject(R.id.tv_alldetail_zhuangxiumiaoshu)
    TextView tvZhuangxiumiaoshu;
    @ViewInject(R.id.tv_alldetail_tuijianliyou)
    TextView tvTuijianliyou;

    @ViewInject(R.id.iv_ershoualldetall_close)
    ImageView ivClose;

    @ViewInject(R.id.shuxing)
    ScrollView ScShuxing;
    @ViewInject(R.id.tese)
    ScrollView ScTese;

    private ErShouFangDetails e;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_shou_all_detail);
        FontHelper.injectFont(findViewById(android.R.id.content));
        x.view().inject(this);
        setLisenter();
    }

    @Override
    protected void onResume() {
        e = (ErShouFangDetails) getIntent().getSerializableExtra("er");
        tag = getIntent().getStringExtra("erTag");
        if (tag.equals("suxing")) {
            ScShuxing.setVisibility(View.VISIBLE);
            ScTese.setVisibility(View.GONE);
        } else if (tag.equals("tese")) {
            ScTese.setVisibility(View.VISIBLE);
            ScShuxing.setVisibility(View.GONE);
        }

        showAllDetail();
        super.onResume();
    }

    private void showAllDetail() {
        tvBeiyongdianti.setText(e.getDianti());
        tvChanquansuoshu.setText(e.getChanquansuoshu());
        tvChaoxiang.setText(e.getChaoxiang());
        tvDiyaxinxi.setText(e.getDiyaxinxi());
        tvFangwuType.setText(e.getFangwuyongtu());//
        tvGuapaiTime.setText(e.getGuapaidate());
        tvHuxing.setText(e.getShi() + "室" + e.getTing() + "厅");
        tvHuxingjiegou.setText(e.getJiegou());
        tvJianzhujiegou.setText(e.getJianzhujiegou());
        tvJianzhuleixing.setText(e.getJianzhutype());
        tvJianzhumianji.setText(e.getJianzhumianji() + "㎡");
        tvJiaoyupeitao.setText(e.getXuexiaomingcheng());
        tvLouceng.setText(e.getCeng() + "(" + e.getZongceng() + "层)");
        tvQuansudiya.setText(e.getQuanshudiya());
        tvShangcijiaoyi.setText(e.getShangcijiaoyi());
        tvTaoneimianji.setText(e.getTaoneimianji() + "㎡");
        tvTihubili.setText(e.getTihubili());
        tvWeiyizhuzhai.setText(e.getIsweiyi());
        tvWuyeType.setText(e.getJiaoyiquanshu());
        tvXiaoquyoushi.setText(e.getXiaoquyoushi());
        tvZhoubianpeitao.setText(e.getShenghuopeitao());
        tvZhuangxiu.setText(e.getZhuangxiu());

        tvTouzifenxi.setText(e.getTouzifenxi());
        tvHexinmaidian.setText(e.getHexinmaidian());
        tvJiaotongchuxing.setText(e.getJiaotong());
        tvShuifeijiexi.setText(e.getShuifeijiexi());
        tvZhuangxiumiaoshu.setText(e.getZxdesc());
        tvTuijianliyou.setText(e.getYezhushuo());
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
