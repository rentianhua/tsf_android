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

import util.BigPicActivity;
import util.Bimp;
import util.FileUtils;
import util.ImageItem;
import util.PublicWay;
import util.Res;
import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.dumu.housego.AgentPersonalActivity;
import com.dumu.housego.PuTongMyGuanZhuActivity;
import com.dumu.housego.R;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.UpLoadPic;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.presenter.HouseUpLoadPicPresenter;
import com.dumu.housego.presenter.IHouseUpLoadPicPresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.utils.UploadUtil;
import com.dumu.housego.utils.UploadUtil.OnUploadProcessListener;
import com.dumu.housego.view.IHouseUploadPicView;

public class ImageGrallyMain2 extends BaseActivity implements OnUploadProcessListener, IHouseUploadPicView{

	private GridView noScrollgridview;
	private GridAdapter adapter;
	private View parentView;
	private PopupWindow pop = null;
	private LinearLayout ll_popup;
	public static Bitmap bimap ;
	private boolean IsFirstIn=true;

	
	   public Handler mHandler=new Handler()  
	    {  
	        public void handleMessage(Message msg)  
	        {  
	            switch(msg.what)  
	            {  
	            case 1:  
	            	int position=(Integer) msg.obj;
//	            	pics.remove(position);
//					Bimp.tempSelectBitmap.remove(position);
//					adapter.update();
					
					if (pics.size() == 1) {
						Bimp.tempSelectBitmap.clear();
						pics.remove(position);
						Bimp.max = 0;
						adapter.update();;
//						Intent intent = new Intent("data.broadcast.action");  
//						sendBroadcast(intent);  
//						finish();
						
					} else {
						Bimp.tempSelectBitmap.remove(position);
						Bimp.max--;
//						pager.removeAllViews();
						pics.remove(position);
						adapter.update();;
					}
					
					
					
					
	                break;  
	            default:  
	                break;        
	            }  
	            super.handleMessage(msg);  
	        }  
	    }; 
	
	
	
	public static final int ATERSHOUPIC =20;
	public static final int ATRentingPIC =21;
	
	public static final int PTERSHOUPIC =22;
	public static final int PTRentingPIC =23;

	private static RequestQueue mSingleQueue;
	private static String TAGG= "test";
	
	  public static final int TAKE_PHOTO = 1;

	    public static final int CHOOSE_PHOTO = 2;

	    private ImageView picture;

	    private Uri imageUri;
	    
	private ImageView uploadpic;
	private TextView activity_selectimg_send;
	private IHouseUpLoadPicPresenter presenter;
	private String userid;
	private String catid;
	private UserInfo userinfo;
	
	private String IMAGEPATH;
	
	List<Pics>pics=new ArrayList<Pics>();
	
	private String TAG;
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
		FontHelper.injectFont(findViewById(android.R.id.content));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onResume() {
		TAG=getIntent().getStringExtra("TAG");
		
		if(IsFirstIn){
			
			if(getIntent().getSerializableExtra("pics")!=null){
				pics=(List<Pics>) getIntent().getSerializableExtra("pics");
				showPics();
			}
			
			IsFirstIn=false;
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
	
		Log.e("", "执行了onresume");
		Bitmap bm;
		String imagepath;
		Log.e("pics=", pics.toString());
		
		for (Pics p  : pics) {
			ImageItem takePhoto = new ImageItem();
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = false;
			options.inPreferredConfig = Config.RGB_565;
			options.inDither = true;
			bm=BitmapFactory.decodeFile(p.getImagepath());
			imagepath=p.getImagepath();
			takePhoto.setImagePath(imagepath);
			takePhoto.setBitmap(bm);
			
			Bimp.tempSelectBitmap.add(takePhoto);
			Bimp.tempSelectBitmap.toString();
			Log.e("", "执行了onresume");
		}
		adapter.update();
		
	}

	private void setlistener() {
		uploadpic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bimp.tempSelectBitmap.clear();
				Bimp.max = 0;
				ImageGrallyMain2.this.finish();
				
			}
		});
		
		activity_selectimg_send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ChuanPic();
			}
		});
		
	}

	protected void ChuanPic() {
		Bimp.tempSelectBitmap.clear();
		Bimp.max = 0;
			//返回代码
			switch (Integer.valueOf(TAG)) {
			case 1:
				Intent i1=new Intent(ImageGrallyMain2.this, PuTongMyGuanZhuActivity.class);
				i1.putExtra("uploadpics", (Serializable)pics);
				setResult(PTERSHOUPIC, i1);
				finish();
				break;
			case 2:
				Intent i2=new Intent(ImageGrallyMain2.this, PuTongMyGuanZhuActivity.class);
 				i2.putExtra("uploadpics", (Serializable)pics);
				setResult(PTRentingPIC, i2);
				finish();
				break;
				
			case 3:
				Intent i3=new Intent(ImageGrallyMain2.this, AgentPersonalActivity.class);
				i3.putExtra("uploadpics", (Serializable)pics);
				setResult(ATERSHOUPIC, i3);
				finish();
				break;
			case 4:
				Intent i4=new Intent(ImageGrallyMain2.this, AgentPersonalActivity.class);
				i4.putExtra("uploadpics", (Serializable)pics);
				setResult(ATRentingPIC, i4);
				finish();
				break;
			}
			
			
		
	}

	private void initView() {
		
		uploadpic=(ImageView) findViewById(R.id.uploadpic);
		activity_selectimg_send=(TextView) findViewById(R.id.activity_selectimg_send);
		
	}

	
	
	
	
	public void Init() {
		
		pop = new PopupWindow(ImageGrallyMain2.this);
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
                   imageUri = FileProvider.getUriForFile(ImageGrallyMain2.this, "com.example.cameraalbumtest.fileprovider", outputImage);
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
				
				if (ContextCompat.checkSelfPermission(ImageGrallyMain2.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ImageGrallyMain2.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
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
					ll_popup.startAnimation(AnimationUtils.loadAnimation(ImageGrallyMain2.this,R.anim.activity_translate_in));
					pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
				} else {
					
					Intent intent = new Intent(ImageGrallyMain2.this,BigPicActivity.class);
					List<String>Url=new ArrayList<String>();
					   for(Pics p : pics){
						String url=  "http://www.taoshenfang.com"+p.getUrl();
						Url.add(url);
					   }
					   
					   Log.e("111111111111111", "Url="+Url.toString());
					   String[]images=Url.toArray(new String[Url.size()]);;
					intent.putExtra("picsUrls", images);
					startActivity(intent);
					
//					Intent intent = new Intent(ImageGrallyMain2.this,
//							GalleryActivity.class);
//					intent.putExtra("position", "1");
//					intent.putExtra("ID", arg2);
//					startActivity(intent);
				}
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
                      
                  	ImageItem takePhoto = new ImageItem();
    				takePhoto.setBitmap(bm);
    				takePhoto.setImagePath(imagepath);
    				Bimp.tempSelectBitmap.add(takePhoto);
    				
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
              		
//              		headpresenter.ChangeHead("149", imagePath);
              		
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
		
        FileUtils.saveBitmap(bm, imagePath);
        
      	ImageItem takePhoto = new ImageItem();
		takePhoto.setBitmap(bm);
		takePhoto.setImagePath(imagePath);
		Bimp.tempSelectBitmap.add(takePhoto);
		
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

		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_published_grida,
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
			
			
			if (position ==Bimp.tempSelectBitmap.size()) {
				
				holder.image.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), R.drawable.icon_addpic_unfocused));
				holder.delete.setVisibility(View.GONE);
				if (position == 9) {
					holder.image.setVisibility(View.GONE);
				}
			} else {
				holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
				holder.delete.setVisibility(View.VISIBLE);
			}
			
			
			holder.delete.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					 Thread thread=new Thread(new Runnable()  
				        {  
				            @Override  
				            public void run()  
				            {  
				            	
				                Message message=new Message();  
				                message.obj=position;
				                message.what=1;  
				                mHandler.sendMessage(message);  
				            }  
				        });  
				        thread.start();  
					}
			});
			
			
			return convertView;
		}

		public class ViewHolder {
			public ImageView image;
			public ImageView delete;
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
		
		Log.e("ffffff", "ffffffffffffffff="+f.toString());
		
		String filekey="img";
		UploadUtil uploadutil=UploadUtil.getInstance();
		uploadutil.setOnUploadProcessListener(this);

		Map<String, String >m=new HashMap<String, String>();
		m.put("userid", userid);
		m.put("catid", catid);
		m.put("module", "content");
		
		uploadutil.uploadFile(f, filekey, RequestURL, m);
	}
	

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
			p.setImagepath(IMAGEPATH);
			
			pics.add(p);
			Log.e("pics", "pics="+pics.toString());
			adapter.update();
			
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
