package com.dumu.housego.framgent;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;

import com.aigestudio.wheelpicker.view.WheelPicker;
import com.aigestudio.wheelpicker.view.WheelPicker.OnWheelChangeListener;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Address;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.AddressModel;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.presenter.IQiuZuHouseSubmitPresenter;
import com.dumu.housego.presenter.QiuZuHouseSubmitPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IQiuZuHouseSubmitView;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.drawable.BitmapDrawable;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PTQiuZuSubmitFragment extends Fragment implements IQiuZuHouseSubmitView {
	private LinearLayout ll_back_ptqiuzusubmit;
	private RelativeLayout rl_qiuzu_fangyuanquyu, rl_qiuzu_rentingType, rl_qiuzu_qiwangtingshi, rl_qiuzu_qiwangjine;
	private TextView tv_qiuzu_phone, tv_qiuzu_housearea, tv_qiuzu_zulinType, tv_qiuzu_tingshi, tv_qiuzu_jine;
	private Button btn_qiuzu_submit;
	private EditText etChenhu;
	private UserInfo userinfo;
	private AddressModel model = new AddressModel();
	private AddressModel model2 = new AddressModel();
	private PopupWindow pop;
	private LinearLayout ll_wheel;
	private LinearLayout ll_popup;
	private String pid;
	String area1 = null;
	private IQiuZuHouseSubmitPresenter presenter;

	private Handler handler = new Handler();
	String str;

	String AREA;
	String MINAREA;
	private List<Address> minarea;
	private List<Address> area;

	private List<String> a = new ArrayList<String>();
	private List<String> shen = new ArrayList<String>();
	private List<String> Area = new ArrayList<String>();
	private List<String> MinArea = new ArrayList<String>();
	private List<String> zuLinType = new ArrayList<String>();
	private List<String> TingShi = new ArrayList<String>();
	private List<String> JinE = new ArrayList<String>();
	String t1 = "1室";
	String t2 = "2室";
	String t3 = "3室";
	String t4 = "4室";
	String t5 = "5室";
	String t6 = "5室以上";

	String q = "深圳";
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

	String zulin1 = "整租";
	String zulin2 = "合租";

	String J1 = "500元以下/月";
	String J2 = "500-1000元/月";
	String J3 = "1000-1500元/月";
	String J4 = "1500-2000元/月";
	String J5 = "3000-4500元/月";
	String J6 = "4500元以上/月";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View Pview = inflater.inflate(R.layout.fragment_pt_qiuzhusubmit, null);
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

		zuLinType.add(zulin1);
		zuLinType.add(zulin2);

		TingShi.add(t1);
		TingShi.add(t2);
		TingShi.add(t3);
		TingShi.add(t4);
		TingShi.add(t5);
		TingShi.add(t6);

		JinE.add(J1);
		JinE.add(J2);
		JinE.add(J3);
		JinE.add(J4);
		JinE.add(J5);
		JinE.add(J6);
		presenter = new QiuZuHouseSubmitPresenter(this);
		initView(Pview);
		setListener(Pview);
		FontHelper.injectFont(Pview);
		return Pview;
	}

	@Override
	public void onResume() {
		userinfo = HouseGoApp.getContext().getCurrentUserInfo();
		String phone = userinfo.getUsername();
		tv_qiuzu_phone.setText(phone + "");

		super.onResume();
	}

	private void initView(View view) {
		ll_back_ptqiuzusubmit = (LinearLayout) view.findViewById(R.id.ll_back_ptqiuzusubmit);
		rl_qiuzu_fangyuanquyu = (RelativeLayout) view.findViewById(R.id.rl_qiuzu_fangyuanquyu);
		rl_qiuzu_rentingType = (RelativeLayout) view.findViewById(R.id.rl_qiuzu_rentingType);
		rl_qiuzu_qiwangtingshi = (RelativeLayout) view.findViewById(R.id.rl_qiuzu_qiwangtingshi);
		rl_qiuzu_qiwangjine = (RelativeLayout) view.findViewById(R.id.rl_qiuzu_qiwangjine);

		tv_qiuzu_housearea = (TextView) view.findViewById(R.id.tv_qiuzu_housearea);
		tv_qiuzu_phone = (TextView) view.findViewById(R.id.tv_qiuzu_phone);
		tv_qiuzu_zulinType = (TextView) view.findViewById(R.id.tv_qiuzu_zulinType);
		tv_qiuzu_tingshi = (TextView) view.findViewById(R.id.tv_qiuzu_tingshi);
		tv_qiuzu_jine = (TextView) view.findViewById(R.id.tv_qiuzu_jine);

		etChenhu = (EditText) view.findViewById(R.id.et_qiuzu_cehnghu);

		btn_qiuzu_submit = (Button) view.findViewById(R.id.btn_qiuzu_submit);

	}

	private void setListener(final View pview) {

		ll_back_ptqiuzusubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment fragment = new PTQiuZuListFragment();
				FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.rl_container, fragment);
				trans.commitAllowingStateLoss();
			}
		});

		rl_qiuzu_fangyuanquyu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				HouseAreaAlertDialog();
			}
		});

		rl_qiuzu_rentingType.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlerDialogWheelPicker(zuLinType, tv_qiuzu_zulinType, "请选择租赁类型");
			}
		});

		rl_qiuzu_qiwangtingshi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlerDialogWheelPicker(TingShi, tv_qiuzu_tingshi, "请选择期望厅室");
			}
		});

		rl_qiuzu_qiwangjine.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlerDialogWheelPicker(JinE, tv_qiuzu_jine, "请选择期望租金");
			}
		});

		// 房源发布
		btn_qiuzu_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 获取地区area的id;

				for (int i = 0; i < minarea.size(); i++) {
					String area = minarea.get(i).getName();
					Log.e("area", "area=" + area + "   " + MINAREA);

					if (area.equals(MINAREA.trim())) {
						PTQiuZuSubmitFragment.this.area1 = minarea.get(i).getId() + "";
						Log.e("area1", "area1=" + area1 + "   " + MINAREA);
						break;
					} else {
						Log.e("xxxx--area1", "xxx---area1=" + area1 + "   " + MINAREA);
					}

				}

				String[] s = tv_qiuzu_tingshi.getText().toString().split("室");
				String[] j = tv_qiuzu_jine.getText().toString().split("元");

				String username = userinfo.getUsername();
				String province = "1";
				String city = pid + "";
				String shi ;
				String zulin = tv_qiuzu_zulinType.getText().toString();
				
				if(tv_qiuzu_tingshi.getText().toString().equals("5室以上")){
					shi="6";
				}else{
					shi = s[0];	
				}
				
				String zujinrange = j[0];
				
				String chenghu = etChenhu.getText().toString();
				String title = tv_qiuzu_housearea.getText().toString() + tv_qiuzu_zulinType.getText().toString()
						+ tv_qiuzu_tingshi.getText().toString() + tv_qiuzu_jine.getText().toString();
				Log.e("xxxxx", "x=" + minarea.toString());
				Log.e("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "QiuZuSubmit=" + " " + username + " " + province + " " + city
						+ " " + area1 + " " + zulin + " " + shi + " " + zujinrange + " " + chenghu + " " + title);
				presenter.QiuZuSubmit(username, province, city, area1, zulin, shi, zujinrange, chenghu, title);
			}
		});

	}

	@SuppressLint("ResourceAsColor")
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
					PTQiuZuSubmitFragment.this.pid = "2";
				} else if (data.equals("福田区")) {
					PTQiuZuSubmitFragment.this.pid = "4";
				} else if (data.equals("南山区")) {
					PTQiuZuSubmitFragment.this.pid = "5";
				} else if (data.equals("盐田区")) {
					PTQiuZuSubmitFragment.this.pid = "6";
				} else if (data.equals("宝安区")) {
					PTQiuZuSubmitFragment.this.pid = "7";
				} else if (data.equals("龙岗新区")) {
					PTQiuZuSubmitFragment.this.pid = "8";
				} else if (data.equals("龙华新区")) {
					PTQiuZuSubmitFragment.this.pid = "9";
				} else if (data.equals("光明新区")) {
					PTQiuZuSubmitFragment.this.pid = "10";
				} else if (data.equals("坪山新区")) {
					PTQiuZuSubmitFragment.this.pid = "11";
				} else if (data.equals("大鹏新区")) {
					PTQiuZuSubmitFragment.this.pid = "12";
				} else if (data.equals("东莞")) {
					PTQiuZuSubmitFragment.this.pid = "13";
				} else {
					PTQiuZuSubmitFragment.this.pid = "14";
				}

				model2.address(pid, new AsycnCallBack() {
					@Override
					public void onSuccess(Object success) {
						PTQiuZuSubmitFragment.this.minarea = (List<Address>) success;

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
				PTQiuZuSubmitFragment.this.MINAREA = " " + data;
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
				tv_qiuzu_housearea.setText(q + " " + AREA + MINAREA);
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

	@Override
	public void QiuZuSubmit(String info) {
		if (info.equals("发布求租成功")) {
			MyToastShowCenter.CenterToast(getActivity(), info);

			handler.postDelayed(new Runnable() {
				public void run() {
					Fragment fragment = new PTQiuZuListFragment();
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
