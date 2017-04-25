package com.dumu.housego.framgent;

import java.util.ArrayList;
import java.util.List;

import com.aigestudio.wheelpicker.view.WheelPicker;
import com.aigestudio.wheelpicker.view.WheelPicker.OnWheelChangeListener;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.BuyHouseSubmitPresenter;
import com.dumu.housego.presenter.IBuyHouseSubmitPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IBuyHouseSubmitView;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("ResourceAsColor")
public class PTBuyHouseSubmitFragment extends Fragment implements IBuyHouseSubmitView {
	private LinearLayout ll_back_ptbuyhousesubmit;
	private TextView tv_buyhouse_phone, tv_buyhouse_housearea, tv_buyhouse_tingshi, tv_buyhouse_jine;
	private EditText et_buyhouse_chenghu;
	private RelativeLayout rl_buyhouse_fangyuanquyu, rl_buyhouse_qiwangtingshi, rl_buyhouse_yusuanjine;
	private Button btn_buyhouse_submit;
	private UserInfo userinfo;
	protected String str;

	private IBuyHouseSubmitPresenter presenter;

	private List<String> JinE = new ArrayList<String>();
	private List<String> TingShi = new ArrayList<String>();

	private List<String> Area = new ArrayList<String>();
	private List<String> MinArea = new ArrayList<String>();

	private AddressModel model2 = new AddressModel();

	String AREA;
	String MINAREA;
	private List<Address> minarea;
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

	String t1 = "1室";
	String t2 = "2室";
	String t3 = "3室";
	String t4 = "4室";
	String t5 = "5室";
	String t6 = "5室以上";

	String J2 = "100万以下";
	String J3 = "100-200万";
	String J4 = "200-300万";
	String J5 = "300-400万";
	String J6 = "400万以上";
	private Handler handler = new Handler();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pt_buyhousesubmit, null);
		TingShi.add(t1);
		TingShi.add(t2);
		TingShi.add(t3);
		TingShi.add(t4);
		TingShi.add(t5);
		TingShi.add(t6);

		JinE.add(J2);
		JinE.add(J3);
		JinE.add(J4);
		JinE.add(J5);
		JinE.add(J6);

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

		presenter = new BuyHouseSubmitPresenter(this);
		FontHelper.injectFont(view);
		initView(view);
		setListener();
		return view;
	}

	@Override
	public void onResume() {
		userinfo = HouseGoApp.getContext().getCurrentUserInfo();
		String username = userinfo.getUsername();
		tv_buyhouse_phone.setText(username);

		super.onResume();
	}

	private void initView(View view) {
		ll_back_ptbuyhousesubmit = (LinearLayout) view.findViewById(R.id.ll_back_ptbuyhousesubmit);

		tv_buyhouse_phone = (TextView) view.findViewById(R.id.tv_buyhouse_phone);
		tv_buyhouse_housearea = (TextView) view.findViewById(R.id.tv_buyhouse_housearea);
		tv_buyhouse_tingshi = (TextView) view.findViewById(R.id.tv_buyhouse_tingshi);
		tv_buyhouse_jine = (TextView) view.findViewById(R.id.tv_buyhouse_jine);

		et_buyhouse_chenghu = (EditText) view.findViewById(R.id.et_buyhouse_chenghu);

		rl_buyhouse_fangyuanquyu = (RelativeLayout) view.findViewById(R.id.rl_buyhouse_fangyuanquyu);
		rl_buyhouse_qiwangtingshi = (RelativeLayout) view.findViewById(R.id.rl_buyhouse_qiwangtingshi);
		rl_buyhouse_yusuanjine = (RelativeLayout) view.findViewById(R.id.rl_buyhouse_yusuanjine);

		btn_buyhouse_submit = (Button) view.findViewById(R.id.btn_buyhouse_submit);

	}

	private void setListener() {
		ll_back_ptbuyhousesubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment = new PTBuyHouseListFragment();
				FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.rl_container, fragment);
				trans.commitAllowingStateLoss();
			}
		});

		rl_buyhouse_fangyuanquyu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HouseAreaAlertDialog();

			}
		});

		rl_buyhouse_qiwangtingshi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlerDialogWheelPicker(TingShi, tv_buyhouse_tingshi, "请选择预算厅室");

			}
		});

		rl_buyhouse_yusuanjine.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlerDialogWheelPicker(JinE, tv_buyhouse_jine, "请选择预算金额");

			}
		});

		btn_buyhouse_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 获取地区area的id;
				for (int i = 0; i < minarea.size(); i++) {
					String area = minarea.get(i).getName();
					Log.e("area", "area=" + area + "   " + MINAREA);

					if (area.equals(MINAREA.trim())) {
						PTBuyHouseSubmitFragment.this.area1 = minarea.get(i).getId() + "";
						Log.e("area1", "area1=" + area1 + "   " + MINAREA);
						break;
					} else {
						Log.e("xxxx--area1", "xxx---area1=" + area1 + "   " + MINAREA);
					}

				}

				String[] s = tv_buyhouse_tingshi.getText().toString().split("室");
				String[] j = tv_buyhouse_jine.getText().toString().split("万");

				String username = userinfo.getUsername();
				String province = "1";
				String city = pid + "";
				String shi ;
				if(tv_buyhouse_tingshi.getText().toString().equals("5室以上")){
					shi="6";
				}else{
					shi = s[0];	
				}
				String zongjiarange = j[0];

				String chenghu = et_buyhouse_chenghu.getText().toString();

				String title = tv_buyhouse_housearea.getText().toString() + tv_buyhouse_tingshi.getText().toString()
						+ tv_buyhouse_jine.getText().toString();

				presenter.buyhousesubmit(username, province, city, area1, shi, zongjiarange, chenghu, title);

			}
		});

	}

	public void AlerDialogWheelPicker(List<String> Data, final TextView tv, String title) {
		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.alerlog_wheelpicker_zulin);
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		WheelPicker picker = (WheelPicker) window.findViewById(R.id.zulin_wheel);
		TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
		TextView tvCancle = (TextView) window.findViewById(R.id.tv_wheel_cancle);
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

	protected void HouseAreaAlertDialog() {

		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.ac_main_dialog2);
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		WheelPicker picker1 = (WheelPicker) window.findViewById(R.id.main_dialog_container1);
		final WheelPicker picker2 = (WheelPicker) window.findViewById(R.id.main_dialog_container2);
		final WheelPicker picker3 = (WheelPicker) window.findViewById(R.id.main_dialog_container3);
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

//		picker1.setStyle(WheelPicker.STRAIGHT);
//		picker2.setStyle(WheelPicker.STRAIGHT);
//		picker3.setStyle(WheelPicker.STRAIGHT);

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
				MinArea.clear();
				if (data.equals("罗湖区")) {
					PTBuyHouseSubmitFragment.this.pid = "2";
				} else if (data.equals("福田区")) {
					PTBuyHouseSubmitFragment.this.pid = "4";
				} else if (data.equals("南山区")) {
					PTBuyHouseSubmitFragment.this.pid = "5";
				} else if (data.equals("盐田区")) {
					PTBuyHouseSubmitFragment.this.pid = "6";
				} else if (data.equals("宝安区")) {
					PTBuyHouseSubmitFragment.this.pid = "7";
				} else if (data.equals("龙岗新区")) {
					PTBuyHouseSubmitFragment.this.pid = "8";
				} else if (data.equals("龙华新区")) {
					PTBuyHouseSubmitFragment.this.pid = "9";
				} else if (data.equals("光明新区")) {
					PTBuyHouseSubmitFragment.this.pid = "10";
				} else if (data.equals("坪山新区")) {
					PTBuyHouseSubmitFragment.this.pid = "11";
				} else if (data.equals("大鹏新区")) {
					PTBuyHouseSubmitFragment.this.pid = "12";
				} else if (data.equals("东莞")) {
					PTBuyHouseSubmitFragment.this.pid = "13";
				} else {
					PTBuyHouseSubmitFragment.this.pid = "14";
				}

				model2.address(pid, new AsycnCallBack() {
					@Override
					public void onSuccess(Object success) {
						PTBuyHouseSubmitFragment.this.minarea = (List<Address>) success;

						for (Address address : minarea) {
							String ad = address.getName();
							MinArea.add(ad);
						}
						MinArea.add(0,"");
						MinArea.add("");
						picker3.setData(MinArea);
					}

					@Override
					public void onError(Object error) {

					}
				});

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
				PTBuyHouseSubmitFragment.this.MINAREA = " " + data;
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
				tv_buyhouse_housearea.setText(q + " " + AREA + MINAREA);
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

	@Override
	public void buyHouse(String info) {
		if (info.equals("发布求购成功")) {
			MyToastShowCenter.CenterToast(getActivity(), info);

			handler.postDelayed(new Runnable() {
				public void run() {
					Fragment fragment = new PTBuyHouseListFragment();
					FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
					trans.replace(R.id.rl_container, fragment);
					trans.commitAllowingStateLoss();
				}
			}, 1000);
		} else {
			MyToastShowCenter.CenterToast(getActivity(), info);
		}

	}

}
