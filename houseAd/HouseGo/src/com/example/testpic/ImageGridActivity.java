package com.example.testpic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import util.FileUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.dumu.housego.R;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.utils.CreateBmpFactory;
import com.dumu.housego.utils.UploadUtil;
import com.dumu.housego.utils.UploadUtil.OnUploadProcessListener;
import com.example.testpic.ImageGridAdapter.TextCallback;

public class ImageGridActivity extends BaseActivity implements
		OnUploadProcessListener {
	public static final String EXTRA_IMAGE_LIST = "imagelist";

	List<ImageItem> dataList;
	GridView gridView;
	ImageGridAdapter adapter;
	AlbumHelper helper;
	Button bt;
	CreateBmpFactory bmpFactory = null;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(ImageGridActivity.this, "最多选择9张图片", 400).show();
				break;

			default:
				break;
			}
		}
	};

	private UserInfo userinfo;

	private String userid;
	ArrayList<String> list;

	private TextView tv_imagie_view;
	public static ArrayList<Pics> pics = new ArrayList<Pics>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_image_grid);

		bmpFactory = new CreateBmpFactory(ImageGridActivity.this);
		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());

		dataList = (List<ImageItem>) getIntent().getSerializableExtra(
				EXTRA_IMAGE_LIST);
		userinfo = HouseGoApp.getLoginInfo(getApplicationContext());
		userid = userinfo.getUserid();

		initView();
		bt = (Button) findViewById(R.id.bt);
		tv_imagie_view = (TextView) findViewById(R.id.tv_imagie_view);

		tv_imagie_view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();

			}
		});

		bt.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				bt.setEnabled(false);

				list = new ArrayList<String>();
				Collection<String> c = adapter.map.values();
				Iterator<String> it = c.iterator();
				for (; it.hasNext();) {
					list.add(it.next());
				}

				// if (Bimp.act_bool) {
				// Intent intent = new Intent(ImageGridActivity.this,
				// PublishedActivity.class);
				// startActivity(intent);
				// Bimp.act_bool = false;
				// }
				for (int i = 0; i < list.size(); i++) {
					if (Bimp.drr.size() < 9) {
						String path = list.get(i);

						// BitmapFactory.Options options = new
						// BitmapFactory.Options();
						// options.inJustDecodeBounds = true; //
						// 只获取图片的大小信息，而不是将整张图片载入在内存中，避免内存溢出
						// BitmapFactory.decodeFile(path, options);
						// int height = options.outHeight;
						// int width= options.outWidth;
						// int inSampleSize = 2; //默认像素压缩比例，压缩为原图的1/2
						// int minLen = Math.min(height, width); // 原图的最小边长
						// if(minLen > 100) {
						// //如果原始图像的最小边长大于100dp（此处单位我认为是dp，而非px）
						// float ratio = (float)minLen / 100.0f; // 计算像素压缩比例
						// inSampleSize = (int)ratio;
						// }
						// options.inJustDecodeBounds = false; //
						// 计算好压缩比例后，这次可以去加载原图了
						// options.inSampleSize = inSampleSize; // 设置为刚才计算的压缩比例
						// Bitmap bm = BitmapFactory.decodeFile(path, options);

//						BitmapFactory.Options options = new BitmapFactory.Options();
//						//options.inSampleSize = 4; // 等于数字n即代表压缩成原来的1/n，当数字小于1时会被当成1
//						options.inJustDecodeBounds = false; // 该属性表示是否只是处理图片的一些宽高值。当值为true时，decodeFile()方法返回null，但是options里面的outHeight/outWidth等参数还是会得到对应值
//						options.inPreferredConfig = Bitmap.Config.RGB_565;
//						options.inDither = true; // 是否抖动
//						Bitmap bm = BitmapFactory.decodeFile(path);

						Bitmap bm = bmpFactory.getBitmapByOpt(path);
						if (bm != null)
							uploadpic(path, bm);
					}
				}

			}

		});
	}

	protected void uploadpic(String path, Bitmap bm) {
		File f = null;
		FileOutputStream out = null;
		try {
			if (!FileUtils.isFileExist("")) {
				File tempf = FileUtils.createSDDir("");
			}
			f = new File(path);
			if (f.exists()) {
				f.delete();
			}
			out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 70, out);
			out.flush();
			out.close();

			bm.recycle();
			bm = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Log.e("ffffff", "ffffffffffffffff=" + f.toString());
		;

		String filekey = "img";
		UploadUtil uploadutil = UploadUtil.getInstance();
		uploadutil.setOnUploadProcessListener(this);

		Map<String, String> m = new HashMap<String, String>();
		m.put("userid", userid);
		m.put("catid", "6");
		m.put("module", "content");
		String RequestURL = UrlFactory.PostupLoadPic();
		uploadutil.uploadFile(f, filekey, RequestURL, m);

	}

	/**
	 * 鍒濆鍖杤iew瑙嗗浘
	 */
	private void initView() {
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new ImageGridAdapter(ImageGridActivity.this, dataList,
				mHandler);
		gridView.setAdapter(adapter);
		adapter.setTextCallback(new TextCallback() {
			public void onListen(int count) {
				// bt.setText("完成" + "(" + count + ")");
				bt.setText("完成");
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// if (dataList.size() >= 2) {
				// MyToastShowCenter.CenterToast(getApplicationContext(),
				// "只能选一张");
				// list.remove(2);
				// }
				adapter.notifyDataSetChanged();
			}
		});
	}

	public void onUploadDone(int responseCode, String message) {
		Pics p = new Pics();
		try {
			JSONObject j = new JSONObject(message);
			String url = j.getString("url");
			p.setUrl(url);
			// String o = j.getString("picname");
			p.setAlt("图片");
			if (url.startsWith("http")) {
				Bimp.drr.add(url);
			} else {
				Bimp.drr.add(url);
			}

			pics.add(p);

			//if (list.size() == pics.size()) {
				finish();
			//}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void onUploadProcess(int uploadSize) {
	}

	public void initUpload(int fileSize) {
	}
}
