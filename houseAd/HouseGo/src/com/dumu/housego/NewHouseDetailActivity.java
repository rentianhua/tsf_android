package com.dumu.housego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import autolooppager.AutoLoopLayout;
import autolooppager.ILoopAdapter;
import util.BigPicActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.adapter.NewHouseDongTaiAdapter;
import com.dumu.housego.adapter.NewHouseHotAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.NewDongTai;
import com.dumu.housego.entity.NewHouseDetail;
import com.dumu.housego.entity.NewHouseHotRecommend;
import com.dumu.housego.entity.NewHtml;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.entity.YHQ;
import com.dumu.housego.entity.YHQinfo;
import com.dumu.housego.pay.AuthResult;
import com.dumu.housego.pay.H5PayDemoActivity;
import com.dumu.housego.pay.PayResult;
import com.dumu.housego.pay.util.OrderInfoUtil2_0;
import com.dumu.housego.presenter.GuanZhuHousePresenter;
import com.dumu.housego.presenter.GuanZhuNewPresenter;
import com.dumu.housego.presenter.IGuanZhuHousePresenter;
import com.dumu.housego.presenter.IGuanZhuNewPresenter;
import com.dumu.housego.presenter.INewHouseDetailPresenter;
import com.dumu.housego.presenter.INewTuiJianPresenter;
import com.dumu.housego.presenter.IPaySuccessPresenter;
import com.dumu.housego.presenter.IYHQGetyzmPresenter;
import com.dumu.housego.presenter.NewHouseDetailPresenter;
import com.dumu.housego.presenter.NewHouseTuiJianPresenter;
import com.dumu.housego.presenter.PaySuccessPresenter;
import com.dumu.housego.presenter.YHQGetYzmPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.view.IGuanZhuHouseView;
import com.dumu.housego.view.IGuanZhuNewView;
import com.dumu.housego.view.INewHouseDetailView;
import com.dumu.housego.view.INewTuiJianView;
import com.dumu.housego.view.IPaySuccessView;
import com.dumu.housego.view.IYHQGetYzmView;
import com.example.testpic.PhotoActivity;
import com.example.testpic.PublishedActivity;

public class NewHouseDetailActivity extends BaseActivity implements IPaySuccessView, INewTuiJianView, IGuanZhuNewView, IGuanZhuHouseView, IYHQGetYzmView, INewHouseDetailView {
    private INewHouseDetailPresenter presenter;
    private IYHQGetyzmPresenter yhqpresenter;
    private NewHouseDetail e;
    private List<YHQinfo> yhqs;

    private INewTuiJianPresenter tuijianPresenter;
    private ListViewForScrollView lvLoupanDongtai, lv_new_tuijian;
    private TextView tvNoDongTai;

    private TextView tv_yhq_none, tv_new_yhq_1, tv_new_yhq_2, tv_new_yhq_3,tv_new_yhq_4,tv_new_yhq_5;
    private RelativeLayout rl_new_yhq;
    @ViewInject(R.id.ll_new_house_detail_back)
    LinearLayout llNewHouseDetailBack;
    @ViewInject(R.id.tv_newhousedetail_price)
    TextView tvNewhousedetailPrice;
    @ViewInject(R.id.ms_newhouse)
    MyReboundScrollView ms_newhouse;

    @ViewInject(R.id.rl_ditu_new)
    RelativeLayout rlDituNew;

    @ViewInject(R.id.ll_agent_bottom)
    LinearLayout llagentbottom;
    @ViewInject(R.id.ll_newhousedetail_bottom)
    LinearLayout llNewhousedetailBottom;


    @ViewInject(R.id.tv_new_house_detail_title)
    TextView tvTitle;
    @ViewInject(R.id.tv_agentzixun)
    TextView tv_agentzixun;
    @ViewInject(R.id.tv_bianhao)
    TextView tv_bianhao;


    @ViewInject(R.id.tv_newhousedetail_title)
    TextView tvTitle1;
    @ViewInject(R.id.tv_newhousedetail_housetype)
    TextView tvHousetype;
    @ViewInject(R.id.tv_newhousedetail_kaipanshijian)
    TextView tvkaipanshijian;
    @ViewInject(R.id.tv_newhousedetail_jiaofangshijian)
    TextView tvjiaofangshijian;
    @ViewInject(R.id.tv_newhousedetail_updatetime)
    TextView tvNewhousedetailUpdatetime;
    @ViewInject(R.id.tv_newhousedetail_maintype)
    TextView tvNewhousedetailMaintype;
    @ViewInject(R.id.tv_newhousedetail_loupandizhi)
    TextView tvNewhousedetailLoupandizhi;

    @ViewInject(R.id.tv_newhousedetail_kaifashang)
    TextView tvNewhousedetailKaifashang;
    @ViewInject(R.id.tv_newhousedetail_zuixinkaipan)
    TextView tvNewhousedetailZuixinkaipan;
    @ViewInject(R.id.tv_newhousedetail_chanquannianxian)
    TextView tvNewhousedetailChanquannianxian;
    @ViewInject(R.id.tv_newhousedetail_zuixinjiaofang)
    TextView tvNewhousedetailZuixinjiaofang;
    @ViewInject(R.id.tv_newhousedetail_dianping)
    TextView tvNewhousedetailDianping;

    @ViewInject(R.id.wb_huxingjieshao)
    WebView wbHuXing;
    @ViewInject(R.id.wb_fukuanfangshi)
    WebView wbfukuanfangshi;
    private BaiduMap mBaiduMAP;
    private MapView mMapView;

    @ViewInject(R.id.yishouImageView)
    ImageView yishouImageView;

    String total_amount;

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2016072301657641";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "2088421264539813";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "ruianxingye888@126.com";

    /**
     * 商户私钥，pkcs8格式
     */
    public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANPTGHR7P3aPng0zA841Gh6uE19hesCd6NlOGBZKeXzTq5PLTPA0tGGAfIpqx1ze+hWsz5On/P2fBILF3U23S1u7wP/Yem1Ci2T4DYx5AZZe6xz927nNsnJO7vrUohl25/tXuSn/LaY01RABabvt1p5+a50uAqCS0FkapzqPebCFAgMBAAECgYEAkjPXYz5WFU0XN+EINWGtf5OCx4iOozfaqXIfafNJWwD2IfJmTjzya4G1dAwzQkSctC0ssKt4EM2a3XAYSTXECnBb4zJ7ppOhI8+OOPfScEnalCqq7XAC33bcgG17PHLfyAqesh0f+o5uwVptXpGOjX7Cu0qdYCjn3VAybVgi1kECQQD8C6T16v+mj8E9oG2p7IkuZmywqF8zc4dNY5lK7tjk+BnaFPe3Xy8uUZUYEfo11Rg3pjGYlT6/djaNgJm3/VNZAkEA1yXmy8sJ9ix4+QUKODRTN9BpgYh4ummSFgsP/DRMtA0LZCK83s/Kgu9fjuxVo/cSOjqAuywOZ1AYBkDkCfp9DQJARvO8N2I1H51eR8vusyQcJgy9UinDywcdsqJ0F80PD73sASFf7qYD8SUUNJdy+U6Ip7nIQmzZIirUBpeKLmpI2QJBAKmrtkvZn82IXQ7lrp2MhmRp9Aq3eZ5pS1AfAUhAZo1IDEe4LYL6FBcWeCHat99LJhDNul/h6qoHPCsSWcSUyrECQQDKnNGXtAwGQLYxIYIEHiWbmidKuJZoG8XgohLLvUGOx7uBDbU4e4TohyffdGJ9uZqF2v1OCtYLhqu/O56oCnGE";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private String trade_no;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    Mpop.dismiss();
                    llpopupSpinnerMore.clearAnimation();
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {

                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。

                        try {

                            JSONObject b = new JSONObject(resultInfo);
                            Log.e("resultInfo", "resultInfo=bbb=" + b.toString());
                            JSONObject j = b.getJSONObject("alipay_trade_app_pay_response");
                            trade_no = j.getString("trade_no");
                            Log.e("resultInfo", "resultInfo=" + resultInfo);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("paypresenter", "paypresenter=" + resultStatus + "  " + total_amount + "   " + order_no + "   " + trade_no);

                        paypresenter.PayInfo(resultStatus, total_amount, order_no, trade_no);


                        MyToastShowCenter.CenterToast(NewHouseDetailActivity.this, "支付成功");
                    } else {
                        Mpop.dismiss();
                        llpopupSpinnerMore.clearAnimation();
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        MyToastShowCenter.CenterToast(NewHouseDetailActivity.this, "支付失败");
                    }
                    break;
                }

                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户

                        MyToastShowCenter.CenterToast(NewHouseDetailActivity.this, "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()));
                    } else {
                        // 其他状态值则为授权失败
                        MyToastShowCenter.CenterToast(NewHouseDetailActivity.this, "授权失败" + String.format("authCode:%s", authResult.getAuthCode()));
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    e = (NewHouseDetail) msg.obj;

                    Show();
                    showPopWindowYHQ();
                    break;

                default:
                    break;
            }
        }
    };

    private String order_no;
    private BitmapDescriptor mCurrentMarker;
    private String id;
    private String catid;
    private String coupon_id;
    private String coupon_id1;
    private String coupon_id2;
    private String coupon_id3;
    private String coupon_id4;
    private String coupon_id5;
    private UserInfo userinfo;
    private IPaySuccessPresenter paypresenter;
    private LinearLayout llpopupSpinnerMore;
    private PopupWindow Mpop;
    private String userid;
    private String username;
    private String title = "";
    private NewHtml html;
    private List<NewDongTai> dongtais;
    private List<NewDongTai> oneDongtai = new ArrayList<NewDongTai>();
    //轮播图
    private AutoLoopLayout<Pics> mSlider;

    private List<Pics> pics = new ArrayList<Pics>();
    private List<Pics> loupan = new ArrayList<Pics>();
    private List<Pics> weizhi = new ArrayList<Pics>();
    private List<Pics> yangban = new ArrayList<Pics>();
    private List<Pics> shijing = new ArrayList<Pics>();
    private List<Pics> xiaoqu = new ArrayList<Pics>();
    private List<Pics> xiangcePics = new ArrayList<Pics>();
    @ViewInject(R.id.xiangce_gridview)
    GridView xiangce_gridview;

    //发送验证码变化
    Thread thread = null;
    private boolean tag12 = true;
    private int i = 60;
    public boolean isChange = false;

    private NewHouseDongTaiAdapter dongtaiAdapter;

    private TextView tv_html_fukuanfangshi, tv_newhousezixun;

    private RelativeLayout rl_loupanxiangqing, rl_new_loupandongtai;

    private Button rb_newhouseguanzhu;

    private IGuanZhuHousePresenter guanzhupresenter;

    private IGuanZhuNewPresenter newpresenter;

    //private String t = "1";

    private List<NewHouseDetail> newhousedetails = new ArrayList<NewHouseDetail>();
    private List<NewHouseHotRecommend> tuijians;

    private NewHouseHotAdapter tuijianadapter;
    private PopupWindow Surepop;
    private LinearLayout SurePhone;
    private TextView item_pop_boda;
    protected View view;
    private YHQ yhq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_house_detail);
        FontHelper.injectFont(findViewById(android.R.id.content));
        x.view().inject(this);
        initView();

        showSurePhone();
        setListener();

        id = getIntent().getStringExtra("Id");
        catid = getIntent().getStringExtra("catid");
        paypresenter = new PaySuccessPresenter(this);
        presenter = new NewHouseDetailPresenter(this);
        guanzhupresenter = new GuanZhuHousePresenter(this);
        tuijianPresenter = new NewHouseTuiJianPresenter(this);
        yhqpresenter = new YHQGetYzmPresenter(this);
        newpresenter = new GuanZhuNewPresenter(this);

    }

    @Override
    protected void onResume() {
        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
        presenter.FindNewHousedetail(catid, id);
        presenter.GetYHQinfo(id, catid);
        tuijianPresenter.Tuijian("5");
        if (userinfo != null) {
            userid = userinfo.getUserid();
            username = userinfo.getUsername();
            newpresenter.LoadGuanZhuNew(username, "new");
        } else {

        }


        mMapView.onResume();
        super.onResume();
    }

    private void initView() {
        lv_new_tuijian = (ListViewForScrollView) findViewById(R.id.lv_new_tuijian);
        tv_newhousezixun = (TextView) findViewById(R.id.tv_newhousezixun);
        rb_newhouseguanzhu = (Button) findViewById(R.id.rb_newhouseguanzhu);

        rl_loupanxiangqing = (RelativeLayout) findViewById(R.id.rl_loupanxiangqing);
        rl_new_loupandongtai = (RelativeLayout) findViewById(R.id.rl_new_loupandongtai);
        tv_html_fukuanfangshi = (TextView) findViewById(R.id.tv_html_fukuanfangshi);
        lvLoupanDongtai = (ListViewForScrollView) findViewById(R.id.lv_newhouse_loupandongtai);
        tvNoDongTai = (TextView) findViewById(R.id.wudongtai);


        wbfukuanfangshi.getSettings().setJavaScriptEnabled(true);
        wbfukuanfangshi.getSettings().setUseWideViewPort(true);
        wbfukuanfangshi.getSettings().setLoadWithOverviewMode(true);
        wbHuXing.getSettings().setJavaScriptEnabled(true);
        wbHuXing.getSettings().setUseWideViewPort(true);
        wbHuXing.getSettings().setLoadWithOverviewMode(true);

        rl_new_yhq = (RelativeLayout) findViewById(R.id.rl_new_yhq);
        tv_yhq_none = (TextView) findViewById(R.id.tv_yhq_none);
        tv_new_yhq_1 = (TextView) findViewById(R.id.tv_new_yhq_1);
        tv_new_yhq_2 = (TextView) findViewById(R.id.tv_new_yhq_2);
        tv_new_yhq_3 = (TextView) findViewById(R.id.tv_new_yhq_3);
        tv_new_yhq_4 = (TextView) findViewById(R.id.tv_new_yhq_4);
        tv_new_yhq_5 = (TextView) findViewById(R.id.tv_new_yhq_5);
        mSlider = (AutoLoopLayout<Pics>) findViewById(R.id.slider);

        mMapView = (MapView) findViewById(R.id.new_bmapView);
        mBaiduMAP = mMapView.getMap();

        mMapView.showZoomControls(false);
        mMapView.showScaleControl(false);

        UiSettings settings = mBaiduMAP.getUiSettings();
        settings.setAllGesturesEnabled(false);

        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f);
        mBaiduMAP.setMapStatus(msu);
    }

    @ViewInject(R.id.tv_zonghepingfen)
    TextView tv_zonghepingfen;

    @ViewInject(R.id.zhoubianpeizhi_tv_fenshu)
    TextView zhoubianpeizhi_tv_fenshu;
    private int zhoubianpeizhi_xxIds[] = {
            R.id.zhoubianpeizhi_xx01,
            R.id.zhoubianpeizhi_xx02,
            R.id.zhoubianpeizhi_xx03,
            R.id.zhoubianpeizhi_xx04,
            R.id.zhoubianpeizhi_xx05
    };

    @ViewInject(R.id.jiaotongfangbian_tv_fenshu)
    TextView jiaotongfangbian_tv_fenshu;
    private int jiaotongfangbian_xxIds[] = {
            R.id.jiaotongfangbian_xx01,
            R.id.jiaotongfangbian_xx02,
            R.id.jiaotongfangbian_xx03,
            R.id.jiaotongfangbian_xx04,
            R.id.jiaotongfangbian_xx05
    };

    @ViewInject(R.id.lvhuahuanjing_tv_fenshu)
    TextView lvhuahuanjing_tv_fenshu;
    private int lvhuahuanjing_xxIds[] = {
            R.id.lvhuahuanjing_xx01,
            R.id.lvhuahuanjing_xx02,
            R.id.lvhuahuanjing_xx03,
            R.id.lvhuahuanjing_xx04,
            R.id.lvhuahuanjing_xx05
    };

    private void showPinfen() {
        int totalDefen = 0;
        if (e.getDafen_zbpt() != null && e.getDafen_zbpt().length() > 0) {
            int df = Integer.parseInt(e.getDafen_zbpt());
            zhoubianpeizhi_tv_fenshu.setText(df + "分");
            totalDefen += df;
            for (int i = 0; i < zhoubianpeizhi_xxIds.length; i++) {
                ImageView xx = (ImageView) findViewById(zhoubianpeizhi_xxIds[i]);
                if (i < df)
                    xx.setSelected(true);
                else
                    xx.setSelected(false);
            }
        }

        if (e.getDafen_jt() != null && e.getDafen_jt().length() > 0) {
            int df = Integer.parseInt(e.getDafen_jt());
            jiaotongfangbian_tv_fenshu.setText(df + "分");
            totalDefen += df;
            for (int i = 0; i < jiaotongfangbian_xxIds.length; i++) {
                ImageView xx = (ImageView) findViewById(jiaotongfangbian_xxIds[i]);
                if (i < df)
                    xx.setSelected(true);
                else
                    xx.setSelected(false);
            }
        }

        if (e.getDafen_hj() != null && e.getDafen_hj().length() > 0) {
            int df = Integer.parseInt(e.getDafen_hj());
            lvhuahuanjing_tv_fenshu.setText(df + "分");
            totalDefen += df;
            for (int i = 0; i < lvhuahuanjing_xxIds.length; i++) {
                ImageView xx = (ImageView) findViewById(lvhuahuanjing_xxIds[i]);
                if (i < df)
                    xx.setSelected(true);
                else
                    xx.setSelected(false);
            }
        }

        tv_zonghepingfen.setText("综合评分: " + totalDefen / 3 + "分");
    }

    protected void Show() {
        if (e == null) return;

        try {
            showPinfen();

            if (e.getZaishou() != null && e.getZaishou().equals("0"))
                yishouImageView.setVisibility(View.VISIBLE);
            else
                yishouImageView.setVisibility(View.GONE);

            tv_bianhao.setText(e.getBianhao());
            tv_agentzixun.setText("联系方式： " + e.getContacttel());
            tv_newhousezixun.setText("联系方式： " + e.getContacttel());

            if (userinfo != null) {
                if (userinfo.getModelid().equals("35")) {
                    llagentbottom.setVisibility(View.GONE);
                    llNewhousedetailBottom.setVisibility(View.VISIBLE);
                } else {
                    llagentbottom.setVisibility(View.VISIBLE);
                    llNewhousedetailBottom.setVisibility(View.GONE);
                }
            } else {
                llagentbottom.setVisibility(View.GONE);
                llNewhousedetailBottom.setVisibility(View.VISIBLE);
            }

            this.html = e.getHtml();
            this.dongtais = e.getDongtais();

            if (dongtais != null) {
                lvLoupanDongtai.setVisibility(View.VISIBLE);
                tvNoDongTai.setVisibility(View.GONE);
                oneDongtai.clear();
                oneDongtai.add(dongtais.get(0));
                dongtaiAdapter = new NewHouseDongTaiAdapter(oneDongtai, getApplicationContext());
                lvLoupanDongtai.setAdapter(dongtaiAdapter);
            } else {
                lvLoupanDongtai.setVisibility(View.GONE);
                tvNoDongTai.setVisibility(View.VISIBLE);
            }


            if (html != null) {
                if (!html.getHuxingintro().equals("") && html.getHuxingintro() != null) {
                    String intro = html.getHuxingintro();
                    String intro1 = intro.replace("/d", "https://www.taoshenfang.com/d");

                    String customHtml = "<!DOCTYPE html>"
                            + "<html>"
                            + "<head>"
                            + "<meta charset=\"utf-8\">"
                            + "<meta content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\" name=\"viewport\" />"
                            + "<meta http-equiv=\"Cache-Control\" content=\"no-cache\" />"
                            + "<meta http-equiv=\"Expires\" content=\"0\" />"
                            + "<meta name='viewport' content='width=320' />"
                            + "<meta http-equiv=\"Access-Control-Allow-Origin\" content=\"*\" />"
                            + "<meta name=\"format-detection\" content=\"telephone=no\" />"
                            + "<style> "
                            + "body{ width:100%;padding:0 16px; font-size:14px; margin:10px; background-color:#fff;color:#333; font-weight:400;  word-wrap:break-word;font-family:Arial;   word-break: break-all;}"
                            + "	p{text-align:left;line-height:20px; }"
                            + "img{max-width:220px; height:auto;} "
                            + "div{ text-align:center;width:90%;margin:auto}"
                            + "</style>"
                            + "</head> "
                            + "<body>"
                            + intro1 + "</body>"
                            + "</html>";
                    wbHuXing.loadData(customHtml, "text/html; charset=UTF-8", null);
                }

                if (!html.getFukuanfangshi().equals("") && html.getFukuanfangshi() != null) {

                    tv_html_fukuanfangshi.setVisibility(View.GONE);
                    wbfukuanfangshi.setVisibility(View.VISIBLE);

                    String fukun = html.getFukuanfangshi();
                    String fukun2 = fukun.replace("/d", "https://www.taoshenfang.com/d");
                    String customHtml = "<!DOCTYPE html>"
                            + "<html>"
                            + "<head>"
                            + "<meta charset=\"utf-8\">"
                            + "<meta content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\" name=\"viewport\" />"
                            + "<meta http-equiv=\"Cache-Control\" content=\"no-cache\" />"
                            + "<meta http-equiv=\"Expires\" content=\"0\" />"
                            + "<meta http-equiv=\"Access-Control-Allow-Origin\" content=\"*\" />"
                            + "<meta name='viewport' content='width=320' />"
                            + "<meta name=\"format-detection\" content=\"telephone=no\" />"
                            + "<style> "
                            + "body{ width:100%;padding:0 16px; font-size:14px; margin:0; background-color:#fff;color:#333; font-weight:400; word-break: break-all;}"
                            + "	p{text-align:left;line-height:20px; }"
                            + "img{max-width:220px; height:auto;} "
                            + "div{ text-align:center;}"
                            + "</style>"
                            + "</head> "
                            + "<body>"
                            + fukun2 + "</body>"
                            + "</html>";
                    wbfukuanfangshi.loadData(customHtml, "text/html; charset=UTF-8", null);
                } else {
                    tv_html_fukuanfangshi.setVisibility(View.VISIBLE);
                    wbfukuanfangshi.setVisibility(View.GONE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (newhousedetails != null) {
                for (NewHouseDetail i : newhousedetails) {
                    if (id.equals(i.getPosid())) {
                        rb_newhouseguanzhu.setSelected(true);
                        rb_newhouseguanzhu.setText("已关注");
                        //t = "0";
                    } else {
                        rb_newhouseguanzhu.setSelected(false);
                        rb_newhouseguanzhu.setText("关注");
                        //t = "1";
                    }
                }
            }

            tvNewhousedetailLoupandizhi.setText(e.getLoupandizhi());
            tvNewhousedetailMaintype.setText(e.getZhulihuxing());
            tvNewhousedetailUpdatetime.setText(TimeTurnDate.getStringShortDate(e.getUpdatetime()));

            tvHousetype.setText(e.getFangwuyongtu() + "");
            tvjiaofangshijian.setText(e.getJiaofangdate() + "");
            tvkaipanshijian.setText(e.getKaipandate() + "");
            tvNewhousedetailPrice.setText(e.getJunjia() + "元/平米");
            tvTitle.setText(e.getTitle() + "");
            tvTitle1.setText(e.getTitle() + "");

            tvNewhousedetailKaifashang.setText(e.getKaifashang());
            tvNewhousedetailZuixinkaipan.setText(e.getKaipandate());
            tvNewhousedetailChanquannianxian.setText(e.getChanquannianxian() + "年");
            tvNewhousedetailZuixinjiaofang.setText(e.getJiaofangdate());
            tvNewhousedetailDianping.setText(e.getDianping());

            String jwd = e.getJingweidu();
            String[] arr = jwd.split(",");
            String j = arr[0].toString();
            String w = arr[1].toString();

            double latitude = Double.valueOf(j);
            double longitude = Double.valueOf(w);

            LatLng latLng = new LatLng(longitude, latitude);

            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);

            loupan = e.getLoupantupian();
            yangban = e.getYangbantu();
            shijing = e.getShijingtu();
            weizhi = e.getWeizhitu();
            xiaoqu = e.getXiaoqutu();
            pics.clear();
            xiangcePics.clear();

            if (loupan != null && loupan.size() > 0) {
                pics.addAll(loupan);

                Pics ps = new Pics();
                ps.setUrl(loupan.get(0).getUrl());
                ps.setAlt("效果图" + "(" + loupan.size() + ")");
                xiangcePics.add(ps);
            }
            if (weizhi != null && weizhi.size() > 0) {
                pics.addAll(weizhi);

                Pics ps = new Pics();
                ps.setUrl(weizhi.get(0).getUrl());
                ps.setAlt("交通图" + "(" + weizhi.size() + ")");
                xiangcePics.add(ps);
            }
            if (yangban != null && yangban.size() > 0) {
                pics.addAll(yangban);

                Pics ps = new Pics();
                ps.setUrl(yangban.get(0).getUrl());
                ps.setAlt("样板间" + "(" + yangban.size() + ")");
                xiangcePics.add(ps);
            }
            if (shijing != null && shijing.size() > 0) {
                pics.addAll(shijing);

                Pics ps = new Pics();
                ps.setUrl(shijing.get(0).getUrl());
                ps.setAlt("实景图" + "(" + shijing.size() + ")");
                xiangcePics.add(ps);
            }
            if (xiaoqu != null && xiaoqu.size() > 0) {
                pics.addAll(xiaoqu);

                Pics ps = new Pics();
                ps.setUrl(xiaoqu.get(0).getUrl());
                ps.setAlt("小区配套" + "(" + xiaoqu.size() + ")");
                xiangcePics.add(ps);
            }

            mSlider.setILoopAdapter(new ILoopAdapter<Pics>() {
                public View createView(ViewGroup viewGroup, LayoutInflater inflater, Context context) {
                    FrameLayout layout = (FrameLayout) inflater.inflate(R.layout.view_pager_item, viewGroup, false);
                    return layout;
                }

                public void bindItem(View view, int position, Pics p) {
                    TextView ptitleTv = (TextView) view.findViewById(R.id.text_pager_item_title);
                    ImageView pImageViw = (ImageView) view.findViewById(R.id.imageView);
                    ptitleTv.setText(p.getAlt());
                    String urls = p.getUrl();
                    if (!urls.startsWith("http")) {
                        urls = UrlFactory.TSFURL + urls;
                    }
                    Glide.with(NewHouseDetailActivity.this).load(urls).into(pImageViw);
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(NewHouseDetailActivity.this, BigPicActivity.class);
                            List<String> Url = new ArrayList<String>();
                            for (Pics p : pics) {
                                String url = p.getUrl();
                                Url.add(url);
                            }

                            String[] images = Url.toArray(new String[Url.size()]);
                            intent.putExtra("index", 0);
                            intent.putExtra("picsUrls", images);
                            startActivity(intent);
                        }
                    });
                }
            });
            mSlider.updateData(pics);

            xiangce_gridview.setAdapter(new XinagceGridAdapter(this));

            mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
            OverlayOptions option = new MarkerOptions().position(latLng).icon(mCurrentMarker);
            mBaiduMAP.addOverlay(option);
            mBaiduMAP.animateMapStatus(msu);

            ms_newhouse.scrollTo(0, 0);
            ms_newhouse.smoothScrollTo(0, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSurePhone() {
        Surepop = new PopupWindow(NewHouseDetailActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_pop_phone, null);

        SurePhone = (LinearLayout) view.findViewById(R.id.ll_popup_phone);
        Surepop.setWidth(LayoutParams.MATCH_PARENT);
        Surepop.setHeight(LayoutParams.MATCH_PARENT);
        Surepop.setBackgroundDrawable(new BitmapDrawable());
        Surepop.setFocusable(true);
        Surepop.setOutsideTouchable(true);
        Surepop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);


        item_pop_boda = (TextView) view.findViewById(R.id.item_pop_boda);
        TextView tv_phone_sure = (TextView) view.findViewById(R.id.tv_phone_sure);
        TextView tv_phone_cancle = (TextView) view.findViewById(R.id.tv_phone_cancle);


        tv_phone_sure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = item_pop_boda.getText().toString().replace("拨打", "").trim();

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                startActivity(intent);
                Surepop.dismiss();
                SurePhone.clearAnimation();
            }
        });

        tv_phone_cancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Surepop.dismiss();
                SurePhone.clearAnimation();
            }
        });

        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Surepop.dismiss();
                SurePhone.clearAnimation();
            }
        });
    }


    private void setListener() {
        lv_new_tuijian.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NewHouseHotRecommend n = tuijians.get(position);
                Intent i = new Intent(getApplicationContext(), NewHouseDetailActivity.class);
                String Id = n.getId();
                String catid = n.getCatid();
                i.putExtra("Id", Id);
                i.putExtra("catid", catid);
                startActivity(i);
                finish();
            }
        });

        tv_agentzixun.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userinfo != null) {
                    if (e.getUsername().equals("admin")) {
                        item_pop_boda.setText("拨打" + e.getContacttel());
                    } else {
                        item_pop_boda.setText("拨打" + e.getUsername());
                    }

                    Animation anim = AnimationUtils.loadAnimation(NewHouseDetailActivity.this,
                            R.anim.activity_translate_in);

                    SurePhone.setAnimation(anim);
                    Surepop.showAtLocation(v, Gravity.CENTER, 0, 0);

                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });

        tv_newhousezixun.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userinfo != null) {
                    if (e.getUsername().equals("admin")) {
                        item_pop_boda.setText("拨打" + e.getContacttel());
                    } else {
                        item_pop_boda.setText("拨打" + e.getUsername());
                    }

                    Animation anim = AnimationUtils.loadAnimation(NewHouseDetailActivity.this,
                            R.anim.activity_translate_in);

                    SurePhone.setAnimation(anim);
                    Surepop.showAtLocation(v, Gravity.CENTER, 0, 0);

                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });

        rb_newhouseguanzhu.setOnClickListener(new OnClickListener() {
            private String fromid;
            private String fromtable;
            private String type;

            public void onClick(View v) {
                if (userinfo != null) {
                    if (rb_newhouseguanzhu.isSelected()) {
                        MyToastShowCenter.CenterToast(getApplicationContext(), "该房源已关注");
                        return;
                    }

                    fromtable = "new";
                    type = "新房";
                    guanzhupresenter.LoadGuanZhuHouse(id, fromtable, userid, username, type, rb_newhouseguanzhu.isSelected() ? "0" : "1");
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });

        rl_loupanxiangqing.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HouseAllDetailsActivity.class);
                i.putExtra("xiangqing", (Serializable) e);
                i.putExtra("TAG", "2");
                startActivity(i);
            }
        });

        rl_new_loupandongtai.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HouseAllDetailsActivity.class);
                if (dongtais != null) {
                    i.putExtra("dongtai", (Serializable) dongtais);
                    i.putExtra("dongtaiNull", "y");
                } else {
                    i.putExtra("dongtaiNull", "w");
                }
                i.putExtra("TAG", "1");
                startActivity(i);
            }
        });


        llNewHouseDetailBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rlDituNew.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (e.getJingweidu() == null) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "房源的经纬度为空");
                } else {
                    String jwd = e.getJingweidu();
                    String[] arr = jwd.split(",");
                    String j = arr[0].toString();
                    String w = arr[1].toString();

                    double latitude = Double.valueOf(j);
                    double longitude = Double.valueOf(w);

                    Intent i = new Intent(getApplicationContext(), BaiduMapActivity.class);
                    i.putExtra("latitude", latitude);
                    i.putExtra("longitude", longitude);
                    startActivity(i);
                }
            }
        });


        mBaiduMAP.setOnMapClickListener(new OnMapClickListener() {
            public boolean onMapPoiClick(MapPoi arg0) {
                return false;
            }

            public void onMapClick(LatLng arg0) {

                if (e.getJingweidu() == null) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "房源的经纬度为空");
                } else {
                    String jwd = e.getJingweidu();
                    String[] arr = jwd.split(",");
                    String j = arr[0].toString();
                    String w = arr[1].toString();

                    double latitude = Double.valueOf(j);
                    double longitude = Double.valueOf(w);

                    Intent i = new Intent(getApplicationContext(), BaiduMapActivity.class);
                    i.putExtra("latitude", latitude);
                    i.putExtra("longitude", longitude);
                    startActivity(i);
                }
            }
        });


        tv_new_yhq_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                coupon_id = coupon_id1;
                if (userinfo == null) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "你还没有登录，请先登录");
                    startActivity(new Intent(NewHouseDetailActivity.this, LoginActivity.class));
                } else {
                    if (tv_yhqDes != null) {
                        if (yhqs != null && yhqs.size() >= 1) {
                            tv_yhqDes.setText("使用说明：" + yhqs.get(0).getDescription());
                        } else {
                            tv_yhqDes.setText("使用说明：");
                        }
                    }
                    Mpop.showAtLocation(v, Gravity.CENTER, 0, 0);
                }
            }
        });

        tv_new_yhq_2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                coupon_id = coupon_id2;
                if (userinfo == null) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "你还没有登录，请先登录");
                    startActivity(new Intent(NewHouseDetailActivity.this, LoginActivity.class));
                } else {
                    if (tv_yhqDes != null) {
                        if (yhqs != null && yhqs.size() >= 2) {
                            tv_yhqDes.setText("使用说明：" + yhqs.get(1).getDescription());
                        } else {
                            tv_yhqDes.setText("使用说明：");
                        }
                    }
                    Mpop.showAtLocation(v, Gravity.CENTER, 0, 0);
                }
            }
        });

        tv_new_yhq_3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                coupon_id = coupon_id3;
                if (userinfo == null) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "你还没有登录，请先登录");
                    startActivity(new Intent(NewHouseDetailActivity.this, LoginActivity.class));
                } else {
                    if (tv_yhqDes != null) {
                        if (yhqs != null && yhqs.size() >= 3) {
                            tv_yhqDes.setText("使用说明：" + yhqs.get(2).getDescription());
                        } else {
                            tv_yhqDes.setText("使用说明：");
                        }
                    }
                    Mpop.showAtLocation(v, Gravity.CENTER, 0, 0);
                }
            }
        });
        tv_new_yhq_4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                coupon_id = coupon_id4;
                if (userinfo == null) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "你还没有登录，请先登录");
                    startActivity(new Intent(NewHouseDetailActivity.this, LoginActivity.class));
                } else {
                    if (tv_yhqDes != null) {
                        if (yhqs != null && yhqs.size() >= 3) {
                            tv_yhqDes.setText("使用说明：" + yhqs.get(2).getDescription());
                        } else {
                            tv_yhqDes.setText("使用说明：");
                        }
                    }
                    Mpop.showAtLocation(v, Gravity.CENTER, 0, 0);
                }
            }
        });
        tv_new_yhq_5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                coupon_id = coupon_id5;
                if (userinfo == null) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "你还没有登录，请先登录");
                    startActivity(new Intent(NewHouseDetailActivity.this, LoginActivity.class));
                } else {
                    if (tv_yhqDes != null) {
                        if (yhqs != null && yhqs.size() >= 3) {
                            tv_yhqDes.setText("使用说明：" + yhqs.get(2).getDescription());
                        } else {
                            tv_yhqDes.setText("使用说明：");
                        }
                    }
                    Mpop.showAtLocation(v, Gravity.CENTER, 0, 0);
                }
            }
        });
    }

    public void showNewHouseDetailData(NewHouseDetail news) {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = news;
        handler.sendMessage(msg);
    }

    public void YHQgetInfoSuccess(List<YHQinfo> yhqs) {
        this.yhqs = yhqs;
        if (yhqs != null && yhqs.size() > 0) {
            rl_new_yhq.setVisibility(View.VISIBLE);
            tv_yhq_none.setVisibility(View.GONE);

            tv_new_yhq_1.setVisibility(View.GONE);
            tv_new_yhq_2.setVisibility(View.GONE);
            tv_new_yhq_3.setVisibility(View.GONE);
            tv_new_yhq_4.setVisibility(View.GONE);
            tv_new_yhq_5.setVisibility(View.GONE);

            if(yhqs.size()>=1)
            {
                tv_new_yhq_1.setVisibility(View.VISIBLE);

                YHQinfo y = yhqs.get(0);
                this.coupon_id1 = y.getId();
                tv_new_yhq_1.setText(y.getTitle());
            }

            if(yhqs.size()>=2)
            {
                tv_new_yhq_2.setVisibility(View.VISIBLE);

                YHQinfo y2 = yhqs.get(1);
                this.coupon_id2 = y2.getId();
                tv_new_yhq_2.setText(y2.getTitle());
            }
            if(yhqs.size()>=3)
            {
                tv_new_yhq_3.setVisibility(View.VISIBLE);
                YHQinfo y3 = yhqs.get(2);
                this.coupon_id3 = y3.getId();
                tv_new_yhq_3.setText(y3.getTitle());
            }

            if(yhqs.size()>=4)
            {
                tv_new_yhq_4.setVisibility(View.VISIBLE);
                YHQinfo y4 = yhqs.get(3);
                this.coupon_id4 = y4.getId();
                tv_new_yhq_4.setText(y4.getTitle());
            }
            if(yhqs.size()>=5)
            {
                tv_new_yhq_5.setVisibility(View.VISIBLE);
                YHQinfo y5 = yhqs.get(4);
                this.coupon_id5 = y5.getId();
                tv_new_yhq_5.setText(y5.getTitle());
            }
        } else {
            rl_new_yhq.setVisibility(View.GONE);
            tv_yhq_none.setVisibility(View.VISIBLE);
        }
    }

    public void YHQgetInfoFail(String info) {
        rl_new_yhq.setVisibility(View.GONE);
        tv_yhq_none.setVisibility(View.VISIBLE);
    }

    TextView tv_yhqDes = null;

    private void showPopWindowYHQ() {
        Mpop = new PopupWindow(NewHouseDetailActivity.this);
        View view = getLayoutInflater().inflate(R.layout.popwin_new_yhq, null);
        llpopupSpinnerMore = (LinearLayout) view.findViewById(R.id.ll_popup_yhq);
        Mpop.setWidth(LayoutParams.MATCH_PARENT);
        Mpop.setHeight(LayoutParams.MATCH_PARENT);
        Mpop.setBackgroundDrawable(new BitmapDrawable());
        Mpop.setFocusable(true);
        Mpop.setOutsideTouchable(true);
        Mpop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_yhq);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_yhq_title);
        final EditText etname = (EditText) view.findViewById(R.id.et_goufangren);
        final EditText etphone = (EditText) view.findViewById(R.id.et_shoujihao);
        final EditText etyzm = (EditText) view.findViewById(R.id.et_yanzhenma);
        final Button btsend = (Button) view.findViewById(R.id.btn_yhq_sendcode);
        TextView tvLijiGou = (TextView) view.findViewById(R.id.tv_lijiqianggou);
        tv_yhqDes = (TextView) view.findViewById(R.id.tv_yhqDes);

        tvTitle.setText(e.getTitle());
        btsend.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String phone = userinfo.getUsername();
                if (phone.equals("") || phone == null) {
                    //电话为空时，提示电话必须填
                    MyToastShowCenter.CenterToast(getApplicationContext(), "请填写手机号！");
                } else {
                    //发送验证码
                    isChange = true;
                    yhqpresenter.YzmInfo(phone);


                    changeBtnGetCode();
                }
            }

            private void changeBtnGetCode() {
                thread = new Thread() {
                    @Override
                    public void run() {
                        if (tag12) {
                            while (i > 0) {
                                i--;
                                if (NewHouseDetailActivity.this == null) {
                                    break;
                                }

                                NewHouseDetailActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        btsend.setText("重发(" + i + "s)");
                                        btsend.setClickable(false);
                                    }
                                });
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            tag12 = false;
                        }

                        i = 60;
                        tag12 = true;
                        if (NewHouseDetailActivity.this != null) {
                            NewHouseDetailActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    btsend.setText("发送验证码");
                                    btsend.setClickable(true);
                                }
                            });
                        }
                    }

                    ;
                };
                thread.start();
            }
        });

        //立即抢购
        tvLijiGou.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                //设置弹窗的title
                NewHouseDetailActivity.this.view = v;
                final String mob = etyzm.getText().toString().trim();
                final String buyname = etname.getText().toString().trim();
                final String buytel = etphone.getText().toString().trim();
                final String yzm = etyzm.getText().toString().trim();
                userid = userinfo.getUserid();
                username = userinfo.getUsername();
                //Log.e("aaaaaaaaaaaaaaaaaa", "id=" + id + " coupon_id=" + coupon_id + " userid=" + userid + " buyname=" + buyname + " buytel=" + buytel + " username=" + username + " yzm=" + yzm);
                yhqpresenter.AddYHQ(id, coupon_id, userid, buyname, buytel, username, yzm);
                Mpop.dismiss();
                llpopupSpinnerMore.clearAnimation();
            }
        });

        parent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Mpop.dismiss();
                llpopupSpinnerMore.clearAnimation();
            }
        });
    }

    //发送验证码请求返回数据
    public void YzmInfo(String info) {
        MyToastShowCenter.CenterToast(getApplicationContext(), info);
    }

    //发送添加优惠券请求，成功返回数据
    public void AddYhqsuccess(YHQ y) {
        Mpop.dismiss();
        llpopupSpinnerMore.clearAnimation();
        this.yhq = y;
        payV2(view);
    }

    //发送添加优惠券请求，失败返回数据
    public void AddYhqfail(String info) {
        Mpop.dismiss();
        llpopupSpinnerMore.clearAnimation();
        MyToastShowCenter.CenterToast(getApplicationContext(), info);
    }

    public void GuanZhuSuccess(String info) {
        MyToastShowCenter.CenterToast(getApplicationContext(), info);
        rb_newhouseguanzhu.setSelected(true);
        rb_newhouseguanzhu.setText("已关注");
    }

    public void GuanZhuFail(String errorinfo) {
        MyToastShowCenter.CenterToast(getApplicationContext(), errorinfo);
    }

    public void showGuanZhuSuccess(List<NewHouseDetail> newhousedetails) {
        this.newhousedetails = newhousedetails;
    }

    public void showGuanZhuFail(String errorinfo) {
    }

    public void Tuijian(List<NewHouseHotRecommend> tuijians) {
        this.tuijians = tuijians;
        tuijianadapter = new NewHouseHotAdapter(tuijians, getApplicationContext());
        lv_new_tuijian.setAdapter(tuijianadapter);
    }

    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    public void PayInfo(String info) {
    }

    /**
     * 支付宝支付业务
     *
     * @param v
     */
    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */

        total_amount = yhq.getShifu();
        order_no = yhq.getOrder_no();
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, total_amount, yhq.getCoupon_name(), "2.0", yhq.getOrder_no());
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);

        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(NewHouseDetailActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝账户授权业务
     *
     * @param v
     */
    public void authV2(View v) {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)
                || TextUtils.isEmpty(TARGET_ID)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo的获取必须来自服务端；
         */
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, RSA_PRIVATE);
        final String authInfo = info + "&" + sign;
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(NewHouseDetailActivity.this);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    /**
     * get the sdk version. 获取SDK版本号
     */
    public String getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
//		MyToastShowCenter.CenterToast(this, version);
        return version;
    }

    /**
     * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
     *
     * @param v
     */
    public void h5Pay(View v) {
        Intent intent = new Intent(this, H5PayDemoActivity.class);
        Bundle extras = new Bundle();
        /**
         * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
         * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
         * 商户可以根据自己的需求来实现
         */
        String url = "http://m.taobao.com";
        // url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
        extras.putString("url", url);
        intent.putExtras(extras);
        startActivity(intent);
    }


    public class XinagceGridAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public XinagceGridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return xiangcePics == null ? 0 : xiangcePics.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_xiangce,
                        parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView
                        .findViewById(R.id.xc_imageView);
                holder.et_image_name = (TextView) convertView
                        .findViewById(R.id.xc_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (xiangcePics != null && xiangcePics.size() > 0) {
                setImageInfo(xiangcePics.get(position), holder.image, holder.et_image_name);
            }
            return convertView;
        }

        private void setImageInfo(final Pics info, ImageView imageView, TextView imageNameText) {
            imageNameText.setText(info.getAlt());
            if (info.getUrl() != null && info.getUrl().length() > 0) {
                if (!info.getUrl().startsWith("http")) {
                    Glide.with(NewHouseDetailActivity.this).load(UrlFactory.TSFURL + info.getUrl()).into(imageView);
                } else {
                    Glide.with(NewHouseDetailActivity.this).load(info.getUrl()).into(imageView);

                }
            }

            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(NewHouseDetailActivity.this, BigPicActivity.class);
                    List<String> Url = new ArrayList<String>();
                    for (Pics p : pics) {
                        String url = p.getUrl();
                        Url.add(url);
                    }

                    String[] images = Url.toArray(new String[Url.size()]);
                    intent.putExtra("index", 0);
                    intent.putExtra("picsUrls", images);
                    startActivity(intent);
                }
            });
        }

        public class ViewHolder {
            public ImageView image;
            public TextView et_image_name;
        }
    }
}
