package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.activity.MainActivity;
import com.dumu.housego.adapter.ErShouFangRecommendAdapter;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.SpinnerData;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.ErShouFangProgramaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.ErShouFangProgramaPresenter;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IErShouFangRecommendView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ErShouFangMainActivity extends BaseActivity implements View.OnClickListener, IErShouFangRecommendView, OnRefreshListener2<ListView> {
    private LinearLayout llErshoufang;
    private ErShouFangRecommendAdapter adapter;
    private IFourDataProgramePresenter presenter;
    private List<ErShouFangRecommendData> lastershous = new ArrayList<ErShouFangRecommendData>();
    private FourDataPrograma fourdata;
    private TextView ershoufangQuyuSp1;
    private TextView ershoufangQuyuSp2;
    private TextView ershoufangQuyuSp3;
    private TextView ershoufangQuyuSp4, tv_ershoufang_search;
    private LinearLayout ll_ershou_search;
    //private SimpleAdapter adapter1;
    //private ArrayAdapter<String> Spinneradapter;
    private ImageView iv_location, iv_MsM;

    private ErShouFangProgramaModel model = new ErShouFangProgramaModel();

    private SparseArray<Integer> filterArray = new SparseArray<Integer>();
    private SparseArray<Integer> filterArrayTmp = new SparseArray<Integer>();

    private static int SEC_CAOXIANG = 1;
    private static int SEC_MIANJI = 2;
    private static int SEC_BIAOQIAN = 3;
    private static int SEC_LOUCENG = 4;
    private static int SEC_WUYE = 5;
    private static int SEC_LAIYUAN = 6;
    private static int SEC_LEIXING = 7;

    public SpinnerAdapter spinneradapter;
    public SpinnerAdapter spinneradapter1;
    private PullToRefreshListView refreshListview;
    private int page = 1;
    private PopupWindow pop;
    private LinearLayout ll_popup_spinner, ll_ershoufang_spinner;
    private PopupWindow Tpop;
    private LinearLayout llpopupSpinner2;
    private PopupWindow QuYupop;
    private LinearLayout llpopupSpinnerQuyu;
    private List<String> SpinnerQuYu = new ArrayList<String>();
    private PopupWindow Mpop;
    private LinearLayout llpopupSpinnerMore;
    private AddressModel addmodel = new AddressModel();
    private String city = "2";
    private List<Address> minarea;
    private List<String> MinArea = new ArrayList<String>();
    private int posi;
    private String Kwds;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_shou_fang_main);
        FontHelper.injectFont(findViewById(android.R.id.content));
        SpinnerQuYu = SpinnerData.Area;
        setViews();
        showPopWindow();
        showPopWindow2();
        showPopWindowQuyu();
        showPopWindowMore();
        setListener();

        presenter = new ErShouFangProgramaPresenter(this);

        fourdata = new FourDataPrograma();

        fourdata.setCatid("6");
        fourdata.setPage("1");
        fourdata.setCt("");
        fourdata.setAr("");
        fourdata.setDt("");
        fourdata.setZj("");
        fourdata.setShi("");
        fourdata.setMj("");
        fourdata.setQs("");
        fourdata.setLy("");

        fourdata.setYt("");
        fourdata.setZx("");
        fourdata.setLc("");
        fourdata.setCx("");
        fourdata.setWy("");
        fourdata.setKwds("");

        tv_ershoufang_search.setText("请输入小区或商圈名");
        if (getIntent().getStringExtra("search") != null) {
            this.Kwds = getIntent().getStringExtra("search");
            fourdata.setKwds(Kwds);
            tv_ershoufang_search.setText(Kwds);
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

        ll_ershou_search.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SearchMainActivity.class);
                i.putExtra("TAG", "ershou");
                startActivity(i);
            }
        });

        llErshoufang.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ErShouFangMainActivity.this.finish();
            }
        });

        refreshListview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                Intent i = new Intent(ErShouFangMainActivity.this, ErShouFangDetailsActivity.class);
                String catid = lastershous.get(position - 1).getCatid();
                String ID = lastershous.get(position - 1).getId();
                i.putExtra("catid", catid);
                i.putExtra("id", ID);
                startActivity(i);
            }
        });

        ershoufangQuyuSp3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //Animation anim = AnimationUtils.loadAnimation(ErShouFangMainActivity.this, R.anim.activity_translate_out);
                //ll_popup_spinner.setAnimation(anim);
                pop.showAsDropDown(ll_ershoufang_spinner, 0, 0);
            }
        });

        ershoufangQuyuSp2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //Animation anim = AnimationUtils.loadAnimation(ErShouFangMainActivity.this, R.anim.activity_translate_out);
                //llpopupSpinner2.setAnimation(anim);
                Tpop.showAsDropDown(ll_ershoufang_spinner, 0, 0);
            }
        });

        ershoufangQuyuSp1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //Animation anim = AnimationUtils.loadAnimation(ErShouFangMainActivity.this, R.anim.activity_translate_out);
                //llpopupSpinnerQuyu.setAnimation(anim);
                QuYupop.showAsDropDown(ll_ershoufang_spinner, 0, 0);
            }
        });

        ershoufangQuyuSp4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //Animation anim = AnimationUtils.loadAnimation(ErShouFangMainActivity.this, R.anim.activity_translate_out);
                //llpopupSpinnerMore.setAnimation(anim);

                filterArrayTmp.clear();
                filterArrayTmp = filterArray.clone();
                refreshMoreFilterUI();
                Mpop.showAsDropDown(ll_ershoufang_spinner, 0, 0);
            }
        });
    }

    private void setViews() {
        iv_location = (ImageView) findViewById(R.id.iv_location);
        iv_MsM = (ImageView) findViewById(R.id.iv_MsM);

        tv_ershoufang_search = (TextView) findViewById(R.id.tv_ershoufang_search);
        ll_ershou_search = (LinearLayout) findViewById(R.id.ll_ershou_search);

        // 刷新
        refreshListview = (PullToRefreshListView) findViewById(R.id.refresh_listview);
        refreshListview.setMode(PullToRefreshBase.Mode.BOTH);
        refreshListview.setOnRefreshListener(this);
        //
        llErshoufang = (LinearLayout) findViewById(R.id.ll_ershoufang_back);
        ershoufangQuyuSp1 = (TextView) findViewById(R.id.ershoufang_quyu_sp1);
        ershoufangQuyuSp2 = (TextView) findViewById(R.id.ershoufang_quyu_sp2);
        ershoufangQuyuSp3 = (TextView) findViewById(R.id.ershoufang_quyu_sp3);
        ershoufangQuyuSp4 = (TextView) findViewById(R.id.ershoufang_quyu_sp4);
        ll_ershoufang_spinner = (LinearLayout) findViewById(R.id.ll_ershoufang_spinner);
    }

    public void showData(List<ErShouFangRecommendData> ershoufangrecommends) {
        //清空数据
        lastershous.clear();
        if (ershoufangrecommends == null || ershoufangrecommends.size() <= 0) {
            MyToastShowCenter.CenterToast(getApplicationContext(), "找到了0个房源！");
        } else {
            MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + ershoufangrecommends.get(0).getZonghe() + "个房源！");
            this.lastershous.addAll(ershoufangrecommends);
        }
        adapter = new ErShouFangRecommendAdapter(lastershous, getApplicationContext());
        refreshListview.setAdapter(adapter);
    }

    /**
     * 刷新
     */
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        page = 1;
        fourdata.setCatid("6");
        fourdata.setPage(page + "");
        presenter.LoadProgrameData(fourdata);

        model.GetRecommedHouse(fourdata, new AsycnCallBack() {
            public void onSuccess(Object success) {
                List<ErShouFangRecommendData> ershous = (List<ErShouFangRecommendData>) success;

                //清空数据
                lastershous.clear();

                if (ershous != null && ershous.size() > 0) {
                    lastershous.addAll(ershous);
                    MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastershous.get(0).getZonghe() + "个房源！");
                } else {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "找到了0个房源！");
                }

                // 刷新界面
                adapter.notifyDataSetChanged();
                // 关闭上拉加载刷新布局
                refreshListview.onRefreshComplete();
            }

            public void onError(Object error) {
                MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
                refreshListview.onRefreshComplete();
            }
        });
    }

    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        page = page + 1;
        fourdata.setPage(page + "");
        model.GetRecommedHouse(fourdata, new AsycnCallBack() {
            public void onSuccess(Object success) {
                List<ErShouFangRecommendData> ershous = (List<ErShouFangRecommendData>) success;
                if (ershous != null) {
                    lastershous.addAll(ershous);
                    // 刷新界面
                    adapter.notifyDataSetChanged();
                    // 关闭上拉加载刷新布局
                    refreshListview.onRefreshComplete();
//					MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastershous.get(0).getZonghe() + "个房源！");
                } else {
                    refreshListview.onRefreshComplete();
                    MyToastShowCenter.CenterToast(getApplicationContext(), "已经拉到底部，没有更多的数据了。。。");
                }
            }

            public void onError(Object error) {
                MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
                refreshListview.onRefreshComplete();
            }
        });
    }

    /**
     * spinner
     */
    private void showPopWindow() {
        pop = new PopupWindow(ErShouFangMainActivity.this);

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
                    ershoufangQuyuSp3.setText("房型");
                    fourdata.setShi("");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 6) {
                    ershoufangQuyuSp3.setText("5室以上");
                    fourdata.setShi("6");
                    presenter.LoadProgrameData(fourdata);
                } else {
                    String shi = SpinnerData.HouseType.get(position).split("室")[0];

                    ershoufangQuyuSp3.setText(SpinnerData.HouseType.get(position));
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
    private void showPopWindow2() {
        Tpop = new PopupWindow(ErShouFangMainActivity.this);

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
                ethigh.setText("");
                etlow.setText("");
            }
        });

        spinneradapter = new SpinnerAdapter(SpinnerData.Price, getApplicationContext());
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
                    ershoufangQuyuSp2.setText("价格");
                    fourdata.setZj("");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 1) {
                    ershoufangQuyuSp2.setText("100万以下");
                    fourdata.setZj("0-100");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 5) {
                    ershoufangQuyuSp2.setText("400万以上");
                    fourdata.setZj("400-");
                    presenter.LoadProgrameData(fourdata);
                } else {
                    String price = SpinnerData.Price.get(position).split("万")[0];
                    ershoufangQuyuSp2.setText(SpinnerData.Price.get(position));
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
        QuYupop = new PopupWindow(ErShouFangMainActivity.this);

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

        rbDitie.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
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
                        ershoufangQuyuSp1.setText("区域");
                        fourdata.setCt("");
                        fourdata.setAr("");
                        fourdata.setDt("");
                        presenter.LoadProgrameData(fourdata);
                        QuYupop.dismiss();
                        //llpopupSpinnerQuyu.clearAnimation();
                    } else {
                        ershoufangQuyuSp1.setText(SpinnerQuYu.get(position));
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
                            ershoufangQuyuSp1.setText("区域");
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
                        public void onSuccess(Object success) {
                            minarea = (List<Address>) success;
                            for (Address address : minarea) {
                                String ad = address.getName();
                                ErShouFangMainActivity.this.MinArea.add(ad);
                            }
                            MinArea.add(0, "不限");
                            spinneradapter1 = new SpinnerAdapter(MinArea, getApplicationContext());
                            lvspinner2.setAdapter(spinneradapter1);
                        }

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
                    ershoufangQuyuSp1.setText(SpinnerData.Area.get(posi));
                    fourdata.setCt(city);
                    fourdata.setAr("");
                    fourdata.setDt("");
                } else {
                    String pid = minarea.get(position - 1).getId();
                    ershoufangQuyuSp1.setText(minarea.get(position - 1).getName());
                    fourdata.setCt(city);
                    fourdata.setAr(pid);
                    fourdata.setDt("");
                    Log.e("city=pid", "city=" + city + "--pid=" + pid);
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
        Mpop = new PopupWindow(ErShouFangMainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.item_popupwin_spinner4, null);
        llpopupSpinnerMore = (LinearLayout) view.findViewById(R.id.ll_popup_spinner_more);
        Mpop.setWidth(LayoutParams.MATCH_PARENT);
        Mpop.setHeight(LayoutParams.MATCH_PARENT);
        Mpop.setBackgroundDrawable(new BitmapDrawable());
        Mpop.setFocusable(true);
        Mpop.setOutsideTouchable(true);
        Mpop.setContentView(view);
        //RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner_more);
        view.findViewById(R.id.ershou_chaoxiang_dong).setOnClickListener(this);
        view.findViewById(R.id.ershou_chaoxiang_nan).setOnClickListener(this);
        view.findViewById(R.id.ershou_chaoxiang_xi).setOnClickListener(this);
        view.findViewById(R.id.ershou_chaoxiang_bei).setOnClickListener(this);
        view.findViewById(R.id.ershou_chaoxiang_nanbei).setOnClickListener(this);
        view.findViewById(R.id.ershou_mianji_wushi).setOnClickListener(this);
        view.findViewById(R.id.ershou_mianji_wu_qi).setOnClickListener(this);
        view.findViewById(R.id.ershou_mianji_qi_jiu).setOnClickListener(this);
        view.findViewById(R.id.ershou_mianji_jiu_shiyi).setOnClickListener(this);
        view.findViewById(R.id.ershou_mianji_shiyi_shisi).setOnClickListener(this);
        view.findViewById(R.id.ershou_mianji_shisi_shiqi).setOnClickListener(this);
        view.findViewById(R.id.ershou_mianji_shiqi_ershi).setOnClickListener(this);
        view.findViewById(R.id.ershou_mianji_ershi).setOnClickListener(this);
        view.findViewById(R.id.ershou_biaoqian_jing).setOnClickListener(this);
        view.findViewById(R.id.ershou_biaoqian_weiyi).setOnClickListener(this);
        view.findViewById(R.id.ershou_louceng_low).setOnClickListener(this);
        view.findViewById(R.id.ershou_louceng_center).setOnClickListener(this);
        view.findViewById(R.id.ershou_louceng_high).setOnClickListener(this);
        view.findViewById(R.id.ershou_wuye_shangpinfang).setOnClickListener(this);
        view.findViewById(R.id.ershou_wuye_cunweitongjian).setOnClickListener(this);
        view.findViewById(R.id.ershou_wuye_kaifangshangjianshe).setOnClickListener(this);
        view.findViewById(R.id.ershou_wuye_gerenzijianshe).setOnClickListener(this);
        view.findViewById(R.id.ershou_wuye_junquchanfang).setOnClickListener(this);
        view.findViewById(R.id.ershou_wuye_wujingchanfang).setOnClickListener(this);
        view.findViewById(R.id.ershou_wuye_gongyechangzufang).setOnClickListener(this);
        view.findViewById(R.id.ershou_wuye_gongyechanquanfang).setOnClickListener(this);
        view.findViewById(R.id.ershou_wuye_qita).setOnClickListener(this);
        view.findViewById(R.id.ershou_from_yezhu).setOnClickListener(this);
        view.findViewById(R.id.ershou_from_agent).setOnClickListener(this);
        view.findViewById(R.id.ershou_type_zuzhai).setOnClickListener(this);
        view.findViewById(R.id.ershou_type_gongyu).setOnClickListener(this);
        view.findViewById(R.id.ershou_type_shangpu).setOnClickListener(this);
        view.findViewById(R.id.ershou_type_qita).setOnClickListener(this);
        view.findViewById(R.id.ershou_type_xiezilou).setOnClickListener(this);
        view.findViewById(R.id.ershou_spinner_cancle).setOnClickListener(this);
        view.findViewById(R.id.ershou_spinner_ok).setOnClickListener(this);
    }

    private int moreIds[] = {
            R.id.ershou_chaoxiang_dong,
            R.id.ershou_chaoxiang_nan,
            R.id.ershou_chaoxiang_xi,
            R.id.ershou_chaoxiang_bei,
            R.id.ershou_chaoxiang_nanbei,
            R.id.ershou_mianji_wushi,
            R.id.ershou_mianji_wu_qi,
            R.id.ershou_mianji_qi_jiu,
            R.id.ershou_mianji_jiu_shiyi,
            R.id.ershou_mianji_shiyi_shisi,
            R.id.ershou_mianji_shisi_shiqi,
            R.id.ershou_mianji_shiqi_ershi,
            R.id.ershou_mianji_ershi,
            R.id.ershou_biaoqian_jing,
            R.id.ershou_biaoqian_weiyi,
            R.id.ershou_louceng_low,
            R.id.ershou_louceng_center,
            R.id.ershou_louceng_high,
            R.id.ershou_wuye_shangpinfang,
            R.id.ershou_wuye_cunweitongjian,
            R.id.ershou_wuye_kaifangshangjianshe,
            R.id.ershou_wuye_gerenzijianshe,
            R.id.ershou_wuye_junquchanfang,
            R.id.ershou_wuye_wujingchanfang,
            R.id.ershou_wuye_gongyechangzufang,
            R.id.ershou_wuye_gongyechanquanfang,
            R.id.ershou_wuye_qita,
            R.id.ershou_from_yezhu,
            R.id.ershou_from_agent,
            R.id.ershou_type_zuzhai,
            R.id.ershou_type_gongyu,
            R.id.ershou_type_shangpu,
            R.id.ershou_type_qita,
            R.id.ershou_type_xiezilou
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
        fourdata.setCx("");
        fourdata.setMj("");
        fourdata.setQs("");
        fourdata.setLy("");
        fourdata.setYt("");
        fourdata.setZx("");
        fourdata.setLc("");
        fourdata.setWy("");
        for (int i = 0; i < filterArrayTmp.size(); i++) {
            switch (filterArrayTmp.valueAt(i)) {
                case R.id.ershou_chaoxiang_dong:
                    fourdata.setCx("东");
                    break;
                case R.id.ershou_chaoxiang_nan:
                    fourdata.setCx("南");
                    break;
                case R.id.ershou_chaoxiang_xi:
                    fourdata.setCx("西");
                    break;
                case R.id.ershou_chaoxiang_bei:
                    fourdata.setCx("北");
                    break;
                case R.id.ershou_chaoxiang_nanbei:
                    fourdata.setCx("南北");
                    break;
                case R.id.ershou_mianji_wushi:
                    fourdata.setMj("0-50");
                    break;
                case R.id.ershou_mianji_wu_qi:
                    fourdata.setMj("50-70");
                    break;
                case R.id.ershou_mianji_qi_jiu:
                    fourdata.setMj("70-90");
                    break;
                case R.id.ershou_mianji_jiu_shiyi:
                    fourdata.setMj("90-110");
                    break;
                case R.id.ershou_mianji_shiyi_shisi:
                    fourdata.setMj("110-140");
                    break;
                case R.id.ershou_mianji_shisi_shiqi:
                    fourdata.setMj("140-170");
                    break;
                case R.id.ershou_mianji_shiqi_ershi:
                    fourdata.setMj("170-200");
                    break;
                case R.id.ershou_mianji_ershi:
                    fourdata.setMj("200-");
                    break;
                case R.id.ershou_biaoqian_jing:
                    fourdata.setZx("精装");
                    break;
                case R.id.ershou_biaoqian_weiyi:
                    fourdata.setWy("是");
                    break;
                case R.id.ershou_louceng_low:
                    fourdata.setLc("低层");
                    break;
                case R.id.ershou_louceng_center:
                    fourdata.setLc("中层");
                    break;
                case R.id.ershou_louceng_high:
                    fourdata.setLc("高层");
                    break;
                case R.id.ershou_wuye_shangpinfang:
                    fourdata.setQs("商品房");
                    break;
                case R.id.ershou_wuye_cunweitongjian:
                    fourdata.setQs("村委统建");
                    break;
                case R.id.ershou_wuye_kaifangshangjianshe:
                    fourdata.setQs("开发商建设");
                    break;
                case R.id.ershou_wuye_gerenzijianshe:
                    fourdata.setQs("个人自建房");
                    break;
                case R.id.ershou_wuye_junquchanfang:
                    fourdata.setQs("广东省军区军产房");
                    break;
                case R.id.ershou_wuye_wujingchanfang:
                    fourdata.setQs("武警部队军产房");
                    break;
                case R.id.ershou_wuye_gongyechangzufang:
                    fourdata.setQs("工业长租房");
                    break;
                case R.id.ershou_wuye_gongyechanquanfang:
                    fourdata.setQs("工业产权房");
                    break;
                case R.id.ershou_wuye_qita:
                    fourdata.setQs("其他");
                    break;
                case R.id.ershou_from_yezhu:
                    fourdata.setLy("yz");
                    break;
                case R.id.ershou_from_agent:
                    fourdata.setLy("jjr");
                    break;
                case R.id.ershou_type_zuzhai:
                    fourdata.setYt("住宅");
                    break;
                case R.id.ershou_type_gongyu:
                    fourdata.setYt("公寓");
                    break;
                case R.id.ershou_type_qita:
                    fourdata.setYt("其他");
                    break;
                case R.id.ershou_type_shangpu:
                    fourdata.setYt("商铺");
                    break;
                case R.id.ershou_type_xiezilou:
                    fourdata.setYt("写字楼");
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
            case R.id.ershou_chaoxiang_dong:
                moreFilterWillSelect(SEC_CAOXIANG, R.id.ershou_chaoxiang_dong);
                break;
            case R.id.ershou_chaoxiang_nan:
                moreFilterWillSelect(SEC_CAOXIANG, R.id.ershou_chaoxiang_nan);
                break;
            case R.id.ershou_chaoxiang_xi:
                moreFilterWillSelect(SEC_CAOXIANG, R.id.ershou_chaoxiang_xi);
                break;
            case R.id.ershou_chaoxiang_bei:
                moreFilterWillSelect(SEC_CAOXIANG, R.id.ershou_chaoxiang_bei);
                break;
            case R.id.ershou_chaoxiang_nanbei:
                moreFilterWillSelect(SEC_CAOXIANG, R.id.ershou_chaoxiang_nanbei);
                break;
            case R.id.ershou_mianji_wushi:
                moreFilterWillSelect(SEC_MIANJI, R.id.ershou_mianji_wushi);
                break;
            case R.id.ershou_mianji_wu_qi:
                moreFilterWillSelect(SEC_MIANJI, R.id.ershou_mianji_wu_qi);
                break;
            case R.id.ershou_mianji_qi_jiu:
                moreFilterWillSelect(SEC_MIANJI, R.id.ershou_mianji_qi_jiu);
                break;
            case R.id.ershou_mianji_jiu_shiyi:
                moreFilterWillSelect(SEC_MIANJI, R.id.ershou_mianji_jiu_shiyi);
                break;
            case R.id.ershou_mianji_shiyi_shisi:
                moreFilterWillSelect(SEC_MIANJI, R.id.ershou_mianji_shiyi_shisi);
                break;
            case R.id.ershou_mianji_shisi_shiqi:
                moreFilterWillSelect(SEC_MIANJI, R.id.ershou_mianji_shisi_shiqi);
                break;
            case R.id.ershou_mianji_shiqi_ershi:
                moreFilterWillSelect(SEC_MIANJI, R.id.ershou_mianji_shiqi_ershi);
                break;
            case R.id.ershou_mianji_ershi:
                moreFilterWillSelect(SEC_MIANJI, R.id.ershou_mianji_ershi);
                break;
            case R.id.ershou_biaoqian_jing:
                moreFilterWillSelect(SEC_BIAOQIAN, R.id.ershou_biaoqian_jing);
                break;
            case R.id.ershou_biaoqian_weiyi:
                moreFilterWillSelect(SEC_BIAOQIAN, R.id.ershou_biaoqian_weiyi);
                break;
            case R.id.ershou_louceng_low:
                moreFilterWillSelect(SEC_LOUCENG, R.id.ershou_louceng_low);
                break;
            case R.id.ershou_louceng_center:
                moreFilterWillSelect(SEC_LOUCENG, R.id.ershou_louceng_center);
                break;
            case R.id.ershou_louceng_high:
                moreFilterWillSelect(SEC_LOUCENG, R.id.ershou_louceng_high);
                break;
            case R.id.ershou_wuye_shangpinfang:
                moreFilterWillSelect(SEC_WUYE, R.id.ershou_wuye_shangpinfang);
                break;
            case R.id.ershou_wuye_cunweitongjian:
                moreFilterWillSelect(SEC_WUYE, R.id.ershou_wuye_cunweitongjian);
                break;
            case R.id.ershou_wuye_kaifangshangjianshe:
                moreFilterWillSelect(SEC_WUYE, R.id.ershou_wuye_kaifangshangjianshe);
                break;
            case R.id.ershou_wuye_gerenzijianshe:
                moreFilterWillSelect(SEC_WUYE, R.id.ershou_wuye_gerenzijianshe);
                break;
            case R.id.ershou_wuye_junquchanfang:
                moreFilterWillSelect(SEC_WUYE, R.id.ershou_wuye_junquchanfang);
                break;
            case R.id.ershou_wuye_wujingchanfang:
                moreFilterWillSelect(SEC_WUYE, R.id.ershou_wuye_wujingchanfang);
                break;
            case R.id.ershou_wuye_gongyechangzufang:
                moreFilterWillSelect(SEC_WUYE, R.id.ershou_wuye_gongyechangzufang);
                break;
            case R.id.ershou_wuye_gongyechanquanfang:
                moreFilterWillSelect(SEC_WUYE, R.id.ershou_wuye_gongyechanquanfang);
                break;
            case R.id.ershou_wuye_qita:
                moreFilterWillSelect(SEC_WUYE, R.id.ershou_wuye_qita);
                break;
            case R.id.ershou_from_yezhu:
                moreFilterWillSelect(SEC_LAIYUAN, R.id.ershou_from_yezhu);
                break;
            case R.id.ershou_from_agent:
                moreFilterWillSelect(SEC_LAIYUAN, R.id.ershou_from_agent);
                break;
            case R.id.ershou_type_zuzhai:
                moreFilterWillSelect(SEC_LEIXING, R.id.ershou_type_zuzhai);
                break;
            case R.id.ershou_type_gongyu:
                moreFilterWillSelect(SEC_LEIXING, R.id.ershou_type_gongyu);
                break;
            case R.id.ershou_type_qita:
                moreFilterWillSelect(SEC_LEIXING, R.id.ershou_type_qita);
                break;
            case R.id.ershou_type_shangpu:
                moreFilterWillSelect(SEC_LEIXING, R.id.ershou_type_shangpu);
                break;
            case R.id.ershou_type_xiezilou:
                moreFilterWillSelect(SEC_LEIXING, R.id.ershou_type_xiezilou);
                break;
            case R.id.ershou_spinner_cancle:
                filterArrayTmp.clear();
                doFilterSearch();
                break;
            case R.id.ershou_spinner_ok:
                doFilterSearch();
                break;
            default:
                break;
        }
    }

    public static class SpinnerAdapter extends BaseAdapter {
        private List<String> comments;
        private Context context;
        private LayoutInflater Inflater;

        public SpinnerAdapter(List<String> comments, Context context) {
            super();
            this.comments = comments;
            this.context = context;
            this.Inflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return comments == null ? 0 : comments.size();
        }

        public String getItem(int position) {
            return comments.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = Inflater.inflate(R.layout.item_spinner_show, null);
                holder = new ViewHolder();
                holder.tvSpinnershow = (TextView) convertView.findViewById(R.id.tv_spinner_show);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String str = comments.get(position);
            holder.tvSpinnershow.setText(str);
            return convertView;
        }

        class ViewHolder {
            TextView tvSpinnershow;
        }
    }

    public void showDatafail(String info) {
        if (lastershous != null)
            lastershous.clear();
        if (adapter != null)
            adapter.notifyDataSetChanged();
        MyToastShowCenter.CenterToast(getApplicationContext(), "找不到对应房源");
    }
}
