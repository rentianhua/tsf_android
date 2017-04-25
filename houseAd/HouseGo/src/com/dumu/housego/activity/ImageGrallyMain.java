package com.dumu.housego.activity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import util.Bimp;
import util.FileUtils;
import util.ImageItem;
import util.PublicWay;
import util.Res;
import activity.AlbumActivity;
import activity.GalleryActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dumu.housego.AgentPersonalActivity;
import com.dumu.housego.PuTongMyGuanZhuActivity;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.UpLoadPic;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.HouseUpLoadPicPresenter;
import com.dumu.housego.presenter.IHouseUpLoadPicPresenter;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.utils.UploadUtil;
import com.dumu.housego.utils.UploadUtil.OnUploadProcessListener;
import com.dumu.housego.view.IHouseUploadPicView;

public class ImageGrallyMain extends BaseActivity implements OnUploadProcessListener, IHouseUploadPicView{

	private GridView noScrollgridview;
	private GridAdapter adapter;
	private View parentView;
	private PopupWindow pop = null;
	private LinearLayout ll_popup;
	public static Bitmap bimap ;
	public static final int ATERSHOUPIC =20;
	public static final int ATRentingPIC =21;
	
	public static final int PTERSHOUPIC =22;
	public static final int PTRentingPIC =23;

	
	private ImageView uploadpic;
	private TextView activity_selectimg_send;
	private IHouseUpLoadPicPresenter presenter;
	private String userid;
	private String catid;
	private UserInfo userinfo;
	List<Pics>pics=new ArrayList<Pics>();
	private int TAG;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Res.init(this);
		bimap = BitmapFactory.decodeResource(
				getResources(),
				R.drawable.icon_addpic_unfocused);
		PublicWay.activityList.add(this);
		parentView = getLayoutInflater().inflate(R.layout.activity_selectimg, null);
		setContentView(parentView);
		presenter=new HouseUpLoadPicPresenter(this);
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		userid=userinfo.
				getUserid();
		catid="6";
		Init();
		initView();
		setlistener();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onResume() {
		TAG=getIntent().getIntExtra("TAG", 1);
		if(getIntent().getSerializableExtra("pics")!=null){
			pics=(List<Pics>) getIntent().getSerializableExtra("pics");
			showPics();
			
		}
		
		
		super.onResume();
	}
	
	public Bitmap getBitmap(String url) {  
        Bitmap bm = null;  
        try {  
            URL iconUrl = new URL(url);  
            URLConnection conn = iconUrl.openConnection();  
            HttpURLConnection http = (HttpURLConnection) conn;  
              
            int length = http.getContentLength();  
              
            conn.connect();  
            // 获得图像的字符流  
            InputStream is = conn.getInputStream();  
            BufferedInputStream bis = new BufferedInputStream(is, length);  
            bm = BitmapFactory.decodeStream(bis);  
            bis.close();  
            is.close();// 关闭流  
        }  
        catch (Exception e) {  
            e.printStackTrace();  
        }  
        return bm;  
    } 
	

	private void showPics() {
		
		for (Pics p  : pics) {
			Bitmap bm = (Bitmap)getBitmap(p.getUrl());
			String filename=p.getAlt();
//			FileUtils.saveBitmap(bm, filename);
			ImageItem takePhoto = new ImageItem();
			takePhoto.setBitmap(bm);
			takePhoto.setImagePath(FileUtils.SDPATH+filename + ".JPEG");
			Bimp.tempSelectBitmap.add(takePhoto);
		}
		
		
		
		
	
		
		
	}

	private void setlistener() {
		uploadpic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ImageGrallyMain.this.finish();
				
			}
		});
		
		activity_selectimg_send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String RequestURL=UrlFactory.PostupLoadPic();
				Map<String, String>p=new HashMap<String, String>();
				
				for(int i=0;i<Bimp.tempSelectBitmap.size();i++){
					String path=Bimp.tempSelectBitmap.get(i).imagePath;
//						String name=Bimp.tempSelectBitmap.get(i).thumbnailPath;
						Bitmap bm=Bimp.tempSelectBitmap.get(i).getBitmap();
						UploadPic(path, bm, userid, catid, RequestURL);
				}
				
				
//				Intent i=new Intent();
//				i.putExtra("pics", (Serializable)pics);
//				startActivityForResult(i, ATERSHOUPIC);
				
				
				
				
			}
		});
		
	}

	private void initView() {
		uploadpic=(ImageView) findViewById(R.id.uploadpic);
		activity_selectimg_send=(TextView) findViewById(R.id.activity_selectimg_send);
	}

	public void Init() {
		
		pop = new PopupWindow(ImageGrallyMain.this);
		
		View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

		ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
		
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);
		
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		Button bt1 = (Button) view
				.findViewById(R.id.item_popupwindows_camera);
		Button bt2 = (Button) view
				.findViewById(R.id.item_popupwindows_Photo);
		Button bt3 = (Button) view
				.findViewById(R.id.item_popupwindows_cancel);
		parent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});
		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				photo();
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ImageGrallyMain.this,
						AlbumActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
				
				pop.dismiss();
				ll_popup.clearAnimation();
				finish();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});
		
		noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);	
		noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new GridAdapter(this);
		adapter.update();
		noScrollgridview.setAdapter(adapter);
		noScrollgridview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == Bimp.tempSelectBitmap.size()) {
					Log.i("ddddddd", "----------");
					ll_popup.startAnimation(AnimationUtils.loadAnimation(ImageGrallyMain.this,R.anim.activity_translate_in));
					pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
				} else {
					Intent intent = new Intent(ImageGrallyMain.this,
							GalleryActivity.class);
					intent.putExtra("position", "1");
					intent.putExtra("ID", arg2);
					startActivity(intent);
				}
			}
		});

	}

	@SuppressLint("HandlerLeak")
	public class GridAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private int selectedPosition = -1;
		private boolean shape;

		public boolean isShape() {
			return shape;
		}

		public void setShape(boolean shape) {
			this.shape = shape;
		}

		public GridAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public void update() {
			loading();
		}

		public int getCount() {
			if(Bimp.tempSelectBitmap.size() == 9){
				return 9;
			}
			return (Bimp.tempSelectBitmap.size() + 1);
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int arg0) {
			return 0;
		}

		public void setSelectedPosition(int position) {
			selectedPosition = position;
		}

		public int getSelectedPosition() {
			return selectedPosition;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_published_grida,
						parent, false);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.item_grida_image);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (position ==Bimp.tempSelectBitmap.size()) {
				holder.image.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), R.drawable.icon_addpic_unfocused));
				if (position == 9) {
					holder.image.setVisibility(View.GONE);
				}
			} else {
				holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
			}

			return convertView;
		}

		public class ViewHolder {
			public ImageView image;
		}

		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					adapter.notifyDataSetChanged();
					break;
				}
				super.handleMessage(msg);
			}
		};

		public void loading() {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						if (Bimp.max == Bimp.tempSelectBitmap.size()) {
							Message message = new Message();
							message.what = 1;
							handler.sendMessage(message);
							break;
						} else {
							Bimp.max += 1;
							Message message = new Message();
							message.what = 1;
							handler.sendMessage(message);
						}
					}
				}
			}).start();
		}
	}

	public String getString(String s) {
		String path = null;
		if (s == null)
			return "";
		for (int i = s.length() - 1; i > 0; i++) {
			s.charAt(i);
		}
		return path;
	}

	protected void onRestart() {
		adapter.update();
		super.onRestart();
	}

	private static final int TAKE_PICTURE = 0x000001;

	public void photo() {
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(openCameraIntent, TAKE_PICTURE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TAKE_PICTURE:
			if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {
				
				String fileName = String.valueOf(System.currentTimeMillis());
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				FileUtils.saveBitmap(bm, fileName);
				
				ImageItem takePhoto = new ImageItem();
				takePhoto.setBitmap(bm);
				takePhoto.setImagePath(FileUtils.SDPATH+fileName + ".JPEG");
				
				String	RequestURL=UrlFactory.PostupLoadPic();
				Log.e("zzzzzzzzzzzzzzzzzzzzz", "zzzzzz="+FileUtils.SDPATH+fileName + ".JPEG");
				Bimp.tempSelectBitmap.add(takePhoto);
				
				Pics p=new Pics();
				
				
				
			}
			break;
		}
	}

	public  void UploadPic(String fileName, Bitmap bm,String userid,String catid,String RequestURL) {
		File f = null ;
		FileOutputStream out=null;
		try {
			if (!FileUtils.isFileExist("")) {
				File tempf = FileUtils.createSDDir("");
			}
			 f = new File(fileName); 
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
		
		Log.e("ffffff", "ffffffffffffffff="+f.toString());;
		
		String filekey="img";
		UploadUtil uploadutil=UploadUtil.getInstance();
		uploadutil.setOnUploadProcessListener(this);

		Map<String, String >m=new HashMap<String, String>();
		m.put("userid", userid);
		m.put("catid", catid);
		m.put("module", "content");
		
		uploadutil.uploadFile(f, filekey, RequestURL, m);
	}
	
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			for(int i=0;i<PublicWay.activityList.size();i++){
//				if (null != PublicWay.activityList.get(i)) {
//					PublicWay.activityList.get(i).finish();
//				}
//			}
//			System.exit(0);
//		}
//		return true;
//	}

	@Override
	public void uploadpicsuccess(List<UpLoadPic> pics) {
		
	}

	@Override
	public void uploadfail(String info){
		
	}

	@Override
	public void onUploadDone(int responseCode, String message) {
		Pics p=new Pics();
		try {
			JSONObject j=new JSONObject(message);
			String url=j.getString("url");
			String o=j.getString("picname");
			p.setUrl(url);
			p.setAlt(o);
			
			this.pics.add(p);
			Log.e("pics", "pics="+pics.toString());
			
			if(pics.size()==Bimp.tempSelectBitmap.size()){
				//返回代码
				switch (TAG) {
				case 1:
					Intent i1=new Intent(this, PuTongMyGuanZhuActivity.class);
					i1.putExtra("pics", (Serializable)pics);
					setResult(PTERSHOUPIC, i1);
					finish();
					break;
				case 2:
					Intent i2=new Intent(this, PuTongMyGuanZhuActivity.class);
					i2.putExtra("pics", (Serializable)pics);
					setResult(PTRentingPIC, i2);
					finish();
					break;
					
				case 3:
					Intent i3=new Intent(this, AgentPersonalActivity.class);
					i3.putExtra("pics", (Serializable)pics);
					setResult(ATERSHOUPIC, i3);
					finish();
					break;
				case 4:
					Intent i4=new Intent(this, AgentPersonalActivity.class);
					i4.putExtra("pics", (Serializable)pics);
					setResult(ATRentingPIC, i4);
					finish();
					break;
				}
				
				
				
				
				
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void onUploadProcess(int uploadSize) {
		
	}

	@Override
	public void initUpload(int fileSize) {
		// TODO Auto-generated method stub
		
	}
	
	

}
