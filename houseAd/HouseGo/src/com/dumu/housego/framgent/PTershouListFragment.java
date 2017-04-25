package com.dumu.housego.framgent;

import java.io.File;
import java.io.FileInputStream;
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

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.dumu.housego.ErShouFangDetailsActivity;
import com.dumu.housego.R;
import com.dumu.housego.adapter.SubmitErshouListAdapter;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.SubmitErshouList;
import com.dumu.housego.entity.UpLoadHtsfz;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.framgent.ATershouListFragment.SubmitErshouListAdapter.ViewHolder;
import com.dumu.housego.presenter.ATershouListDeletePresenter;
import com.dumu.housego.presenter.ApplyShouChuPresenter;
import com.dumu.housego.presenter.IATershouListDeletePresenter;
import com.dumu.housego.presenter.IApplyShouChuPresenter;
import com.dumu.housego.presenter.ISubmitErShouListpresenter;
import com.dumu.housego.presenter.IUpLoadHTSFZPresenter;
import com.dumu.housego.presenter.SubmitErShoulistpresenter;
import com.dumu.housego.presenter.UpLoadHTSFZPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.utils.UploadUtil;
import com.dumu.housego.utils.UploadUtil.OnUploadProcessListener;
import com.dumu.housego.view.IATershouListDeleteView;
import com.dumu.housego.view.IApplyShouChuView;
import com.dumu.housego.view.ISubmitErShouListView;
import com.dumu.housego.view.IUpLoadHTSFZView;
import com.example.testpic.ImageGridActivity;
import com.example.testpic.PublishedActivity;
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
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

import util.FileUtils;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PTershouListFragment extends Fragment implements IUpLoadHTSFZView, OnUploadProcessListener, IApplyShouChuView, ISubmitErShouListView, IATershouListDeleteView {
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


    private ListView lvSubmitErshouList;
    private SubmitErshouListAdapter adapter;
    private List<ErShouFangDetails> submitershous;
    private ISubmitErShouListpresenter presenter;
    private IApplyShouChuPresenter applyPresenter;
    private UserInfo userinfo;
    private IATershouListDeletePresenter deletepresenter;
    private TextView tv_putongershoulist_submit;
    private LinearLayout ll_back_putongershoulist;
    private String Id;
    private PopupWindow pop;
    private LinearLayout ll_popup_delete;
    private int POSI;
    private String username;
    private String userid;
    private String table;
    private PopupWindow careatpop;
    private LinearLayout llPopup;
    private RelativeLayout parent;
    private IUpLoadHTSFZPresenter htsfzPresenter;
    private String EDit;
    private int adapterPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pt_ershou_list, null);

        userinfo = HouseGoApp.getContext().getLoginInfo(getActivity());
        username = userinfo.getUsername();
        userid = userinfo.getUserid();
        table = "ershou";
        deletepresenter = new ATershouListDeletePresenter(this);
        presenter = new SubmitErShoulistpresenter(this);
        applyPresenter = new ApplyShouChuPresenter(this);
        htsfzPresenter = new UpLoadHTSFZPresenter(this);
        FontHelper.injectFont(view);
        initView(view);
        creatPopupwindows();
        PopDelete();
        setListener();

        return view;
    }

    @Override
    public void onResume() {
        presenter.SubmitErShouList(username, userid, table);
        super.onResume();
    }


    private void setListener() {
        ll_back_putongershoulist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        tv_putongershoulist_submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragment = new PTershouSumbitFragment();
                FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.rl_container, fragment);
                trans.commitAllowingStateLoss();

            }
        });

        lvSubmitErshouList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ErShouFangDetailsActivity.class);
                if (submitershous.get(position).getStatus().equals("1")) {
                    MyToastShowCenter.CenterToast(getActivity(), "房源审核中");
                } else {
                    Id = submitershous.get(position).getId();
                    String catid = submitershous.get(position).getCatid();
                    String posid = submitershous.get(position).getPosid();
                    i.putExtra("id", Id);
                    i.putExtra("catid", catid);
                    i.putExtra("posid", posid);
                    startActivity(i);
                }
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
        tv.setText("是否删除该二手房");

        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup_delete.clearAnimation();
            }
        });

        bt1.setOnClickListener(new OnClickListener() {


            public void onClick(View v) {
                String lock = submitershous.get(POSI).getLock();

                String ID = submitershous.get(POSI).getId();

                deletepresenter.DeleteershouList(username, userid, table, ID);

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
                convertView = Inflater.inflate(R.layout.item_submit_ershou_list, null);
                holder = new ViewHolder();

                holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_submit_ershou_list);

                holder.tvChenHu = (TextView) convertView.findViewById(R.id.tv_submit_ershou_list_chenhu);
                holder.tvDetail = (TextView) convertView.findViewById(R.id.tv_submit_ershou_list_detail);
                holder.ivEdit = (ImageView) convertView.findViewById(R.id.iv_submit_ershou_list_edit);
                holder.ivDelete = (ImageView) convertView.findViewById(R.id.iv_submit_ershou_list_delete);
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
                    Glide.clear(holder.ivPic);
                    holder.ivPic.setImageResource(R.drawable.img_no_url);
                }

//				String url = UrlFactory.TSFURL+ n.getThumb();
//				Glide.with(getActivity()).load(url).into(holder.ivPic);

//				DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
//						.bitmapConfig(Bitmap.Config.RGB_565).build();
//				ImageLoader.getInstance().displayImage(url, holder.ivPic, options);


                String type = n.getTypeid();

                String pubtype = n.getPub_type();

                if (pubtype.equals("1")) {
                    holder.tvChenHu.setText(n.getUsername() + "/自售");
                    holder.ivEdit.setVisibility(convertView.VISIBLE);
                } else if (pubtype.equals("2")) {
                    holder.ivEdit.setVisibility(convertView.GONE);
                    holder.tvChenHu.setText(n.getUsername() + "/委托给经纪人");

                } else {
                    holder.ivEdit.setVisibility(convertView.GONE);
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

                if (!n.getLock().equals("0")) {
                    holder.ivDelete.setBackgroundColor(getResources().getColor(android.R.color.white));
                    holder.ivEdit.setBackgroundColor(getResources().getColor(android.R.color.white));
                    holder.ivDelete.setClickable(false);
                    holder.ivDelete.setVisibility(View.GONE);
                    holder.ivEdit.setVisibility(View.GONE);
                    holder.ivEdit.setClickable(false);
                } else {
                    holder.ivDelete.setVisibility(View.VISIBLE);
                    holder.ivEdit.setVisibility(View.VISIBLE);
                }


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

                        PTershouSumbitFragment fragment = new PTershouSumbitFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("ptershouqq", n);
                        fragment.setArguments(bundle);
                        trans.replace(R.id.rl_container, fragment);
                        //trans.addToBackStack(null);
                        //trans.commit();
                        trans.commitAllowingStateLoss();
                    }
                });

                holder.ivDelete.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        PTershouListFragment.this.POSI = position;
                        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.activity_translate_in);
                        ll_popup_delete.setAnimation(anim);
                        pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                    }
                });

                holder.tvUpLoadht.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (n.getContract() != null && !n.getContract().equals("") && !n.getContract().equals("a:0:{}")) {
                            MyToastShowCenter.CenterToast(getContext(), "合同已上传");
                        } else {
                            PTershouListFragment.this.POSI = position;
                            EDit = "ht";
                            ImageGridActivity.pics.clear();
                            Intent i = new Intent(getActivity(), PublishedActivity.class);
                            i.putExtra("TAG", "5");
                            i.putExtra("ID", n.getId());
                            i.putExtra("username", username);
                            startActivityForResult(i, PublishedActivity.UploadWTHT);

//							llPopup.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.activity_slide_enter_bottom));
//							careatpop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                        }


                    }
                });

                holder.tvUpLoadsfz.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (n.getIdcard() != null && !n.getIdcard().equals("") && !n.getIdcard().equals("a:0:{}")) {
                            MyToastShowCenter.CenterToast(getContext(), "身份证已上传");
                        } else {
                            PTershouListFragment.this.POSI = position;
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
            ImageView ivDelete;
            ImageView ivPic;
        }
    }


    private void initView(View view) {
        lvSubmitErshouList = (ListView) view.findViewById(R.id.lv_submit_ershou_list1);
        ll_back_putongershoulist = (LinearLayout) view.findViewById(R.id.ll_back_putongershoulist);
        tv_putongershoulist_submit = (TextView) view.findViewById(R.id.tv_putongershoulist_submit);
    }

    @Override
    public void SubmitListSuccess(List<ErShouFangDetails> submitershous) {
        this.submitershous = submitershous;

        adapter = new SubmitErshouListAdapter(submitershous, getActivity());
        lvSubmitErshouList.setAdapter(adapter);

    }

    @Override
    public void SubmitListError(String info) {
        MyToastShowCenter.CenterToast(getActivity(), info);

    }

    @Override
    public void ATershouListDelete(String info) {
        MyToastShowCenter.CenterToast(getActivity(), info);
//		if(info.equals("删除成功")){
//			this.submitershous.remove(POSI);
//			adapter.notifyDataSetChanged();
//		}
        presenter.SubmitErShouList(username, userid, table);
    }

    @Override
    public void applyShouchu(String info) {
        MyToastShowCenter.CenterToast(getActivity(), info);
        submitershous.clear();
        presenter.SubmitErShouList(username, userid, table);
        adapter.notifyDataSetChanged();
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
                    htsfzPresenter.HTSFZ(h);

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

        intent.putExtra("aspectX", 3);

        intent.putExtra("aspectY", 4);
        intent.putExtra("outputX", 120);
        intent.putExtra("outputY", 160);

        intent.putExtra("scale", true);

        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true); // no face detection

        startActivityForResult(intent,
                REQUEST_CODE_GETIMAGE_BYSDCARD);
    }

    /**
     * 上传头像到服务器
     */
    protected void toUploadFile() {
//		Bitmap bmp=BitmapFactory.decodeFile(cropUri.getPath());
//		Bitmap bmp=bitmapfromFile(cropUri.getPath());
//		String picPath=cropUri.getPath();
//		
//		
//	
//	if (bmp != null) {
//			
//			File f = null ;
//			FileOutputStream out=null;
//			try {
//				if (!FileUtils.isFileExist("")) {
//					File tempf = FileUtils.createSDDir("");
//				}
//				 f = new File(picPath); 
//				if (f.exists()) {
//					f.delete();
//				}
//				out = new FileOutputStream(f);
//				bmp.compress(Bitmap.CompressFormat.JPEG, 90, out);
//				out.flush();
//				out.close();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			Log.e("ffffff", "ffffffffffffffff="+f.toString());
//			
//			String filekey="img";
//			UploadUtil uploadutil=UploadUtil.getInstance();
//			uploadutil.setOnUploadProcessListener(this);
//
//			Map<String, String >m=new HashMap<String, String>();
//			m.put("userid", userid);
//			m.put("catid", catid);
//			m.put("module", "content");
//			String RequestURL=UrlFactory.PostupLoadPic();
//			uploadutil.uploadFile(f, filekey, RequestURL, m);
//		
//	}

        String fileKey = "img";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
        //定义一个Map集合，封装请求服务端时需要的参数
        Map<String, String> m = new HashMap<String, String>();
        //在这里根据服务端需要的参数自己来定

        m.put("userid", userid);
        m.put("catid", "6");
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

        try {
            Pics p = new Pics();
            JSONObject j = new JSONObject(message);
            String url = j.getString("url");
            String o = j.getString("picname");
            p.setUrl(url);
            p.setAlt(o);
            this.pics.add(p);

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
        presenter.SubmitErShouList(username, userid, table);
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


}
