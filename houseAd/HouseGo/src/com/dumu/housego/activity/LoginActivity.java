package com.dumu.housego.activity;

import java.util.ArrayList;

import com.dumu.housego.FindPasswordMainActivity;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.User;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.framgent.MyFramgent;
import com.dumu.housego.presenter.ILoginPresenter;
import com.dumu.housego.presenter.ILoginUserInfoPresenter;
import com.dumu.housego.presenter.IYzmLoginPresenter;
import com.dumu.housego.presenter.IYzmSendCodePresenter;
import com.dumu.housego.presenter.LoginPresenter;
import com.dumu.housego.presenter.LoginUserInfoPresenter;
import com.dumu.housego.presenter.YzmLoginPresenter;
import com.dumu.housego.presenter.YzmSendCodePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.ILoginUserInfoView;
import com.dumu.housego.view.ILoginView;
import com.dumu.housego.view.IYZMLoginView;
import com.dumu.housego.view.IYzmSendCodeView;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements ILoginView, ILoginUserInfoView, IYZMLoginView, IYzmSendCodeView {
    private TextView tvCancle;
    private TextView tvRegist, tvForgivepw;
    private Button btnLogin, btnShortLoginSendCode, btnshortLogin;
    private ILoginPresenter generalpresenter;
    private IYzmLoginPresenter shortPresenter;
    private IYzmSendCodePresenter codePresenter;

    private EditText etGeneralUsername;
    private EditText etGeneralPassword;

    private EditText etShortUsername;
    private EditText etShortLoginSendCode;

    //private User user;
    private UserInfo userinfo;
    private ILoginUserInfoPresenter userinfoPresenter;

    Thread thread = null;
    private boolean tag12 = true;
    private int curCountIndex = 60;
    //public boolean isChange = false;

    ViewPager pager = null;
    NavigationTabStrip tabStrip = null;
    ArrayList<View> viewContainter = new ArrayList<View>();
    //ArrayList<String> titleContainer = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setViews();
        setAdapter();
        setListener();

        // btnShortLoginSendCode.setEnabled(false);
        generalpresenter = new LoginPresenter(this);
        userinfoPresenter = new LoginUserInfoPresenter(this);
        shortPresenter = new YzmLoginPresenter(this);
        codePresenter = new YzmSendCodePresenter(this);
        FontHelper.injectFont(findViewById(android.R.id.content));
    }

    private void setListener() {
        pager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int index) {
                tabStrip.setTabIndex(index);
            }
        });

        tvCancle.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                //finish();
            }
        });

        tvRegist.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
            }
        });

        tvForgivepw.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, FindPasswordMainActivity.class));
            }
        });

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String Gphonenum = etGeneralUsername.getText().toString();
                String password = etGeneralPassword.getText().toString();
                generalpresenter.login(Gphonenum, password);
            }
        });

        btnShortLoginSendCode.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String telStr = etShortUsername.getText().toString();
                if (telStr == null || telStr.length() != 11) {
                    Toast.makeText(LoginActivity.this, "手机号格式不正确",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                btnShortLoginSendCode.setClickable(false);
                codePresenter.YzmSendCode(telStr);
            }
        });

        btnshortLogin.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String shortnumber = etShortUsername.getText().toString();
                String shortYZM = etShortLoginSendCode.getText().toString();
                shortPresenter.login(shortnumber, shortYZM);
            }
        });
    }

    private void setAdapter() {
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewContainter.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView(viewContainter.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(viewContainter.get(position));
                return viewContainter.get(position);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

//            @Override
//            public CharSequence getPageTitle(int position) {
//                return titleContainer.get(position);
//            }
        });
        tabStrip.setViewPager(pager);
    }

    private void setViews() {
        View general = LayoutInflater.from(this).inflate(R.layout.login_general, null);
        View shortcut = LayoutInflater.from(this).inflate(R.layout.login_shortcut, null);
        pager = (ViewPager) this.findViewById(R.id.login_viewpager);
        tabStrip = (NavigationTabStrip) this.findViewById(R.id.login_tabstrip);
        btnLogin = (Button) general.findViewById(R.id.btn_general_Login);

        btnshortLogin = (Button) shortcut.findViewById(R.id.btn_short_Login);
        tvCancle = (TextView) findViewById(R.id.tv_cancle);
        tvRegist = (TextView) findViewById(R.id.tv_regist);
        btnShortLoginSendCode = (Button) shortcut.findViewById(R.id.btn_quicklogin_sendcode);
        tvForgivepw = (TextView) findViewById(R.id.tv_forgivepw);

        etGeneralUsername = (EditText) general.findViewById(R.id.et_genal_login_phonenumber);
        etGeneralPassword = (EditText) general.findViewById(R.id.et_genal_login_lock);

        etShortUsername = (EditText) shortcut.findViewById(R.id.et_short_login_phonenumber);
        etShortLoginSendCode = (EditText) shortcut.findViewById(R.id.et_short_login_lock_quick);

        viewContainter.add(general);
        viewContainter.add(shortcut);
//        titleContainer.add("普通登录");
//        titleContainer.add("验证码快捷登录");
        tabStrip.setTitles("普通登录", "验证码快捷登录");
    }

    protected void changeBtnGetCode() {
        thread = new Thread() {
            @Override
            public void run() {
                if (tag12) {
                    while (curCountIndex > 0) {
                        curCountIndex--;
                        if (LoginActivity.this == null) {
                            break;
                        }

                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btnShortLoginSendCode.setText("重发(" + curCountIndex + "s)");
                                btnShortLoginSendCode.setClickable(false);
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    tag12 = false;
                }

                curCountIndex = 60;
                tag12 = true;
                if (LoginActivity.this != null) {
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            btnShortLoginSendCode.setText("发送验证码");
                            btnShortLoginSendCode.setClickable(true);
                        }
                    });
                }
            }
        };
        thread.start();
    }

    @Override
    public void loginFail(String errorMessage) {
        MyToastShowCenter.CenterToast(LoginActivity.this, errorMessage);

    }

    @Override
    public void loginSuccess() {
        MyToastShowCenter.CenterToast(LoginActivity.this, "登录成功！");

        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
        String userid = userinfo.getUserid();
        userinfoPresenter.login(userid);

    }

    @Override
    public void loginUserInfoFail(String errorMessage) {
        MyToastShowCenter.CenterToast(LoginActivity.this, "获取用户信息失败");
    }

    @Override
    public void loginUserInfoSuccess() {

        userinfo = HouseGoApp.getContext().getCurrentUserInfo();


        HouseGoApp.saveLoginInfo(getApplicationContext(), userinfo);
        HouseGoApp.getContext().SaveCurrentUserInfo(userinfo);

//		startActivity(new Intent(getApplicationContext(), MainActivity.class));
        //登录之后清空任务栈
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //注意本行的FLAG设置
        startActivity(intent);
        finish();

    }

    @Override
    public void YzmloginFail(String errorMessage) {
        MyToastShowCenter.CenterToast(LoginActivity.this, errorMessage);
    }

    @Override
    public void YzmloginSuccess() {
        MyToastShowCenter.CenterToast(LoginActivity.this, "登陆成功！");

        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
        String userid = userinfo.getUserid();
        userinfoPresenter.login(userid);
    }

    public void YzmSendCode_Success(String infomation) {
        Toast toast = Toast.makeText(getApplicationContext(), infomation, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        btnShortLoginSendCode.setClickable(false);
        changeBtnGetCode();
    }

    public void YzmSendCode_Fail(String infomation) {
        Toast toast = Toast.makeText(getApplicationContext(), infomation, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        btnShortLoginSendCode.setText("发送验证码");
        btnShortLoginSendCode.setClickable(true);
    }
}
