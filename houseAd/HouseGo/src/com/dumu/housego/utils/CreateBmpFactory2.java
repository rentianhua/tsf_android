package com.dumu.housego.utils;

import java.io.File;
import java.io.IOException;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.WindowManager;
import android.widget.Toast;

public class CreateBmpFactory2 {

	// ----------���ͼƬ��ҵ�����
	private static final int PHOTO_REQUEST_CAREMA = 1;// ����
	private static final int PHOTO_REQUEST_GALLERY = 2;// �������ѡ��

	private Fragment fragment;
	private Activity activity;
	private File tempFile;

	public CreateBmpFactory2(Fragment fragment) {
		super();
		this.fragment = fragment;
		WindowManager wm = (WindowManager) fragment.getActivity()
				.getSystemService("window");
		windowHeight = wm.getDefaultDisplay().getHeight();
		windowWidth = wm.getDefaultDisplay().getWidth();
	}

	int windowHeight;
	int windowWidth;
	private Uri imageUri;
	private File outputImage;

	public CreateBmpFactory2(Activity activity) {
		super();
		this.activity = activity;
		WindowManager wm = (WindowManager) activity.getSystemService("window");
		windowHeight = wm.getDefaultDisplay().getHeight();
		windowWidth = wm.getDefaultDisplay().getWidth();
	}

	
	public void OpenGallery() {
		
//		Intent intent = new Intent(Intent.ACTION_PICK);
//		intent.setType("image/*");
		if (fragment != null) {
			
			if (ContextCompat.checkSelfPermission(fragment.getContext(),
					Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(fragment.getActivity(),
						new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1);
			} else {
			
			Intent intent;
			if (Build.VERSION.SDK_INT < 19) {
				intent = new Intent();
				intent.setAction(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				fragment.startActivityForResult(Intent.createChooser(intent, "选择图片"), PHOTO_REQUEST_GALLERY);
			} else {
				intent = new Intent(Intent.ACTION_PICK, Images.Media.EXTERNAL_CONTENT_URI);
				intent.setType("image/*");
				fragment.startActivityForResult(Intent.createChooser(intent, "选择图片"), PHOTO_REQUEST_GALLERY);
			}
			}
		} else {
			
			if (ContextCompat.checkSelfPermission(activity,
					Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(activity,
						new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1);
			} else {
			
			Intent intent;
			if (Build.VERSION.SDK_INT < 19) {
				intent = new Intent();
				intent.setAction(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				activity.startActivityForResult(Intent.createChooser(intent, "选择图片"), PHOTO_REQUEST_GALLERY);
			} else {
				intent = new Intent(Intent.ACTION_PICK, Images.Media.EXTERNAL_CONTENT_URI);
				intent.setType("image/*");
				activity.startActivityForResult(Intent.createChooser(intent, "选择图片"), PHOTO_REQUEST_GALLERY);
			}
			
			}
		}
		
		
	}

	
	
	
	
	public void OpenCamera() {
		// 照相
		 
       
		if (fragment != null) {
			// 创建File对象，用于存储拍照后的图片
		       outputImage = new File(fragment.getContext().getExternalCacheDir(), "output_image.jpg");
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
		           imageUri = FileProvider.getUriForFile(fragment.getContext(), "com.example.cameraalbumtest.fileprovider", outputImage);
		       }
		       
		       // 启动相机程序
		       Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		       intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			
			fragment.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
		} else {
			// 创建File对象，用于存储拍照后的图片
		       outputImage = new File(activity.getExternalCacheDir(), "output_image.jpg");
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
		           imageUri = FileProvider.getUriForFile(activity, "com.example.cameraalbumtest.fileprovider", outputImage);
		       }
		       // 启动相机程序
		       Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		       intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			activity.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
		}
	}
	
	
	

	public String getBitmapFilePath(int requestCode, int resultCode, Intent data) {
		if (requestCode == PHOTO_REQUEST_GALLERY) {
			if (data != null) {
				Uri uri = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = null;
				if (fragment != null) {
					cursor = fragment.getActivity().getContentResolver()
							.query(uri, filePathColumn, null, null, null);
				} else {
					cursor = activity.getContentResolver().query(uri,
							filePathColumn, null, null, null);
				}
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();
				return picturePath;
			}
		} else if (requestCode == PHOTO_REQUEST_CAREMA) {
			if (hasSdcard()) {
				return outputImage.getAbsolutePath();
			} else {
				if (fragment != null) {
					Toast.makeText(fragment.getActivity(), "δ�ҵ��洢�����޷��洢��Ƭ��", 0)
							.show();
				} else {
					Toast.makeText(activity, "δ�ҵ��洢�����޷��洢��Ƭ��", 0).show();
				}
			}
		}
		return null;
	}

	private int aspectX = 0;
	private int aspectY = 0;

	public Bitmap getBitmapByOpt(String picturePath) {
		Options opt = new Options();
		opt.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(picturePath, opt);
		int imgHeight = opt.outHeight;
		int imgWidth = opt.outWidth;
		int scaleX = imgWidth / windowWidth;
		int scaleY = imgHeight / windowHeight;
		int scale = 1;
		if (scaleX > scaleY & scaleY >= 1) {
			scale = scaleX;
		}
		if (scaleY > scaleX & scaleX >= 1) {
			scale = scaleY;
		}
		opt.inJustDecodeBounds = false;
		opt.inSampleSize = scale;
		return BitmapFactory.decodeFile(picturePath, opt);
	}

	private boolean hasSdcard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
}
