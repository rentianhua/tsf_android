package com.dumu.housego.framgent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bumptech.glide.Glide;
import com.dumu.housego.ErShouFangDetailsActivity;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.UpLoadHtsfz;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ApplyShouChuPresenter;
import com.dumu.housego.presenter.IApplyShouChuPresenter;
import com.dumu.housego.presenter.IUpLoadHTSFZPresenter;
import com.dumu.housego.presenter.IWeiTuoPresenter;
import com.dumu.housego.presenter.UpLoadHTSFZPresenter;
import com.dumu.housego.presenter.WeiTuoPresenter;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.utils.UploadUtil;
import com.dumu.housego.utils.UploadUtil.OnUploadProcessListener;
import com.dumu.housego.view.IApplyShouChuView;
import com.dumu.housego.view.IUpLoadHTSFZView;
import com.dumu.housego.view.IWeiTuoErShouView;
import com.example.testpic.ImageGridActivity;
import com.example.testpic.PublishedActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import util.FileUtils;

public class ErShouWeiTuoFragment extends Fragment implements IWeiTuoErShouView, IUpLoadHTSFZView, OnUploadProcessListener, IApplyShouChuView {
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

    private ListView lv_ershou_weituo;
    private IWeiTuoPresenter presenter;
    private PopupWindow careatpop;
    private LinearLayout llPopup;
    private RelativeLayout parent;
    private List<ErShouFangDetails> submitershous;
    private IUpLoadHTSFZPresenter htsfzPresenter;
    private IApplyShouChuPresenter applyPresenter;
    private String EDit;
    private int POSI;
    private UserInfo userinfo;
    private String userid;
    private SubmitErshouListAdapter adapter;
    private String table;
    private String username;
    private Boolean isFirst = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weituo_ershou, null);
        InitView(view);
        presenter = new WeiTuoPresenter(this);
        applyPresenter = new ApplyShouChuPresenter(this);
        htsfzPresenter = new UpLoadHTSFZPresenter(this);
        creatPopupwindows();
        setListener();
        return view;
    }

    @Override
    public void onResume() {
        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
        userid = userinfo.getUserid();
        username = userinfo.getUsername();
        table = "ershou";
//		if(isFirst){
        presenter.WeiTuo(userid, table);
//			isFirst=false;
//		}

        super.onResume();
    }


    private void InitView(View view) {
        lv_ershou_weituo = (ListView) view.findViewById(R.id.lv_ershou_weituo);
    }

    private void setListener() {
        lv_ershou_weituo.setOnItemClickListener(new OnItemClickListener() {
            private String Id;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ErShouFangDetailsActivity.class);
                Id = submitershous.get(position).getId();
                String catid = submitershous.get(position).getCatid();
                String posid = submitershous.get(position).getPosid();
                i.putExtra("id", Id);
                i.putExtra("catid", catid);
                i.putExtra("posid", posid);
                startActivity(i);

            }
        });

    }

    @Override
    public void weituoSuccess(List<ErShouFangDetails> ershous) {
        this.submitershous = ershous;

        adapter = new SubmitErshouListAdapter(submitershous, getActivity());
        lv_ershou_weituo.setAdapter(adapter);

    }

    @Override
    public void weituofail(String info) {
        // TODO Auto-generated method stub

    }


    public class SubmitErshouListAdapter extends BaseAdapter {
        private List<ErShouFangDetails> submitershous;
        private Context context;
        private LayoutInflater Inflater;

        public SubmitErshouListAdapter(List<ErShouFangDetails> submitershous, Context context) {
            super();
            this.submitershous = submitershous;
            this.context = context;
            this.Inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return submitershous == null ? 0 : submitershous.size();
        }

        @Override
        public ErShouFangDetails getItem(int position) {

            return submitershous.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = Inflater.inflate(R.layout.item_submit_ershou_list2, null);
                holder = new ViewHolder();

                holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_submit_ershou_list);

                holder.tvChenHu = (TextView) convertView.findViewById(R.id.tv_submit_ershou_list_chenhu);
                holder.tvDetail = (TextView) convertView.findViewById(R.id.tv_submit_ershou_list_detail);
                holder.ivEdit = (ImageView) convertView.findViewById(R.id.iv_submit_ershou_list_edit);
                holder.tvLouceng = (TextView) convertView.findViewById(R.id.tv_submit_ershou_list_louceng);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_submit_ershou_list_title);
                holder.tvUpLoadht = (TextView) convertView.findViewById(R.id.tv_submit_ershou_list_hetong);
                holder.tvUpLoadsfz = (TextView) convertView.findViewById(R.id.tv_submit_ershou_list_sfz);
                holder.tvZongjia = (TextView) convertView.findViewById(R.id.tv_submit_ershou_list_zongjia);
                holder.tvShenqingshouchu = (TextView) convertView.findViewById(R.id.tv_shenqingshouchu);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            try {

                final ErShouFangDetails n = getItem(position);

                if (n.getThumb() != null && !n.getThumb().equals("")) {

                    if (n.getThumb().startsWith("http")) {
                        Glide.with(getActivity()).load(n.getThumb()).into(holder.ivPic);
                    } else {
                        String url = UrlFactory.TSFURL + n.getThumb();
                        Glide.with(getActivity()).load(url).into(holder.ivPic);
                    }

                } else {
                    //Bitmap bm=BitmapFactory.decodeResource(getResources(), R.drawable.img_no_url);
                    holder.ivPic.setImageResource(R.drawable.img_no_url);
                }

                String type = n.getTypeid();

                String pubtype = n.getPub_type();

                if (pubtype.equals("1")) {
                    holder.tvChenHu.setText(n.getUsername() + "/自售");
                } else if (pubtype.equals("2")) {
                    holder.tvChenHu.setText(n.getUsername() + "/委托给经纪人");
                } else {
                    holder.tvChenHu.setText(n.getUsername() + "/委托给平台");
                }

                holder.tvDetail.setText(n.getCityname() + " / " + n.getAreaname() + " / " + n.getXiaoquname());
                holder.tvLouceng.setText(n.getCeng() + "(共" + n.getZongceng() + "层)");
                holder.tvTitle.setText(n.getTitle() + "");
                holder.tvZongjia.setText(n.getZongjia() + "");


                if (n.getStatus().equals("1")) {
                    holder.tvShenqingshouchu.setVisibility(View.GONE);
                } else {
                    holder.tvShenqingshouchu.setVisibility(View.VISIBLE);
                }

//				if(!n.getLock().equals("0")){
//					holder.ivDelete.setBackgroundColor(getResources().getColor(android.R.color.white));
//					holder.ivEdit.setBackgroundColor(getResources().getColor(android.R.color.white));
//					holder.ivDelete.setClickable(false);
//					holder.ivDelete.setVisibility(View.GONE);
//					holder.ivEdit.setVisibility(View.GONE);
//					holder.ivEdit.setClickable(false);
//				}else{
//					holder.ivDelete.setVisibility(View.VISIBLE);
//					holder.ivEdit.setVisibility(View.VISIBLE);
//				}


                if (n.getZaishou().equals("1")) {
                    if (n.getApply_state().equals("1")) {
                        holder.tvShenqingshouchu.setText("已售出申请中");
                        holder.tvShenqingshouchu.setTextColor(getResources().getColor(R.color.huisetextcolor));
                        holder.tvShenqingshouchu.setClickable(false);
                        holder.tvShenqingshouchu.setEnabled(false);
                    } else {
                        holder.tvShenqingshouchu.setClickable(true);
                        holder.tvShenqingshouchu.setEnabled(true);
                        holder.tvShenqingshouchu.setText("申请已售出");
                        holder.tvShenqingshouchu.setTextColor(getResources().getColor(R.color.conusertextcolor));
                        holder.tvShenqingshouchu.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                } else {
                    holder.tvShenqingshouchu.setText("已售出");
                    holder.tvShenqingshouchu.setTextColor(getResources().getColor(R.color.white));
                    holder.tvShenqingshouchu.setBackgroundColor(getResources().getColor(R.color.bisque));
                    holder.tvShenqingshouchu.setClickable(false);
                    holder.tvShenqingshouchu.setEnabled(false);
                }


                if (n.getIdcard() != null && !n.getIdcard().equals("") && !n.getIdcard().equals("a:0:{}")) {
                    holder.tvUpLoadsfz.setEnabled(false);
                    holder.tvUpLoadsfz.setClickable(false);
                    holder.tvUpLoadsfz.setText("身份证已传");
                    holder.tvUpLoadsfz.setTextColor(getResources().getColor(R.color.white));
                    holder.tvUpLoadsfz.setBackgroundColor(getResources().getColor(R.color.bisque));
                } else {
                    holder.tvUpLoadsfz.setEnabled(true);
                    holder.tvUpLoadsfz.setClickable(true);
                    holder.tvUpLoadsfz.setText("上传身份证");
                    holder.tvUpLoadsfz.setTextColor(getResources().getColor(R.color.conusertextcolor));
                    holder.tvUpLoadsfz.setBackgroundColor(getResources().getColor(R.color.white));
                }

                if (n.getContract() != null && !n.getContract().equals("") && !n.getContract().equals("a:0:{}")) {
                    holder.tvUpLoadht.setEnabled(false);
                    holder.tvUpLoadht.setClickable(false);
                    holder.tvUpLoadht.setText("合同已传");
                    holder.tvUpLoadht.setTextColor(getResources().getColor(R.color.white));
                    holder.tvUpLoadht.setBackgroundColor(getResources().getColor(R.color.bisque));
                } else {
                    holder.tvUpLoadht.setEnabled(true);
                    holder.tvUpLoadht.setClickable(true);
                    holder.tvUpLoadht.setText("上传合同");
                    holder.tvUpLoadht.setTextColor(getResources().getColor(R.color.conusertextcolor));
                    holder.tvUpLoadht.setBackgroundColor(getResources().getColor(R.color.white));
                }


                holder.tvShenqingshouchu.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        applyPresenter.ApplyShouchu(n.getId(), "ershou", userinfo.getUsername());
//						holder.tvShenqingshouchu.setText("已售出申请中");

                    }
                });

                holder.ivEdit.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //fragment之间传递数值
                        FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();

                        ATershouSubmitFragment fragment = new ATershouSubmitFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("key", n);
                        bundle.putString("weituoedites", "1");
                        fragment.setArguments(bundle);
                        trans.replace(R.id.fl_agent_fragment, fragment);
                        trans.addToBackStack(null);
                        trans.commit();
                        //trans.commitAllowingStateLoss();
                    }
                });


                holder.tvUpLoadht.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (n.getContract() != null && !n.getContract().equals("") && !n.getContract().equals("a:0:{}")) {
                            MyToastShowCenter.CenterToast(getContext(), "合同已上传");
                        } else {
                            ErShouWeiTuoFragment.this.POSI = position;
                            EDit = "ht";
//							llPopup.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.activity_slide_enter_bottom));
//							careatpop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                            ImageGridActivity.pics.clear();
                            Intent i = new Intent(getActivity(), PublishedActivity.class);
                            i.putExtra("TAG", "5");
                            i.putExtra("ID", n.getId());
                            i.putExtra("username", username);
                            startActivityForResult(i, PublishedActivity.UploadWTHT);

                        }


                    }
                });

                holder.tvUpLoadsfz.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (n.getIdcard() != null && !n.getIdcard().equals("") && !n.getIdcard().equals("a:0:{}")) {
                            MyToastShowCenter.CenterToast(getContext(), "身份证已上传");
                        } else {
                            ErShouWeiTuoFragment.this.POSI = position;
                            EDit = "sfz";
//							llPopup.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.activity_slide_enter_bottom));
//							careatpop.showAtLocation(v, Gravity.BOTTOM, 0, 0);

                            ImageGridActivity.pics.clear();
                            Intent i = new Intent(getActivity(), PublishedActivity.class);
                            i.putExtra("TAG", "6");
                            i.putExtra("ID", n.getId());
                            i.putExtra("username", username);
                            startActivityForResult(i, PublishedActivity.UploadWTSFZ);
                        }


                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

            return convertView;

        }

        class ViewHolder {
            TextView tvTitle;
            TextView tvDetail;
            TextView tvLouceng;
            TextView tvChenHu;
            TextView tvZongjia;
            TextView tvShenqingshouchu;
            TextView tvUpLoadht;
            TextView tvUpLoadsfz;
            ImageView ivEdit;
            ImageView ivPic;
        }
    }


    /**
     * 创建对话框
     */
    protected void creatPopupwindows() {

        careatpop = new PopupWindow(getActivity());
        View popupView = getActivity().getLayoutInflater().inflate(R.layout.item_pop_camra, null);
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
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请读写权限
                    ActivityCompat.requestPermissions(getActivity(),
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
            Toast.makeText(getActivity(), "无法保存照片，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
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
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
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

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnIntent) {
        //回调处理结果
        if (resultCode != Activity.RESULT_OK)
            return;

        switch (requestCode) {

            case REQUEST_CODE_GETIMAGE_BYCAMERA:

//				startActionCrop(origUri);// 拍照后裁剪


                Bitmap bmp = bitmapfromFile(origUri.getPath());
                String picPath = origUri.getPath();
                if (bmp != null) {

                    File f = null;
                    FileOutputStream out = null;
                    try {
                        if (!FileUtils.isFileExist("")) {
                            File tempf = FileUtils.createSDDir("");
                        }
                        f = new File(picPath);
                        if (f.exists()) {
                            f.delete();
                        }
                        out = new FileOutputStream(f);
                        bmp.compress(Bitmap.CompressFormat.JPEG, 70, out);
                        out.flush();
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Log.e("ffffff", "ffffffffffffffff=" + f.toString());

                    String filekey = "img";
                    UploadUtil uploadutil = UploadUtil.getInstance();
                    uploadutil.setOnUploadProcessListener(this);

                    Map<String, String> m = new HashMap<String, String>();
                    m.put("userid", userinfo.getUserid());
                    m.put("catid", "6");
                    m.put("module", "content");
                    String RequestURL = UrlFactory.PostupLoadPic();
                    uploadutil.uploadFile(f, filekey, RequestURL, m);
                }
                break;
            case REQUEST_CODE_GETIMAGE_BYCROP:

//				startActionCrop(imageReturnIntent.getData());// 选图后裁剪
                String thePath = getAbsolutePathFromNoStandardUri(imageReturnIntent.getData());
                if (thePath.isEmpty()) {
                    thePath = getAbsoluteImagePath(imageReturnIntent.getData());
                }

                Log.e("qqqqqqqqqqqqq	", "thePath" + thePath);
                Bitmap bmp1 = bitmapfromFile(thePath);
                String picPath1 = thePath;
                ;
                if (bmp1 != null) {

                    File f = null;
                    FileOutputStream out = null;
                    try {
                        if (!FileUtils.isFileExist("")) {
                            File tempf = FileUtils.createSDDir("");
                        }
                        f = new File(picPath1);
                        if (f.exists()) {
                            f.delete();
                        }
                        out = new FileOutputStream(f);
                        bmp1.compress(Bitmap.CompressFormat.JPEG, 70, out);
                        out.flush();
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Log.e("ffffff", "ffffffffffffffff=" + f.toString());

                    String filekey = "img";
                    UploadUtil uploadutil = UploadUtil.getInstance();
                    uploadutil.setOnUploadProcessListener(this);

                    Map<String, String> m = new HashMap<String, String>();
                    m.put("userid", userinfo.getUserid());
                    m.put("catid", "6");
                    m.put("module", "content");
                    String RequestURL = UrlFactory.PostupLoadPic();
                    uploadutil.uploadFile(f, filekey, RequestURL, m);

                }


                break;
            case REQUEST_CODE_GETIMAGE_BYSDCARD:
                //发送上传头像的消息
//			handler.sendEmptyMessage(TO_UPLOAD_FILE);
                break;

            case PublishedActivity.UploadWTHT:
                this.pics = (List<Pics>) imageReturnIntent.getSerializableExtra("uploadpics");
                Log.i("pics============", "pics==============" + pics.toString());
                uploadTHpic(pics);
                break;
        }

    }

    private void uploadTHpic(List<Pics> pics) {
        UpLoadHtsfz h = new UpLoadHtsfz();
        ErShouFangDetails e = submitershous.get(POSI);
        h.setId(e.getId());
        h.setUsername(username);

        //list集合转json
        JSONArray json = new JSONArray();
        try {
            for (Pics a : pics) {
                JSONObject jo = new JSONObject();
                jo.put("url", a.getUrl());
                jo.put("alt", a.getAlt());
                json.put(jo);
            }
        } catch (Exception e2) {
        }

        h.setContract(json.toString());
        h.setIdcard("");

        htsfzPresenter.HTSFZ(h);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TO_UPLOAD_FILE:
//				toUploadFile();
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
                        Toast.makeText(getActivity(), "上传失败", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 3:
                    Pics p = pics.get(0);

                    UpLoadHtsfz h = new UpLoadHtsfz();
                    ErShouFangDetails e = submitershous.get(POSI);
                    h.setId(e.getId());
                    h.setUsername(username);
                    if (EDit.equals("ht")) {
                        h.setContract(p.getUrl());
                        h.setIdcard("");
                    } else {
                        h.setContract("");
                        h.setIdcard(p.getUrl());
                    }
                    Log.e("hhhhhhhhhhhhhhhh", "h=" + h);
                    htsfzPresenter.HTSFZ(h);

                    break;


                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


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
            Toast.makeText(getActivity(), "无法保存照片，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
            ;
            return null;
        }

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());

        String thePath = getAbsolutePathFromNoStandardUri(uri);
        if (thePath.isEmpty()) {
            thePath = getAbsoluteImagePath(uri);
        }

        //获取图片路径的扩展名
        String ext = thePath.substring(thePath.lastIndexOf('.') + 1);
        ext = ext.isEmpty() ? "jpg" : ext;

        // 照片命名
        String cropFileName = "crop_" + timeStamp + "." + ext;
        protraitFile = new File(portraitPath, cropFileName);
        cropUri = Uri.fromFile(protraitFile);
        Log.e("protraitFile", "6:56------protraitFile=" + protraitFile.toString());
        Log.e("cropUri", "6:56------cropUri=" + cropUri.getPath().toString());
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
        Cursor cursor = getActivity().managedQuery(uri, proj, // Which columns to
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

        Log.e("messagemessagemessage", "message=" + message);
        try {
            Pics p = new Pics();
            JSONObject j = new JSONObject(message);
            String url = j.getString("url");
            String o = j.getString("picname");
            p.setUrl(url);
            p.setAlt(o);
            pics.add(p);
            Message msg = new Message();
            msg.what = 3;
            handler.sendMessage(msg);


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

    @Override
    public void HTSFZ(String info) {
        MyToastShowCenter.CenterToast(getActivity(), info);
        presenter.WeiTuo(userid, table);
        adapter.notifyDataSetChanged();

    }

    public static Bitmap bitmapfromFile(String photoPath) {
        Bitmap bitmap = null;
        File f = new File(photoPath);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void applyShouchu(String info) {
        MyToastShowCenter.CenterToast(getActivity(), info);
        presenter.WeiTuo(userid, table);
        adapter.notifyDataSetChanged();
    }


}
