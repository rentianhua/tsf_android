package com.dumu.housego.framgent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.aigestudio.wheelpicker.view.WheelPicker;
import com.aigestudio.wheelpicker.view.WheelPicker.OnWheelChangeListener;
import com.aigestudio.wheelpicker.widget.WheelCityPicker;
import com.aigestudio.wheelpicker.widget.WheelLocalPicker;
import com.aigestudio.wheelpicker.widget.WheelProvincePicker;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.dumu.housego.GetLocationActivity;
import com.dumu.housego.R;
import com.dumu.housego.SearchActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.Street;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.entity.shenZhenLocation;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.ErShouEditPresenter;
import com.dumu.housego.presenter.IErShouFangEditPresenter;
import com.dumu.housego.presenter.IPTershouSubmitPresenter;
import com.dumu.housego.presenter.PTershouSubmitPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.WheelpickerData;
import com.dumu.housego.utils.SelectionActivity;
import com.dumu.housego.view.IErShouFangEditView;
import com.dumu.housego.view.IPTershouSubmitView;
import com.example.testpic.ImageGridActivity;
import com.example.testpic.PublishedActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint({"ResourceAsColor", "NewApi"})
public class PTershouSumbitFragment extends Fragment implements
        IErShouFangEditView, IPTershouSubmitView {

    private String[] mProvinceDatas;
    private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

    private JSONObject mJsonObj;
    private String mCurrentProviceName;
    private String mCurrentCityName;
    private String mCurrentAreaName = "";

    private List<String> Area = new ArrayList<String>();
    private List<String> MinArea = new ArrayList<String>();
    private AddressModel model2 = new AddressModel();
    String AREA;
    String MINAREA;
    //private List<Address> minarea;

    private String texts;

    private List<Address> area;
    private List<String> shen = new ArrayList<String>();
    private String pid;
    String area1 = null;
    String w1 = "罗湖区";
    String w2 = "福田区";
    String w3 = "南山区";
    String w4 = "盐田区";
    String w5 = "宝安区";
    String w6 = "龙岗新区";
    String w7 = "龙华新区";
    String w8 = "光明新区";
    String w9 = "坪山新区";
    String w10 = "大鹏新区";
    String w11 = "东莞";
    String w12 = "惠州";
    String q = "深圳";

    //
    Handler handler = new Handler();
    //
    private static final int XIAOQUNAME = 15;
    private static final int LOCATION = 0;
    //
    private LinearLayout ll_back_putongershou;
    private TextView tv_ershou_housearea, tv_ershou_xiaoquname,
            tv_ershou_jingweidu, tv_ershou_louceng, tv_ershou_loucengmenu,
            tv_ershou_uploadPic, tv_ershou_gongbufangshi,
            tv_ershou_yincangphone;

    private RelativeLayout rl_yincangshouji;

    private EditText et_ershou_chenghu, et_ershou_housrprice,
            et_ershou_loudong, et_ershou_menpai, et_ershou_danyuan;
    private Button btn_ershou_submit, btn_yezhuershou;
    private double latitude;
    private double longitude;
    protected String PoiString;
    protected String louceng1;
    protected String louceng2;
    protected String str;
    private UserInfo userinfo;
    private String username;
    private String userid;
    private String modelid;
    private ErShouFangDetails e = new ErShouFangDetails();

    private IPTershouSubmitPresenter presenter;

    private String tag = "";
    private List<Pics> pics = new ArrayList<Pics>();
    private ErShouFangDetails n = null;
    private IErShouFangEditPresenter editPresenter;
    protected int pos;
    private String TAG = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pt_ershou_sumbit, null);
        // 房源区域
        shen.add(q);
        Area.add(w1);
        Area.add(w2);
        Area.add(w3);
        Area.add(w4);
        Area.add(w5);
        Area.add(w6);
        Area.add(w7);
        Area.add(w8);
        Area.add(w9);
        Area.add(w10);
        Area.add(w11);
        Area.add(w12);

        presenter = new PTershouSubmitPresenter(this);
        editPresenter = new ErShouEditPresenter(this);
        userinfo = HouseGoApp.getLoginInfo(getContext());
        username = userinfo.getUsername();
        userid = userinfo.getUserid();
        modelid = userinfo.getModelid();

        initView(view);
        setListener();
        FontHelper.injectFont(view);
        userinfo = HouseGoApp.getContext().getCurrentUserInfo();

        try {
            if (getArguments().getSerializable("ptershouqq") != null
                    && !getArguments().getSerializable("ptershouqq").equals("")) {
                n = (ErShouFangDetails) getArguments().getSerializable(
                        "ptershouqq");
                ShowAllDetail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onResume() {
        try {

            if (getArguments().getString("yezhuershou") != null
                    && !getArguments().getString("yezhuershou").equals("")) {
                TAG = getArguments().getString("yezhuershou");
                rl_yincangshouji.setVisibility(View.GONE);
                btn_yezhuershou.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onResume();
    }

    private void ShowAllDetail() {
        tv_ershou_housearea.setText("深圳" + " " + n.getCityname() + " "
                + n.getAreaname());
        area1 = tv_ershou_housearea.getText().toString();
        tv_ershou_xiaoquname.setText(n.getXiaoquname());
        et_ershou_chenghu.setText(n.getChenghu());
        et_ershou_housrprice.setText(n.getZongjia() + "");
        et_ershou_loudong.setText(n.getLoudong() + "");
        et_ershou_menpai.setText(n.getMenpai() + "");
        et_ershou_danyuan.setText(n.getDanyuan() + "");

        tv_ershou_jingweidu.setText(n.getJingweidu());
        tv_ershou_louceng.setText(n.getCurceng() + "层 共" + n.getZongceng()
                + "层");
        tv_ershou_loucengmenu.setText(n.getCeng());

        String pubtype = n.getPub_type();
        if (pubtype.equals("1")) {
            tv_ershou_gongbufangshi.setText("自售");
        } else if (pubtype.equals("2")) {
            tv_ershou_gongbufangshi.setText("委托给经纪人");
        } else {
            tv_ershou_gongbufangshi.setText("委托给平台");
        }

        this.pics = n.getPics();
        if (this.pics == null)
            tv_ershou_uploadPic.setText("已经上传" + 0 + "张");
        else
            tv_ershou_uploadPic.setText("已经上传" + pics.size() + "张");

        tv_ershou_yincangphone.setText(n.getHidetel());

    }

    private void initView(View view) {

        rl_yincangshouji = (RelativeLayout) view
                .findViewById(R.id.rl_yincangshouji);
        btn_yezhuershou = (Button) view.findViewById(R.id.btn_yezhuershou);

        ll_back_putongershou = (LinearLayout) view
                .findViewById(R.id.ll_back_putongershou);
        tv_ershou_gongbufangshi = (TextView) view
                .findViewById(R.id.tv_ershou_gongbufangshi);
        tv_ershou_housearea = (TextView) view
                .findViewById(R.id.tv_ptershou_housearea);
        tv_ershou_jingweidu = (TextView) view
                .findViewById(R.id.tv_ershou_jingweidu);
        tv_ershou_louceng = (TextView) view
                .findViewById(R.id.tv_ershou_louceng);
        tv_ershou_loucengmenu = (TextView) view
                .findViewById(R.id.tv_ershou_loucengmenu);
        tv_ershou_uploadPic = (TextView) view
                .findViewById(R.id.tv_ershou_uploadPic);
        tv_ershou_xiaoquname = (TextView) view
                .findViewById(R.id.tv_ershou_xiaoquname);
        tv_ershou_yincangphone = (TextView) view
                .findViewById(R.id.tv_ershou_yincangphone);
        et_ershou_chenghu = (EditText) view
                .findViewById(R.id.et_ershou_chenghu);
        et_ershou_housrprice = (EditText) view
                .findViewById(R.id.et_ershou_housrprice);
        et_ershou_loudong = (EditText) view
                .findViewById(R.id.et_ershou_loudong);
        et_ershou_menpai = (EditText) view.findViewById(R.id.et_ershou_menpai);
        et_ershou_danyuan = (EditText) view
                .findViewById(R.id.et_ershou_danyuan);
        btn_ershou_submit = (Button) view.findViewById(R.id.btn_ershou_submit);
        tv_ershou_uploadPic.setText("已经上传" + pics.size() + "张");

    }

    private void setListener() {

        btn_ershou_submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                e.setProvince("1");

                // e.setCity(pid);
                // e.setArea(area1);
                if (tv_ershou_housearea.getText() != null
                        && !tv_ershou_housearea.getText().equals("")) {
                    String[] area = tv_ershou_housearea.getText().toString()
                            .split(" ");

                    String areA = area[2];
                    String city = area[1];

                    Log.e("area", "area=" + areA + "   " + city);
                    String areapos = "";
                    String citypos = "";
                    for (shenZhenLocation s : shenZhenLocation.locations) {
                        if (city.equals(s.getName())) {
                            citypos = s.getId();
                        }
                    }

                    // for (int i = 0; i < minarea.size(); i++) {
                    // if (areA.equals(minarea.get(i).getName())) {
                    // areapos = minarea.get(i).getId();
                    // }
                    // }
                    for (Street i : Street.streets) {
                        if (areA.equals(i.getName())) {
                            areapos = i.getId();
                        }
                    }

                    e.setCity(citypos);
                    e.setArea(areapos);
                }

                e.setDanyuan(et_ershou_danyuan.getText().toString() + "");

                e.setXiaoquname(tv_ershou_xiaoquname.getText().toString());
                e.setChenghu(et_ershou_chenghu.getText().toString());
                e.setZongjia(et_ershou_housrprice.getText().toString());

                e.setLoudong(et_ershou_loudong.getText().toString());//
                e.setMenpai(et_ershou_menpai.getText().toString());//

                e.setJingweidu(tv_ershou_jingweidu.getText().toString());

//				// 楼层，所在层与总层
//				if (tv_ershou_louceng.getText().toString().length() > 0) {
//					String[] c = tv_ershou_louceng.getText().toString()
//							.split(" ");
//					String c1 = c[0].split("层")[0];
//					String z1 = c[1].split("层")[0].split("共")[1];
//					e.setCurceng(c1);
//					e.setZongceng(z1);
//				} else {
//					e.setCurceng("");//
//					e.setZongceng("");//
//				}

                // 楼层，所在层与总层
                if (tv_ershou_louceng.getText().toString().length() > 0) {

                    if (!tv_ershou_louceng.getText().toString().startsWith("层")) {
                        String[] c = tv_ershou_louceng.getText().toString()
                                .split(" ");
                        String c1 = c[0].split("层")[0];
                        String z1 = c[1].split("层")[0].split("共")[1];
                        e.setCurceng(c1);
                        e.setZongceng(z1);
                    } else {
                        String[] c = tv_ershou_louceng.getText().toString()
                                .split(" ");
                        String c1 = "";
                        String z1 = "";
                        if (c[1].split("层")[0].endsWith("共")) {
                            z1 = "";
                        } else {
                            z1 = c[1].split("层")[0].split("共")[1];
                        }

                        e.setCurceng(c1);
                        e.setZongceng(z1);
                    }
                } else {
                    e.setCurceng("");
                    e.setZongceng("");
                }

                e.setCeng(tv_ershou_loucengmenu.getText().toString());//

                String hTitle = tv_ershou_housearea.getText().toString()
                        + tv_ershou_xiaoquname.getText().toString();
                if (et_ershou_loudong.getText().toString() != null && et_ershou_loudong.getText().toString().length() > 0) {
                    hTitle += et_ershou_loudong.getText().toString() + "栋";
                }
                if (et_ershou_danyuan.getText().toString() != null && et_ershou_danyuan.getText().toString().length() > 0) {
                    hTitle += et_ershou_danyuan.getText().toString() + "单元";
                }
                if (et_ershou_menpai.getText().toString() != null && et_ershou_menpai.getText().toString().length() > 0) {
                    hTitle += et_ershou_menpai.getText().toString() + "号";
                }
                e.setTitle(hTitle.trim());

                String pub = tv_ershou_gongbufangshi.getText().toString();
                if (pub.equals("自售")) {
                    e.setPub_type("1");
                } else if (pub.equals("委托给经纪人")) {
                    e.setPub_type("2");
                } else {
                    e.setPub_type("3");
                }

                if (TAG != null) {
                    e.setHidetel("公开");
                } else {
                    e.setHidetel(tv_ershou_yincangphone.getText().toString());
                }

                e.setPics(pics);

                if (!tv_ershou_housearea.getText().toString().equals("")
                        && !tv_ershou_xiaoquname.getText().toString()
                        .equals("")
                        && !et_ershou_chenghu.getText().toString().equals("")
                        && !et_ershou_housrprice.getText().toString()
                        .equals("")
                        && !tv_ershou_jingweidu.getText().toString().equals("")
                        && !tv_ershou_gongbufangshi.getText().toString()
                        .equals("")) {

                    if (n == null) {
                        presenter.PTershouSubmit(username, userid, modelid, e);
                    } else {
                        e.setId(n.getId());
                        editPresenter
                                .ptershouedit(username, userid, modelid, e);
                    }

                } else {
                    MyToastShowCenter.CenterToast(getActivity(), "还有必填项未填！");
                }

            }
        });

        ll_back_putongershou.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TAG != null) {
                    getActivity().finish();
                } else {
                    Fragment fragment = new PTershouListFragment();
                    FragmentTransaction trans = getActivity()
                            .getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.rl_container, fragment);
                    trans.commitAllowingStateLoss();
                }
            }
        });

        tv_ershou_housearea.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                HouseAreaAlertDialog();
            }
        });

        tv_ershou_jingweidu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), GetLocationActivity.class);
                startActivityForResult(i, LOCATION);
            }
        });

        tv_ershou_xiaoquname.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (area1 == null) {
                    MyToastShowCenter.CenterToast(getActivity(), "请先选择房源区域！");
                } else {
                    Intent i = new Intent(getActivity(), SearchActivity.class);
                    i.putExtra("Area", area1);
                    startActivityForResult(i, XIAOQUNAME);
                }

            }
        });

        tv_ershou_louceng.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerTwo(WheelpickerData.LOUCENG1,
                        WheelpickerData.LOUCENG2, tv_ershou_louceng, "请选择楼层");
            }
        });

        tv_ershou_loucengmenu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerOne(WheelpickerData.LOUCENGMenu,
                        tv_ershou_loucengmenu, "请选择楼层属性");
            }
        });

        tv_ershou_gongbufangshi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TAG != null) {
                    WheelPickerOne(WheelpickerData.FABUFANGSHIT,
                            tv_ershou_gongbufangshi, "请选择发布方式");
                } else {
                    WheelPickerOne(WheelpickerData.FABUFANGSHI,
                            tv_ershou_gongbufangshi, "请选择发布方式");
                }
            }
        });

        tv_ershou_yincangphone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // WheelPickerOne(WheelpickerData.YINCANGPHONE,
                // tv_ershou_yincangphone, "是否隐藏手机号码");
                if (userinfo.getZhuanjie().equals("1")) {
                    YincangPhone(WheelpickerData.YINCANGPHONE,
                            tv_ershou_yincangphone);
                } else {
                    YincangPhone(WheelpickerData.WeiYINCANGPHONE,
                            tv_ershou_yincangphone);
                }
            }
        });

        tv_ershou_uploadPic.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // startActivity(new Intent(getActivity(),
                // PublishedActivity.class));
                Intent i = new Intent(getActivity(), PublishedActivity.class);
                i.putExtra("TAG", "1");
                if (pics != null) {
                    i.putExtra("pics", (Serializable) pics);
                }
                startActivityForResult(i, PublishedActivity.PTERSHOUPIC);

            }
        });

    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            switch (requestCode) {
                case LOCATION:
                    latitude = data.getDoubleExtra("latitude", 0);
                    longitude = data.getDoubleExtra("longitude", 0);

                    LatLng position = new LatLng(latitude, longitude);

                    // LatLng location; 点击事件得到的location
                    GeoCoder geoCoder = GeoCoder.newInstance();
                    geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                        @Override
                        public void onGetGeoCodeResult(GeoCodeResult arg0) {
                        }

                        @Override
                        public void onGetReverseGeoCodeResult(
                                ReverseGeoCodeResult result) {
                            if (result == null
                                    || result.error != SearchResult.ERRORNO.NO_ERROR) {
                                return;
                            }
                            PTershouSumbitFragment.this.PoiString = result
                                    .getAddress();// 解析到的地址
                        }
                    });
                    // 反向地理解析
                    geoCoder.reverseGeoCode(new ReverseGeoCodeOption()
                            .location(position));
                    // Log.e("request",
                    // "result="+latitude+" "+longitude+" "+PoiString);
                    tv_ershou_jingweidu.setText(round(longitude, 6) + ","
                            + round(latitude, 6));
                    break;

                case XIAOQUNAME:
                    String xiaoquname = data.getStringExtra("XIAOQUNAME");
                    tv_ershou_xiaoquname.setText(xiaoquname);
                    break;

                case SelectionActivity.PTERSHOUPIC:
                    if (pics == null)
                        pics = new ArrayList<Pics>();
                    pics.clear();
                    this.pics = (List<Pics>) data
                            .getSerializableExtra("uploadpics");
                    tv_ershou_uploadPic.setText("已经上传" + this.pics.size() + "张");
                    Log.e("PT", "Ptershousubmit" + pics.toString());
                    ImageGridActivity.pics.clear();
                    Log.e("PT", "Ptershousubmit==========ImageGridActivity.pics="
                            + ImageGridActivity.pics);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 将double类型的值，精确小数点
     *
     * @param v
     * @param scale
     * @return
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 房源区域选择
     */
    @SuppressLint({"ResourceAsColor", "NewApi"})
    protected void HouseAreaAlertDialog() {

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.ac_main_dialog2);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        WheelPicker picker1 = (WheelPicker) window
                .findViewById(R.id.main_dialog_container1);
        final WheelPicker picker2 = (WheelPicker) window
                .findViewById(R.id.main_dialog_container2);
        final WheelPicker picker3 = (WheelPicker) window
                .findViewById(R.id.main_dialog_container3);

        Button btncancle = (Button) window.findViewById(R.id.btn_straight);
        Button btnTitle = (Button) window.findViewById(R.id.btn_obtain);
        Button btnSure = (Button) window.findViewById(R.id.btn_curved);

        picker2.setSelected(true);
        picker3.setSelected(true);

        picker1.setBackgroundColor(0xFFF);
        picker1.setSelected(true);
        picker1.setTextColor(R.color.Dingyiblue);
        picker2.setBackgroundColor(0xFFF);
        picker2.setTextColor(R.color.Dingyiblue);
        picker2.setLabelFor(android.graphics.Color.RED);
        picker3.setBackgroundColor(0xFFF);
        picker3.setTextColor(R.color.Dingyiblue);
        picker3.setLabelFor(android.graphics.Color.RED);

        picker1.setData(shen);
        picker2.setData(Area);

        picker1.setOnWheelChangeListener(new OnWheelChangeListener() {
            @Override
            public void onWheelSelected(int index, String data) {
                AREA = data;
            }

            @Override
            public void onWheelScrolling() {
            }

            @Override
            public void onWheelScrollStateChanged(int state) {
            }
        });

        picker2.setOnWheelChangeListener(new OnWheelChangeListener() {

            @Override
            public void onWheelSelected(int index, String data) {
                if (data.equals("罗湖区")) {
                    PTershouSumbitFragment.this.pid = "2";
                } else if (data.equals("福田区")) {
                    PTershouSumbitFragment.this.pid = "4";
                } else if (data.equals("南山区")) {
                    PTershouSumbitFragment.this.pid = "5";
                } else if (data.equals("盐田区")) {
                    PTershouSumbitFragment.this.pid = "6";
                } else if (data.equals("宝安区")) {
                    PTershouSumbitFragment.this.pid = "7";
                } else if (data.equals("龙岗新区")) {
                    PTershouSumbitFragment.this.pid = "8";
                } else if (data.equals("龙华新区")) {
                    PTershouSumbitFragment.this.pid = "9";
                } else if (data.equals("光明新区")) {
                    PTershouSumbitFragment.this.pid = "10";
                } else if (data.equals("坪山新区")) {
                    PTershouSumbitFragment.this.pid = "11";
                } else if (data.equals("大鹏新区")) {
                    PTershouSumbitFragment.this.pid = "12";
                } else if (data.equals("东莞")) {
                    PTershouSumbitFragment.this.pid = "13";
                } else {
                    PTershouSumbitFragment.this.pid = "14";
                }

                MinArea.clear();
                List<String> list = Street.getSubArea(PTershouSumbitFragment.this.pid);
                if (list != null && list.size() > 0) {
                    MinArea.addAll(list);
                    MinArea.add(0, "");
                    MinArea.add("");
                }
                picker3.setData(MinArea);

//				model2.address(pid, new AsycnCallBack() {
//					@Override
//					public void onSuccess(Object success) {
//
//						PTershouSumbitFragment.this.minarea = (List<Address>) success;
//
//						for (Address address : minarea) {
//							String ad = address.getName();
//							MinArea.add(ad);
//						}
//						MinArea.add(0, "");
//						MinArea.add("");
//						picker3.setData(MinArea);
//					}
//
//					@Override
//					public void onError(Object error) {
//
//					}
//				});

                AREA = data;
            }

            @Override
            public void onWheelScrolling() {
            }

            @Override
            public void onWheelScrollStateChanged(int state) {

            }
        });

        picker3.setOnWheelChangeListener(new OnWheelChangeListener() {
            @Override
            public void onWheelSelected(int index, String data) {
                PTershouSumbitFragment.this.MINAREA = " " + data;
            }

            @Override
            public void onWheelScrolling() {
            }

            @Override
            public void onWheelScrollStateChanged(int state) {
            }
        });
        btnSure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_ershou_housearea.setText(q + " " + AREA + MINAREA);

                // 求区域id
                for (int i = 0; i < Street.streets.size(); i++) {
                    String area = Street.streets.get(i).getName();
                    Log.e("area", "area=" + area + "   " + MINAREA);

                    if (area.equals(MINAREA.trim())) {
                        PTershouSumbitFragment.this.area1 = Street.streets.get(i)
                                .getId() + "";
                        Log.e("area1", "area1=" + area1 + "   " + MINAREA);
                        break;
                    } else {
                        Log.e("xxxx--area1", "xxx---area1=" + area1 + "   "
                                + MINAREA);
                    }
                }

                alertDialog.cancel();
            }
        });
        btncancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

        btnTitle.setText("请选择房源区域");
    }

    /**
     * @param Data  数据源，需要展示的数据
     * @param tv    确认后，所需要显示的什么空间上
     * @param title 中间显示条目的内容
     */
    public void WheelPickerOne(List<String> Data, final TextView tv,
                               String title) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.alerlog_wheelpicker_zulin);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        WheelPicker picker = (WheelPicker) window
                .findViewById(R.id.zulin_wheel);
        TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
        TextView tvCancle = (TextView) window
                .findViewById(R.id.tv_wheel_cancle);
        TextView tvTitle = (TextView) window.findViewById(R.id.tv_wheel_title);
        tvTitle.setText(title);
        picker.setData(Data);
        picker.setOnWheelChangeListener(new OnWheelChangeListener() {
            @Override
            public void onWheelSelected(int index, String data) {
                str = data;
            }

            @Override
            public void onWheelScrolling() {
            }

            @Override
            public void onWheelScrollStateChanged(int state) {
            }
        });
        tvSure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(str);
                alertDialog.cancel();
            }
        });

        tvCancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

    }

    /**
     * @param Data  数据源，需要展示的数据
     * @param tv    确认后，所需要显示的什么空间上
     * @param title 中间显示条目的内容
     */
    public void WheelPickerTwo(List<String> Data1, List<String> Data2,
                               final TextView tv, String title) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.alerlog_wheelpicker_jine);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        WheelPicker picker1 = (WheelPicker) window
                .findViewById(R.id.zulin_wheel1);
        WheelPicker picker2 = (WheelPicker) window
                .findViewById(R.id.zulin_wheel2);
        TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
        TextView tvCancle = (TextView) window
                .findViewById(R.id.tv_wheel_cancle);
        TextView tvTitle = (TextView) window.findViewById(R.id.tv_wheel_title);
        tvTitle.setText(title);
        picker1.setData(Data1);
        picker2.setData(Data2);
        picker1.setOnWheelChangeListener(new OnWheelChangeListener() {
            @Override
            public void onWheelSelected(int index, String data) {
                louceng1 = data;
            }

            @Override
            public void onWheelScrolling() {
            }

            @Override
            public void onWheelScrollStateChanged(int state) {
            }
        });
        picker2.setOnWheelChangeListener(new OnWheelChangeListener() {
            @Override
            public void onWheelSelected(int index, String data) {
                louceng2 = data;
            }

            @Override
            public void onWheelScrolling() {
            }

            @Override
            public void onWheelScrollStateChanged(int state) {
            }
        });

        tvSure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(louceng1 + " " + louceng2);
                louceng1 = "";
                louceng2 = "";
                alertDialog.cancel();
            }
        });

        tvCancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

    }

    @SuppressLint("ResourceAsColor")
    public void YincangPhone(List<String> data, final TextView tv) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.phoneyincang_window);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
        TextView tvCancle = (TextView) window
                .findViewById(R.id.tv_wheel_cancle);
        final RadioButton rbgongkai = (RadioButton) window
                .findViewById(R.id.phone_gongkai);
        final RadioButton rbyincang = (RadioButton) window
                .findViewById(R.id.phone_yincang);
        RadioGroup rg = (RadioGroup) window.findViewById(R.id.rg_phone);
        rbgongkai.setChecked(true);
        rbgongkai.setTextColor(R.color.black);
        rbyincang.setTextColor(R.color.huisetextcolor);
        str = "公开";
        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.phone_gongkai:
                        rbgongkai.setTextColor(R.color.black);
                        rbyincang.setTextColor(R.color.huisetextcolor);
                        str = "公开";
                        break;
                    case R.id.phone_yincang:
                        rbyincang.setTextColor(R.color.black);
                        rbgongkai.setTextColor(R.color.huisetextcolor);

                        if (userinfo.getZhuanjie().equals("1")) {
                            str = "保密";
                        } else {
                            str = "保密(请至个人中心申请分机号)";
                        }

                        break;
                    default:
                        break;
                }

            }
        });

        tvSure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str.equals("保密(请至个人中心申请分机号)")) {
                    tv.setText("");
                    MyToastShowCenter.CenterToast(getContext(),
                            "未绑定400,请至个人中心申请分机号");
                } else {
                    tv.setText(str);
                }
                alertDialog.cancel();
            }
        });

        tvCancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

    }

    @Override
    public void PTershouSubmit(String info) {
        MyToastShowCenter.CenterToast(getActivity(), info);
        if (info.equals("发布成功")) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    Fragment fragment = new PTershouListFragment();
                    FragmentTransaction trans = getActivity()
                            .getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.rl_container, fragment);
                    trans.commitAllowingStateLoss();
                }
            }, 1000);
        }
    }

    @Override
    public void ershouedit(String info) {
        MyToastShowCenter.CenterToast(getContext(), info);
        if (info.equals("修改成功") || info.equals("该房源已锁定")) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    Fragment fragment = new PTershouListFragment();
                    FragmentTransaction trans = getActivity()
                            .getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.rl_container, fragment);
                    trans.commitAllowingStateLoss();
                }
            }, 1000);
        }
    }

    public class TextViewAdapter extends BaseAdapter {
        private List<String> blocktrades;
        private Context context;
        private LayoutInflater Inflater;
        private Typeface typeface;

        public TextViewAdapter(List<String> blocktrades, Context context) {
            super();
            this.blocktrades = blocktrades;
            this.context = context;
            this.Inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return blocktrades == null ? 0 : blocktrades.size();
        }

        @Override
        public String getItem(int position) {

            return blocktrades.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = Inflater.inflate(R.layout.item_list_forphone,
                        null);
                holder = new ViewHolder();
                holder.tv_qiuzu_area = (TextView) convertView
                        .findViewById(R.id.tv_spinner_show);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (pos == position) {
                String n = getItem(position);

                holder.tv_qiuzu_area.setTextColor(R.color.blue);
                ;
                holder.tv_qiuzu_area.setText(n);
            }

            return convertView;
        }

        class ViewHolder {
            TextView tv_qiuzu_area;
        }

    }

}
