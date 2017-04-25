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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dumu.housego.ErShouFangMainActivity.SpinnerAdapter;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.adapter.NewHouseListAdapter;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.NewHouseList;
import com.dumu.housego.entity.SpinnerData;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.NewHouseListModel;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.presenter.NewHouseListPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.INewHouseListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class NewHouseListActivity extends BaseActivity
        implements View.OnClickListener, INewHouseListView, OnRefreshListener2<ListView> {
    private LinearLayout llNewhouselistBack;
    private NewHouseListAdapter listadapter;
    private IFourDataProgramePresenter presenter;
    private FourDataPrograma fourdata;
    private PullToRefreshListView lv_newhouselist;
    private List<NewHouseList> lastnews = new ArrayList<NewHouseList>();
    int page = 1;
    private AddressModel addmodel = new AddressModel();
    private NewHouseListModel model = new NewHouseListModel();
    private PopupWindow QuYupop;
    private LinearLayout llpopupSpinnerQuyu;
    private SpinnerAdapter spinneradapter1;
    private String city;
    private List<Address> minarea;
    private List<String> MinArea = new ArrayList<String>();
    private TextView newhousespinner1, newhousespinner2, newhousespinner3, newhousespinner4, tv_new_search;
    protected int posi;
    private PopupWindow Tpop;
    private LinearLayout llpopupSpinner2, ll_new_search;
    private SpinnerAdapter spinneradapter;
    protected LinearLayout ll_newhouse_spinner;
    private PopupWindow pop;
    private LinearLayout ll_popup_spinner;
    private PopupWindow Mpop;
    private LinearLayout llpopupSpinnerMore;
    private String Kwds;

    private SparseArray<Integer> filterArray = new SparseArray<Integer>();
    private SparseArray<Integer> filterArrayTmp = new SparseArray<Integer>();
    private static int SEC_ZHUANGXIU = 1;
    private static int SEC_LEIXING = 2;
    private static int SEC_SHUXING = 3;
    private static int SEC_XIAOQU = 4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_house_list);
        FontHelper.injectFont(findViewById(android.R.id.content));
        setViews();
        showPopWindowPrice();
        showPopWindowQuyu();
        showPopWindowType();
        showPopWindowMore();
        setListener();
        presenter = new NewHouseListPresenter(this);
        fourdata = new FourDataPrograma();
        fourdata.setCatid("3");
        fourdata.setPage("1");
        fourdata.setCt("");
        fourdata.setAr("");
        fourdata.setZj("");
        fourdata.setShi("");
        fourdata.setZx("");
        fourdata.setYt("");
        fourdata.setWy("");
        fourdata.setXq("");
        fourdata.setKwds("");

        if (getIntent().getStringExtra("newTag") != null) {
            String tag = getIntent().getStringExtra("newTag");
            if (tag.equals("futian")) {
                fourdata.setCt("4");
            } else if (tag.equals("longgang")) {
                fourdata.setCt("8");
            } else if (tag.equals("luohu")) {
                fourdata.setCt("2");
            } else if (tag.equals("jingzhuangxiu")) {
                fourdata.setZx("精装");
            } else if (tag.equals("liangju")) {
                fourdata.setShi("2");
            } else if (tag.equals("sanju")) {
                fourdata.setShi("3");
            } else if (tag.equals("siju")) {
                fourdata.setShi("4");
            } else if (tag.equals("shangpinfang")) {
                fourdata.setWy("商品房");
            }
        }

        tv_new_search.setText("请输入小区或商圈名");
        if (getIntent().getStringExtra("search") != null) {
            this.Kwds = getIntent().getStringExtra("search");
            fourdata.setKwds(Kwds);
            tv_new_search.setText(Kwds);
        }
        presenter.LoadProgrameData(fourdata);
    }

    private void setViews() {
        ll_new_search = (LinearLayout) findViewById(R.id.ll_new_search);
        tv_new_search = (TextView) findViewById(R.id.tv_new_search);
        llNewhouselistBack = (LinearLayout) findViewById(R.id.ll_newhouselist_back);
        lv_newhouselist = (PullToRefreshListView) findViewById(R.id.lv_newhouselist);
        lv_newhouselist.setMode(PullToRefreshBase.Mode.BOTH);
        lv_newhouselist.setOnRefreshListener(this);

        newhousespinner1 = (TextView) findViewById(R.id.newhouse_quyu_sp1);
        newhousespinner2 = (TextView) findViewById(R.id.newhouse_quyu_sp2);
        newhousespinner3 = (TextView) findViewById(R.id.newhouse_quyu_sp3);
        newhousespinner4 = (TextView) findViewById(R.id.newhouse_quyu_sp4);

        ll_newhouse_spinner = (LinearLayout) findViewById(R.id.ll_newhouse_spinner);
    }

    private void setListener() {
        ll_new_search.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SearchMainActivity.class);
                i.putExtra("TAG", "new");
                startActivity(i);
            }
        });

        llNewhouselistBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lv_newhouselist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewHouseList n = lastnews.get(position - 1);
                Intent i = new Intent(getApplicationContext(), NewHouseDetailActivity.class);
                String Id = n.getId();
                String catid = n.getCatid();
                i.putExtra("Id", Id);
                i.putExtra("catid", catid);
                startActivity(i);
            }
        });

        newhousespinner1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Animation anim = AnimationUtils.loadAnimation(NewHouseListActivity.this, R.anim.activity_translate_out);
//                llpopupSpinnerQuyu.setAnimation(anim);
                QuYupop.showAsDropDown(ll_newhouse_spinner, 0, 0);
            }
        });
        newhousespinner2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                Animation anim = AnimationUtils.loadAnimation(NewHouseListActivity.this, R.anim.activity_translate_out);
//                llpopupSpinner2.setAnimation(anim);
                Tpop.showAsDropDown(ll_newhouse_spinner, 0, 0);
            }
        });

        newhousespinner3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                Animation anim = AnimationUtils.loadAnimation(NewHouseListActivity.this, R.anim.activity_translate_out);
//                ll_popup_spinner.setAnimation(anim);
                pop.showAsDropDown(ll_newhouse_spinner, 0, 0);
            }
        });
        newhousespinner4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                Animation anim = AnimationUtils.loadAnimation(NewHouseListActivity.this, R.anim.activity_translate_out);
//                llpopupSpinnerMore.setAnimation(anim);
                filterArrayTmp.clear();
                filterArrayTmp = filterArray.clone();
                refreshMoreFilterUI();
                Mpop.showAsDropDown(ll_newhouse_spinner, 0, 0);
            }
        });


        //map
        findViewById(R.id.iv_location).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(NewHouseListActivity.this, MapHouseMainActivity.class));
            }
        });
    }

    public void showNewHouseList(List<NewHouseList> newhouselists) {
        //清空数据
        this.lastnews.clear();

        if (newhouselists != null && newhouselists.size() > 0) {
            this.lastnews.addAll(newhouselists);
            MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastnews.get(0).getZonghe() + "个房源！");
        } else {
            MyToastShowCenter.CenterToast(getApplicationContext(), "找到了0个房源！");
        }

        listadapter = new NewHouseListAdapter(lastnews, getApplicationContext());
        lv_newhouselist.setAdapter(listadapter);
    }

    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        page = 1;
        fourdata.setCatid("3");
        fourdata.setPage(page + "");

        model.GetRecommedHouse(fourdata, new AsycnCallBack() {
            public void onSuccess(Object success) {
                List<NewHouseList> news = (List<NewHouseList>) success;

                //清空数据
                lastnews.clear();

                if (news != null && news.size() > 0) {
                    lastnews.addAll(news);
                    MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastnews.get(0).getZonghe() + "个房源！");
                } else {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "找到了0个房源！");
                }

                // 刷新界面
                listadapter.notifyDataSetChanged();
                // 关闭上拉加载刷新布局
                lv_newhouselist.onRefreshComplete();
            }

            public void onError(Object error) {
                MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
                lv_newhouselist.onRefreshComplete();
            }
        });
    }

    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        page = page + 1;
        fourdata.setCatid("3");
        fourdata.setPage(page + "");
        model.GetRecommedHouse(fourdata, new AsycnCallBack() {

            @Override
            public void onSuccess(Object success) {
                List<NewHouseList> news = (List<NewHouseList>) success;
                if (news == null) {
                    lv_newhouselist.onRefreshComplete();
                    MyToastShowCenter.CenterToast(getApplicationContext(), "已经拉到底部，没有更多的数据了。。。");
                } else {
                    // 将数据追加到集合中
                    lastnews.addAll(news);
                    // 刷新界面
                    listadapter.notifyDataSetChanged();
                    // 关闭上拉加载刷新布局
                    lv_newhouselist.onRefreshComplete();
//					MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastnews.size() + "个房源！");
                }
            }

            public void onError(Object error) {
                MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
                lv_newhouselist.onRefreshComplete();
            }
        });
    }

    // 区域
    private void showPopWindowQuyu() {
        QuYupop = new PopupWindow(NewHouseListActivity.this);
        View view = getLayoutInflater().inflate(R.layout.item_newhouse_spinner1, null);
        llpopupSpinnerQuyu = (LinearLayout) view.findViewById(R.id.ll_popup_spinner);
        QuYupop.setWidth(LayoutParams.MATCH_PARENT);
        QuYupop.setHeight(LayoutParams.MATCH_PARENT);
        QuYupop.setBackgroundDrawable(new BitmapDrawable());
        QuYupop.setFocusable(true);
        QuYupop.setOutsideTouchable(true);
        QuYupop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner);
        final ListView lvspinner1 = (ListView) view.findViewById(R.id.lv_popwin_list);
        final ListView lvspinner2 = (ListView) view.findViewById(R.id.lv_popwin_list2);

        spinneradapter1 = new SpinnerAdapter(SpinnerData.Area, getApplicationContext());
        lvspinner1.setAdapter(spinneradapter1);

        parent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                QuYupop.dismiss();
            }
        });

        lvspinner1.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        newhousespinner1.setText("区域");
                        fourdata.setCt("");
                        fourdata.setAr("");
                        posi = 0;
                        presenter.LoadProgrameData(fourdata);
                        QuYupop.dismiss();
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
                        posi = 6;
                        city = "8";
                        MinArea.clear();
                        break;
                    case 7:
                        posi = 7;
                        city = "9";
                        MinArea.clear();
                        break;
                    case 8:
                        posi = 8;
                        city = "10";
                        MinArea.clear();
                        break;
                    case 9:
                        posi = 9;
                        city = "11";
                        MinArea.clear();
                        break;
                    case 10:
                        posi = 10;
                        city = "12";
                        MinArea.clear();
                        break;
                    case 11:
                        posi = 11;
                        city = "13";
                        MinArea.clear();
                        break;
                    case 12:
                        posi = 12;
                        city = "14";
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
                            NewHouseListActivity.this.MinArea.add(ad);
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
        });

        lvspinner2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    newhousespinner1.setText(SpinnerData.Area.get(posi));
                    fourdata.setCt(city);
                    fourdata.setAr("");
                    fourdata.setDt("");
                    presenter.LoadProgrameData(fourdata);
                    QuYupop.dismiss();
                } else {
                    String pid = minarea.get(position - 1).getId();
                    newhousespinner1.setText(minarea.get(position - 1).getName());
                    fourdata.setCt(city);
                    fourdata.setAr(pid);
                    fourdata.setDt("");
                    presenter.LoadProgrameData(fourdata);
                    QuYupop.dismiss();
                }
            }
        });
    }

    /**
     * spinner 价格
     */
    private void showPopWindowPrice() {
        Tpop = new PopupWindow(NewHouseListActivity.this);

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
                } else if (!low.equals("") && high.equals("")) {
                    fourdata.setZj(low + "-");
                    presenter.LoadProgrameData(fourdata);
                    Tpop.dismiss();
                } else if (!low.equals("") && !high.equals("")) {
                    int l = Integer.valueOf(low);
                    int h = Integer.valueOf(high);

                    if (l > h) {
                        MyToastShowCenter.CenterToast(getApplicationContext(), "最高价必须大于最低价！");
                    } else if (l == h) {
                        fourdata.setZj(low);
                        presenter.LoadProgrameData(fourdata);
                        Tpop.dismiss();
                    } else {
                        fourdata.setZj(low + "-" + high);
                        presenter.LoadProgrameData(fourdata);
                        Tpop.dismiss();
                    }
                }
                //
                ethigh.setText("");
                etlow.setText("");

            }
        });

        spinneradapter = new SpinnerAdapter(SpinnerData.NewPrice, getApplicationContext());
        lvspinner1.setAdapter(spinneradapter);

        parent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Tpop.dismiss();
            }
        });

        lvspinner1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    newhousespinner2.setText("价格");
                    fourdata.setZj("");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 1) {
                    newhousespinner2.setText("30万以下");
                    fourdata.setZj("0-30");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 2) {
                    newhousespinner2.setText("30-50万");
                    fourdata.setZj("30-50");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 3) {
                    newhousespinner2.setText("50-100万");
                    fourdata.setZj("50-100");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 4) {
                    newhousespinner2.setText("100-150万");
                    fourdata.setZj("100-150");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 5) {
                    newhousespinner2.setText("150-200万");
                    fourdata.setZj("150-200");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 6) {
                    newhousespinner2.setText("200-250万");
                    fourdata.setZj("200-250");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 7) {
                    newhousespinner2.setText("250-300万");
                    fourdata.setZj("250-300");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 8) {
                    newhousespinner2.setText("300万以上");
                    fourdata.setZj("300-");
                    presenter.LoadProgrameData(fourdata);
                } else {
                    String price = SpinnerData.Price.get(position).split("万")[0];
                    Log.e("price", "price=" + price);
                    newhousespinner2.setText(SpinnerData.NewPrice.get(position));
                    fourdata.setZj(price);
                    presenter.LoadProgrameData(fourdata);
                }
                Tpop.dismiss();
            }
        });
    }

    /**
     * spinner
     */
    private void showPopWindowType() {
        pop = new PopupWindow(NewHouseListActivity.this);

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
            }
        });

        lvspinner1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    newhousespinner3.setText("房型");
                    fourdata.setShi("");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 6) {
                    newhousespinner3.setText("5室以上");
                    fourdata.setShi("6");
                    presenter.LoadProgrameData(fourdata);
                } else {
                    String shi = SpinnerData.HouseType.get(position).split("室")[0];

                    newhousespinner3.setText(SpinnerData.HouseType.get(position));
                    fourdata.setShi(shi);
                    presenter.LoadProgrameData(fourdata);
                }
                pop.dismiss();
            }
        });

    }

    //
    private void showPopWindowMore() {
        Mpop = new PopupWindow(NewHouseListActivity.this);
        View view = getLayoutInflater().inflate(R.layout.item_newhouse_spinner4, null);
        llpopupSpinnerMore = (LinearLayout) view.findViewById(R.id.ll_popup_spinner_more);
        Mpop.setWidth(LayoutParams.MATCH_PARENT);
        Mpop.setHeight(LayoutParams.MATCH_PARENT);
        Mpop.setBackgroundDrawable(new BitmapDrawable());
        Mpop.setFocusable(true);
        Mpop.setOutsideTouchable(true);
        Mpop.setContentView(view);
        //RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner_more);
        view.findViewById(R.id.newhouse_zx_jing).setOnClickListener(this);
        view.findViewById(R.id.newhouse_zx_jian).setOnClickListener(this);
        view.findViewById(R.id.newhouse_zx_mao).setOnClickListener(this);
        view.findViewById(R.id.newhouse_type_bangong).setOnClickListener(this);
        view.findViewById(R.id.newhouse_type_changfang).setOnClickListener(this);
        view.findViewById(R.id.newhouse_type_juzhu).setOnClickListener(this);
        view.findViewById(R.id.newhouse_type_shangye).setOnClickListener(this);
        view.findViewById(R.id.newhouse_type_shangzhu).setOnClickListener(this);
        view.findViewById(R.id.newhouse_type_zomngheti).setOnClickListener(this);
        view.findViewById(R.id.newhouse_sx_cunwei).setOnClickListener(this);
        view.findViewById(R.id.newhouse_sx_geren).setOnClickListener(this);
        view.findViewById(R.id.newhouse_sx_gongyechangzu).setOnClickListener(this);
        view.findViewById(R.id.newhouse_sx_gongyechanquan).setOnClickListener(this);
        view.findViewById(R.id.newhouse_sx_junjuchanfang).setOnClickListener(this);
        view.findViewById(R.id.newhouse_sx_kaifangshang).setOnClickListener(this);
        view.findViewById(R.id.newhouse_sx_qita).setOnClickListener(this);
        view.findViewById(R.id.newhouse_sx_shangpin).setOnClickListener(this);
        view.findViewById(R.id.newhouse_sx_wujingchanfang).setOnClickListener(this);
        view.findViewById(R.id.newhouse_xq_dudong).setOnClickListener(this);
        view.findViewById(R.id.newhouse_xq_xiaoqu).setOnClickListener(this);
        view.findViewById(R.id.newhouse_all_cancle).setOnClickListener(this);
        view.findViewById(R.id.newhouse_all_ok).setOnClickListener(this);
    }

    private int moreIds[] = {
            R.id.newhouse_zx_jing,
            R.id.newhouse_zx_jian,
            R.id.newhouse_zx_mao,
            R.id.newhouse_type_bangong,
            R.id.newhouse_type_changfang,
            R.id.newhouse_type_juzhu,
            R.id.newhouse_type_shangye,
            R.id.newhouse_type_shangzhu,
            R.id.newhouse_type_zomngheti,
            R.id.newhouse_sx_cunwei,
            R.id.newhouse_sx_geren,
            R.id.newhouse_sx_gongyechangzu,
            R.id.newhouse_sx_gongyechanquan,
            R.id.newhouse_sx_junjuchanfang,
            R.id.newhouse_sx_kaifangshang,
            R.id.newhouse_sx_qita,
            R.id.newhouse_sx_shangpin,
            R.id.newhouse_sx_wujingchanfang,
            R.id.newhouse_xq_dudong,
            R.id.newhouse_xq_xiaoqu,
            R.id.newhouse_all_cancle
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
        fourdata.setZx("");
        fourdata.setYt("");
        fourdata.setWy("");
        fourdata.setXq("");
        for (int i = 0; i < filterArrayTmp.size(); i++) {
            switch (filterArrayTmp.valueAt(i)) {
                case R.id.newhouse_zx_jing:
                    fourdata.setZx("精装");
                    break;
                case R.id.newhouse_zx_jian:
                    fourdata.setZx("简装");
                    break;
                case R.id.newhouse_zx_mao:
                    fourdata.setZx("毛坯");
                    break;
                case R.id.newhouse_type_bangong:
                    fourdata.setYt("办公");
                    break;
                case R.id.newhouse_type_changfang:
                    fourdata.setYt("厂房");
                    break;
                case R.id.newhouse_type_juzhu:
                    fourdata.setYt("居住");
                    break;
                case R.id.newhouse_type_shangye:
                    fourdata.setYt("商业");
                    break;
                case R.id.newhouse_type_shangzhu:
                    fourdata.setYt("商住两用");
                    break;
                case R.id.newhouse_type_zomngheti:
                    fourdata.setYt("综合体");
                    break;
                case R.id.newhouse_sx_cunwei:
                    fourdata.setWy("集体用地村委统建楼");
                    break;
                case R.id.newhouse_sx_geren:
                    fourdata.setWy("集体用地个人自建房");
                    break;
                case R.id.newhouse_sx_gongyechangzu:
                    fourdata.setWy("工业属性长租物业");
                    break;
                case R.id.newhouse_sx_gongyechanquan:
                    fourdata.setWy("工业可分割产权物业");
                    break;
                case R.id.newhouse_sx_junjuchanfang:
                    fourdata.setWy("军区建房");
                    break;
                case R.id.newhouse_sx_kaifangshang:
                    fourdata.setWy("集体用地开发商自建楼");
                    break;
                case R.id.newhouse_sx_qita:
                    fourdata.setWy("其他");
                    break;
                case R.id.newhouse_sx_shangpin:
                    fourdata.setWy("商品房");
                    break;
                case R.id.newhouse_sx_wujingchanfang:
                    fourdata.setWy("武警部队建房");
                    break;
                case R.id.newhouse_xq_dudong:
                    fourdata.setXq("独栋");
                    break;
                case R.id.newhouse_xq_xiaoqu:
                    fourdata.setXq("小区房");
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
            case R.id.newhouse_zx_jing:
                moreFilterWillSelect(SEC_ZHUANGXIU, R.id.newhouse_zx_jing);
                break;
            case R.id.newhouse_zx_jian:
                moreFilterWillSelect(SEC_ZHUANGXIU, R.id.newhouse_zx_jian);
                break;
            case R.id.newhouse_zx_mao:
                moreFilterWillSelect(SEC_ZHUANGXIU, R.id.newhouse_zx_mao);
                break;
            case R.id.newhouse_type_bangong:
                moreFilterWillSelect(SEC_LEIXING, R.id.newhouse_type_bangong);
                break;
            case R.id.newhouse_type_changfang:
                moreFilterWillSelect(SEC_LEIXING, R.id.newhouse_type_changfang);
                break;
            case R.id.newhouse_type_juzhu:
                moreFilterWillSelect(SEC_LEIXING, R.id.newhouse_type_juzhu);
                break;
            case R.id.newhouse_type_shangye:
                moreFilterWillSelect(SEC_LEIXING, R.id.newhouse_type_shangye);
                break;
            case R.id.newhouse_type_shangzhu:
                moreFilterWillSelect(SEC_LEIXING, R.id.newhouse_type_shangzhu);
                break;
            case R.id.newhouse_type_zomngheti:
                moreFilterWillSelect(SEC_LEIXING, R.id.newhouse_type_zomngheti);
                break;
            case R.id.newhouse_sx_cunwei:
                moreFilterWillSelect(SEC_SHUXING, R.id.newhouse_sx_cunwei);
                break;
            case R.id.newhouse_sx_geren:
                moreFilterWillSelect(SEC_SHUXING, R.id.newhouse_sx_geren);
                break;
            case R.id.newhouse_sx_gongyechangzu:
                moreFilterWillSelect(SEC_SHUXING, R.id.newhouse_sx_gongyechangzu);
                break;
            case R.id.newhouse_sx_gongyechanquan:
                moreFilterWillSelect(SEC_SHUXING, R.id.newhouse_sx_gongyechanquan);
                break;
            case R.id.newhouse_sx_junjuchanfang:
                moreFilterWillSelect(SEC_SHUXING, R.id.newhouse_sx_junjuchanfang);
                break;
            case R.id.newhouse_sx_kaifangshang:
                moreFilterWillSelect(SEC_SHUXING, R.id.newhouse_sx_kaifangshang);
                break;
            case R.id.newhouse_sx_qita:
                moreFilterWillSelect(SEC_SHUXING, R.id.newhouse_sx_qita);
                break;
            case R.id.newhouse_sx_shangpin:
                moreFilterWillSelect(SEC_SHUXING, R.id.newhouse_sx_shangpin);
                break;
            case R.id.newhouse_sx_wujingchanfang:
                moreFilterWillSelect(SEC_SHUXING, R.id.newhouse_sx_wujingchanfang);
                break;
            case R.id.newhouse_xq_dudong:
                moreFilterWillSelect(SEC_XIAOQU, R.id.newhouse_xq_dudong);
                break;
            case R.id.newhouse_xq_xiaoqu:
                moreFilterWillSelect(SEC_XIAOQU, R.id.newhouse_xq_xiaoqu);
                break;
            case R.id.newhouse_all_cancle:
                filterArrayTmp.clear();
                doFilterSearch();
                break;
            case R.id.newhouse_all_ok:
                doFilterSearch();
                break;
            default:
                break;
        }
    }

    public void showlistFail(String info) {
        if (lastnews != null)
            lastnews.clear();
        if (listadapter != null)
            listadapter.notifyDataSetChanged();
        MyToastShowCenter.CenterToast(getApplicationContext(), "找不到对应房源");
    }
}
