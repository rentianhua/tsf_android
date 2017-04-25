package com.dumu.housego.framgent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.aigestudio.wheelpicker.view.WheelPicker;
import com.aigestudio.wheelpicker.view.WheelPicker.OnWheelChangeListener;
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
import com.dumu.housego.activity.ImageGrallyMain;
import com.dumu.housego.activity.ImageGrallyMain2;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.entity.Street;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.entity.shenZhenLocation;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.ErShouEditPresenter;
import com.dumu.housego.presenter.IErShouFangEditPresenter;
import com.dumu.housego.presenter.IPTrentingSubmitPresenter;
import com.dumu.housego.presenter.PTrentingSubmitPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.WheelpickerData;
import com.dumu.housego.utils.SelectionActivity;
import com.dumu.housego.view.IErShouFangEditView;
import com.dumu.housego.view.IPTrentingSubmitView;
import com.example.testpic.ImageGridActivity;
import com.example.testpic.PublishedActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

public class PTrentingSumbitFragment extends Fragment implements IErShouFangEditView, IPTrentingSubmitView {
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
    //
    Handler handler = new Handler();
    //
    private static final int XIAOQUNAME = 15;
    private static final int LOCATION = 0;
    //
    private List<String> Area = new ArrayList<String>();
    private List<String> MinArea = new ArrayList<String>();
    private AddressModel model2 = new AddressModel();
    String AREA;
    String MINAREA;
    //private List<Address> minarea;
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
    private LinearLayout ll_back_putongrenting;
    private TextView tv_renting_housearea, tv_renting_xiaoquname, tv_renting_jingweidu, tv_renting_huType,
            tv_renting_uploadPic, tv_renting_fabufangshi, tv_renting_yincangphone;
    private EditText et_renting_housrprice, et_renting_mianji;
    private Button btn_renting_submit;
    private RelativeLayout rl_yincangshouji_renting;
    protected String ting1;
    protected String ting2;
    protected String ting3;

    private RentingDetail r = new RentingDetail();
    //
    private IPTrentingSubmitPresenter presenter;
    private List<Pics> pics = new ArrayList<Pics>();
    private RentingDetail n = null;
    private IErShouFangEditPresenter editPresenter;
    private String TAG = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pt_renting_sumbit, null);
        presenter = new PTrentingSubmitPresenter(this);
        editPresenter = new ErShouEditPresenter(this);
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
        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
        initView(view);
        FontHelper.injectFont(view);
        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
        try {
            if (getArguments().getSerializable("ptrentings") != null && !getArguments().getSerializable("ptrentings").equals("")) {

                n = (RentingDetail) getArguments().getSerializable("ptrentings");
                ShowAllDetail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setListener();
        return view;
    }

    @Override
    public void onResume() {
        try {

            if (getArguments().getString("yezhurenting") != null && !getArguments().getString("yezhurenting").equals("")) {
                TAG = getArguments().getString("yezhurenting");
                rl_yincangshouji_renting.setVisibility(View.GONE);
            }

            Log.e("TAG=", "TAG=" + TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }


        super.onResume();
    }

    private void ShowAllDetail() {
        tv_renting_housearea.setText("深圳" + " " + n.getCityname() + " " + n.getAreaname());
        area1 = tv_renting_housearea.getText().toString();
        tv_renting_xiaoquname.setText(n.getXiaoquname());
        et_renting_housrprice.setText(n.getZujin() + "");
        et_renting_mianji.setText(n.getMianji() + "");
        tv_renting_jingweidu.setText(n.getJingweidu());
        if (n.getShi().equals("6")) {
            tv_renting_huType.setText(n.getShi() + "室 " + n.getTing() + "厅 " + n.getWei() + "卫");
        } else {
            tv_renting_huType.setText(n.getShi() + "室 " + n.getTing() + "厅 " + n.getWei() + "卫");
        }

        String pubtype = n.getPub_type();
        if (pubtype.equals("1")) {
            tv_renting_fabufangshi.setText("自售");
        } else if (pubtype.equals("2")) {
            tv_renting_fabufangshi.setText("委托给经纪人");
        } else {
            tv_renting_fabufangshi.setText("委托给平台");
        }

        pics = n.getPics();
        if (this.pics == null)
            tv_renting_uploadPic.setText("已经上传" + 0 + "张");
        else
            tv_renting_uploadPic.setText("已经上传" + pics.size() + "张");

        tv_renting_yincangphone.setText(n.getHidetel());

    }

    private void initView(View view) {
        ll_back_putongrenting = (LinearLayout) view.findViewById(R.id.ll_back_putongrenting);
        rl_yincangshouji_renting = (RelativeLayout) view.findViewById(R.id.rl_yincangshouji_renting);

        tv_renting_fabufangshi = (TextView) view.findViewById(R.id.tv_renting_fabufangshi);
        tv_renting_housearea = (TextView) view.findViewById(R.id.tv_renting_housearea);
        tv_renting_huType = (TextView) view.findViewById(R.id.tv_renting_huType);
        tv_renting_jingweidu = (TextView) view.findViewById(R.id.tv_renting_jingweidu);
        tv_renting_uploadPic = (TextView) view.findViewById(R.id.tv_renting_uploadPic);
        tv_renting_xiaoquname = (TextView) view.findViewById(R.id.tv_renting_xiaoquname);
        tv_renting_yincangphone = (TextView) view.findViewById(R.id.tv_renting_yincangphone);

        et_renting_mianji = (EditText) view.findViewById(R.id.et_renting_mianji);
        et_renting_housrprice = (EditText) view.findViewById(R.id.et_renting_housrprice);

        btn_renting_submit = (Button) view.findViewById(R.id.btn_renting_submit);

        tv_renting_uploadPic.setText("已经上传" + pics.size() + "张");
    }

    private void setListener() {

        btn_renting_submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                r.setUsername(userinfo.getUsername());
                r.setUserid(userinfo.getUserid());
                r.setModelid(userinfo.getModelid());

                r.setProvince("1");

                if (tv_renting_housearea.getText() != null && !tv_renting_housearea.getText().equals("")) {
                    String[] area = tv_renting_housearea.getText().toString().split(" ");

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

                    for (Street i : Street.streets) {
                        if (areA.equals(i.getName())) {
                            areapos = i.getId();
                        }
                    }

                    r.setCity(citypos);
                    r.setArea(areapos);
                }
//				r.setCity(pid);
//				r.setArea(area1);
                r.setXiaoquname(tv_renting_xiaoquname.getText().toString());

                r.setZujin(et_renting_housrprice.getText().toString());
                r.setMianji(et_renting_mianji.getText().toString());

                r.setJingweidu(tv_renting_jingweidu.getText().toString());

                String pub = tv_renting_fabufangshi.getText().toString();
                if (pub.equals("自售")) {
                    r.setPub_type("1");
                } else if (pub.equals("委托给经纪人")) {
                    r.setPub_type("2");
                } else {
                    r.setPub_type("3");
                }

                if (TAG != null) {
                    r.setHidetel("公开");
                } else {
                    r.setHidetel(tv_renting_yincangphone.getText().toString());
                }

                // 户型
                r.setHuxing(tv_renting_huType.getText().toString());
                if (tv_renting_huType.getText().toString().length() > 0) {
                    String h = tv_renting_huType.getText().toString();
                    String shi = h.split(" ")[0].split("室")[0];
                    String ting = h.split(" ")[1].split("厅")[0];
                    String wei = h.split(" ")[2].split("卫")[0];

                    r.setShi(shi);
                    r.setTing(ting);
                    r.setWei(wei);
                } else {
                    r.setShi("");
                    r.setTing("");
                    r.setWei("");
                }

                String x = tv_renting_housearea.getText().toString().trim()
                        + tv_renting_xiaoquname.getText().toString().trim()
                        + tv_renting_huType.getText().toString().trim();

                r.setTitle(x);
                r.setPics(pics);

                if (!tv_renting_housearea.getText().toString().equals("") && !tv_renting_xiaoquname.getText().toString().equals("") && !et_renting_housrprice.getText().toString().equals("")
                        && !et_renting_mianji.getText().toString().equals("") && !tv_renting_jingweidu.getText().toString().equals("")
                        && !tv_renting_fabufangshi.getText().toString().equals("") && !tv_renting_huType.getText().toString().equals("")
//						&&!tv_renting_yincangphone.getText().toString().equals("")
                        ) {

                    if (n == null) {
                        presenter.PTrentingSubmit(r);
                    } else {
                        r.setId(n.getId());
                        editPresenter.ptrentingedit(r);
                    }
                } else {
                    MyToastShowCenter.CenterToast(getActivity(), "还有必填项未填！");
                }
            }
        });

        ll_back_putongrenting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TAG != null) {
                    getActivity().finish();
                } else {
                    Fragment fragment = new PTrentingListFragment();
                    FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.rl_container, fragment);
                    trans.commitAllowingStateLoss();
                }
            }
        });

        tv_renting_housearea.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                HouseAreaAlertDialog();

            }
        });

        tv_renting_xiaoquname.setOnClickListener(new OnClickListener() {

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

        tv_renting_huType.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerThree(WheelpickerData.PTHUTYPE1, WheelpickerData.PTHUTYPE2, WheelpickerData.PTHUTYPE3,
                        tv_renting_huType, "请选择户型");
            }
        });

        tv_renting_jingweidu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), GetLocationActivity.class);
                startActivityForResult(i, LOCATION);

            }
        });

        tv_renting_fabufangshi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TAG != null) {

                    WheelPickerOne(WheelpickerData.FABUFANGSHIT, tv_renting_fabufangshi, "请选择发布方式");

                } else {

                    WheelPickerOne(WheelpickerData.FABUFANGSHI, tv_renting_fabufangshi, "请选择发布方式");

                }
            }
        });

        tv_renting_yincangphone.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//				WheelPickerOne(WheelpickerData.YINCANGPHONE, tv_renting_yincangphone, "是否隐藏手机号码");
                YincangPhone(WheelpickerData.YINCANGPHONE, tv_renting_yincangphone);
            }
        });

        tv_renting_uploadPic.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), PublishedActivity.class);
                i.putExtra("TAG", "2");
                if (pics != null) {
                    i.putExtra("pics", (Serializable) pics);
                }
                startActivityForResult(i, PublishedActivity.PTRentingPIC);

            }
        });

    }

    /**
     * 房源区域选择
     */
    @SuppressLint("ResourceAsColor")
    protected void HouseAreaAlertDialog() {

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.ac_main_dialog2);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        WheelPicker picker1 = (WheelPicker) window.findViewById(R.id.main_dialog_container1);
        final WheelPicker picker2 = (WheelPicker) window.findViewById(R.id.main_dialog_container2);
        final WheelPicker picker3 = (WheelPicker) window.findViewById(R.id.main_dialog_container3);
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

//		picker1.setStyle(WheelPicker.STRAIGHT);
//		picker2.setStyle(WheelPicker.STRAIGHT);
//		picker3.setStyle(WheelPicker.STRAIGHT);

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
                    PTrentingSumbitFragment.this.pid = "2";
                } else if (data.equals("福田区")) {
                    PTrentingSumbitFragment.this.pid = "4";
                } else if (data.equals("南山区")) {
                    PTrentingSumbitFragment.this.pid = "5";
                } else if (data.equals("盐田区")) {
                    PTrentingSumbitFragment.this.pid = "6";
                } else if (data.equals("宝安区")) {
                    PTrentingSumbitFragment.this.pid = "7";
                } else if (data.equals("龙岗新区")) {
                    PTrentingSumbitFragment.this.pid = "8";
                } else if (data.equals("龙华新区")) {
                    PTrentingSumbitFragment.this.pid = "9";
                } else if (data.equals("光明新区")) {
                    PTrentingSumbitFragment.this.pid = "10";
                } else if (data.equals("坪山新区")) {
                    PTrentingSumbitFragment.this.pid = "11";
                } else if (data.equals("大鹏新区")) {
                    PTrentingSumbitFragment.this.pid = "12";
                } else if (data.equals("东莞")) {
                    PTrentingSumbitFragment.this.pid = "13";
                } else {
                    PTrentingSumbitFragment.this.pid = "14";
                }

                MinArea.clear();
                List<String> list = Street.getSubArea(PTrentingSumbitFragment.this.pid);
                if (list != null && list.size() > 0) {
                    MinArea.addAll(list);
                    MinArea.add(0, "");
                    MinArea.add("");
                }
                picker3.setData(MinArea);

//				model2.address(pid, new AsycnCallBack() {
//					@Override
//					public void onSuccess(Object success) {
//						PTrentingSumbitFragment.this.minarea = (List<Address>) success;
//
//						for (Address address : minarea) {
//							String ad = address.getName();
//							MinArea.add(ad);
//						}
//						MinArea.add(0,"");
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
                PTrentingSumbitFragment.this.MINAREA = " " + data;
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
                tv_renting_housearea.setText(q + " " + AREA + MINAREA);

                // 求区域id
                for (int i = 0; i < Street.streets.size(); i++) {
                    String area = Street.streets.get(i).getName();
                    Log.e("area", "area=" + area + "   " + MINAREA);

                    if (area.equals(MINAREA.trim())) {
                        PTrentingSumbitFragment.this.area1 = Street.streets.get(i).getId() + "";
                        Log.e("area1", "area1=" + area1 + "   " + MINAREA);
                        break;
                    } else {
                        Log.e("xxxx--area1", "xxx---area1=" + area1 + "   " + MINAREA);
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
                        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                                return;
                            }
                            PTrentingSumbitFragment.this.PoiString = result.getAddress();// 解析到的地址
                        }
                    });
                    // 反向地理解析
                    geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(position));
                    // Log.e("request", "result="+latitude+" "+longitude+"
                    // "+PoiString);
                    tv_renting_jingweidu.setText(round(longitude, 6) + "," + round(latitude, 6));
                    break;

                case XIAOQUNAME:
                    String xiaoquname = data.getStringExtra("XIAOQUNAME");
                    tv_renting_xiaoquname.setText(xiaoquname);
                    break;

                case PublishedActivity.PTRentingPIC:
                    if (pics == null)
                        pics = new ArrayList<Pics>();
                    pics.clear();
                    this.pics = (List<Pics>) data.getSerializableExtra("uploadpics");
                    Log.e("PT", "Ptershousubmit" + pics.toString());
                    tv_renting_uploadPic.setText("已经上传" + pics.size() + "张");
                    ImageGridActivity.pics.clear();
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
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * @param Data  数据源，需要展示的数据
     * @param tv    确认后，所需要显示的什么空间上
     * @param title 中间显示条目的内容
     */
    public void WheelPickerOne(List<String> Data, final TextView tv, String title) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.alerlog_wheelpicker_zulin);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        WheelPicker picker = (WheelPicker) window.findViewById(R.id.zulin_wheel);
        TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
        TextView tvCancle = (TextView) window.findViewById(R.id.tv_wheel_cancle);
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


    @SuppressLint("ResourceAsColor")
    public void YincangPhone(List<String> data, final TextView tv) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.phoneyincang_window);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
        TextView tvCancle = (TextView) window.findViewById(R.id.tv_wheel_cancle);
        final RadioButton rbgongkai = (RadioButton) window.findViewById(R.id.phone_gongkai);
        final RadioButton rbyincang = (RadioButton) window.findViewById(R.id.phone_yincang);
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
                    MyToastShowCenter.CenterToast(getContext(), "未绑定400,请至个人中心申请分机号");
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


    /**
     * @param Data  数据源，需要展示的数据
     * @param tv    确认后，所需要显示的什么空间上
     * @param title 中间显示条目的内容
     */
    public void WheelPickerThree(List<String> Data1, List<String> Data2, List<String> Data3, final TextView tv,
                                 String title) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.alerlog_wheelpicker_tingshi);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        WheelPicker picker1 = (WheelPicker) window.findViewById(R.id.zulin_wheel1);
        WheelPicker picker2 = (WheelPicker) window.findViewById(R.id.zulin_wheel2);
        WheelPicker picker3 = (WheelPicker) window.findViewById(R.id.zulin_wheel3);

        TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
        TextView tvCancle = (TextView) window.findViewById(R.id.tv_wheel_cancle);
        TextView tvTitle = (TextView) window.findViewById(R.id.tv_wheel_title);
        tvTitle.setText(title);
        picker1.setData(Data1);
        picker2.setData(Data2);
        picker3.setData(Data3);
        picker1.setOnWheelChangeListener(new OnWheelChangeListener() {
            @Override
            public void onWheelSelected(int index, String data) {
                ting1 = data;
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
                ting2 = data;
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
                ting3 = data;
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
                tv.setText(ting1 + " " + ting2 + " " + ting3);
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
    public void PTrentingSubmit(String info) {
        MyToastShowCenter.CenterToast(getActivity(), info);

        if (info.equals("发布成功")) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    Fragment fragment = new PTrentingListFragment();
                    FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.rl_container, fragment);
                    trans.commitAllowingStateLoss();
                }
            }, 1000);
        }

    }

    @Override
    public void ershouedit(String info) {
        MyToastShowCenter.CenterToast(getActivity(), info);
        if (info.equals("修改成功") || info.equals("该房源已锁定")) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    Fragment fragment = new PTrentingListFragment();
                    FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.rl_container, fragment);
                    trans.commitAllowingStateLoss();
                }
            }, 1000);
        }
    }

}
