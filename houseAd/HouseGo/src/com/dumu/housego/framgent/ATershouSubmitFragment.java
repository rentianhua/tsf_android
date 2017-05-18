package com.dumu.housego.framgent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aigestudio.wheelpicker.view.WheelPicker;
import com.aigestudio.wheelpicker.view.WheelPicker.OnWheelChangeListener;
import com.aigestudio.wheelpicker.widget.WheelDatePicker;
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
import com.dumu.housego.SubmitEdittextActivity;
import com.dumu.housego.activity.ImageGrallyMain2;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ATerShouSubmit;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.Street;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.entity.shenZhenLocation;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.ATershousubmitPresenter;
import com.dumu.housego.presenter.ErShouEditPresenter;
import com.dumu.housego.presenter.IATerhsouSubmitPresenter;
import com.dumu.housego.presenter.IErShouFangEditPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.WheelpickerData;
import com.dumu.housego.view.IATershouSubmitView;
import com.dumu.housego.view.IErShouFangEditView;
import com.example.testpic.ImageGridActivity;
import com.example.testpic.PublishedActivity;

@SuppressLint("NewApi")
public class ATershouSubmitFragment extends Fragment implements
        IErShouFangEditView, IATershouSubmitView {
    String time = "";
    //
    private String tcb1 = "";
    private String tcb2 = "";
    private String tcb3 = "";
    private String tcb4 = "";
    private String tcb5 = "";
    private String tcb6 = "";
    //
    private String louceng1;
    private String louceng2;

    private String ting1;
    private String ting2;
    private String ting3;

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
    // 房源区域
    private static final int XIAOQUNAME = 15;
    private static final int LOCATION = 0;
    private static final int BIAOTI = 1;
    private static final int FANGYUANMIAOSHU = 2;
    private static final int TOUZIFENXI = 3;
    private static final int HUXINGJIESHAO = 4;
    private static final int XIAOQUJIESHAO = 5;
    private static final int SHUIFEIJIEXI = 6;
    private static final int ZHUANGXIUMIAOSHU = 7;
    private static final int ZHOUBIANPEITAO = 8;
    private static final int JIAOYUPEITAO = 9;
    private static final int JIAOTONGCHUXING = 10;
    private static final int HEXINMAIDIAN = 11;
    private static final int XIAOQUYOUSHI = 12;
    private static final int QUANSHUDIYA = 13;
    private static final int TUIJIANLIYOU = 14;

    private static final int BACK = 404;

    private EditText et_ershou_housrprice, et_ershou_jianzhumianji,
            et_ershou_fangling, et_ershou_loudong, et_ershou_menpai,
            et_ershou_taoneimianji;
    private LinearLayout ll_back_agentershousubmit;
    private TextView tv_ershou_housearea, tv_ershou_xiaoquname,
            tv_ershou_jingweidu, tv_ershou_louceng, tv_ershou_loucengmenu,
            tv_ershou_wuyeType,tv_ershou_shangcijiaoyi, tv_ershou_diyaxinxi, tv_ershou_huType,
            tv_ershou_houseSX, tv_ershou_jianzhuType, tv_ershou_jianzhuJiegou,
            tv_ershou_tihubili, tv_ershou_houseUse, tv_ershou_chanquansuoshu,
            tv_ershou_SfDianti, tv_ershou_weiyizhuzhai, tv_ershou_guapaitime,
            tv_ershou_biaoqian, tv_ershou_ditieline;
    private TextView tv_ershou_biaoti, tv_ershou_houseDesc,
            tv_ershou_touzifenxi, tv_ershou_huxingjieshao,
            tv_ershou_xiaoqujieshao, tv_ershou_shuifeijiexi,
            tv_ershou_zhuangxiumiaoshu, tv_ershou_zhoubianpeitao,
            tv_ershou_jiaoyupeitao, tv_ershou_jiaotongchuxing,
            tv_ershou_hexinmaidian, tv_ershou_xiaoquyoushi,
            tv_ershou_quanshudiya, tv_ershou_tuijianliyou, tv_ershou_uploadPic;
    private Button btn_ershou_submit;

    // 地图地标返回信息
    double latitude, longitude;
    String PoiString;
    //
    private String str;
    //
    private int TAG = 0;

    private IATerhsouSubmitPresenter presenter;
    private ATerShouSubmit at = new ATerShouSubmit();

    SubmitEditTextFragment f2;
    private UserInfo userinfo;

    Handler handler = new Handler();
    private List<Pics> pics = new ArrayList<Pics>();
    ErShouFangDetails n = null;
    private IErShouFangEditPresenter editPresenter;
    private EditText et_ershou_danyuan;
    private String tcb7;
    private String tcb8;

    private String weituoedit = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agent_ershou_submit,
                null);
        presenter = new ATershousubmitPresenter(this);
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
        //
        initView(view);
        FontHelper.injectFont(view);
        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
        try {
            if (getArguments().getSerializable("key") != null
                    && !getArguments().getSerializable("key").equals("")) {
                n = (ErShouFangDetails) getArguments().getSerializable("key");

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
            if (getArguments().getString("weituoedites") != null
                    && !getArguments().getString("weituoedites").equals("")) {
                weituoedit = getArguments().getString("weituoedites");
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
        et_ershou_housrprice.setText(n.getZongjia() + "");
        et_ershou_jianzhumianji.setText(n.getJianzhumianji() + "");
        et_ershou_fangling.setText(n.getFangling() + "");
        et_ershou_loudong.setText(n.getLoudong() + "");
        et_ershou_menpai.setText(n.getMenpai() + "");
        et_ershou_taoneimianji.setText(n.getTaoneimianji() + "");
        et_ershou_danyuan.setText(n.getDanyuan() + "");

        tv_ershou_jingweidu.setText(n.getJingweidu());

        if (n.getCurceng() != null && !n.getCurceng().equals("")) {
            tv_ershou_louceng.setText(n.getCurceng() + "层 共" + n.getZongceng()
                    + "层");
        } else {
            tv_ershou_louceng.setText("层 共" + n.getZongceng() + "层");
        }
        tv_ershou_loucengmenu.setText(n.getCeng());
        tv_ershou_wuyeType.setText(n.getJiaoyiquanshu());
        tv_ershou_diyaxinxi.setText(n.getDiyaxinxi());
        tv_ershou_huType.setText(n.getShi() + "室 " + n.getTing() + "厅 "
                + n.getWei() + "卫");
        tv_ershou_houseSX.setText(n.getJiegou() + " " + n.getZhuangxiu() + " "
                + n.getChaoxiang());

        this.pics = n.getPics();
        if (this.pics == null)
            tv_ershou_uploadPic.setText("已经上传" + 0 + "张");
        else
            tv_ershou_uploadPic.setText("已经上传" + pics.size() + "张");

        tv_ershou_jianzhuType.setText(n.getJianzhutype());
        tv_ershou_jianzhuJiegou.setText(n.getJianzhujiegou());
        tv_ershou_tihubili.setText(n.getTihubili());
        tv_ershou_houseUse.setText(n.getFangwuyongtu());
        tv_ershou_chanquansuoshu.setText(n.getChanquansuoshu());
        tv_ershou_SfDianti.setText(n.getDianti());
        tv_ershou_weiyizhuzhai.setText(n.getIsweiyi());
        tv_ershou_guapaitime.setText(n.getGuapaidate());
        tv_ershou_shangcijiaoyi.setText(n.getShangcijiaoyi());
        tv_ershou_biaoqian.setText(n.getBiaoqian());
        tv_ershou_ditieline.setText(n.getDitiexian());
        tv_ershou_biaoti.setText(n.getTitle());
        tv_ershou_houseDesc.setText(n.getDesc());
        tv_ershou_touzifenxi.setText(n.getTouzifenxi());
        tv_ershou_huxingjieshao.setText(n.getHuxingintro());
        tv_ershou_xiaoqujieshao.setText(n.getXiaoquintro());
        tv_ershou_shuifeijiexi.setText(n.getShuifeijiexi());
        tv_ershou_zhuangxiumiaoshu.setText(n.getZxdesc());
        tv_ershou_zhoubianpeitao.setText(n.getShenghuopeitao());
        tv_ershou_jiaoyupeitao.setText(n.getXuexiaomingcheng());
        tv_ershou_jiaotongchuxing.setText(n.getJiaotong());
        tv_ershou_hexinmaidian.setText(n.getHexinmaidian());
        tv_ershou_xiaoquyoushi.setText(n.getXiaoquyoushi());
        tv_ershou_quanshudiya.setText(n.getQuanshudiya());
        tv_ershou_tuijianliyou.setText(n.getYezhushuo());
    }

    private void initView(View view) {
        ll_back_agentershousubmit = (LinearLayout) view
                .findViewById(R.id.ll_back_agentershousubmit);
        //
        tv_ershou_housearea = (TextView) view
                .findViewById(R.id.tv_ershou_housearea);
        tv_ershou_xiaoquname = (TextView) view
                .findViewById(R.id.tv_ershou_xiaoquname);
        //
        et_ershou_fangling = (EditText) view
                .findViewById(R.id.et_ershou_fangling);
        et_ershou_housrprice = (EditText) view
                .findViewById(R.id.et_ershou_housrprice);
        et_ershou_jianzhumianji = (EditText) view
                .findViewById(R.id.et_ershou_jianzhumianji);
        et_ershou_loudong = (EditText) view
                .findViewById(R.id.et_ershou_loudong);
        et_ershou_menpai = (EditText) view.findViewById(R.id.et_ershou_menpai);
        et_ershou_taoneimianji = (EditText) view
                .findViewById(R.id.et_ershou_taoneimianji);
        et_ershou_danyuan = (EditText) view
                .findViewById(R.id.et_ershou_danyuan);
        //
        tv_ershou_jingweidu = (TextView) view
                .findViewById(R.id.tv_ershou_jingweidu);
        tv_ershou_diyaxinxi = (TextView) view
                .findViewById(R.id.tv_ershou_diyaxinxi);
        tv_ershou_houseSX = (TextView) view
                .findViewById(R.id.tv_ershou_houseSX);
        tv_ershou_huType = (TextView) view.findViewById(R.id.tv_ershou_huType);
        tv_ershou_louceng = (TextView) view
                .findViewById(R.id.tv_ershou_louceng);
        tv_ershou_loucengmenu = (TextView) view
                .findViewById(R.id.tv_ershou_loucengmenu);
        tv_ershou_wuyeType = (TextView) view
                .findViewById(R.id.tv_ershou_wuyeType);
        //
        tv_ershou_jianzhuType = (TextView) view
                .findViewById(R.id.tv_ershou_jianzhuType);
        tv_ershou_jianzhuJiegou = (TextView) view
                .findViewById(R.id.tv_ershou_jianzhuJiegou);
        tv_ershou_tihubili = (TextView) view
                .findViewById(R.id.tv_ershou_tihubili);
        tv_ershou_houseUse = (TextView) view
                .findViewById(R.id.tv_ershou_houseUse);
        tv_ershou_chanquansuoshu = (TextView) view
                .findViewById(R.id.tv_ershou_chanquansuoshu);
        tv_ershou_SfDianti = (TextView) view
                .findViewById(R.id.tv_ershou_SfDianti);
        tv_ershou_weiyizhuzhai = (TextView) view
                .findViewById(R.id.tv_ershou_weiyizhuzhai);
        tv_ershou_guapaitime = (TextView) view
                .findViewById(R.id.tv_ershou_guapaitime);
        tv_ershou_shangcijiaoyi = (TextView) view
                .findViewById(R.id.tv_ershou_shangcijiaoyi);
        tv_ershou_biaoqian = (TextView) view
                .findViewById(R.id.tv_ershou_biaoqian);
        tv_ershou_ditieline = (TextView) view
                .findViewById(R.id.tv_ershou_ditieline);

        tv_ershou_uploadPic = (TextView) view
                .findViewById(R.id.tv_ershou_uploadPic);

        //
        tv_ershou_biaoti = (TextView) view.findViewById(R.id.tv_ershou_biaoti);
        tv_ershou_houseDesc = (TextView) view
                .findViewById(R.id.tv_ershou_houseDesc);
        tv_ershou_touzifenxi = (TextView) view
                .findViewById(R.id.tv_ershou_touzifenxi);
        tv_ershou_huxingjieshao = (TextView) view
                .findViewById(R.id.tv_ershou_huxingjieshao);
        tv_ershou_xiaoqujieshao = (TextView) view
                .findViewById(R.id.tv_ershou_xiaoqujieshao);
        tv_ershou_shuifeijiexi = (TextView) view
                .findViewById(R.id.tv_ershou_shuifeijiexi);
        tv_ershou_zhuangxiumiaoshu = (TextView) view
                .findViewById(R.id.tv_ershou_zhuangxiumiaoshu);
        tv_ershou_zhoubianpeitao = (TextView) view
                .findViewById(R.id.tv_ershou_zhoubianpeitao);
        tv_ershou_jiaoyupeitao = (TextView) view
                .findViewById(R.id.tv_ershou_jiaoyupeitao);
        tv_ershou_jiaotongchuxing = (TextView) view
                .findViewById(R.id.tv_ershou_jiaotongchuxing);
        tv_ershou_hexinmaidian = (TextView) view
                .findViewById(R.id.tv_ershou_hexinmaidian);
        tv_ershou_xiaoquyoushi = (TextView) view
                .findViewById(R.id.tv_ershou_xiaoquyoushi);
        tv_ershou_quanshudiya = (TextView) view
                .findViewById(R.id.tv_ershou_quanshudiya);
        tv_ershou_tuijianliyou = (TextView) view
                .findViewById(R.id.tv_ershou_tuijianliyou);
        //
        btn_ershou_submit = (Button) view.findViewById(R.id.btn_ershou_submit);
        //

    }

    private void setListener() {

        tv_ershou_uploadPic.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), PublishedActivity.class);
                i.putExtra("TAG", "3");

                if (pics != null) {
                    i.putExtra("pics", (Serializable) pics);
                }

                startActivityForResult(i, PublishedActivity.ATERSHOUPIC);

            }
        });

        btn_ershou_submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                at.setUsername(userinfo.getUsername());
                at.setModelid(userinfo.getModelid());
                at.setUserid(userinfo.getUserid());
                at.setProvince("1");

                // 求区域id
                // for (int i = 0; i < minarea.size(); i++) {
                // String area = minarea.get(i).getName();
                // Log.e("area", "area=" + area + "   " + MINAREA);
                //
                // if (area.equals(MINAREA.trim())) {
                // ATershouSubmitFragment.this.area1 = minarea.get(i).getId() +
                // "";
                // Log.e("area1", "area1=" + area1 + "   " + MINAREA);
                // break;
                // } else {
                // Log.e("xxxx--area1", "xxx---area1=" + area1 + "   " +
                // MINAREA);
                // }
                // }

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

//					for (int i = 0; i < minarea.size(); i++) {
//						if (areA.equals(minarea.get(i).getName())) {
//							areapos = minarea.get(i).getId();
//						}
//					}
                    for (Street i : Street.streets) {
                        if (areA.equals(i.getName())) {
                            areapos = i.getId();
                        }
                    }

                    at.setCity(citypos);
                    at.setArea(areapos);
                }

                at.setDanyuan(et_ershou_danyuan.getText().toString() + "");
                at.setXiaoquname(tv_ershou_xiaoquname.getText().toString());
                at.setJingweidu(tv_ershou_jingweidu.getText().toString());
                at.setZongjia(et_ershou_housrprice.getText().toString());
                at.setTitle(tv_ershou_biaoti.getText().toString());
                at.setDesc(tv_ershou_houseDesc.getText().toString());
                at.setFangling(et_ershou_fangling.getText().toString());
                at.setJianzhumianji(et_ershou_jianzhumianji.getText()
                        .toString());
                // 以上必传
                at.setLongdong(et_ershou_loudong.getText().toString());
                at.setMenpai(et_ershou_menpai.getText().toString());
                at.setTaoneimianji(et_ershou_taoneimianji.getText().toString());
                //
                at.setLouceng(tv_ershou_louceng.getText().toString());

                at.setPic(pics);

                // 楼层，所在层与总层
                if (tv_ershou_louceng.getText().toString().length() > 0) {

                    if (!tv_ershou_louceng.getText().toString().startsWith("层")) {
                        String[] c = tv_ershou_louceng.getText().toString()
                                .split(" ");
                        String c1 = c[0].split("层")[0];
                        String z1 = c[1].split("层")[0].split("共")[1];
                        at.setCurceng(c1);
                        at.setZongceng(z1);
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

                        at.setCurceng(c1);
                        at.setZongceng(z1);
                    }
                } else {
                    at.setCurceng("");
                    at.setZongceng("");
                }

                // 户型
                at.setHuxing(tv_ershou_huType.getText().toString());
                if (tv_ershou_huType.getText().toString().length() > 0) {

                    String h = tv_ershou_huType.getText().toString();
                    String shi = h.split(" ")[0].split("室")[0];
                    String ting = h.split(" ")[1].split("厅")[0];
                    String wei = h.split(" ")[2].split("卫")[0];

                    at.setShi(shi);
                    at.setTing(ting);
                    at.setWei(wei);
                } else {
                    at.setShi("");
                    at.setTing("");
                    at.setWei("");
                }
                // 房屋属性
                if (tv_ershou_houseSX.getText().toString().length() > 0 && tv_ershou_houseSX.getText().toString().split(" ") != null) {
                    if (tv_ershou_houseSX.getText().toString().split(" ").length > 2) {
                        String s = tv_ershou_houseSX.getText().toString();
                        String jiegou = s.split(" ")[0];
                        String zhuangxiu = s.split(" ")[1];
                        String chaoxiang = s.split(" ")[2];
                        at.setJiegou(jiegou);
                        at.setZhuangxiu(zhuangxiu);
                        at.setChaoxiang(chaoxiang);
                    } else {
                        at.setJiegou("");
                        at.setZhuangxiu("");
                        at.setChaoxiang("");
                    }
                } else {
                    at.setJiegou("");
                    at.setZhuangxiu("");
                    at.setChaoxiang("");
                }

                at.setPic(pics);
                at.setLoucengshuxing(tv_ershou_loucengmenu.getText().toString());
                at.setWuyetype(tv_ershou_wuyeType.getText().toString());
                at.setDiyaxinxi(tv_ershou_diyaxinxi.getText().toString());

                at.setHouseshuxing(tv_ershou_houseSX.getText().toString());
                //
                at.setJianzhutype(tv_ershou_jianzhuType.getText().toString());
                at.setJianzhujiegou(tv_ershou_jianzhuJiegou.getText()
                        .toString());
                at.setTihubili(tv_ershou_tihubili.getText().toString());
                at.setHouseuse(tv_ershou_houseUse.getText().toString());
                at.setChanquansuoshu(tv_ershou_chanquansuoshu.getText()
                        .toString());
                at.setShifoudianti(tv_ershou_SfDianti.getText().toString());
                at.setWeiyizhuzhai(tv_ershou_weiyizhuzhai.getText().toString());
                at.setGuapaishijian(tv_ershou_guapaitime.getText().toString());
                at.setShangcijiaoyi(tv_ershou_shangcijiaoyi.getText().toString());
                at.setBianqian(tv_ershou_biaoqian.getText().toString());
                at.setDitieline(tv_ershou_ditieline.getText().toString());
                //
                at.setTouzifenxi(tv_ershou_touzifenxi.getText().toString());
                at.setHuxingjieshao(tv_ershou_huxingjieshao.getText()
                        .toString());
                at.setXiaoqujieshao(tv_ershou_xiaoqujieshao.getText()
                        .toString());
                at.setShuifeijiexi(tv_ershou_shuifeijiexi.getText().toString());
                at.setZhuangxiumiaoshu(tv_ershou_zhuangxiumiaoshu.getText()
                        .toString());
                at.setZhoubianpeitao(tv_ershou_zhoubianpeitao.getText()
                        .toString());
                at.setJiaoyupeitao(tv_ershou_jiaoyupeitao.getText().toString());
                at.setJiaotongchuxing(tv_ershou_jiaotongchuxing.getText()
                        .toString());
                at.setHexinmaidian(tv_ershou_hexinmaidian.getText().toString());
                at.setXiaoquyoushi(tv_ershou_xiaoquyoushi.getText().toString());
                at.setQuanshudiya(tv_ershou_quanshudiya.getText().toString());
                at.setYezhushuo(tv_ershou_tuijianliyou.getText().toString());

                if (!tv_ershou_housearea.getText().toString().equals("")
                        && !tv_ershou_xiaoquname.getText().toString()
                        .equals("")
                        && !tv_ershou_jingweidu.getText().toString().equals("")
                        && !et_ershou_housrprice.getText().toString()
                        .equals("")
                        && !tv_ershou_biaoti.getText().toString().equals("")
                        && !tv_ershou_houseDesc.getText().toString().equals("")
                        && !et_ershou_fangling.getText().toString().equals("")
                        && !et_ershou_jianzhumianji.getText().toString()
                        .equals("")) {

                    Log.e("xxxxxx", "at========" + at.toString());

                    if (n == null) {
                        presenter.ATershousubmit(at);
                    } else {
                        at.setId(n.getId());
                        editPresenter.ershouedit(at);
                    }

                } else {
                    MyToastShowCenter.CenterToast(getActivity(), "还有必填项未填！");
                }

            }
        });

        ll_back_agentershousubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (weituoedit != null) {
                    Fragment fragment = new WeiTuoGuanLiFragment();
                    FragmentTransaction trans = getActivity()
                            .getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.fl_agent_fragment, fragment);
                    trans.commitAllowingStateLoss();
                } else {
                    Fragment fragment = new ATershouListFragment();
                    FragmentTransaction trans = getActivity()
                            .getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.fl_agent_fragment, fragment);
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

        tv_ershou_jingweidu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), GetLocationActivity.class);
                startActivityForResult(i, LOCATION);
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

        tv_ershou_wuyeType.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerOne(WheelpickerData.WUYETYPE, tv_ershou_wuyeType,
                        "请选择物业性质");
            }
        });

        tv_ershou_shangcijiaoyi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerDate(tv_ershou_shangcijiaoyi, "请选择上次交易时间");
            }
        });

        tv_ershou_diyaxinxi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerOne(WheelpickerData.DIYAXINXI, tv_ershou_diyaxinxi,
                        "请选择抵押信息");
            }
        });

        tv_ershou_huType.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerThree(WheelpickerData.HUTYPE1,
                        WheelpickerData.HUTYPE2, WheelpickerData.HUTYPE3,
                        tv_ershou_huType, "请选择户型");

            }
        });

        tv_ershou_houseSX.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerThree(WheelpickerData.HOUSESX1,
                        WheelpickerData.HOUSESX2, WheelpickerData.HOUSESX3,
                        tv_ershou_houseSX, "请选择房屋属性");
            }
        });
        //
        tv_ershou_jianzhuType.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerOne(WheelpickerData.JIANZHUTYPE,
                        tv_ershou_jianzhuType, "请选择建筑类型");
            }
        });
        tv_ershou_jianzhuJiegou.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerOne(WheelpickerData.JIANZHUJIEGOU,
                        tv_ershou_jianzhuJiegou, "请选择建筑结构");
            }
        });

        tv_ershou_tihubili.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerOne(WheelpickerData.TIHUBILI, tv_ershou_tihubili,
                        "请选择梯户比例");
            }
        });
        tv_ershou_houseUse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerOne(WheelpickerData.HOUSEUSE, tv_ershou_houseUse,
                        "请选择房屋用途");
            }
        });
        tv_ershou_chanquansuoshu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerOne(WheelpickerData.CHANQUANSUOSHU,
                        tv_ershou_chanquansuoshu, "请选择产权所属");
            }
        });
        tv_ershou_SfDianti.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerOne(WheelpickerData.SFDIANTI, tv_ershou_SfDianti,
                        "请选择是否有电梯");
            }
        });
        tv_ershou_weiyizhuzhai.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerOne(WheelpickerData.WEIYIZHUZHAI,
                        tv_ershou_weiyizhuzhai, "请选择是否唯一住宅");
            }
        });

        tv_ershou_guapaitime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WheelPickerDate(tv_ershou_guapaitime, "请选择挂牌时间");

            }
        });
        tv_ershou_biaoqian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_ershou_biaoqian.setText("");

                SIXCheckDialog(WheelpickerData.BIAOQIAN1,
                        WheelpickerData.BIAOQIAN2, WheelpickerData.BIAOQIAN3,
                        WheelpickerData.BIAOQIAN4, WheelpickerData.BIAOQIAN5,
                        WheelpickerData.BIAOQIAN6, tv_ershou_biaoqian, "请选择标签");
            }
        });

        tv_ershou_ditieline.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_ershou_ditieline.setText("");

                EightCheckDialog(WheelpickerData.DITIELINE1,
                        WheelpickerData.DITIELINE2, WheelpickerData.DITIELINE3,
                        WheelpickerData.DITIELINE4, WheelpickerData.DITIELINE5,
                        WheelpickerData.DITIELINE6, WheelpickerData.DITIELINE7,
                        WheelpickerData.DITIELINE8, tv_ershou_ditieline,
                        "请选择地铁线");
            }
        });

        tv_ershou_biaoti.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String biaoti = tv_ershou_biaoti.getText().toString();
                i.putExtra("S1", biaoti);
                i.putExtra("X", 1);
                startActivityForResult(i, BIAOTI);
            }
        });

        tv_ershou_houseDesc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_houseDesc.getText().toString();
                i.putExtra("S2", houseDesc);
                i.putExtra("X", 2);
                startActivityForResult(i, FANGYUANMIAOSHU);
            }
        });

        tv_ershou_touzifenxi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_touzifenxi.getText().toString();
                i.putExtra("S3", houseDesc);
                i.putExtra("X", 3);
                startActivityForResult(i, TOUZIFENXI);
            }
        });

        tv_ershou_huxingjieshao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_huxingjieshao.getText().toString();
                i.putExtra("S4", houseDesc);
                i.putExtra("X", 4);
                startActivityForResult(i, HUXINGJIESHAO);
            }
        });

        tv_ershou_xiaoqujieshao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_xiaoqujieshao.getText().toString();
                i.putExtra("S5", houseDesc);
                i.putExtra("X", 5);
                startActivityForResult(i, XIAOQUJIESHAO);
            }
        });
        tv_ershou_shuifeijiexi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_shuifeijiexi.getText().toString();
                i.putExtra("S6", houseDesc);
                i.putExtra("X", 6);
                startActivityForResult(i, SHUIFEIJIEXI);
            }
        });
        tv_ershou_zhuangxiumiaoshu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_zhuangxiumiaoshu.getText()
                        .toString();
                i.putExtra("S7", houseDesc);
                i.putExtra("X", 7);
                startActivityForResult(i, ZHUANGXIUMIAOSHU);
            }
        });
        tv_ershou_zhoubianpeitao.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_zhoubianpeitao.getText()
                        .toString();
                i.putExtra("S8", houseDesc);
                i.putExtra("X", 8);
                startActivityForResult(i, ZHOUBIANPEITAO);

            }
        });

        tv_ershou_jiaoyupeitao.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_jiaoyupeitao.getText().toString();
                i.putExtra("S9", houseDesc);
                i.putExtra("X", 9);
                startActivityForResult(i, JIAOYUPEITAO);
            }
        });

        tv_ershou_jiaotongchuxing.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_jiaotongchuxing.getText()
                        .toString();
                i.putExtra("S10", houseDesc);
                i.putExtra("X", 10);
                startActivityForResult(i, JIAOTONGCHUXING);

            }
        });

        tv_ershou_hexinmaidian.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_hexinmaidian.getText().toString();
                i.putExtra("S11", houseDesc);
                i.putExtra("X", 11);
                startActivityForResult(i, HEXINMAIDIAN);
            }
        });

        tv_ershou_xiaoquyoushi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_xiaoquyoushi.getText().toString();
                i.putExtra("S12", houseDesc);
                i.putExtra("X", 12);
                startActivityForResult(i, XIAOQUYOUSHI);
            }
        });

        tv_ershou_quanshudiya.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_quanshudiya.getText().toString();
                i.putExtra("S13", houseDesc);
                i.putExtra("X", 13);
                startActivityForResult(i, QUANSHUDIYA);
            }
        });

        tv_ershou_tuijianliyou.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),
                        SubmitEdittextActivity.class);
                String houseDesc = tv_ershou_tuijianliyou.getText().toString();
                i.putExtra("S14", houseDesc);
                i.putExtra("X", 14);
                startActivityForResult(i, TUIJIANLIYOU);

            }
        });

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
        //
        // picker1.setStyle(WheelPicker.STRAIGHT);
        // picker2.setStyle(WheelPicker.STRAIGHT);
        // picker3.setStyle(WheelPicker.STRAIGHT);

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
                    ATershouSubmitFragment.this.pid = "2";
                } else if (data.equals("福田区")) {
                    ATershouSubmitFragment.this.pid = "4";
                } else if (data.equals("南山区")) {
                    ATershouSubmitFragment.this.pid = "5";
                } else if (data.equals("盐田区")) {
                    ATershouSubmitFragment.this.pid = "6";
                } else if (data.equals("宝安区")) {
                    ATershouSubmitFragment.this.pid = "7";
                } else if (data.equals("龙岗新区")) {
                    ATershouSubmitFragment.this.pid = "8";
                } else if (data.equals("龙华新区")) {
                    ATershouSubmitFragment.this.pid = "9";
                } else if (data.equals("光明新区")) {
                    ATershouSubmitFragment.this.pid = "10";
                } else if (data.equals("坪山新区")) {
                    ATershouSubmitFragment.this.pid = "11";
                } else if (data.equals("大鹏新区")) {
                    ATershouSubmitFragment.this.pid = "12";
                } else if (data.equals("东莞")) {
                    ATershouSubmitFragment.this.pid = "13";
                } else {
                    ATershouSubmitFragment.this.pid = "14";
                }

                MinArea.clear();
                List<String> list = Street.getSubArea(ATershouSubmitFragment.this.pid);
                if (list != null && list.size() > 0) {
                    MinArea.addAll(list);
                    MinArea.add(0, "");
                    MinArea.add("");
                }
                picker3.setData(MinArea);

//				model2.address(pid, new AsycnCallBack() {
//					@Override
//					public void onSuccess(Object success) {
//						ATershouSubmitFragment.this.minarea = (List<Address>) success;
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
                ATershouSubmitFragment.this.MINAREA = " " + data;
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
                        ATershouSubmitFragment.this.area1 = Street.streets.get(i)
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
     * 地图返回坐标
     */
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
                            ATershouSubmitFragment.this.PoiString = result
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

                case BIAOTI:
                    String str1 = data.getStringExtra("M1");
                    Log.e("BIAOTI", "BIAOTI=" + str1);
                    tv_ershou_biaoti.setText(data.getStringExtra("M1"));
                    break;
                case FANGYUANMIAOSHU:
                    String str2 = data.getStringExtra("M2");
                    tv_ershou_houseDesc.setText(str2);
                    break;
                case HEXINMAIDIAN:
                    String str11 = data.getStringExtra("M11");
                    tv_ershou_hexinmaidian.setText(str11);
                    break;
                case HUXINGJIESHAO:
                    String str4 = data.getStringExtra("M4");
                    tv_ershou_huxingjieshao.setText(str4);
                    break;
                case JIAOTONGCHUXING:
                    String str10 = data.getStringExtra("M10");
                    tv_ershou_jiaotongchuxing.setText(str10);
                    break;
                case JIAOYUPEITAO:
                    String str9 = data.getStringExtra("M9");
                    tv_ershou_jiaoyupeitao.setText(str9);
                    break;
                case QUANSHUDIYA:
                    String str13 = data.getStringExtra("M13");
                    tv_ershou_quanshudiya.setText(str13);
                    break;
                case SHUIFEIJIEXI:
                    String str6 = data.getStringExtra("M6");
                    tv_ershou_shuifeijiexi.setText(str6);
                    break;
                case TOUZIFENXI:
                    String str3 = data.getStringExtra("M3");
                    tv_ershou_touzifenxi.setText(str3);
                    break;
                case TUIJIANLIYOU:
                    String str14 = data.getStringExtra("M14");
                    tv_ershou_tuijianliyou.setText(str14);
                    break;
                case XIAOQUJIESHAO:
                    String str5 = data.getStringExtra("M5");
                    tv_ershou_xiaoqujieshao.setText(str5);
                    break;
                case XIAOQUYOUSHI:
                    String str12 = data.getStringExtra("M12");
                    tv_ershou_xiaoquyoushi.setText(str12);
                    break;

                case ZHOUBIANPEITAO:
                    String str8 = data.getStringExtra("M8");
                    tv_ershou_zhoubianpeitao.setText(str8);
                    break;

                case ZHUANGXIUMIAOSHU:
                    String str7 = data.getStringExtra("M7");
                    tv_ershou_zhuangxiumiaoshu.setText(str7);
                    break;
                case BACK:

                    break;
                case XIAOQUNAME:
                    String xiaoquname = data.getStringExtra("XIAOQUNAME");
                    tv_ershou_xiaoquname.setText(xiaoquname);
                    break;
                case PublishedActivity.ATERSHOUPIC:
                    if (pics == null)
                        pics = new ArrayList<Pics>();
                    pics.clear();
                    this.pics = (List<Pics>) data
                            .getSerializableExtra("uploadpics");
                    tv_ershou_uploadPic.setText("已经上传" + pics.size() + "张");
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
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
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

    /**
     * @param Data  数据源，需要展示的数据
     * @param tv    确认后，所需要显示的什么空间上
     * @param title 中间显示条目的内容
     */
    public void WheelPickerThree(List<String> Data1, List<String> Data2,
                                 List<String> Data3, final TextView tv, String title) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.alerlog_wheelpicker_tingshi);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        WheelPicker picker1 = (WheelPicker) window
                .findViewById(R.id.zulin_wheel1);
        WheelPicker picker2 = (WheelPicker) window
                .findViewById(R.id.zulin_wheel2);
        WheelPicker picker3 = (WheelPicker) window
                .findViewById(R.id.zulin_wheel3);

        TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
        TextView tvCancle = (TextView) window
                .findViewById(R.id.tv_wheel_cancle);
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

    /**
     * 标签，与地铁线
     *
     * @param Data  数据源，需要展示的数据
     * @param tv    确认后，所需要显示的什么空间上
     * @param title 中间显示条目的内容
     */
    public void EightCheckDialog(String Data1, String Data2, String Data3,
                                 String Data4, String Data5, String Data6, String Data7,
                                 String Data8, final TextView tv, String title) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.alerlog_check_bqdtl2);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
        TextView tvCancle = (TextView) window
                .findViewById(R.id.tv_wheel_cancle);
        TextView tvTitle = (TextView) window.findViewById(R.id.tv_wheel_title);

        tvTitle.setText(title);

        final CheckBox cb1 = (CheckBox) window.findViewById(R.id.check_1);
        final CheckBox cb2 = (CheckBox) window.findViewById(R.id.check_2);
        final CheckBox cb3 = (CheckBox) window.findViewById(R.id.check_3);
        final CheckBox cb4 = (CheckBox) window.findViewById(R.id.check_4);
        final CheckBox cb5 = (CheckBox) window.findViewById(R.id.check_5);
        final CheckBox cb6 = (CheckBox) window.findViewById(R.id.check_6);
        final CheckBox cb7 = (CheckBox) window.findViewById(R.id.check_7);
        final CheckBox cb8 = (CheckBox) window.findViewById(R.id.check_8);

        cb1.setText(Data1);
        cb2.setText(Data2);
        cb3.setText(Data3);
        cb4.setText(Data4);
        cb5.setText(Data5);
        cb6.setText(Data6);
        cb7.setText(Data7);
        cb8.setText(Data8);

        tcb1 = "";
        tcb2 = "";
        tcb3 = "";
        tcb4 = "";
        tcb5 = "";
        tcb6 = "";
        tcb7 = "";
        tcb8 = "";

        cb8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb8 = "," + "[11]";//(String) buttonView.getText().toString();
                } else {
                    tcb8 = "";
                }
            }
        });
        cb7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb7 = "," + "[9]";//(String) buttonView.getText().toString();
                } else {
                    tcb7 = "";
                }
            }
        });
        cb6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb6 = "," + "[7]";//(String) buttonView.getText().toString();
                } else {
                    tcb6 = "";
                }
            }
        });
        cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb1 = "," + "[1]";//(String) buttonView.getText().toString();
                } else {
                    tcb1 = "";
                }
            }
        });
        cb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb2 = "," + "[2]";//(String) buttonView.getText().toString();
                } else {
                    tcb2 = "";
                }
            }
        });
        cb3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb3 = "," + "[3]";//(String) buttonView.getText().toString();
                } else {
                    tcb3 = "";
                }
            }
        });
        cb4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb4 = "," + "[4]";//(String) buttonView.getText().toString();
                } else {
                    tcb4 = "";
                }
            }
        });
        cb5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb5 = "," + "[5]";//(String) buttonView.getText().toString();
                } else {
                    tcb5 = "";
                }
            }
        });

        tvSure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String cb = tcb1 + tcb2 + tcb3 + tcb4 + tcb5 + tcb6 + tcb7
                        + tcb8;

                if (cb.startsWith(",")) {
                    String tx = cb.substring(1);
                    tv.setText(tx.toString());
                } else {
                    String tx = cb;
                    tv.setText(tx.toString());
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

    public void SIXCheckDialog(String Data1, String Data2, String Data3,
                               String Data4, String Data5, String Data6, final TextView tv,
                               String title) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.alerlog_check_bqdtl);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
        TextView tvCancle = (TextView) window
                .findViewById(R.id.tv_wheel_cancle);
        TextView tvTitle = (TextView) window.findViewById(R.id.tv_wheel_title);

        tvTitle.setText(title);

        final CheckBox cb1 = (CheckBox) window.findViewById(R.id.check_1);
        final CheckBox cb2 = (CheckBox) window.findViewById(R.id.check_2);
        final CheckBox cb3 = (CheckBox) window.findViewById(R.id.check_3);
        final CheckBox cb4 = (CheckBox) window.findViewById(R.id.check_4);
        final CheckBox cb5 = (CheckBox) window.findViewById(R.id.check_5);
        final CheckBox cb6 = (CheckBox) window.findViewById(R.id.check_6);

        cb1.setText(Data1);
        cb2.setText(Data2);
        cb3.setText(Data3);
        cb4.setText(Data4);
        cb5.setText(Data5);
        cb6.setText(Data6);

        tcb1 = "";
        tcb2 = "";
        tcb3 = "";
        tcb4 = "";
        tcb5 = "";
        tcb6 = "";

        cb6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb6 = "," + (String) buttonView.getText().toString();
                } else {
                    tcb6 = "";
                }
            }
        });
        cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb1 = "," + (String) buttonView.getText().toString();
                } else {
                    tcb1 = "";
                }
            }
        });
        cb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb2 = "," + (String) buttonView.getText().toString();
                } else {
                    tcb2 = "";
                }
            }
        });
        cb3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb3 = "," + (String) buttonView.getText().toString();
                } else {
                    tcb3 = "";
                }
            }
        });
        cb4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb4 = "," + (String) buttonView.getText().toString();
                } else {
                    tcb4 = "";
                }
            }
        });
        cb5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tcb5 = "," + (String) buttonView.getText().toString();
                } else {
                    tcb5 = "";
                }
            }
        });

        tvSure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String cb = tcb1 + tcb2 + tcb3 + tcb4 + tcb5 + tcb6;

                if (cb.startsWith(",")) {
                    String tx = cb.substring(1);
                    tv.setText(tx.toString());
                } else {
                    String tx = cb;
                    tv.setText(tx.toString());
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
    public void WheelPickerDate(final TextView tv, String title) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.alerlog_wheel_guapaitime);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        WheelDatePicker picker = (WheelDatePicker) window
                .findViewById(R.id.Date_wheel);

        TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
        TextView tvCancle = (TextView) window
                .findViewById(R.id.tv_wheel_cancle);
        TextView tvTitle = (TextView) window.findViewById(R.id.tv_wheel_title);
        tvTitle.setText(title);

        picker.setOnWheelChangeListener(new OnWheelChangeListener() {
            @Override
            public void onWheelSelected(int index, String data) {
                time = data;
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
                tv.setText(time);
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
    public void ATershou(final String info) {
        MyToastShowCenter.CenterToast(getActivity(), info);

        if (info.equals("发布成功")) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    Fragment fragment = new ATershouListFragment();
                    FragmentTransaction trans = getActivity()
                            .getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.fl_agent_fragment, fragment);
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
                    if (weituoedit != null) {
                        Fragment fragment = new WeiTuoGuanLiFragment();
                        FragmentTransaction trans = getActivity()
                                .getSupportFragmentManager().beginTransaction();
                        trans.replace(R.id.fl_agent_fragment, fragment);
                        trans.commitAllowingStateLoss();
                    } else {
                        Fragment fragment = new ATershouListFragment();
                        FragmentTransaction trans = getActivity()
                                .getSupportFragmentManager().beginTransaction();
                        trans.replace(R.id.fl_agent_fragment, fragment);
                        trans.commitAllowingStateLoss();
                    }

                }
            }, 1000);
        }
    }

}
