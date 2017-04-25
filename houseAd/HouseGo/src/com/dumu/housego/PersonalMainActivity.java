package com.dumu.housego;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

import util.FileUtils;
import android.Manifest;
import android.annotation.TargetApi;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.LoginUserInfoModel;
import com.dumu.housego.presenter.ChangeHeadPhotoPresenter;
import com.dumu.housego.presenter.ChangeUserInfoPresenter;
import com.dumu.housego.presenter.IChangeHeadPhotoPresenter;
import com.dumu.housego.presenter.IChangeUserInfoPresenter;
import com.dumu.housego.util.CircleImageView;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.utils.UploadUtil;
import com.dumu.housego.utils.UploadUtil.OnUploadProcessListener;
import com.dumu.housego.view.IChangeHeadPhotoView;
import com.dumu.housego.view.IChangeUserInfoView;

public class PersonalMainActivity extends BaseActivity
		implements OnUploadProcessListener, IChangeUserInfoView, IChangeHeadPhotoView {
	Button bt;
	private static RequestQueue mSingleQueue;
	private static String TAG = "test";
	private String SDCARD_MNT = "/mnt/sdcard";
	private String SDCARD = "/sdcard";
	private Uri cropUri;
	private File protraitFile;

	public static final int TAKE_PHOTO = 1;

	public static final int CHOOSE_PHOTO = 2;

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
	protected static final int UPLOAD_FILE_DONE = 5; //

	private Uri origUri;

	private String SuccessImageUrl;

	// private static final int TAKE_PHOTO_REQUEST_CODE = 1;

	private LoginUserInfoModel infomodel = new LoginUserInfoModel();
	private TextView tv_person_fenjihao;
	private IChangeUserInfoPresenter userinfopresenter;
	private Button btn_changefenjihao_shenqing, btn_changefenjihao_jiebang, btn_changepassword_submit;
	private EditText et_changrealname, et_changegerenjieshao, et_change_currentpassword, et_change_Newpassword,
			et_change_repassword;
	private LinearLayout llPersonalBack, ll_changerealname_back, ll_changefenjihao_back, ll_changegerenjieshao_back,
			ll_changepassword_back;
	private RelativeLayout rl_person_sex, rlMyTouxiang, rl_person_realname, rl_person_fenjihao, rl_person_gerenjieshao,
			rl_personal_changepassword, rl_personal_phone;
	private TextView tvPersonalPhone, tv_changerealname_save, tv_changegerenjieshao_save, tv_person_sex,
			tv_fenjihao_show, tvfenjihao_show_1, tv_gerenshuoming;
	private UserInfo userinfo;
	private CircleImageView ivPersonalPhoto;
	private Bitmap head;// ͷ��Bitmap
	// private File file=new File("\\sdcard\\HouseGo\\");
	// private static String path=File.pathSeparator;//sd·��
	private IChangeHeadPhotoPresenter headpresenter;
	private TextView tv_personal_viptype;
	private MyReboundScrollView scrollview;

	private LinearLayout llchangerealname;
	private LinearLayout llchangfenjiahao;
	private LinearLayout llchangegerenjieshao;
	private LinearLayout llchangepassword;

	private LinearLayout llfenjihaojiebang;
	private LinearLayout llfenjihaoshenqing;

	Handler handler = new Handler() {
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
				Glide.with(getApplicationContext()).load(SuccessImageUrl).into(ivPersonalPhoto);

				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	private PopupWindow pop;
	private LinearLayout ll_popup;
	private LinearLayout ll_cancle;
	private View parentView;
	private PopupWindow popTouXiang;
	private LinearLayout ll_cancle_touxiang;
	private LinearLayout ll_popup_touxiang;
	protected TextView tv_realname;
	private Uri imagwUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		parentView = getLayoutInflater().inflate(R.layout.activity_personal_main, null);
		setContentView(parentView);
		FontHelper.injectFont(findViewById(android.R.id.content));
		x.view().inject(this);
		setViews();
		showPopWindow();
		showTouxaingWindow();
		userinfopresenter = new ChangeUserInfoPresenter(this);

		setListeners();

		headpresenter = new ChangeHeadPhotoPresenter(this);

	}

	protected void toUploadFile() {
		Log.e("sssssssss", "sssssss="+cropUri.getPath());
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

		String fileKey = "__avatar1";
		UploadUtil uploadUtil = UploadUtil.getInstance();
		uploadUtil.setOnUploadProcessListener(this); // 设置监听器监听上传状态
		// 定义一个Map集合，封装请求服务端时需要的参数
		Map<String, String> m = new HashMap<String, String>();
		// 在这里根据服务端需要的参数自己来定
		
		m.put("userid", userinfo.getUserid());
		String RequestURL = UrlFactory.PostChangeHeadPhotoUrl();

		uploadUtil.uploadFile(f, fileKey, RequestURL, m);
		if (cropUri.getPath() != null) {
			options = new BitmapFactory.Options();
			options.inJustDecodeBounds = false;
			options.inPreferredConfig = Config.RGB_565;
			options.inDither = true;
			Bitmap bitmap = BitmapFactory.decodeFile(cropUri.getPath());
			ivPersonalPhoto.setImageBitmap(bitmap);
		} else {
			Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
		}

	}

	// 获取保存头像地址的Uri
	private Uri getUploadTempFile(Uri uri) {
		String portraitPath = "";
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// 保存图像的文件夹路径
			portraitPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/image/Portrait";
			File saveDir = new File(portraitPath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
		} else {
			Toast.makeText(PersonalMainActivity.this, "无法保存照片，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
			;
			return null;
		}

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		String thePath = getAbsolutePathFromNoStandardUri(uri);
		if (thePath.isEmpty()) {
			thePath = getAbsoluteImagePath(uri);
		}

		// 获取图片路径的扩展名
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
			filePath = Environment.getExternalStorageDirectory().getPath() + File.separator
					+ mUriString.substring(pre1.length());
		} else if (mUriString.startsWith(pre2)) {
			filePath = Environment.getExternalStorageDirectory().getPath() + File.separator
					+ mUriString.substring(pre2.length());
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
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = PersonalMainActivity.this.managedQuery(uri, proj, // Which
																			// columns
																			// to
				// return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)

		if (cursor != null) {
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			if (cursor.getCount() > 0 && cursor.moveToFirst()) {
				imagePath = cursor.getString(column_index);
			}
		}
		return imagePath;
	}

	private void showTouxaingWindow() {
		popTouXiang = new PopupWindow(PersonalMainActivity.this);

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
					imageUri = FileProvider.getUriForFile(PersonalMainActivity.this,
							"com.example.cameraalbumtest.fileprovider", outputImage);
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

				if (ContextCompat.checkSelfPermission(PersonalMainActivity.this,
						Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(PersonalMainActivity.this,
							new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1);
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
		Intent intent;
		if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent();
			intent.setAction(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
			startActivityForResult(Intent.createChooser(intent, "选择图片"), CHOOSE_PHOTO);
		} else {
			intent = new Intent(Intent.ACTION_PICK, Images.Media.EXTERNAL_CONTENT_URI);
			intent.setType("image/*");
			startActivityForResult(Intent.createChooser(intent, "选择图片"), CHOOSE_PHOTO);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TAKE_PHOTO:
			if (resultCode == RESULT_OK) {

				startActionCrop(imageUri);

			}
			break;
		case CHOOSE_PHOTO:
			if (resultCode == RESULT_OK) {
				startActionCrop(data.getData());// 选图后裁剪
			}
			break;

		case REQUEST_CODE_GETIMAGE_BYSDCARD:
			handler.sendEmptyMessage(TO_UPLOAD_FILE);
			break;
		default:
			break;
		}
	}

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
		startActivityForResult(intent, REQUEST_CODE_GETIMAGE_BYSDCARD);
	}

	@TargetApi(19)
	private void handleImageOnKitKat(Intent data) {
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
				Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(docId));
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
	}

	private void handleImageBeforeKitKat(Intent data) {
		Uri uri = data.getData();
		String imagePath = getImagePath(uri, null);
		displayImage(imagePath);
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

		String fileKey = "__avatar1";
		UploadUtil uploadUtil = UploadUtil.getInstance();
		uploadUtil.setOnUploadProcessListener(this); // 设置监听器监听上传状态
		// 定义一个Map集合，封装请求服务端时需要的参数
		Map<String, String> m = new HashMap<String, String>();
		// 在这里根据服务端需要的参数自己来定

		// headpresenter.ChangeHead("149", imagePath);

		m.put("userid", userinfo.getUserid());
		// m.put("__avatar1", f);
		// m.put("module", "__avatar1");
		String RequestURL = UrlFactory.PostChangeHeadPhotoUrl();

		uploadUtil.uploadFile(f, fileKey, RequestURL, m);
		// if(protraitFile.exists()){
		// //参数三：请求的url，这里我用到了公司的url
		// uploadUtil.uploadFile(protraitFile.getAbsolutePath()
		// ,fileKey,UrlFactory.PostupLoadPic(),m);
		// }

		if (imagePath != null) {
			options = new BitmapFactory.Options();
			options.inJustDecodeBounds = false;
			options.inPreferredConfig = Config.RGB_565;
			options.inDither = true;
			Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
			ivPersonalPhoto.setImageBitmap(bitmap);
		} else {
			Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
		}

	}

	private void showPopWindow() {
		pop = new PopupWindow(PersonalMainActivity.this);

		View view = getLayoutInflater().inflate(R.layout.item_popupwindows_1, null);

		ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
		ll_cancle = (LinearLayout) view.findViewById(R.id.ll_cancle);
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);

		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_btn1);
		Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_btn2);
		Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);

		bt1.setText("男");
		bt2.setText("女");
		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// tv_agent_sex.setText("男");

				String userid = userinfo.getUserid();
				String sex = "1";
				userinfopresenter.ChangeSex(userid, sex);
				tv_person_sex.setText("男");
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// tv_agent_sex.setText("女");
				String userid = userinfo.getUserid();
				String sex = "2";
				userinfopresenter.ChangeSex(userid, sex);
				tv_person_sex.setText("女");
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});

	}

	@Override
	protected void onResume() {
		userinfo = HouseGoApp.getLoginInfo(getApplicationContext());
		if (userinfo == null) {
			startActivity(new Intent(this, MainActivity.class));
		} else {
			String url = userinfo.getUserpic();
			Glide.with(getApplicationContext()).load(url).into(ivPersonalPhoto);
			tvPersonalPhone.setText(userinfo.getUsername() + "");
			tv_fenjihao_show.setText(userinfo.getUsername());
			tvfenjihao_show_1.setText(userinfo.getVtel());

			et_changegerenjieshao.setText(userinfo.getAbout());
			tv_gerenshuoming.setText(userinfo.getAbout());
			if (userinfo.getModelid().equals("35")) {
				tv_personal_viptype.setText("普通会员");
			} else {
				tv_personal_viptype.setText("经纪人");
			}
			et_changrealname.setText(userinfo.getRealname() + "");
			if (userinfo.getSex().equals("0")) {
				tv_person_sex.setText("未知");
			} else if (userinfo.getSex().equals("1")) {
				tv_person_sex.setText("男");
			} else {
				tv_person_sex.setText("女");
			}
			if(userinfo.getZhuanjie().equals("0")){
				tv_person_fenjihao.setText(userinfo.getUsername());
			}else{
				tv_person_fenjihao.setText(userinfo.getVtel());
			}
			
			
			tv_realname.setText(userinfo.getRealname());
		}
		super.onResume();
	}

	private void setListeners() {
		llPersonalBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PersonalMainActivity.this.finish();

			}
		});

		rl_person_realname.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				scrollview.setVisibility(View.GONE);
				llchangerealname.setVisibility(View.VISIBLE);
			}
		});

		rl_person_fenjihao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				scrollview.setVisibility(View.GONE);

				if (userinfo.getZhuanjie().equals("0")) {
					llfenjihaoshenqing.setVisibility(View.VISIBLE);
				} else {
					llfenjihaojiebang.setVisibility(View.VISIBLE);
				}
				llchangfenjiahao.setVisibility(View.VISIBLE);
			}
		});

		rl_person_gerenjieshao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				scrollview.setVisibility(View.GONE);
				llchangegerenjieshao.setVisibility(View.VISIBLE);
			}
		});

		rl_personal_changepassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				scrollview.setVisibility(View.GONE);
				llchangepassword.setVisibility(View.VISIBLE);
			}
		});

		rl_person_sex.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Animation anim = AnimationUtils.loadAnimation(PersonalMainActivity.this, R.anim.activity_translate_in);

				ll_popup.setAnimation(anim);
				ll_cancle.setAnimation(anim);
				pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

			}
		});

		tv_changerealname_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String userid = userinfo.getUserid();
				String realname = et_changrealname.getText() + "";
				String modelid = userinfo.getModelid();
				userinfopresenter.ChangeRealName(userid, realname, modelid);
				tv_realname.setText(realname);

				handler.postDelayed(new Runnable() {
					public void run() {

						llchangerealname.setVisibility(View.GONE);
						scrollview.setVisibility(View.VISIBLE);
						onResume();
					}
				}, 1000);

			}
		});

		ll_changerealname_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				llchangerealname.setVisibility(View.GONE);
				scrollview.setVisibility(View.VISIBLE);
			}
		});

		ll_changepassword_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				et_change_currentpassword.setText("");
				et_change_Newpassword.setText("");
				et_change_repassword.setText("");
				llchangepassword.setVisibility(View.GONE);
				scrollview.setVisibility(View.VISIBLE);
			}
		});

		ll_changefenjihao_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				llfenjihaojiebang.setVisibility(View.GONE);
				llfenjihaoshenqing.setVisibility(View.GONE);
				llchangfenjiahao.setVisibility(View.GONE);
				scrollview.setVisibility(View.VISIBLE);
			}
		});

		ll_changegerenjieshao_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				llchangegerenjieshao.setVisibility(View.GONE);

				scrollview.setVisibility(View.VISIBLE);

			}
		});

		btn_changefenjihao_shenqing.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String tel = userinfo.getUsername();
				String userid = userinfo.getUserid();
				userinfopresenter.ChangeSQfenjihao(tel, userid);
				handler.postDelayed(new Runnable() {
					public void run() {
						llfenjihaoshenqing.setVisibility(View.GONE);
						llchangfenjiahao.setVisibility(View.GONE);
						scrollview.setVisibility(View.VISIBLE);
						onResume();
					}
				}, 1000);

			}
		});

		btn_changefenjihao_jiebang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String tel = userinfo.getUsername();
				String userid = userinfo.getUserid();
				userinfopresenter.ChangeJBfenjihao(tel, userid);
				
				handler.postDelayed(new Runnable() {
					public void run() {

						llfenjihaojiebang.setVisibility(View.GONE);
						llchangfenjiahao.setVisibility(View.GONE);
						scrollview.setVisibility(View.VISIBLE);
						onResume();
					}
				}, 1000);

			}
		});

		tv_changegerenjieshao_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				String about = et_changegerenjieshao.getText().toString();
				String userid = userinfo.getUserid();
				userinfopresenter.ChangeGeRenJieshao(userid, about);
				tv_gerenshuoming.setText(about);
				handler.postDelayed(new Runnable() {
					public void run() {
						llchangegerenjieshao.setVisibility(View.GONE);
						scrollview.setVisibility(View.VISIBLE);
					}
				}, 1000);
			}
		});

		btn_changepassword_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String username = userinfo.getUsername();
				String userid = userinfo.getUserid();
				String oldpwd = et_change_currentpassword.getText().toString();
				String pwd1 = et_change_Newpassword.getText().toString();
				String pwd2 = et_change_repassword.getText().toString();

				userinfopresenter.ChangePassword(username, userid, oldpwd, pwd1, pwd2);
				et_change_currentpassword.setText("");
				et_change_Newpassword.setText("");
				et_change_repassword.setText("");
				handler.postDelayed(new Runnable() {
					public void run() {
						llchangepassword.setVisibility(View.GONE);
						scrollview.setVisibility(View.VISIBLE);
					}
				}, 1000);

			}
		});

		ivPersonalPhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Animation anim = AnimationUtils.loadAnimation(PersonalMainActivity.this, R.anim.activity_translate_in);

				ll_popup_touxiang.setAnimation(anim);
				ll_cancle_touxiang.setAnimation(anim);
				popTouXiang.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

			}
		});

	}

	private void setViews() {

		tv_gerenshuoming = (TextView) findViewById(R.id.tv_gerenshuoming);

		btn_changepassword_submit = (Button) findViewById(R.id.btn_changepassword_submit);

		et_change_currentpassword = (EditText) findViewById(R.id.et_change_currentpassword);
		et_change_Newpassword = (EditText) findViewById(R.id.et_change_Newpassword);
		et_change_repassword = (EditText) findViewById(R.id.et_change_repassword);

		et_changegerenjieshao = (EditText) findViewById(R.id.et_changegerenjieshao);
		tv_changegerenjieshao_save = (TextView) findViewById(R.id.tv_changegerenjieshao_save);
		btn_changefenjihao_shenqing = (Button) findViewById(R.id.btn_changefenjihao_shenqing);
		btn_changefenjihao_jiebang = (Button) findViewById(R.id.btn_changefenjihao_jiebang);
		tvfenjihao_show_1 = (TextView) findViewById(R.id.tv_fenjihao_show_1);
		tv_fenjihao_show = (TextView) findViewById(R.id.tv_fenjihao_show);
		tv_person_sex = (TextView) findViewById(R.id.tv_person_sex);
		et_changrealname = (EditText) findViewById(R.id.et_changrealname);

		scrollview = (MyReboundScrollView) findViewById(R.id.scrollview_changeshow);

		ll_changerealname_back = (LinearLayout) findViewById(R.id.ll_changerealname_back);
		ll_changegerenjieshao_back = (LinearLayout) findViewById(R.id.ll_changegerenjieshao_back);
		ll_changepassword_back = (LinearLayout) findViewById(R.id.ll_changepassword_back);
		ll_changefenjihao_back = (LinearLayout) findViewById(R.id.ll_changefenjihao_back);

		tv_changerealname_save = (TextView) findViewById(R.id.tv_changerealname_save);

		rl_personal_phone = (RelativeLayout) findViewById(R.id.rl_personal_phone);

		rl_person_fenjihao = (RelativeLayout) findViewById(R.id.rl_person_fenjihao);

		rl_person_gerenjieshao = (RelativeLayout) findViewById(R.id.rl_person_gerenjieshao);

		rl_person_sex = (RelativeLayout) findViewById(R.id.rl_person_sex);
		rl_personal_changepassword = (RelativeLayout) findViewById(R.id.rl_personal_changepassword);

		llPersonalBack = (LinearLayout) findViewById(R.id.ll_personal_back);

		tv_personal_viptype = (TextView) findViewById(R.id.tv_personal_viptype);

		rlMyTouxiang = (RelativeLayout) findViewById(R.id.rl_MyTouxiang);
		rl_person_realname = (RelativeLayout) findViewById(R.id.rl_person_realname);
		tvPersonalPhone = (TextView) findViewById(R.id.tv_personal_phone);
		tv_person_fenjihao = (TextView) findViewById(R.id.tv_person_fenjihao);
		ivPersonalPhoto = (CircleImageView) findViewById(R.id.iv_personal_Photo);

		// 各个分栏
		llchangerealname = (LinearLayout) findViewById(R.id.rl_person_changerealname);
		llchangfenjiahao = (LinearLayout) findViewById(R.id.ll__person_changfenjiahao);
		llchangegerenjieshao = (LinearLayout) findViewById(R.id.ll_person_gerenjieshao);
		llchangepassword = (LinearLayout) findViewById(R.id.ll_person_changepassword);
		llfenjihaojiebang = (LinearLayout) findViewById(R.id.fenjihao_jiebang);
		llfenjihaoshenqing = (LinearLayout) findViewById(R.id.fenjihao_shenqing);
		tv_realname = (TextView) findViewById(R.id.tv_realname);
	}

//	// 监听back键按下事件
//	@Override
//	public void onBackPressed() {
//		// 屏蔽back键盘
//		// super.onBackPressed();
//	}

	@Override
	public void changeInfo(final String info) {
		MyToastShowCenter.CenterToast(getApplicationContext(), info);

		handler.postDelayed(new Runnable() {
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

	@Override
	public void changeHeadFail(String errorMessage) {
		MyToastShowCenter.CenterToast(getApplicationContext(), "头像更换失败！");

	}

	@Override
	public void changeHeadSuccess(String picUrl) {
		MyToastShowCenter.CenterToast(getApplicationContext(), "头像更换成功！" + picUrl);
		Glide.with(getApplicationContext()).load(picUrl).into(ivPersonalPhoto);
	}

	@Override
	public void onUploadDone(int responseCode, String message) {
		try {
			JSONObject j = new JSONObject(message);
			String url = j.getString("avatarUrls");

			this.SuccessImageUrl = url;

			Message msg = new Message();
			msg.obj = url;
			msg.arg1 = 3;
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

}
