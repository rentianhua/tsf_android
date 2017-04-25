package com.example.testpic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.dumu.housego.R;
import com.dumu.housego.activity.BaseActivity;
import com.dumu.housego.entity.Pics;
import com.dumu.housego.util.UrlFactory;

public class PhotoActivity extends BaseActivity {
    private ViewPager pager;
    private MyPageAdapter adapter;
    private int curSel = 0;
    public List<Pics> images = new ArrayList<Pics>();

    RelativeLayout photo_relativeLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        images = (List<Pics>) getIntent().getSerializableExtra(
                "pics");

        photo_relativeLayout = (RelativeLayout) findViewById(R.id.photo_relativeLayout);
        photo_relativeLayout.setBackgroundColor(0x70000000);

        Button photo_bt_exit = (Button) findViewById(R.id.photo_bt_exit);
        photo_bt_exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Button photo_bt_del = (Button) findViewById(R.id.photo_bt_del);
        photo_bt_del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (images.size() > 0 && images.size() > curSel) {
                    images.remove(curSel);
                    adapter = new MyPageAdapter();
                    pager.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                    if (images.size() > 1) {
                        if (curSel >= images.size()) {
                            pager.setCurrentItem(images.size() - 1);
                        } else if (curSel < 0) {
                            pager.setCurrentItem(0);
                        } else {
                            pager.setCurrentItem(curSel);
                        }
                    } else {
                        pager.setCurrentItem(0);
                    }
                } else {
                    if (images.size() > 0) {
                        images.remove(0);
                        adapter = new MyPageAdapter();
                        pager.setAdapter(adapter);
                    }
                }
            }
        });

        Button photo_bt_enter = (Button) findViewById(R.id.photo_bt_enter);
        photo_bt_enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PhotoActivity.this, PublishedActivity.class);
                intent.putExtra("pics", (Serializable) images);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setOnPageChangeListener(pageChangeListener);
        adapter = new MyPageAdapter();
        pager.setAdapter(adapter);
        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", 0);
        pager.setCurrentItem(id);
    }

    private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
        public void onPageSelected(int index) {// 页面选择响应函数
            curSel = index;
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
    };

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }

    class MyPageAdapter extends PagerAdapter {
        public int getCount() {
            return images == null ? 0 : images.size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        public Object instantiateItem(ViewGroup container, int position) {// 返回view对象
            ImageView imageView = new ImageView(PhotoActivity.this);
            //imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setBackgroundColor(0xff000000);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));

            Pics p = images.get(position);
            if (p.getImagepath() != null && p.getImagepath().length() > 0)
                Glide.with(PhotoActivity.this).load(p.getImagepath()).into(imageView);
            else if (p.getUrl() != null && p.getUrl().length() > 0)
                Glide.with(PhotoActivity.this).load(UrlFactory.TSFURL + p.getUrl()).into(imageView);
            else {
            }

            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {// 销毁view对象
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}
