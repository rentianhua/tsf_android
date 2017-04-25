package com.dumu.housego.framgent;

import java.util.List;

import com.dumu.housego.AgentMainActivity;
import com.dumu.housego.BlockTradeMainActivity;
import com.dumu.housego.ErShouFangMainActivity;
import com.dumu.housego.HousePuchaseMainActivity;
import com.dumu.housego.MapHouseMainActivity;
import com.dumu.housego.NewHouseMainActivity;
import com.dumu.housego.ProprietorMainActivity;
import com.dumu.housego.R;
import com.dumu.housego.RentingMainActivity;
import com.dumu.housego.SearchMainActivity;
import com.dumu.housego.WapRecommedMainActivity;
import com.dumu.housego.WebHousePriceDetailActivity;
import com.dumu.housego.adapter.RecommendHouseAdapter;
import com.dumu.housego.entity.FangJiaXiangQing;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.entity.ShouYeBiaoTi;
import com.dumu.housego.model.IFangJiaXiangQingPresenter;
import com.dumu.housego.presenter.FangJiaXiangQingPresenter;
import com.dumu.housego.presenter.IRecommendHousePresenter;
import com.dumu.housego.presenter.IShouYeBiaoTiPresenter;
import com.dumu.housego.presenter.RecommendHousePresenter;
import com.dumu.housego.presenter.ShouYeBiaoTiPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.view.IFangjiaXiangQingView;
import com.dumu.housego.view.IShopGuideView;
import com.dumu.housego.view.IShouYeBiaoTiView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FirstFramgent extends Fragment implements IFangjiaXiangQingView,IShopGuideView ,IShouYeBiaoTiView{
	private RecommendHouseAdapter recommendAdapter;
	private List<RecommendNews> recommends;
	private IRecommendHousePresenter presenter;
	private TextView tv_first_search,tv_frist_title,tv_second_title;
	private ListView lvShopHouseGudie;
	private RadioButton rbErShouFang;
	private RadioButton rbNewHouse;
	private RadioButton rbZuFang;
	private RadioButton rbJinJiRen;
	private RadioButton rbYeZhuWeiTuo;
	private RadioButton rbBigBussnies;
	private RadioButton rbMapHouse;
	private RadioButton rbShopHouse;
	private RelativeLayout rlShopHouseGuide;
	private TextView tvMonthNumber, tvPriceNumber, tvHouseNumber;
	private LinearLayout llSearch;
	private MyReboundScrollView scrollview;
	private IShouYeBiaoTiPresenter shouyePresenter;
	private List<ShouYeBiaoTi>biaotis;
	private IFangJiaXiangQingPresenter FangPresenter;
	private List<FangJiaXiangQing>fangs;
	
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				biaotis=(List<ShouYeBiaoTi>) msg.obj;
				
				showBiaoTi(biaotis);
				break;
			case 2:
				showFangjiaXingqing(fangs);
				break;
			default:
				break;
			}
			
			super.handleMessage(msg);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.framgent_first, null);
		setViews(view);
		setListener();
		presenter = new RecommendHousePresenter(this);
		shouyePresenter=new ShouYeBiaoTiPresenter(this);
		FangPresenter=new FangJiaXiangQingPresenter(this);
		
		FangPresenter.fangjia();
		presenter.LoadRecommend();
		shouyePresenter.shouye();
		FontHelper.injectFont(view);
		return view;
	}

	protected void showFangjiaXingqing(List<FangJiaXiangQing> fangs) {
		 FangJiaXiangQing f=fangs.get(0);
		 tvHouseNumber.setText(f.getComp_count()+"");
		 tvMonthNumber.setText(f.getMonth()+"月成交量");
		 tvPriceNumber.setText(f.getAvg_price()+"");
		
	}

	protected void showBiaoTi(List<ShouYeBiaoTi> biaotis) {
		if(biaotis!=null){
			String ZhuBiaoti=biaotis.get(0).getValue();
			String FuBiaoti=biaotis.get(1).getValue();
			tv_frist_title.setText(ZhuBiaoti);
			tv_second_title.setText(FuBiaoti);
		}
	
		
	}

	private void setListener() {
		
		llSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getActivity(), SearchMainActivity.class);
				i.putExtra("TAG", "all");
				startActivity(i);
			}
		});
		
		
		
		
		rbBigBussnies.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), BlockTradeMainActivity.class));

			}
		});

		rbErShouFang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), ErShouFangMainActivity.class));

			}
		});

		rbJinJiRen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), AgentMainActivity.class));
//				startActivity(new Intent(getActivity(),ImageGrallyMain.class));
			}
		});

		rbMapHouse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), MapHouseMainActivity.class));

			}
		});

		rbNewHouse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), NewHouseMainActivity.class));

			}
		});

		rbShopHouse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), HousePuchaseMainActivity.class));

			}
		});

		rbYeZhuWeiTuo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), ProprietorMainActivity.class));

			}
		});

		rbZuFang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), RentingMainActivity.class));

			}
		});

		lvShopHouseGudie.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				Intent i = new Intent(getActivity(), WapRecommedMainActivity.class);
				String url = recommends.get(position).getUrl();//"http://www.taoshenfang.com" + recommends.get(position).getThumb();
				//String title = recommends.get(position).getTitle();
				//String content = recommends.get(position).getDescription();
				//i.putExtra("content", content);
				i.putExtra("url", url);
				//i.putExtra("title", title);
				startActivity(i);
			}
		});

		

	}

	private void setViews(View view) {

		tv_first_search = (TextView) view.findViewById(R.id.tv_first_search);
		tv_frist_title = (TextView) view.findViewById(R.id.tv_frist_title);
		tv_second_title = (TextView) view.findViewById(R.id.tv_second_title);
		
		lvShopHouseGudie = (ListView) view.findViewById(R.id.lv_ShopGuide);
//		rlHouseDetails = (RelativeLayout) view.findViewById(R.id.HouseDetails);
		rlShopHouseGuide = (RelativeLayout) view.findViewById(R.id.ShopHouseGuide);
		tvHouseNumber = (TextView) view.findViewById(R.id.tv_housenumber);
		tvMonthNumber = (TextView) view.findViewById(R.id.monthnumber);
		tvPriceNumber = (TextView) view.findViewById(R.id.tv_pricenumber);
		rbBigBussnies = (RadioButton) view.findViewById(R.id.rb_dazongjiaoyi);
		rbErShouFang = (RadioButton) view.findViewById(R.id.rb_ershoufang);
		rbJinJiRen = (RadioButton) view.findViewById(R.id.rb_jingjiren);
		rbMapHouse = (RadioButton) view.findViewById(R.id.rb_dituzhaofang);
		rbNewHouse = (RadioButton) view.findViewById(R.id.rb_xinfang);
		rbShopHouse = (RadioButton) view.findViewById(R.id.rb_goufangxuzhi);
		rbYeZhuWeiTuo = (RadioButton) view.findViewById(R.id.rb_yezhuweituo);
		rbZuFang = (RadioButton) view.findViewById(R.id.rb_zufang);
		llSearch = (LinearLayout) view.findViewById(R.id.ll_search);

		scrollview = (MyReboundScrollView) view.findViewById(R.id.first_scrollview);
		scrollview.smoothScrollTo(0, 0);

	}

	@Override
	public void showData(List<RecommendNews> recommends) {

		this.recommends = recommends;
		recommendAdapter = new RecommendHouseAdapter(recommends, getActivity());
		lvShopHouseGudie.setAdapter(recommendAdapter);

	}

	@Override
	public void shouye(List<ShouYeBiaoTi> biaotis) {
		Message msg=new Message();
		msg.obj=biaotis;
		msg.what=1;
		handler.sendMessage(msg);
	}

	@Override
	public void fangjia(List<FangJiaXiangQing> fangs) {
		this.fangs=fangs;
		Message msg=new Message();
		msg.what=2;
		handler.sendMessage(msg);
		
	}


}
