package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dumu.housego.ErShouFangMainActivity.SpinnerAdapter;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.activity.MainActivity;
import com.dumu.housego.adapter.RentingRecommendAdapter;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.RentingRecommendData;
import com.dumu.housego.entity.SpinnerData;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.RentingProgramaModel;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.presenter.RentingProgramaPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IRentingProgramaView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class RentingMainActivity extends BaseActivity implements View.OnClickListener, IRentingProgramaView, OnRefreshListener2<ListView> {
    private LinearLayout llRentingBack;
    private RentingRecommendAdapter adapter;
    private IFourDataProgramePresenter presenter;
    private FourDataPrograma fourdata;
    private PullToRefreshListView lvRenting;
    private List<RentingRecommendData> lastrentings = new ArrayList<RentingRecommendData>();
    int page = 1;
    private RentingProgramaModel model = new RentingProgramaModel();
    private TextView rentingSpinner1, rentingSpinner2, rentingSpinner3, rentingSpinner4, tv_renting_search;
    private String city = "2";
    private String area;
    private List<Address> minarea;
    private List<String> MinArea = new ArrayList<String>();
    private ImageView iv_location, iv_MsM;
    private int posi;
    private PopupWindow QuYupop;
    private LinearLayout llpopupSpinnerQuyu;
    protected List<String> SpinnerQuYu = new ArrayList<String>();
    protected SpinnerAdapter spinneradapter1;
    private AddressModel addmodel = new AddressModel();
    private LinearLayout ll_renting_spinner;
    private PopupWindow Tpop;
    private LinearLayout llpopupSpinner2, ll_renting_search;
    private SpinnerAdapter spinneradapter;
    private PopupWindow pop;
    private LinearLayout ll_popup_spinner;
    private PopupWindow Mpop;
    private LinearLayout llpopupSpinnerMore;
    private String Kwds;

    private SparseArray<Integer> filterArray = new SparseArray<Integer>();
    private SparseArray<Integer> filterArrayTmp = new SparseArray<Integer>();
    private static int SEC_CAOXIANG = 1;
    private static int SEC_XIAOQU = 2;
    private static int SEC_MIANJI = 3;
    private static int SEC_BIAOQIAN = 4;
    private static int SEC_LOUCENG = 5;
    private static int SEC_WUYE = 6;
    private static int SEC_FANGSHI = 7;
    private static int SEC_LEIXING = 8;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renting_main);
        FontHelper.injectFont(findViewById(android.R.id.content));
        setViews();
        showPopWindowQuyu();
        showPopWindowprice();
        showPopWindowType();
        showPopWindowMore();
        setListener();

        presenter = new RentingProgramaPresenter(this);
        fourdata = new FourDataPrograma();
        fourdata.setCatid("8");
        fourdata.setPage("1");
        fourdata.setCt("");
        fourdata.setAr("");
        fourdata.setDt("");
        fourdata.setZj("");
        fourdata.setShi("");

        fourdata.setMj("");
        fourdata.setQs("");
        fourdata.setYt("");
        fourdata.setXq("");
        fourdata.setCx("");
        fourdata.setLc("");
        fourdata.setZl("");
        fourdata.setZx("");
        fourdata.setKf("");
        fourdata.setKwds("");

        tv_renting_search.setText("请输入小区或商圈名");
        if (getIntent().getStringExtra("search") != null) {
            this.Kwds = getIntent().getStringExtra("search");
            fourdata.setKwds(Kwds);
            tv_renting_search.setText(Kwds);
        }
        presenter.LoadProgrameData(fourdata);
    }

    private void setListener() {
        iv_location.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapHouseMainActivity.class));
            }
        });

        iv_MsM.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("page", "1");
                startActivity(i);
            }
        });

        ll_renting_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SearchMainActivity.class);
                i.putExtra("TAG", "renting");
                startActivity(i);

            }
        });

        llRentingBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                RentingMainActivity.this.finish();
            }
        });

        lvRenting.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String catid = lastrentings.get(position - 1).getCatid();
                String Id = lastrentings.get(position - 1).getId();
                Intent i = new Intent(getApplicationContext(), RentingDetailActivity.class);
                i.putExtra("catid", catid);
                i.putExtra("id", Id);
                startActivity(i);
            }
        });

        rentingSpinner1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animation anim = AnimationUtils.loadAnimation(RentingMainActivity.this, R.anim.activity_translate_out);
                //llpopupSpinnerQuyu.
                //        setAnimation(anim);
                QuYupop.showAsDropDown(ll_renting_spinner, 0, 0);
            }
        });

        rentingSpinner2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animation anim = AnimationUtils.loadAnimation(RentingMainActivity.this, R.anim.activity_translate_out);
                //llpopupSpinner2.
                //        setAnimation(anim);
                Tpop.showAsDropDown(ll_renting_spinner, 0, 0);
            }
        });
        rentingSpinner3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animation anim = AnimationUtils.loadAnimation(RentingMainActivity.this, R.anim.activity_translate_out);
                //ll_popup_spinner.
                //        setAnimation(anim);
                pop.showAsDropDown(ll_renting_spinner, 0, 0);
            }
        });
        rentingSpinner4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animation anim = AnimationUtils.loadAnimation(RentingMainActivity.this, R.anim.activity_translate_out);
                //llpopupSpinnerMore.
                //        setAnimation(anim);
                filterArrayTmp.clear();
                filterArrayTmp = filterArray.clone();
                refreshMoreFilterUI();
                Mpop.showAsDropDown(ll_renting_spinner, 0, 0);
            }
        });
    }

    private void setViews() {
        tv_renting_search = (TextView) findViewById(R.id.tv_renting_search);
        ll_renting_search = (LinearLayout) findViewById(R.id.ll_renting_search);

        iv_location = (ImageView) findViewById(R.id.iv_location);
        iv_MsM = (ImageView) findViewById(R.id.iv_MsM);


        llRentingBack = (LinearLayout) findViewById(R.id.ll_renting_back);
        lvRenting = (PullToRefreshListView) findViewById(R.id.lv_rentinglist);
        lvRenting.setMode(PullToRefreshBase.Mode.BOTH);
        lvRenting.setOnRefreshListener(this);

        ll_renting_spinner = (LinearLayout) findViewById(R.id.ll_renting_spinner);

        rentingSpinner1 = (TextView) findViewById(R.id.renting_quyu_sp1);
        rentingSpinner2 = (TextView) findViewById(R.id.renting_quyu_sp2);
        rentingSpinner3 = (TextView) findViewById(R.id.renting_quyu_sp3);
        rentingSpinner4 = (TextView) findViewById(R.id.renting_quyu_sp4);
    }

    public void showData(List<RentingRecommendData> ershoufangrecommends) {
        //清空数据
        this.lastrentings.clear();
        if (ershoufangrecommends != null && ershoufangrecommends.size() > 0) {
            this.lastrentings.addAll(ershoufangrecommends);
            MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastrentings.get(0).getZonghe() + "个房源！");
        } else {
            MyToastShowCenter.CenterToast(getApplicationContext(), "找到了0个房源！");
        }
        adapter = new RentingRecommendAdapter(lastrentings, getApplicationContext());
        lvRenting.setAdapter(adapter);
    }

    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        page = 1;
        fourdata.setCatid("8");
        fourdata.setPage(page + "");
        model.GetRecommedHouse(fourdata, new AsycnCallBack() {
            public void onSuccess(Object success) {
                List<RentingRecommendData> ershous = (List<RentingRecommendData>) success;

                //清空数据
                lastrentings.clear();

                if (ershous != null && ershous.size() > 0) {
                    lastrentings.addAll(ershous);
                    MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastrentings.get(0).getZonghe() + "个房源！");
                } else {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "找到了0个房源！");
                }
                // 刷新界面
                adapter.notifyDataSetChanged();
                // 关闭上拉加载刷新布局
                lvRenting.onRefreshComplete();
            }

            public void onError(Object error) {
                MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
                lvRenting.onRefreshComplete();
            }
        });
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        page = page + 1;
        fourdata.setCatid("8");
        fourdata.setPage(page + "");
        model.GetRecommedHouse(fourdata, new AsycnCallBack() {
            public void onSuccess(Object success) {
                List<RentingRecommendData> ershous = (List<RentingRecommendData>) success;
                if (ershous == null) {
                    lvRenting.onRefreshComplete();
                    MyToastShowCenter.CenterToast(getApplicationContext(), "已经拉到底部，没有更多的数据了。。。");
                } else {
                    // 将数据追加到集合中
                    lastrentings.addAll(ershous);
                    // 刷新界面
                    adapter.notifyDataSetChanged();
                    // 关闭上拉加载刷新布局
                    lvRenting.onRefreshComplete();
//				MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastrentings.size() + "个房源！");
                }
            }

            public void onError(Object error) {
                MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
                lvRenting.onRefreshComplete();
            }
        });
    }

    /**
     * spinner
     */
    private void showPopWindowType() {
        pop = new PopupWindow(RentingMainActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwin_spinner, null);

        ll_popup_spinner = (LinearLayout) view.findViewById(R.id.ll_popup_spinner);
        pop.setWidth(LayoutParams.MATCH_PARENT);
        pop.setHeight(LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner);
        ListView lvspinner1 = (ListView) view.findViewById(R.id.lv_popwin_list);
        spinneradapter = new SpinnerAdapter(SpinnerData.HouseType, getApplicationContext());
        lvspinner1.setAdapter(spinneradapter);

        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pop.dismiss();
                //ll_popup_spinner.clearAnimation();
            }
        });

        lvspinner1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    rentingSpinner3.setText("房型");
                    fourdata.setShi("");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 6) {
                    rentingSpinner3.setText("5室以上");
                    fourdata.setShi("6");
                    presenter.LoadProgrameData(fourdata);
                } else {
                    String shi = SpinnerData.HouseType.get(position).split("室")[0];

                    rentingSpinner3.setText(SpinnerData.HouseType.get(position));
                    fourdata.setShi(shi);
                    presenter.LoadProgrameData(fourdata);
                }
                pop.dismiss();
                //ll_popup_spinner.clearAnimation();
            }
        });
    }

    /**
     * spinner
     */
    private void showPopWindowprice() {
        Tpop = new PopupWindow(RentingMainActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwin_spinner2, null);

        llpopupSpinner2 = (LinearLayout) view.findViewById(R.id.ll_popup_spinner2);
        Tpop.setWidth(LayoutParams.MATCH_PARENT);
        Tpop.setHeight(LayoutParams.MATCH_PARENT);
        Tpop.setBackgroundDrawable(new BitmapDrawable());
        Tpop.setFocusable(true);
        Tpop.setOutsideTouchable(true);
        Tpop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner2);
        ListViewForScrollView lvspinner1 = (ListViewForScrollView) view.findViewById(R.id.lv_popwin_list2);
        final EditText etlow = (EditText) view.findViewById(R.id.ershou_lowprice_et);
        final EditText ethigh = (EditText) view.findViewById(R.id.ershou_highprice_et);
        TextView tvlowhigh = (TextView) view.findViewById(R.id.tv_low_high);
        TextView tv_spinner_jiage = (TextView) view.findViewById(R.id.tv_spinner_jiage);

        tv_spinner_jiage.setText("元");

        tvlowhigh.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String low = etlow.getText().toString();
                String high = ethigh.getText().toString();
                if (low.equals("") && !high.equals("")) {
                    fourdata.setZj("0-" + high);
                    presenter.LoadProgrameData(fourdata);
                    Tpop.dismiss();
                    //llpopupSpinner2.clearAnimation();
                } else if (!low.equals("") && high.equals("")) {
                    fourdata.setZj(low + "-");
                    presenter.LoadProgrameData(fourdata);
                    Tpop.dismiss();
                    //llpopupSpinner2.clearAnimation();
                } else if (!low.equals("") && !high.equals("")) {
                    int l = Integer.valueOf(low);
                    int h = Integer.valueOf(high);

                    if (l > h) {
                        MyToastShowCenter.CenterToast(getApplicationContext(), "最高价必须大于最低价！");
                    } else if (l == h) {
                        fourdata.setZj(low);
                        presenter.LoadProgrameData(fourdata);
                        Tpop.dismiss();
                        //llpopupSpinner2.clearAnimation();
                    } else {
                        fourdata.setZj(low + "-" + high);
                        presenter.LoadProgrameData(fourdata);
                        Tpop.dismiss();
                        //llpopupSpinner2.clearAnimation();
                    }
                }
                //
                ethigh.setText("");
                etlow.setText("");

            }
        });


        spinneradapter = new SpinnerAdapter(SpinnerData.ZuJin, getApplicationContext());
        lvspinner1.setAdapter(spinneradapter);

        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Tpop.dismiss();
                //llpopupSpinner2.clearAnimation();
            }
        });

        lvspinner1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    rentingSpinner2.setText("价格");
                    fourdata.setZj("");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 1) {
                    rentingSpinner2.setText("500元以下");
                    fourdata.setZj("0-500");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 8) {
                    rentingSpinner2.setText("10000元以上");
                    fourdata.setZj("10000-");
                    presenter.LoadProgrameData(fourdata);
                } else {

                    String price = SpinnerData.ZuJin.get(position).split("万")[0];
                    rentingSpinner2.setText(SpinnerData.ZuJin.get(position));
                    fourdata.setZj(price);
                    presenter.LoadProgrameData(fourdata);
                }
                Tpop.dismiss();
                //llpopupSpinner2.clearAnimation();
            }
        });
    }

    /**
     * spinner
     */
    private void showPopWindowQuyu() {
        QuYupop = new PopupWindow(RentingMainActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwin_spinner3, null);

        llpopupSpinnerQuyu = (LinearLayout) view.findViewById(R.id.ll_popup_spinner_quyu);
        QuYupop.setWidth(LayoutParams.MATCH_PARENT);
        QuYupop.setHeight(LayoutParams.MATCH_PARENT);
        QuYupop.setBackgroundDrawable(new BitmapDrawable());
        QuYupop.setFocusable(true);
        QuYupop.setOutsideTouchable(true);
        QuYupop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner_quyu);
        final ListView lvspinner1 = (ListView) view.findViewById(R.id.lv_popwin_list_quyu);
        final ListView lvspinner2 = (ListView) view.findViewById(R.id.lv_popwin_list_area);
        RadioGroup rgQuyu = (RadioGroup) view.findViewById(R.id.rg_popwin_title);
        RadioButton rbquyu = (RadioButton) view.findViewById(R.id.rb_popwin_quyu);
        RadioButton rbDitie = (RadioButton) view.findViewById(R.id.rb_popwin_ditie);

        SpinnerQuYu = SpinnerData.Area;
        spinneradapter1 = new SpinnerAdapter(SpinnerQuYu, getApplicationContext());
        lvspinner1.setAdapter(spinneradapter1);

        rbDitie.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                lvspinner2.setVisibility(View.GONE);
                SpinnerQuYu = SpinnerData.DiTie;
                spinneradapter1 = new SpinnerAdapter(SpinnerQuYu, getApplicationContext());
                lvspinner1.setAdapter(spinneradapter1);
            }
        });
        rbquyu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                lvspinner2.setVisibility(View.VISIBLE);
                SpinnerQuYu = SpinnerData.Area;
                spinneradapter1 = new SpinnerAdapter(SpinnerQuYu, getApplicationContext());
                lvspinner1.setAdapter(spinneradapter1);
            }
        });


        spinneradapter1 = new SpinnerAdapter(SpinnerQuYu, getApplicationContext());
        lvspinner1.setAdapter(spinneradapter1);

        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                QuYupop.dismiss();
                //llpopupSpinnerQuyu.clearAnimation();
            }
        });

        lvspinner1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (SpinnerQuYu.equals(SpinnerData.DiTie)) {
                    if (position == 0) {
                        rentingSpinner1.setText("区域");
                        fourdata.setCt("");
                        fourdata.setAr("");
                        fourdata.setDt("");
                        presenter.LoadProgrameData(fourdata);
                        QuYupop.dismiss();
                        //llpopupSpinnerQuyu.clearAnimation();
                    } else {
                        rentingSpinner1.setText(SpinnerQuYu.get(position));
                        String dt = SpinnerQuYu.get(position).split("号")[0];
                        fourdata.setCt("");
                        fourdata.setAr("");
                        fourdata.setDt(dt);
                        presenter.LoadProgrameData(fourdata);
                        QuYupop.dismiss();
                        //llpopupSpinnerQuyu.clearAnimation();
                    }
                } else {

                    switch (position) {
                        case 0:
                            rentingSpinner1.setText("区域");
                            fourdata.setCt("");
                            fourdata.setAr("");
                            fourdata.setDt("");
                            posi = 0;
                            presenter.LoadProgrameData(fourdata);
                            QuYupop.dismiss();
                            //llpopupSpinnerQuyu.clearAnimation();
                            break;
                        case 1:
                            city = "2";
                            posi = 1;
                            MinArea.clear();
                            break;
                        case 2:
                            city = "4";
                            posi = 2;
                            MinArea.clear();
                            break;
                        case 3:
                            city = "5";
                            posi = 3;
                            MinArea.clear();
                            break;
                        case 4:
                            city = "6";
                            posi = 4;
                            MinArea.clear();
                            break;
                        case 5:
                            city = "7";
                            posi = 5;
                            MinArea.clear();
                            break;
                        case 6:
                            city = "8";
                            posi = 6;
                            MinArea.clear();
                            break;
                        case 7:
                            city = "9";
                            posi = 7;
                            MinArea.clear();
                            break;
                        case 8:
                            city = "10";
                            posi = 8;
                            MinArea.clear();
                            break;
                        case 9:
                            city = "11";
                            posi = 9;
                            MinArea.clear();
                            break;
                        case 10:
                            city = "12";
                            posi = 10;
                            MinArea.clear();
                            break;
                        case 11:
                            city = "13";
                            posi = 11;
                            MinArea.clear();
                            break;
                        case 12:
                            city = "14";
                            posi = 12;
                            MinArea.clear();
                            break;
                        default:
                            break;
                    }

                    addmodel.address(city, new AsycnCallBack() {
                        @Override
                        public void onSuccess(Object success) {
                            minarea = (List<Address>) success;
                            for (Address address : minarea) {
                                String ad = address.getName();
                                RentingMainActivity.this.MinArea.add(ad);
                            }
                            MinArea.add(0, "不限");
                            spinneradapter1 = new SpinnerAdapter(MinArea, getApplicationContext());
                            lvspinner2.setAdapter(spinneradapter1);
                        }

                        @Override
                        public void onError(Object error) {
                        }
                    });
                }
            }
        });

        lvspinner2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    rentingSpinner1.setText(SpinnerData.Area.get(posi));
                    fourdata.setCt(city);
                    fourdata.setAr("");
                    fourdata.setDt("");
                } else {
                    String pid = minarea.get(position - 1).getId();
                    rentingSpinner1.setText(minarea.get(position - 1).getName());
                    fourdata.setCt(city);
                    fourdata.setAr(pid);
                    fourdata.setDt("");
                }
                presenter.LoadProgrameData(fourdata);
                QuYupop.dismiss();
                //llpopupSpinnerQuyu.clearAnimation();
            }
        });
    }

    /**
     * spinner_more
     */
    private void showPopWindowMore() {
        Mpop = new PopupWindow(RentingMainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.item_renting_spinner4, null);
        llpopupSpinnerMore = (LinearLayout) view.findViewById(R.id.ll_popup_spinner_more);
        Mpop.setWidth(LayoutParams.MATCH_PARENT);
        Mpop.setHeight(LayoutParams.MATCH_PARENT);
        Mpop.setBackgroundDrawable(new BitmapDrawable());
        Mpop.setFocusable(true);
        Mpop.setOutsideTouchable(true);
        Mpop.setContentView(view);
        //RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner_more);
        view.findViewById(R.id.renting_chaoxiang_dong).setOnClickListener(this);
        view.findViewById(R.id.renting_chaoxiang_nan).setOnClickListener(this);
        view.findViewById(R.id.renting_chaoxiang_xi).setOnClickListener(this);
        view.findViewById(R.id.renting_chaoxiang_bei).setOnClickListener(this);
        view.findViewById(R.id.renting_chaoxiang_nanbei).setOnClickListener(this);
        view.findViewById(R.id.renting_mianji_wushi).setOnClickListener(this);
        view.findViewById(R.id.renting_mianji_wu_qi).setOnClickListener(this);
        view.findViewById(R.id.renting_mianji_qi_jiu).setOnClickListener(this);
        view.findViewById(R.id.renting_mianji_jiu_shiyi).setOnClickListener(this);
        view.findViewById(R.id.renting_mianji_shiyi_shisi).setOnClickListener(this);
        view.findViewById(R.id.renting_mianji_shisi_shiqi).setOnClickListener(this);
        view.findViewById(R.id.renting_mianji_shiqi_ershi).setOnClickListener(this);
        view.findViewById(R.id.renting_mianji_ershi).setOnClickListener(this);
        view.findViewById(R.id.renting_biaoqian_jingzhuangxiu).setOnClickListener(this);
        view.findViewById(R.id.renting_biaoqian_suishikanfang).setOnClickListener(this);
        view.findViewById(R.id.renting_louceng_low).setOnClickListener(this);
        view.findViewById(R.id.renting_louceng_center).setOnClickListener(this);
        view.findViewById(R.id.renting_louceng_high).setOnClickListener(this);
        view.findViewById(R.id.renting_wuye_shangpinfang).setOnClickListener(this);
        view.findViewById(R.id.renting_wuye_cunweitongjian).setOnClickListener(this);
        view.findViewById(R.id.renting_wuye_kaifangshangjianshe).setOnClickListener(this);
        view.findViewById(R.id.renting_wuye_gerenzijianshe).setOnClickListener(this);
        view.findViewById(R.id.renting_wuye_junquchanfang).setOnClickListener(this);
        view.findViewById(R.id.renting_wuye_wujingchanfang).setOnClickListener(this);
        view.findViewById(R.id.renting_wuye_gongyechangzufang).setOnClickListener(this);
        view.findViewById(R.id.renting_wuye_gongyechanquanfang).setOnClickListener(this);
        view.findViewById(R.id.renting_wuye_qita).setOnClickListener(this);
        view.findViewById(R.id.renting_fangshi_zhengzu).setOnClickListener(this);
        view.findViewById(R.id.renting_fangshi_hezu).setOnClickListener(this);
        view.findViewById(R.id.renting_type_zuzhai).setOnClickListener(this);
        view.findViewById(R.id.renting_type_gongyu).setOnClickListener(this);
        view.findViewById(R.id.renting_type_shangpu).setOnClickListener(this);
        view.findViewById(R.id.renting_type_xiezilou).setOnClickListener(this);
        view.findViewById(R.id.renting_spinner_cancle).setOnClickListener(this);
        view.findViewById(R.id.renting_type_qita).setOnClickListener(this);
        view.findViewById(R.id.renting_xiaoqu_dudong).setOnClickListener(this);
        view.findViewById(R.id.renting_xiaoqu_xiaoqufang).setOnClickListener(this);
        view.findViewById(R.id.renting_spinner_cancle).setOnClickListener(this);
        view.findViewById(R.id.renting_spinner_ok).setOnClickListener(this);
    }

    private int moreIds[] = {
            R.id.renting_chaoxiang_dong,
            R.id.renting_chaoxiang_nan,
            R.id.renting_chaoxiang_xi,
            R.id.renting_chaoxiang_bei,
            R.id.renting_chaoxiang_nanbei,
            R.id.renting_mianji_wushi,
            R.id.renting_mianji_wu_qi,
            R.id.renting_mianji_qi_jiu,
            R.id.renting_mianji_jiu_shiyi,
            R.id.renting_mianji_shiyi_shisi,
            R.id.renting_mianji_shisi_shiqi,
            R.id.renting_mianji_shiqi_ershi,
            R.id.renting_mianji_ershi,
            R.id.renting_biaoqian_jingzhuangxiu,
            R.id.renting_biaoqian_suishikanfang,
            R.id.renting_louceng_low,
            R.id.renting_louceng_center,
            R.id.renting_louceng_high,
            R.id.renting_wuye_shangpinfang,
            R.id.renting_wuye_cunweitongjian,
            R.id.renting_wuye_kaifangshangjianshe,
            R.id.renting_wuye_gerenzijianshe,
            R.id.renting_wuye_junquchanfang,
            R.id.renting_wuye_wujingchanfang,
            R.id.renting_wuye_gongyechangzufang,
            R.id.renting_wuye_gongyechanquanfang,
            R.id.renting_wuye_qita,
            R.id.renting_fangshi_zhengzu,
            R.id.renting_fangshi_hezu,
            R.id.renting_type_zuzhai,
            R.id.renting_type_gongyu,
            R.id.renting_type_shangpu,
            R.id.renting_type_xiezilou,
            R.id.renting_spinner_cancle,
            R.id.renting_type_qita,
            R.id.renting_xiaoqu_dudong,
            R.id.renting_xiaoqu_xiaoqufang
    };

    private void refreshMoreFilterUI() {
        View view = Mpop.getContentView();
        for (int mid : moreIds) {
            view.findViewById(mid).setSelected(false);
        }

        for (int i = 0; i < filterArrayTmp.size(); i++) {
            int mid = filterArrayTmp.valueAt(i);
            view.findViewById(mid).setSelected(true);
        }
    }

    private void moreFilterWillSelect(int sec, int mid) {
        Integer value = filterArrayTmp.get(sec);
        if (value == null) {
            filterArrayTmp.put(sec, mid);
        } else {
            if (value.intValue() == mid)
                filterArrayTmp.remove(sec);
            else
                filterArrayTmp.put(sec, mid);
        }
        refreshMoreFilterUI();
    }

    private void doFilterSearch() {
        fourdata.setMj("");
        fourdata.setQs("");
        fourdata.setYt("");
        fourdata.setXq("");
        fourdata.setCx("");
        fourdata.setLc("");
        fourdata.setZl("");
        fourdata.setZx("");
        fourdata.setKf("");
        for (int i = 0; i < filterArrayTmp.size(); i++) {
            switch (filterArrayTmp.valueAt(i)) {
                case R.id.renting_xiaoqu_dudong:
                    fourdata.setXq("独栋");
                    break;
                case R.id.renting_xiaoqu_xiaoqufang:
                    fourdata.setXq("小区房");
                    break;
                case R.id.renting_chaoxiang_dong:
                    fourdata.setCx("东");
                    break;
                case R.id.renting_chaoxiang_nan:
                    fourdata.setCx("南");
                    break;
                case R.id.renting_chaoxiang_xi:
                    fourdata.setCx("西");
                    break;
                case R.id.renting_chaoxiang_bei:
                    fourdata.setCx("北");
                    break;
                case R.id.renting_chaoxiang_nanbei:
                    fourdata.setCx("南北");
                    break;
                case R.id.renting_mianji_ershi:
                    fourdata.setMj("200-");
                    break;
                case R.id.renting_mianji_jiu_shiyi:
                    fourdata.setMj("90-110");
                    break;
                case R.id.renting_mianji_qi_jiu:
                    fourdata.setMj("70-90");
                    break;
                case R.id.renting_mianji_shiqi_ershi:
                    fourdata.setMj("170-200");
                    break;
                case R.id.renting_mianji_shisi_shiqi:
                    fourdata.setMj("140-170");
                    break;
                case R.id.renting_mianji_shiyi_shisi:
                    fourdata.setMj("110-140");
                    break;
                case R.id.renting_mianji_wu_qi:
                    fourdata.setMj("50-70");
                    break;
                case R.id.renting_mianji_wushi:
                    fourdata.setMj("0-50");
                    break;
                case R.id.renting_biaoqian_jingzhuangxiu:
                    fourdata.setZx("精装");
                    break;
                case R.id.renting_biaoqian_suishikanfang:
                    fourdata.setKf("随时看房");
                    break;
                case R.id.renting_louceng_low:
                    fourdata.setLc("低层");
                    break;
                case R.id.renting_louceng_center:
                    fourdata.setLc("中层");
                    break;
                case R.id.renting_louceng_high:
                    fourdata.setLc("高层");
                    break;
                case R.id.renting_wuye_cunweitongjian:
                    fourdata.setQs("村委统建");
                    break;
                case R.id.renting_wuye_gerenzijianshe:
                    fourdata.setQs("个人自建房");
                    break;
                case R.id.renting_wuye_gongyechangzufang:
                    fourdata.setQs("工业长租房");
                    break;
                case R.id.renting_wuye_gongyechanquanfang:
                    fourdata.setQs("工业产权房");
                    break;
                case R.id.renting_wuye_junquchanfang:
                    fourdata.setQs("广东省军区军产房");
                    break;
                case R.id.renting_wuye_kaifangshangjianshe:
                    fourdata.setQs("开发商建设");
                    break;
                case R.id.renting_wuye_qita:
                    fourdata.setQs("其他");
                    break;
                case R.id.renting_wuye_shangpinfang:
                    fourdata.setQs("商品房");
                    break;
                case R.id.renting_wuye_wujingchanfang:
                    fourdata.setQs("武警部队军产房");
                    break;
                case R.id.renting_fangshi_zhengzu:
                    fourdata.setZl("整租");
                    break;
                case R.id.renting_fangshi_hezu:
                    fourdata.setZl("合租");
                    break;
                case R.id.renting_type_gongyu:
                    fourdata.setYt("公寓");
                    break;
                case R.id.renting_type_shangpu:
                    fourdata.setYt("商铺");
                    break;
                case R.id.renting_type_qita:
                    fourdata.setYt("其他");
                    break;
                case R.id.renting_type_xiezilou:
                    fourdata.setYt("写字楼");
                    break;
                case R.id.renting_type_zuzhai:
                    fourdata.setYt("住宅");
                    break;
                default:
                    break;
            }
        }
        filterArray = filterArrayTmp.clone();
        presenter.LoadProgrameData(fourdata);
        Mpop.dismiss();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.parent_spinner_more:
                Mpop.dismiss();
                break;
            case R.id.renting_xiaoqu_dudong:
                moreFilterWillSelect(SEC_XIAOQU, R.id.renting_xiaoqu_dudong);
                break;
            case R.id.renting_xiaoqu_xiaoqufang:
                moreFilterWillSelect(SEC_XIAOQU, R.id.renting_xiaoqu_xiaoqufang);
                break;
            case R.id.renting_chaoxiang_dong:
                moreFilterWillSelect(SEC_CAOXIANG, R.id.renting_chaoxiang_dong);
                break;
            case R.id.renting_chaoxiang_nan:
                moreFilterWillSelect(SEC_CAOXIANG, R.id.renting_chaoxiang_nan);
                break;
            case R.id.renting_chaoxiang_xi:
                moreFilterWillSelect(SEC_CAOXIANG, R.id.renting_chaoxiang_xi);
                break;
            case R.id.renting_chaoxiang_bei:
                moreFilterWillSelect(SEC_CAOXIANG, R.id.renting_chaoxiang_bei);
                break;
            case R.id.renting_chaoxiang_nanbei:
                moreFilterWillSelect(SEC_CAOXIANG, R.id.renting_chaoxiang_nanbei);
                break;
            case R.id.renting_mianji_ershi:
                moreFilterWillSelect(SEC_MIANJI, R.id.renting_mianji_ershi);
                break;
            case R.id.renting_mianji_jiu_shiyi:
                moreFilterWillSelect(SEC_MIANJI, R.id.renting_mianji_jiu_shiyi);
                break;
            case R.id.renting_mianji_qi_jiu:
                moreFilterWillSelect(SEC_MIANJI, R.id.renting_mianji_qi_jiu);
                break;
            case R.id.renting_mianji_shiqi_ershi:
                moreFilterWillSelect(SEC_MIANJI, R.id.renting_mianji_shiqi_ershi);
                break;
            case R.id.renting_mianji_shisi_shiqi:
                moreFilterWillSelect(SEC_MIANJI, R.id.renting_mianji_shisi_shiqi);
                break;
            case R.id.renting_mianji_shiyi_shisi:
                moreFilterWillSelect(SEC_MIANJI, R.id.renting_mianji_shiyi_shisi);
                break;
            case R.id.renting_mianji_wu_qi:
                moreFilterWillSelect(SEC_MIANJI, R.id.renting_mianji_wu_qi);
                break;
            case R.id.renting_mianji_wushi:
                moreFilterWillSelect(SEC_MIANJI, R.id.renting_mianji_wushi);
                break;
            case R.id.renting_biaoqian_jingzhuangxiu:
                moreFilterWillSelect(SEC_BIAOQIAN, R.id.renting_biaoqian_jingzhuangxiu);
                break;
            case R.id.renting_biaoqian_suishikanfang:
                moreFilterWillSelect(SEC_BIAOQIAN, R.id.renting_biaoqian_suishikanfang);
                break;
            case R.id.renting_louceng_low:
                moreFilterWillSelect(SEC_LOUCENG, R.id.renting_louceng_low);
                break;
            case R.id.renting_louceng_center:
                moreFilterWillSelect(SEC_LOUCENG, R.id.renting_louceng_center);
                break;
            case R.id.renting_louceng_high:
                moreFilterWillSelect(SEC_LOUCENG, R.id.renting_louceng_high);
                break;
            case R.id.renting_wuye_cunweitongjian:
                moreFilterWillSelect(SEC_WUYE, R.id.renting_wuye_cunweitongjian);
                break;
            case R.id.renting_wuye_gerenzijianshe:
                moreFilterWillSelect(SEC_WUYE, R.id.renting_wuye_gerenzijianshe);
                break;
            case R.id.renting_wuye_gongyechangzufang:
                moreFilterWillSelect(SEC_WUYE, R.id.renting_wuye_gongyechangzufang);
                break;
            case R.id.renting_wuye_gongyechanquanfang:
                moreFilterWillSelect(SEC_WUYE, R.id.renting_wuye_gongyechanquanfang);
                break;
            case R.id.renting_wuye_junquchanfang:
                moreFilterWillSelect(SEC_WUYE, R.id.renting_wuye_junquchanfang);
                break;
            case R.id.renting_wuye_kaifangshangjianshe:
                moreFilterWillSelect(SEC_WUYE, R.id.renting_wuye_kaifangshangjianshe);
                break;
            case R.id.renting_wuye_qita:
                moreFilterWillSelect(SEC_WUYE, R.id.renting_wuye_qita);
                break;
            case R.id.renting_wuye_shangpinfang:
                moreFilterWillSelect(SEC_WUYE, R.id.renting_wuye_shangpinfang);
                break;
            case R.id.renting_wuye_wujingchanfang:
                moreFilterWillSelect(SEC_WUYE, R.id.renting_wuye_wujingchanfang);
                break;
            case R.id.renting_fangshi_zhengzu:
                moreFilterWillSelect(SEC_FANGSHI, R.id.renting_fangshi_zhengzu);
                break;
            case R.id.renting_fangshi_hezu:
                moreFilterWillSelect(SEC_FANGSHI, R.id.renting_fangshi_hezu);
                break;
            case R.id.renting_type_gongyu:
                moreFilterWillSelect(SEC_LEIXING, R.id.renting_type_gongyu);
                break;
            case R.id.renting_type_shangpu:
                moreFilterWillSelect(SEC_LEIXING, R.id.renting_type_shangpu);
                break;
            case R.id.renting_type_qita:
                moreFilterWillSelect(SEC_LEIXING, R.id.renting_type_qita);
                break;
            case R.id.renting_type_xiezilou:
                moreFilterWillSelect(SEC_LEIXING, R.id.renting_type_xiezilou);
                break;
            case R.id.renting_type_zuzhai:
                moreFilterWillSelect(SEC_LEIXING, R.id.renting_type_zuzhai);
                break;
            case R.id.renting_spinner_cancle:
                filterArrayTmp.clear();
                doFilterSearch();
                break;
            case R.id.renting_spinner_ok:
                doFilterSearch();
                break;
            default:
                break;
        }
    }

    public void showlistFail(String info) {
        if (lastrentings != null)
            lastrentings.clear();
        if (adapter != null)
            adapter.notifyDataSetChanged();
        MyToastShowCenter.CenterToast(getApplicationContext(), "找不到对应房源");
    }
}