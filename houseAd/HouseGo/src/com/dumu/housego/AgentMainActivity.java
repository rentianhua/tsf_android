package com.dumu.housego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dumu.housego.ErShouFangMainActivity.SpinnerAdapter;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.activity.MainActivity;
import com.dumu.housego.adapter.AgentDataAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.AgentData;
import com.dumu.housego.entity.SpinnerData;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.AgentModelDataPresenter;
import com.dumu.housego.presenter.IAgentModelDataPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IAgentModelDataView;

public class AgentMainActivity extends BaseActivity implements IAgentModelDataView {
    private ImageView ivAgentBack;
    private ListViewForScrollView lvAgentList;
    private AgentDataAdapter agentDataAdapter;
    private List<AgentData> agentdatas = new ArrayList<AgentData>();
    private IAgentModelDataPresenter presenter;
    private TextView tv_ershoufang_search, agentSpinner1, agentSpinner2, agentSpinner3;
    private PopupWindow QuYupop;
    private LinearLayout ll_agent_search, llpopupSpinnerQuyu, ll_agent_spinner;
    private SpinnerAdapter spinneradapter1;
    private String ct = "", bq = "", kw = "";
    private String catid = "52";
    private PopupWindow shaixunYupop;
    private LinearLayout llpopupSpinnersaixuan;

    private ImageView iv_location;
    //
    private String tcb1 = "";
    private String tcb2 = "";
    private String tcb3 = "";
    private String tcb4 = "";
    private String tcb5 = "";
    private String tcb6 = "";
    private PopupWindow pop;
    private LinearLayout ll_popup_spinner;
    private SpinnerAdapter spinneradapter;

    private String paixuStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_main);
        FontHelper.injectFont(findViewById(android.R.id.content));
        presenter = new AgentModelDataPresenter(this);

        showPopWindowQuyu();
        showPopWindowshuaixuan();
        showPopWindowType();
        setViews();
        setListener();
    }

    @Override
    protected void onResume() {
        tv_ershoufang_search.setText("请输入经纪人姓名");
        if (getIntent().getStringExtra("search") != null) {
            this.kw = getIntent().getStringExtra("search");
            tv_ershoufang_search.setText(kw);
        }
        presenter.FindAgentModelData(ct, bq, kw);
        super.onResume();
    }

    private void setListener() {
        iv_location.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                UserInfo userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                if (userinfo == null) {
                    startActivity(new Intent(getApplicationContext(),
                            LoginActivity.class));
                } else {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("page", "1");
                    startActivity(i);
                }
            }
        });

        ivAgentBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lvAgentList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AgentData a = agentdatas.get(position);
                Intent i = new Intent(getApplicationContext(), AgentDetailActivity.class);
                i.putExtra("userid", a.getUserid());
                i.putExtra("username", a.getUsername());
                startActivity(i);
            }
        });

        agentSpinner1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //Animation anim = AnimationUtils.loadAnimation(AgentMainActivity.this, R.anim.activity_translate_out);
                //llpopupSpinnerQuyu.
                //setAnimation(anim);
                QuYupop.showAsDropDown(ll_agent_spinner, 0, 0);
            }
        });
        agentSpinner2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//				Animation anim = AnimationUtils.loadAnimation(AgentMainActivity.this, R.anim.activity_translate_out);
//				llpopupSpinnersaixuan.
//				setAnimation(anim);
                shaixunYupop.showAsDropDown(ll_agent_spinner, 0, 0);
            }
        });
        agentSpinner3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//				Animation anim = AnimationUtils.loadAnimation(AgentMainActivity.this, R.anim.activity_translate_out);
//				ll_popup_spinner.
//				setAnimation(anim);
                pop.showAsDropDown(ll_agent_spinner, 0, 0);
            }
        });

        ll_agent_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SearchMainActivity.class);
                i.putExtra("TAG", "agent");
                startActivity(i);
            }
        });
    }

    private void setViews() {
        iv_location = (ImageView) findViewById(R.id.iv_location);
        tv_ershoufang_search = (TextView) findViewById(R.id.tv_ershoufang_search);
        ivAgentBack = (ImageView) findViewById(R.id.iv_agent_back);
        lvAgentList = (ListViewForScrollView) findViewById(R.id.lv_agent_list);
        ll_agent_search = (LinearLayout) findViewById(R.id.ll_agent_search);
        agentSpinner1 = (TextView) findViewById(R.id.agent_quyu_sp1);
        agentSpinner2 = (TextView) findViewById(R.id.agent_quyu_sp2);
        agentSpinner3 = (TextView) findViewById(R.id.agent_quyu_sp3);
        ll_agent_spinner = (LinearLayout) findViewById(R.id.ll_agent_spinner);
    }

    public class SortChengjiaoComparator implements Comparator {
        public int compare(Object lhs, Object rhs) {
            AgentData a = (AgentData) lhs;
            AgentData b = (AgentData) rhs;
            return (b.chengjiao_count - a.chengjiao_count);
        }
    }

    public class SortFbRateComparator implements Comparator {
        public int compare(Object lhs, Object rhs) {
            AgentData a = (AgentData) lhs;
            AgentData b = (AgentData) rhs;
            return (b.fb_rate - a.fb_rate);
        }
    }

    public class SortDaikanComparator implements Comparator {
        public int compare(Object lhs, Object rhs) {
            AgentData a = (AgentData) lhs;
            AgentData b = (AgentData) rhs;
            return (b.daikan_count - a.daikan_count);
        }
    }

    public void showAgentModelData(List<AgentData> agentdatas) {
        if (agentdatas != null && agentdatas.size() > 1) {
            if (paixuStr == null || paixuStr.equals("不限")) {

            } else if (paixuStr.equals("成交量从高到低")) {
                Comparator comp = new SortChengjiaoComparator();
                Collections.sort(agentdatas, comp);
            } else if (paixuStr.equals("好评率从高到低")) {
                Comparator comp = new SortFbRateComparator();
                Collections.sort(agentdatas, comp);
            } else if (paixuStr.equals("带看量从高到低")) {
                Comparator comp = new SortDaikanComparator();
                Collections.sort(agentdatas, comp);
            } else {

            }
        }

        this.agentdatas = agentdatas;
        if (agentdatas != null)
            MyToastShowCenter.CenterToast(getApplicationContext(), "找到了" + agentdatas.size() + "条数据");
        else
            MyToastShowCenter.CenterToast(getApplicationContext(), "找到了0条数据");
        agentDataAdapter = new AgentDataAdapter(agentdatas, this);
        lvAgentList.setAdapter(agentDataAdapter);
    }

    private void showPopWindowQuyu() {
        QuYupop = new PopupWindow(AgentMainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.item_popupwin_spinner, null);
        llpopupSpinnerQuyu = (LinearLayout) view.findViewById(R.id.ll_popup_spinner);
        QuYupop.setWidth(LayoutParams.MATCH_PARENT);
        QuYupop.setHeight(LayoutParams.MATCH_PARENT);
        QuYupop.setBackgroundDrawable(new BitmapDrawable());
        QuYupop.setFocusable(true);
        QuYupop.setOutsideTouchable(true);
        QuYupop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner);
        final ListView lvspinner1 = (ListView) view.findViewById(R.id.lv_popwin_list);

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
                        agentSpinner1.setText("区域");
                        ct = "";
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    case 1:
                        ct = SpinnerData.Area.get(position);
                        agentSpinner1.setText(ct);
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    case 2:
                        ct = SpinnerData.Area.get(position);
                        agentSpinner1.setText(ct);
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    case 3:
                        ct = SpinnerData.Area.get(position);
                        agentSpinner1.setText(ct);
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    case 4:
                        ct = SpinnerData.Area.get(position);
                        agentSpinner1.setText(ct);
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    case 5:
                        ct = SpinnerData.Area.get(position);
                        agentSpinner1.setText(ct);
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    case 6:
                        ct = SpinnerData.Area.get(position);
                        agentSpinner1.setText(ct);
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    case 7:
                        ct = SpinnerData.Area.get(position);
                        agentSpinner1.setText(ct);
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    case 8:
                        ct = SpinnerData.Area.get(position);
                        agentSpinner1.setText(ct);
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    case 9:
                        ct = SpinnerData.Area.get(position);
                        agentSpinner1.setText(ct);
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    case 10:
                        ct = SpinnerData.Area.get(position);
                        agentSpinner1.setText(ct);
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    case 11:
                        ct = SpinnerData.Area.get(position);
                        agentSpinner1.setText(ct);
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    case 12:
                        ct = SpinnerData.Area.get(position);
                        agentSpinner1.setText(ct);
                        presenter.FindAgentModelData(ct, bq, kw);
                        QuYupop.dismiss();
                        break;
                    default:
                        break;
                }
            }
        });
    }


    private void showPopWindowshuaixuan() {
        shaixunYupop = new PopupWindow(AgentMainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.item_agent_spinner_saixuan, null);
        llpopupSpinnersaixuan = (LinearLayout) view.findViewById(R.id.ll_popup_spinner2);
        shaixunYupop.setWidth(LayoutParams.MATCH_PARENT);
        shaixunYupop.setHeight(LayoutParams.MATCH_PARENT);
        shaixunYupop.setBackgroundDrawable(new BitmapDrawable());
        shaixunYupop.setFocusable(true);
        shaixunYupop.setOutsideTouchable(true);
        shaixunYupop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_spinner2);

        final CheckBox cb1 = (CheckBox) view.findViewById(R.id.cb_spinner1);
        final CheckBox cb2 = (CheckBox) view.findViewById(R.id.cb_spinner2);
        final CheckBox cb3 = (CheckBox) view.findViewById(R.id.cb_spinner3);
        final CheckBox cb4 = (CheckBox) view.findViewById(R.id.cb_spinner4);
        final CheckBox cb5 = (CheckBox) view.findViewById(R.id.cb_spinner5);
        final CheckBox cb6 = (CheckBox) view.findViewById(R.id.cb_spinner6);
        final TextView AgentSpinnerCancle = (TextView) view.findViewById(R.id.tv_agent__spinner_cancle);
        final TextView AgentSpinnerSubmit = (TextView) view.findViewById(R.id.tv_agent__spinner_submit);

        cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tcb1 = "," + (String) buttonView.getText().toString();
                } else {
                    tcb1 = "";
                }
            }
        });
        cb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tcb2 = "," + (String) buttonView.getText().toString();
                } else {
                    tcb2 = "";
                }
            }
        });
        cb3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tcb3 = "," + (String) buttonView.getText().toString();
                } else {
                    tcb3 = "";
                }
            }
        });
        cb4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tcb4 = "," + (String) buttonView.getText().toString();
                } else {
                    tcb4 = "";
                }
            }
        });
        cb5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tcb5 = "," + (String) buttonView.getText().toString();
                } else {
                    tcb5 = "";
                }
            }
        });
        cb6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tcb6 = "," + (String) buttonView.getText().toString();
                } else {
                    tcb6 = "";
                }
            }
        });

        AgentSpinnerSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String cb = tcb1 + tcb2 + tcb3 + tcb4 + tcb5 + tcb6;

                if (cb.startsWith(",")) {
                    String tx = cb.substring(1);
                    bq = tx;
                    presenter.FindAgentModelData(ct, bq, kw);
                    shaixunYupop.dismiss();
                } else {
                    String tx = cb;
                    bq = tx;
                    presenter.FindAgentModelData(ct, bq, kw);
                    shaixunYupop.dismiss();
                }
            }
        });

        AgentSpinnerCancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(false);
                cb4.setChecked(false);
                cb5.setChecked(false);
                cb6.setChecked(false);
            }
        });
        parent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                shaixunYupop.dismiss();
            }
        });
    }

    private void showPopWindowType() {
        pop = new PopupWindow(AgentMainActivity.this);

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
        spinneradapter = new SpinnerAdapter(SpinnerData.PAIXU, getApplicationContext());
        lvspinner1.setAdapter(spinneradapter);

        parent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        lvspinner1.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                agentSpinner3.setText(SpinnerData.PAIXU.get(position));
                pop.dismiss();
                paixuStr = SpinnerData.PAIXU.get(position);
                presenter.FindAgentModelData(ct, bq, kw);
            }
        });
    }

    public void showAgentFail(String info) {
        try {
            if (agentdatas != null) {
                agentdatas.clear();
                agentDataAdapter.notifyDataSetChanged();
            }
            MyToastShowCenter.CenterToast(getApplicationContext(), "找不到对应经纪人");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
