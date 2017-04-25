package com.example.testpic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dumu.housego.AgentChangeUserInfoActivity;
import com.dumu.housego.AgentPersonalActivity;
import com.dumu.housego.PuTongMyGuanZhuActivity;
import com.dumu.housego.R;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.UpLoadHtsfz;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.IUpLoadHTSFZPresenter;
import com.dumu.housego.presenter.UpLoadHTSFZPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.utils.UploadUtil;
import com.dumu.housego.utils.UploadUtil.OnUploadProcessListener;
import com.dumu.housego.view.IUpLoadHTSFZView;
import com.victor.loading.rotate.RotateLoading;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import util.FileUtils;

public class PublishedActivity extends BaseActivity implements IUpLoadHTSFZView,
        OnUploadProcessListener {
    public static final int ATERSHOUPIC = 20;
    public static final int ATRentingPIC = 21;
    public static final int PTERSHOUPIC = 22;
    public static final int PTRentingPIC = 23;
    public static final int UploadWTHT = 24;
    public static final int UploadATESHT = 25;
    public static final int UploadPTESHT = 26;
    public static final int UploadWTSFZ = 27;
    public static final int UploadPERSONSFZ = 28;
    private String fromTag;

    public static final int PICKER_REQUEST_CODE = 98;
    public static final int MODIFY_REQUEST_CODE = 23;

    private GridView noScrollgridview;
    private GridAdapter adapter;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    adapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private String id;
    private String username;
    private IUpLoadHTSFZPresenter htsfzPresenter;
    private UserInfo userinfo;
    private String userid;

    private int curUploadIndex = -1;
    private List<Pics> chuanPics = new ArrayList<Pics>();
    private int maxImageCount = 30;

    private PopupWindow Mpop;
    private Pics curModifyNamePic = null;

    private ViewGroup waitingBkView;
    private RotateLoading rotateLoading;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectimg);

        htsfzPresenter = new UpLoadHTSFZPresenter(this);
        userinfo = HouseGoApp.getLoginInfo(getApplicationContext());
        userid = userinfo.getUserid();

        InitData();
        InitView();
    }

    private void InitView() {
        noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAdapter(this);
        noScrollgridview.setAdapter(adapter);

        findViewById(R.id.cancel_action_imageview).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PublishedActivity.this.finish();
            }
        });
        findViewById(R.id.activity_selectimg_send).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                uploadpics();
            }
        });

        rotateLoading = (RotateLoading) findViewById(R.id.rotateloading);
        waitingBkView = (ViewGroup) findViewById(R.id.waitingBkView);
        waitingBkView.setVisibility(View.INVISIBLE);
    }

    private void InitData() {
        this.fromTag = getIntent().getStringExtra("TAG");
        if (this.fromTag == null || this.fromTag.length() <= 0)
            this.fromTag = "1";

        if (getIntent().getStringExtra("TAG").equals("5")) {
            id = getIntent().getStringExtra("ID");
            username = getIntent().getStringExtra("username");
        }
        if (getIntent().getStringExtra("TAG").equals("6")) {
            id = getIntent().getStringExtra("ID");
            username = getIntent().getStringExtra("username");
        }

        if(Integer.valueOf(this.fromTag) == 6 || Integer.valueOf(this.fromTag) == 18)
            maxImageCount = 3;
        else
            maxImageCount = 30;

        //最大选择图片数量
        //maxImageCount = getIntent().getIntExtra("maxSelectImageCount", 30);

        //删除临时文件
        String targetPath = FileUtils.SDPATH + "uploadtmp";
        FileUtils.deleteDir(targetPath);
        if (getIntent().getSerializableExtra("pics") != null) {
            chuanPics = (List<Pics>) getIntent().getSerializableExtra(
                    "pics");
            if (chuanPics != null && chuanPics.size() > 0) {
                for (Pics info : chuanPics) {
                    info.setImagepath("");
                }
            }
        }
    }

    protected void ChuanPic() {
        switch (Integer.valueOf(this.fromTag)) {
            case 1:
                Intent i1 = new Intent(this, PuTongMyGuanZhuActivity.class);
                i1.putExtra("uploadpics", (Serializable) chuanPics);
                setResult(PTERSHOUPIC, i1);
                finish();
                break;
            case 2:
                Intent i2 = new Intent(this, PuTongMyGuanZhuActivity.class);
                i2.putExtra("uploadpics", (Serializable) chuanPics);
                setResult(PTRentingPIC, i2);
                finish();
                break;

            case 3:
                Intent i3 = new Intent(this, AgentPersonalActivity.class);
                i3.putExtra("uploadpics", (Serializable) chuanPics);
                setResult(ATERSHOUPIC, i3);
                finish();
                break;
            case 4:
                Intent i4 = new Intent(this, AgentPersonalActivity.class);
                i4.putExtra("uploadpics", (Serializable) chuanPics);
                setResult(ATRentingPIC, i4);
                finish();
                break;
            case 5:
                uploadTHpic(chuanPics);
                Intent i5 = new Intent(this, AgentPersonalActivity.class);
                i5.putExtra("uploadpics", (Serializable) chuanPics);
                setResult(UploadWTHT, i5);
                finish();
                break;
            case 6:
                uploadTHpic(chuanPics);
                Intent i6 = new Intent(this, AgentPersonalActivity.class);
                i6.putExtra("uploadpics", (Serializable) chuanPics);
                setResult(UploadWTSFZ, i6);
                finish();
                break;
            case 18:
                Intent i7 = new Intent();
                i7.putExtra("uploadpics", (Serializable) chuanPics);
                setResult(Activity.RESULT_OK, i7);
                finish();
                break;
        }
    }

    private void uploadTHpic(List<Pics> chuanPics) {
        UpLoadHtsfz h = new UpLoadHtsfz();
        h.setId(id);
        h.setUsername(username);
        // list集合转json
        JSONArray json = new JSONArray();
        try {
            for (Pics a : chuanPics) {
                JSONObject jo = new JSONObject();
                jo.put("url", a.getUrl());
                jo.put("alt", a.getAlt());
                json.put(jo);
            }
        } catch (Exception e2) {
        }

        if (this.fromTag.equals("5")) {
            h.setContract(json.toString());
            h.setIdcard("");
        } else {
            h.setContract("");
            h.setIdcard(json.toString());
        }
        htsfzPresenter.HTSFZ(h);
    }

    private void showPopWindowYHQ() {
        Mpop = new PopupWindow(this);
        View view = getLayoutInflater().inflate(
                R.layout.popwin_change_imagename, null);
        Mpop.setWidth(LayoutParams.MATCH_PARENT);
        Mpop.setHeight(LayoutParams.MATCH_PARENT);
        Mpop.setBackgroundDrawable(new BitmapDrawable());
        Mpop.setFocusable(true);
        Mpop.setOutsideTouchable(true);
        Mpop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view
                .findViewById(R.id.parent_yhq_delete);
        final EditText et_yanzhenma_delete = (EditText) view
                .findViewById(R.id.et_yanzhenma_delete);
        TextView tv_querendelete = (TextView) view
                .findViewById(R.id.tv_querendelete);

        if (curModifyNamePic != null) {
            String old = curModifyNamePic.getAlt();
            et_yanzhenma_delete.setText(old);
        }

        parent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Mpop.dismiss();
            }
        });

        tv_querendelete.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String name = et_yanzhenma_delete.getText().toString();

                curModifyNamePic.setAlt(name);
                Mpop.dismiss();
                et_yanzhenma_delete.setText("");
                //刷新页面
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        });

    }

    private void uploadpics() {
        if (this.chuanPics != null && this.chuanPics.size() > 0) {
            boolean isHasUploadPics = false;
            for (Pics p : this.chuanPics) {
                if (p.getUrl() == null || p.getUrl().length() <= 0) {
                    if (p.getImagepath() != null && p.getImagepath().length() > 0) {
                        isHasUploadPics = true;
                        break;
                    }
                }
            }
            if (isHasUploadPics) {
                rotateLoading.start();
                waitingBkView.setVisibility(View.VISIBLE);
                this.curUploadIndex = -1;
                UploadUtil.getInstance().setOnUploadProcessListener(this);
                gotoNextUploadPics();
            } else {
                ChuanPic();
            }
        } else {
            this.finish();
        }
    }

    private void gotoNextUploadPics() {
        int index = 0;
        if (this.curUploadIndex == -1) {
            index = 0;
        } else {
            index++;
        }

        boolean isHasNext = false;
        Pics p = null;
        if (index < this.chuanPics.size()) {
            for (int i = index; i < this.chuanPics.size(); i++) {
                p = this.chuanPics.get(i);
                if (p.getUrl() == null || p.getUrl().length() <= 0) {
                    if (p.getImagepath() != null && p.getImagepath().length() > 0) {
                        this.curUploadIndex = i;
                        isHasNext = true;
                        break;
                    }
                }
            }
        }

        if (isHasNext && p != null) {
            uploadImageFile(p.getImagepath());
        } else {
            waitingBkView.setVisibility(View.INVISIBLE);
            rotateLoading.stop();
            //结束上传
            UploadUtil.getInstance().setOnUploadProcessListener(null);
            ChuanPic();
        }
    }

    private void uploadImageFile(String path) {
        UploadUtil uploadutil = UploadUtil.getInstance();
        Map<String, String> m = new HashMap<String, String>();
        m.put("userid", userid);
        if(Integer.valueOf(this.fromTag) == 18)
            m.put("catid", "52");
        else
            m.put("catid", "6");
        m.put("module", "content");
        String RequestURL = UrlFactory.PostupLoadPic();
        uploadutil.uploadFile(path, "img", RequestURL, m);
    }

    private void uploadImageFileFailed() {
        waitingBkView.setVisibility(View.INVISIBLE);
        rotateLoading.stop();
        //上传失败
        this.curUploadIndex = -1;
        UploadUtil.getInstance().setOnUploadProcessListener(null);
        MyToastShowCenter.CenterToast(this, "图片上传失败，请检查网络是否正常");
    }

    public void onUploadDone(final int responseCode, final String message) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (responseCode == UploadUtil.UPLOAD_SUCCESS_CODE) {
                    if (curUploadIndex >= 0 && curUploadIndex < chuanPics.size()) {
                        boolean uploadSuccess = false;
                        Pics p = chuanPics.get(curUploadIndex);
                        try {
                            JSONObject j = new JSONObject(message);
                            String url = j.getString("url");
                            p.setUrl(url);
                            uploadSuccess = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (uploadSuccess)
                            gotoNextUploadPics();
                        else
                            uploadImageFileFailed();
                    } else {
                        uploadImageFileFailed();
                    }
                } else {
                    uploadImageFileFailed();
                }
            }
        });
    }

    @Override
    public void onUploadProcess(int uploadSize) {
    }

    @Override
    public void initUpload(int fileSize) {
    }

    @Override
    public void HTSFZ(String info) {
        MyToastShowCenter.CenterToast(getApplication(), info);
    }

    public class GridAdapter extends BaseAdapter {
        private LayoutInflater inflater; //视图容器

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public int getCount() {
            if (chuanPics != null && chuanPics.size() > 0) {
                if (chuanPics.size() >= maxImageCount)
                    return chuanPics.size();
                else
                    return chuanPics.size() + 1;
            } else {
                return 1;
            }
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int arg0) {
            return 0;
        }

        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_published_grida,
                        parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView
                        .findViewById(R.id.item_grida_image);
                holder.et_image_name = (TextView) convertView
                        .findViewById(R.id.et_image_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (chuanPics != null && chuanPics.size() > 0) {
                if (chuanPics.size() >= maxImageCount) {
                    if (position < chuanPics.size())
                        setSelImageInfo(chuanPics.get(position), holder.image, holder.et_image_name);
                } else {
                    if (position < chuanPics.size())
                        setSelImageInfo(chuanPics.get(position), holder.image, holder.et_image_name);
                    else
                        setAddImageInfo(holder.image, holder.et_image_name);
                }
            } else {
                setAddImageInfo(holder.image, holder.et_image_name);
            }
            return convertView;
        }

        private void setSelImageInfo(final Pics info, ImageView imageView, TextView imageNameText) {
            imageNameText.setVisibility(View.VISIBLE);
            imageNameText.setText(info.getAlt());

            if (info.getImagepath() != null && info.getImagepath().length() > 0)
                Glide.with(PublishedActivity.this).load(info.getImagepath()).into(imageView);
            else if (info.getUrl() != null && info.getUrl().length() > 0)
                Glide.with(PublishedActivity.this).load(UrlFactory.TSFURL + info.getUrl()).into(imageView);
            else {
                Glide.clear(imageView);
                imageView.setImageResource(R.drawable.appicon160x160);
            }

//            if (info.getImagepath() != null && info.getImagepath().length() > 0)
//                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage("file://" + info.getImagepath(), imageView);
//            else if (info.getUrl() != null && info.getUrl().length() > 0)
//                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(UrlFactory.TSFURL + info.getUrl(), imageView);
//            else {
//                com.nostra13.universalimageloader.core.ImageLoader.getInstance().cancelDisplayTask(imageView);
//                imageView.setImageResource(R.drawable.appicon160x160);
//            }

            imageNameText.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    curModifyNamePic = info;
                    showPopWindowYHQ();
                    Mpop.showAtLocation(v, Gravity.CENTER, 0, 0);
                }
            });

            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(PublishedActivity.this,
                            PhotoActivity.class);
                    intent.putExtra("ID", chuanPics.indexOf(info));
                    intent.putExtra("pics", (Serializable) chuanPics);
                    startActivityForResult(intent, MODIFY_REQUEST_CODE);
                }
            });
        }

        private void setAddImageInfo(ImageView imageView, TextView imageNameText) {
            Glide.clear(imageView);
            imageView.setImageBitmap(BitmapFactory.decodeResource(
                    getResources(), R.drawable.icon_addpic_unfocused));
            imageNameText.setVisibility(View.GONE);

            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    ImgSelConfig config = new ImgSelConfig.Builder(PublishedActivity.this, loader)
                            // 是否多选, 默认true
                            .multiSelect(true)
                            // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                            .rememberSelected(false)
                            // “确定”按钮背景色
                            .btnBgColor(Color.TRANSPARENT)
                            // “确定”按钮文字颜色
                            .btnTextColor(Color.WHITE)
                            // 使用沉浸式状态栏
                            //.statusBarColor(Color.parseColor("#3F51B5"))
                            // 返回图标ResId
                            .backResId(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                            // 标题
                            .title("图片")
                            // 标题文字颜色
                            .titleColor(Color.WHITE)
                            // TitleBar背景色
                            .titleBgColor(Color.RED)
                            // 裁剪大小。needCrop为true的时候配置
                            //.cropSize(1, 1, 200, 200)
                            //.needCrop(true)
                            // 第一个是否显示相机，默认true
                            .needCamera(true)
                            // 最大选择图片数量，默认9
                            .maxNum(curSelMaxCount())
                            .build();
                    ImgSelActivity.startActivity(PublishedActivity.this, config, PICKER_REQUEST_CODE);
                }
            });
        }

        public class ViewHolder {
            public ImageView image;
            public TextView et_image_name;
        }
    }

    private int curSelMaxCount() {
        if (chuanPics == null)
            return maxImageCount;
        return maxImageCount - chuanPics.size();
    }

    // 自定义图片加载器
    private ImageLoader loader = new ImageLoader() {
        public void displayImage(Context context, String path, ImageView imageView) {
            // TODO 在这边可以自定义图片加载库来加载ImageView，例如Glide、Picasso、ImageLoader等
            Glide.with(context).load(path).into(imageView);
            //com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage("file://" + path, imageView);//
        }
    };

    private String getImageTmpName() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString() + ".jpg";
        return uniqueId;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == PICKER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            final List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (pathList != null && pathList.size() > 0) {
                //后台线程完成
                rotateLoading.start();
                waitingBkView.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    public void run() {
                        final ArrayList<Pics> tmpList = new ArrayList<Pics>();
                        for (String path : pathList) {
                            //压缩处理
                            String targetPath = FileUtils.SDPATH + "uploadtmp/" + getImageTmpName();
                            targetPath = compressImage(path, targetPath, 70);
                            if (targetPath == null || targetPath.length() <= 0)
                                targetPath = path;

                            Pics p = new Pics();
                            p.setAlt("图片");
                            p.setImagepath(targetPath);
                            tmpList.add(p);
                        }

                        PublishedActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                if (chuanPics == null)
                                    chuanPics = new ArrayList<Pics>();
                                chuanPics.addAll(tmpList);
                                //刷新页面
                                Message msg = new Message();
                                msg.what = 1;
                                handler.sendMessage(msg);

                                waitingBkView.setVisibility(View.INVISIBLE);
                                rotateLoading.stop();
                            }
                        });

                    }
                }).start();
            }
        }

        if (requestCode == MODIFY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<Pics> list = (List<Pics>) data.getSerializableExtra("pics");
            this.chuanPics = list;

            //刷新页面
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);
        }
    }

    protected void onDestroy() {
        UploadUtil.getInstance().setOnUploadProcessListener(null);
        super.onDestroy();
    }

    //图片压缩
    public static String compressImage(String filePath, String targetPath, int quality) {
        Bitmap bm = getSmallBitmap(filePath);//获取一定尺寸的图片
        int degree = readPictureDegree(filePath);//获取相片拍摄角度
        if (degree != 0) {//旋转照片角度，防止头像横着显示
            bm = rotateBitmap(bm, degree);
        }
        File outputFile = new File(targetPath);
        try {
            if (!outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
            } else {
                outputFile.delete();
            }
            FileOutputStream out = new FileOutputStream(outputFile);
            bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
        } catch (Exception e) {
        }
        return outputFile.getPath();
    }

    /**
     * 根据路径获得图片信息并按比例压缩，返回bitmap
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只解析图片边沿，获取宽高
        BitmapFactory.decodeFile(filePath, options);
        // 计算缩放比
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // 完整解析图片返回bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
