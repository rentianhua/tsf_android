package com.dumu.housego.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dumu.housego.AgentPersonalActivity;
import com.dumu.housego.PuTongMyGuanZhuActivity;
import com.dumu.housego.R;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.utils.UploadUtil.OnUploadProcessListener;
/**android��gridView ���ͼƬ��ɾ��ͼƬ*/
public class SelectionActivity extends BaseActivity implements OnUploadProcessListener,OnClickListener {
	/** ���磬���ڶ�̬��ʾ���ɾ��ͼƬ */
	private GridView gv;
	/** ������ */
	private MyGridViewAdapter adapter;
	/** �����б� */
	private List<Bitmap> viewList;
	/** ��������� */
	private LayoutInflater inflater;
	/** ���͸���Ĳ��֣������ṩ��ᣬ������Ĳ���ѡ� */
	private LinearLayout sendmoreLyt;
	/** BMP���칤�������ڻ������ͼ�������������������ɵ�ͼƬ�������Լ��� */
	private CreateBmpFactory2 mCreateBmpFactory;
	List<Pics>pics=new ArrayList<Pics>();
	private UserInfo userinfo;
	private boolean IsFirstIn=true;
	private String TAG;
	

	public static final int ATERSHOUPIC =20;
	public static final int ATRentingPIC =21;
	
	public static final int PTERSHOUPIC =22;
	public static final int PTRentingPIC =23;
	
	private EditText etname;
	
	private ImageView uploadpic;
	private TextView activity_selectimg_send;
	
	private List<String>Urls=new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.main);
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		// ͼ�������BMPҵ��
		sendmoreLyt = (LinearLayout) this.findViewById(R.id.layout_sendmore);
		this.findViewById(R.id.sendCamera).setOnClickListener(this);
		this.findViewById(R.id.sendPic).setOnClickListener(this);
		mCreateBmpFactory = new CreateBmpFactory2(this);

		// ��ɾͼƬ����ҵ��
		
		
		gv = (GridView) this.findViewById(R.id.gridView);
		viewList = new ArrayList<Bitmap>();
		viewList.add(null);
		adapter = new MyGridViewAdapter();
		gv.setAdapter(adapter);

		super.onCreate(savedInstanceState);
		
		uploadpic=(ImageView) findViewById(R.id.uploadpic);
		activity_selectimg_send=(TextView) findViewById(R.id.activity_selectimg_send);
		
		setListener();
		
	}
	
	
	
	
	private void setListener() {
		uploadpic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bimp.tempSelectBitmap.clear();
				Bimp.max = 0;
				SelectionActivity.this.finish();
				
			}
		});
		
		activity_selectimg_send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ChuanPic();
			}
		});
		
	}
		




	@SuppressWarnings("unchecked")
	protected void onResume() {
		TAG=getIntent().getStringExtra("TAG");
		
//		if(IsFirstIn){
			
			if(getIntent().getSerializableExtra("pics")!=null){
				pics=(List<Pics>) getIntent().getSerializableExtra("pics");
				Log.e("pics", "selections,pics="+pics.toString());
				showPics();
			}
//			
//			IsFirstIn=false;
//		}
		
		super.onResume();
		
	}
	

	private void showPics() {

//			Message msg=new Message();
//			msg.what=4;
//			mHandler.sendMessage(msg);
		Thread thread=new Thread(new Runnable() {
			public void run() {
				Bitmap bm=null;
        		for (Pics p  : pics) {
        			if(p.getUrl().startsWith("http")){
        				bm=GetLocalOrNetBitmap(p.getUrl());
        			}else{
        				bm=GetLocalOrNetBitmap(UrlFactory.TSFURL+p.getUrl());
        			}
        			 
        			viewList.add(0,bm);
        			
        		}
        		
        	Message msg=new Message();
        	msg.what=1;
        	mHandler.sendMessage(msg);
        		
        		
			}
		});
		
		thread.start();
		
		
	}






	public class MyGridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (viewList == null) {
				return 0;
			}
			return viewList.size();
		}

		@Override
		public Object getItem(int position) {
			return viewList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			inflater = LayoutInflater.from(SelectionActivity.this);
			if (position == viewList.size() - 1) {
				View addView = inflater.inflate(R.layout.gv_item_add, null);
				ImageView ivadd=(ImageView) addView.findViewById(R.id.add);
				if (viewList.size() == 10) {
					ivadd.setVisibility(View.GONE);
				} else {
					
					ivadd.setOnClickListener(
							new OnClickListener() {

								@Override
								public void onClick(View v) {
									sendmoreLyt.setVisibility(View.VISIBLE);
								}
							});
					
				}
			
				return addView;
			} else {
				
				View picView = inflater.inflate(R.layout.gv_item_pic, null);
				ImageButton picIBtn = (ImageButton) picView
						.findViewById(R.id.pic);
				picIBtn.setImageBitmap(viewList.get(position));
				picIBtn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						
					}
				});
				
				picView.findViewById(R.id.delete).setOnClickListener(
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								Message msg=new Message();
								msg.what=2;
								msg.obj=position;
								mHandler.sendMessage(msg);
								
							}
						});
				
				
			etname=(EditText) picView.findViewById(R.id.et_picname);
			etname.setFocusableInTouchMode(true);
			etname.setFocusable(true);
			Log.i("positionpositionpositionpositionposition", "position="+position);
//				etname.setText(pics.get(position-1).getAlt());
				return picView;
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sendCamera:
			mCreateBmpFactory.OpenCamera();
			sendmoreLyt.setVisibility(View.GONE);
			break;

		default:
			mCreateBmpFactory.OpenGallery();
			sendmoreLyt.setVisibility(View.GONE);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String picPath = mCreateBmpFactory.getBitmapFilePath(requestCode,
				resultCode, data);
		Bitmap bmp = mCreateBmpFactory.getBitmapByOpt(picPath);
		if (bmp != null) {
			
			File f = null ;
			FileOutputStream out=null;
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
			
			Log.e("ffffff", "ffffffffffffffff="+f.toString());
			
			String filekey="img";
			UploadUtil uploadutil=UploadUtil.getInstance();
			uploadutil.setOnUploadProcessListener(this);

			Map<String, String >m=new HashMap<String, String>();
			m.put("userid", userinfo.getUserid());
			m.put("catid", "6");
			m.put("module", "content");
			String RequestURL=UrlFactory.PostupLoadPic();
			uploadutil.uploadFile(f, filekey, RequestURL, m);
			
			
	
		}
		super.onActivityResult(requestCode, resultCode, data);
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
			
			pics.add(0,p);
			Log.e("pics", "pics="+pics.toString());
			Bitmap bm=GetLocalOrNetBitmap(UrlFactory.TSFURL+p.getUrl());
			
					viewList.add(0,bm);
		Message msg=new Message();
		msg.what=1;
		mHandler.sendMessage(msg);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	
	}
	

	   public Handler mHandler=new Handler()  
	    {  
	        public void handleMessage(Message msg)  
	        {  
	            switch(msg.what)  {  
	            case 1:  
	            	adapter.notifyDataSetChanged();
	                break;  
	            case 2:  
	            	int position=(Integer) msg.obj;
	            	viewList.remove(position);
					pics.remove(position);
	            	adapter.notifyDataSetChanged();
	                break;  
	                
	    
	            }  
	            super.handleMessage(msg);  
	        }  
	    }; 
	
	

		protected void ChuanPic() {
//			Bimp.tempSelectBitmap.clear();
				//返回代码
				switch (Integer.valueOf(TAG)) {
				case 1:
					Intent i1=new Intent(this, PuTongMyGuanZhuActivity.class);
					i1.putExtra("uploadpics", (Serializable)pics);
					setResult(PTERSHOUPIC, i1);
					finish();
					break;
				case 2:
					Intent i2=new Intent(this, PuTongMyGuanZhuActivity.class);
	 				i2.putExtra("uploadpics", (Serializable)pics);
					setResult(PTRentingPIC, i2);
					finish();
					break;
					
				case 3:
					Intent i3=new Intent(this, AgentPersonalActivity.class);
					i3.putExtra("uploadpics", (Serializable)pics);
					setResult(ATERSHOUPIC, i3);
					finish();
					break;
				case 4:
					Intent i4=new Intent(this, AgentPersonalActivity.class);
					i4.putExtra("uploadpics", (Serializable)pics);
					setResult(ATRentingPIC, i4);
					finish();
					break;
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
	
	
	
	
	/**
	 * 网络图片转换为Bitmap
	 *
	 * @Author: ChengBin
	 * @Time: 16/4/5 下午2:41
	 */
	public static Bitmap netPicToBmp(String src) {
	    try {
	        Log.d("FileUtil", src);
	        URL url = new URL(src);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);

	        //设置固定大小
	        //需要的大小
	        float newWidth = 200f;
	        float newHeigth = 200f;

	        //图片大小
	        int width = myBitmap.getWidth();
	        int height = myBitmap.getHeight();

	        //缩放比例
	        float scaleWidth = newWidth / width;
	        float scaleHeigth = newHeigth / height;
	        Matrix matrix = new Matrix();
	        matrix.postScale(scaleWidth, scaleHeigth);

	        Bitmap bitmap = Bitmap.createBitmap(myBitmap, 0, 0, width, height, matrix, true);
	        return bitmap;
	    } catch (IOException e) {
	        // Log exception
	        return null;
	    }
	}
	
	
	public static Bitmap GetLocalOrNetBitmap(String url) 
	 { 
	  Bitmap bitmap = null; 
	  InputStream in = null; 
	  BufferedOutputStream out = null; 
	  try 
	  { 
	   in = new BufferedInputStream(new URL(url).openStream(), 1024); 
	   final ByteArrayOutputStream dataStream = new ByteArrayOutputStream(); 
	   out = new BufferedOutputStream(dataStream, 1024); 
	   copy(in, out); 
	   out.flush(); 
	   byte[] data = dataStream.toByteArray(); 
	   bitmap = BitmapFactory.decodeByteArray(data, 0, data.length); 
	   data = null; 
	   return bitmap; 
	  } 
	  catch (IOException e) 
	  { 
	   e.printStackTrace(); 
	   return null; 
	  } 
	 } 
	
	private static void copy(InputStream in, OutputStream out)
            throws IOException {
        byte[] b = new byte[1024];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
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
}