package com.dumu.housego;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import util.FileUtils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.activity.MainActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.CheckBoxArea;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.LoginUserInfoModel;
import com.dumu.housego.presenter.ChangeUserInfoPresenter;
import com.dumu.housego.presenter.IChangeUserInfoPresenter;
import com.dumu.housego.util.CircleImageView;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.utils.UploadUtil;
import com.dumu.housego.utils.UploadUtil.OnUploadProcessListener;
import com.dumu.housego.view.IChangeUserInfoView;
import com.example.testpic.PublishedActivity;

public class AgentChangeUserInfoActivity extends BaseActivity implements OnUploadProcessListener, IChangeUserInfoView {
    private static RequestQueue mSingleQueue;
    private static String TAG = "test";


    public static final int TAKE_PHOTO = 6;

    public static final int CHOOSE_PHOTO = 7;

    private ImageView picture;

    private Uri imageUri;

    /**
     * 请求裁剪码
     */
    public static final int REQUEST_CODE_GETIMAGE_BYCROP = 1;
    /**
     * 请求相机码
     */
    public static final int REQUEST_CODE_GETIMAGE_BYCAMERA = 2;
    /**
     * 请求相册
     */
    public static final int REQUEST_CODE_GETIMAGE_BYSDCARD = 3;

    /**
     * 去上传文件
     */
    protected static final int TO_UPLOAD_FILE = 4;

    /**
     * 上传文件响应
     */
    protected static final int UPLOAD_FILE_DONE = 5;  //

    private Uri origUri;
    private String SDCARD_MNT = "/mnt/sdcard";
    private String SDCARD = "/sdcard";
    private Uri cropUri;
    private File protraitFile;
    String catid = "6";
    List<Pics> pics = new ArrayList<Pics>();

    private String PICPATH;

    private IChangeUserInfoPresenter userinfopresenter;
    private LoginUserInfoModel infomodel = new LoginUserInfoModel();

    private LinearLayout ll_agent_back;

    private EditText et_agent_changrealname, et_agent_changegerenjieshao, et_agent_changeshenfenzheng,
            et_agent_changecompanyname, et_agent_change_currentpassword, et_agent_change_Newpassword,
            et_agent_change_repassword;
    private LinearLayout ll_agent_changerealname, ll_agent_changerealname_back, ll__aegnt_changfenjiahao,
            ll_agent_changefenjihao_back, agent_fenjihao_shenqing, agent_fenjihao_jiebang, ll_agent_gerenjieshao,
            ll_agent_changegerenjieshao_back, ll_agent_changeshenfenzheng, ll_agent_changeshenfenzheng_back,
            ll_agent_changecompanyname, ll_agent_changecompanyname_back, ll_agent_changepassword,
            ll_agent_changepassword_back;

    private RelativeLayout rl_agent_shenfenzheng, rl_agent_mainarea, rl_agent_zhiyeType, rl_agent_companyName,
            rl_agent_chongyeshijian, rl_agent_upload, rl_agent_changepassword, rl_agent_realname, rl_agent_sex,
            rl_agent_fenjihao, rl_agent_gerenjieshao;

    private TextView tv_agent_shenfenzheng, tv_agent_mainarea, tv_agent_zhiyeType, tv_agent_realname,
            tv_agent_chongyeshijian, tv_agent_phone, tv_agent_viptype, tv_agent_sex, tv_agent_fenjihao,
            tv_agent_fenjihao_show, tv_agent_fenjihao_show_1, tv_agent_changegerenjieshao_save,
            tv_agent_changeshenfenzheng_save, tv_agent_changecompanyname_save;
    private Button line_gongsiname, btn_agent_changefenjihao_shenqing, btn_agent_changefenjihao_jiebang, btn_agent_changepassword_submit;

    private TextView tv_agent_changerealname_save, tv_gerenshuoming, tv_sfz_pic, tv_gongsi;

    private CircleImageView iv_agent_Photo;
    private UserInfo userinfo;

    private MyReboundScrollView agent_changeshow;

    protected Handler h = new Handler() {
        public void handleMessage(Message msg) {

            String userid = userinfo.getUserid();

            infomodel.login(userid, new AsycnCallBack() {
                @Override
                public void onSuccess(Object success) {
                    UserInfo Nuserinfo = (UserInfo) success;
                    HouseGoApp.getContext().SaveCurrentUserInfo(Nuserinfo);
                    HouseGoApp.saveLoginInfo(getApplicationContext(), Nuserinfo);
                }

                @Override
                public void onError(Object error) {
                }
            });
            userinfo = HouseGoApp.getContext().getCurrentUserInfo();


        }
    };


    private PopupWindow popSex;
    private LinearLayout ll_popupSex;

    private View parentView;
    private LinearLayout ll_cancle;
    private PopupWindow popType;
    private LinearLayout ll_popup_zhiyeType;
    private LinearLayout ll_cancle_zhiyeType;
    private PopupWindow popWorkTime;
    private LinearLayout ll_popup_worktime;
    private LinearLayout ll_cancle_worktime;
    private PopupWindow popTouXiang;
    private LinearLayout ll_popup_touxiang;
    private LinearLayout ll_cancle_touxiang;
    private File imageFile;
    private Bitmap Cropbitmap;
    private String imagePath;
    private PopupWindow careatpop;
    private LinearLayout llPopup;
    private RelativeLayout parent;
    private String userid;
    private String check = null;

    private String PICTAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_agent_change_user_info);
        parentView = getLayoutInflater().inflate(R.layout.activity_agent_change_user_info, null);
        setContentView(parentView);
        FontHelper.injectFont(findViewById(android.R.id.content));

        setViews();
        userinfopresenter = new ChangeUserInfoPresenter(this);
        creatPopupwindows();
        showTouxaingWindow();
        showPopWindowSex();
        showPopWindowType();
        showPopWindowWorkTime();
        setListener();
    }

    private void showPopWindowWorkTime() {
        popWorkTime = new PopupWindow(AgentChangeUserInfoActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows_worktime, null);

        ll_popup_worktime = (LinearLayout) view.findViewById(R.id.ll_popup_worktime);
        ll_cancle_worktime = (LinearLayout) view.findViewById(R.id.ll_cancle_worktime);
        popWorkTime.setWidth(LayoutParams.MATCH_PARENT);
        popWorkTime.setHeight(LayoutParams.WRAP_CONTENT);
        popWorkTime.setBackgroundDrawable(new BitmapDrawable());
        popWorkTime.setFocusable(true);
        popWorkTime.setOutsideTouchable(true);
        popWorkTime.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_worktime);
        Button bt4 = (Button) view.findViewById(R.id.item_popupwindows_worktime_btn4);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_worktime_btn2);
        Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_worktime_btn3);
        Button cancle = (Button) view.findViewById(R.id.item_popupwindows_worktime_cancel);
        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                popWorkTime.dismiss();
                ll_popup_worktime.clearAnimation();
                ll_cancle_worktime.clearAnimation();
            }
        });

        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                String userid = userinfo.getUserid();
                String worktime = "1-2";
                userinfopresenter.ChangeWorkTime(userid, worktime);
                tv_agent_chongyeshijian.setText(worktime + "年");
                popWorkTime.dismiss();
                ll_popup_worktime.clearAnimation();
                ll_cancle_worktime.clearAnimation();
            }
        });
        bt3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String userid = userinfo.getUserid();
                String worktime = "2-5";
                userinfopresenter.ChangeWorkTime(userid, worktime);
                tv_agent_chongyeshijian.setText(worktime + "年");
                popWorkTime.dismiss();
                ll_popup_worktime.clearAnimation();
                ll_cancle_worktime.clearAnimation();
            }
        });


        bt4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String userid = userinfo.getUserid();
                String worktime = "5";
                userinfopresenter.ChangeWorkTime(userid, worktime);
                tv_agent_chongyeshijian.setText(worktime + "年");
                popWorkTime.dismiss();
                ll_popup_worktime.clearAnimation();
                ll_cancle_worktime.clearAnimation();
            }
        });
        cancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                popWorkTime.dismiss();
                ll_popup_worktime.clearAnimation();
                ll_cancle_worktime.clearAnimation();
            }
        });

    }


    /**
     * 创建对话框
     */
    protected void creatPopupwindows() {

        careatpop = new PopupWindow(this);
        View popupView = getLayoutInflater().inflate(R.layout.item_pop_camra, null);
        llPopup = (LinearLayout) popupView.findViewById(R.id.ll_popup);
        careatpop.setWidth(LayoutParams.MATCH_PARENT);
        careatpop.setHeight(LayoutParams.WRAP_CONTENT);
        careatpop.setBackgroundDrawable(new BitmapDrawable());
        careatpop.setFocusable(true);
        careatpop.setOutsideTouchable(true);
        careatpop.setContentView(popupView);


        parent = (RelativeLayout) popupView.findViewById(R.id.parent);
        Button bt1 = (Button) popupView
                .findViewById(R.id.item_popupwindows_camera);//照相
        Button bt2 = (Button) popupView
                .findViewById(R.id.item_popupwindows_Photo);//相册
        Button bt3 = (Button) popupView
                .findViewById(R.id.item_popupwindows_cancel);//取消

        bt1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //照相
                photo();
                careatpop.dismiss();
                llPopup.clearAnimation();
            }
        });
        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                // Android6.0以上，要动态申请读写权限
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请读写权限
                    ActivityCompat.requestPermissions(AgentChangeUserInfoActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GETIMAGE_BYCROP);
                } else {

                    if (Build.VERSION.SDK_INT < 19) {
                        intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "选择图片"),
                                REQUEST_CODE_GETIMAGE_BYCROP);
                    } else {
                        intent = new Intent(Intent.ACTION_PICK,
                                Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "选择图片"),
                                REQUEST_CODE_GETIMAGE_BYCROP);
                    }
                }
                careatpop.dismiss();
                //清除动画
                llPopup.clearAnimation();
            }
        });

        bt3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                careatpop.dismiss();
                llPopup.clearAnimation();
            }
        });

    }

    /**
     * 照相
     */
    protected void photo() {
        String savePath = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //SD卡处于挂载状态,创建图片保存的文件夹路径
            savePath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/image/";
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                //文件夹不存在，创建文件夹
                saveDir.mkdirs();
            }
        } else {
            Toast.makeText(this, "无法保存照片，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
            return;
        }

        //创建图片名称
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String fileName = "taoshenfang_" + timeStamp + ".jpg";// 照片命名

        //自定义图片保存路径
        File out = new File(savePath, fileName);
        Uri uri = Uri.fromFile(out);
        origUri = uri;

        // 动态申请照相权限和读写权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_GETIMAGE_BYCAMERA);
        } else {
            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Log.i("MainActivity", uri.toString());
            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(openCameraIntent,
                    REQUEST_CODE_GETIMAGE_BYCAMERA);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnIntent) {
        if (resultCode == Activity.RESULT_OK && requestCode == PublishedActivity.UploadPERSONSFZ) {
            List<Pics> ps = (List<Pics>) imageReturnIntent
                    .getSerializableExtra("uploadpics");
            if (ps != null && ps.size() > 0) {
                String urls = ps.get(0).getUrl();
                if (ps.size() > 1) {
                    for (int i = 1; i < ps.size(); i++)
                        urls += "," + ps.get(i).getUrl();
                }
                userinfopresenter.ChangeShenFenZhengPic(userinfo.getUserid(), urls);
            }
        }

        //回调处理结果
        if (resultCode != Activity.RESULT_OK)
            return;

        switch (requestCode) {
            case REQUEST_CODE_GETIMAGE_BYCAMERA:
                startActionCrop(origUri);// 拍照后裁剪
                break;

            case REQUEST_CODE_GETIMAGE_BYCROP:
                startActionCrop(imageReturnIntent.getData());// 选图后裁剪
                break;
            case REQUEST_CODE_GETIMAGE_BYSDCARD:
                //发送上传头像的消息
                handler.sendEmptyMessage(TO_UPLOAD_FILE);

//			if (resultCode == RESULT_OK) {
//	            // 判断手机系统版本号
//	            if (Build.VERSION.SDK_INT >= 19) {
//	                // 4.4及以上系统使用这个方法处理图片
//	                handleImageOnKitKat(imageReturnIntent);
//	            } else {
//	                // 4.4以下系统使用这个方法处理图片
//	                handleImageBeforeKitKat(imageReturnIntent);
//	            }
//	        }

                break;
            case TAKE_PHOTO:

                if (resultCode == RESULT_OK) {
                    try {
                        if (PICTAG.equals("tx")) {
                            startActionCrop(imageUri);
                        } else {


                            // 将拍摄的照片显示出来
                            String url = getContentResolver().openInputStream(imageUri).toString();


                            Log.e("url", "url=" + url);
                            Log.e("imageUri", "imageUri=" + imageUri);

                            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                            String imagepath = imageUri.toString().replace("file://", "");
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = false;
                            options.inPreferredConfig = Config.RGB_565;
                            options.inDither = true;
                            Bitmap bm = BitmapFactory.decodeFile(imagepath);
                            File f = null;
                            FileOutputStream out = null;
                            try {
                                if (!FileUtils.isFileExist("")) {
                                    File tempf = FileUtils.createSDDir("");
                                }
                                f = new File(imagepath);
                                if (f.exists()) {
                                    f.delete();
                                }
                                out = new FileOutputStream(f);
                                bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                                out.flush();
                                out.close();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (PICTAG.equals("tx")) {

                                String fileKey = "__avatar1";
                                UploadUtil uploadUtil = UploadUtil.getInstance();
                                uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
                                //定义一个Map集合，封装请求服务端时需要的参数
                                Map<String, String> m = new HashMap<String, String>();
                                //在这里根据服务端需要的参数自己来定

                                m.put("userid", userinfo.getUserid());
                                String RequestURL = UrlFactory.PostChangeHeadPhotoUrl();

                                uploadUtil.uploadFile(f, fileKey, RequestURL, m);

                                iv_agent_Photo.setImageBitmap(bitmap);
                            } else {

                                String fileKey = "img";
                                UploadUtil uploadUtil = UploadUtil.getInstance();
                                uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
                                //定义一个Map集合，封装请求服务端时需要的参数
                                Map<String, String> m = new HashMap<String, String>();
                                //在这里根据服务端需要的参数自己来定

                                m.put("userid", userid);
                                m.put("catid", catid);
                                m.put("module", "content");

                                uploadUtil.uploadFile(f, fileKey, UrlFactory.PostupLoadPic(), m);


                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {

                    if (PICTAG.equals("tx")) {
                        startActionCrop(imageReturnIntent.getData());
                    } else {

                        // 判断手机系统版本号
                        if (Build.VERSION.SDK_INT >= 19) {
                            // 4.4及以上系统使用这个方法处理图片
                            handleImageOnKitKat(imageReturnIntent);
                        } else {
                            // 4.4以下系统使用这个方法处理图片
                            handleImageBeforeKitKat(imageReturnIntent);
                        }
                    }

                }


                break;
            case 10:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号

                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(imageReturnIntent);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(imageReturnIntent);
                    }
                }
                break;
            case 11:
                toUploadFileTake(imageReturnIntent.getData());
                break;
        }
    }

    private void toUploadFileTake(Uri data) {
        String imagepath = cropUri.getPath();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        options.inDither = true;
        Bitmap bm = BitmapFactory.decodeFile(imagepath);
        File f = null;
        FileOutputStream out = null;
        try {
            if (!FileUtils.isFileExist("")) {
                File tempf = FileUtils.createSDDir("");
            }
            f = new File(imagepath);
            if (f.exists()) {
                f.delete();
            }
            out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (PICTAG.equals("tx")) {
            String fileKey = "__avatar1";
            UploadUtil uploadUtil = UploadUtil.getInstance();
            uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
            //定义一个Map集合，封装请求服务端时需要的参数
            Map<String, String> m = new HashMap<String, String>();
            //在这里根据服务端需要的参数自己来定

            m.put("userid", userinfo.getUserid());
            String RequestURL = UrlFactory.PostChangeHeadPhotoUrl();

            uploadUtil.uploadFile(f, fileKey, RequestURL, m);
            if (imagepath != null) {
                options = new BitmapFactory.Options();
                options.inJustDecodeBounds = false;
                options.inPreferredConfig = Config.RGB_565;
                options.inDither = true;
                Bitmap bitmap = BitmapFactory.decodeFile(imagepath);
                iv_agent_Photo.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
            }

        } else {

            String fileKey = "img";
            UploadUtil uploadUtil = UploadUtil.getInstance();
            uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
            //定义一个Map集合，封装请求服务端时需要的参数
            Map<String, String> m = new HashMap<String, String>();
            //在这里根据服务端需要的参数自己来定

            m.put("userid", userid);
            m.put("catid", catid);
            m.put("module", "content");

            uploadUtil.uploadFile(f, fileKey, UrlFactory.PostupLoadPic(), m);

        }

    }

    private void startPhotoZoomtake(Uri data) {
            /*
             * 至于下面这个Intent的ACTION是怎么知道的，大家可以看下自己路径下的如下网页
	         * yourself_sdk_path/docs/reference/android/content/Intent.html  
	         * 直接在里面Ctrl+F搜：CROP ，之前小马没仔细看过，其实安卓系统早已经有自带图片裁剪功能,  
	         * 是直接调本地库的，小马不懂C C++  这个不做详细了解去了，有轮子就用轮子，不再研究轮子是怎么  
	         * 制做的了...吼吼  
	         */
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.putExtra("output", this.getUploadTempFile(data));
        intent.setDataAndType(data, "image/*");
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 11);

    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        if (!PICTAG.equals("tx")) {
            String imagePath = null;
            Uri uri = data.getData();
            Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
            if (DocumentsContract.isDocumentUri(this, uri)) {
                // 如果是document类型的Uri，则通过document id处理
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                    String id = docId.split(":")[1]; // 解析出数字格式的id
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(contentUri, null);
                }
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                // 如果是content类型的Uri，则使用普通方式处理
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                // 如果是file类型的Uri，直接获取图片路径即可
                imagePath = uri.getPath();
            }
            displayImage(imagePath); // 根据图片路径显示图片
        } else {
            displayImage(cropUri.getPath()); // 根据图片路径显示图片
        }


    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);

    }


    public void startPhotoZoom(Uri uri) {
            /*
             * 至于下面这个Intent的ACTION是怎么知道的，大家可以看下自己路径下的如下网页
	         * yourself_sdk_path/docs/reference/android/content/Intent.html  
	         * 直接在里面Ctrl+F搜：CROP ，之前小马没仔细看过，其实安卓系统早已经有自带图片裁剪功能,  
	         * 是直接调本地库的，小马不懂C C++  这个不做详细了解去了，有轮子就用轮子，不再研究轮子是怎么  
	         * 制做的了...吼吼  
	         */
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("output", this.getUploadTempFile(uri));
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 10);
    }


    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        Log.e("path", "path=" + path);
        return path;
    }

    private void displayImage(String imagePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        options.inDither = true;
        Bitmap bm = BitmapFactory.decodeFile(imagePath);
        File f = null;
        FileOutputStream out = null;
        try {
            if (!FileUtils.isFileExist("")) {
                File tempf = FileUtils.createSDDir("");
            }
            f = new File(imagePath);
            if (f.exists()) {
                f.delete();
            }
            out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (PICTAG.equals("tx")) {
            String fileKey = "__avatar1";
            UploadUtil uploadUtil = UploadUtil.getInstance();
            uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
            //定义一个Map集合，封装请求服务端时需要的参数
            Map<String, String> m = new HashMap<String, String>();
            //在这里根据服务端需要的参数自己来定

            m.put("userid", userinfo.getUserid());
            String RequestURL = UrlFactory.PostChangeHeadPhotoUrl();

            uploadUtil.uploadFile(f, fileKey, RequestURL, m);
            if (imagePath != null) {
                options = new BitmapFactory.Options();
                options.inJustDecodeBounds = false;
                options.inPreferredConfig = Config.RGB_565;
                options.inDither = true;
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                iv_agent_Photo.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
            }


        } else {

            String fileKey = "img";
            UploadUtil uploadUtil = UploadUtil.getInstance();
            uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
            //定义一个Map集合，封装请求服务端时需要的参数
            Map<String, String> m = new HashMap<String, String>();
            //在这里根据服务端需要的参数自己来定

            m.put("userid", userid);
            m.put("catid", catid);
            m.put("module", "content");

            uploadUtil.uploadFile(f, fileKey, UrlFactory.PostupLoadPic(), m);

        }


    }


    private Handler handler = new Handler() {
        private String SuccessImageUrl;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TO_UPLOAD_FILE:
                    toUploadFile();
                    break;
                case 3:

                    SuccessImageUrl = (String) msg.obj;
                    userinfo.setTheme(SuccessImageUrl);

                    HouseGoApp.saveLoginInfo(getApplicationContext(), userinfo);
                    HouseGoApp.getContext().SaveCurrentUserInfo(userinfo);
                    onResume();
                    Glide.with(getApplicationContext()).load(SuccessImageUrl).into(iv_agent_Photo);

                    break;

                case UPLOAD_FILE_DONE:
                    //响应返回的结果
                    if (msg.arg1 == UploadUtil.UPLOAD_SUCCESS_CODE) {
                        String result = (String) msg.obj;
                        try {
                            //上传成功之后，服务端返回的数据
                            JSONObject obj = new JSONObject(result);
                            //获取服务端返回的头像地址
                            String portrait = obj.getString("portrait");
                            //根据自己业务做后续处理

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else if (msg.arg1 == UploadUtil.UPLOAD_SERVER_ERROR_CODE) {
                        Toast.makeText(AgentChangeUserInfoActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    /**
     * 请求裁剪
     *
     * @param origUri
     */
    private void startActionCrop(Uri data) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("output", this.getUploadTempFile(data));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);// 输出图片大小
        intent.putExtra("outputY", 200);
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        startActivityForResult(intent,
                REQUEST_CODE_GETIMAGE_BYSDCARD);
    }

    /**
     * 上传头像到服务器
     */
    protected void toUploadFile() {

        Log.e("sssssssss", "sssssss=" + cropUri.getPath());
        Log.e("sssssssss", "sssssss=" + PICPATH);

//		imageUri=cropUri.getPath();

//		 String   imagepath=imageUri.toString().replace("file://", "");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        options.inDither = true;
        Bitmap bm = BitmapFactory.decodeFile(cropUri.getPath());
        File f = null;
        FileOutputStream out = null;

        try {
            if (!FileUtils.isFileExist("")) {
                File tempf = FileUtils.createSDDir("");
            }
            f = new File(cropUri.getPath());
            if (f.exists()) {
                f.delete();
            }
            out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (PICTAG.equals("tx")) {
            String fileKey = "__avatar1";
            UploadUtil uploadUtil = UploadUtil.getInstance();
            uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
            //定义一个Map集合，封装请求服务端时需要的参数
            Map<String, String> m = new HashMap<String, String>();
            //在这里根据服务端需要的参数自己来定

            m.put("userid", userinfo.getUserid());
            String RequestURL = UrlFactory.PostChangeHeadPhotoUrl();

            uploadUtil.uploadFile(f, fileKey, RequestURL, m);
            if (cropUri.getPath() != null) {
                options = new BitmapFactory.Options();
                options.inJustDecodeBounds = false;
                options.inPreferredConfig = Config.RGB_565;
                options.inDither = true;
                Bitmap bitmap = BitmapFactory.decodeFile(cropUri.getPath());
                iv_agent_Photo.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
            }

        } else {

            String fileKey = "img";
            UploadUtil uploadUtil = UploadUtil.getInstance();
            uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
            //定义一个Map集合，封装请求服务端时需要的参数
            Map<String, String> m = new HashMap<String, String>();
            //在这里根据服务端需要的参数自己来定

            m.put("userid", userid);
            m.put("catid", catid);
            m.put("module", "content");

            uploadUtil.uploadFile(f, fileKey, UrlFactory.PostupLoadPic(), m);

        }


    }

    //获取保存头像地址的Uri
    private Uri getUploadTempFile(Uri uri) {
        String portraitPath = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //保存图像的文件夹路径
            portraitPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/image/Portrait";
            File saveDir = new File(portraitPath);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
        } else {
            Toast.makeText(AgentChangeUserInfoActivity.this, "无法保存照片，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
            ;
            return null;
        }

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());

        String thePath = getAbsolutePathFromNoStandardUri(uri);
        if (thePath.isEmpty()) {
            thePath = getAbsoluteImagePath(uri);
        }

        PICPATH = thePath;

        //获取图片路径的扩展名
        String ext = thePath.substring(thePath.lastIndexOf('.') + 1);
        ext = ext.isEmpty() ? "jpg" : ext;

        // 照片命名
        String cropFileName = "crop_" + timeStamp + "." + ext;
        protraitFile = new File(portraitPath, cropFileName);
        cropUri = Uri.fromFile(protraitFile);
        return cropUri;
    }


    /**
     * 判断当前Url是否标准的content://样式，如果不是，则返回绝对路径
     *
     * @param uri
     * @return
     */
    private String getAbsolutePathFromNoStandardUri(Uri mUri) {
        String filePath = "";

        String mUriString = mUri.toString();
        mUriString = Uri.decode(mUriString);

        String pre1 = "file://" + SDCARD + File.separator;
        String pre2 = "file://" + SDCARD_MNT + File.separator;

        if (mUriString.startsWith(pre1)) {
            filePath = Environment.getExternalStorageDirectory().getPath()
                    + File.separator + mUriString.substring(pre1.length());
        } else if (mUriString.startsWith(pre2)) {
            filePath = Environment.getExternalStorageDirectory().getPath()
                    + File.separator + mUriString.substring(pre2.length());
        }
        return filePath;
    }

    /**
     * 通过uri获取文件的绝对路径
     *
     * @param uri
     * @return
     */
    @SuppressWarnings("deprecation")
    private String getAbsoluteImagePath(Uri uri) {

        String imagePath = "";
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = AgentChangeUserInfoActivity.this.managedQuery(uri, proj, // Which columns to
                // return
                null, // WHERE clause; which rows to return (all rows)
                null, // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)

        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                imagePath = cursor.getString(column_index);
            }
        }
        return imagePath;
    }


    @Override
    public void onUploadDone(int responseCode, String message) {


        try {
            if (PICTAG.equals("tx")) {
                JSONObject j = new JSONObject(message);
                String url = j.getString("avatarUrls");


                Message msg = new Message();
                msg.obj = url;
                msg.what = 3;
                handler.sendMessage(msg);
            } else {

                JSONObject j = new JSONObject(message);
                String url = j.getString("url");
                userinfopresenter.ChangeShenFenZhengPic(userinfo.getUserid(), url);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onUploadProcess(int uploadSize) {
        // TODO Auto-generated method stub

    }


    @Override
    public void initUpload(int fileSize) {
        // TODO Auto-generated method stub

    }


    private void showTouxaingWindow() {
        popTouXiang = new PopupWindow(AgentChangeUserInfoActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows_touxiang, null);

        ll_popup_touxiang = (LinearLayout) view.findViewById(R.id.ll_popup_touxaing);
        ll_cancle_touxiang = (LinearLayout) view.findViewById(R.id.ll_cancle_touxaing);
        popTouXiang.setWidth(LayoutParams.MATCH_PARENT);
        popTouXiang.setHeight(LayoutParams.WRAP_CONTENT);
        popTouXiang.setBackgroundDrawable(new BitmapDrawable());
        popTouXiang.setFocusable(true);
        popTouXiang.setOutsideTouchable(true);
        popTouXiang.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_touxaing);
        Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_touxaing1);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_touxaing2);
        Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_touxaing_cancel);

        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                popTouXiang.dismiss();
                ll_popup_touxiang.clearAnimation();
                ll_cancle_touxiang.clearAnimation();
            }
        });
        bt1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // 照相
                // 创建File对象，用于存储拍照后的图片
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT < 24) {
                    imageUri = Uri.fromFile(outputImage);
                } else {
                    imageUri = FileProvider.getUriForFile(AgentChangeUserInfoActivity.this, "com.example.cameraalbumtest.fileprovider", outputImage);
                }

                // 启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);


                popTouXiang.dismiss();
                ll_popup_touxiang.clearAnimation();
                ll_cancle_touxiang.clearAnimation();
            }
        });


        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(AgentChangeUserInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AgentChangeUserInfoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
                popTouXiang.dismiss();
                ll_popup_touxiang.clearAnimation();
                ll_cancle_touxiang.clearAnimation();
            }
        });

        bt3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                popTouXiang.dismiss();
                ll_popup_touxiang.clearAnimation();
                ll_cancle_touxiang.clearAnimation();
            }
        });

    }


    private void openAlbum() {
//        Intent intent = new Intent("android.intent.action.GET_CONTENT");
//        intent.setType("image/*");
//        startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    CHOOSE_PHOTO);
        } else {
            intent = new Intent(Intent.ACTION_PICK,
                    Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    CHOOSE_PHOTO);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }


    private void showPopWindowType() {
        popType = new PopupWindow(AgentChangeUserInfoActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows_zhiyetype, null);

        ll_popup_zhiyeType = (LinearLayout) view.findViewById(R.id.ll_popup_yizhitype);
        ll_cancle_zhiyeType = (LinearLayout) view.findViewById(R.id.ll_cancle_yizhitype);
        popType.setWidth(LayoutParams.MATCH_PARENT);
        popType.setHeight(LayoutParams.WRAP_CONTENT);
        popType.setBackgroundDrawable(new BitmapDrawable());
        popType.setFocusable(true);
        popType.setOutsideTouchable(true);
        popType.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_yizhitype);
        Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_yizhitype_btn1);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_yizhitype_btn2);
        Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_yizhitype_cancel);
        Button bt4 = (Button) view.findViewById(R.id.item_popupwindows_yizhitype_btn3);

        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                popType.dismiss();
                ll_popup_zhiyeType.clearAnimation();
                ll_cancle_zhiyeType.clearAnimation();
            }
        });

        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                String userid = userinfo.getUserid();
                String leixing = "个人";
                userinfopresenter.ChangeLeixing(userid, leixing);
                tv_agent_zhiyeType.setText(leixing);
                rl_agent_companyName.setVisibility(View.GONE);
                line_gongsiname.setVisibility(View.GONE);
                popType.dismiss();
                ll_popup_zhiyeType.clearAnimation();
                ll_cancle_zhiyeType.clearAnimation();
            }
        });
        bt3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                popType.dismiss();
                ll_popup_zhiyeType.clearAnimation();
                ll_cancle_zhiyeType.clearAnimation();
            }
        });
        bt4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = userinfo.getUserid();
                String leixing = "公司";
                userinfopresenter.ChangeLeixing(userid, leixing);
                tv_agent_zhiyeType.setText(leixing);
                rl_agent_companyName.setVisibility(View.VISIBLE);
                line_gongsiname.setVisibility(View.VISIBLE);
                popType.dismiss();
                ll_popup_zhiyeType.clearAnimation();
                ll_cancle_zhiyeType.clearAnimation();
            }
        });

    }

    private void showPopWindowSex() {
        popSex = new PopupWindow(AgentChangeUserInfoActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows_1, null);

        ll_popupSex = (LinearLayout) view.findViewById(R.id.ll_popup);
        ll_cancle = (LinearLayout) view.findViewById(R.id.ll_cancle);
        popSex.setWidth(LayoutParams.MATCH_PARENT);
        popSex.setHeight(LayoutParams.WRAP_CONTENT);
        popSex.setBackgroundDrawable(new BitmapDrawable());
        popSex.setFocusable(true);
        popSex.setOutsideTouchable(true);
        popSex.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_btn1);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_btn2);
        Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);

        bt1.setText("男");
        bt2.setText("女");
        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                popSex.dismiss();
                ll_popupSex.clearAnimation();
                ll_cancle.clearAnimation();
            }
        });

        bt1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String userid = userinfo.getUserid();
                String sex = "1";
                userinfopresenter.ChangeSex(userid, sex);
                tv_agent_sex.setText("男");
                popSex.dismiss();
                ll_popupSex.clearAnimation();
                ll_cancle.clearAnimation();
            }
        });
        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String userid = userinfo.getUserid();
                String sex = "2";
                userinfopresenter.ChangeSex(userid, sex);
                tv_agent_sex.setText("女");
                popSex.dismiss();
                ll_popupSex.clearAnimation();
                ll_cancle.clearAnimation();
            }
        });
        bt3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                popSex.dismiss();
                ll_popupSex.clearAnimation();
                ll_cancle.clearAnimation();
            }
        });
    }

    protected void onResume() {
        userinfo = HouseGoApp.getLoginInfo(getApplicationContext());
        if (userinfo == null) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            String url = userinfo.getUserpic();
            Glide.with(this).load(url).into(iv_agent_Photo);

            if (userinfo.getModelid() == null || userinfo.getModelid().equals("35")) {
                tv_agent_viptype.setText("普通会员");
            } else {
                tv_agent_viptype.setText("经纪人");
            }

            if (userinfo.getSex() == null || userinfo.getSex().equals("0")) {
                tv_agent_sex.setText("未知");
            } else if (userinfo.getSex().equals("1")) {
                tv_agent_sex.setText("男");
            } else {
                tv_agent_sex.setText("女");
            }
            tv_agent_fenjihao_show.setText(userinfo.getUsername() == null ? "" : userinfo.getUsername());
            tv_agent_fenjihao_show_1.setText(userinfo.getVtel() == null ? "" : userinfo.getVtel());

            tv_agent_realname.setText(userinfo.getRealname() == null ? "" : userinfo.getRealname());
            tv_agent_phone.setText(userinfo.getUsername() + "");
            if (userinfo.getZhuanjie() == null || userinfo.getZhuanjie().equals("0")) {
                tv_agent_fenjihao.setText(userinfo.getUsername() == null ? "" : userinfo.getUsername());
            } else {
                tv_agent_fenjihao.setText(userinfo.getVtel() == null ? "" : userinfo.getVtel());
            }
            tv_agent_shenfenzheng.setText(userinfo.getCardnumber() == null ? "" : userinfo.getCardnumber());
            tv_agent_mainarea.setText(userinfo.getMainarea() == null ? "" : userinfo.getMainarea());
            if (userinfo.getLeixing() == null || userinfo.getLeixing().equals("个人")) {
                tv_agent_zhiyeType.setText(userinfo.getLeixing() == null ? "" : userinfo.getLeixing());
                rl_agent_companyName.setVisibility(View.GONE);
                line_gongsiname.setVisibility(View.GONE);
            } else {
                tv_agent_zhiyeType.setText(userinfo.getLeixing() == null ? "" : userinfo.getLeixing());
                rl_agent_companyName.setVisibility(View.VISIBLE);
                line_gongsiname.setVisibility(View.VISIBLE);
            }

            tv_agent_chongyeshijian.setText(userinfo.getWorktime() == null ? "" : userinfo.getWorktime() + "年");
            et_agent_changrealname.setText(userinfo.getRealname() == null ? "" : userinfo.getRealname());

            tv_gerenshuoming.setText(userinfo.getAbout() == null ? "" : userinfo.getAbout());
            et_agent_changegerenjieshao.setText(tv_gerenshuoming.getText() == null ? "" : tv_gerenshuoming.getText());
            tv_gongsi.setText(userinfo.getConame() == null ? "" : userinfo.getConame());
            et_agent_changecompanyname.setText(userinfo.getConame() == null ? "" : userinfo.getConame());
            check = userinfo.getMainarea();

            if (userinfo.getSfzpic() != null && userinfo.getSfzpic().length() > 0 && !userinfo.getSfzpic().equals("null")) {
                tv_sfz_pic.setText("身份证已上传");
                tv_sfz_pic.setTextColor(getResources().getColor(android.R.color.white));
                tv_sfz_pic.setBackgroundColor(getResources().getColor(R.color.light_blue));
            } else {
                tv_sfz_pic.setText("身份证未上传");
                tv_sfz_pic.setTextColor(getResources().getColor(R.color.conusertextcolor));
                tv_sfz_pic.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
        super.onResume();
    }

    private void setListener() {
        ll_agent_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_agent_Photo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

//				llPopup.startAnimation(AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this,R.anim.activity_slide_enter_bottom));
//				careatpop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                Animation anim = AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this, R.anim.activity_translate_in);
                PICTAG = "tx";
                ll_popup_touxiang.setAnimation(anim);
                ll_cancle_touxiang.setAnimation(anim);
                popTouXiang.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

            }
        });

        rl_agent_realname.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                agent_changeshow.setVisibility(View.GONE);
                ll_agent_changerealname.setVisibility(View.VISIBLE);
            }
        });

        rl_agent_sex.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this,
                        R.anim.activity_translate_in);
                ll_popupSex.setAnimation(anim);
                ll_cancle.setAnimation(anim);
                popSex.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
            }

        });

        rl_agent_fenjihao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                agent_changeshow.setVisibility(View.GONE);

                if (userinfo.getZhuanjie().equals("0")) {
                    agent_fenjihao_shenqing.setVisibility(View.VISIBLE);
                } else {
                    agent_fenjihao_jiebang.setVisibility(View.VISIBLE);
                }
                ll__aegnt_changfenjiahao.setVisibility(View.VISIBLE);

            }
        });

        rl_agent_gerenjieshao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                agent_changeshow.setVisibility(View.GONE);
                ll_agent_gerenjieshao.setVisibility(View.VISIBLE);
            }
        });

        rl_agent_shenfenzheng.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                agent_changeshow.setVisibility(View.GONE);
                ll_agent_changeshenfenzheng.setVisibility(View.VISIBLE);
            }
        });

        rl_agent_mainarea.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CityCheckDialog();
            }
        });

        rl_agent_zhiyeType.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this,
                        R.anim.activity_translate_in);

                ll_popup_zhiyeType.setAnimation(anim);
                ll_cancle_zhiyeType.setAnimation(anim);
                popType.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
            }
        });

        rl_agent_companyName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                agent_changeshow.setVisibility(View.GONE);
                ll_agent_changecompanyname.setVisibility(View.VISIBLE);
            }
        });

        rl_agent_chongyeshijian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this,
                        R.anim.activity_translate_in);

                ll_popup_worktime.setAnimation(anim);
                ll_cancle_worktime.setAnimation(anim);
                popWorkTime.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
            }
        });

        rl_agent_upload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//				if(userinfo.getSfzpic()==null||userinfo.getSfzpic()=="" || userinfo.getSfzpic().equals("")){
//					llPopup.startAnimation(AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this,R.anim.activity_slide_enter_bottom));
//					careatpop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
//                PICTAG = "sfz";
//                Animation anim = AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this, R.anim.activity_translate_in);
//
//                ll_popup_touxiang.setAnimation(anim);
//                ll_cancle_touxiang.setAnimation(anim);
//                popTouXiang.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
                if (userinfo.getSfzpic() != null && userinfo.getSfzpic().length() > 0 && !userinfo.getSfzpic().equals("null")) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "身份证已上传");
                } else {
                    Intent i = new Intent(AgentChangeUserInfoActivity.this, PublishedActivity.class);
                    i.putExtra("TAG", "18");
                    startActivityForResult(i, PublishedActivity.UploadPERSONSFZ);
                }
            }
        });

        rl_agent_changepassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                agent_changeshow.setVisibility(View.GONE);
                ll_agent_changepassword.setVisibility(View.VISIBLE);
            }
        });

        /**
         * 各个模型功能 真实姓名
         *
         */

        ll_agent_changerealname_back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ll_agent_changerealname.setVisibility(View.GONE);
                agent_changeshow.setVisibility(View.VISIBLE);

            }
        });


        tv_agent_changerealname_save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = userinfo.getUserid();
                String realname = et_agent_changrealname.getText().toString();
                String modelid = userinfo.getModelid();
                userinfopresenter.ChangeRealName(userid, realname, modelid);
                tv_agent_realname.setText(realname);
                h.postDelayed(new Runnable() {
                    public void run() {
                        ll_agent_changerealname.setVisibility(View.GONE);
                        agent_changeshow.setVisibility(View.VISIBLE);
                        onResume();
                    }
                }, 1000);

            }
        });

        /**
         * 各个模型功能 分机号
         */
        ll_agent_changefenjihao_back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                agent_fenjihao_jiebang.setVisibility(View.GONE);
                agent_fenjihao_shenqing.setVisibility(View.GONE);
                ll__aegnt_changfenjiahao.setVisibility(View.GONE);
                agent_changeshow.setVisibility(View.VISIBLE);

            }
        });

        btn_agent_changefenjihao_shenqing.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String tel = userinfo.getUsername();
                String userid = userinfo.getUserid();
                userinfopresenter.ChangeSQfenjihao(tel, userid);

                h.postDelayed(new Runnable() {
                    public void run() {
                        agent_fenjihao_shenqing.setVisibility(View.GONE);
                        ll__aegnt_changfenjiahao.setVisibility(View.GONE);
                        agent_changeshow.setVisibility(View.VISIBLE);
                        onResume();
                    }
                }, 1000);

            }
        });
        btn_agent_changefenjihao_jiebang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String tel = userinfo.getUsername();
                String userid = userinfo.getUserid();
                userinfopresenter.ChangeJBfenjihao(tel, userid);

                h.postDelayed(new Runnable() {
                    public void run() {
                        agent_fenjihao_jiebang.setVisibility(View.GONE);
                        ll__aegnt_changfenjiahao.setVisibility(View.GONE);
                        agent_changeshow.setVisibility(View.VISIBLE);
                        onResume();
                    }
                }, 1000);

            }
        });

        /**
         * 各个模型功能 个人介绍
         */
        ll_agent_changegerenjieshao_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_agent_gerenjieshao.setVisibility(View.GONE);
                agent_changeshow.setVisibility(View.VISIBLE);
            }
        });

        tv_agent_changegerenjieshao_save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String userid = userinfo.getUserid();
                String about = et_agent_changegerenjieshao.getText().toString();
                userinfopresenter.ChangeGeRenJieshao(userid, about);
                tv_gerenshuoming.setText(about);

                h.postDelayed(new Runnable() {
                    public void run() {
                        ll_agent_gerenjieshao.setVisibility(View.GONE);
                        agent_changeshow.setVisibility(View.VISIBLE);
                        onResume();
                    }
                }, 1000);

            }
        });

        /**
         * 各个模型功能 身份证号码
         */
        ll_agent_changeshenfenzheng_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_agent_changeshenfenzheng.setVisibility(View.GONE);
                agent_changeshow.setVisibility(View.VISIBLE);
            }
        });

        tv_agent_changeshenfenzheng_save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String userid = userinfo.getUserid();
                String cardnumber = et_agent_changeshenfenzheng.getText().toString();
                userinfopresenter.ChangeShenFenZheng(userid, cardnumber);
                h.postDelayed(new Runnable() {
                    public void run() {
                        ll_agent_changeshenfenzheng.setVisibility(View.GONE);
                        agent_changeshow.setVisibility(View.VISIBLE);
                        onResume();
                    }
                }, 1000);
            }
        });

        /**
         * 各个模型功能 公司名称
         */
        ll_agent_changecompanyname_back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ll_agent_changecompanyname.setVisibility(View.GONE);
                agent_changeshow.setVisibility(View.VISIBLE);
            }
        });

        tv_agent_changecompanyname_save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String userid = userinfo.getUserid();
                String coname = et_agent_changecompanyname.getText().toString();
                userinfopresenter.ChangeConame(userid, coname);
                tv_gongsi.setText(coname);

                h.postDelayed(new Runnable() {
                    public void run() {
                        ll_agent_changecompanyname.setVisibility(View.GONE);
                        agent_changeshow.setVisibility(View.VISIBLE);
                        onResume();
                    }
                }, 1000);
            }
        });

        /**
         * 各个模型功能 密码修改
         */
        ll_agent_changepassword_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_agent_changepassword.setVisibility(View.GONE);
                agent_changeshow.setVisibility(View.VISIBLE);
            }
        });

        btn_agent_changepassword_submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = userinfo.getUsername();
                String userid = userinfo.getUserid();
                String oldpwd = et_agent_change_currentpassword.getText().toString();
                String pwd1 = et_agent_change_Newpassword.getText().toString();
                String pwd2 = et_agent_change_repassword.getText().toString();
                userinfopresenter.ChangePassword(username, userid, oldpwd, pwd1, pwd2);

                h.postDelayed(new Runnable() {
                    public void run() {
                        ll_agent_changepassword.setVisibility(View.GONE);
                        agent_changeshow.setVisibility(View.VISIBLE);
                        onResume();
                    }
                }, 1000);

            }
        });

    }

    private void setViews() {


        tv_gongsi = (TextView) findViewById(R.id.tv_gongsi);

        line_gongsiname = (Button) findViewById(R.id.line_gongsiname);
        tv_gerenshuoming = (TextView) findViewById(R.id.tv_gerenshuoming);
        tv_sfz_pic = (TextView) findViewById(R.id.tv_sfz_pic);
        ll_agent_back = (LinearLayout) findViewById(R.id.ll_agent_changeinfo_back);
        // 各个条目
        rl_agent_changepassword = (RelativeLayout) findViewById(R.id.rl_agent_changepassword);
        rl_agent_chongyeshijian = (RelativeLayout) findViewById(R.id.rl_agent_chongyeshijian);
        rl_agent_companyName = (RelativeLayout) findViewById(R.id.rl_agent_companyName);
        rl_agent_fenjihao = (RelativeLayout) findViewById(R.id.rl_agent_fenjihao);
        rl_agent_gerenjieshao = (RelativeLayout) findViewById(R.id.rl_agent_gerenjieshao);
        rl_agent_mainarea = (RelativeLayout) findViewById(R.id.rl_agent_mainarea);
        rl_agent_realname = (RelativeLayout) findViewById(R.id.rl_agent_realname);
        rl_agent_sex = (RelativeLayout) findViewById(R.id.rl_agent_sex);
        rl_agent_shenfenzheng = (RelativeLayout) findViewById(R.id.rl_agent_shenfenzheng);
        rl_agent_upload = (RelativeLayout) findViewById(R.id.rl_agent_upload);
        rl_agent_zhiyeType = (RelativeLayout) findViewById(R.id.rl_agent_zhiyeType);
        // 显示文本
        tv_agent_chongyeshijian = (TextView) findViewById(R.id.tv_agent_chongyeshijian);
        tv_agent_fenjihao = (TextView) findViewById(R.id.tv_agent_fenjihao);
        tv_agent_mainarea = (TextView) findViewById(R.id.tv_agent_mainarea);
        tv_agent_phone = (TextView) findViewById(R.id.tv_agent_phone);
        tv_agent_sex = (TextView) findViewById(R.id.tv_agent_sex);
        tv_agent_shenfenzheng = (TextView) findViewById(R.id.tv_agent_shenfenzheng);
        tv_agent_viptype = (TextView) findViewById(R.id.tv_agent_viptype);
        tv_agent_zhiyeType = (TextView) findViewById(R.id.tv_agent_zhiyeType);
        tv_agent_realname = (TextView) findViewById(R.id.tv_agent_realname);
        // 图片
        iv_agent_Photo = (CircleImageView) findViewById(R.id.iv_agent_Photo);
        // 整个框架
        agent_changeshow = (MyReboundScrollView) findViewById(R.id.agent_changeshow);

        // 真实姓名栏目
        ll_agent_changerealname = (LinearLayout) findViewById(R.id.ll_agent_changerealname);
        ll_agent_changerealname_back = (LinearLayout) findViewById(R.id.ll_agent_changerealname_back);
        tv_agent_changerealname_save = (TextView) findViewById(R.id.tv_agent_changerealname_save);
        et_agent_changrealname = (EditText) findViewById(R.id.et_agent_changrealname);
        // 分机号
        ll__aegnt_changfenjiahao = (LinearLayout) findViewById(R.id.ll_aegnt_changfenjiahao);
        ll_agent_changefenjihao_back = (LinearLayout) findViewById(R.id.ll_agent_changefenjihao_back);
        agent_fenjihao_jiebang = (LinearLayout) findViewById(R.id.agent_fenjihao_jiebang);
        agent_fenjihao_shenqing = (LinearLayout) findViewById(R.id.agent_fenjihao_shenqing);
        tv_agent_fenjihao_show = (TextView) findViewById(R.id.tv_agent_fenjihao_show);
        tv_agent_fenjihao_show_1 = (TextView) findViewById(R.id.tv_agent_fenjihao_show_1);
        btn_agent_changefenjihao_shenqing = (Button) findViewById(R.id.btn_agent_changefenjihao_shenqing);
        btn_agent_changefenjihao_jiebang = (Button) findViewById(R.id.btn_agent_changefenjihao_jiebang);
        // 个人说明
        ll_agent_gerenjieshao = (LinearLayout) findViewById(R.id.ll_agent_gerenjieshao);
        ll_agent_changegerenjieshao_back = (LinearLayout) findViewById(R.id.ll_agent_changegerenjieshao_back);
        tv_agent_changegerenjieshao_save = (TextView) findViewById(R.id.tv_agent_changegerenjieshao_save);
        et_agent_changegerenjieshao = (EditText) findViewById(R.id.et_agent_changegerenjieshao);
        // 身份证
        ll_agent_changeshenfenzheng = (LinearLayout) findViewById(R.id.ll_agent_changeshenfenzheng);
        ll_agent_changeshenfenzheng_back = (LinearLayout) findViewById(R.id.ll_agent_changeshenfenzheng_back);
        tv_agent_changeshenfenzheng_save = (TextView) findViewById(R.id.tv_agent_changeshenfenzheng_save);
        et_agent_changeshenfenzheng = (EditText) findViewById(R.id.et_agent_changeshenfenzheng);
        // 公司名称
        ll_agent_changecompanyname = (LinearLayout) findViewById(R.id.ll_agent_changecompanyname);
        ll_agent_changecompanyname_back = (LinearLayout) findViewById(R.id.ll_agent_changecompanyname_back);
        tv_agent_changecompanyname_save = (TextView) findViewById(R.id.tv_agent_changecompanyname_save);
        et_agent_changecompanyname = (EditText) findViewById(R.id.et_agent_changecompanyname);
        // 密码修改
        ll_agent_changepassword = (LinearLayout) findViewById(R.id.ll_agent_changepassword);
        ll_agent_changepassword_back = (LinearLayout) findViewById(R.id.ll_agent_changepassword_back);
        et_agent_change_currentpassword = (EditText) findViewById(R.id.et_agent_change_currentpassword);
        et_agent_change_Newpassword = (EditText) findViewById(R.id.et_agent_change_Newpassword);
        et_agent_change_repassword = (EditText) findViewById(R.id.et_agent_change_repassword);
        btn_agent_changepassword_submit = (Button) findViewById(R.id.btn_agent_changepassword_submit);

    }

    @Override
    public void changeInfo(final String info) {
//		Message msg = new Message();
//		h.sendMessage(msg);

        MyToastShowCenter.CenterToast(getApplicationContext(), info);

        h.postDelayed(new Runnable() {
            public void run() {
                if (info.equals("密码修改成功")) {
                    HouseGoApp.clearData(userinfo);
                    onResume();
                    startActivity(new Intent(getBaseContext(), LoginActivity.class));
                }
            }
        }, 1000);

        String userid = userinfo.getUserid();
        infomodel.login(userid, new AsycnCallBack() {
            @Override
            public void onSuccess(Object success) {
                UserInfo Nuserinfo = (UserInfo) success;
                HouseGoApp.getContext().SaveCurrentUserInfo(Nuserinfo);
                HouseGoApp.saveLoginInfo(getApplicationContext(), Nuserinfo);
                onResume();
            }

            @Override
            public void onError(Object error) {
            }
        });
    }

    public void CityCheckDialog() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.alerlog_check_city);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
        TextView tvCancle = (TextView) window.findViewById(R.id.tv_wheel_cancle);
        //TextView tvTitle = (TextView) window.findViewById(R.id.tv_wheel_title);

        CheckBox cb1 = (CheckBox) window.findViewById(R.id.check_1);
        CheckBox cb2 = (CheckBox) window.findViewById(R.id.check_2);
        CheckBox cb3 = (CheckBox) window.findViewById(R.id.check_3);
        CheckBox cb4 = (CheckBox) window.findViewById(R.id.check_4);
        CheckBox cb5 = (CheckBox) window.findViewById(R.id.check_5);
        CheckBox cb6 = (CheckBox) window.findViewById(R.id.check_6);
        CheckBox cb7 = (CheckBox) window.findViewById(R.id.check_7);
        CheckBox cb8 = (CheckBox) window.findViewById(R.id.check_8);
        CheckBox cb9 = (CheckBox) window.findViewById(R.id.check_9);
        CheckBox cb10 = (CheckBox) window.findViewById(R.id.check_10);
        CheckBox cb11 = (CheckBox) window.findViewById(R.id.check_11);
        CheckBox cb12 = (CheckBox) window.findViewById(R.id.check_12);
        final List<CheckBox> cblist = new ArrayList<CheckBox>();
        cblist.add(cb1);
        cblist.add(cb2);
        cblist.add(cb3);
        cblist.add(cb4);
        cblist.add(cb5);
        cblist.add(cb6);
        cblist.add(cb7);
        cblist.add(cb8);
        cblist.add(cb9);
        cblist.add(cb10);
        cblist.add(cb11);
        cblist.add(cb12);

        if (check != null) {
            if (check.contains(",")) {
                String[] cek = check.split(",");
                for (String ck : cek) {
                    for (int i = 0; i < CheckBoxArea.checks.size(); i++) {
                        CheckBoxArea s = CheckBoxArea.checks.get(i);
                        if (ck.equals(s.getCity())) {
                            cblist.get(i).setChecked(true);
                        }
                    }
                }
            } else {
                for (int i = 0; i < CheckBoxArea.checks.size(); i++) {
                    CheckBoxArea s = CheckBoxArea.checks.get(i);
                    if (check.equals(s.getCity())) {
                        cblist.get(i).setChecked(true);
                    }
                }
            }
        }

        tvSure.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int count = 0;
                String tx = "";
                for (CheckBox cb : cblist) {
                    if (cb.isChecked()) {
                        if (tx.length() <= 0)
                            tx = cb.getText().toString();
                        else
                            tx += "," + cb.getText().toString();
                        count++;
                    }
                }

                if (count > 2) {
                    MyToastShowCenter.CenterToast(getApplicationContext(), "最多只能选择两个区域！");
                } else {
                    userinfopresenter.ChangeMainArea(userinfo.getUserid(), tx);
                    tv_agent_mainarea.setText(tx);
                    alertDialog.cancel();
                }
            }
        });
        tvCancle.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

    }
}
