package com.dumu.housego.framgent;

import java.util.List;

import org.xutils.x;

import com.bumptech.glide.Glide;
import com.dumu.housego.AgentChangeUserInfoActivity;
import com.dumu.housego.AgentPersonalActivity;
import com.dumu.housego.MySettingMainActivity;
import com.dumu.housego.PersonalMainActivity;
import com.dumu.housego.PuTongMyGuanZhuActivity;
import com.dumu.housego.R;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.util.CircleImageView;
import com.dumu.housego.util.FontHelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyFramgent extends Fragment {
	private List<Fragment> fragmentss;
	private TextView tvLoginRegist;
	private RelativeLayout rlSetting;
	private RelativeLayout rlHistory;
	private RelativeLayout rlYuyueguanli;
	private RelativeLayout rlMyentrust;
	private RelativeLayout rlMyrenting;
	private RelativeLayout rlMyershoufang,rl_no_goufangjsq,rl_PT_goufangjsq,rl_AT_goufangjsq;
	private RelativeLayout rl_putong_mychuzu, rl_putong_myzhufang, rl_putong_myshophouse,rl_putong_mygoudidingdan;
	private CircleImageView ivMyPic;
	private UserInfo userinfo;
	private RelativeLayout rlPTMyGuanZhu, rl_putong_mysalehouse;
	private LinearLayout llMysettingAgentLogin, llMysettingPuTongLogin, llMysettingNologin;
	private RelativeLayout rlAgentErshou, rlAgentRenting, rlAgentChengjiaoHouse,rlAgentGoudidingdan,rl_putong_myyouhuiquan;
	private RelativeLayout rl_agent_myyouhuiquan,rl_agent_weituoguanli,rl_agent_qiuzuguanli,rl_agent_qiugouguanli,rl_putong_myhistroy,rl_agent_myhistroy;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.framgent_my, null);

		setViews(view);
		x.view().inject(view);
		setListener();

		FontHelper.injectFont(view);
		return view;
	}

	@Override
	public void onResume() {
		userinfo=null;
		userinfo = HouseGoApp.getLoginInfo(getContext());
		
		if (userinfo != null) {
			String nickname = userinfo.getRealname();
			if(nickname == null || nickname.length()<=0)
				nickname = userinfo.getUsername();
			tvLoginRegist.setText(nickname + "");
			String imgurl = userinfo.getUserpic();
			Glide.with(getActivity()).load(imgurl).into(ivMyPic);
			tvLoginRegist.setClickable(false);
			ivMyPic.setClickable(true);

			if (userinfo.getModelid().equals("35")) {
				llMysettingNologin.setVisibility(View.GONE);
				llMysettingAgentLogin.setVisibility(View.GONE);
				llMysettingPuTongLogin.setVisibility(View.VISIBLE);

			} else {
				llMysettingPuTongLogin.setVisibility(View.GONE);
				llMysettingNologin.setVisibility(View.GONE);
				llMysettingAgentLogin.setVisibility(View.VISIBLE);

			}

		} else {
			tvLoginRegist.setText("登录/注册");

			ivMyPic.setImageResource(R.drawable.touxiang2);
			// Glide.with(getActivity()).load(imgurl).into(ivMyPic);
			llMysettingAgentLogin.setVisibility(View.GONE);
			llMysettingPuTongLogin.setVisibility(View.GONE);
			llMysettingNologin.setVisibility(View.VISIBLE);
			ivMyPic.setClickable(false);
			tvLoginRegist.setClickable(true);
		}

		super.onResume();
	}

	private void setListener() {

		llMysettingNologin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), LoginActivity.class));

			}
		});

		ivMyPic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (userinfo.getModelid().equals("35")) {
					startActivity(new Intent(getActivity(), PersonalMainActivity.class));
				} else {
					startActivity(new Intent(getActivity(), AgentChangeUserInfoActivity.class));
				}
			}
		});

		rlSetting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (userinfo != null) {
					
					Intent i3 = new Intent(getActivity(), MySettingMainActivity.class);
					startActivity(i3);
//					Intent intent = new Intent();
//					intent.setClass(getActivity(), MySettingMainActivity.class);
//					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //注意本行的FLAG设置
//					startActivity(intent);
					
					
					
				} else {
					startActivity(new Intent(getActivity(), LoginActivity.class));
				}

			}
		});

		rlMyrenting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), LoginActivity.class));

			}
		});

		tvLoginRegist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
		});

		rlPTMyGuanZhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), PuTongMyGuanZhuActivity.class);
				i.putExtra("v", "guanzhu");
				startActivity(i);

			}
		});

		rl_putong_mychuzu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), PuTongMyGuanZhuActivity.class);
				i.putExtra("v", "rentinghouse");
				startActivity(i);

			}
		});

		rl_putong_mysalehouse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), PuTongMyGuanZhuActivity.class);
				i.putExtra("v", "ershouhouse");
				startActivity(i);

			}
		});

		rl_putong_myzhufang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), PuTongMyGuanZhuActivity.class);
				i.putExtra("v", "qiuzu");
				startActivity(i);

			}
		});

		rl_putong_myshophouse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), PuTongMyGuanZhuActivity.class);
				i.putExtra("v", "maifang");
				startActivity(i);

			}
		});
		
		rl_putong_mygoudidingdan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), PuTongMyGuanZhuActivity.class);
				i.putExtra("v", "goudi");
				startActivity(i);

			}
		});
		
		rl_putong_myyouhuiquan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), PuTongMyGuanZhuActivity.class);
				i.putExtra("v", "yhq");
				startActivity(i);
				
			}
		});
		
		rl_putong_myhistroy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), PuTongMyGuanZhuActivity.class);
				i.putExtra("v", "histroy");
				startActivity(i);
			}
		});
		
		
		
		//

		rlAgentErshou.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AgentPersonalActivity.class);
				i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.AGENT_ERSHOU_LIST);
				startActivity(i);
			}
		});
		rlAgentRenting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AgentPersonalActivity.class);
				i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.AGENT_RENTING_LIST);
				startActivity(i);
			}
		});

		rlAgentChengjiaoHouse.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AgentPersonalActivity.class);
				i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.AGENT_CHENGJIAO);
				startActivity(i);
			}
		});
		
		rlAgentGoudidingdan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AgentPersonalActivity.class);
				i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.AGENT_GOUDI);
				startActivity(i);
			}
		});
		
		rl_agent_myyouhuiquan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AgentPersonalActivity.class);
				i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.AGENT_YHQ);
				startActivity(i);
			}
		});
		
		rl_agent_qiuzuguanli.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AgentPersonalActivity.class);
				i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.AGENT_QIUZU);
				startActivity(i);
			}
		});
		rl_agent_qiugouguanli.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AgentPersonalActivity.class);
				i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.AGENT_QIUGOU);
				startActivity(i);
			}
		});
		
		rl_agent_myhistroy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AgentPersonalActivity.class);
				i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.AGENT_HISTROY);
				startActivity(i);
			}
		});
		
		rl_no_goufangjsq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AgentPersonalActivity.class);
				i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.GOUFANGJISUANQI);
				startActivity(i);
			}
		});
		
		rl_PT_goufangjsq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AgentPersonalActivity.class);
				i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.GOUFANGJISUANQI);
				startActivity(i);
			}
		});
		rl_AT_goufangjsq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AgentPersonalActivity.class);
				i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.GOUFANGJISUANQI);
				startActivity(i);
			}
		});
		
		rl_agent_weituoguanli.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AgentPersonalActivity.class);
				i.putExtra("FRAGMENT_KEY", AgentPersonalActivity.AGENT_WEITUOGUANLI);
				startActivity(i);
			}
		});
		
		
	}

	private void setViews(View view) {
		
		rl_no_goufangjsq=(RelativeLayout) view.findViewById(R.id.rl_no_goufangjsq);
		rl_PT_goufangjsq=(RelativeLayout) view.findViewById(R.id.rl_PT_goufangjsq);
		rl_AT_goufangjsq=(RelativeLayout) view.findViewById(R.id.rl_AT_goufangjsq);
		
		rl_putong_myhistroy=(RelativeLayout) view.findViewById(R.id.rl_putong_myhistroy);
		rl_agent_myhistroy=(RelativeLayout) view.findViewById(R.id.rl_agent_myhistroy);
		
		
		rl_agent_qiuzuguanli=(RelativeLayout) view.findViewById(R.id.rl_agent_qiuzuguanli);
		rl_agent_qiugouguanli=(RelativeLayout) view.findViewById(R.id.rl_agent_qiugouguanli);
		rl_agent_weituoguanli=(RelativeLayout) view.findViewById(R.id.rl_agent_weituoguanli);
		
		tvLoginRegist = (TextView) view.findViewById(R.id.tv_my_login_regist);
		rlSetting = (RelativeLayout) view.findViewById(R.id.rl_settings);
		rlHistory = (RelativeLayout) view.findViewById(R.id.rl_Histroy);
		rlMyershoufang = (RelativeLayout) view.findViewById(R.id.rl_Myershoufang);
		rlMyrenting = (RelativeLayout) view.findViewById(R.id.rl_Myrenting);
		rlMyentrust = (RelativeLayout) view.findViewById(R.id.rl_Myentrust);
		rlYuyueguanli = (RelativeLayout) view.findViewById(R.id.rl_Yuyueguanli);
		ivMyPic = (CircleImageView) view.findViewById(R.id.iv_my_Photo);

		llMysettingNologin = (LinearLayout) view.findViewById(R.id.ll_mysetting_nologin);
		llMysettingAgentLogin = (LinearLayout) view.findViewById(R.id.ll_mysetting_agent_login);
		llMysettingPuTongLogin = (LinearLayout) view.findViewById(R.id.ll_mysetting_putong_login);
		rlPTMyGuanZhu = (RelativeLayout) view.findViewById(R.id.rl_putong_myguanzhu);
		rl_putong_myzhufang = (RelativeLayout) view.findViewById(R.id.rl_putong_myzhufang);
		rl_putong_mychuzu = (RelativeLayout) view.findViewById(R.id.rl_putong_mychuzu);
		rl_putong_mysalehouse = (RelativeLayout) view.findViewById(R.id.rl_putong_mysalehouse);
		rl_putong_mygoudidingdan=(RelativeLayout) view.findViewById(R.id.rl_putong_mygoudidingdan);
		
		
		rlAgentErshou = (RelativeLayout) view.findViewById(R.id.rl_agent_ershoufangguanli);
		rlAgentRenting = (RelativeLayout) view.findViewById(R.id.rl_agent_chuzufangguanli);
		rl_putong_myshophouse = (RelativeLayout) view.findViewById(R.id.rl_putong_myshophouse);

		rlAgentChengjiaoHouse = (RelativeLayout) view.findViewById(R.id.rl_agent_chengjiaofangyuan);
		rlAgentGoudidingdan=(RelativeLayout) view.findViewById(R.id.rl_agent_goudidingdan);
	
		rl_putong_myyouhuiquan=(RelativeLayout) view.findViewById(R.id.rl_putong_myyouhuiquan);
		rl_agent_myyouhuiquan=(RelativeLayout) view.findViewById(R.id.rl_agent_myyouhuiquan);
	}

}
