package com.dumu.housego;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.dumu.housego.activity.BaseFragmentActivity;
import com.dumu.housego.framgent.AGChengJiaohouseFragment;
import com.dumu.housego.framgent.ATGouDiListFragment;
import com.dumu.housego.framgent.ATHistroyDataFragment;
import com.dumu.housego.framgent.ATQiuGouFragment;
import com.dumu.housego.framgent.ATQiuZuFragment;
import com.dumu.housego.framgent.ATYHQListFragment;
import com.dumu.housego.framgent.ATershouListFragment;
import com.dumu.housego.framgent.ATershouSubmitFragment;
import com.dumu.housego.framgent.ATrentingListFragment;
import com.dumu.housego.framgent.JiSuanQiFragment;
import com.dumu.housego.framgent.WeiTuoGuanLiFragment;
import com.dumu.housego.util.FontHelper;

public class AgentPersonalActivity extends BaseFragmentActivity {
	public static final int AGENT_ERSHOU_LIST = 0;// 经纪人二手房列表
	public static final int AGENT_ERSHOU_SUBMIT = 1;// 经纪人二手房发布
	public static final int AGENT_RENTING_LIST = 2;// 经纪人出租房列表
	public static final int AGENT_RENTING_SUBMIT = 3;// 经纪人出租房发布
	public static final int AGENT_CHENGJIAO = 4; // 经纪人成交房源
	public static final int AGENT_GOUDI = 5; // 经纪人成交房源
	public static final int AGENT_YHQ = 6; // 经纪人成交房源
	public static final int AGENT_QIUZU = 7; // 经纪人成交房源
	public static final int AGENT_QIUGOU = 8; // 经纪人成交房源
	public static final int AGENT_HISTROY = 9; // 经纪人成交房源
	public static final int GOUFANGJISUANQI = 10; // 经纪人成交房源
	public static final int AGENT_WEITUOGUANLI = 11; // 经纪人成交房源

	private FrameLayout flAgentFragment;
	private Fragment fragment;
	int DisplayType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agent_personal);
		FontHelper.injectFont(findViewById(android.R.id.content));
		DisplayType = getIntent().getIntExtra("FRAGMENT_KEY", AGENT_ERSHOU_LIST);
		initView();
		initData();

	}

	private void initData() {

		switch (DisplayType) {
		case AGENT_ERSHOU_LIST:
			fragment = new ATershouListFragment();
			break;
		case AGENT_RENTING_LIST:
			fragment = new ATrentingListFragment();
			break;
		case AGENT_CHENGJIAO:
			fragment = new AGChengJiaohouseFragment();
			break;
		case AGENT_GOUDI:
			fragment = new ATGouDiListFragment();
			break;
		case AGENT_YHQ:
			fragment = new ATYHQListFragment();
			break;
		case AGENT_QIUZU:
			fragment = new ATQiuZuFragment();
			break;
		case AGENT_QIUGOU:
			fragment = new ATQiuGouFragment();
			break;
		case AGENT_HISTROY:
			fragment = new ATHistroyDataFragment();
			break;
		case GOUFANGJISUANQI:
			fragment = new JiSuanQiFragment();
			break;
		case AGENT_ERSHOU_SUBMIT:
			fragment = new ATershouSubmitFragment();
			break;
		case AGENT_WEITUOGUANLI:
			fragment = new WeiTuoGuanLiFragment();
			break;
		default:
			break;
		}
		
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.replace(R.id.fl_agent_fragment, fragment);
		trans.commitAllowingStateLoss();

	}

	private void initView() {
		flAgentFragment = (FrameLayout) findViewById(R.id.fl_agent_fragment);

	}

}
