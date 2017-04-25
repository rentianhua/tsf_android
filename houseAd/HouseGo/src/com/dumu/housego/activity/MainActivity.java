package com.dumu.housego.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.LiuYanList;
import com.dumu.housego.entity.User;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.framgent.FirstFramgent;
import com.dumu.housego.framgent.HouseFramgent;
import com.dumu.housego.framgent.MessageFramgent;
import com.dumu.housego.framgent.MyFramgent;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.LoginUserInfoModel;
import com.dumu.housego.presenter.ILiuYanPresenter;
import com.dumu.housego.presenter.ILoginPresenter;
import com.dumu.housego.presenter.ILoginUserInfoPresenter;
import com.dumu.housego.presenter.LiuYanListPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.ILiuYanView;
import com.jauker.widget.BadgeView;

@SuppressLint("RtlHardcoded")
public class MainActivity extends BaseFragmentActivity implements ILiuYanView {
    private BadgeView badgeView;
    private LinearLayout ll_mian_message;
    private RadioGroup rgControl;
    private RadioButton rbFrist;
    private RadioButton rbMessage;
    private RadioButton rbHouse;
    private RadioButton rbMy;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private List<Fragment> fragments;
    private ILiuYanPresenter liuyanlistPresenter;
    private UserInfo userinfo;
    private LoginUserInfoModel infomodel = new LoginUserInfoModel();
    private ILoginPresenter generalpresenter;
    private ILoginUserInfoPresenter userinfoPresenter;
    List<LiuYanList> liuyans = new ArrayList<LiuYanList>();
    private User user;
    List<RadioButton> list = new ArrayList<RadioButton>();
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            this.update();
            handler.postDelayed(this, 1000 * 5);// 间隔120秒
        }

        void update() {

            // 刷新msg的内容
            userinfo = HouseGoApp.getLoginInfo(MainActivity.this);

            if (userinfo != null) {

                String userid = userinfo.getUserid();
                liuyanlistPresenter.liuyan(userinfo.getUserid());
                infomodel.login(userid, new AsycnCallBack() {
                    @Override
                    public void onSuccess(Object success) {
                        UserInfo Nuserinfo = (UserInfo) success;

                        if (userinfo.getPassword().equals(
                                Nuserinfo.getPassword())) {

                            HouseGoApp.getContext().SaveCurrentUserInfo(
                                    Nuserinfo);
                            HouseGoApp.saveLoginInfo(getApplicationContext(),
                                    Nuserinfo);

                        } else {

                            //User user = null;
                            UserInfo userinfo = null;
                            HouseGoApp.saveLoginInfo(getApplicationContext(),
                                    userinfo);
                            HouseGoApp.getContext().SaveCurrentUserInfo(
                                    userinfo);
                            //HouseGoApp.getContext().SaveCurrentUser(user);
                            MyToastShowCenter.CenterToast(
                                    getApplicationContext(), "密码已修改，请重新登录！");

                        }

                    }

                    @Override
                    public void onError(Object error) {
                    }
                });
            }
            Log.e("userinfo	", "userinfo为空");

        }
    };
    private Button btn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
        rbFrist.setTextColor(getResources().getColor(R.color.button_ckeck));
        liuyanlistPresenter = new LiuYanListPresenter(this);
        setViewPagerAdapter();
        setListeners();

        FontHelper.injectFont(findViewById(android.R.id.content));

        if (!isNetworkConnected(this)) {
            MyToastShowCenter.CenterToast(
                    getApplicationContext(), "网络不可用，请检查网络连接！");
        }
    }

    //
    @Override
    protected void onResume() {
        // getIntent().getIntExtra("page", -1);

        if (getIntent().getStringExtra("page") != null
                && !getIntent().getStringExtra("page").equals("")) {
            String s1 = getIntent().getStringExtra("page");
            int s = Integer.valueOf(s1);
            viewPager.setCurrentItem(s);

        }
        // liuyanlistPresenter.liuyan(userinfo.getUserid());

        handler.postDelayed(runnable, 1000 * 1);

        // HouseGoApp app=HouseGoApp.getContext();
        // userinfo=app.getLoginInfo(getApplicationContext());
        // app.SaveCurrentUserInfo(userinfo);

        // userinfo = HouseGoApp.getLoginInfo(this);
        //
        // if (userinfo != null) {
        //
        // String userid = userinfo.getUserid();
        // infomodel.login(userid, new AsycnCallBack() {
        // @Override
        // public void onSuccess(Object success) {
        // UserInfo Nuserinfo = (UserInfo) success;
        // Log.e("userinfo	","userinfo="+userinfo.getPassword()+" "+"Nuserinfo="+Nuserinfo.getPassword());
        //
        // if( userinfo.getPassword().equals(Nuserinfo.getPassword()) ){
        //
        // HouseGoApp.getContext().SaveCurrentUserInfo(Nuserinfo);
        // HouseGoApp.saveLoginInfo(getApplicationContext(), Nuserinfo);
        // Log.e("userinfo	","userinfo相等");
        //
        // }else{
        //
        // User user=null;
        // UserInfo userinfo=null;
        // HouseGoApp.saveLoginInfo(getApplicationContext(), userinfo);
        // HouseGoApp.getContext().SaveCurrentUserInfo(userinfo);
        // HouseGoApp.getContext().SaveCurrentUser(user);
        // Log.e("userinfo	","userinfo不相等");
        //
        // }
        //
        // }
        //
        // @Override
        // public void onError(Object error) {
        // }
        // });
        // }
        // Log.e("userinfo	","userinfo为空");

        super.onResume();
    }

    @Override
    protected void onDestroy() {
        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
        HouseGoApp.saveLoginInfo(getApplicationContext(), userinfo);
        handler.removeCallbacks(runnable); // 停止刷新
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }

    private void setViews() {

        // badgeview.set
        rgControl = (RadioGroup) findViewById(R.id.rg_control);
        rbFrist = (RadioButton) findViewById(R.id.first);
        rbMessage = (RadioButton) findViewById(R.id.message);
        rbHouse = (RadioButton) findViewById(R.id.house);
        rbMy = (RadioButton) findViewById(R.id.my);
        viewPager = (ViewPager) findViewById(R.id.viewpage);
        btn_btn = (Button) findViewById(R.id.btn_btn);
        ll_mian_message = (LinearLayout) findViewById(R.id.ll_mian_message);

        list.add(rbFrist);
        list.add(rbMessage);
        list.add(rbHouse);
        list.add(rbMy);

        badgeView = new BadgeView(this);

    }

    private void setViewPagerAdapter() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new FirstFramgent());
        fragments.add(new MessageFramgent());
        fragments.add(new HouseFramgent());
        fragments.add(new MyFramgent());

        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

    }

    private void setListeners() {
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                switch (position) {

                    case 0:
                        list.get(position).setChecked(true);
                        rbFrist.setTextColor(getResources().getColor(
                                R.color.button_ckeck));
                        rbMessage.setTextColor(getResources().getColor(
                                R.color.button_unckeck));
                        rbHouse.setTextColor(getResources().getColor(
                                R.color.button_unckeck));
                        rbMy.setTextColor(getResources().getColor(
                                R.color.button_unckeck));
                        break;
                    case 1:
                        list.get(position).setChecked(true);
                        rbFrist.setTextColor(getResources().getColor(
                                R.color.button_unckeck));
                        rbMessage.setTextColor(getResources().getColor(
                                R.color.button_ckeck));
                        rbHouse.setTextColor(getResources().getColor(
                                R.color.button_unckeck));
                        rbMy.setTextColor(getResources().getColor(
                                R.color.button_unckeck));

                        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
                        if (userinfo == null) {
                            startActivity(new Intent(getApplicationContext(),
                                    LoginActivity.class));
                        }

                        break;
                    case 2:
                        list.get(position).setChecked(true);
                        rbFrist.setTextColor(getResources().getColor(
                                R.color.button_unckeck));
                        rbMessage.setTextColor(getResources().getColor(
                                R.color.button_unckeck));
                        rbHouse.setTextColor(getResources().getColor(
                                R.color.button_ckeck));
                        rbMy.setTextColor(getResources().getColor(
                                R.color.button_unckeck));
                        break;
                    case 3:
                        list.get(position).setChecked(true);
                        rbFrist.setTextColor(getResources().getColor(
                                R.color.button_unckeck));
                        rbMessage.setTextColor(getResources().getColor(
                                R.color.button_unckeck));
                        rbHouse.setTextColor(getResources().getColor(
                                R.color.button_unckeck));
                        rbMy.setTextColor(getResources().getColor(
                                R.color.button_ckeck));
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        rgControl.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.first:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.message:
                        viewPager.setCurrentItem(1);

                        break;
                    case R.id.house:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.my:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });

    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

    }

    @Override
    public void liuyanlist(List<LiuYanList> liuyans) {
        this.liuyans = liuyans;

        if (liuyans != null && liuyans.size() > 0
                && liuyans.get(0).getWeidu_sum() != null) {
            badgeView.setTargetView(btn_btn);
            badgeView.setBadgeMargin(0, 0, -10, 0);
            badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.FILL_HORIZONTAL);
            ll_mian_message.setVisibility(View.VISIBLE);
            badgeView.setBadgeCount(Integer.valueOf(liuyans.get(0)
                    .getWeidu_sum()));
        } else {
            ll_mian_message.setVisibility(View.GONE);
        }

    }

    @Override
    public void liuyanerror(String info) {
        ll_mian_message.setVisibility(View.GONE);

    }

    boolean isBack = false;
    private long downTime;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isBack) {
                Toast.makeText(this, "请在按一次退出", Toast.LENGTH_SHORT).show();
                downTime = event.getDownTime();
                isBack = true;
                return true;
            } else {
                if (event.getDownTime() - downTime <= 2000) {
                    ActivityManager.finishApplication();
                } else {
                    Toast.makeText(this, "请在按一次退出", Toast.LENGTH_SHORT).show();
                    downTime = event.getDownTime();
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
