package com.dumu.housego;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import autolooppager.AutoLoopLayout;
import autolooppager.ILoopAdapter;
import util.BigPicActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

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
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.adapter.ErShouFangTuijianAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.AgentDetail;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.SeeHouse;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.entity.YuYueData;
import com.dumu.housego.presenter.AgentDetailPresenter;
import com.dumu.housego.presenter.ErShouFangDetailPresenter;
import com.dumu.housego.presenter.ErShouFangRecommendPresenter;
import com.dumu.housego.presenter.ErShouUserInfoPresenter;
import com.dumu.housego.presenter.GuanZhuErShouPresenter;
import com.dumu.housego.presenter.GuanZhuHousePresenter;
import com.dumu.housego.presenter.IAgentDetailPresenter;
import com.dumu.housego.presenter.IErShouFangDetailPresenter;
import com.dumu.housego.presenter.IErShouuserInfoPresenter;
import com.dumu.housego.presenter.IGuanZhuErShouPresenter;
import com.dumu.housego.presenter.IGuanZhuHousePresenter;
import com.dumu.housego.presenter.IMyYuYueHousePresenter;
import com.dumu.housego.presenter.IRecommendHousePresenter;
import com.dumu.housego.presenter.IYuYueHousePresenter;
import com.dumu.housego.presenter.MyYuYueHousePresenter;
import com.dumu.housego.presenter.YuYueHousePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.view.IAgentDetailView;
import com.dumu.housego.view.IErShouFangDetailView;
import com.dumu.housego.view.IErShouFangRecommendView;
import com.dumu.housego.view.IErshouUserInfoView;
import com.dumu.housego.view.IGuanZhuErShouView;
import com.dumu.housego.view.IGuanZhuHouseView;
import com.dumu.housego.view.IMyYuYueHouseView;
import com.dumu.housego.view.IYuYueHouseView;

public class ErShouFangDetailsActivity extends BaseActivity
        implements IErshouUserInfoView, IYuYueHouseView, IAgentDetailView, IMyYuYueHouseView, IErShouFangDetailView, IGuanZhuHouseView, IGuanZhuErShouView, IErShouFangRecommendView {

    private String dateyuyue;
    private String timeyuyue;
    private BaiduMap mBaiduMAP;
    private MapView mMapView;
    private Button rbErshoufangGuanzhu;
    private ErShouFangDetails e;
    private IErShouFangDetailPresenter esfPresenter;
    private IGuanZhuHousePresenter guanzhuPresenter;
    private LinearLayout llBackErshoufangdetails;
    private RelativeLayout rlBaidumap;
    private UserInfo userinfo;
    private MyReboundScrollView ErshoufangScrollview;
    private boolean isFirstIn = true;
    private TextView tvYuyuekanfang;
    public LinearLayout llBackYuyuekanfang;
    private IYuYueHousePresenter yuyuepresenter;
    private Button btnYuyuekanfang;
    private List<Pics> pics = new ArrayList<Pics>();

    @ViewInject(R.id.tv_ershoufangdetails)
    TextView tvtitle;
    @ViewInject(R.id.rl_ditu_ershou)
    RelativeLayout rlDituErshou;

    @ViewInject(R.id.ll_ershou_biaoqian)
    LinearLayout ll_ershou_biaoqian;
    @ViewInject(R.id.tv_ershou_biaoqian)
    TextView tvBianQian1;
    @ViewInject(R.id.tv_ershou_biaoqian2)
    TextView tvBianQian2;
    @ViewInject(R.id.tv_ershou_biaoqian3)
    TextView tvBianQian3;
    @ViewInject(R.id.tv_ershou_biaoqian4)
    TextView tvBianQian4;
    @ViewInject(R.id.tv_ershou_title)
    TextView tvErshoutitle;
    @ViewInject(R.id.ershoufang_shoujia)
    TextView tvShoujia;
    @ViewInject(R.id.ershoufang_huxing)
    TextView tvHuXing;
    @ViewInject(R.id.tv_junjia)
    TextView tvJunjia;
    @ViewInject(R.id.tv_louceng)
    TextView tvLouceng;
    @ViewInject(R.id.tv_zhuangxiu)
    TextView tvZhuangxiu;
    @ViewInject(R.id.Tv_fangling)
    TextView TvFangling;
    @ViewInject(R.id.tv_kanfangshijian)
    TextView tvKanfangshijian;
    @ViewInject(R.id.tv_guapaishijian)
    TextView tvguapaishijian;
    @ViewInject(R.id.tv_chaoxiang)
    TextView tvChaoxiang;
    @ViewInject(R.id.tv_louxing)
    TextView tvLouxing;
    @ViewInject(R.id.tv_quyu)
    TextView tvQuyu;
    @ViewInject(R.id.Tv_xiaoqu)
    TextView TvXiaoqu;
    @ViewInject(R.id.Tv_fangyuanbianhao)
    TextView TvFangyuanbianhao;
    @ViewInject(R.id.ershoufang_mianji)
    TextView tvershoufang_mianji;

    @ViewInject(R.id.tv_hexinmaidian1)
    TextView tvHexinmaidian;
    @ViewInject(R.id.tv_jiaotongchuxing1)
    TextView tvJiaotongchuxing;

    @ViewInject(R.id.goufangjisuanqi)
    TextView tvGoufangJisuanqi;


    @ViewInject(R.id.tv_weekhistroy)
    TextView tvWeekhistroy;
    @ViewInject(R.id.tv_totalhistroy)
    TextView tvTotalhistroy;
    @ViewInject(R.id.lv_haofangtuijian)
    ListView lvHouseTuijian;

    @ViewInject(R.id.tv_morenews)
    TextView tvMoreDetails;

    @ViewInject(R.id.tv_morehousetese)
    TextView tvMoreTese;

    @ViewInject(R.id.tv_zixun_pt)
    TextView tvPtZixun;
    @ViewInject(R.id.tv_agent_zixun)
    TextView tvATZixun;

    @ViewInject(R.id.ll_ershoufangdetail_agentbottom)
    LinearLayout llAgentBottom;
    @ViewInject(R.id.ll_ershoufangdetail_bottom)
    LinearLayout llPutongBottom;

    @ViewInject(R.id.yishouImageView)
    ImageView yishouImageView;

    private Spinner yuyuedateSpinner;
    private Spinner yuyuetimeSpinner;

    private List<String> spinnerList1 = new ArrayList<String>();
    private List<String> spinnerList2 = new ArrayList<String>();
    private ArrayAdapter<String> Spinneradapter1;
    private ArrayAdapter<String> Spinneradapter2;

    private Handler mhandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    e = (ErShouFangDetails) msg.obj;
                    Show();
                    break;

                default:
                    break;
            }
        }
    };

    private RelativeLayout ershoufang_feiyuyue;
    private RelativeLayout rlErshoufangYuyuewindows;
    private BitmapDescriptor mCurrentMarker;
    private PopupWindow pop;
    private LinearLayout ll_popup;
    private LinearLayout ll_cancle;
    private View parentView;
    private List<ErShouFangRecommendData> ershoufangrecommends;
    private IRecommendHousePresenter recommendPresenter;
    private ErShouFangTuijianAdapter tuijianadapter;
    //轮播图
    private AutoLoopLayout<Pics> mSlider;
    private List<ErShouFangDetails> ershoufangdetails = new ArrayList<ErShouFangDetails>();
    private List<YuYueData> yuyuedatas = new ArrayList<YuYueData>();
    private String catid;
    private String id;
    //private String t = "1";
    private IGuanZhuErShouPresenter ershouguanzhuPresenter;
    private IMyYuYueHousePresenter yuyuePresenter;
    private IAgentDetailPresenter agentPresenter;

    private AgentDetail agentdetail;
    private RelativeLayout rlagentPhone;
    private PopupWindow Agentpop;
    private UserInfo u;
    private IErShouuserInfoPresenter infoPresenter;

    private ImageView iv_agent_pic;
    ImageView iv_agent_phone;
    TextView tv_agent_name;
    TextView tv_agent_pinglun;
    TextView tv_agent_phone;
    private PopupWindow Surepop;
    private LinearLayout SurePhone;
    private TextView item_pop_boda;
    private ImageView iv_agent_message;

    private ListView lv_chenjiao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentView = getLayoutInflater().inflate(R.layout.activity_er_shou_fang_details, null);

        setContentView(parentView);
        x.view().inject(this);
        FontHelper.injectFont(findViewById(android.R.id.content));
        initViews();
        showPopWindow();
        showAgentPhone();
        showSurePhone();
        initListener();

        long times = System.currentTimeMillis() / 1000;
        long oneday = 24L * 60 * 60 * 1000 / 1000;
        String today = TimeTurnDate.getStringDate(times);
        String tomorrow = TimeTurnDate.getStringDate(times + oneday);
        String afterTomrrow = TimeTurnDate.getStringDate(times + 2 * oneday);
        spinnerList1.add(today);
        spinnerList1.add(tomorrow);
        spinnerList1.add(afterTomrrow);

        spinnerList2.add("09:00-12:00");
        spinnerList2.add("13:00-15:00");

        Spinneradapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList1);
        Spinneradapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList2);
        Spinneradapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinneradapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yuyuedateSpinner.setAdapter(Spinneradapter1);
        yuyuetimeSpinner.setAdapter(Spinneradapter2);

        esfPresenter = new ErShouFangDetailPresenter(this);
        guanzhuPresenter = new GuanZhuHousePresenter(this);
        yuyuepresenter = new YuYueHousePresenter(this);
        recommendPresenter = new ErShouFangRecommendPresenter(this);
        ershouguanzhuPresenter = new GuanZhuErShouPresenter(this);
        yuyuePresenter = new MyYuYueHousePresenter(this);
        agentPresenter = new AgentDetailPresenter(this);
        infoPresenter = new ErShouUserInfoPresenter(this);

        catid = getIntent().getStringExtra("catid");
        id = getIntent().getStringExtra("id");
        esfPresenter.FindErShouFangdetail(catid, id);
        recommendPresenter.LoadRecommend();
    }

    protected void onResume() {
        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
        if (userinfo != null) {
            if (userinfo.getModelid().equals("35")) {
                llPutongBottom.setVisibility(View.VISIBLE);
                llAgentBottom.setVisibility(View.GONE);

                ershouguanzhuPresenter.LoadGuanZhuErShou(userinfo.getUsername(), "ershou");
                yuyuePresenter.LoadMyYuYueHosue(userinfo.getUsername());
            } else {
                llAgentBottom.setVisibility(View.VISIBLE);
                llPutongBottom.setVisibility(View.GONE);
            }

        } else {
            llPutongBottom.setVisibility(View.VISIBLE);
            llAgentBottom.setVisibility(View.GONE);
        }
        super.onResume();
    }

    private void showPopWindow() {
        pop = new PopupWindow(ErShouFangDetailsActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows_1, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
        ll_cancle = (LinearLayout) view.findViewById(R.id.ll_cancle);
        pop.setWidth(LayoutParams.MATCH_PARENT);
        pop.setHeight(LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        LinearLayout llshow = (LinearLayout) view.findViewById(R.id.ll_showpop_1);
        Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_btn1);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_btn2);
        Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        bt1.setClickable(false);
        bt1.setEnabled(false);
        bt1.setVisibility(View.GONE);
        bt1.setText("预约看房管理");
        bt2.setText("立即预约");
        llshow.setVisibility(View.GONE);

        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
                ll_cancle.clearAnimation();
            }
        });
        bt1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

            }
        });
        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ershoufang_feiyuyue.setVisibility(View.GONE);
                rlErshoufangYuyuewindows.setVisibility(View.VISIBLE);
                pop.dismiss();
                ll_popup.clearAnimation();
                ll_cancle.clearAnimation();
            }
        });
        bt3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
                ll_cancle.clearAnimation();
            }
        });
    }

    private void showAgentPhone() {
        Agentpop = new PopupWindow(ErShouFangDetailsActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_pop_agent, null);

        rlagentPhone = (RelativeLayout) view.findViewById(R.id.rl_agent_win);
        Agentpop.setWidth(LayoutParams.MATCH_PARENT);
        Agentpop.setHeight(LayoutParams.MATCH_PARENT);
        Agentpop.setBackgroundDrawable(new BitmapDrawable());
        Agentpop.setFocusable(true);
        Agentpop.setOutsideTouchable(true);
        Agentpop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);

        iv_agent_pic = (ImageView) view.findViewById(R.id.iv_agent_pic);
        iv_agent_phone = (ImageView) view.findViewById(R.id.iv_agent_phone);
        tv_agent_name = (TextView) view.findViewById(R.id.tv_agent_name);
        tv_agent_pinglun = (TextView) view.findViewById(R.id.tv_agent_pinglun);
        tv_agent_phone = (TextView) view.findViewById(R.id.tv_agent_phone);
        iv_agent_message = (ImageView) view.findViewById(R.id.iv_agent_message);

        parent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Agentpop.dismiss();
                rlagentPhone.clearAnimation();
            }
        });

        iv_agent_pic.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AgentDetailActivity.class);
                i.putExtra("userid", agentdetail.getUserid());
                i.putExtra("username", agentdetail.getUsername());
                startActivity(i);
                Agentpop.dismiss();
                rlagentPhone.clearAnimation();
            }
        });

        iv_agent_message.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                UserInfo userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                if (userinfo == null) {
                    startActivity(new Intent(getApplicationContext(),
                            LoginActivity.class));
                } else {
                    Intent i = new Intent(getApplicationContext(), LiuYanDetailActivity.class);
                    i.putExtra("towhos", agentdetail.getUserid());
                    i.putExtra("realnames", agentdetail.getRealname());
                    startActivity(i);
                }
            }
        });


        iv_agent_phone.setOnClickListener(new OnClickListener() {
            private String phoneno;

            public void onClick(View v) {
//				phoneno=agentdetail.getCtel();
//				Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneno));
//				startActivity(intent);

                item_pop_boda.setText("拨打" + agentdetail.getCtel());

                Animation anim = AnimationUtils.loadAnimation(ErShouFangDetailsActivity.this,
                        R.anim.activity_translate_in);

                SurePhone.setAnimation(anim);
                Surepop.showAtLocation(parentView, Gravity.CENTER, 0, 0);
                Agentpop.dismiss();
                rlagentPhone.clearAnimation();
            }
        });
    }

    private void showSurePhone() {
        Surepop = new PopupWindow(ErShouFangDetailsActivity.this);

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

    private void initListener() {
        lvHouseTuijian.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ErShouFangDetailsActivity.this, ErShouFangDetailsActivity.class);
                String catid = ershoufangrecommends.get(position).getCatid();
                String ID = ershoufangrecommends.get(position).getId();
                i.putExtra("catid", catid);
                i.putExtra("id", ID);
                startActivity(i);
                finish();
            }
        });

        tvGoufangJisuanqi.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AgentPersonalActivity.class);
                i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.GOUFANGJISUANQI);
                startActivity(i);
            }
        });

        tvATZixun.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (agentdetail != null && !agentdetail.equals("")) {
                    String url = agentdetail.getUserpic();
                    Glide.with(ErShouFangDetailsActivity.this).load(url).into(iv_agent_pic);

                    tv_agent_name.setText(agentdetail.getRealname());
                    tv_agent_phone.setText(agentdetail.getVtel());
                    Animation anim = AnimationUtils.loadAnimation(ErShouFangDetailsActivity.this,
                            R.anim.activity_translate_in);

                    rlagentPhone.setAnimation(anim);
                    Agentpop.showAtLocation(parentView, Gravity.CENTER, 0, 0);
                } else {
                    if (e.getHidetel().equals("") || e.getHidetel().equals("公开")) {
                        item_pop_boda.setText("拨打" + e.getUsername());
                    } else {
                        item_pop_boda.setText("拨打" + u.getCtel());
                    }

                    Animation anim = AnimationUtils.loadAnimation(ErShouFangDetailsActivity.this,
                            R.anim.activity_translate_in);
                    SurePhone.setAnimation(anim);
                    Surepop.showAtLocation(parentView, Gravity.CENTER, 0, 0);
                }
            }
        });

        tvPtZixun.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (agentdetail != null && !agentdetail.equals("")) {

                    String url = agentdetail.getUserpic();
                    Glide.with(ErShouFangDetailsActivity.this).load(url).into(iv_agent_pic);

                    tv_agent_name.setText(agentdetail.getRealname());
                    tv_agent_phone.setText(agentdetail.getVtel());


                    Animation anim = AnimationUtils.loadAnimation(ErShouFangDetailsActivity.this,
                            R.anim.activity_translate_in);

                    rlagentPhone.setAnimation(anim);
                    Agentpop.showAtLocation(parentView, Gravity.CENTER, 0, 0);
                } else {

                    if (e.getHidetel().equals("") || e.getHidetel().equals("公开")) {
                        item_pop_boda.setText("拨打" + e.getUsername());
                    } else {
                        if (u.getZhuanjie().equals("1")) {
                            item_pop_boda.setText("拨打" + u.getCtel());
                        } else {
                            item_pop_boda.setText("拨打" + e.getUsername());
                        }
                    }

                    Animation anim = AnimationUtils.loadAnimation(ErShouFangDetailsActivity.this,
                            R.anim.activity_translate_in);
                    SurePhone.setAnimation(anim);
                    Surepop.showAtLocation(parentView, Gravity.CENTER, 0, 0);
                }
            }
        });

        tvMoreDetails.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ErShouFangDetailsActivity.this, ErShouAllDetailActivity.class);
                i.putExtra("er", e);
                i.putExtra("erTag", "suxing");
                startActivity(i);
            }
        });

        tvMoreTese.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ErShouFangDetailsActivity.this, ErShouAllDetailActivity.class);
                i.putExtra("er", e);
                i.putExtra("erTag", "tese");
                startActivity(i);
            }
        });

        rbErshoufangGuanzhu.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (userinfo == null) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "还没有登录，请先登录！");
                } else {
                    if (e.getUserid() != null && userinfo.getUserid() != null) {
                        if (e.getUserid().equals(userinfo.getUserid())) {
                            MyToastShowCenter.CenterToast(getApplicationContext(), "亲，不能关注自己发布的房源");
                            return;
                        }
                    }

                    if (rbErshoufangGuanzhu.isSelected()) {
                        MyToastShowCenter.CenterToast(getApplicationContext(), "该房源已关注");
                        return;
                    }

                    String fromid = e.getId() + "";
                    String fromtable = "ershou";
                    String userid = userinfo.getUserid();
                    String username = userinfo.getUsername();
                    String type = "二手房";

                    guanzhuPresenter.LoadGuanZhuHouse(fromid, fromtable, userid, username, type, rbErshoufangGuanzhu.isSelected() ? "0" : "1");
                }
            }
        });

        llBackErshoufangdetails.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });

        rlDituErshou.setOnClickListener(new OnClickListener() {
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

        tvYuyuekanfang.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (userinfo == null) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "您还没有登录，请先登录");
                    mhandler.postDelayed(new Runnable() {
                        public void run() {
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    }, 1000);
                } else {
                    if (e.getUserid() != null && userinfo.getUserid() != null) {
                        if (e.getUserid().equals(userinfo.getUserid())) {
                            MyToastShowCenter.CenterToast(getApplicationContext(), "亲，不能预约自己发布的房源");
                            return;
                        }
                    }

                    if (tvYuyuekanfang.getText().equals("已预约")) {
                        MyToastShowCenter.CenterToast(getApplicationContext(), "已预约");
                    } else {
                        Animation anim = AnimationUtils.loadAnimation(ErShouFangDetailsActivity.this,
                                R.anim.activity_translate_in);

                        ll_popup.setAnimation(anim);
                        ll_cancle.setAnimation(anim);
                        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
                    }
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

        llBackYuyuekanfang.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ershoufang_feiyuyue.setVisibility(View.VISIBLE);
                rlErshoufangYuyuewindows.setVisibility(View.GONE);
            }
        });

        yuyuedateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dateyuyue = spinnerList1.get(position);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        yuyuetimeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeyuyue = spinnerList2.get(position);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnYuyuekanfang.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (e.getUserid() != null && userinfo.getUserid() != null) {
                    if (e.getUserid().equals(userinfo.getUserid())) {
                        MyToastShowCenter.CenterToast(getApplicationContext(), "亲，不能预约自己发布的房源");
                        return;
                    }
                }

                String formid = e.getId();
                String fromtable = "ershou";
                String username = userinfo.getUsername();
                String fromuserid;

                if (e.getJjr_id().equals("")) {
                    fromuserid = e.getUserid();
                } else {
                    fromuserid = e.getJjr_id();
                }

                String type = "二手房";
                String yuyuedate = dateyuyue;
                String yuyuetime = timeyuyue;
                String t = "1";

                yuyuepresenter.loadyuyue(formid, fromtable, username, fromuserid, type, yuyuedate, yuyuetime, t);
                mhandler.postDelayed(new Runnable() {
                    public void run() {
                        ershoufang_feiyuyue.setVisibility(View.VISIBLE);
                        rlErshoufangYuyuewindows.setVisibility(View.GONE);
                    }
                }, 1000);
            }
        });

        findViewById(R.id.tv_moredaikandate).setOnClickListener(new OnClickListener() {//查看更多带看记录
            public void onClick(View view) {
                Intent i = new Intent(ErShouFangDetailsActivity.this, ErShouDaikanActivity.class);
                i.putExtra("er", e);
                startActivity(i);
            }
        });

        findViewById(R.id.tv_moreChenjiaojilu).setOnClickListener(new OnClickListener() {//查看更多成交记录
            public void onClick(View view) {
                Intent i = new Intent(ErShouFangDetailsActivity.this, ErShouChengjiaoActivity.class);
                i.putExtra("er", e);
                startActivity(i);
            }
        });
    }

    protected void NewAlertDialog() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.dialog_main_info);
        TextView tv_title = (TextView) window.findViewById(R.id.tv_dialog_title);
        tv_title.setText("是否去预约");
        TextView tv_message = (TextView) window.findViewById(R.id.tv_dialog_message);
        Button btnCancle = (Button) window.findViewById(R.id.btn_dialog_cancle);
        Button btnSure = (Button) window.findViewById(R.id.btn_dialog_sure);
        btnCancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btnSure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                tvYuyuekanfang.setText("已预约");
                ershoufang_feiyuyue.setVisibility(View.GONE);
                rlErshoufangYuyuewindows.setVisibility(View.VISIBLE);
                alertDialog.cancel();
            }
        });
    }

    private void initViews() {
        mSlider = (AutoLoopLayout<Pics>) findViewById(R.id.slider);

        rbErshoufangGuanzhu = (Button) findViewById(R.id.rb_ershoufangguanzhu);
        llBackErshoufangdetails = (LinearLayout) findViewById(R.id.ll_back_ershoufangdetails);
        rlBaidumap = (RelativeLayout) findViewById(R.id.rl_baidumap);
        ErshoufangScrollview = (MyReboundScrollView) findViewById(R.id.ershoufang_scrollview);
        tvYuyuekanfang = (TextView) findViewById(R.id.tv_yuyuekanfang);

        yuyuedateSpinner = (Spinner) findViewById(R.id.spinner_yuyuedate);
        yuyuetimeSpinner = (Spinner) findViewById(R.id.spinner_yuyuetime);

        ershoufang_feiyuyue = (RelativeLayout) findViewById(R.id.ershoufang_feiyuyue);
        rlErshoufangYuyuewindows = (RelativeLayout) findViewById(R.id.rl_ershoufang_yuyuewindows);

        llBackYuyuekanfang = (LinearLayout) findViewById(R.id.ll_back_yuyuekanfnag);
        btnYuyuekanfang = (Button) findViewById(R.id.btn_yuyuekanfang);


        mMapView = (MapView) findViewById(R.id.ershou_bmapView);
        mBaiduMAP = mMapView.getMap();
        mMapView.showZoomControls(false);
        mMapView.showScaleControl(false);
        mMapView.setClickable(false);
        mBaiduMAP.getUiSettings().setAllGesturesEnabled(false);

        lv_chenjiao = (ListView) findViewById(R.id.lv_chenjiao);
    }

    public void showErshoufangData(ErShouFangDetails ershoufangdetail) {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = ershoufangdetail;
        mhandler.sendMessage(msg);
    }

    private void Show() {
        if (e == null) return;
        if (e.getPics() != null) {
            this.pics = e.getPics();
        } else {
            this.pics = e.getPics();
        }

        if (e.getZaishou() != null && e.getZaishou().equals("0"))
            yishouImageView.setVisibility(View.VISIBLE);
        else
            yishouImageView.setVisibility(View.GONE);

        String url = UrlFactory.TSFURL + e.getThumb();
        tvtitle.setText(e.getTitle());
        tvShoujia.setText(e.getZongjia() + "万");
        tvHuXing.setText(e.getShi() + "室" + e.getTing() + "厅");
        tvershoufang_mianji.setText(e.getJianzhumianji() + "平米");
        tvChaoxiang.setText(e.getChaoxiang() + "");
        tvguapaishijian.setText(e.getGuapaidate() + "");

        tvErshoutitle.setText(e.getTitle());

        double zongjia = Integer.valueOf(e.getZongjia().trim()).doubleValue();
        double mianji = Integer.valueOf(e.getJianzhumianji()).doubleValue();
        double junjia = zongjia / mianji;
        int x = (int) (junjia + 0.5);
        tvJunjia.setText(x + "万元/平");

        tvLouceng.setText(e.getCeng() + "/" + e.getZongceng());
        tvLouxing.setText(e.getJianzhutype() + "");
        tvQuyu.setText(e.getCityname() + " " + e.getAreaname());
        tvZhuangxiu.setText(e.getZhuangxiu() + "");
        TvFangling.setText(e.getFangling() + "年");
        TvFangyuanbianhao.setText(e.getBianhao() + "");
        TvXiaoqu.setText(e.getXiaoquname() + "");
        //
        tvHexinmaidian.setText(e.getXiaoquintro());
        tvJiaotongchuxing.setText(e.getHuxingintro());
        //
        List<SeeHouse> list = e.getDaikan();
        List<SeeHouse> wlist = SeeHouse.filterWeekSee(list);
        tvWeekhistroy.setText(wlist != null ? wlist.size() + "" : "0");
        tvTotalhistroy.setText(list != null ? list.size() + "" : "0");

        if (!e.getJjr_id().equals("") && e.getJjr_id() != null) {
            agentPresenter.AgentDetail(e.getJjr_id());
        } else {
            infoPresenter.login(e.getUserid());
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
                Glide.with(ErShouFangDetailsActivity.this).load(urls).into(pImageViw);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(ErShouFangDetailsActivity.this, BigPicActivity.class);
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

        if (e == null || e.tongqu == null || e.tongqu.size() <= 0) {
            findViewById(R.id.chengjiaoContentView).setVisibility(View.GONE);
            findViewById(R.id.chengjiaoContentView_NoData).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.chengjiaoContentView).setVisibility(View.VISIBLE);
            findViewById(R.id.chengjiaoContentView_NoData).setVisibility(View.GONE);
            lv_chenjiao.setAdapter(new er_detail_chenjiaoAdapter());
            lv_chenjiao.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                    if (e != null && e.tongqu != null && e.tongqu.size() > 0) {
                        Intent i = new Intent(ErShouFangDetailsActivity.this, ErShouFangDetailsActivity.class);
                        String catid = e.tongqu.get(0).getCatid();
                        String ID = e.tongqu.get(0).getId();
                        i.putExtra("catid", catid);
                        i.putExtra("id", ID);
                        startActivity(i);
                    }
                }
            });
        }

        if (!e.getBiaoqian().equals("")) {
            String[] b = e.getBiaoqian().split(",");
            int l = b.length;
            switch (l) {
                case 1:
                    tvBianQian1.setText(b[0]);
                    tvBianQian1.setVisibility(View.VISIBLE);
                    tvBianQian2.setVisibility(View.GONE);
                    tvBianQian3.setVisibility(View.GONE);
                    tvBianQian4.setVisibility(View.GONE);
                    break;
                case 2:
                    tvBianQian1.setText(b[0]);
                    tvBianQian2.setText(b[1]);
                    tvBianQian1.setVisibility(View.VISIBLE);
                    tvBianQian2.setVisibility(View.VISIBLE);
                    tvBianQian3.setVisibility(View.GONE);
                    tvBianQian4.setVisibility(View.GONE);
                    break;
                case 3:
                    tvBianQian1.setText(b[0]);
                    tvBianQian2.setText(b[1]);
                    tvBianQian3.setText(b[2]);
                    tvBianQian1.setVisibility(View.VISIBLE);
                    tvBianQian2.setVisibility(View.VISIBLE);
                    tvBianQian3.setVisibility(View.VISIBLE);
                    tvBianQian4.setVisibility(View.GONE);
                    break;
                case 4:
                    tvBianQian1.setText(b[0]);
                    tvBianQian2.setText(b[1]);
                    tvBianQian3.setText(b[2]);
                    tvBianQian4.setText(b[3]);
                    tvBianQian1.setVisibility(View.VISIBLE);
                    tvBianQian3.setVisibility(View.VISIBLE);
                    tvBianQian2.setVisibility(View.VISIBLE);
                    tvBianQian4.setVisibility(View.VISIBLE);
                    break;
            }
        } else {
            tvBianQian1.setVisibility(View.GONE);
            tvBianQian3.setVisibility(View.GONE);
            tvBianQian2.setVisibility(View.GONE);
            tvBianQian4.setVisibility(View.GONE);
            ll_ershou_biaoqian.setVisibility(View.GONE);
        }

        if (ershoufangdetails != null) {
            for (ErShouFangDetails i : ershoufangdetails) {
                if (id.equals(i.getPosid())) {
                    rbErshoufangGuanzhu.setSelected(true);
                    rbErshoufangGuanzhu.setText("已关注");
                    //t = "0";
                } else {
                    rbErshoufangGuanzhu.setSelected(false);
                    rbErshoufangGuanzhu.setText("关注");
                    //t = "1";
                }
            }
        }

        if (yuyuedatas != null) {
            for (YuYueData i : yuyuedatas) {
                if (id.equals(i.getId())) {
                    tvYuyuekanfang.setText("已预约");
                } else {
                    tvYuyuekanfang.setText("立即预约");
                }
            }
        }

        try {
            String jwd = e.getJingweidu();
            String[] arr = jwd.split(",");
            String j = arr[0].toString();
            String w = arr[1].toString();

            double latitude = Double.valueOf(j);
            double longitude = Double.valueOf(w);

            LatLng latLng = new LatLng(longitude, latitude);

            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
            mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
            OverlayOptions option = new MarkerOptions().position(latLng).icon(mCurrentMarker);
            mBaiduMAP.addOverlay(option);
            mBaiduMAP.animateMapStatus(msu);

            ErshoufangScrollview.scrollTo(0, 0);
            ErshoufangScrollview.smoothScrollTo(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GuanZhuSuccess(String info) {
        MyToastShowCenter.CenterToast(getApplicationContext(), info);
        rbErshoufangGuanzhu.setSelected(true);
        rbErshoufangGuanzhu.setText("已关注");
    }

    public void GuanZhuFail(String errorinfo) {
        MyToastShowCenter.CenterToast(getApplicationContext(), errorinfo);
    }

    public void yuYueSuccess(String info) {
        MyToastShowCenter.CenterToast(getApplicationContext(), info);
    }

    public void yuYueFail(String error) {
        MyToastShowCenter.CenterToast(getApplicationContext(), error);
    }

    public void showData(List<ErShouFangRecommendData> ershoufangrecommends) {
        this.ershoufangrecommends = ershoufangrecommends;
        tuijianadapter = new ErShouFangTuijianAdapter(ershoufangrecommends, getApplicationContext());
        lvHouseTuijian.setAdapter(tuijianadapter);
    }

    public void showDatafail(String info) {
        MyToastShowCenter.CenterToast(getApplicationContext(), info);
    }

    public void showGuanZhuSuccess(List<ErShouFangDetails> ershoufangdetails) {
        this.ershoufangdetails = ershoufangdetails;
    }

    public void showGuanZhuFail(String errorinfo) {
//		MyToastShowCenter.CenterToast(getApplicationContext(), errorinfo);
    }

    public void LoadYuYueDatasuccess(List<YuYueData> yuyuedatas) {
        this.yuyuedatas = yuyuedatas;
    }

    public void LoadYuYueDataFaid(String info) {
    }

    public void AgentDetail(AgentDetail agentdetail) {
        this.agentdetail = agentdetail;
    }

    public void loginUserInfoFail(String errorMessage) {
        MyToastShowCenter.CenterToast(getApplicationContext(), errorMessage);
    }

    public void loginUserInfoSuccess(UserInfo u) {
        this.u = u;
    }


    public class er_detail_chenjiaoAdapter extends BaseAdapter {
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
                convertView = LayoutInflater.from(ErShouFangDetailsActivity.this).inflate(R.layout.item_chengjiao_house, null);
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
            Glide.with(ErShouFangDetailsActivity.this).load(UrlFactory.TSFURL + url).into(holder.image);
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
