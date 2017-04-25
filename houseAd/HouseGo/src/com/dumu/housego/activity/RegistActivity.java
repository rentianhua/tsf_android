package com.dumu.housego.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.User;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.CheckPhoneRegistPresenter;
import com.dumu.housego.presenter.ICheckPhoneRegistPresenter;
import com.dumu.housego.presenter.ILoginUserInfoPresenter;
import com.dumu.housego.presenter.IPhoneCodePresenter;
import com.dumu.housego.presenter.IRegistPresenter;
import com.dumu.housego.presenter.LoginUserInfoPresenter;
import com.dumu.housego.presenter.PhoneCodePresenter;
import com.dumu.housego.presenter.RegistPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.ICheckPhoneRegistView;
import com.dumu.housego.view.ILoginUserInfoView;
import com.dumu.housego.view.IPhoneCodeView;
import com.dumu.housego.view.IRegistView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class RegistActivity extends BaseActivity implements IPhoneCodeView,
        ILoginUserInfoView, IRegistView {
    private Button btnSendCode, btnRegist;
    private LinearLayout llBackLogin;
    private IPhoneCodePresenter presenter;
    private IRegistPresenter registpresenter;
    //private ICheckPhoneRegistPresenter checkPresenter;

    private ImageView imageCodeView;
    private EditText etImageCode;
    private EditText etPhoneNum;
    private EditText etSmscode;
    private EditText etPassword;
    private EditText etRepassword;
    private RadioGroup rgRegistType;
    private RadioButton rbAgent;
    private ILoginUserInfoPresenter userinfoPresenter;
    Thread thread = null;
    private boolean tag12 = true;
    private int i = 60;
    public boolean isChange = false;
    private UserInfo userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        presenter = new PhoneCodePresenter(this);
        registpresenter = new RegistPresenter(this);
        //checkPresenter = new CheckPhoneRegistPresenter(this);
        userinfoPresenter = new LoginUserInfoPresenter(this);
        setViews();
        //btnRegist.setEnabled(false);
        //btnSendCode.setEnabled(false);

        setListener();
        FontHelper.injectFont(findViewById(android.R.id.content));
    }

    private void setListener() {
        llBackLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSendCode.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String imageCode = etImageCode.getText().toString();
                if (etImageCode == null || etImageCode.length() != 4) {
                    Toast.makeText(RegistActivity.this, "验证码错误",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String number = etPhoneNum.getText().toString();
                if (number == null || number.length() != 11) {
                    Toast.makeText(RegistActivity.this, "手机号格式不正确",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                btnSendCode.setClickable(false);
                presenter.LoadMob(number, imageCode);

                //isChange = true;
                //changeBtnGetCode();
            }
        });

        btnRegist.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String telStr = etPhoneNum.getText().toString();
                if (telStr == null || telStr.length() != 11) {
                    Toast.makeText(RegistActivity.this, "手机号格式不正确",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String smsCodeStr = etSmscode.getText().toString();
                if (smsCodeStr == null || smsCodeStr.length() == 0) {
                    Toast.makeText(RegistActivity.this, "短信验证码不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String pswStr = etPassword.getText().toString();
                if (pswStr == null || pswStr.length() == 0) {
                    Toast.makeText(RegistActivity.this, "密码不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String cpswStr = etRepassword.getText().toString();
                if (cpswStr == null || cpswStr.length() == 0) {
                    Toast.makeText(RegistActivity.this, "确认密码不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pswStr.equals(cpswStr)) {
                    Toast.makeText(RegistActivity.this, "密码和确认密码不一致",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User();
                String modelid = rbAgent.isChecked() ? "36" : "35";
                user.setUsername(telStr);
                user.setModelid(modelid);
                user.setPassword(pswStr);
                user.setPassword2(cpswStr);
                user.setYzm(smsCodeStr);
                registpresenter.Regist(user);
            }
        });

//        etPhoneNum.addTextChangedListener(new TextWatcher() {
//            public void onTextChanged(CharSequence s, int start, int before,
//                                      int count) {
//                if (s.length() == 11) {
//                    btnRegist.setEnabled(true);
//                    btnSendCode.setEnabled(true);
//                    String mob = etPhoneNum.getText().toString();
//                    checkPresenter.checkPhone(mob);
//                } else {
//                    btnRegist.setEnabled(false);
//                    btnSendCode.setEnabled(false);
//                }
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//            }
//
//            public void afterTextChanged(Editable s) {
//            }
//        });
    }

    protected void changeBtnGetCode() {
        thread = new Thread() {
            public void run() {
                if (tag12) {
                    while (i > 0) {
                        i--;
                        if (RegistActivity.this == null) {
                            break;
                        }

                        RegistActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                btnSendCode.setText("重发(" + i + "s)");
                                btnSendCode.setClickable(false);
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
                i = 60;
                tag12 = true;
                if (RegistActivity.this != null) {
                    RegistActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btnSendCode.setText("发送验证码");
                            btnSendCode.setClickable(true);
                        }
                    });
                }
            }

            ;
        };
        thread.start();
    }

    private void setViews() {
        btnSendCode = (Button) findViewById(R.id.btn_sendcode);
        llBackLogin = (LinearLayout) findViewById(R.id.ll_back_login);
        btnRegist = (Button) findViewById(R.id.btn_Regist);
        etPhoneNum = (EditText) findViewById(R.id.et_phonenumb);
        etPassword = (EditText) findViewById(R.id.et_password);
        etRepassword = (EditText) findViewById(R.id.et_repassword);
        etSmscode = (EditText) findViewById(R.id.et_smscode);
        rbAgent = (RadioButton) findViewById(R.id.rb_agent);
        imageCodeView = (ImageView) findViewById(R.id.img_getcode);
        etImageCode = (EditText) findViewById(R.id.et_imagecode);

        //
        resetImageCode();
        imageCodeView.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                resetImageCode();
            }
        });
    }

    private void resetImageCode() {
        ImageLoader
                .getInstance()
                .displayImage(
                        "http://www.taoshenfang.com/index.php?g=Api&m=Checkcode&code_len=4&font_size=20&width=130&height=40&refresh=1&time="
                                + Math.random(), imageCodeView);
    }

    public void setData(boolean isSuccess, String infomation) {
        if (isSuccess) {
            Toast.makeText(RegistActivity.this, infomation, Toast.LENGTH_SHORT)
                    .show();
            btnSendCode.setClickable(false);
            changeBtnGetCode();
        } else {
            Toast.makeText(RegistActivity.this, infomation, Toast.LENGTH_SHORT)
                    .show();
            btnSendCode.setText("发送验证码");
            btnSendCode.setClickable(true);
        }
    }

    public void registSuccess(String userid) {
        MyToastShowCenter.CenterToast(getApplicationContext(), "注册成功！");
        userinfoPresenter.login(userid);
    }

    public void registFail(String errorMessage) {
        Toast.makeText(RegistActivity.this, errorMessage.toString(),
                Toast.LENGTH_SHORT).show();
    }

//    public void CheckSuccess(String info) {
//        btnSendCode.setText("发送验证码");
//        btnSendCode.setEnabled(true);
//        btnSendCode.setClickable(true);
//        btnSendCode.setFocusable(true);
//        MyToastShowCenter.CenterToast(getApplicationContext(), info);
//    }
//
//    public void CheckFail(String errorMessage) {
//        if (!errorMessage.equals("可以注册")) {
//            btnSendCode.setText(errorMessage);
//            btnSendCode.setEnabled(false);
//        } else {
//            btnSendCode.setFocusable(true);
//            btnSendCode.setEnabled(true);
//            btnSendCode.setClickable(true);
//            btnSendCode.setText("发送验证码");
//        }
//        MyToastShowCenter.CenterToast(getApplicationContext(), errorMessage);
//    }

    public void loginUserInfoFail(String errorMessage) {
        MyToastShowCenter.CenterToast(RegistActivity.this, "获取用户信息失败");
    }

    public void loginUserInfoSuccess() {
        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
        HouseGoApp.saveLoginInfo(getApplicationContext(), userinfo);
        HouseGoApp.getContext().SaveCurrentUserInfo(userinfo);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
