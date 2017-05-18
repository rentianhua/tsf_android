package com.dumu.housego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.adapter.AgentChengJiaoErShouAdapter;
import com.dumu.housego.adapter.AgentCommentAdapter;
import com.dumu.housego.adapter.AgentSubmitErShouAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.AgentCommentList;
import com.dumu.housego.entity.AgentDetail;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.AgentCommentPresenter;
import com.dumu.housego.presenter.AgentDetailPresenter;
import com.dumu.housego.presenter.ChengJiaoESPresenter;
import com.dumu.housego.presenter.IAgentCommentPresenter;
import com.dumu.housego.presenter.IAgentDetailPresenter;
import com.dumu.housego.presenter.IChengjiaoESPresenter;
import com.dumu.housego.presenter.ISubmitErShouListpresenter;
import com.dumu.housego.presenter.SubmitErShoulistpresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.ListViewForScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IAgentCommentView;
import com.dumu.housego.view.IAgentDetailView;
import com.dumu.housego.view.IChengJiaoErShouView;
import com.dumu.housego.view.ISubmitErShouListView;

public class AgentDetailActivity extends BaseActivity implements IAgentDetailView,IAgentCommentView,IChengJiaoErShouView,ISubmitErShouListView{
	private List<ErShouFangDetails> ershoudes=new ArrayList<ErShouFangDetails>();
	private List<ErShouFangDetails> ershous=new ArrayList<ErShouFangDetails>();
	
	private List<ErShouFangDetails> ESers=new ArrayList<ErShouFangDetails>();
	private List<ErShouFangDetails> ES=new ArrayList<ErShouFangDetails>();
	
	private List<AgentCommentList> comments=new ArrayList<AgentCommentList>();
	private List<AgentCommentList> comms=new ArrayList<AgentCommentList>();
	private AgentCommentAdapter adapter;
	private AgentChengJiaoErShouAdapter chengjiaoadapter;
	private AgentSubmitErShouAdapter ershouadapter;
	private AgentDetail e;
	private IAgentCommentPresenter commentpresenter;
	private ISubmitErShouListpresenter ershouPresenter;
	private IChengjiaoESPresenter chengjiaoPresenter;
	
	private Handler handler = new Handler() {
		

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				e = (AgentDetail) msg.obj;
				Show();
				break;

			default:
				break;
			}
		}
	};
	
	private LinearLayout llAgentdetailBack;
	private String userid;
	private String username;
	private IAgentDetailPresenter presenter;
	
	private ImageView circleImageView1;
	private TextView tv_agent_name,tv_agent_dengji,tv_agent_mainarea,tv_agent_vtel,tv_agent_biaoqian,tv_agent_biaoqian2,tv_agent_biaoqian3,tv_agent_biaoqian4,tv_agent_biaoqian5,tv_agent_biaoqian6
	,tv_agent_worktime,tv_agent_mianarea2,tv_agent_company,tv_agent_history,tv_agent_pingjunzhouqi,tv_agent_sanshiday;
	private ImageView imageView2;
	private LinearLayout pinglun_youshuju,pinglun_wushuju,chengjiaofangyuan_youshuju,chengjiaofangyuan_wushuju,ershoufangyuan_youshuju,ershgoufangyuan_wushuju;
	private ListViewForScrollView lvPinglun,lvChengjiaofangyuan,lvErshoufangyuan;
	private TextView tv_company,tv_agent_morepinglun,tv_agent_morechengjiaofangyuan,tv_agent_moreershoufangyuan;
	private LinearLayout ershoufangyuan_wushuju;
	protected UserInfo userinfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agent_detail);
		FontHelper.injectFont(findViewById(android.R.id.content));
		presenter=new AgentDetailPresenter(this);
		commentpresenter=new AgentCommentPresenter(this);
		ershouPresenter=new SubmitErShoulistpresenter(this);
		chengjiaoPresenter=new ChengJiaoESPresenter(this);
		initViews();
		initListener();
	}

	@Override
	protected void onResume() {
		ershoudes.clear();
		ershous.clear();
		ESers.clear();
		ES.clear();
		comments.clear();
		comms.clear();
		
		username=getIntent().getStringExtra("username");
		String table="ershou";
		userid=getIntent().getStringExtra("userid");
		presenter.AgentDetail(userid);
		
		commentpresenter.AgentComment(userid);
		ershouPresenter.SubmitErShouList(username, userid, table);
		chengjiaoPresenter.ChengJiaoES(username,userid, table);
		super.onResume();
	}
	
	private void initViews() {
		imageView2=(ImageView) findViewById(R.id.imageView2);
		llAgentdetailBack = (LinearLayout) findViewById(R.id.ll_agentdetail_back);
		circleImageView1=(ImageView) findViewById(R.id.circleImageView1);
		tv_agent_biaoqian=(TextView) findViewById(R.id.tv_agent_biaoqian);
		tv_agent_biaoqian2=(TextView) findViewById(R.id.tv_agent_biaoqian2);
		tv_agent_biaoqian3=(TextView) findViewById(R.id.tv_agent_biaoqian3);
		tv_agent_biaoqian4=(TextView) findViewById(R.id.tv_agent_biaoqian4);
		tv_agent_biaoqian5=(TextView) findViewById(R.id.tv_agent_biaoqian5);
		tv_agent_biaoqian6=(TextView) findViewById(R.id.tv_agent_biaoqian6);
		tv_agent_company=(TextView) findViewById(R.id.tv_agent_company);
		tv_agent_dengji=(TextView) findViewById(R.id.tv_agent_dengji);
		tv_agent_mainarea=(TextView) findViewById(R.id.tv_agent_mainarea);
		tv_agent_mianarea2=(TextView) findViewById(R.id.tv_agent_mianarea2);
		tv_agent_name=(TextView) findViewById(R.id.tv_agent_name);
		tv_agent_vtel=(TextView) findViewById(R.id.tv_agent_vtel);
		tv_agent_worktime=(TextView) findViewById(R.id.tv_agent_worktime);
		tv_company=(TextView) findViewById(R.id.tv_company);
		tv_agent_history=(TextView) findViewById(R.id.tv_agent_history);
		tv_agent_pingjunzhouqi=(TextView) findViewById(R.id.tv_agent_pingjunzhouqi);
		tv_agent_sanshiday=(TextView) findViewById(R.id.tv_agent_sanshiday);
		
		tv_agent_morechengjiaofangyuan=(TextView) findViewById(R.id.tv_agent_morechengjiaofangyuan);
		tv_agent_moreershoufangyuan=(TextView) findViewById(R.id.tv_agent_moreershoufangyuan);
		tv_agent_morepinglun=(TextView) findViewById(R.id.tv_agent_morepinglun);
	
		pinglun_youshuju=(LinearLayout) findViewById(R.id.pinglun_youshuju);
		pinglun_wushuju=(LinearLayout) findViewById(R.id.pinglun_wushuju);
		chengjiaofangyuan_youshuju=(LinearLayout) findViewById(R.id.chengjiaofangyuan_youshuju);
		chengjiaofangyuan_wushuju=(LinearLayout) findViewById(R.id.chengjiaofangyuan_wushuju);
		ershoufangyuan_youshuju=(LinearLayout) findViewById(R.id.ershoufangyuan_youshuju);
		ershoufangyuan_wushuju=(LinearLayout) findViewById(R.id.ershoufangyuan_wushuju);
		
		lvPinglun=(ListViewForScrollView) findViewById(R.id.lv_agent_pinglun);
		lvChengjiaofangyuan=(ListViewForScrollView) findViewById(R.id.lv_agent_chengjiaofangyuan);
		lvErshoufangyuan=(ListViewForScrollView) findViewById(R.id.lv_agent_ershoufangyuan);
		
	}

	private void initListener() {
		llAgentdetailBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		imageView2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				userinfo=HouseGoApp.getContext().getCurrentUserInfo();
				if(userinfo==null){
					MyToastShowCenter.CenterToast(getApplicationContext(), "你还没有登录，请先登录！");
					Intent i=new Intent(AgentDetailActivity.this, LoginActivity.class);
					startActivity(i);
				}else{
					Intent i=new Intent(AgentDetailActivity.this, ShowAgentDataActivity.class);
					i.putExtra("TYPE", "COMMENT");
					i.putExtra("userid", userid);
					startActivity(i);
				}
				
			}
		});
		
		tv_agent_morechengjiaofangyuan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(AgentDetailActivity.this, ShowAgentDataActivity.class);
				i.putExtra("TYPE", "LIST");
				i.putExtra("TAG", "chengjiao");
				i.putExtra("chengjiao", (Serializable)ershoudes);
				startActivity(i);
				
			}
		});
		tv_agent_moreershoufangyuan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(AgentDetailActivity.this, ShowAgentDataActivity.class);
				i.putExtra("TYPE", "LIST");
				i.putExtra("TAG", "ershou");
				i.putExtra("ershou", (Serializable)ESers);
				startActivity(i);
				
			}
		});
		tv_agent_morepinglun.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(AgentDetailActivity.this, ShowAgentDataActivity.class);
				i.putExtra("TYPE", "LIST");
				i.putExtra("TAG", "pinglun");
				i.putExtra("comment", (Serializable)comments);
				startActivity(i);
				
			}
		});
		
		lvChengjiaofangyuan.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ErShouFangDetails e=	ershoudes.get(position);
				Intent i = new Intent(AgentDetailActivity.this, ErShouFangDetailsActivity.class);
				String catid=e.getCatid();
				String ID=e.getId();
				i.putExtra("catid", catid);
				i.putExtra("id", ID);
				startActivity(i);
				
			}
		});
		
		lvErshoufangyuan.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ErShouFangDetails e=ESers.get(position);
				Intent i = new Intent(AgentDetailActivity.this, ErShouFangDetailsActivity.class);
				String catid=e.getCatid();
				String ID=e.getId();
				i.putExtra("catid", catid);
				i.putExtra("id", ID);
				startActivity(i);
				
			}
		});
		
	}
	
	
	protected void Show() {
		//经纪人基本信息
		String url=e.getUserpic();
		//DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
		//		.bitmapConfig(Bitmap.Config.RGB_565).build();
		//ImageLoader.getInstance().displayImage(url,circleImageView1 , options);
		Glide.with(this).load(url).into(circleImageView1);

		tv_agent_name.setText(e.getRealname());
		tv_agent_mainarea.setText(e.getMainareaids());
		tv_agent_mianarea2.setText(e.getMainareaids());
		tv_agent_vtel.setText(e.getVtel());
		tv_agent_worktime.setText(e.getWorktime()+"年");
		if(e.getLeixing().equals("个人")){
			tv_company.setText("");
			tv_agent_company.setText("");
		}else{
			tv_agent_company.setText(e.getConame());
		}
		//
		
		if(!e.getBiaoqian().equals("")){
			String[] b=e.getBiaoqian().split(",");
			int l=b.length;
			tv_agent_biaoqian.setVisibility(View.GONE);
			tv_agent_biaoqian2.setVisibility(View.GONE);
			tv_agent_biaoqian3.setVisibility(View.GONE);
			tv_agent_biaoqian4.setVisibility(View.GONE);
			tv_agent_biaoqian5.setVisibility(View.GONE);
			tv_agent_biaoqian6.setVisibility(View.GONE);

			switch (l) {
			case 1:
				tv_agent_biaoqian.setText(b[0]);
				tv_agent_biaoqian.setVisibility(View.VISIBLE);
				break;
			case 2:
				tv_agent_biaoqian.setText(b[0]);
				tv_agent_biaoqian2.setText(b[1]);
				tv_agent_biaoqian.setVisibility(View.VISIBLE);
				tv_agent_biaoqian2.setVisibility(View.VISIBLE);
				break;
			case 3:
				tv_agent_biaoqian.setText(b[0]);
				tv_agent_biaoqian2.setText(b[1]);
				tv_agent_biaoqian3.setText(b[2]);
				tv_agent_biaoqian.setVisibility(View.VISIBLE);
				tv_agent_biaoqian2.setVisibility(View.VISIBLE);
				tv_agent_biaoqian3.setVisibility(View.VISIBLE);
				break;
			case 4:
				tv_agent_biaoqian.setText(b[0]);
				tv_agent_biaoqian2.setText(b[1]);
				tv_agent_biaoqian3.setText(b[2]);
				tv_agent_biaoqian4.setText(b[3]);
				tv_agent_biaoqian.setVisibility(View.VISIBLE);
				tv_agent_biaoqian3.setVisibility(View.VISIBLE);
				tv_agent_biaoqian2.setVisibility(View.VISIBLE);
				tv_agent_biaoqian4.setVisibility(View.VISIBLE);
				break;
			case 5:
				tv_agent_biaoqian.setText(b[0]);
				tv_agent_biaoqian2.setText(b[1]);
				tv_agent_biaoqian3.setText(b[2]);
				tv_agent_biaoqian4.setText(b[3]);
				tv_agent_biaoqian5.setText(b[4]);
				tv_agent_biaoqian.setVisibility(View.VISIBLE);
				tv_agent_biaoqian3.setVisibility(View.VISIBLE);
				tv_agent_biaoqian2.setVisibility(View.VISIBLE);
				tv_agent_biaoqian4.setVisibility(View.VISIBLE);
				tv_agent_biaoqian5.setVisibility(View.VISIBLE);
				break;
			case 6:
				tv_agent_biaoqian.setText(b[0]);
				tv_agent_biaoqian2.setText(b[1]);
				tv_agent_biaoqian3.setText(b[2]);
				tv_agent_biaoqian4.setText(b[3]);
				tv_agent_biaoqian5.setText(b[4]);
				tv_agent_biaoqian6.setText(b[5]);

				tv_agent_biaoqian.setVisibility(View.VISIBLE);
				tv_agent_biaoqian2.setVisibility(View.VISIBLE);
				tv_agent_biaoqian3.setVisibility(View.VISIBLE);
				tv_agent_biaoqian4.setVisibility(View.VISIBLE);
				tv_agent_biaoqian5.setVisibility(View.VISIBLE);
				tv_agent_biaoqian6.setVisibility(View.VISIBLE);
				break;
			}
		}else{
			tv_agent_biaoqian.setVisibility(View.GONE);
			tv_agent_biaoqian2.setVisibility(View.GONE);
			tv_agent_biaoqian3.setVisibility(View.GONE);
			tv_agent_biaoqian4.setVisibility(View.GONE);
			tv_agent_biaoqian5.setVisibility(View.GONE);
			tv_agent_biaoqian6.setVisibility(View.GONE);
		}
		//等级
		String dengji="";
		if(!e.getDengji().equals(null) && !e.getDengji().equals("")){
			int dj=	Integer.valueOf(e.getDengji());
			switch (dj) {
			case 1:
				dengji="普通经纪人";
				tv_agent_dengji.setText(dengji);
				break;
			case 2:
				dengji="优秀经纪人";
				tv_agent_dengji.setText(dengji);
				break;
			case 3:
				dengji="高级经纪人";
				tv_agent_dengji.setText(dengji);
				break;
			case 4:
				dengji="资深经纪人";
				tv_agent_dengji.setText(dengji);
				break;
			}
		}else{
			tv_agent_dengji.setText("");
		}
		//
		//tv_agent_history,tv_agent_pingjunzhouqi,tv_agent_sanshiday
		//$info['chengjiao_count']=0;
		//$info['weituo_count']=0;
		//$info['daikan_count']=0;	
		if(e.getChengjiao_count()==null)
			tv_agent_history.setText("0");
		else
			tv_agent_history.setText(e.getChengjiao_count()+"");
		if(e.getWeituo_count()==null)
			tv_agent_pingjunzhouqi.setText("0");
		else
			tv_agent_pingjunzhouqi.setText(e.getWeituo_count()+"");
		if(e.getDaikan_count()==null)
			tv_agent_sanshiday.setText("0");
		else
			tv_agent_sanshiday.setText(e.getDaikan_count()+"");
	}
	
	@Override
	public void AgentDetail(AgentDetail agentdetail) {
		Message msg = new Message();
		msg.what = 1;
		msg.obj = agentdetail;
		handler.sendMessage(msg);
	}


	@Override
	public void AgentCommentSuccess(List<AgentCommentList> comments) {
		this.comments=comments;
		pinglun_wushuju.setVisibility(View.GONE);
		pinglun_youshuju.setVisibility(View.VISIBLE);
		if(comments.size()>=2){
			comms.add(comments.get(0));
			comms.add(comments.get(1));
			adapter=new AgentCommentAdapter(comms, getApplicationContext());
			lvPinglun.setAdapter(adapter);
		}else{
			adapter=new AgentCommentAdapter(comments, getApplicationContext());
			lvPinglun.setAdapter(adapter);
		}
		
	}

	@Override
	public void AgentCommentFail(String info) {
		if(info.equals("没有评论")){
			pinglun_wushuju.setVisibility(View.VISIBLE);
			pinglun_youshuju.setVisibility(View.GONE);
		}
		
	}

	@Override
	public void ChengjiaoErShouSuccess(List<ErShouFangDetails> ershoudes) {
		this.ershoudes=ershoudes;
		
		chengjiaofangyuan_wushuju.setVisibility(View.GONE);
		chengjiaofangyuan_youshuju.setVisibility(View.VISIBLE);
		if(ershoudes.size()>=3){
			
			ershous.add(ershoudes.get(0));
			ershous.add(ershoudes.get(1));
			ershous.add(ershoudes.get(2));
			
			chengjiaoadapter=new AgentChengJiaoErShouAdapter(ershous, getApplicationContext());
			lvChengjiaofangyuan.setAdapter(chengjiaoadapter);
		}else{
			chengjiaoadapter=new AgentChengJiaoErShouAdapter(ershoudes, getApplicationContext());
			lvChengjiaofangyuan.setAdapter(chengjiaoadapter);
		}
		
	}

	@Override
	public void chengjiaoErShouFail(String info) {
		chengjiaofangyuan_wushuju.setVisibility(View.VISIBLE);
		chengjiaofangyuan_youshuju.setVisibility(View.GONE);
		
	}

	@Override
	public void SubmitListSuccess(List<ErShouFangDetails> ESers) {
		this.ESers=ESers;
	
		ershoufangyuan_youshuju.setVisibility(View.VISIBLE);
		ershoufangyuan_wushuju.setVisibility(View.GONE);
		
		if(ESers.size()>=3){
			ES.add(ESers.get(0));
			ES.add(ESers.get(1));
			ES.add(ESers.get(2));
			ershouadapter=new AgentSubmitErShouAdapter(ES, getApplicationContext());
			lvErshoufangyuan.setAdapter(ershouadapter);
		}else{
			ershouadapter=new AgentSubmitErShouAdapter(ESers, getApplicationContext());
			lvErshoufangyuan.setAdapter(ershouadapter);
		}
		
	}

	@Override
	public void SubmitListError(String info) {
		ershoufangyuan_wushuju.setVisibility(View.VISIBLE);
		ershoufangyuan_youshuju.setVisibility(View.GONE);
		
	}
}
