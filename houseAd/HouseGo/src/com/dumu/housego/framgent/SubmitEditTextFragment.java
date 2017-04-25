package com.dumu.housego.framgent;

import com.dumu.housego.R;
import com.dumu.housego.util.FontHelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubmitEditTextFragment extends Fragment {

	public Listener mListener;// 接口

	String str;
	private LinearLayout llBackSubmitedittext;
	private EditText submitEdittext;
	private TextView tvSubmiteditsave;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_submitedittext, null);
		initView(view);
		FontHelper.injectFont(view);
		setListener();
		return view;
	}

	@Override
	public void onResume() {
		getTransitiveData();// 获取上一个碎片传递过来的数据
		super.onResume();
	}

	//

	public SubmitEditTextFragment(Listener mListener) {
		super();
		this.mListener = mListener;
	}

	//
	public void sends(String string) {

		mListener.send(string);// 开始发送数据
	}

	//

	// public SubmitEditTextFragment() {
	// super();
	// }

	private void getTransitiveData() {
		// 获取由碎片1传递过来的String类型数据
		String biaoti = getArguments().getString("biaoti");
		submitEdittext.setText(biaoti);
	}

	private void initView(View view) {
		llBackSubmitedittext = (LinearLayout) view.findViewById(R.id.ll_back_submitedittext);
		submitEdittext = (EditText) view.findViewById(R.id.submit_edittext);
		tvSubmiteditsave = (TextView) view.findViewById(R.id.tv_submiteditsave);
	}

	private void setListener() {

		llBackSubmitedittext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		tvSubmiteditsave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fragment2 = new ATershouSubmitFragment();
				str = submitEdittext.getText().toString();
				// Bundle b=new Bundle();
				// b.putString("biaoti_new", biaoti_new);
				// fragment2.setArguments(b);
				Log.e("biaoti_new", "biaoti_new" + str);
				// showFragment(SubmitEditTextFragment.this, fragment2);

				sends(str);

				FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.fl_agent_fragment, fragment2);
				trans.addToBackStack(null);
				trans.commit();
			}
		});

	}

	/**
	 * 公共方法： 从碎片fragment1跳转到碎片fragment2
	 * 
	 * @param fragment1
	 *            当前fragment
	 * @param fragment2
	 *            跳转后的fragment
	 */
	public void showFragment(Fragment fragment1, Fragment fragment2) {
		// 获取 FragmentTransaction 对象
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		// 如果fragment2没有被添加过，就添加它替换当前的fragment1
		if (!fragment2.isAdded()) {
			transaction.add(R.id.fl_agent_fragment, fragment2)
					// 加入返回栈，这样你点击返回键的时候就会回退到fragment1了
					.addToBackStack(null).hide(fragment1)
					// 提交事务
					.commitAllowingStateLoss();
		} else { // 如果已经添加过了的话就隐藏fragment1，显示fragment2
			transaction
					// 隐藏fragment1，即当前碎片
					.hide(fragment1)
					// 显示已经添加过的碎片，即fragment2
					.show(fragment2)
					// 加入返回栈
					.addToBackStack(null)
					// 提交事务
					.commitAllowingStateLoss();
		}
	}

	public interface Listener {
		void send(String s);
	}

}
