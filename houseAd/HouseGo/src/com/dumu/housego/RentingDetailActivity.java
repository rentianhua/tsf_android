package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import autolooppager.AutoLoopLayout;
import autolooppager.ILoopAdapter;
import util.BigPicActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.adapter.RentingTuijianAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.AgentDetail;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.entity.RentingTuijian;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.IRentingTuiJianPresenter;
import com.dumu.housego.presenter.AgentDetailPresenter;
import com.dumu.housego.presenter.ErShouUserInfoPresenter;
import com.dumu.housego.presenter.IAgentDetailPresenter;
import com.dumu.housego.presenter.IErShouuserInfoPresenter;
import com.dumu.housego.presenter.IRentingDetailPresenter;
import com.dumu.housego.presenter.RentingDetailPresenter;
import com.dumu.housego.presenter.RentingTuiJianPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.TimeTurnDate;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.view.IAgentDetailView;
import com.dumu.housego.view.IErshouUserInfoView;
import com.dumu.housego.view.IRentingDetailView;
import com.dumu.housego.view.IRentingTuijianView;

public class RentingDetailActivity extends BaseActivity implements IAgentDetailView, IErshouUserInfoView, IRentingTuijianView, IRentingDetailView {
    private LinearLayout llBackRentingdetails;
    private IRentingDetailPresenter presenter;
    private RentingDetail b;

    private BaiduMap mBaiduMAP;
    private MapView mMapView;
    private UserInfo u;
    private IErShouuserInfoPresenter infoPresenter;

    @ViewInject(R.id.iv_renting_pic)
    ImageView ivRentingpic;
    @ViewInject(R.id.ershoufang_shoujia)
    TextView tvRentingshoujia;
    @ViewInject(R.id.ershoufang_huxing)
    TextView tvRentinghuxing;
    @ViewInject(R.id.ershoufang_mianji)
    TextView tvRentingmianji;
    @ViewInject(R.id.tv_renting_phone)
    TextView tv_renting_phone;
    @ViewInject(R.id.rl_renting_map)
    RelativeLayout rl_renting_map;
    @ViewInject(R.id.sc_renting)
    MyReboundScrollView sc_renting;

    @ViewInject(R.id.renting_tedian)
    TextView tvRentingTedian;
    @ViewInject(R.id.tv_rentingdetails)
    TextView tv_rentingdetails;
    @ViewInject(R.id.renting_louceng)
    TextView tvRentingLouceng;
    @ViewInject(R.id.renting_chaoxiang)
    TextView tvRentingChaoxiang;
    @ViewInject(R.id.renting_zhuangxiu)
    TextView tvRentingZhuangxiu;
    @ViewInject(R.id.renting_location)
    TextView tvRentingLocation;
    @ViewInject(R.id.renting_mianji)
    TextView tvRentingMianji;
    @ViewInject(R.id.renting_huxing)
    TextView tvRentingHuxing;
    @ViewInject(R.id.renting_fangshi)
    TextView tvRentingFangshi;
    @ViewInject(R.id.renting_fabushijian)
    TextView tvRentingFabushijian;
    @ViewInject(R.id.renting_ditie)
    TextView tvRentingDitie;
    @ViewInject(R.id.renting_xiaoqu)
    TextView tvRentingXiaoqu;
    @ViewInject(R.id.tv_fangyuanliangdian)
    TextView tvFangyuanliangdian;
    @ViewInject(R.id.tv_jiaotongchuxing)
    TextView tvJiaotongchuxing;
    @ViewInject(R.id.tv_renting_lishijilu)
    TextView tvRentingLishijilu;
    @ViewInject(R.id.tv_xiaoqubianhao)
    TextView tv_xiaoqubianhao;
    @ViewInject(R.id.lv_rentinglistview)
    ListView lv_rentinglistview;
    private IAgentDetailPresenter agentPresenter;

    @ViewInject(R.id.yishouImageView)
    ImageView yishouImageView;

    @ViewInject(R.id.tv_morehousetese)
    TextView tvMoreTese;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    b = (RentingDetail) msg.obj;
                    Show();
                    break;
                default:
                    break;
            }
        }
    };
    private BitmapDescriptor mCurrentMarker;

    //轮播图
    private AutoLoopLayout<Pics> mSlider;
    private List<Pics> pics = new ArrayList<Pics>();
    private IRentingTuiJianPresenter tuijianPresenter;

    private RentingTuijianAdapter tuijianadapter;

    List<RentingTuijian> tuijians = new ArrayList<RentingTuijian>();
    private PopupWindow Agentpop;
    private RelativeLayout rlagentPhone;
    private ImageView iv_agent_pic;
    private ImageView iv_agent_phone;
    private TextView tv_agent_name;
    private TextView tv_agent_pinglun;
    private TextView tv_agent_phone;
    private AgentDetail agentdetail;
    private PopupWindow Surepop;
    private LinearLayout SurePhone;
    private TextView item_pop_boda;
    private View parentView;
    private ImageView iv_agent_message;
    private ListView lv_chenjiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentView = getLayoutInflater().inflate(R.layout.activity_renting_detail, null);
        setContentView(parentView);
        x.view().inject(this);
        FontHelper.injectFont(findViewById(android.R.id.content));
        setViews();
        showAgentPhone();
        showSurePhone();

        setListener();
        presenter = new RentingDetailPresenter(this);
        tuijianPresenter = new RentingTuiJianPresenter(this);
        infoPresenter = new ErShouUserInfoPresenter(this);
        agentPresenter = new AgentDetailPresenter(this);
        String catid = getIntent().getStringExtra("catid");
        String id = getIntent().getStringExtra("id");
        presenter.LoadRenting(catid, id);
        tuijianPresenter.tuijian();

    }

    protected void Show() {
        if (b == null) return;

        try {
            if (b.getZaizu() != null && b.getZaizu().equals("0"))
                yishouImageView.setVisibility(View.VISIBLE);
            else
                yishouImageView.setVisibility(View.GONE);

            if (b.getTitle() != null) {
                tv_rentingdetails.setText(b.getTitle());

            } else {
                tv_rentingdetails.setText("");
            }
            pics = b.getPics();

            tv_xiaoqubianhao.setText(b.getBianhao());

            tvRentinghuxing.setText(" " + b.getShi() + "室 " + b.getTing() + "厅");
            tvRentingmianji.setText(" " + b.getMianji() + "平米");
            tvRentingshoujia.setText(" " + b.getZujin() + "元/月");

            tvRentingTedian.setText(" " + b.getBiaoqian());
            tvRentingLouceng.setText(" " + b.getCeng() + "/" + b.getZongceng());
            tvRentingChaoxiang.setText(" " + b.getChaoxiang());
            tvRentingZhuangxiu.setText(" " + b.getZhuangxiu());
            tvRentingLocation.setText(" " + b.getCityname() + " " + b.getAreaname());
            tvRentingMianji.setText(" " + b.getMianji() + "㎡");
            tvRentingHuxing.setText(" " + b.getHuxingjieshao());
            tvRentingFangshi.setText(" " + b.getZulin());
            tvRentingFabushijian.setText(" " + TimeTurnDate.getStringShortDate(b.getInputtime()));
            tvRentingDitie.setText(" " + b.getDitiexian());
            tvRentingXiaoqu.setText(" " + b.getXiaoquname());
            tvFangyuanliangdian.setText(" " + b.getShenghuopeitao());
            tvJiaotongchuxing.setText(" " + b.getJiaotong());
            tvRentingLishijilu.setText("近一个月新增记录" + b.getMonthviews() + "位");

            if (!b.getJjr_id().equals("") && b.getJjr_id() != null) {
                agentPresenter.AgentDetail(b.getJjr_id());
            } else {

                infoPresenter.login(b.getUserid());
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
                    Glide.with(RentingDetailActivity.this).load(urls).into(pImageViw);
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(RentingDetailActivity.this, BigPicActivity.class);
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

            if (b == null || b.tongqu == null || b.tongqu.size() <= 0) {
                findViewById(R.id.chengjiaoContentView).setVisibility(View.GONE);
                findViewById(R.id.chengjiaoContentView_NoData).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.chengjiaoContentView).setVisibility(View.VISIBLE);
                findViewById(R.id.chengjiaoContentView_NoData).setVisibility(View.GONE);
                lv_chenjiao.setAdapter(new renting_detail_chenjiaoAdapter());
                lv_chenjiao.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                        if (b != null && b.tongqu != null && b.tongqu.size() > 0) {
                            Intent i = new Intent(RentingDetailActivity.this, RentingDetailActivity.class);
                            String catid = b.tongqu.get(0).getCatid();
                            String ID = b.tongqu.get(0).getId();
                            i.putExtra("catid", catid);
                            i.putExtra("id", ID);
                            startActivity(i);
                        }
                    }
                });
            }

            String jwd = b.getJingweidu();
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

            sc_renting.scrollTo(0, 0);
            sc_renting.smoothScrollTo(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setViews() {
        mSlider = (AutoLoopLayout<Pics>) findViewById(R.id.slider);

        llBackRentingdetails = (LinearLayout) findViewById(R.id.ll_back_rentingdetails);

        mMapView = (MapView) findViewById(R.id.renting_bmapView);
        mBaiduMAP = mMapView.getMap();

        mMapView.showZoomControls(false);
        mMapView.showScaleControl(false);

        UiSettings settings = mBaiduMAP.getUiSettings();
        settings.setAllGesturesEnabled(false);
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f);
        mBaiduMAP.setMapStatus(msu);

        lv_chenjiao = (ListView) findViewById(R.id.lv_chenjiao);
    }

    private void showSurePhone() {
        Surepop = new PopupWindow(RentingDetailActivity.this);

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

    private void showAgentPhone() {
        Agentpop = new PopupWindow(RentingDetailActivity.this);

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
                item_pop_boda.setText("拨打" + agentdetail.getCtel());

                Animation anim = AnimationUtils.loadAnimation(RentingDetailActivity.this,
                        R.anim.activity_translate_in);

                SurePhone.setAnimation(anim);
                Surepop.showAtLocation(parentView, Gravity.CENTER, 0, 0);
                Agentpop.dismiss();
                rlagentPhone.clearAnimation();
            }
        });
    }


    private void setListener() {
        tv_renting_phone.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    if (agentdetail != null && !agentdetail.equals("")) {
                        Log.e("agentdetail", "agentdetail=" + agentdetail.toString());
                        String url = agentdetail.getUserpic();
                        Glide.with(RentingDetailActivity.this).load(url).into(iv_agent_pic);

                        tv_agent_name.setText(agentdetail.getRealname());
                        tv_agent_phone.setText(agentdetail.getVtel());
                        Animation anim = AnimationUtils.loadAnimation(RentingDetailActivity.this,
                                R.anim.activity_translate_in);

                        rlagentPhone.setAnimation(anim);
                        Agentpop.showAtLocation(parentView, Gravity.CENTER, 0, 0);
                    } else {
                        if (b.getHidetel().equals("") || b.getHidetel().equals("公开")) {
                            item_pop_boda.setText("拨打" + b.getUsername());
                        } else {
                            if (b.getHidetel().equals("") || b.getHidetel().equals("公开")) {
                                item_pop_boda.setText("拨打" + b.getUsername());
                            } else {
                                if (u.getZhuanjie().equals("1")) {
                                    item_pop_boda.setText("拨打" + u.getCtel());
                                } else {
                                    item_pop_boda.setText("拨打" + b.getUsername());
                                }
                            }
                        }
                        Animation anim = AnimationUtils.loadAnimation(RentingDetailActivity.this,
                                R.anim.activity_translate_in);
                        SurePhone.setAnimation(anim);
                        Surepop.showAtLocation(parentView, Gravity.CENTER, 0, 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        lv_rentinglistview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String catid = tuijians.get(position).getCatid();
                String Id = tuijians.get(position).getId();
                Intent i = new Intent(getApplicationContext(), RentingDetailActivity.class);
                i.putExtra("catid", catid);
                i.putExtra("id", Id);
                startActivity(i);
                finish();
            }
        });


        llBackRentingdetails.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rl_renting_map.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (b.getJingweidu() == null) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "房源的经纬度为空");
                } else {
                    String jwd = b.getJingweidu();
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
                if (b.getJingweidu() == null) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "房源的经纬度为空");
                } else {
                    String jwd = b.getJingweidu();
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

        tvMoreTese.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (b != null) {
                    Intent i = new Intent(RentingDetailActivity.this, RentingAllDetailActivity.class);
                    i.putExtra("rt", b);
                    startActivity(i);
                }
            }
        });

        findViewById(R.id.tv_moreChenjiaojilu).setOnClickListener(new OnClickListener() {//查看更多成交记录
            public void onClick(View view) {
                Intent i = new Intent(RentingDetailActivity.this, RentingChengjiaoActivity.class);
                i.putExtra("rt", b);
                startActivity(i);
            }
        });
    }

    public void GetRenting(RentingDetail detail) {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = detail;
        handler.sendMessage(msg);
    }

    public void tuijian(List<RentingTuijian> tuijians) {
        this.tuijians = tuijians;
        tuijianadapter = new RentingTuijianAdapter(tuijians, getApplicationContext());
        lv_rentinglistview.setAdapter(tuijianadapter);
    }

    public void loginUserInfoFail(String errorMessage) {
//		MyToastShowCenter.CenterToast(getApplicationContext(), errorMessage);
    }

    public void loginUserInfoSuccess(UserInfo u) {
        this.u = u;
    }

    public void AgentDetail(AgentDetail agentdetail) {
        this.agentdetail = agentdetail;
    }


    public class renting_detail_chenjiaoAdapter extends BaseAdapter {
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
                convertView = LayoutInflater.from(RentingDetailActivity.this).inflate(R.layout.item_chengjiao_house, null);
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
            Glide.with(RentingDetailActivity.this).load(UrlFactory.TSFURL + url).into(holder.image);
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