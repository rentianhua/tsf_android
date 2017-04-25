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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dumu.housego.ErShouFangMainActivity.SpinnerAdapter;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.activity.MainActivity;
import com.dumu.housego.adapter.BlockTradeLsitAdapter;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.BlockTradeList;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.entity.SpinnerData;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.BlockTradeProgramaModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.BlockTradeProgramaPresenter;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IBlockTradeProgramaView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class BlockTradeMainActivity extends BaseActivity
        implements View.OnClickListener, IBlockTradeProgramaView, OnRefreshListener2<ListView> {
    private LinearLayout llBlockTradeBack;
    private PullToRefreshListView lvBlockTrade;
    private IFourDataProgramePresenter presenter;
    private BlockTradeLsitAdapter adapter;
    private List<BlockTradeList> lastblocks = new ArrayList<BlockTradeList>();
    private FourDataPrograma fourdata;
    private BlockTradeProgramaModel model = new BlockTradeProgramaModel();
    private AddressModel addmodel = new AddressModel();
    int page = 1;

    private TextView tv_block_trade_search, blocktrade_quyu_sp1, blocktrade_quyu_sp2, blocktrade_quyu_sp3, blocktrade_quyu_sp4;
    private LinearLayout ll_blocktrade_spinner;

    private LinearLayout llpopupSpinnerQuyu;
    private SpinnerAdapter spinneradapter1;

    private String city;
    private List<Address> minarea;
    private List<String> MinArea = new ArrayList<String>();
    protected int posi;
    private SpinnerAdapter spinneradapter;

    private LinearLayout llpopupSpinner2;

    private LinearLayout ll_popup_spinner;
    private PopupWindow Tpop;
    private PopupWindow Mpop;
    private PopupWindow pop;
    private PopupWindow QuYupop;
    private LinearLayout llpopupSpinnerMore, ll_block_search;
    private String Kwds;

    private ImageView iv_location;
    private ImageView iv_MsM;

    private SparseArray<Integer> filterArray = new SparseArray<Integer>();
    private SparseArray<Integer> filterArrayTmp = new SparseArray<Integer>();
    private static int SEC_SHUXING = 1;
    private static int SEC_LEIXING = 2;
    private static int SEC_FANGSHI = 3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_trade_main);
        FontHelper.injectFont(findViewById(android.R.id.content));
        setViews();
        showPopWindowMianji();
        showPopWindowMore();
        showPopWindowPrice();
        showPopWindowQuyu();
        setListener();
        presenter = new BlockTradeProgramaPresenter(this);
        fourdata = new FourDataPrograma();
        fourdata.setCatid("7");
        fourdata.setPage("1");
        fourdata.setAr("");
        fourdata.setCt("");
        fourdata.setZj("");
        fourdata.setMj("");

        fourdata.setSx("");
        fourdata.setLx("");
        fourdata.setFs("");
        fourdata.setKwds("");

        tv_block_trade_search.setText("请输入小区或商圈名");
        if (getIntent().getStringExtra("search") != null) {
            this.Kwds = getIntent().getStringExtra("search");
            fourdata.setKwds(Kwds);
            tv_block_trade_search.setText(Kwds);
        }
        presenter.LoadProgrameData(fourdata);
    }

    private void setViews() {
        iv_location = (android.widget.ImageView) findViewById(R.id.iv_location);
        iv_MsM = (android.widget.ImageView) findViewById(R.id.iv_MsM);

        ll_block_search = (LinearLayout) findViewById(R.id.ll_block_search);

        tv_block_trade_search = (TextView) findViewById(R.id.tv_block_trade_search);

        llBlockTradeBack = (LinearLayout) findViewById(R.id.ll_block_trade_back);
        lvBlockTrade = (PullToRefreshListView) findViewById(R.id.lv_blocktrade);
        lvBlockTrade.setMode(PullToRefreshBase.Mode.BOTH);
        lvBlockTrade.setOnRefreshListener(this);

        blocktrade_quyu_sp4 = (TextView) findViewById(R.id.blocktrade_quyu_sp4);
        blocktrade_quyu_sp3 = (TextView) findViewById(R.id.blocktrade_quyu_sp3);
        blocktrade_quyu_sp1 = (TextView) findViewById(R.id.blocktrade_quyu_sp1);
        blocktrade_quyu_sp2 = (TextView) findViewById(R.id.blocktrade_quyu_sp2);
        ll_blocktrade_spinner = (LinearLayout) findViewById(R.id.ll_blocktrade_spinner);
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

        ll_block_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SearchMainActivity.class);
                i.putExtra("TAG", "block");
                startActivity(i);
            }
        });

        llBlockTradeBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lvBlockTrade.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {

                Intent i = new Intent(BlockTradeMainActivity.this, BlockTradeDetailActivity.class);
                String Id = lastblocks.get(position - 1).getId();
                String catid = lastblocks.get(position - 1).getCatid();
                String posid = lastblocks.get(position - 1).getPosid();
                i.putExtra("id", Id);
                i.putExtra("catid", catid);
                i.putExtra("posid", posid);
                startActivity(i);
            }
        });

        blocktrade_quyu_sp1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Tpop.dismiss();
                Mpop.dismiss();
                pop.dismiss();
//                Animation anim = AnimationUtils.loadAnimation(BlockTradeMainActivity.this,
//                        R.anim.activity_translate_out);
//                llpopupSpinnerQuyu.setAnimation(anim);
                QuYupop.showAsDropDown(ll_blocktrade_spinner, 0, 0);
            }
        });

        blocktrade_quyu_sp2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Mpop.dismiss();
                pop.dismiss();
                QuYupop.dismiss();
//                Animation anim = AnimationUtils.loadAnimation(BlockTradeMainActivity.this,
//                        R.anim.activity_translate_out);
//
//                llpopupSpinner2.setAnimation(anim);
                Tpop.showAsDropDown(ll_blocktrade_spinner, 0, 0);
            }
        });

        blocktrade_quyu_sp3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Tpop.dismiss();
                Mpop.dismiss();
                QuYupop.dismiss();
//                Animation anim = AnimationUtils.loadAnimation(BlockTradeMainActivity.this,
//                        R.anim.activity_translate_out);
//
//                ll_popup_spinner.setAnimation(anim);
                pop.showAsDropDown(ll_blocktrade_spinner, 0, 0);
            }
        });

        blocktrade_quyu_sp4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Tpop.dismiss();
                pop.dismiss();
                QuYupop.dismiss();
//                Animation anim = AnimationUtils.loadAnimation(BlockTradeMainActivity.this,
//                        R.anim.activity_translate_out);
//                llpopupSpinnerMore.setAnimation(anim);
                filterArrayTmp.clear();
                filterArrayTmp = filterArray.clone();
                refreshMoreFilterUI();
                Mpop.showAsDropDown(ll_blocktrade_spinner, 0, 0);
            }
        });
    }

    public void showData(List<BlockTradeList> blocktrades) {
        //清空数据
        this.lastblocks.clear();

        if (blocktrades != null && blocktrades.size() > 0) {
            this.lastblocks.addAll(blocktrades);
            MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastblocks.get(0).getZonghe() + "个房源！");
        } else {
            MyToastShowCenter.CenterToast(getApplicationContext(), "找到了0个房源！");
        }
        adapter = new BlockTradeLsitAdapter(lastblocks, getApplicationContext());
        lvBlockTrade.setAdapter(adapter);
    }

    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        page = 1;
        fourdata.setCatid("7");
        fourdata.setPage(page + "");
        model.GetRecommedHouse(fourdata, new AsycnCallBack() {

            public void onSuccess(Object success) {
                List<BlockTradeList> blocks = (List<BlockTradeList>) success;

                //清空数据
                lastblocks.clear();

                if (blocks != null && blocks.size() > 0) {
                    lastblocks.addAll(blocks);
                    MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastblocks.get(0).getZonghe() + "个房源！");
                } else {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "找到了0个房源！");
                }

                // 刷新界面
                adapter.notifyDataSetChanged();
                // 关闭上拉加载刷新布局
                lvBlockTrade.onRefreshComplete();
            }

            public void onError(Object error) {
                MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
                lvBlockTrade.onRefreshComplete();
            }
        });
    }

    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        page = page + 1;
        fourdata.setCatid("7");
        fourdata.setPage(page + "");
        model.GetRecommedHouse(fourdata, new AsycnCallBack() {
            public void onSuccess(Object success) {
                List<BlockTradeList> blocks = (List<BlockTradeList>) success;
                if (blocks == null) {
                    lvBlockTrade.onRefreshComplete();
                    MyToastShowCenter.CenterToast(getApplicationContext(), "已经拉到底部，没有更多的数据了。。。");
                } else {
                    // 将数据追加到集合中
                    lastblocks.addAll(blocks);
                    // 刷新界面
                    adapter.notifyDataSetChanged();
                    // 关闭上拉加载刷新布局
                    lvBlockTrade.onRefreshComplete();
//					MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + lastblocks.size() + "个房源！");
                }
            }

            public void onError(Object error) {
                MyToastShowCenter.CenterToast(getApplicationContext(), error.toString());
                lvBlockTrade.onRefreshComplete();
            }
        });
    }

    public void showDataFail(String info) {
        if (lastblocks != null)
            lastblocks.clear();
        if (adapter != null)
            adapter.notifyDataSetChanged();
        MyToastShowCenter.CenterToast(getApplicationContext(), "找不到对应房源");
    }

    // 区域
    private void showPopWindowQuyu() {
        QuYupop = new PopupWindow(BlockTradeMainActivity.this);
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
            public void onClick(View v) {
                QuYupop.dismiss();
            }
        });

        lvspinner1.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        blocktrade_quyu_sp1.setText("区域");
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
                    public void onSuccess(Object success) {
                        minarea = (List<Address>) success;
                        for (Address address : minarea) {
                            String ad = address.getName();
                            BlockTradeMainActivity.this.MinArea.add(ad);
                        }
                        MinArea.add(0, "不限");
                        spinneradapter1 = new SpinnerAdapter(MinArea, getApplicationContext());
                        lvspinner2.setAdapter(spinneradapter1);
                    }

                    public void onError(Object error) {
                    }
                });
            }
        });

        lvspinner2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    blocktrade_quyu_sp1.setText(SpinnerData.Area.get(posi));
                    fourdata.setCt(city);
                    fourdata.setAr("");
                    fourdata.setDt("");
                    presenter.LoadProgrameData(fourdata);
                    QuYupop.dismiss();
                } else {
                    String pid = minarea.get(position - 1).getId();
                    blocktrade_quyu_sp1.setText(minarea.get(position - 1).getName());
                    fourdata.setCt(city);
                    fourdata.setAr(pid);
                    fourdata.setDt("");
                    presenter.LoadProgrameData(fourdata);
                    QuYupop.dismiss();
                }
            }
        });
    }

    // 金额
    private void showPopWindowPrice() {
        Tpop = new PopupWindow(BlockTradeMainActivity.this);

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

        spinneradapter = new SpinnerAdapter(SpinnerData.JINE, getApplicationContext());
        lvspinner1.setAdapter(spinneradapter);

        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Tpop.dismiss();
            }
        });

        lvspinner1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    blocktrade_quyu_sp2.setText("金额");
                    fourdata.setZj("");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 1) {
                    blocktrade_quyu_sp2.setText("1000万以下");
                    fourdata.setZj("0-1000");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 4) {
                    blocktrade_quyu_sp2.setText("5000万-1亿");
                    fourdata.setZj("5000-10000");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 5) {
                    blocktrade_quyu_sp2.setText("1亿以上");
                    fourdata.setZj("10000-");
                    presenter.LoadProgrameData(fourdata);
                } else {
                    String price = SpinnerData.JINE.get(position).split("万")[0];
                    blocktrade_quyu_sp2.setText(SpinnerData.JINE.get(position));
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
    private void showPopWindowMianji() {
        pop = new PopupWindow(BlockTradeMainActivity.this);

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
        spinneradapter = new SpinnerAdapter(SpinnerData.MIANJI, getApplicationContext());
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
                    blocktrade_quyu_sp3.setText("房型");
                    fourdata.setMj("");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 1) {
                    blocktrade_quyu_sp3.setText("1万平米以下");
                    fourdata.setMj("0-10000");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 2) {
                    blocktrade_quyu_sp3.setText("1-5万平米");
                    fourdata.setMj("10000-50000");
                    presenter.LoadProgrameData(fourdata);
                } else if (position == 3) {
                    blocktrade_quyu_sp3.setText("5-10万平米");
                    fourdata.setMj("50000-100000");
                    presenter.LoadProgrameData(fourdata);
                } else {
                    blocktrade_quyu_sp3.setText("10万平米以上");
                    fourdata.setMj("100000-");
                    presenter.LoadProgrameData(fourdata);
                }
                pop.dismiss();
            }
        });
    }

    private void showPopWindowMore() {
        Mpop = new PopupWindow(BlockTradeMainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.item_blocktrade_spinner4, null);
        llpopupSpinnerMore = (LinearLayout) view.findViewById(R.id.ll_popup_spinner_more);
        Mpop.setWidth(LayoutParams.MATCH_PARENT);
        Mpop.setHeight(LayoutParams.MATCH_PARENT);
        Mpop.setBackgroundDrawable(new BitmapDrawable());
        Mpop.setFocusable(true);
        Mpop.setOutsideTouchable(true);
        Mpop.setContentView(view);
        //RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner_more);
        view.findViewById(R.id.blockbrade_fangshi_bufenzhuanrang).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_fangshi_guquanrongzi).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_fangshi_kongguquanzhuanrang).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_fangshi_zhaiquanrongzi).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_fangshi_zhengtizhuanrang).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_fangshi_zulinrongzi).setOnClickListener(this);

        view.findViewById(R.id.blockbrade_sx_cunjiti).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_sx_junduiyongdi).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_sx_linyeyongdi).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_sx_shangye).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_sx_shangyongbangong).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_sx_zhuzhaiyongdi).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_sx_zongheyongdi).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_sx_gongyeyongdi).setOnClickListener(this);

        view.findViewById(R.id.blockbrade_type_gongyeyongfang).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_type_jiti).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_type_jiudian).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_type_junchanfang).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_type_shangyeyong).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_type_xiezilou).setOnClickListener(this);
        view.findViewById(R.id.blockbrade_type_zhuzhaiyong).setOnClickListener(this);
        view.findViewById(R.id.blocktrade_spinner_cancle).setOnClickListener(this);
        view.findViewById(R.id.blocktrade_spinner_ok).setOnClickListener(this);
    }

    private int moreIds[] = {
            R.id.blockbrade_fangshi_bufenzhuanrang,
            R.id.blockbrade_fangshi_guquanrongzi,
            R.id.blockbrade_fangshi_kongguquanzhuanrang,
            R.id.blockbrade_fangshi_zhaiquanrongzi,
            R.id.blockbrade_fangshi_zhengtizhuanrang,
            R.id.blockbrade_fangshi_zulinrongzi,
            R.id.blockbrade_sx_cunjiti,
            R.id.blockbrade_sx_junduiyongdi,
            R.id.blockbrade_sx_linyeyongdi,
            R.id.blockbrade_sx_shangye,
            R.id.blockbrade_sx_shangyongbangong,
            R.id.blockbrade_sx_zhuzhaiyongdi,
            R.id.blockbrade_sx_zongheyongdi,
            R.id.blockbrade_sx_gongyeyongdi,
            R.id.blockbrade_type_gongyeyongfang,
            R.id.blockbrade_type_jiti,
            R.id.blockbrade_type_jiudian,
            R.id.blockbrade_type_junchanfang,
            R.id.blockbrade_type_shangyeyong,
            R.id.blockbrade_type_xiezilou,
            R.id.blockbrade_type_zhuzhaiyong,
            R.id.blocktrade_spinner_cancle
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
        fourdata.setFs("");
        fourdata.setLx("");
        fourdata.setSx("");
        for (int i = 0; i < filterArrayTmp.size(); i++) {
            switch (filterArrayTmp.valueAt(i)) {
                case R.id.blockbrade_fangshi_bufenzhuanrang:
                    fourdata.setFs("部分转让");
                    break;
                case R.id.blockbrade_fangshi_guquanrongzi:
                    fourdata.setFs("股权融资");
                    break;
                case R.id.blockbrade_fangshi_kongguquanzhuanrang:
                    fourdata.setFs("控股权转让");
                    break;
                case R.id.blockbrade_fangshi_zhaiquanrongzi:
                    fourdata.setFs("债权融资");
                    break;
                case R.id.blockbrade_fangshi_zhengtizhuanrang:
                    fourdata.setFs("整体转让");
                    break;
                case R.id.blockbrade_fangshi_zulinrongzi:
                    fourdata.setFs("租赁融资");
                    break;
                case R.id.blockbrade_type_gongyeyongfang:
                    fourdata.setLx("工业厂房");
                    break;
                case R.id.blockbrade_type_jiti:
                    fourdata.setLx("集体");
                    break;
                case R.id.blockbrade_type_jiudian:
                    fourdata.setLx("酒店");
                    break;
                case R.id.blockbrade_type_junchanfang:
                    fourdata.setLx("军产房");
                    break;
                case R.id.blockbrade_type_shangyeyong:
                    fourdata.setLx("商业用房");
                    break;
                case R.id.blockbrade_type_xiezilou:
                    fourdata.setLx("写字楼");
                    break;
                case R.id.blockbrade_type_zhuzhaiyong:
                    fourdata.setLx("住宅用房");
                    break;
                case R.id.blockbrade_sx_cunjiti:
                    fourdata.setSx("村集体用地");
                    break;
                case R.id.blockbrade_sx_junduiyongdi:
                    fourdata.setSx("军队用地");
                    break;
                case R.id.blockbrade_sx_linyeyongdi:
                    fourdata.setSx("林业用地");
                    break;
                case R.id.blockbrade_sx_shangye:
                    fourdata.setSx("商业用地");
                    break;
                case R.id.blockbrade_sx_gongyeyongdi:
                    fourdata.setSx("工业用地");
                    break;
                case R.id.blockbrade_sx_shangyongbangong:
                    fourdata.setSx("商用/办公土地");
                    break;
                case R.id.blockbrade_sx_zhuzhaiyongdi:
                    fourdata.setSx("住宅用地");
                    break;
                case R.id.blockbrade_sx_zongheyongdi:
                    fourdata.setSx("综合用地");
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
            case R.id.blockbrade_fangshi_bufenzhuanrang:
                moreFilterWillSelect(SEC_FANGSHI, R.id.blockbrade_fangshi_bufenzhuanrang);
                break;
            case R.id.blockbrade_fangshi_guquanrongzi:
                moreFilterWillSelect(SEC_FANGSHI, R.id.blockbrade_fangshi_guquanrongzi);
                break;
            case R.id.blockbrade_fangshi_kongguquanzhuanrang:
                moreFilterWillSelect(SEC_FANGSHI, R.id.blockbrade_fangshi_kongguquanzhuanrang);
                break;
            case R.id.blockbrade_fangshi_zhaiquanrongzi:
                moreFilterWillSelect(SEC_FANGSHI, R.id.blockbrade_fangshi_zhaiquanrongzi);
                break;
            case R.id.blockbrade_fangshi_zhengtizhuanrang:
                moreFilterWillSelect(SEC_FANGSHI, R.id.blockbrade_fangshi_zhengtizhuanrang);
                break;
            case R.id.blockbrade_fangshi_zulinrongzi:
                moreFilterWillSelect(SEC_FANGSHI, R.id.blockbrade_fangshi_zulinrongzi);
                break;
            case R.id.blockbrade_type_gongyeyongfang:
                moreFilterWillSelect(SEC_LEIXING, R.id.blockbrade_type_gongyeyongfang);
                break;
            case R.id.blockbrade_type_jiti:
                moreFilterWillSelect(SEC_LEIXING, R.id.blockbrade_type_jiti);
                break;
            case R.id.blockbrade_type_jiudian:
                moreFilterWillSelect(SEC_LEIXING, R.id.blockbrade_type_jiudian);
                break;
            case R.id.blockbrade_type_junchanfang:
                moreFilterWillSelect(SEC_LEIXING, R.id.blockbrade_type_junchanfang);
                break;
            case R.id.blockbrade_type_shangyeyong:
                moreFilterWillSelect(SEC_LEIXING, R.id.blockbrade_type_shangyeyong);
                break;
            case R.id.blockbrade_type_xiezilou:
                moreFilterWillSelect(SEC_LEIXING, R.id.blockbrade_type_xiezilou);
                break;
            case R.id.blockbrade_type_zhuzhaiyong:
                moreFilterWillSelect(SEC_LEIXING, R.id.blockbrade_type_zhuzhaiyong);
                break;
            case R.id.blockbrade_sx_cunjiti:
                moreFilterWillSelect(SEC_SHUXING, R.id.blockbrade_sx_cunjiti);
                break;
            case R.id.blockbrade_sx_junduiyongdi:
                moreFilterWillSelect(SEC_SHUXING, R.id.blockbrade_sx_junduiyongdi);
                break;
            case R.id.blockbrade_sx_linyeyongdi:
                moreFilterWillSelect(SEC_SHUXING, R.id.blockbrade_sx_linyeyongdi);
                break;
            case R.id.blockbrade_sx_shangye:
                moreFilterWillSelect(SEC_SHUXING, R.id.blockbrade_sx_shangye);
                break;
            case R.id.blockbrade_sx_gongyeyongdi:
                moreFilterWillSelect(SEC_SHUXING, R.id.blockbrade_sx_gongyeyongdi);
                break;
            case R.id.blockbrade_sx_shangyongbangong:
                moreFilterWillSelect(SEC_SHUXING, R.id.blockbrade_sx_shangyongbangong);
                break;
            case R.id.blockbrade_sx_zhuzhaiyongdi:
                moreFilterWillSelect(SEC_SHUXING, R.id.blockbrade_sx_zhuzhaiyongdi);
                break;
            case R.id.blockbrade_sx_zongheyongdi:
                moreFilterWillSelect(SEC_SHUXING, R.id.blockbrade_sx_zongheyongdi);
                break;
            case R.id.blocktrade_spinner_cancle:
                filterArrayTmp.clear();
                doFilterSearch();
                break;
            case R.id.blocktrade_spinner_ok:
                doFilterSearch();
                break;
            default:
                break;
        }
    }
}
