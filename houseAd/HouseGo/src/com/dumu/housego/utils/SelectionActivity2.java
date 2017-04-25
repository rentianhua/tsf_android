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
import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
public class SelectionActivity2 extends BaseActivity implements OnUploadProcessListener,OnClickListener {
	/** ���磬���ڶ�̬��ʾ���ɾ��ͼƬ */
	private GridView gv;
	/** ������ */
	private MyGridViewAdapter adapter;
	/** �����б� */
	private List<Bitmap> viewList;
	/** ��������� */
	/** ���͸���Ĳ��֣������ṩ��ᣬ������Ĳ���ѡ� */
	private LinearLayout sendmoreLyt;
	/** BMP���칤�������ڻ������ͼ�������������������ɵ�ͼƬ�������Լ��� */
	private CreateBmpFactory2 mCreateBmpFactory;
	List<Pics>pics=new ArrayList<Pics>();
	private UserInfo userinfo;
	private boolean IsFirstIn=true;
	private String TAG;
	private int max=0;
    private Uri imageUri;
	public static final int ATERSHOUPIC =20;
	public static final int ATRentingPIC =21;
	
	public static final int PTERSHOUPIC =22;
	public static final int PTRentingPIC =23;
	
	  public static final int TAKE_PHOTO = 1;

	    public static final int CHOOSE_PHOTO = 2;

	    private ImageView picture;
	private EditText etname;
	
	private ImageView uploadpic;
	private TextView activity_selectimg_send;
	
	private List<String>Urls=new ArrayList<String>();
	private PopupWindow pop;
	private LinearLayout ll_popup;
	private String IMAGEPATH;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectimg);
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		
		uploadpic=(ImageView) findViewById(R.id.uploadpic);
		activity_selectimg_send=(TextView) findViewById(R.id.activity_selectimg_send);
		
		gv = (GridView) this.findViewById(R.id.noScrollgridview);
		viewList = new ArrayList<Bitmap>();
//		Bitmap bm=BitmapFactory.decodeResource(getResources(), R.drawable.addpic);
		viewList.add(null);
		adapter = new MyGridViewAdapter(viewList, this);
		gv.setAdapter(adapter);

			
		Init();

		
		setListener();
		
	}
	
	
	
	
	private void setListener() {
		uploadpic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bimp.tempSelectBitmap.clear();
				Bimp.max = 0;
				SelectionActivity2.this.finish();
				
			}
		});
		
		activity_selectimg_send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ChuanPic();
			}
		});
		
	}
	
public void Init() {
		
		pop = new PopupWindow(SelectionActivity2.this);
		
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
                   imageUri = FileProvider.getUriForFile(SelectionActivity2.this, "com.example.cameraalbumtest.fileprovider", outputImage);
               }
               // 启动相机程序
               Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
               intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
               startActivityForResult(intent, TAKE_PHOTO);
				
				pop.dismiss();
				ll_popup.clearAnimation();
				
			}
		});
		
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				if (ContextCompat.checkSelfPermission(SelectionActivity2.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SelectionActivity2.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
                } else {
                    openAlbum();
                }
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});
}
		




protected void openAlbum() {
	Intent intent = new Intent("android.intent.action.GET_CONTENT");
    intent.setType("image/*");
    startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
	
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
        	if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {
				
                try {
                    // 将拍摄的照片显示出来
                	String url=getContentResolver().openInputStream(imageUri).toString();
                	
                	Log.e("url", "url="+url);
                	Log.e("imageUri", "imageUri="+imageUri);
                	
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                  String   imagepath=imageUri.toString().replace("file://", "");
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inJustDecodeBounds = false;
					options.inPreferredConfig = Config.RGB_565;
					options.inDither = true;
                  Bitmap bm = BitmapFactory.decodeFile(imagepath);
                  
                  FileUtils.saveBitmap(bm, imagepath);
//                  
//              	ImageItem takePhoto = new ImageItem();
//				takePhoto.setBitmap(bm);
//				takePhoto.setImagePath(imagepath);
//				Bimp.tempSelectBitmap.add(takePhoto);
                  Bimp.max++;
				
				IMAGEPATH=imagepath;
                 
              	File f = null ;
          		FileOutputStream out=null;
          		
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
          		
        		String filekey="img";
          		UploadUtil uploadUtil = UploadUtil.getInstance();
          		uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
          		//定义一个Map集合，封装请求服务端时需要的参数
          		Map<String, String> m = new HashMap<String, String>();
          		//在这里根据服务端需要的参数自己来定
          		
//          		headpresenter.ChangeHead("149", imagePath);
          		
          		m.put("userid", userinfo.getUserid());
          		m.put("catid", "6");
          		m.put("module", "content");
          		
          		String RequestURL=UrlFactory.PostupLoadPic();
          		
          		uploadUtil.uploadFile(f, filekey, RequestURL, m);   
                    
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
        case CHOOSE_PHOTO:
            if (resultCode == RESULT_OK) {
                // 判断手机系统版本号
                if (Build.VERSION.SDK_INT >= 19) {
                    // 4.4及以上系统使用这个方法处理图片
                    handleImageOnKitKat(data);
                } else {
                    // 4.4以下系统使用这个方法处理图片
                    handleImageBeforeKitKat(data);
                }
            }
            break;
        default:
            break;
    }
}



@TargetApi(19)
private void handleImageOnKitKat(Intent data) {
    String imagePath = null;
    Uri uri = data.getData();
    Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
    if (DocumentsContract.isDocumentUri(this, uri)) {
        // 如果是document类型的Uri，则通过document id处理
        String docId = DocumentsContract.getDocumentId(uri);
        if("com.android.providers.media.documents".equals(uri.getAuthority())) {
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
    Log.e("path", "path="+path);
    return path;
}

private void displayImage(String imagePath) {
	
	IMAGEPATH=imagePath;
	BitmapFactory.Options options = new BitmapFactory.Options();
	options.inJustDecodeBounds = false;
	options.inPreferredConfig = Config.RGB_565;
	options.inDither = true;
	Bitmap bm = BitmapFactory.decodeFile(imagePath);
	File f = null ;
	FileOutputStream out=null;
	
//    FileUtils.saveBitmap(bm, imagePath);
    
Bimp.max++;
	
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
	
	String filekey="img";
	UploadUtil uploadUtil = UploadUtil.getInstance();
	uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
	//定义一个Map集合，封装请求服务端时需要的参数
	//在这里根据服务端需要的参数自己来定
		//定义一个Map集合，封装请求服务端时需要的参数
		Map<String, String> m = new HashMap<String, String>();
		//在这里根据服务端需要的参数自己来定
		
		
		m.put("userid", userinfo.getUserid());
		m.put("catid", "6");
		m.put("module", "content");
		
		String RequestURL=UrlFactory.PostupLoadPic();
		
		uploadUtil.uploadFile(f, filekey, RequestURL, m);   
	

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
				 max=max+pics.size();
				Bitmap bm=null;
        		for (Pics p  : pics) {
        			if(p.getUrl().startsWith("http")){
        				bm=GetLocalOrNetBitmap(p.getUrl());
        			}else{
        				bm=GetLocalOrNetBitmap(UrlFactory.TSFURL+p.getUrl());
        			}
        			
        			viewList.add(bm);
        			
        		}
        		
        	Message msg=new Message();
        	msg.what=1;
        	mHandler.sendMessage(msg);
        		
        		
			}
		});
		
		thread.start();
		
		
	}






	public class MyGridViewAdapter extends BaseAdapter {
		private  List<Bitmap> viewList;
	private LayoutInflater Inflater;
	private Context context;
		public MyGridViewAdapter(List<Bitmap> viewList,Context context) {
			super();
			this.viewList = viewList;
			this.context = context;
			this.Inflater = LayoutInflater.from(context);
		}

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
			ViewHolder holder = null;
			if (convertView == null) {
				
				convertView = Inflater.inflate(R.layout.item_published_grida,
						parent, false);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.item_grida_image);
				holder.delete = (ImageView) convertView
						.findViewById(R.id.iv_delete);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			if(position==max){
//				Bitmap mp=BitmapFactory.decodeResource(getResources(), R.drawable.addpic);
//			viewList.add(mp);
//			max=+1;
				
				holder.image.setImageResource(R.drawable.addpic);
				
				holder.delete.setVisibility(View.GONE);
				
				holder.image.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						
						ll_popup.startAnimation(AnimationUtils.loadAnimation(SelectionActivity2.this,R.anim.activity_translate_in));
						pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
						
					}
				});
				
			
				
				if (position == 9) {
				holder.image.setVisibility(View.GONE);
			}
			
			
			
			
			}else{
				holder.image.setImageBitmap(viewList.get(position));
				holder.delete.setVisibility(View.VISIBLE);
			}
			
			holder.delete.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Message msg=new Message();
					msg.what=2;
					msg.obj=position;
					mHandler.sendMessage(msg);
				}
			});
			
	
			
			
			return convertView;
		}
		
		public class ViewHolder {
			public ImageView image;
			public ImageView delete;
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

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		String picPath = mCreateBmpFactory.getBitmapFilePath(requestCode,
//				resultCode, data);
//		Bitmap bmp = mCreateBmpFactory.getBitmapByOpt(picPath);
//		if (bmp != null) {
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
//				bmp.compress(Bitmap.CompressFormat.JPEG, 70, out);
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
//			m.put("userid", userinfo.getUserid());
//			m.put("catid", "6");
//			m.put("module", "content");
//			String RequestURL=UrlFactory.PostupLoadPic();
//			uploadutil.uploadFile(f, filekey, RequestURL, m);
//	
//		}
//		super.onActivityResult(requestCode, resultCode, data);
//	}

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
			max=+1;
//			viewList.remove(0);
					viewList.add(0,bm);
					
					Log.e("````````````````", "viewlist="+viewList.toString());
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