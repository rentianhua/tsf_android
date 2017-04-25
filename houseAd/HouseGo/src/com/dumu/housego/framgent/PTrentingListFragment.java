package com.dumu.housego.framgent;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.RentingDetailActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.RentingDetail;
import com.dumu.housego.entity.SubmitErshouList;
import com.dumu.housego.entity.UpLoadHtsfz;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.ATershouListDeletePresenter;
import com.dumu.housego.presenter.ApplyShouChuPresenter;
import com.dumu.housego.presenter.IATershouListDeletePresenter;
import com.dumu.housego.presenter.IApplyShouChuPresenter;
import com.dumu.housego.presenter.ISubmitRentingListpresenter;
import com.dumu.housego.presenter.IUpLoadHTSFZchuzuPresenter;
import com.dumu.housego.presenter.SubmitRentinglistpresenter;
import com.dumu.housego.presenter.UpLoadHTSFZchuzuPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.utils.UploadUtil;
import com.dumu.housego.utils.UploadUtil.OnUploadProcessListener;
import com.dumu.housego.view.IATershouListDeleteView;
import com.dumu.housego.view.IApplyShouChuView;
import com.dumu.housego.view.ISubmitRentingListView;
import com.dumu.housego.view.IUpLoadHTSFZchuzuView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

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
import android.view.animation.Animation;
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

public class PTrentingListFragment extends Fragment implements IApplyShouChuView, IUpLoadHTSFZchuzuView, OnUploadProcessListener, ISubmitRentingListView, IATershouListDeleteView {
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


    private LinearLayout ll_back_putongrentinglist;
    private TextView tv_putongrentinglist_submit;
    private ListView lv_submit_renting_list;
    private List<SubmitErshouList> submitershous;
    private ISubmitRentingListpresenter presenter;
    private SubmitRentingListAdapter adapter;
    private UserInfo userinfo;
    List<RentingDetail> rentingdetails;
    private int POSI;
    private IATershouListDeletePresenter deletepresenter;
    private String username;
    private String userid;
    private String table;
    private PopupWindow pop;
    private LinearLayout ll_popup_delete;
    private PopupWindow careatpop;
    private LinearLayout llPopup;
    private RelativeLayout parent;
    private IUpLoadHTSFZchuzuPresenter htsfzPresenter;
    private String EDit;
    private IApplyShouChuPresenter applyPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pt_renting_list, null);
        userinfo = HouseGoApp.getContext().getCurrentUserInfo();
        username = userinfo.getUsername();
        userid = userinfo.getUserid();
        table = "chuzu";
        presenter = new SubmitRentinglistpresenter(this);
        deletepresenter = new ATershouListDeletePresenter(this);
        htsfzPresenter = new UpLoadHTSFZchuzuPresenter(this);
        applyPresenter = new ApplyShouChuPresenter(this);
        initView(view);
        creatPopupwindows();
        PopDelete();
        setListener();
        FontHelper.injectFont(view);
        presenter.SubmitRentingList(username, userid, table);
        return view;
    }

    private void initView(View view) {
        ll_back_putongrentinglist = (LinearLayout) view.findViewById(R.id.ll_back_putongrentinglist);
        tv_putongrentinglist_submit = (TextView) view.findViewById(R.id.tv_putongrentinglist_submit);
        lv_submit_renting_list = (ListView) view.findViewById(R.id.lv_submit_renting_list);
    }

    private void setListener() {
        lv_submit_renting_list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (rentingdetails.get(position).getStatus().equals("1")) {
                    MyToastShowCenter.CenterToast(getActivity(), "房源审核中");
                } else {
                    Intent i = new Intent(getActivity(), RentingDetailActivity.class);
                    String Id = rentingdetails.get(position).getId();
                    String catid = rentingdetails.get(position).getCatid();
                    String posid = rentingdetails.get(position).getPosid();

                    i.putExtra("id", Id);
                    i.putExtra("catid", catid);
                    i.putExtra("posid", posid);
                    startActivity(i);
                }


            }
        });

        ll_back_putongrentinglist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

            }
        });

        tv_putongrentinglist_submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PTrentingSumbitFragment();
                FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.rl_container, fragment);
                trans.commitAllowingStateLoss();
            }
        });

    }

    private void PopDelete() {

        pop = new PopupWindow(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.item_popupwindows_delete, null);

        ll_popup_delete = (LinearLayout) view.findViewById(R.id.ll_popup_delete);
        pop.setWidth(LayoutParams.MATCH_PARENT);
        pop.setHeight(LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_delete);

        Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_sure);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        TextView tv = (TextView) view.findViewById(R.id.item_popupwindows_title);
        tv.setText("是否删除该出租房");

        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup_delete.clearAnimation();
            }
        });

        bt1.setOnClickListener(new OnClickListener() {


            public void onClick(View v) {
                String lock = rentingdetails.get(POSI).getLock();

                String ID = rentingdetails.get(POSI).getId();

                deletepresenter.DeleteershouList(username, userid, table, ID);

//				if (lock.equals("0")) {
//					ershoudetails.remove(position);
//					adapter.notifyDataSetChanged();
//				}

                pop.dismiss();
                ll_popup_delete.clearAnimation();
            }
        });

        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup_delete.clearAnimation();
            }
        });


    }


    public class SubmitRentingListAdapter extends BaseAdapter {
        private List<RentingDetail> submitershous;
        private Context context;
        private LayoutInflater Inflater;

        public SubmitRentingListAdapter(List<RentingDetail> submitershous, Context context) {
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
        public RentingDetail getItem(int position) {

            return submitershous.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = Inflater.inflate(R.layout.item_submit_renting_list, null);
                holder = new ViewHolder();

                holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_submit_renting_list);

                holder.tvChenHu = (TextView) convertView.findViewById(R.id.tv_submit_renting_list_chuzuType);
                holder.tvDetail = (TextView) convertView.findViewById(R.id.tv_submit_renting_list_detail);
                holder.ivDelete = (ImageView) convertView.findViewById(R.id.iv_submit_renting_list_delete);
                holder.ivEdit = (ImageView) convertView.findViewById(R.id.iv_submit_renting_list_edit);
                holder.tvLouceng = (TextView) convertView.findViewById(R.id.tv_submit_renting_list_area);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_submit_renting_list_title);
                holder.tvZongjia = (TextView) convertView.findViewById(R.id.tv_submit_renting_list_zongjia);
                holder.rl_renting = (LinearLayout) convertView.findViewById(R.id.rl_renting);
                holder.tvShenqingshouchu = (TextView) convertView.findViewById(R.id.tv_shenqingshouchu);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            try {

                final RentingDetail n = getItem(position);


                if (n.getZaizu().equals("1")) {
                    if (n.getApply_state().equals("1")) {
                        holder.tvShenqingshouchu.setText("已出租申请中");
                        holder.tvShenqingshouchu.setTextColor(getResources().getColor(R.color.huisetextcolor));
                        holder.tvShenqingshouchu.setClickable(false);
                        holder.tvShenqingshouchu.setEnabled(false);
                    } else {
                        holder.tvShenqingshouchu.setClickable(true);
                        holder.tvShenqingshouchu.setEnabled(true);
                        holder.tvShenqingshouchu.setText("申请已出租");
                        holder.tvShenqingshouchu.setTextColor(getResources().getColor(R.color.conusertextcolor));
                        holder.tvShenqingshouchu.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                } else {
                    holder.tvShenqingshouchu.setText("已出租");
                    holder.tvShenqingshouchu.setTextColor(getResources().getColor(R.color.white));
                    holder.tvShenqingshouchu.setBackgroundColor(getResources().getColor(R.color.bisque));
                    holder.tvShenqingshouchu.setClickable(false);
                    holder.tvShenqingshouchu.setEnabled(false);
                }

                if (n.getStatus().equals("1")) {
                    holder.tvShenqingshouchu.setVisibility(View.GONE);
                } else {
                    holder.tvShenqingshouchu.setVisibility(View.VISIBLE);
                }


                if (n.getLock().equals("1")) {
//				holder.rl_renting.setVisibility(View.GONE);
                    holder.ivDelete.setBackgroundColor(getResources().getColor(android.R.color.white));
                    holder.ivEdit.setBackgroundColor(getResources().getColor(android.R.color.white));
                    holder.ivDelete.setClickable(false);
                    holder.ivDelete.setVisibility(View.GONE);
                    holder.ivEdit.setVisibility(View.GONE);
                    holder.ivEdit.setClickable(false);
                } else {
//				holder.rl_renting.setVisibility(View.VISIBLE);
                    holder.ivDelete.setVisibility(View.VISIBLE);
                    holder.ivEdit.setVisibility(View.VISIBLE);
                }


                if (n.getThumb() != null && !n.getThumb().equals("")) {

                    if (n.getThumb().startsWith("http")) {
                        Glide.with(getActivity()).load(n.getThumb()).into(holder.ivPic);
                    } else {
                        String url = UrlFactory.TSFURL + n.getThumb();
                        Glide.with(getActivity()).load(url).into(holder.ivPic);
                    }
                } else {
//				Glide.with(getActivity()).load(url).into(holder.ivPic);
                    //Bitmap bm=BitmapFactory.decodeResource(getResources(), R.drawable.img_no_url);
                    Glide.clear(holder.ivPic);
                    holder.ivPic.setImageResource(R.drawable.img_no_url);
                }

//			DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//					.bitmapConfig(Bitmap.Config.RGB_565).build();
//			ImageLoader.getInstance().displayImage(url, holder.ivPic, options);


                String pubtype = n.getPub_type();
                if (pubtype.equals("1")) {
                    holder.tvChenHu.setText("直接出租");
                    holder.ivEdit.setVisibility(convertView.VISIBLE);

                } else if (pubtype.equals("2")) {
                    holder.tvChenHu.setText("委托给经纪人");
                    holder.ivEdit.setVisibility(convertView.GONE);
                } else {
                    holder.tvChenHu.setText("委托给平台");
                    holder.ivEdit.setVisibility(convertView.GONE);
                }

                holder.tvDetail.setText(n.getCityname() + " / " + n.getAreaname() + " / " + n.getXiaoquname());
                holder.tvLouceng.setText(n.getShi() + "室" + n.getTing() + "厅 / " + n.getMianji() + "平米");
                holder.tvTitle.setText(n.getTitle() + "");
                holder.tvZongjia.setText(n.getZujin() + "");


                holder.tvShenqingshouchu.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        applyPresenter.ApplyShouchu(n.getId(), "chuzu", userinfo.getUsername());
//					holder.tvShenqingshouchu.setText("已售出申请中");
                    }
                });


                holder.ivEdit.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //fragment之间传递数值
                        FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();

                        PTrentingSumbitFragment fragment = new PTrentingSumbitFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("ptrentings", n);
                        fragment.setArguments(bundle);
                        trans.replace(R.id.rl_container, fragment);
                        trans.addToBackStack(null);
                        trans.commit();
                        //trans.commitAllowingStateLoss();
                    }
                });
                holder.ivDelete.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        PTrentingListFragment.this.POSI = position;
                        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.activity_translate_in);

                        ll_popup_delete.setAnimation(anim);
                        pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
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
            TextView tvShenqingshouchu;
            TextView tvChenHu;
            TextView tvZongjia;
            ImageView ivEdit;
            ImageView ivDelete;
            ImageView ivPic;
            LinearLayout rl_renting;

        }
    }


    @Override
    public void SubmitListError(String info) {
        //MyToastShowCenter.CenterToast(getActivity(), info);

    }

    @Override
    public void SubmitListSuccess(List<RentingDetail> rentingdetails) {
        this.rentingdetails = rentingdetails;
        adapter = new SubmitRentingListAdapter(rentingdetails, getActivity());
        lv_submit_renting_list.setAdapter(adapter);

    }

    @Override
    public void ATershouListDelete(String info) {
        MyToastShowCenter.CenterToast(getActivity(), info);
        presenter.SubmitRentingList(username, userid, table);
//		if(info.equals("删除成功")){
//			this.rentingdetails.remove(POSI);
//			adapter.notifyDataSetChanged();
//		}
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnIntent) {
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
                break;
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TO_UPLOAD_FILE:
                    toUploadFile();
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
//		intent.putExtra("crop", "true");
//		intent.putExtra("aspectX", 1);// 裁剪框比例
//		intent.putExtra("aspectY", 1);
//		intent.putExtra("outputX", 200);// 输出图片大小
//		intent.putExtra("outputY", 200);
//		intent.putExtra("scale", true);// 去黑边
//		intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        startActivityForResult(intent,
                REQUEST_CODE_GETIMAGE_BYSDCARD);
    }

    /**
     * 上传头像到服务器
     */
    protected void toUploadFile() {
        String fileKey = "img";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
        //定义一个Map集合，封装请求服务端时需要的参数
        Map<String, String> m = new HashMap<String, String>();
        //在这里根据服务端需要的参数自己来定

        m.put("userid", userid);
        m.put("catid", catid);
        m.put("module", "content");

        if (protraitFile.exists()) {
            //参数三：请求的url，这里我用到了公司的url
            uploadUtil.uploadFile(protraitFile.getAbsolutePath(), fileKey, UrlFactory.PostupLoadPic(), m);
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

        try {
            Pics p = new Pics();
            JSONObject j = new JSONObject(message);
            String url = j.getString("url");
            String o = j.getString("picname");
            p.setUrl(url);
            p.setAlt(o);
            this.pics.add(p);

            UpLoadHtsfz h = new UpLoadHtsfz();

            h.setId(rentingdetails.get(POSI).getId());
            h.setUsername(username);
            if (EDit.equals("ht")) {
                h.setContract(p.getUrl());
                h.setIdcard("");
            } else {
                h.setContract("");
                h.setIdcard(p.getUrl());
            }
            htsfzPresenter.HTSFZ(h);


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

    }

    @Override
    public void applyShouchu(String info) {
        MyToastShowCenter.CenterToast(getActivity(), info);
        rentingdetails.clear();
        presenter.SubmitRentingList(username, userid, table);
        adapter.notifyDataSetChanged();

    }


}
